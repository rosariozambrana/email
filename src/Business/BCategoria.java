
package Business;

import Data.DCategoria;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author angela
 */
public class BCategoria {
    private DCategoria dCategoria;
    
    public BCategoria(){
        dCategoria = new DCategoria();
    }
   public void guardar(String nombre, String descripcion) throws SQLException, ParseException{
       dCategoria.guardar(nombre,descripcion);
       dCategoria.desconectar();
   }
   
   public void modificar(int id, String nombre, String descripcion) throws SQLException, ParseException{
       dCategoria.modificar(id,nombre,descripcion);
       dCategoria.desconectar();
   }
   
   public void eliminar(int id) throws SQLException{
       dCategoria.eliminar(id);
       dCategoria.desconectar();
   }
   
   public List<String[]>listar() throws SQLException{
       ArrayList<String[]> categoria = (ArrayList<String[]>) dCategoria.listar();
       dCategoria.desconectar();
       return categoria;
   }
    public String[] ver(int id) throws SQLException{
        String[] categoria = dCategoria.ver(id);
        dCategoria.desconectar();
        return categoria;
    }
}
