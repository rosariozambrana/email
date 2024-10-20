
package Consultas;

import Utils.ConstGlobal;
import Utils.Help;
import Utils.Subject;
import Business.BCategoria;
import Business.BMedida;
import Business.BPrecio;
import Business.BProducto;
import Business.BAlmacen;
import Business.BInventario;
import connection.Pop3;
import connection.Smtp;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import Object.Mensaje;




public class Consulta {
     private final BCategoria categoria;
     private final BMedida medida;
     private final BPrecio precio;
     private final BProducto producto;
     private final BAlmacen almacen;
     private final BInventario inventario;
   // private Smtp smtp;
    private Pop3 pop3;

    public Consulta() throws IOException {
        // negocio o bussinness
        categoria = new BCategoria();
        medida = new BMedida();
        precio = new BPrecio();
        producto = new BProducto();
        almacen = new BAlmacen();
        inventario = new BInventario();
    }  
    public int getCantidadMails() throws IOException {
        pop3 = new Pop3(ConstGlobal.SERVIDOR, ConstGlobal.PORT_POP3);
        pop3.login(ConstGlobal.USER, ConstGlobal.PASS);
        pop3.list();
        String number = pop3.number;
//        System.out.println(number);
        pop3.retr(Integer.parseInt(number));
//      pop3.retr(Integer.parseInt(number));
        pop3.quit();
        pop3.close();
        //System.out.println(number);
        return Integer.parseInt(number);
    }
    public void newMensaje() throws IOException {
        String token = pop3.Token();
        String email = pop3.getEmail();
        System.out.println("TOKEN: "+token);        
        System.out.println("EMAIL: "+email);

        if (token.toUpperCase().contains(Help.HELP)) {
            sendMail(email, "Ayuda", Help.ContenidoHelp());
            return;
        }
        Mensaje msj = Subject.subject(token, email);
        if (msj == null) {
            sendMail(email, "Error de comandos", comandoIncorrecto(token));
            return;
        }
        try {
            negocioAction(msj);
        } catch (NumberFormatException e) {
            System.out.println("CATCH 1");
            System.err.println(e);
            sendMail(msj.getEmisor(), "Error al convertir un parametros", errorConvertirParametros(msj));
        } catch (ParseException ex) {
            System.out.println("CATCH 2");
            System.err.println(ex);
            sendMail(msj.getEmisor(), "Error al convertir un parametros", errorConvertirParametros(msj));
        } catch (SQLException ex) {
            System.err.println(ex);
            System.out.println("CATCH 3");
            sendMail(msj.getEmisor(), "Error de conexion", errorConexion(ex.toString(),msj));
        }catch (Exception ex) {
            System.out.println("CATCH 4");
            System.err.println(ex);
            sendMail(msj.getEmisor(), "Error de conexion", errorConexion(ex.toString(),msj));
        }
    }   
    private void negocioAction(Mensaje msj) throws IOException, SQLException, ParseException {
        String tableAction = msj.tableAction();
        switch (tableAction) {
            //CATEGORIA 
            case Help.CATEGORIA + "_" + Help.LIS: {
                System.out.println("INGRESAMOS AL METODO");
                List<String[]> lista = categoria.listar();
                list(Help.categoriaHeader, lista, msj);
                break;
            }
            
            case Help.CATEGORIA + "_" + Help.ADD: {
                if (msj.getParametros().size() == Help.LENPARAM2) {
//                   int id_categoria = Integer.parseInt(msj.getParametros().get(0));
                    String nombre = msj.getParametros().get(0);
                    String descripcion = msj.getParametros().get(1);
//                    String email = msj.getParametros().get(3);
//                    String telefono = msj.getParametros().get(4);
//                    String fechaNacimiento = msj.getParametros().get(5);
                    categoria.guardar(nombre, descripcion);
                    sendMail(msj.getEmisor(), msj.evento(), "Los datos insertado a la tabla " + msj.getTable() + " se guardo con exito");
                } else {
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj));
                }
                break;
                }
            case Help.CATEGORIA + "_" + Help.DEL:{
                if(msj.getParametros().size() == Help.LENPARAM1){ 
                    int id = Integer.parseInt(msj.getParametros().get(0));
                    categoria.eliminar(id);
                    sendMail(msj.getEmisor(), msj.evento(), "El dato de la tabla " + msj.getTable() + " se elimino con exito");
                }else{
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj)); 
                }
                break;
            }
            case Help.CATEGORIA + "_" + Help.MOD: {
                if (msj.getParametros().size() == Help.LENPARAM3) {
                    int id = Integer.parseInt(msj.getParametros().get(0)); // Obtener el id
                    String nombre = msj.getParametros().get(1);
                    String descripcion = msj.getParametros().get(2);
                    categoria.modificar(id, nombre, descripcion);                  
                    sendMail(msj.getEmisor(), msj.evento(), "La " + msj.getTable() + " ha sido modificado con exito");
                } else {
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj));
                }
                break;
            }
           
            //CATEGORIA END
            
            
            //MEDIDA 
            case Help.MEDIDA + "_" + Help.LIS: {
                System.out.println("INGRESAMOS AL METODO");
                List<String[]> lista = medida.listar();
                list(Help.medidaHeader, lista, msj);
                break;
            }
            
            case Help.MEDIDA + "_" + Help.ADD: {
                if (msj.getParametros().size() == Help.LENPARAM1) {
//                   int id_categoria = Integer.parseInt(msj.getParametros().get(0));
                    String detalle = msj.getParametros().get(0);
//                    String descripcion = msj.getParametros().get(1);
//                    String email = msj.getParametros().get(3);
//                    String telefono = msj.getParametros().get(4);
//                    String fechaNacimiento = msj.getParametros().get(5);
                    medida.guardar(detalle);
                    sendMail(msj.getEmisor(), msj.evento(), "Los datos insertado a la tabla " + msj.getTable() + " se ha guardo con exito");
                } else {
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj));
                }
                break;
                }
            case Help.MEDIDA + "_" + Help.DEL:{
                if(msj.getParametros().size() == Help.LENPARAM1){ 
                    int id = Integer.parseInt(msj.getParametros().get(0));
                    medida.eliminar(id);
                    sendMail(msj.getEmisor(), msj.evento(), "El dato de la tabla " + msj.getTable() + " se elimino con exito");
                }else{
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj)); 
                }
                break;
            }
            case Help.MEDIDA + "_" + Help.MOD: {
                if (msj.getParametros().size() == Help.LENPARAM2) {
                    int id = Integer.parseInt(msj.getParametros().get(0)); // Obtener el id
                    String detalle = msj.getParametros().get(1);
//                    String descripcion = msj.getParametros().get(2);
                    medida.modificar(id, detalle);                  
                    sendMail(msj.getEmisor(), msj.evento(), "La " + msj.getTable() + " ha sido modificado con exito");
                } else {
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj));
                }
                break;
            }  
            //MEDIDA END
                        
            //PRECIO             
            case Help.PRECIO + "_" + Help.LIS: {
                System.out.println("INGRESAMOS AL METODO");
                List<String[]> lista = precio.listar();
                list(Help.precioHeader, lista, msj);
                break;
            }
            
            case Help.PRECIO + "_" + Help.ADD: {
                if (msj.getParametros().size() == Help.LENPARAM2) {
//                   int id_categoria = Integer.parseInt(msj.getParametros().get(0));
                    double precios = Double.parseDouble(msj.getParametros().get(0));
                    String detalle = msj.getParametros().get(1);
//                    String descripcion = msj.getParametros().get(1);
//                    String email = msj.getParametros().get(3);
//                    String telefono = msj.getParametros().get(4);
//                    String fechaNacimiento = msj.getParametros().get(5);
                    precio.guardar(precios, detalle);
                    sendMail(msj.getEmisor(), msj.evento(), "Los datos insertado a la tabla " + msj.getTable() + " se ha guardo con exito");
                } else {
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj));
                }
                break;
                }
            
            case Help.PRECIO + "_" + Help.MOD: {
                if (msj.getParametros().size() == Help.LENPARAM3) {
                    int id = Integer.parseInt(msj.getParametros().get(0));
                    Double precios = Double.parseDouble(msj.getParametros().get(1));
                    String detalle = msj.getParametros().get(2);
//                    String descripcion = msj.getParametros().get(2);
                    precio.modificar(id,precios, detalle);                  
                    sendMail(msj.getEmisor(), msj.evento(), "La " + msj.getTable() + " ha sido modificado con exito");
                } else {
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj));
                }
                break;
            }
            
            case Help.PRECIO + "_" + Help.DEL:{
                if(msj.getParametros().size() == Help.LENPARAM1){ 
                    int id = Integer.parseInt(msj.getParametros().get(0));
                    precio.eliminar(id);
                    sendMail(msj.getEmisor(), msj.evento(), "El dato de la tabla " + msj.getTable() + " se elimino con exito");
                }else{
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj)); 
                }
                break;
            }
            //PRECIO END
            
            //PRODUCTO             
            case Help.PRODUCTO + "_" + Help.LIS: {
                System.out.println("INGRESAMOS AL METODO");
                List<String[]> lista = producto.listar();
                list(Help.productoHeader, lista, msj);
                break;
            }
            
            case Help.PRODUCTO + "_" + Help.ADD: {
                if (msj.getParametros().size() == Help.LENPARAM6) {
                    String nombre = msj.getParametros().get(0);
                    int codigo = Integer.parseInt(msj.getParametros().get(1));
                    String descripcion = msj.getParametros().get(2);
                    double precios = Double.parseDouble(msj.getParametros().get(3));
                    int categoria_id = Integer.parseInt(msj.getParametros().get(4));
                    int medida_id = Integer.parseInt(msj.getParametros().get(5));
                    producto.guardar(nombre, codigo,descripcion,precios,categoria_id,medida_id);
                    sendMail(msj.getEmisor(), msj.evento(), "Los datos insertado a la tabla " + msj.getTable() + " se ha guardo con exito");
                } else {
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj));
                }
                break;
                }
            
            case Help.PRODUCTO + "_" + Help.MOD: {
                if (msj.getParametros().size() == Help.LENPARAM7) {
                    int id = Integer.parseInt(msj.getParametros().get(0));
                    String nombre = msj.getParametros().get(1);
                    int codigo = Integer.parseInt(msj.getParametros().get(2));
                    String descripcion = msj.getParametros().get(3);
                    Double precios = Double.parseDouble(msj.getParametros().get(4));
                    int categoria_id = Integer.parseInt(msj.getParametros().get(5));
                    int medida_id = Integer.parseInt(msj.getParametros().get(6));
                    producto.modificar(id,nombre,codigo,descripcion,precios, categoria_id,medida_id);                  
                    sendMail(msj.getEmisor(), msj.evento(), "La " + msj.getTable() + " ha sido modificado con exito");
                } else {
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj));
                }
                break;
            }
            
            case Help.PRODUCTO + "_" + Help.DEL:{
                if(msj.getParametros().size() == Help.LENPARAM1){ 
                    int id = Integer.parseInt(msj.getParametros().get(0));
                    producto.eliminar(id);
                    sendMail(msj.getEmisor(), msj.evento(), "El dato de la tabla " + msj.getTable() + " se elimino con exito");
                }else{
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj)); 
                }
                break;
            }
            
            //PRODUCTO END
            
            //ALMACEN             
            case Help.ALMACEN + "_" + Help.LIS: {
                System.out.println("INGRESAMOS AL METODO");
                List<String[]> lista = almacen.listar();
                list(Help.almacenHeader, lista, msj);
                break;
            }
            
            case Help.ALMACEN + "_" + Help.ADD: {
                if (msj.getParametros().size() == Help.LENPARAM2) {
//                    String nombre = msj.getParametros().get(0);
                    int codigo = Integer.parseInt(msj.getParametros().get(0));
                    String direccion = msj.getParametros().get(1);
                    almacen.guardar(codigo,direccion);
                    sendMail(msj.getEmisor(), msj.evento(), "Los datos insertado a la tabla " + msj.getTable() + " se ha guardo con exito");
                } else {
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj));
                }
                break;
                }
            
            case Help.ALMACEN + "_" + Help.MOD: {
                if (msj.getParametros().size() == Help.LENPARAM3) {
                    int id = Integer.parseInt(msj.getParametros().get(0));
                    int codigo = Integer.parseInt(msj.getParametros().get(1));
                    String direccion = msj.getParametros().get(2);
                    
                    almacen.modificar(id,codigo,direccion);                  
                    sendMail(msj.getEmisor(), msj.evento(), "La " + msj.getTable() + " ha sido modificado con exito");
                } else {
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj));
                }
                break;
            }
            
            case Help.ALMACEN + "_" + Help.DEL:{
                if(msj.getParametros().size() == Help.LENPARAM1){ 
                    int id = Integer.parseInt(msj.getParametros().get(0));
                    almacen.eliminar(id);
                    sendMail(msj.getEmisor(), msj.evento(), "El dato de la tabla " + msj.getTable() + " se elimino con exito");
                }else{
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj)); 
                }
                break;
            }           
            //ALMACEN END
            //INVENTARIO
            case Help.INVENTARIO + "_" + Help.ADD: {
                if (msj.getParametros().size() == Help.LENPARAM4) {
//                    String nombre = msj.getParametros().get(0);
                    int producto_id = Integer.parseInt(msj.getParametros().get(0));
                    int almacen_id = Integer.parseInt(msj.getParametros().get(1));
                    int stock = Integer.parseInt(msj.getParametros().get(2));
                    String fechaString = msj.getParametros().get(3);
        
        // Cambiar el nombre de la variable de fecha
                    java.sql.Date sqlFecha = java.sql.Date.valueOf(fechaString);
//                    String direccion = msj.getParametros().get(1);
                    inventario.guardar(producto_id,almacen_id,stock,sqlFecha);
                    sendMail(msj.getEmisor(), msj.evento(), "Los datos insertado a la tabla " + msj.getTable() + " se ha guardo con exito");
                } else {
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj));
                }
                break;
                }
            case Help.INVENTARIO + "_" + Help.LIS: {
                System.out.println("INGRESAMOS AL METODO");
                List<String[]> lista = inventario.listar();
                list(Help.inventarioHeader, lista, msj);
                break;
            }
             case Help.INVENTARIO + "_" + Help.MOD: {
                if (msj.getParametros().size() == Help.LENPARAM4) {
                    int producto_id = Integer.parseInt(msj.getParametros().get(0));
                    int almacen_id = Integer.parseInt(msj.getParametros().get(1));
                    int stock = Integer.parseInt(msj.getParametros().get(2));
                    String fechaString = msj.getParametros().get(3);
        
        // Cambiar el nombre de la variable de fecha
                    java.sql.Date sqlFecha = java.sql.Date.valueOf(fechaString);  
                    inventario.modificar(producto_id,almacen_id,stock,sqlFecha);
                    sendMail(msj.getEmisor(), msj.evento(), "La " + msj.getTable() + " ha sido modificado con exito");
                } else {
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj));
                }
                break;
            }
            
            case Help.INVENTARIO + "_" + Help.DEL:{
                if(msj.getParametros().size() == Help.LENPARAM2){ 
                    int producto_id = Integer.parseInt(msj.getParametros().get(0));
                    int almacen_id = Integer.parseInt(msj.getParametros().get(1));
                    inventario.eliminar(producto_id,almacen_id);
                    sendMail(msj.getEmisor(), msj.evento(), "El dato de la tabla " + msj.getTable() + " se elimino con exito");
                }else{
                    sendMail(msj.getEmisor(), "Parametros incorrectos", erorrLengthParametros(msj)); 
                }
                break;
            }           
            //INVENTARIO END
            
            default:
                sendMail(msj.getEmisor(), "TABLA O ACCION NO ENCONTRADA:", tablaOActionNotFount(msj));
            break;
       }         
    }

    private String errorConexion(String exception,Mensaje msj){
        String helpError = Help.errorMensaje("Error de conexión", "Error al conectarse al servidor, intente nuevamente.",
                exception + " No se pudo ejecutar el comando: "+ msj.toString());
        return helpError;
    }

    private String erorrLengthParametros(Mensaje msj) {
        String helpError = Help.errorMensaje("Error Parametros", "Cantidad de parametros incorrectos.", msj.toString());
        return helpError;
    }

    private String errorConvertirParametros(Mensaje msj) {
        String helpError = Help.errorMensaje("Error convercion", "Error al convertir elementos del parametro", msj.toString());
        return helpError;
        //return "ERROR AL CONVERTIR PARAMETROS: \n" + msj.toString();
    }

    private String comandoIncorrecto(String token) {
        String helpError = Help.errorMensaje("Error de comandos", "No se pudo decifrar el comando", " comando: " + token);
        //return "No se pudo decifrar el comando: \n" + token + "\n PARA MAS AYUDA ENVIE UN CORREO CON LA PALABRA \t HELP \t";
        return helpError;
    }

    private String tablaOActionNotFount(Mensaje msj) {
        String helpError = Help.errorMensaje("Error TABLA o ACCCION", "Tabla O Accion no encontrada", msj.toString());
        return helpError;
        //return "!!!!TABLA O ACCION NO ENCONTRADA!!! \n Error en el comando: \n" + msj.toString() +"\n PARA MAS AYUDA ENVIE UN CORREO CON LA PALABRA \t HELP\t!!!!";
    }

    private void list(String[] header, List<String[]> lista, Mensaje msj) throws IOException {
        System.out.println("el mensaje es: "+msj);
        String body = Help.listMensaje(msj.tableAction(), header, lista);
        
        sendMail(msj.getEmisor(), msj.evento(), body);
    }

    private void ver(String[] header, String[] cu, Mensaje msj) throws IOException {

        String body = Help.ver(msj.tableAction(), header, cu);
        sendMail(msj.getEmisor(), msj.evento(), body);
    }

    private void sendMail(String rcpt, String titulo, String mensaje) throws IOException {
        System.out.println("rcpt: "+rcpt+"titulo: "+titulo + "mensaje: "+mensaje);    
        Smtp smtp = new Smtp(ConstGlobal.SERVIDOR, ConstGlobal.PORT_SMPT);
                    smtp.sendMail(ConstGlobal.EMAIL, "<"+rcpt+">", titulo, mensaje);
                    
    }
    
}
