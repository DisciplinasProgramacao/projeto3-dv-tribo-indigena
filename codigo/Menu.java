import java.util.List;
import java.util.Scanner;

public class Menu {
    private Relatorio relatorio;

    public Menu() {
        this.relatorio = new Relatorio();
    }

    public void setupMenu(Scanner scanner){
        clearConsole();
        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"Gerencia de Estacionamentos"+FontEffects.RESET);
        System.out.println(FontEffects.WHITE_UNDERLINED+"Escolha uma opcao:"+FontEffects.RESET);
        System.out.println(
                FontEffects.WHITE_BOLD_BRIGHT+"\t1 -\t"+FontEffects.RESET + "Clientes\n"+
                        FontEffects.WHITE_BOLD_BRIGHT+"\t2 -\t"+FontEffects.RESET + "Veiculos\n"+
                        FontEffects.WHITE_BOLD_BRIGHT+"\t3 -\t"+FontEffects.RESET + "Vagas\n"+
                        FontEffects.WHITE_BOLD_BRIGHT+"\t4 -\t"+FontEffects.RESET + "Estacionamentos\n"+
                        FontEffects.WHITE_BOLD_BRIGHT+"\t5 -\t"+FontEffects.RESET + "Relatórios\n"+
                        FontEffects.WHITE_UNDERLINED+"Pressione a tecla referente, ou \"q\" para sair."+FontEffects.RESET
        );
        String line = scanner.nextLine();
        switch (line.trim()){
            case "1":
                menuClientes(scanner);
                break;
            case "2":
                menuVeiculos(scanner);
                break;
            case "3":

                break;
            case "4":

                break;
            case "5":
                menuRelatorios(scanner);
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
                FontEffects.WHITE_BOLD_BRIGHT+"\t4 -\t"+FontEffects.RESET + "Visualizar veiculos associados a um cliente\n" +
                FontEffects.WHITE_UNDERLINED+"Pressione a tecla referente, ou \"q\" para voltar."+FontEffects.RESET);
        String line = scanner.nextLine();
        System.out.println("\n\n\n\n");
        switch (line.trim()){
            case "1": {
                Cliente cliente = new Cliente("");
                System.out.println("Insira o nome do cliente.");
                String lineCliente = scanner.nextLine();
                if (lineCliente.isEmpty()) {
                    menuClientes(scanner);
                } else {
                    cliente.set_name(lineCliente);
                }
                System.out.println("Selecione o tipo de reserva:");
                System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"\t1 -\t"+FontEffects.RESET + "Turno da manha\n" +
                        FontEffects.WHITE_BOLD_BRIGHT+"\t2 -\t"+FontEffects.RESET + "Turno da tarde\n" +
                        FontEffects.WHITE_BOLD_BRIGHT+"\t3 -\t"+FontEffects.RESET + "Turno da noite\n" +
                        FontEffects.WHITE_BOLD_BRIGHT+"\t4 -\t"+FontEffects.RESET + "Horista\n" +
                        FontEffects.WHITE_BOLD_BRIGHT+"\t5 -\t"+FontEffects.RESET + "Mensal\n" +
                        FontEffects.WHITE_UNDERLINED+"Pressione a tecla referente."+FontEffects.RESET);
                String lineClienteTipo = scanner.nextLine();
                if (lineClienteTipo.isEmpty()){
                    menuClientes(scanner);
                } else {
                    switch (Integer.valueOf(lineClienteTipo.trim())){
                        case 1: {
                            cliente.set_tipoCliente(TiposCliente.MANHA);
                            break;
                        }
                        case 2: {
                            cliente.set_tipoCliente(TiposCliente.TARDE);
                            break;
                        }
                        case 3: {
                            cliente.set_tipoCliente(TiposCliente.NOITE);
                            break;
                        }
                        case 4: {
                            cliente.set_tipoCliente(TiposCliente.HORISTA);
                            break;
                        }
                        case 5: {
                            cliente.set_tipoCliente(TiposCliente.MENSALISTA);
                            break;
                        }
                        default: {
                            menuClientes(scanner);
                            break;
                        }
                    }
                }
                Data data = new Data();
                data.addCliente(cliente);
                menuClientes(scanner);
                break;
            }
            case "2": {
                Data data = new Data();
                if (data.getCliente() == null){
                    System.out.println("Nao ha clientes registrados.");
                } else {
                    System.out.println(FontEffects.WHITE_UNDERLINED+"Clientes salvos:"+FontEffects.RESET);
                    for (int i=0;i<data.getCliente().size();i++) {
                        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"Nome:\t"+FontEffects.RESET + data.getCliente().get(i).get_name());
                        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"Tipo:\t"+FontEffects.RESET + data.getCliente().get(i).getParsedTipo());
                        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"ID:\t"+FontEffects.RESET + data.getCliente().get(i).get_id());
                        System.out.println(FontEffects.WHITE_UNDERLINED + "                            " + FontEffects.RESET);
                    }
                }
                menuClientes(scanner);
                break;
            }
            case "3": {
                Data data = new Data();
                if (data.getCliente() == null){
                    System.out.println("Nao ha clientes registrados.");
                } else {
                    System.out.println(FontEffects.WHITE_UNDERLINED+"Clientes salvos:"+FontEffects.RESET);
                    for (int i=0; i<data.getCliente().size();i++) {
                        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+String.valueOf(i)+"-\t"+"Nome:\t"+FontEffects.RESET + data.getCliente().get(i).get_name());
                        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"\tID:\t"+FontEffects.RESET + data.getCliente().get(i).get_id());
                        System.out.println(FontEffects.WHITE_UNDERLINED + "                            " + FontEffects.RESET);
                    }
                    System.out.println(FontEffects.WHITE_UNDERLINED+"Digite o index do cliente que deseja apagar:"+FontEffects.RESET);
                    Integer index = Integer.valueOf(scanner.nextLine());
                    data.rmCliente(index);
                }
                menuClientes(scanner);
                break;
            }
            case "4": {
                List<Veiculo> veiculos = Utils.getVeiculosCliente(Utils.getClientes(scanner));

                for (Veiculo obj: veiculos){
                    Veiculo item = obj;
                    System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"Placa:\t"+FontEffects.RESET + item.getPlaca());
                    String isEstacionado = "nao";
                    if ((Boolean) item.get_isEstacionado()){
                        isEstacionado = "sim";
                    }
                    System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"Esta estacionado:\t"+FontEffects.RESET + isEstacionado);
                    System.out.println(FontEffects.WHITE_UNDERLINED + "                            " + FontEffects.RESET);
                }
                break;
            }
            case "q": {
                setupMenu(scanner);
                break;
            }

        }
    }

    private void menuVeiculos(Scanner scanner) {
        clearConsole();
        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"Gerencia de Estacionamentos"+FontEffects.RESET);
        System.out.println(FontEffects.WHITE_UNDERLINED+"Escolha uma opcao:"+FontEffects.RESET);
        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"\t1 -\t"+FontEffects.RESET + "Cadastrar veiculo\n" +
                FontEffects.WHITE_BOLD_BRIGHT+"\t2 -\t"+FontEffects.RESET + "Visualizar veiculos salvos\n" +
                FontEffects.WHITE_BOLD_BRIGHT+"\t3 -\t"+FontEffects.RESET + "Apagar veiculo salvo\n" +
                FontEffects.WHITE_UNDERLINED+"Pressione a tecla referente, ou \"q\" para voltar."+FontEffects.RESET);
        String line = scanner.nextLine();
        System.out.println("\n\n\n\n");

        switch (line.trim()){
            case "1":{
                System.out.println("Insira a placa do veiculo.");
                String lineVeiculoPlaca = scanner.nextLine();
                System.out.println("O veiculo esta estacionado?\n1-\tSim\n2-\tNao");
                String lineVeiculoEstacionado = scanner.nextLine();
                Boolean isEstacionado = false;
                if (lineVeiculoEstacionado.trim().equals("1")){
                    isEstacionado = true;
                }
                if (lineVeiculoPlaca.isEmpty()) {
                    menuVeiculos(scanner);
                } else {
                    Veiculo veiculo = new Veiculo(lineVeiculoPlaca);
                    veiculo.set_isEstacionado(isEstacionado);
                    Data data = new Data();
                    data.addVeiculo(veiculo);
                    menuVeiculos(scanner);
                }
                break;
            }
            case "2": {
                Data data = new Data();
                if (data.getVeiculo() == null){
                    System.out.println("Nao ha veiculos registrados.");
                } else {
                    System.out.println(FontEffects.WHITE_UNDERLINED+"Veiculos salvos:"+FontEffects.RESET);
                    for (int i=0;i<data.getVeiculo().size();i++) {
                        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"Placa:\t"+FontEffects.RESET + data.getVeiculo().get(i).getPlaca());
                        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"Nome do cliente:\t"+FontEffects.RESET + data.getVeiculo().get(i).getNomeCliente());
                        String isEstacionado = "nao";
                        if ((Boolean) data.getVeiculo().get(i).get_isEstacionado()){
                            isEstacionado = "sim";
                        }
                        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"Esta estacionado:\t"+FontEffects.RESET + isEstacionado);
                        System.out.println(FontEffects.WHITE_UNDERLINED + "                            " + FontEffects.RESET);
                    }
                }
                menuVeiculos(scanner);
                break;
            }
            case "3": {
                Data data = new Data();
                if (data.getVeiculo() == null){
                    System.out.println("Nao ha veiculos cadastrados.");
                } else {
                    System.out.println(FontEffects.WHITE_UNDERLINED+"Veiculos salvos:"+FontEffects.RESET);
                    int count = 0;
                    for (int i=0;i<data.getVeiculo().size();i++) {
                        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+String.valueOf(count)+"-\t"+"Placa:\t"+FontEffects.RESET + data.getVeiculo().get(i).getPlaca());
                        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"\tEsta estacionado:\t"+FontEffects.RESET + data.getVeiculo().get(i).get_isEstacionado());
                        System.out.println(FontEffects.WHITE_UNDERLINED + "                            " + FontEffects.RESET);
                        count++;
                    }
                    System.out.println(FontEffects.WHITE_UNDERLINED+"Digite o index do veiculo que deseja apagar:"+FontEffects.RESET);
                    Integer index = Integer.valueOf(scanner.nextLine());
                    data.rmVeiculo(index);
                }
                menuVeiculos(scanner);
                break;
            }
            case "q": {
                setupMenu(scanner);
                break;
            }
        }
    }

    private void menuRelatorios(Scanner scanner) {
        clearConsole();
        System.out.println(FontEffects.WHITE_BOLD_BRIGHT+"Relatórios de Estacionamento"+FontEffects.RESET);
        System.out.println(FontEffects.WHITE_UNDERLINED+"Escolha uma opção:"+FontEffects.RESET);
        System.out.println(
                FontEffects.WHITE_BOLD_BRIGHT+"\t1 -\t"+FontEffects.RESET + "Top 5 Clientes com Maior Arrecadação\n"+
                        FontEffects.WHITE_BOLD_BRIGHT+"\t2 -\t"+FontEffects.RESET + "Arrecadação Mensal Total\n"+
                        FontEffects.WHITE_UNDERLINED+"Pressione a tecla referente, ou \"q\" para voltar."+FontEffects.RESET
        );

        String line = scanner.nextLine();
        switch (line.trim()){
            case "1":
                relatorio.exibirTopClientes();
                break;
            case "2":
                relatorio.exibirArrecadacaoMensal();
                break;
            case "q":
                setupMenu(scanner);
                break;
            default:
                System.out.println(FontEffects.YELLOW_BRIGHT + "A opção não é válida"+FontEffects.RESET);
                menuRelatorios(scanner);
                break;
        }
        System.out.println("\nPressione qualquer tecla para continuar...");
        scanner.nextLine();
        menuRelatorios(scanner);
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
