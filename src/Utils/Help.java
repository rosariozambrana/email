
package Utils;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author angela
 */
public class Help {
    public static final String ADD = "ADD";
    public static final String MOD = "MOD";
    public static final String DEL = "DEL";
    public static final String ACT = "ACT";
    public static final String DES = "DES";
    public static final String LIS = "LIS";
    public static final String VER = "VER";
    public static final String CAN = "CAN";
    
    // List de todas las tablas
    public static final String CATEGORIA = "CATEGORIA"; 
    public static final String MEDIDA = "MEDIDA";
    public static final String PRECIO = "PRECIO";
    public static final String PRODUCTO = "PRODUCTO";
    public static final String ALMACEN = "ALMACEN";
    public static final String INVENTARIO = "INVENTARIO";
    
    //help
    
     public static final String HELP = "HELP";
     
    public static final int LENPARAM1 = 1;
    public static final int LENPARAM2 = 2;
    public static final int LENPARAM3 = 3;
    public static final int LENPARAM4 = 4;
    public static final int LENPARAM5 = 5;
    public static final int LENPARAM6 = 6;
    public static final int LENPARAM7 = 7;
    public static final int LENPARAM8 = 8;
    public static final int LENPARAM9 = 9;
    
    
     
     
     public static final String[] categoriaHeader = {"id_categoria", "nombre", "descripcion"};
     public static final String[] medidaHeader = {"id_medida", "detalle"};
     public static final String[] precioHeader = {"id_precio", "precio","detalle_medida"};
     public static final String[] productoHeader = {"id_producto", "nombre","codigo","descripcion","precio","categoria_id","medida_id"};
     public static final String[] almacenHeader = {"id_almacen", "codigo","direccion"};
     public static final String[] inventarioHeader = {"producto_id", "almacen_id","stock","fecha"};
    
     
     
      public static String ContenidoHelp() {
        return table();
    }

      
      
      
    public static String errorMensaje(String title, String error, String comando) {
        return "Content-Type: text/html; charset=\"UTF-8\" \r\n\r\n"
                + "<h1 >!!! OCURRIO UN ERROR !!! </h1>"
                + "<table style=\" border-collapse: collapse; width: 100%; border: 1px solid black;\"> \r\n\r\n"
                + "<tr> \r\n\r\n"
                + "<th style=\"text-align: center; padding: 8px; background-color: #e919a4; color: white; border: 1px solid black;\"> Titulo </th> \r\n\r\n"
                + "<th style=\"text-align: center; padding: 8px; background-color: #e919a4; color: white; border: 1px solid black;\"> Error </th> \r\n\r\n"
                + "<th style=\"text-align: center; padding: 8px; background-color: #e919a4; color: white; border: 1px solid black;\"> Comando </th> \r\n\r\n"
                + "</tr> \r\n\r\n"
                + "<tr> \r\n\r\n"
                + "<td style=\"text-align: left; padding: 8px; border: 1px solid black;\">" + title + "</td> \r\n\r\n"
                + "<td style=\"text-align: left; padding: 8px; border: 1px solid black;\"> " + error + " </td> \r\n\r\n"
                + "<td style=\"text-align: left; padding: 8px; border: 1px solid black;\"> " + comando + ";</td> \r\n\r\n"
                + "</tr> \r\n\r\n"
                + "<tr> \r\n\r\n"
                + "</table>"
                + "<h3 style=\"color:red\"> PARA MAS AYUDA ENVIE UN CORREO CON LA PALABRA <b> HELP </b> </h3>";
    }
    
    
    

    public static String listMensaje(String title, String[] header, List<String[]> listaObject) {
        String response = "Content-Type: text/html; charset=\"UTF-8\" \r\n\r\n"
                + "<h1>" + title.toUpperCase() + " - HELP</h1>"
                + "<table style=\" border-collapse: collapse; width: 100%; border: 1px solid black;\"> \r\n\r\n";
        response += trStart;
        for (String head : header) {
            response += thHeader(head);
        }
        response += trEnd;

        for (String[] cadenas : listaObject) {
            response += trStart;
            for (String cad : cadenas) {
                response += td(cad);
            }
            response += trEnd;
        }
        response += footerTable();
        return response;
    }

    public static String ver(String title,String[] header, String[] cad) {
        String response = "Content-Type: text/html; charset=\"UTF-8\" \r\n\r\n"
                + "<h1>" + title.toUpperCase() + " - HELP</h1>"
                + "<table style=\" border-collapse: collapse; width: 100%; border: 1px solid black;\"> \r\n\r\n";
        response += trStart;
        for (String head : header) {
            response += thHeader(head);
        }
        response += trEnd;
        response += trStart;
        for (String c : cad) {
            response += td(c);
        }
        response += trEnd;
        response += footerTable();
        return response;
    }
    

    private static String thHeader(String title) {
        return "<th style=\"text-align: center; padding: 8px; background-color: #e919a4; color: white; border: 1px solid black;\"> " + title + " </th> \r\n\r\n";
    }

    private static String headerTable(String title) {
        return "Content-Type: text/html; charset=\"UTF-8\" \r\n\r\n"
                + "<h1>" + title.toUpperCase() + " - HELP</h1>"
                + "<table style=\" border-collapse: collapse; width: 100%; border: 1px solid black;\"> \r\n\r\n"
                + "<tr> \r\n\r\n"
                + "<th style=\"text-align: center; padding: 8px; background-color: #e919a4; color: white; border: 1px solid black;\"> Caso de Uso </th> \r\n\r\n"
                + "<th style=\"text-align: center; padding: 8px; background-color: #e919a4; color: white; border: 1px solid black;\"> Metodo </th> \r\n\r\n"
                + "<th style=\"text-align: center; padding: 8px; background-color: #e919a4; color: white; border: 1px solid black;\"> Comando </th> \r\n\r\n"
                + "</tr> \r\n\r\n";
    }

    private static String trStart = "<tr> \r\n\r\n";
    private static String trEnd = "</tr> \r\n\r\n";

    private static String table() {
        String header = headerTable("SISTEMA VIA EMAIL");
        List<CasosUso> listCasosUso = getCasosUso();
        String body = "";
        for (CasosUso casoUso : listCasosUso) {
            String title = casoUso.titulo;
            for (Option option : casoUso.options) {
                body += trStart;
                body += td(title);
                body += td(option.title);
                body += td(option.parametros);
                body += trEnd;
            }

        }
        header += body;
        header += footerTable();
        return header;
    }
    
    
    
    private static List<CasosUso> getCasosUso() {
        List<CasosUso> casos = new LinkedList<>();
        
     //categoria
        CasosUso categoria = new CasosUso("CU1. Gestionar Categoria");
        categoria.addCaso(new Option("Guardar categoria", "CATEGORIA_ADD[nombre,descripcion];"));
        categoria.addCaso(new Option("Modificar categoria", "CATEGORIA_MOD[id,nombre,descripcion];"));
        categoria.addCaso(new Option("Eliminar categoria", "CATEGORIA_DEL[id];"));
        categoria.addCaso(new Option("Listar categoria", "CATEGORIA_LIS[];"));
        categoria.addCaso(new Option("Ver categoria", "CATEGORIA_VER[id];"));   
     

        //Medida   
        CasosUso medida = new CasosUso("CU2. Gestionar Medida");
        medida.addCaso(new Option("Guardar medida", "MEDIDA_ADD[detalle];"));
        medida.addCaso(new Option("Modificar medida", "MEDIDA_MOD[id,detalle];"));
        medida.addCaso(new Option("Eliminar medida", "MEDIDA_DEL[id];"));
        medida.addCaso(new Option("Listar medida", "MEDIDA_LIS[];"));
        medida.addCaso(new Option("Ver medida", "MEDIDA_VER[id];"));   
     
        
        //Precio   
        CasosUso precio = new CasosUso("CU2. Gestionar Precio");
        precio.addCaso(new Option("Guardar precio", "PRECIO_ADD[precio, detalle_medida];"));
        precio.addCaso(new Option("Modificar precio", "PRECIO_MOD[id,precio, detalle_medida];"));
        precio.addCaso(new Option("Eliminar precio", "PRECIO_DEL[id];"));
        precio.addCaso(new Option("Listar precio", "PRECIO_LIS[];"));
        precio.addCaso(new Option("Ver precio", "PRECIO_VER[id];")); 
        
        
        //Producto 
        CasosUso producto = new CasosUso("CU2. Gestionar Producto");
        producto.addCaso(new Option("Guardar producto", "PRODUCTO_ADD[nombre,codigo,descripcion,precio,categoria_id,medida_id];"));
        producto.addCaso(new Option("Modificar producto", "PRODUCTO_MOD[id,nombre,codigo,descripcion,precio,categoria_id,medida_id];"));
        producto.addCaso(new Option("Eliminar producto", "PRODUCTO_DEL[id];"));
        producto.addCaso(new Option("Listar producto", "PRODUCTO_LIS[];"));
        producto.addCaso(new Option("Ver producto", "PRODUCTO_VER[id];")); 
        
        //Almacen 
        CasosUso almacen = new CasosUso("CU2. Gestionar Almacen");
        almacen.addCaso(new Option("Guardar almacen", "ALMACEN_ADD[codigo,direccion];"));
        almacen.addCaso(new Option("Modificar almacen", "ALMACEN_MOD[id_almacen,codigo,direccion];"));
        almacen.addCaso(new Option("Eliminar almacen", "ALMACEN_DEL[id];"));
        almacen.addCaso(new Option("Listar almacen", "ALMACEN_LIS[];"));
        almacen.addCaso(new Option("Ver almacen", "ALMACEN_VER[id];"));
        
        
        //Inventario
        CasosUso inventario = new CasosUso("CU2. Gestionar Inventario");
        inventario.addCaso(new Option("Guardar inventario", "INVENTARIO_ADD[producto_id,almacen_id, stock,fecha];"));
        inventario.addCaso(new Option("Modificar inventario", "INVENTARIO_MOD[producto_id,almacen_id, stock,fecha];"));
        inventario.addCaso(new Option("Eliminar inventario", "INVENTARIO_DEL[producto_id,almacen_id];"));
        inventario.addCaso(new Option("Listar inventario", "INVENTARIO_LIS[];"));
        inventario.addCaso(new Option("Ver inventario", "INVENTARIO_VER[id];"));
        
         casos.add(categoria);
         casos.add(medida);
         casos.add(precio);
         casos.add(producto);
         casos.add(almacen);
         casos.add(inventario);
        
        return casos;
    }
    
    
    
    
     private static String td(String content) {
        return "<td style=\"text-align: left; padding: 8px; border: 1px solid black;\"> " + content + " </td> \r\n\r\n";
    }

    private static String footerTable() {
        return "<tr> \r\n\r\n"
                + "</table>";
    }
}
