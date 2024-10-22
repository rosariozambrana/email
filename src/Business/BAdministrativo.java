package Business;

import Data.DAdministrativo;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
/**
 *
 * @author angela
 */
public class BAdministrativo {

    private DAdministrativo dAdministrativo;

    public BAdministrativo() {
        dAdministrativo = new DAdministrativo();
    }

    public void guardar(int id_persona, int id_administrativo, String cargo) throws SQLException, ParseException {
        dAdministrativo.guardar(id_persona, id_administrativo, cargo);
        dAdministrativo.desconectar();
    }

    public void modificar(int id_persona, int id_administrativo, String cargo, Date fecha_ingreso) throws SQLException, ParseException {
        dAdministrativo.modificar(id_persona, id_administrativo, cargo, fecha_ingreso);
        dAdministrativo.desconectar();
    }

    public void eliminar(int id_persona, int id_administrativo) throws SQLException {
        dAdministrativo.eliminar(id_persona, id_administrativo);
        dAdministrativo.desconectar();
    }

    public List<String[]> listar() throws SQLException {
        ArrayList<String[]> categoria = (ArrayList<String[]>) dAdministrativo.listar();
        dAdministrativo.desconectar();
        return categoria;
    }

    public String[] ver(int id_persona, int id_administrativo) throws SQLException {
        String[] data = dAdministrativo.ver(id_persona, id_administrativo);
        dAdministrativo.desconectar();
        return data;
    }
}
