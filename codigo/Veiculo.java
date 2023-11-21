import java.time.LocalTime;
import java.util.Scanner;

public class Veiculo {
    private String placa;
    private UsoDeVaga usoAtual;
    private Servicos servicos;
    private Boolean _isEstacionado;
    private String _idCliente;

    public Veiculo(String placa) {
        if (this.exists(placa)){
            this.placa = placa;
        } else {
            this.placa = placa;
            this.servicos = new Servicos();
            this.linkVeiculo();
        }
    }

    public String getPlaca() {
        return placa;
    }

    public void estacionar(String vaga) {
        if (usoAtual != null) {
            System.out.println("O veículo já está estacionado!");
            return;
        }
        usoAtual = new UsoDeVaga(vaga, LocalTime.now());
    }

    public double sair() {
        if (usoAtual == null) {
            System.out.println("O veículo não está estacionado!");
            return 0;
        }
        usoAtual.finalizarUso();
        double custo = usoAtual.calcularCusto(usoAtual.getDuracao()) + servicos.getTotalServiceCost();
        usoAtual = null;
        return custo;
    }

    public void selecionarServico(String nomeServico) {
        servicos.selecionar(nomeServico);
    }


    public void set_isEstacionado(Boolean _isEstacionado) {
        this._isEstacionado = _isEstacionado;
    }

    public Boolean get_isEstacionado(){
        return this._isEstacionado;
    }

    private Boolean exists(String placa){
        Data data = new Data();
        Boolean found = false;
        for (int i=0;i<data.getVeiculo().size();i++){
            if (String.valueOf(data.getVeiculo().get(i).getPlaca()).equals(placa)){
                found = true;
                this._isEstacionado = (Boolean) data.getVeiculo().get(i).get_isEstacionado();
                System.out.println(this._isEstacionado);
                break;
            }
        }
        return found;
    }

    private void linkVeiculo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione o cliente");
        String cliente = Utils.getClientes(scanner);
        if (cliente.isEmpty()){
            System.out.println("Erro ao selecionar cliente");
            this._idCliente = "";
        } else {
            this._idCliente = cliente;
        }
    }

    public String get_idCliente() {
        return _idCliente;
    }

    public void set_idCliente(String _idCliente) {
        this._idCliente = _idCliente;
    }
}