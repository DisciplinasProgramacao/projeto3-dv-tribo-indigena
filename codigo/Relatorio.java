import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Relatorio implements Observer {
    private Map<String, Double> arrecadacaoPorCliente = new HashMap<>();
    private PriorityQueue<Map.Entry<String, Double>> topClientes;
    private double arrecadacaoMensal = 0.0;

    public Relatorio() {
        topClientes = new PriorityQueue<>(5, Comparator.comparingDouble(Map.Entry::getValue));
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Veiculo) {
            Veiculo veiculo = (Veiculo) subject;
            String clienteId = veiculo.get_idCliente();
            double custo = veiculo.sair();

            arrecadacaoPorCliente.merge(clienteId, custo, Double::sum);

            arrecadacaoMensal += custo;

            atualizarTopClientes(clienteId, arrecadacaoPorCliente.get(clienteId));
        }
    }

    private void atualizarTopClientes(String clienteId, double arrecadacao) {
        topClientes.removeIf(entry -> entry.getKey().equals(clienteId));

        topClientes.offer(new HashMap.SimpleEntry<>(clienteId, arrecadacao));

        while (topClientes.size() > 5) {
            topClientes.poll();
        }
    }

    public void exibirTopClientes() {
        System.out.println("Top 5 Clientes com Maior Arrecadação:");
        topClientes.forEach(entry ->
                System.out.println("Cliente ID: " + entry.getKey() + ", Arrecadação: " + entry.getValue())
        );
    }

    public void exibirArrecadacaoMensal() {
        System.out.println("Arrecadação Mensal Total: " + arrecadacaoMensal);
    }
}
