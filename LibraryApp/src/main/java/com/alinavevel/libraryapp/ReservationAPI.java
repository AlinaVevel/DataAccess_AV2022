package com.alinavevel.libraryapp;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.Scanner;

/**
 * The type Reservation api.
 */
public class ReservationAPI {


    /**
     * Get request string.
     *
     * @param isbn the isbn
     * @return the string
     */
    public String getRequest(String isbn){
        HttpURLConnection conn = null;
        String code = null;
        try {
            URL url = new URL("http://localhost:8080/api-rest-av2022/reservation");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    if(jsonObject.get("book").equals(isbn)){
                        code = (String) jsonObject.get("borrower");
                    }
                }
            }
            return code;


        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn != null)
                conn.disconnect();
        }

        return code;
    }

    public boolean getRequestUser(String codeUser){
        HttpURLConnection conn = null;

        try {
            URL url = new URL("http://localhost:8080/api-rest-av2022/reservation");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    if(jsonObject.get("borrower").equals(codeUser)){
                        return true;
                    }
                }
            }

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn != null)
                conn.disconnect();
        }

        return false;
    }

    public boolean getRequestBook(String codeUser){
        HttpURLConnection conn = null;

        try {
            URL url = new URL("http://localhost:8080/api-rest-av2022/reservation");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    if(jsonObject.get("book").equals(codeUser)){
                        return true;
                    }
                }
            }

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn != null)
                conn.disconnect();
        }

        return false;
    }


    /**
     * Post request.
     *
     * @param reservation the reservation
     */
    public void postRequest(ReservationJPAEntityFinal reservation){
        HttpURLConnection conn = null;
        try {
            String jsonInputString = new JSONObject()
                    .put("book", reservation.getBook())
                    .put("borrower", reservation.getBorrower())
                    .put("date", reservation.getDate()).toString();
            URL url = new URL("http://localhost:8080/api-rest-av2022/reservation");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            if (conn.getResponseCode() == 200)
                System.out.println("Employee inserted");
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

    /**
     * Delete request.
     *
     * @param id the id reservation
     */
    public void deleteRequest(String id){
        HttpURLConnection conn = null;
        try {
            URL url = new URL(" http://localhost:8080/api-rest-av2022/books/{idreservation}" + id);
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
