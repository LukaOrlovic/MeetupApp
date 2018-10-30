/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conn;

import domain.City;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author orlov
 */
public class Connect{

    private static Connect instance = null;
    private LinkedList<City> cities = new LinkedList<>();

    public static Connect getInstance(){
        if (instance == null){
            instance = new Connect();
        }

        return instance;
    }

    public StringBuffer conn(String url){

        StringBuffer response = new StringBuffer();
        try{

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            //System.out.println("\nSending 'GET' request to URL : " + url);
            //System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();

        } catch (MalformedURLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public void evaluate(int no, StringBuffer response) throws JSONException{

        switch (no){

            case -1:

                JSONObject jsonObj = new JSONObject(response.toString());
                JSONArray ja_data = jsonObj.getJSONArray("results");
                int length = ja_data.length();
                int serialNo = 0;
                for (int i = 0; i < length; i++) {

                    JSONObject jsonObj1 = ja_data.getJSONObject(i);

                    if (jsonObj1.getString("country").equals("rs")){
                        City c = new City(serialNo, jsonObj1.getString("city"));
                        System.out.println(++serialNo + " " + jsonObj1.getString("city"));
                        cities.add(c);
                    }

                }
                break;

            default:
                JSONArray jsonarray = new JSONArray(response.toString());

                int number = 0;

                for (int i = 0; i < jsonarray.length(); i++) {

                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    JSONObject venue = jsonobject.getJSONObject("venue");

                    if (venue.getString("city").equals(cities.get(no).getName())){

                        System.out.println("Meetup number: " + ++number);

                        String name = jsonobject.getString("name");
                        System.out.println("Meetup name: " + name);

                        String link = jsonobject.getString("link");
                        System.out.println("Meetup link: " + link);

                        JSONObject group = jsonobject.getJSONObject("group");
                        System.out.println("Group name: " + group.getString("name"));

                        System.out.println("\n");
                    }

                }
                
                if(number == 0) System.out.println("No events for the chosen city.");
        }
    }

    public LinkedList<City> getCities(){
        return cities;
    }

}
