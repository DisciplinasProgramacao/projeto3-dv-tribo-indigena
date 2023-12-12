import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Veiculo implements Serializable, Subject {
    private String placa;
    private UsoDeVaga usoAtual;
    private Servicos servicos;
    private Boolean _isEstacionado;
    private String _idCliente;
    private RegistroDeUsos registroDeUsos;

    private List<Observer> observers = new ArrayList<>();

    public Veiculo(String placa) {
        if (this.exists(placa)){
            this.placa = placa;
        } else {
            this.placa = placa;
            this.servicos = new Servicos();
            this.linkVeiculo();
        }
    }
    public void relatorioUsosPorData() {
        List<UsoDeVaga> usos = registroDeUsos.getUsosOrdenadosPorData();
        System.out.println("Relatório de Usos Ordenado por Data:");
        for (UsoDeVaga uso : usos) {
            System.out.println("Vaga: " + uso.getVaga() + " | Início: " + uso.getHorarioInicio() + " | Custo: " + uso.getCusto());
        }
    }

    public void relatorioUsosPorValor() {
        List<UsoDeVaga> usos = registroDeUsos.getUsosOrdenadosPorValor();
        System.out.println("Relatório de Usos Ordenado por Valor:");
        for (UsoDeVaga uso : usos) {
            System.out.println("Vaga: " + uso.getVaga() + " | Início: " + uso.getHorarioInicio() + " | Custo: " + uso.getCusto());
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

        notifyObservers();

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
    private double valorEstacionamento(){
        Cliente cliente= this.getCliente();
        Double custo = 0.0;
        switch (cliente.get_tipoCliente()){
            case HORISTA -> {
                custo = usoAtual.getCusto();
            }
            case MENSALISTA -> {
                custo = 0.0;
            }
            case MANHA -> {
                LocalTime manhaInicio = LocalTime.of(8, 0);
                LocalTime manhaFim = LocalTime.of(12, 0);
                LocalTime inicio = usoAtual.getHorarioInicio();
                if ((inicio.isAfter(manhaInicio) || inicio.equals(manhaInicio)) &&
                        (inicio.isBefore(manhaFim) || inicio.equals(manhaFim))) {
                    custo = 0.0;
                } else {
                    custo = usoAtual.getCusto();
                }
            }
            case TARDE -> {

                LocalTime tardeInicio = LocalTime.of(12, 1);
                LocalTime tardeFim = LocalTime.of(18, 0);
                LocalTime inicio = usoAtual.getHorarioInicio();
                if ((inicio.isAfter(tardeInicio) || inicio.equals(tardeInicio)) &&
                        (inicio.isBefore(tardeFim) || inicio.equals(tardeFim))) {
                    custo = 0.0;
                } else {
                    custo = usoAtual.getCusto();
                }
            }
            case NOITE -> {
                LocalTime noiteInicio = LocalTime.of(18, 1);
                LocalTime noiteFim = LocalTime.of(23, 59);
                LocalTime inicio = usoAtual.getHorarioInicio();
                if ((inicio.isAfter(noiteInicio) || inicio.equals(noiteInicio)) &&
                        (inicio.isBefore(noiteFim) || inicio.equals(noiteFim))) {
                    custo = 0.0;
                } else {
                    custo = usoAtual.getCusto();
                }
            }
        }
        return custo;
    }
    private Cliente getCliente(){
        Data data = new Data();
        Cliente cliente = new Cliente(null);
        if (data.getCliente() == null){
            System.out.println("Nao ha clientes registrados.");
        } else {
            for (int i=0; i<data.getCliente().size();i++) {
              if (data.getCliente().get(i).get_id().equals(this._idCliente)){
                  cliente = data.getCliente().get(i);
                  break;
              }
            }
        }
        return cliente;
    }

    public String getNomeCliente(){
        Data data = new Data();
        String nome = "";
        if (data.getCliente() == null){
            System.out.println("Nao ha clientes registrados.");
        } else {
            for (int i=0; i<data.getCliente().size();i++) {
                if (data.getCliente().get(i).get_id().equals(this._idCliente)){
                    nome = data.getCliente().get(i).get_name();
                    break;
                }
            }
        }
        return nome;
    }

    public String get_idCliente() {
        return _idCliente;
    }

    public void set_idCliente(String _idCliente) {
        this._idCliente = _idCliente;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

}