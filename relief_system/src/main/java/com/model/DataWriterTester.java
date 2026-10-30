package com.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWriterTester {

/**
+-------------------------+-------------------------------+
TEST                      |                 REASONING
writing zero users        |    checks that saving an empty user should work 
writing one user          |    checks that one user is saved successfully
writing multiple users    |    checks that mutiple users can be saved at a time - like a family
writing user with role    |    checks that a user's role is included in the saved JSON
writing user with location|    checks that a user's location is included in the saved JSON
+-------------------------+-------------------------------+
 */

}
