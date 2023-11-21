
import java.io.Serializable;
        import java.time.Duration;
        import java.time.LocalTime;

class UsoDeVaga implements Serializable {

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

    public double calcularCusto(Duration duracao) {
        long totalMinutes = duracao.toMinutes();
        long blocksOf15Minutes = (totalMinutes + 14) / 15;
        double cost = blocksOf15Minutes * 4;
        return Math.min(cost, 50);
    }
    public double getCusto() {
        Duration duracao = getDuracao();
        return calcularCusto(duracao);
    }


    // Getters
    public String getVaga() {
        return vaga;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public LocalTime getHorarioTermino() {
        return horarioTermino;
    }
}
