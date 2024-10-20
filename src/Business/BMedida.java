
package Business;

import Data.DMedida;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author angela
 */
public class BMedida {
    private DMedida dMedida;
    
    public BMedida(){
        dMedida = new DMedida();
    }
   public void guardar(String detalle) throws SQLException, ParseException{
       dMedida.guardar(detalle);
       dMedida.desconectar();
   }
   
   public void modificar(int id, String detalle) throws SQLException, ParseException{
       dMedida.modificar(id, detalle);
       dMedida.desconectar();
   }
   
   public void eliminar(int id) throws SQLException{
       dMedida.eliminar(id);
       dMedida.desconectar();
   }
   
   public List<String[]>listar() throws SQLException{
       ArrayList<String[]> medida = (ArrayList<String[]>) dMedida.listar();
       dMedida.desconectar();
       return medida;
   }
    public String[] ver(int id) throws SQLException{
        String[] medida = dMedida.ver(id);
        dMedida.desconectar();
        return medida;
    }
}
