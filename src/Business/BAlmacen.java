
package Business;

import Data.DAlmacen;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class BAlmacen {
    private DAlmacen dAlmacen;
    
    public BAlmacen(){
        dAlmacen = new DAlmacen();
    }
   public void guardar(int codigo, String direccion) throws SQLException, ParseException{
       dAlmacen.guardar(codigo,direccion);
       dAlmacen.desconectar();
   }
   
   public void modificar(int id, int codigo, String direccion) throws SQLException, ParseException{
       dAlmacen.modificar(id,codigo,direccion);
       dAlmacen.desconectar();
   }
   
   public void eliminar(int id) throws SQLException{
       dAlmacen.eliminar(id);
       dAlmacen.desconectar();
   }
   
   public List<String[]>listar() throws SQLException{
       ArrayList<String[]> almacen = (ArrayList<String[]>) dAlmacen.listar();
       dAlmacen.desconectar();
       return almacen;
   }
    public String[] ver(int id) throws SQLException{
        String[] almacen = dAlmacen.ver(id);
        dAlmacen.desconectar();
        return almacen;
    }
}
