import java.time.Duration;
import java.time.LocalTime;

public class Veiculo {
    private String placa;
    private UsoDeVaga usoAtual;
    private Servicos servicos;

    public Veiculo(String placa) {
        this.placa = placa;
        this.servicos = new Servicos();
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
        double custo = calcularCusto(usoAtual.getDuracao()) + servicos.getTotalServiceCost();
        usoAtual = null;
        return custo;
    }

    public void selecionarServico(String nomeServico) {
        servicos.selecionar(nomeServico);
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

class Servicos {
    private final double MANOBRISTA_PRICE = 5.0;
    private final double LAVAGEM_PRICE = 20.0;
    private final double POLIMENTO_PRICE = 45.0;

    private double totalServiceCost = 0.0;

    public void selecionar(String nomeServico) {
        switch (nomeServico.toLowerCase()) {
            case "manobrista":
                manobrista();
                totalServiceCost += MANOBRISTA_PRICE;
                break;
            case "lavagem":
                lavagem();
                totalServiceCost += LAVAGEM_PRICE;
                break;
            case "polimento":
                polimento();
                totalServiceCost += POLIMENTO_PRICE;
                break;
            default:
                System.out.println("Serviço não reconhecido.");
                break;
        }
    }

    public double getTotalServiceCost() {
        return totalServiceCost;
    }

    private void manobrista() {
        System.out.println("Manobrista selecionado. Preço: R$ " + MANOBRISTA_PRICE);
    }

    private void lavagem() {
        System.out.println("Lavagem selecionado. Preço: R$ " + LAVAGEM_PRICE);
    }

    private void polimento() {
        System.out.println("Polimento (lavagem inclusa) selecionado. Preço: R$ " + POLIMENTO_PRICE);
    }
}
