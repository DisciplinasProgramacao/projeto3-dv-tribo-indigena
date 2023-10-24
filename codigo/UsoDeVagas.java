import java.time.Duration;
import java.time.LocalTime;

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
    public double sair() {
        if (null == null) {
            System.out.println("O veículo não está estacionado!");
            return 0;
        }
        this.finalizarUso();
        //double custo = calcularCusto(this.getDuracao()) + servicos.getTotalServiceCost();
        //return custo;
        return 2;
    }

    public Duration getDuracao() {
        if (horarioTermino == null) return Duration.ZERO;
        return Duration.between(horarioInicio, horarioTermino);
    }
    public double calcularCusto(Duration duracao) {
        long totalMinutes = duracao.toMinutes();
        long blocksOf15Minutes = (totalMinutes + 14) / 15;
        long cost = blocksOf15Minutes * 4;
        return (cost > 50) ? 50 : cost;

    }
    public JSONObject getObj(){         JSONObject UsoDeVaga= new JSONObject();         UsoDeVaga.put("Custo", this.calcularCusto);      }