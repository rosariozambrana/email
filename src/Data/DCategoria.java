
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
public class DCategoria {
    private SQLConnection connection;
    
    public DCategoria(){
        connection = new SQLConnection(
                ConstPSQL.user, 
                ConstPSQL.pass, 
                ConstGlobal.SERVIDOR, 
                ConstGlobal.PORT_DB, 
                ConstPSQL.dbName);
    }
  
    
    
    public void guardar(String nombre, String descripcion) throws SQLException, ParseException{
        String query = "INSERT INTO categoria(nombre, descripcion)"
                + "values(?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
//        ps.setInt(1, id_categoria);
        ps.setString(1, nombre);
        ps.setString(2, descripcion);
        
//        ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
//        ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DCategoria.java dice: "
            + "Ocurrio un error al guardar una categoria");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String nombre, String descripcion) throws SQLException, ParseException{
        String query = "UPDATE categoria SET nombre=?, descripcion=? WHERE id_categoria=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setString(2, descripcion);
        ps.setInt(3, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DCategoria.java dice: "
            +"Ocurrio un error al modificar una categoria");
            throw new SQLException();
        }
    }
    
    
    
    public void eliminar(int id) throws SQLException{
        String query ="DELETE FROM categoria WHERE id_categoria=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DCAtegoria.java dice: "
            + "Ocurrio un error al eliminar una Categoria");
            throw new SQLException();  
        }
    }
   
    
    public List<String[]>listar() throws SQLException{
        List<String[]> categoria = new ArrayList<>();
        String query = "SELECT * FROM categoria";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            categoria.add(new String[]{
                String.valueOf(set.getInt("id_categoria")),
                set.getString("nombre"),
                set.getString("descripcion")
            });      
        }
        return categoria;       
    }
    
    public String[] ver(int id) throws SQLException{
        String[] categoria = null;
        String query = "SELECT * FROM categoria WHERE id_categoria=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        
        ResultSet set = ps.executeQuery();
        if(set.next()){
            categoria = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                set.getString("descripcion")   
            };
            
        }
        return categoria;
    }
    
    public void desconectar(){
        if(connection != null){
            connection.closeConnection();
        }
    
    }
}
