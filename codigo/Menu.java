import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Scanner;

public class Menu {
    public void setupMenu(Scanner scanner){
        clearConsole();
        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"Gerencia de Estacionamentos"+FontEffects.RESET);
        System.out.println(FontEffects.WHITE_UNDERLINED+"Escolha uma opcao:"+FontEffects.RESET);
        System.out.println(
                FontEffects.WHITE_BOLD_BRIGHT+"\t1 -\t"+FontEffects.RESET + "Clientes\n"+
                        FontEffects.WHITE_BOLD_BRIGHT+"\t2 -\t"+FontEffects.RESET + "Veiculos\n"+
                        FontEffects.WHITE_BOLD_BRIGHT+"\t3 -\t"+FontEffects.RESET + "Vagas\n"+
                        FontEffects.WHITE_BOLD_BRIGHT+"\t4 -\t"+FontEffects.RESET + "Estacionamentos\n"+
                        FontEffects.WHITE_UNDERLINED+"Pressione a tecla referente, ou \"q\" para sair."+FontEffects.RESET
        );
        String line = scanner.nextLine();
        switch (line.trim()){
            case "1":
                menuClientes(scanner);
                break;
            case "2":

                break;
            case "3":

                break;
            case "4":

                break;
            case "q":
                System.exit(0);
                break;
            default:
                System.out.println(FontEffects.YELLOW_BRIGHT + "A opcao nao e valida"+FontEffects.RESET);
                setupMenu(scanner);
                break;
        }
    }

    private void menuClientes(Scanner scanner){
        clearConsole();
        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"Gerencia de Estacionamentos"+FontEffects.RESET);
        System.out.println(FontEffects.WHITE_UNDERLINED+"Escolha uma opcao:"+FontEffects.RESET);
        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"\t1 -\t"+FontEffects.RESET + "Cadastrar cliente\n" +
                FontEffects.WHITE_BOLD_BRIGHT+"\t2 -\t"+FontEffects.RESET + "Visualizar clientes salvos\n" +
                FontEffects.WHITE_BOLD_BRIGHT+"\t3 -\t"+FontEffects.RESET + "Apagar cliente salvo\n" +
                FontEffects.WHITE_UNDERLINED+"Pressione a tecla referente, ou \"q\" para voltar."+FontEffects.RESET);
        String line = scanner.nextLine();
        System.out.println("\n\n\n\n");
        switch (line.trim()){
            case "1": {
                Cliente cliente = new Cliente();
                System.out.println("Insira o nome do cliente.");
                String lineCliente = scanner.nextLine();
                if (lineCliente.isEmpty()) {
                    menuClientes(scanner);
                } else {
                    cliente.set_name(lineCliente);
                }
                JSONObject data = App.getData();
                JSONArray jsonArray = (JSONArray) data.get("clientes");
                jsonArray.add(cliente.getObj());
                App.saveFile(data.toJSONString());
                menuClientes(scanner);
                break;
            }
            case "2": {
                JSONObject data = App.getData();
                JSONArray jsonArray = (JSONArray) data.get("clientes");
                if (jsonArray.isEmpty()){
                    System.out.println("Nao ha clientes registrados.");
                } else {
                    System.out.println(FontEffects.WHITE_UNDERLINED+"Clientes salvos:"+FontEffects.RESET);
                    for (Object obj: jsonArray) {
                        JSONObject item = (JSONObject) obj;
                        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"Nome:\t"+FontEffects.RESET + item.get("name"));
                        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"ID:\t"+FontEffects.RESET + item.get("id"));
                        System.out.println(FontEffects.WHITE_UNDERLINED + "                            " + FontEffects.RESET);
                    }
                }
                menuClientes(scanner);
                break;
            }
            case "3": {
                JSONObject data = App.getData();
                JSONArray jsonArray = (JSONArray) data.get("clientes");
                if (jsonArray.isEmpty()){
                    System.out.println("Nao ha clientes registrados.");
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
                    System.out.println(FontEffects.WHITE_UNDERLINED+"Digite o index do cliente que deseja apagar:"+FontEffects.RESET);
                    Integer index = Integer.valueOf(scanner.nextLine());
                    for (int i=0;i<jsonArray.size();i++){
                        JSONObject obj = (JSONObject) jsonArray.get(i);
                        if (i == index){
                            jsonArray.remove(i);
                            App.saveFile(data.toJSONString());
                            break;
                        }
                    }
                }
                menuClientes(scanner);
            }
        }
    }

    public void clearConsole() {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
