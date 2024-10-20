
package Business;

import Data.DPrecio;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class BPrecio {
    private DPrecio dPrecio;
    
    public BPrecio(){
        dPrecio = new DPrecio();
    }
   public void guardar(Double precio, String detalle) throws SQLException, ParseException{
       dPrecio.guardar(precio, detalle);
       dPrecio.desconectar();
   }
   
   public void modificar(int id, Double precio, String detalle) throws SQLException, ParseException{
       dPrecio.modificar(id, precio, detalle);
       dPrecio.desconectar();
   }
   
   public void eliminar(int id) throws SQLException{
       dPrecio.eliminar(id);
       dPrecio.desconectar();
   }
   
   public List<String[]>listar() throws SQLException{
       ArrayList<String[]> precio = (ArrayList<String[]>) dPrecio.listar();
       dPrecio.desconectar();
       return precio;
   }
    public String[] ver(int id) throws SQLException{
        String[] precio = dPrecio.ver(id);
        dPrecio.desconectar();
        return precio;
    }
}
