import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Scanner;

public class Utils {
    public static String getClientes(Scanner scanner){
        JSONObject data = App.getData();
        JSONArray jsonArray = (JSONArray) data.get("clientes");
        if (jsonArray.isEmpty()){
            System.out.println("Nao ha clientes registrados.");
            return "";
        } else {
            System.out.println(FontEffects.WHITE_UNDERLINED+"Clientes salvos:"+FontEffects.RESET);
            int count = 0;
            for (Object obj: jsonArray) {
                JSONObject item = (JSONObject) obj;
                System.out.println(FontEffects.WHITE_BOLD_BRIGHT+String.valueOf(count)+"-\t"+"Nome:\t"+FontEffects.RESET + item.get("name"));
                System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"\tID:\t"+FontEffects.RESET + item.get("id"));
                System.out.println(FontEffects.WHITE_UNDERLINED + "                            " + FontEffects.RESET);
                count++;
            }
            System.out.println(FontEffects.WHITE_UNDERLINED+"Digite o index do cliente:"+FontEffects.RESET);
            Integer index = Integer.valueOf(scanner.nextLine());
            for (int i=0;i<jsonArray.size();i++){
                JSONObject obj = (JSONObject) jsonArray.get(i);
                if (i == index){
                    return (String) obj.get("id");
                }
            }
            return "";
        }
    }
}
