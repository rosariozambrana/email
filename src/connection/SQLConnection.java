
package connection;

import Utils.ConstPSQL;
import java.sql.*;

/**
 *conexion con postgresql
 * @author angela
 */
public class SQLConnection {
      private static final String DRIVER = ConstPSQL.PSQL;
    
    
    private Connection connection;
    private String user;
    private String pass;
    private String host;
    private int port;
    private String name;
    private String url;
    
    //constructor

    public SQLConnection(String user, String pass, String host, int port, String name) {
        this.user = user;
        this.pass = pass;
        this.host = host;
        this.port = port;
        this.name = name;
        this.url = DRIVER + host + ":" + port + "/" + name;
    }
    
    public Connection connect() throws SQLException{
        
        connection = DriverManager.getConnection(url,user,pass);
        return connection;
    }
    
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Class SqlConnection.java dice:"
                    + "Ocurrio un error al momento de cerrar la conexion closeConnection()");
        }
    }
    
    
    
}
