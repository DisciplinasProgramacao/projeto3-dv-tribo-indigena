
    import static org.junit.Assert.*;
        import org.junit.Test;

public class ClienteTest {

    @Test
    public void testClienteCreationWithId() {
        Cliente cliente = new Cliente("12345");
        assertEquals("12345", cliente.get_id());
    }

    @Test
    public void testClienteCreationWithoutId() {
        Cliente cliente = new Cliente("");
        assertNotNull(cliente.get_id());
        assertFalse(cliente.get_id().isEmpty());
        assertTrue(cliente.get_id().matches("^[0-9a-fA-F-]+$")); // Verifica se é um UUID válido
    }

    @Test
    public void testGetAndSetName() {
        Cliente cliente = new Cliente("123");
        cliente.set_name("João");
        assertEquals("João", cliente.get_name());
    }


    @Test
    public void testGetAndSetTipoCliente() {
        Cliente cliente = new Cliente("123");
        cliente.set_tipoCliente(TiposCliente.MANHA);
        assertEquals(TiposCliente.MANHA, cliente.get_tipoCliente());

        cliente.set_tipoCliente(TiposCliente.TARDE);
        assertEquals(TiposCliente.TARDE, cliente.get_tipoCliente());

    }

    @Test
    public void testGetParsedTipo() {
        Cliente cliente = new Cliente("123");

        cliente.set_tipoCliente(TiposCliente.MANHA);
        assertEquals("Turno da Manha", cliente.getParsedTipo());

        cliente.set_tipoCliente(TiposCliente.TARDE);
        assertEquals("Turno da Tarde", cliente.getParsedTipo());

        cliente.set_tipoCliente(TiposCliente.NOITE);
        assertEquals("Turno da Noite", cliente.getParsedTipo());

        cliente.set_tipoCliente(TiposCliente.HORISTA);
        assertEquals("Horista", cliente.getParsedTipo());

        cliente.set_tipoCliente(TiposCliente.MENSALISTA);
        assertEquals("Mensalista", cliente.getParsedTipo());
    }
}
