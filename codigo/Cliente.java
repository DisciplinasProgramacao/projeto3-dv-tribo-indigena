import org.json.simple.JSONObject;

import java.util.UUID;

public class Cliente {
    private String _id;
    private String _name;

    Cliente(String id) {
        if (id.isEmpty()){
            this._id = String.valueOf(UUID.randomUUID());
        } else {
            this._id = id;
        }
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

    public JSONObject getObj(){
        JSONObject clienteEntry = new JSONObject();
        clienteEntry.put("id", this._id);
        clienteEntry.put("name", this._name);
        return clienteEntry;
    }
}
