package com.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter extends DataConstants{

   


    public static boolean saveUsers(List<User> users) {
        if (users == null) {
            for (User u : users) {
                if (u == null) continue; {
                    Map<String, Object> obj = new HashMap<>();
                    obj.put(USER_ID_KEY, u.getId().toString());
                    obj.put(USER_FIRSTNAME, u.getFirstName());
                    obj.put(USER_LASTNAME, u.getLastName());
                    obj.put(USER_USERNAME, u.getUsername());
                    obj.put(USER_PASSWORD, u.getPassword());
                    obj.put(USER_EMAIL, u.getEmail());
                    obj.put(USER_LOCATION, u.getLocation() != null ? u.getLocation().toString() : null);
                    obj.put(USER_SHARE_LOCATION, u.isShareLocation());
                }
            }
        }
        return writeToFile(USER_FILE_NAME, ((Object) users).json());

        
    }

    public static boolean saveShelters(List<Shelter> shelters) {
        if (shelters == null) {
            for (Shelter s : shelters) {
                if (s == null) continue; {
                    Map<String, Object> obj = new HashMap<>();
                    obj.put(SHELTER_ID_KEY, s.getId().toString());
                    obj.put(SHELTER_NAME, s.getName());
                    obj.put(SHELTER_CAPACITY, s.getCapacity());
                    obj.put(SHELTER_RESOURCES, s.getResources());
                }
            }
        }
        return writeToFile(SHELTER_FILE_NAME, shelters.json());
    }

    public boolean saveReliefRequests(List<ReliefRequest> requests) {
        if (requests == null) {
            for (ReliefRequest r : requests) {
                if (r == null) continue; {
                    Map<String, Object> obj = new HashMap<>();
                    obj.put(REQUEST_ID_KEY, r.getId().toString());
                    obj.put(REQUEST_TYPE, r.getType().toString());
                    obj.put(REQUEST_URGENCY, r.getUrgency().toString());
                    obj.put(REQUEST_SEVERITY, r.getSeverity().toString());
                    obj.put(REQUEST_LOCATION, r.getLocation() != null ? r.getLocation().toString() : null);
                }
            }
        }
        return writeToFile(REQUEST_FILE_NAME, requests.json());
          
    }

    public boolean saveHurricanes(List<Hurricane> hurricanes) {
        if (hurricanes == null) {
            for (Hurricane h : hurricanes) {
                if (h == null) continue; {
                    Map<String, Object> obj = new HashMap<>();
                    obj.put(HURRICANE_ID_KEY, h.getId().toString());
                    obj.put(HURRICANE_NAME, h.getName());
                    obj.put(HURRICANE_CATEGORY, h.getCategory());
                    obj.put(HURRICANE_LOCATION, h.getLocation() != null ? h.getLocation().toString() : null);
                }
            }
        }
        return writeToFile(HURRICANE_FILE_NAME, ((Object) hurricanes).json());
        
    }


    private static boolean writeToFile(String fileName, List<Map<String, Object>>, jsonData) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonData);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
