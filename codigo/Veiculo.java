import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.Duration;
import java.time.LocalTime;

public class Veiculo {
    private String placa;
    private UsoDeVaga usoAtual;
    private Servicos servicos;
    private Boolean _isEstacionado;

    public Veiculo(String placa) {
        if (this.exists(placa)){
            this.placa = placa;
        } else {
            this.placa = placa;
            this.servicos = new Servicos();
        }
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
        double custo = usoAtual.calcularCusto(usoAtual.getDuracao()) + servicos.getTotalServiceCost();
        usoAtual = null;
        return custo;
    }

    public void selecionarServico(String nomeServico) {
        servicos.selecionar(nomeServico);
    }


    public JSONObject getObj(){
        JSONObject veiculos= new JSONObject();
        veiculos.put("palca", this.placa);
        veiculos.put("precoTotal", this.usoAtual);
        veiculos.put("is_estacionado", this._isEstacionado);
        return veiculos;
    }

    public void set_isEstacionado(Boolean _isEstacionado) {
        this._isEstacionado = _isEstacionado;
    }

    private Boolean exists(String placa){
        JSONObject data = App.getData();
        JSONArray jsonArray = (JSONArray) data.get("veiculos");
        Boolean found = false;
        for (int i=0;i<jsonArray.size();i++){
            JSONObject obj = (JSONObject) jsonArray.get(i);
            if (String.valueOf(obj.get("placa")).equals(placa)){
                found = true;
                this._isEstacionado = (Boolean) obj.get("is_estacionado");
                System.out.println(this._isEstacionado);
                break;
            }
        }
        return found;
    }
}