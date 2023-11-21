import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SerializedData implements Serializable {
    private Cliente[] cliente;
    private Veiculo[] veiculo;

    public Cliente[] getCliente() {
        return cliente;
    }

    public void setCliente(Cliente[] cliente) {
        this.cliente = cliente;
    }

    public Veiculo[] getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo[] veiculo) {
        this.veiculo = veiculo;
    }
}

public class Data {
    private List<Cliente> cliente;
    private List<Veiculo> veiculo;

    public Data(){
        this.deserialize();
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }

    public void rmCliente(int index) {
        this.cliente.remove(index);
    }

    public void addCliente(Cliente cliente){
        if (this.cliente == null){
            this.cliente = new ArrayList<Cliente>();
            this.cliente.add(cliente);
        } else {
            this.cliente.add(cliente);
        }
        try {
            this.saveFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Veiculo> getVeiculo() {
        return veiculo;
    }

    public void rmVeiculo(int index) {
        this.veiculo.remove(index);
        try {
            this.saveFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addVeiculo(Veiculo veiculo) {
        this.veiculo.add(veiculo);
        try {
            this.saveFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setVeiculo(List<Veiculo> veiculo) {
        this.veiculo = veiculo;
        try {
            this.saveFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveFile() throws FileNotFoundException {
        App.saveFile(this.serialize());
    }

    private SerializedData serialize(){
        Cliente[] clientes;
        Veiculo[] veiculos;

        SerializedData serializedData = new SerializedData();

        if (this.cliente == null){
            clientes = new Cliente[0];
        } else {
            clientes = new Cliente[this.cliente.size()];
            for (int i=0;i<this.cliente.size();i++){
                clientes[i] = this.cliente.get(i);
            }
        }

        if (this.veiculo == null){
            veiculos = new Veiculo[0];
        } else {
            veiculos = new Veiculo[this.veiculo.size()];
            for (int i=0;i<this.veiculo.size();i++){
                veiculos[i] = this.veiculo.get(i);
            }
        }

        serializedData.setCliente(clientes);
        serializedData.setVeiculo(veiculos);
        return serializedData;
    }

    private void deserialize() {
        SerializedData serializedData = App.getData();
        Cliente[] clientes = serializedData.getCliente();
        Veiculo[] veiculos = serializedData.getVeiculo();

        if (this.cliente == null){
            this.setCliente(new ArrayList<Cliente>());
        }
        if (this.veiculo == null){
            this.setVeiculo(new ArrayList<Veiculo>());
        }
        this.cliente.addAll(Arrays.asList(clientes));
        this.veiculo.addAll(Arrays.asList(veiculos));
    }
}
