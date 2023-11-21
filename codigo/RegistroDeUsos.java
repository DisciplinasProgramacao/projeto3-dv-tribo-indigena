import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RegistroDeUsos {
    private List<UsoDeVaga> usos;

    public RegistroDeUsos() {
        this.usos = new ArrayList<>();
    }

    public void adicionarUso(UsoDeVaga uso) {
        usos.add(uso);
    }

    public List<UsoDeVaga> getUsosOrdenadosPorData() {
        usos.sort(Comparator.comparing(UsoDeVaga::getHorarioInicio));
        return new ArrayList<>(usos);
    }

    public List<UsoDeVaga> getUsosOrdenadosPorValor() {
        usos.sort(Comparator.comparing(UsoDeVaga::getCusto).reversed());
        return new ArrayList<>(usos);
    }
}
