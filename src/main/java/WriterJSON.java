import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class WriterJSON {
    public String listToJSON(List<Employee> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<List<Employee>>() {}.getType();
        return gson.toJson(list, listType);
    }

    public void writeJSON(String fileName, String json) {
        try (FileWriter writer = new FileWriter(fileName);){
            writer.write(json);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
