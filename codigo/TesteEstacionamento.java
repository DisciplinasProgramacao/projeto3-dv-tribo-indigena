public class TesteEstacionamento {

    public static void main(String[] args) {
        TesteEstacionamento teste = new TesteEstacionamento();
        teste.testeEstacionarSair();
        teste.testeEstacionarSemSair();
        teste.testeSairSemEstacionar();
    }

    public void testeEstacionarSair() {
        Veiculo veiculo = new Veiculo("ABC-1234");
        veiculo.estacionar("8A");
        double custo = veiculo.sair();
        if (custo == 0) {
            System.out.println("Teste Estacionar e Sair: Aprovado");
        } else {
            System.out.println("Teste Estacionar e Sair: Reprovado");
        }
    }

    public void testeEstacionarSemSair() {
        Veiculo veiculo = new Veiculo("ABC-5678");
        veiculo.estacionar("8B");
        if (veiculo.sair() == 0) {
            System.out.println("Teste Estacionar Sem Sair: Reprovado");
        } else {
            System.out.println("Teste Estacionar Sem Sair: Aprovado");
        }
    }

    public void testeSairSemEstacionar() {
        Veiculo veiculo = new Veiculo("ABC-9012");
        if (veiculo.sair() == 0) {
            System.out.println("Teste Sair Sem Estacionar: Aprovado");
        } else {
            System.out.println("Teste Sair Sem Estacionar: Reprovado");
        }
    }
}
