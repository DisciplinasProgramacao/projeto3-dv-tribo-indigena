import java.util.Scanner;
import java.time.LocalTime;
import java.time.Duration;

public class Veiculo {
    private String placa;
    private UsoDeVaga usoAtual;

    public Veiculo(String placa) {
        this.placa = placa;
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
        double custo = calcularCusto(usoAtual.getDuracao());
        usoAtual = null; // Reseta o uso atual após saída
        return custo;
    }

    private double calcularCusto(Duration duracao) {
        long totalMinutes = duracao.toMinutes();
        long blocksOf15Minutes = (totalMinutes + 14) / 15;
        long cost = blocksOf15Minutes * 4;
        return (cost > 50) ? 50 : cost;
    }
}

class UsoDeVaga {
    private String vaga;
    private LocalTime horarioInicio;
    private LocalTime horarioTermino;

    public UsoDeVaga(String vaga, LocalTime horarioInicio) {
        this.vaga = vaga;
        this.horarioInicio = horarioInicio;
    }

    public void finalizarUso() {
        this.horarioTermino = LocalTime.now();
    }

    public Duration getDuracao() {
        if (horarioTermino == null) return Duration.ZERO;
        return Duration.between(horarioInicio, horarioTermino);
    }
}

