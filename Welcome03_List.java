import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Welcome03_List {
   public static void main(String[] args) {  
      DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/index.xml").load();
      ArrayList<WeatherStation> allstns = ds.fetchList("WeatherStation", "station/station_name", 
             "station/station_id", "station/state",
             "station/latitude", "station/longitude");
      
      System.out.println("Total global stations: " + allstns.size());
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a state abbreviation (e.g., CA): ");
      String state = sc.next();
      
      // Track the southernmost station
      WeatherStation southernmost = allstns.get(0); 
      int count = 0; 
      
      System.out.println("Stations in " + state + ":");
      
      for (WeatherStation ws : allstns) {
         // Existing State Filter Logic
         if (ws.isLocatedInState(state)) {
            System.out.println("  " + ws.getId() + ": " + ws.getName());
            count++;
         }
         
         // New Southernmost Logic: Compare latitudes
         if (ws.getLat() < southernmost.getLat()) {
            southernmost = ws;
         }
      }
      
      System.out.println("---------------------------------------");
      System.out.println("Total stations found in " + state + ": " + count);
      System.out.println("Southernmost station: " + southernmost.getName() + 
                         " (Lat: " + southernmost.getLat() + ")");
      System.out.println("---------------------------------------");
      sc.close();
   }
}
