import core.data.*;

public class Welcome02_Object {
   public static void main(String[] args) {
      // 1. First Observation (KATL)
      String id1 = "KATL";
      DataSource ds1 = DataSource.connect("https://forecast.weather.gov/xml/current_obs/" + id1 + ".xml"); 
      ds1.setCacheTimeout(15 * 60);  
      ds1.load();
      Observation ob1 = ds1.fetch("Observation", "weather", "temp_f", "wind_degrees");
      System.out.println(id1 + ": " + ob1);
      
      // 2. Second Observation (KSAV)
      String id2 = "KSAV";
      DataSource ds2 = DataSource.connect("https://forecast.weather.gov/xml/current_obs/" + id2 + ".xml"); 
      ds2.setCacheTimeout(15 * 60);  
      ds2.load();
      Observation ob2 = ds2.fetch("Observation", "weather", "temp_f", "wind_degrees");
      System.out.println(id2 + ": " + ob2);

      // 3. Third Observation (KSFO - Your Location)
      String id3 = "KSFO";
      DataSource ds3 = DataSource.connect("https://forecast.weather.gov/xml/current_obs/" + id3 + ".xml"); 
      ds3.setCacheTimeout(15 * 60);  
      ds3.load();
      Observation ob3 = ds3.fetch("Observation", "weather", "temp_f", "wind_degrees");
      System.out.println(id3 + ": " + ob3);
      
      System.out.println("--- Results ---");

      // Logic to find the coldest of all three
      if (ob1.colderThan(ob2) && ob1.colderThan(ob3)) {
         System.out.println("The coldest location is: " + id1);
      } else if (ob2.colderThan(ob1) && ob2.colderThan(ob3)) {
         System.out.println("The coldest location is: " + id2);
      } else {
         System.out.println("The coldest location is: " + id3);
      }
   }
}

class Observation {
   float temp;    
   int windDir;   
   String description;
   
   Observation(String description, float temp, int windDir) {
      this.description = description;
      this.temp = temp;
      this.windDir = windDir;
   }
   
   public boolean colderThan(Observation that) {
      return this.temp < that.temp;
   }
   
   public String toString() {
      return (temp + " degrees; " + description + " (wind: " + windDir + " degrees)");
   }
}
