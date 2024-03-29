package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {

    public Map<String, String> dataMap(String name, String email, String gender, String status){
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("name", name);
        dataMap.put("email", email);
        dataMap.put("gender", gender);
        dataMap.put("status", status);
        return dataMap;
    }

    public Map<String, Object> expectedDataMethod(Object meta, Map<String, String> dataMap){
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("meta", meta);
        expectedData.put("data", dataMap);
        return expectedData;
    }

}
