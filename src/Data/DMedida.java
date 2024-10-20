
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


/**
 *
 * @author angela
 */
public class DMedida {
    private SQLConnection connection;
    
    public DMedida(){
        connection = new SQLConnection(
                ConstPSQL.user, 
                ConstPSQL.pass, 
                ConstGlobal.SERVIDOR, 
                ConstGlobal.PORT_DB, 
                ConstPSQL.dbName);
    }
  
    
    
    public void guardar(String detalle) throws SQLException, ParseException{
        String query = "INSERT INTO medida(detalle)"
                + "values(?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
//        ps.setInt(1, id_categoria);
        ps.setString(1, detalle);
//        ps.setString(2, descripcion);
        
//        ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
//        ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DMedida.java dice: "
            + "Ocurrio un error al guardar una Medida");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String detalle) throws SQLException, ParseException{
        String query = "UPDATE medida SET detalle=? WHERE id_medida=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, detalle);
//        ps.setString(2, descripcion);
        ps.setInt(2, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DMedida.java dice: "
            +"Ocurrio un error al modificar una Medida");
            throw new SQLException();
        }
    }
    
    
    
    public void eliminar(int id) throws SQLException{
        String query ="DELETE FROM medida WHERE id_medida=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DMedida.java dice: "
            + "Ocurrio un error al eliminar una Medida");
            throw new SQLException();  
        }
    }
   
    
    public List<String[]>listar() throws SQLException{
        List<String[]> medida = new ArrayList<>();
        String query = "SELECT * FROM medida";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            medida.add(new String[]{
                String.valueOf(set.getInt("id_medida")),
                set.getString("detalle"),
//                set.getString("descripcion")
            });      
        }
        return medida;       
    }
    
    public String[] ver(int id) throws SQLException{
        String[] medida = null;
        String query = "SELECT * FROM medida WHERE id_medida=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        
        ResultSet set = ps.executeQuery();
        if(set.next()){
            medida = new String[]{
                String.valueOf(set.getInt("id_medida")),
                set.getString("detalle"),
//                set.getString("descripcion")   
            };
            
        }
        return medida;
    }
    
    public void desconectar(){
        if(connection != null){
            connection.closeConnection();
        }
    
    }
}
