package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String, Object> expectedDataMethod(Integer userId, String title, Boolean completed){ //Bazen null deger alacaklari icin Wrapper Class kullandık
        Map<String, Object> expectedData = new HashMap<>();
        if (userId!=null){
            expectedData.put("userId", userId);
        }
        if (title!=null){
            expectedData.put("title", title);
        }
        if (completed!=null){
            expectedData.put("completed", completed);
        }
        return expectedData;
    }

    public static String expectedDataInString(Integer userId, String title, Boolean completed){
        return "{ \"userId\": " + userId + ", \"title\": \"" + title + "\", \"completed\": " + completed + " }";
    }

}
