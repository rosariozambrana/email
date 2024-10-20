
package Business;

import Data.DInventario;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;


public class BInventario {
    private DInventario dInventario;
    
    public BInventario(){
        dInventario = new DInventario();
    }
   public void guardar(int producto_id, int almacen_id, int stock, Date fecha) throws SQLException, ParseException{
       dInventario.guardar(producto_id,almacen_id,stock,fecha);
       dInventario.desconectar();
   }
   
   public void modificar(int producto_id, int almacen_id, int stock, Date fecha) throws SQLException, ParseException{
       dInventario.modificar(producto_id,almacen_id,stock,fecha);
       dInventario.desconectar();
   }
   
   public void eliminar(int producto_id, int almacen_id) throws SQLException{
       dInventario.eliminar(producto_id, almacen_id);
       dInventario.desconectar();
   }
   
   public List<String[]>listar() throws SQLException{
       ArrayList<String[]> inventario = (ArrayList<String[]>) dInventario.listar();
       dInventario.desconectar();
       return inventario;
   }
    public String[] ver(int id) throws SQLException{
        String[] producto = dInventario.ver(id);
        dInventario.desconectar();
        return producto;
    }
}
