import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private String nome;
    private List<Cliente> clientes;
    private Vaga[][] vagas;
    private int quantFileiras;
    private int vagasPorFileira;

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
