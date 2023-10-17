import java.util.Scanner;
import java.time.LocalTime;
import java.time.Duration;



public class Veiculo {

    private String placa;
    private UsoDeVaga[] usos;
    public Scanner teclado = new Scanner(System.in);
    public LocalTime time1;
    public  LocalTime time2;
    public  Duration duration;
    public long totalMinutes;
    public long blocksOf15Minutes;
    public long cost;

   public String getPlaca;
    public Veiculo(String placa) {
        System.out.print("Escreva a Placa do carro:");
        getPlaca= teclado.nextLine();

    }

    public void estacionar(Vaga vaga) {
System.out.print("Digite a vaga no formato 8y:(8-numero da vaga, y-fileira da vaga)");
vaga = teclado.nextLine();
        System.out.println("Insira o horario do inicio da estadia no estacionamento:(formato HH:mm):");
        time1 = LocalTime.parse(teclado.nextLine());


    }

    public double sair() {
        System.out.println("Insira o horario de saida do estacionamento:(formato HH:mm):");
        time2 = LocalTime.parse(teclado.nextLine());


    }

    public double totalArrecadado() {
        Duration duration = Duration.between(time1, time2);
        long totalMinutes = duration.toMinutes();
        long blocksOf15Minutes = (totalMinutes + 14) / 15;
        long cost = blocksOf15Minutes * 4;
        if (cost > 50) {
            cost = 50;
        }
        System.out.println("Custo da estadia: R$" + cost);


    }

    public double arrecadadoNoMes(int mes) {

    }

    public int totalDeUsos() {

    }

    private static void main(String[] args){

    }

}