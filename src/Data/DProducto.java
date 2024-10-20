
package Data;

import connection.SQLConnection;
import java.sql.*;
import java.util.*;
import java.text.ParsePosition;
import java.time.LocalDateTime;
import Utils.ConstPSQL;
import Utils.DateString;
import Utils.ConstGlobal;
import java.text.ParseException;



public class DProducto {
    private SQLConnection connection;
    
    public DProducto(){
        connection = new SQLConnection(
                ConstPSQL.user, 
                ConstPSQL.pass, 
                ConstGlobal.SERVIDOR, 
                ConstGlobal.PORT_DB, 
                ConstPSQL.dbName);
    }
  
    
    
    public void guardar(String nombre, int codigo, String descripcion, Double precio,int categoria_id, int medida_id) throws SQLException, ParseException{
        String query = "INSERT INTO producto(nombre, codigo, descripcion, precio, categoria_id, medida_id)"
                + "values(?,?,?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, codigo);
        ps.setString(3, descripcion);
        ps.setDouble(4, precio);
        ps.setInt(5, categoria_id);
        ps.setInt(6, medida_id);
//        ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
//        ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DProducto.java dice: "
            + "Ocurrio un error al guardar una producto");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String nombre, int codigo, String descripcion, Double precio,int categoria_id, int medida_id) throws SQLException, ParseException{
        String query = "UPDATE producto SET nombre=?, codigo=?, descripcion=?, precio=?, categoria_id=?, medida_id=?  WHERE id_producto=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, codigo);
        ps.setString(3, descripcion);
        ps.setDouble(4, precio);
        ps.setInt(5, categoria_id);
        ps.setInt(6, medida_id);
        ps.setInt(7, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DProducto.java dice: "
            +"Ocurrio un error al modificar una producto");
            throw new SQLException();
        }
    }
    
    public void eliminar(int id) throws SQLException{
        String query ="DELETE FROM producto WHERE id_producto=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DProducto.java dice: "
            + "Ocurrio un error al eliminar una Producto");
            throw new SQLException();  
        }
    }
   
    public List<String[]>listar() throws SQLException{
        List<String[]> producto = new ArrayList<>();
        String query = "SELECT * FROM producto";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            producto.add(new String[]{
                String.valueOf(set.getInt("id_producto")),
                set.getString("nombre"),
                String.valueOf(set.getInt("codigo")),
                set.getString("descripcion"),
                String.valueOf(set.getDouble("precio")),
                String.valueOf(set.getInt("categoria_id")),
                String.valueOf(set.getInt("medida_id"))
            });      
        }
        return producto;       
    }
    
    public String[] ver(int id) throws SQLException{
        String[] producto = null;
        String query = "SELECT * FROM producto WHERE id_producto=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        
        ResultSet set = ps.executeQuery();
        if(set.next()){
            producto = new String[]{
                String.valueOf(set.getInt("id_producto")),
                set.getString("nombre"),
                String.valueOf(set.getInt("codigo")),
                set.getString("descripcion"),
                String.valueOf(set.getDouble("precio")),
                String.valueOf(set.getInt("categoria_id")),
                String.valueOf(set.getInt("medida_id"))   
            };
            
        }
        return producto;
    }
    
    public void desconectar(){
        if(connection != null){
            connection.closeConnection();
        }
    
    }
}
