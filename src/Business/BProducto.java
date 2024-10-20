
package Business;

import Data.DProducto;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class BProducto {
    private DProducto dProducto;
    
    public BProducto(){
        dProducto = new DProducto();
    }
   public void guardar(String nombre, int codigo, String descripcion, Double precio,int categoria_id, int medida_id) throws SQLException, ParseException{
       dProducto.guardar(nombre,codigo,descripcion,precio,categoria_id,medida_id);
       dProducto.desconectar();
   }
   
   public void modificar(int id, String nombre, int codigo, String descripcion, Double precio,int categoria_id, int medida_id) throws SQLException, ParseException{
       dProducto.modificar(id,nombre,codigo,descripcion,precio,categoria_id,medida_id);
       dProducto.desconectar();
   }
   
   public void eliminar(int id) throws SQLException{
       dProducto.eliminar(id);
       dProducto.desconectar();
   }
   
   public List<String[]>listar() throws SQLException{
       ArrayList<String[]> producto = (ArrayList<String[]>) dProducto.listar();
       dProducto.desconectar();
       return producto;
   }
    public String[] ver(int id) throws SQLException{
        String[] producto = dProducto.ver(id);
        dProducto.desconectar();
        return producto;
    }
}
