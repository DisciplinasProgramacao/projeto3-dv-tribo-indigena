import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private String nome;
    private List<Cliente> clientes;
    private Vaga[][] vagas;
    private int quantFileiras;
    private int vagasPorFileira;

    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new ArrayList<>();
        this.vagas = new Vaga[fileiras][vagasPorFila];
        gerarVagas();
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificador().equals(idCli)) {
                cliente.addVeiculo(veiculo);
                break;
            }
        }
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    private void gerarVagas() {
        char fila = 'A';
        for (int i = 0; i < quantFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                String identificador = fila + String.format("%02d", j + 1);
                vagas[i][j] = new Vaga(identificador);
            }
            fila++;
        }
    }

    public void estacionar(String placa) {
        for (int i = 0; i < quantFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                if (!vagas[i][j].isOcupada()) {
                    for (Cliente cliente : clientes) {
                        Veiculo veiculo = cliente.getVeiculoByPlaca(placa);
                        if (veiculo != null) {
                            vagas[i][j].ocupar();
                            veiculo.setVaga(vagas[i][j]);
                            veiculo.setHoraEntrada(System.currentTimeMillis()); // Registro da hora de entrada
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    public double sair(String placa) {
        double valorCobrado = 0;
        for (int i = 0; i < quantFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                if (vagas[i][j].isOcupada() && vagas[i][j].getVeiculo().getPlaca().equals(placa)) {
                    vagas[i][j].liberar();
                    valorCobrado = calcularCobranca(vagas[i][j].getHoraEntrada());
                    break;
                }
            }
        }
        return valorCobrado;
    }

    private double calcularCobranca(long horaEntrada) {
        long horaSaida = System.currentTimeMillis();
        long tempoOcupada = (horaSaida - horaEntrada) / (1000 * 60 * 15); // 15 minutos
        double valor = tempoOcupada * 4.0;
        return Math.min(valor, 50.0); // Valor máximo de R$50
    }

    public double totalArrecadado() {
        double total = 0;
        for (int i = 0; i < quantFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                if (vagas[i][j].isOcupada()) {
                    total += calcularCobranca(vagas[i][j].getHoraEntrada());
                }
            }
        }
        return total;
    }

    public double arrecadacaoNoMes(int mes) {
        double total = 0;
        for (int i = 0; i < quantFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                if (vagas[i][j].isOcupada() && vagas[i][j].getVeiculo().getMesEstacionado() == mes) {
                    total += calcularCobranca(vagas[i][j].getHoraEntrada());
                }
            }
        }
        return total;
    }

    public double valorMedioPorUso() {
        if (clientes.isEmpty()) {
            return 0;
        }
        double totalArrecadado = totalArrecadado();
        int totalClientes = clientes.size();
        return totalArrecadado / totalClientes;
    }

    public String top5Clientes(int mes) {
        clientes.sort((c1, c2) -> Double.compare(c2.arrecadacaoNoMes(mes), c1.arrecadacaoNoMes(mes));
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Math.min(5, clientes.size()); i++) {
            result.append(i + 1).append(". ").append(clientes.get(i).getNome()).append(": R$").append(clientes.get(i).arrecadacaoNoMes(mes)).append("\n");
        }
        return result.toString();
    }
}

class Cliente {
    private String nome;
    private String identificador;
    private List<Veiculo> veiculos = new ArrayList<>();
    // Construtores, getters e setters
}

class Veiculo {
    private String placa;
    private Cliente cliente;
    private Vaga vaga;
    private long horaEntrada;

    // Construtores, getters e setters
}

class Vaga {
    private String identificador;
    private boolean ocupada;
    private long horaEntrada;

    // Construtores, getters e setters
    public void liberar() {
        this.ocupada = false;
    }
}
