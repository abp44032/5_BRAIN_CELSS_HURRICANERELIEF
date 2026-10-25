package com.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

public class ReliefRequestList {
    private ArrayList<ReliefRequest> requests = new ArrayList<>();
    public ReliefRequestList() { }
    public ReliefRequestList(ArrayList<ReliefRequest> requests) { 
        this.requests = requests == null ? new ArrayList<>() : new ArrayList<>(requests); 
    }

    public ArrayList<ReliefRequest> getRequests() { 
        return new ArrayList<>(requests);
     }

    public void setRequests(ArrayList<ReliefRequest> requests) { 
        this.requests = requests == null ? new ArrayList<>() : new ArrayList<>(requests);
     }

    public ReliefRequest getRequest(UUID requestID) { 
        return requests.stream().filter(r -> r.getRequestID().equals(requestID)).findFirst().orElse(null);
     }

    public ArrayList<ReliefRequest> getRequestsSortedByPriority() {
         ArrayList<ReliefRequest> sorted = getRequests(); 
         sorted.sort(Comparator.comparingInt(ReliefRequest::priority).reversed()); 
         return sorted; 
    }

    public boolean addRequest(ReliefRequest request) {
         if (request == null || getRequest(request.getRequestID()) != null)
             return false; 
         requests.add(request); 
         return true; 
    }
    public boolean removeRequest(ReliefRequest request) { 
        return requests.remove(request); 
    }
    
    public boolean save() { 
        return DataWriter.saveReliefRequests(requests);
     }
}
