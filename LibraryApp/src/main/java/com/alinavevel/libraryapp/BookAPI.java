package com.alinavevel.libraryapp;

import java.net.HttpURLConnection;
import java.net.URL;

public class BookAPI {

    public static void DeleteRequest(String codeToDelete) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(" http://localhost:8080/api-rest-av2022/books/" + codeToDelete);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            if (conn.getResponseCode() == 200)
                System.out.println("Employee deleted");
            else
                System.out.println("Connection failed");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn != null)
                conn.disconnect();
        }
    }
}
