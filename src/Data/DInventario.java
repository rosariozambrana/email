
package Data;

import connection.SQLConnection;
import java.sql.*;
import java.util.*;
import java.sql.Date;
import Utils.ConstPSQL;
import Utils.DateString;
import Utils.ConstGlobal;
import java.text.ParseException;

public class DInventario {
    private SQLConnection connection;
    
    public DInventario(){
        connection = new SQLConnection(
                ConstPSQL.user, 
                ConstPSQL.pass, 
                ConstGlobal.SERVIDOR, 
                ConstGlobal.PORT_DB, 
                ConstPSQL.dbName);
    }
  
    
    
    public void guardar(int producto_id, int almacen_id, int stock, Date fecha) throws SQLException, ParseException{
        String query = "INSERT INTO inventario(producto_id, almacen_id, stock, fecha)"
                + "values(?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, producto_id);
        ps.setInt(2, almacen_id);
        ps.setInt(3, stock);
        ps.setDate(4, fecha);
        
//        ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
//        ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DInventario.java dice: "
            + "Ocurrio un error al guardar una inventario");
            throw new SQLException();
        }
    }
    
    public void modificar(int producto_id, int almacen_id, int stock, java.sql.Date fecha) throws SQLException, ParseException {
    String query = "UPDATE inventario SET stock=?, fecha=? WHERE producto_id=? AND almacen_id=?";
    PreparedStatement ps = connection.connect().prepareStatement(query);
    
    // Asigna los parámetros correctamente
    ps.setInt(1, stock);          // stock a modificar
    ps.setDate(2, fecha);         // fecha a modificar
    ps.setInt(3, producto_id);    // producto a identificar
    ps.setInt(4, almacen_id);     // almacén a identificar

    if (ps.executeUpdate() == 0) {
        System.err.println("Class DInventario.java dice: "
            + "Ocurrio un error al modificar el inventario");
        throw new SQLException();
    }
}

    
    public void eliminar(int producto_id, int almacen_id) throws SQLException{
        String query ="DELETE FROM inventario WHERE producto_id=? and almacen_id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, producto_id);
        ps.setInt(2, almacen_id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DInventario.java dice: "
            + "Ocurrio un error al eliminar una inventario");
            throw new SQLException();  
        }
    }
   
    public List<String[]>listar() throws SQLException{
        List<String[]> inventario = new ArrayList<>();
        String query = "SELECT * FROM inventario";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            inventario.add(new String[]{
                String.valueOf(set.getInt("producto_id")),
                String.valueOf(set.getInt("almacen_id")),
                String.valueOf(set.getInt("stock")),
                String.valueOf(set.getDate("fecha"))
                
            });      
        }
        return inventario;       
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
