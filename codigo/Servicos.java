import java.io.Serializable;

class Servicos implements Serializable {
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
