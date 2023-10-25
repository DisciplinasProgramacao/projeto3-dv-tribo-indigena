import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingGarageTests {

    @Test
    public void testVehicleCreation() {
        Veiculo vehicle = new Veiculo("ABC-1234");
        assertEquals("ABC-1234", vehicle.getPlaca());
    }

    @Test
    public void testVehicleParking() {
        Veiculo vehicle = new Veiculo("ABC-1234");
        vehicle.estacionar("A1");
        // You might need some method to check if the vehicle is parked.
    }

    @Test
    public void testVehicleExiting() {
        Veiculo vehicle = new Veiculo("ABC-1234");
        vehicle.estacionar("A1");
        vehicle.sair();
        // You might need some method to check if the vehicle has exited.
    }

    @Test
    public void testServiceSelection() {
        Veiculo vehicle = new Veiculo("ABC-1234");
        vehicle.selecionarServico("manobrista");
        // You might need some method to check the total service cost.
    }

    @Test
    public void testClientCreation() {
        Cliente client = new Cliente(null);
        client.set_name("Jao");
        assertNotNull(client.get_id());
        assertEquals("Jao", client.get_name());
    }

    @Test
    public void testClientJSONObject() {
        Cliente client = new Cliente(null);
        client.set_name("Jao");
        JSONObject jsonObj = client.getObj();
        assertEquals(client.get_id(), jsonObj.get("id"));
        assertEquals("Jao", jsonObj.get("name"));
    }
}
