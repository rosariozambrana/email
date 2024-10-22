/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Utils.ConstGlobal;
import Utils.ConstPSQL;
import connection.SQLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author fpl
 */
public class DDetalleCompra {

    private SQLConnection connection;

    final String table = "compra";
    final String queryInsert = "INSERT INTO " + table + "(cantidad, precio_unitario, subtotal, compra_id, producto_id) values(?,?,?,?,?)";
    final String queryUpdate = "UPDATE" + table + " SET cantidad=?, precio_unitario=?, subtotal=?, compra_id=?, producto_id=? WHERE id_detallecompra=?";
    final String queryDelete = "DELETE FROM " + table + " WHERE id_detallecompra=?";
    final String queryVer = "SELECT * FROM " + table + " WHERE id_detallecompra=?";
    final String queryListar = "SELECT * FROM " + table;

    final String messageTryCatch = "Class DDetalleCompra.java dice: Ocurrio un error en el proceso ";

    
    
    private String[] arrayData(ResultSet set) throws SQLException {
        return new String[]{
            String.valueOf(set.getInt("id_detallecompra")),
            String.valueOf(set.getDouble("cantidad")),
            String.valueOf(set.getDouble("precio_unitario")),
            String.valueOf(set.getDouble("subtotal")),            
            String.valueOf(set.getInt("compra_id")),
            String.valueOf(set.getInt("producto_id"))
        };
    }

    public DDetalleCompra() {
        connection = new SQLConnection(
                ConstPSQL.user,
                ConstPSQL.pass,
                ConstGlobal.SERVIDOR,
                ConstGlobal.PORT_DB,
                ConstPSQL.dbName);
    }

    public void guardar(double cantidad, double precio_unitario, double subtotal, int compra_id, int producto_id) throws SQLException, ParseException {
        PreparedStatement ps = connection.connect().prepareStatement(queryInsert);
        ps.setDouble(1, cantidad);
        ps.setDouble(2, precio_unitario);
        ps.setDouble(3, subtotal);
        ps.setInt(4, compra_id);        
        ps.setInt(5, producto_id);
        if (ps.executeUpdate() == 0) {
            System.err.println(messageTryCatch + "GUARDAR PERSONA");
            throw new SQLException();
        }
        ps.close();
    }

    public void modificar(int id_detallecompra, double cantidad, double precio_unitario, double subtotal, int compra_id, int producto_id) throws SQLException, ParseException {
        PreparedStatement ps = connection.connect().prepareStatement(queryUpdate);
        ps.setDouble(1, cantidad);
        ps.setDouble(2, precio_unitario);
        ps.setDouble(3, subtotal);
        ps.setInt(4, compra_id);        
        ps.setInt(5, producto_id);
        ps.setInt(6, id_detallecompra);
        if (ps.executeUpdate() == 0) {
            System.err.println(messageTryCatch + "MODIFICAR PERSONA");
            throw new SQLException();
        }
        ps.close();
    }

    public void eliminar(int id_detallecompra) throws SQLException {
        PreparedStatement ps = connection.connect().prepareStatement(queryDelete);
        ps.setInt(1, id_detallecompra);
        if (ps.executeUpdate() == 0) {
            System.err.println(messageTryCatch + "ELIMINAR PERSONA");
            throw new SQLException();
        }
        ps.close();
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> list = new ArrayList<>();
        PreparedStatement ps = connection.connect().prepareStatement(queryListar);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            list.add(arrayData(set));
        }
        set.close();
        ps.close();
        return list;
    }

    public String[] ver(int id_detallecompra) throws SQLException {
        String[] data = null;
        PreparedStatement ps = connection.connect().prepareStatement(queryVer);
        ps.setInt(1, id_detallecompra);
        ResultSet set = ps.executeQuery();
        if (set.next()) {
            data = arrayData(set);
        }
        set.close();
        ps.close();
        return data;
    }

    public void desconectar() {
        if (connection != null) {
            connection.closeConnection();
        }

    }
}
