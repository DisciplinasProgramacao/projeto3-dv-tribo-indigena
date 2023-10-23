import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class App {

    public App() {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        menu.setupMenu(scanner);
    }

    public static void main(String[] args){
        new App();
    }

    public static JSONObject getData(){
        JSONParser parser = new JSONParser();
        JSONObject parsedData = null;

        try (FileReader reader = new FileReader("data.json")) {
            Object obj = parser.parse(reader);
            parsedData = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return parsedData;
    }

    public static void saveFile(String contents){
        try {
            File file = new File("data.json");
            file.createNewFile();
            Files.write(Paths.get("data.json"), contents.getBytes());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
