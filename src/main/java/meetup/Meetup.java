/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetup;

import conn.Connect;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author orlov
 */
public class Meetup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            StringBuffer response = Connect.getInstance().conn("https://api.meetup.com/2/cities?&sign=true");
            
            int no = -1;
            
            Connect.getInstance().evaluate(no, response);

            System.out.println("\n");
            
            System.out.println("Choose a city by number: ");
                        
            while(true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                try {
                    String line = br.readLine();
                    no = Integer.parseInt(line);
                    if(!Connect.getInstance().getCities().isEmpty() && Connect.getInstance().getCities().get(no-1) != null){
                        System.out.println("\n");
                        System.out.println("You chose city: " + Connect.getInstance().getCities().get(no-1).getName());
                        System.out.println("\n");
                        break;
                    }
                } catch (IOException ex) {
                    System.out.println("Number not entered correctly.");
                }
                
            }

            StringBuffer response1 = Connect.getInstance().conn("https://api.meetup.com/self/events?&sign=true&key=173d48636eb254a2b36353d4e1d1242");

            Connect.getInstance().evaluate(no-1, response1);

        } catch (JSONException ex) {
            Logger.getLogger(Meetup.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
