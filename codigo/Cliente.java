import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;

public class Cliente implements Serializable {
    private String _id;
    private String _name;
    private TiposCliente _tipoCliente;
    Cliente(String id) {
        if (id.isEmpty()){
            this._id = String.valueOf(UUID.randomUUID());
        } else {
            this._id = id;
        }
    }

    public TiposCliente get_tipoCliente() {
        return _tipoCliente;
    }

    public String get_name() {
        return _name;
    }

    public String get_id() {
        return _id;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void set_tipoCliente(TiposCliente _tipoCliente) {
        this._tipoCliente = _tipoCliente;
    }

    public String getParsedTipo(){
        String tipo = "";
        switch (this._tipoCliente){
            case MANHA -> {
                tipo = "Turno da Manha";
            }
            case TARDE -> {
                tipo = "Turno da Tarde";
            }
            case NOITE -> {
                tipo = "Turno da Noite";
            }
            case HORISTA -> {
                tipo = "Horista";
            }
            case MENSALISTA -> {
                tipo = "Mensalista";
            }
        }
        return tipo;
    }
}
