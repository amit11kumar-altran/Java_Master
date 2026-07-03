package basetest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JavaMap {

    public static void main(String[] args) {

        // Create a HashMap to store user data
        HashMap<String, Object> userData = new HashMap<>();
        userData.put("id", 1);
        userData.put("name", "John Doe");
        userData.put("email", "john@gmail.com");

        // Print the HashMap
        System.out.println("User Data: " + userData);

        //entryset of map
        Set<Map.Entry<String, Object>> entrySet = userData.entrySet();
        System.out.println("entryset output" + entrySet);
        //entryset output[name=John Doe, id=1, email=john@gmail.com]


        // Iterate over the entry set and print key-value pairs
        userData.entrySet().forEach(entry -> System.out.println(entry.getKey() + "<-key value->" + entry.getValue()));
        userData.entrySet().stream().filter(entry -> entry.getKey().equals("name")).forEach(entry -> System.out.println(entry.getKey() + "<-key using filter value->" + entry.getValue()));

        //update value of key using entryset
        //big catch, stream never modifies original data
        userData.entrySet().stream().filter(entry -> entry.getKey().equals("name")).map(entry -> entry.setValue("Jane " +
                "Doe"));
        System.out.println(userData.get("name"));

        for (Map.Entry<String, Object> entry : userData.entrySet()) {
            if (entry.getKey().equals("name")) {
                entry.setValue("Jane Doe");
            }
        }
        System.out.println(userData.get("name")); // Output: Jane Doe

    }
}
