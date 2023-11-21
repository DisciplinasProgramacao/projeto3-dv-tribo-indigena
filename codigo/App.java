import java.io.*;
import java.util.Scanner;


public class App {
    private Cliente[] cliente;
    private Veiculo[] veiculo;

    public App() {
        getData();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        menu.setupMenu(scanner);
    }

    public static void main(String[] args){
        new App();
    }

    public static SerializedData getData(){
        SerializedData serializedData = new SerializedData();
        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream(Vars.FILE_DATA.toString());
            try(ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                serializedData = (SerializedData) objectIn.readObject();
                return serializedData;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Creating new file");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(Vars.FILE_DATA.toString()));
                serializedData.setCliente(new Cliente[0]);
                serializedData.setVeiculo(new Veiculo[0]);
                return serializedData;
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            System.out.println("Error when reading file");
            serializedData.setCliente(new Cliente[0]);
            serializedData.setVeiculo(new Veiculo[0]);
            return serializedData;
        }
    }

    public static void saveFile(SerializedData serializedData) {
        try (FileOutputStream fileOut = new FileOutputStream(Vars.FILE_DATA.toString())) {
            try(ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(serializedData);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
