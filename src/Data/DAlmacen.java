
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



public class DAlmacen {
    private SQLConnection connection;
    
    public DAlmacen(){
        connection = new SQLConnection(
                ConstPSQL.user, 
                ConstPSQL.pass, 
                ConstGlobal.SERVIDOR, 
                ConstGlobal.PORT_DB, 
                ConstPSQL.dbName);
    }
  
    
    
    public void guardar( int codigo, String direccion) throws SQLException, ParseException{
        String query = "INSERT INTO almacen(codigo,direccion)"
                + "values(?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, codigo);
        ps.setString(2, direccion);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DAlmacen.java dice: "
            + "Ocurrio un error al guardar una almacen");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, int codigo, String direccion) throws SQLException, ParseException{
        String query = "UPDATE almacen SET codigo=?, direccion=? WHERE id_almacen=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, codigo);
        ps.setString(2, direccion);
        ps.setInt(3, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DAlmacen.java dice: "
            +"Ocurrio un error al modificar una almacen");
            throw new SQLException();
        }
    }
    
    public void eliminar(int id) throws SQLException{
        String query ="DELETE FROM almacen WHERE id_almacen=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DAlmacen.java dice: "
            + "Ocurrio un error al eliminar una Almacen");
            throw new SQLException();  
        }
    }
   
    public List<String[]>listar() throws SQLException{
        List<String[]> almacen = new ArrayList<>();
        String query = "SELECT * FROM almacen";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            almacen.add(new String[]{
                String.valueOf(set.getInt("id_almacen")),
                String.valueOf(set.getInt("codigo")),
                set.getString("direccion")
            });      
        }
        return almacen;       
    }
    
    public String[] ver(int id) throws SQLException{
        String[] almacen = null;
        String query = "SELECT * FROM almacen WHERE id_almacen=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        
        ResultSet set = ps.executeQuery();
        if(set.next()){
            almacen = new String[]{
               String.valueOf(set.getInt("id_almacen")),
                String.valueOf(set.getInt("codigo")),
                set.getString("direccion")   
            };
            
        }
        return almacen;
    }
    
    public void desconectar(){
        if(connection != null){
            connection.closeConnection();
        }
    
    }
}
