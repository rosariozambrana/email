
package Data;

import connection.SQLConnection;
import java.sql.*;
import java.util.*;
import java.text.ParsePosition;
import java.time.LocalDateTime;
import Utils.ConstPSQL;
import Utils.DateString;
import Utils.ConstGlobal;
import java.math.BigDecimal;
import java.text.ParseException;



public class DPrecio {
    private SQLConnection connection;
    
    public DPrecio(){
        connection = new SQLConnection(
                ConstPSQL.user, 
                ConstPSQL.pass, 
                ConstGlobal.SERVIDOR, 
                ConstGlobal.PORT_DB, 
                ConstPSQL.dbName);
    }
  
    
    
    public void guardar(Double precio,String detalle) throws SQLException, ParseException{
        String query = "INSERT INTO precio(precio, detalle_medida)"
                + "values(?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
//        ps.setInt(1, id_categoria);
        ps.setDouble(1, precio);
        ps.setString(2, detalle);
//        ps.setString(2, descripcion);
        
//        ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
//        ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DPrecio.java dice: "
            + "Ocurrio un error al guardar una precio");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, Double precio, String detalle) throws SQLException, ParseException{
        String query = "UPDATE precio SET precio=?, detalle_medida=? WHERE id_precio=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setDouble(1, precio);
        ps.setString(2, detalle);
//        ps.setString(2, descripcion);
        ps.setInt(3, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DPrecio.java dice: "
            +"Ocurrio un error al modificar una precio");
            throw new SQLException();
        }
    }
    
    
    
    public void eliminar(int id) throws SQLException{
        String query ="DELETE FROM precio WHERE id_precio=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DPrecio.java dice: "
            + "Ocurrio un error al eliminar una Precio");
            throw new SQLException();  
        }
    }
   
    
    public List<String[]>listar() throws SQLException{
        List<String[]> precio = new ArrayList<>();
        String query = "SELECT * FROM precio";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            precio.add(new String[]{
                String.valueOf(set.getInt("id_precio")),
                String.valueOf(set.getBigDecimal("precio")),
                set.getString("detalle_medida")
            });      
        }
        return precio;       
    }
    
    public String[] ver(int id) throws SQLException{
        String[] precio = null;
        String query = "SELECT * FROM precio WHERE id_precio=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        
        ResultSet set = ps.executeQuery();
        if(set.next()){
            precio = new String[]{
                String.valueOf(set.getInt("id_precio")),
                String.valueOf(set.getBigDecimal("precio")),
                set.getString("detalle_medida"),
//                set.getString("descripcion")   
            };
            
        }
        return precio;
    }
    
    public void desconectar(){
        if(connection != null){
            connection.closeConnection();
        }
    
    }
}
