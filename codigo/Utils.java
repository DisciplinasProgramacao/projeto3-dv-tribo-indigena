import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Utils {
    public static String getClientes(Scanner scanner){
        Data data = new Data();
        if (data.getCliente().isEmpty()){
            System.out.println("Nao ha clientes registrados.");
            return "";
        } else {
            System.out.println(FontEffects.WHITE_UNDERLINED+"Clientes salvos:"+FontEffects.RESET);
            int count = 0;
            for (int i=0;i<data.getCliente().size();i++) {
                System.out.println(FontEffects.WHITE_BOLD_BRIGHT+String.valueOf(count)+"-\t"+"Nome:\t"+FontEffects.RESET + data.getCliente().get(i).get_name());
                System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"\tID:\t"+FontEffects.RESET + data.getCliente().get(i).get_id());
                System.out.println(FontEffects.WHITE_UNDERLINED + "                            " + FontEffects.RESET);
                count++;
            }
            System.out.println(FontEffects.WHITE_UNDERLINED+"Digite o index do cliente:"+FontEffects.RESET);
            Integer index = Integer.valueOf(scanner.nextLine());
            for (int i=0;i<data.getCliente().size();i++){
                Cliente obj = data.getCliente().get(i);
                if (i == index){
                    return (String) obj.get_id();
                }
            }
            return "";
        }
    }

    public static List<Veiculo> getVeiculosCliente(String id) {
        Data data = new Data();
        if (data.getVeiculo().isEmpty()){
            System.out.println("Nao ha clientes registrados.");
            return new ArrayList<Veiculo>();
        } else {
            List<Veiculo> allVeiculos = new ArrayList<Veiculo>();
            for (int i=0;i<data.getVeiculo().size();i++){
                Veiculo obj = data.getVeiculo().get(i);
                if (Objects.equals((String) obj.get_idCliente(), id)){
                    allVeiculos.add(obj);
                }
            }
            return allVeiculos;
        }
    }
}
