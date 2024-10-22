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
    public static final String ADMINISTRATIVO = "ADMINISTRATIVO";
    public static final String ALMACEN = "ALMACEN";    
    public static final String CATEGORIA = "CATEGORIA";    
    public static final String CLIENTE = "CLIENTE";    
    public static final String COMPRA = "COMPRA";    
    public static final String DETALLECOMPRA = "DETALLECOMPRA";    
    public static final String ENVIO = "ENVIO";
    public static final String INVENTARIO = "INVENTARIO";
    public static final String MEDIDA = "MEDIDA";    
    public static final String METODOPAGO = "METODOPAGO";
    public static final String PAGO = "PAGO";
    public static final String PERSONA = "PERSONA";
    public static final String PRECIO = "PRECIO";    
    public static final String PRODUCTO = "PRODUCTO";
    public static final String PROVEEDOR = "PROVEEDOR";
    public static final String SEGUIMIENTO = "SEGUIMIENTO";
    public static final String TRANSACCIONBANCARIA = "TRANSACCIONBANCARIA";

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

    public static final String[] administrativoHeader = {"id_persona", "id_administrativo", "cargo", "fecha_ingreso"};
    public static final String[] almacenHeader = {"id_almacen", "codigo", "direccion"};
    public static final String[] categoriaHeader = {"id_categoria", "nombre", "descripcion"};
    public static final String[] clienteHeader = {"id_persona", "id_cliente", "tipo_cliente"};
    public static final String[] compraHeader = {"id_compra", "preciototal", "fechacompra","estado","cliente_id","administrativo_id"};
    public static final String[] detallecompraHeader = {"id_detallecompra", "cantidad", "precio_unitario","subtotal","compra_id","producto_id"};
    public static final String[] envioHeader = {"id_envio", "fecha_envio", "direccion_envio","ciudad_envio","pais_destino","estado_envio","fecha_entrega", "metodo_envio","transporte","compra_id"};
    public static final String[] inventarioHeader = {"producto_id", "almacen_id", "stock", "fecha"};
    public static final String[] medidaHeader = {"id_medida", "detalle"};
    public static final String[] metodopagoHeader = {"id_medida", "nombre"};
    public static final String[] pagoHeader = {"id_pago", "monto", "moneda","fecha_pago","estado_pago","compra_id","metodopago_id","transaccionbancaria_id"};
    public static final String[] personaHeader = {"id_persona", "nombre", "apellido", "direccion", "telefono", "correo", "ci"};
    public static final String[] precioHeader = {"id_precio", "precio", "detalle_medida"};
    public static final String[] productoHeader = {"id_producto", "nombre", "codigo", "descripcion", "precio", "categoria_id", "medida_id"};
    public static final String[] proveedorHeader = {"id_persona", "id+proveedor", "tipo_proveedor", "descripcion"};
    public static final String[] seguimientoHeader = {"id_seguimiento", "fecha_evento", "descripcion", "ubicacion_actual", "estado_actual","envio_id"};
    public static final String[] transaccionbancariaHeader = {"id_transbanc", "num_transaccion", "banco_origen", "banco_destino", "fecha_transaccion"};
    
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

    public static String ver(String title, String[] header, String[] cad) {
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
        
        //Almacen 
        CasosUso almacen = new CasosUso("CU5. Gestionar Almacen");
        almacen.addCaso(new Option("Guardar almacen", "ALMACEN_ADD[codigo,direccion];"));
        almacen.addCaso(new Option("Modificar almacen", "ALMACEN_MOD[id_almacen,codigo,direccion];"));
        almacen.addCaso(new Option("Eliminar almacen", "ALMACEN_DEL[id];"));
        almacen.addCaso(new Option("Listar almacen", "ALMACEN_LIS[];"));
        almacen.addCaso(new Option("Ver almacen", "ALMACEN_VER[id];"));
        
        //ADMINISTRATIVO 
        CasosUso administrativo = new CasosUso("CU5. GESTIONAR ADMINISTRATIVO");
        administrativo.addCaso(new Option("GUARDAR " + ADMINISTRATIVO, ADMINISTRATIVO + "_ADD[id_administrativo, cargo];"));
        administrativo.addCaso(new Option("MODIFICAR " + ADMINISTRATIVO, ADMINISTRATIVO + "_MOD[id_persona, id_administrativo, cargo, fecha_ingreso];"));
        administrativo.addCaso(new Option("ELIMINAR " + ADMINISTRATIVO, ADMINISTRATIVO + "_DEL[id_persona];"));
        administrativo.addCaso(new Option("LISTAR" + ADMINISTRATIVO, ADMINISTRATIVO + "_LIS[];"));
        administrativo.addCaso(new Option("VER " + ADMINISTRATIVO, ADMINISTRATIVO + "_VER[id_persona];"));
        
        //CATEGORIA
        CasosUso categoria = new CasosUso("CU1. Gestionar Categoria");
        categoria.addCaso(new Option("Guardar categoria", "CATEGORIA_ADD[nombre,descripcion];"));
        categoria.addCaso(new Option("Modificar categoria", "CATEGORIA_MOD[id,nombre,descripcion];"));
        categoria.addCaso(new Option("Eliminar categoria", "CATEGORIA_DEL[id];"));
        categoria.addCaso(new Option("Listar categoria", "CATEGORIA_LIS[];"));
        categoria.addCaso(new Option("Ver categoria", "CATEGORIA_VER[id];"));
        
        //CLIENTE
        CasosUso cliente = new CasosUso("CU8. Gestionar Cliente");
        cliente.addCaso(new Option("GUARDAR " + CLIENTE, CLIENTE + "_ADD[id_persona, id_cliente, tipo_cliente];"));
        cliente.addCaso(new Option("MODIFICAR " + CLIENTE, CLIENTE + "_MOD[id_persona, id_cliente, tipo_cliente];"));
        cliente.addCaso(new Option("ELIMINAR " + CLIENTE, CLIENTE + "_DEL[id_persona, id_cliente];"));
        cliente.addCaso(new Option("LISTAR" + CLIENTE, CLIENTE + "_LIS[];"));
        cliente.addCaso(new Option("VER " + CLIENTE, CLIENTE + "_VER[id_persona, id_cliente];"));
        
        //COMPRA
        CasosUso compra = new CasosUso("CU8. GESTIONAR COMPRA");
        compra.addCaso(new Option("GUARDAR " + COMPRA, COMPRA + "_ADD[preciototal, estado, cliente_id, administrativo_id];"));
        compra.addCaso(new Option("MODIFICAR " + COMPRA, COMPRA + "_MOD[id_compra, preciototal, estado];"));
        compra.addCaso(new Option("ELIMINAR " + COMPRA, COMPRA + "_DEL[id_compra];"));
        compra.addCaso(new Option("LISTAR" + COMPRA, COMPRA + "_LIS[];"));
        compra.addCaso(new Option("VER " + COMPRA, COMPRA + "_VER[id_compra];"));
        
        //DETALLECOMPRA
        CasosUso detallecompra = new CasosUso("CU8. GESTIONAR DETALLE COMPRA");
        detallecompra.addCaso(new Option("GUARDAR " + DETALLECOMPRA, DETALLECOMPRA + "_ADD[cantidad, precio_unitario, subtotal, compra_id, producto_id];"));
        detallecompra.addCaso(new Option("MODIFICAR " + DETALLECOMPRA, DETALLECOMPRA + "_MOD[id_detallecompra, cantidad, precio_unitario, subtotal];"));
        detallecompra.addCaso(new Option("ELIMINAR " + DETALLECOMPRA, DETALLECOMPRA + "_DEL[id_detallecompra];"));
        detallecompra.addCaso(new Option("LISTAR" + DETALLECOMPRA, DETALLECOMPRA + "_LIS[];"));
        detallecompra.addCaso(new Option("VER " + DETALLECOMPRA, DETALLECOMPRA + "_VER[id_detallecompra];"));

        //ENVIO
        CasosUso envio = new CasosUso("CU8. GESTIONAR "+ENVIO);
        envio.addCaso(new Option("GUARDAR " + ENVIO, ENVIO + "_ADD[direccion_envio, ciudad_envio, pais_destino, estado_envio, fecha_entrega, metodo_envio, transporte, compra_id];"));
        envio.addCaso(new Option("MODIFICAR " + ENVIO, ENVIO + "_MOD[id_envio, direccion_envio, ciudad_envio, pais_destino, estado_envio, fecha_entrega, metodo_envio, transporte];"));
        envio.addCaso(new Option("ELIMINAR " + ENVIO, ENVIO + "_DEL[id_envio];"));
        envio.addCaso(new Option("LISTAR" + ENVIO, ENVIO + "_LIS[];"));
        envio.addCaso(new Option("VER " + ENVIO, ENVIO + "_VER[id_envio];"));
        
        //Inventario
        CasosUso inventario = new CasosUso("CU6. Gestionar Inventario");
        inventario.addCaso(new Option("Guardar inventario", "INVENTARIO_ADD[producto_id,almacen_id, stock,fecha];"));
        inventario.addCaso(new Option("Modificar inventario", "INVENTARIO_MOD[producto_id,almacen_id, stock,fecha];"));
        inventario.addCaso(new Option("Eliminar inventario", "INVENTARIO_DEL[producto_id,almacen_id];"));
        inventario.addCaso(new Option("Listar inventario", "INVENTARIO_LIS[];"));
        inventario.addCaso(new Option("Ver inventario", "INVENTARIO_VER[id];"));

        //MEDIDA   
        CasosUso medida = new CasosUso("CU2. Gestionar Medida");
        medida.addCaso(new Option("Guardar medida", "MEDIDA_ADD[detalle];"));
        medida.addCaso(new Option("Modificar medida", "MEDIDA_MOD[id,detalle];"));
        medida.addCaso(new Option("Eliminar medida", "MEDIDA_DEL[id];"));
        medida.addCaso(new Option("Listar medida", "MEDIDA_LIS[];"));
        medida.addCaso(new Option("Ver medida", "MEDIDA_VER[id];"));
        
        //METODOPAGO
        CasosUso metodopago = new CasosUso("CU8. GESTIONAR "+METODOPAGO);
        metodopago.addCaso(new Option("GUARDAR " + METODOPAGO, METODOPAGO + "_ADD[nombre];"));
        metodopago.addCaso(new Option("MODIFICAR " + METODOPAGO, METODOPAGO + "_MOD[id_metodopago, nombre];"));
        metodopago.addCaso(new Option("ELIMINAR " + METODOPAGO, METODOPAGO + "_DEL[id_metodopago];"));
        metodopago.addCaso(new Option("LISTAR" + METODOPAGO, METODOPAGO + "_LIS[];"));
        metodopago.addCaso(new Option("VER " + METODOPAGO, METODOPAGO + "_VER[id_metodopago];"));
        
        //PAGO
        CasosUso pago = new CasosUso("CU8. GESTIONAR "+PAGO);
        pago.addCaso(new Option("GUARDAR " + PAGO, PAGO + "_ADD[monto, moneda, estado_pago, compra_id, metodopago_id, transaccionbancaria_id];"));
        pago.addCaso(new Option("MODIFICAR " + PAGO, PAGO + "_MOD[id_pago, monto, moneda, estado_pago];"));
        pago.addCaso(new Option("ELIMINAR " + PAGO, PAGO + "_DEL[id_pago];"));
        pago.addCaso(new Option("LISTAR" + PAGO, PAGO + "_LIS[];"));
        pago.addCaso(new Option("VER " + PAGO, PAGO + "_VER[id_pago];"));
        
        //PERSONA
        CasosUso persona = new CasosUso("CU7. Gestionar Persona");
        persona.addCaso(new Option("GUARDAR " + PERSONA, PERSONA + "_ADD[nombre, apellido, direccion, telefono, correo, ci];"));
        persona.addCaso(new Option("MODIFICAR " + PERSONA, PERSONA + "_MOD[persona_id, nombre, apellido, direccion, telefono, correo, ci];"));
        persona.addCaso(new Option("ELIMINAR " + PERSONA, PERSONA + "_DEL[persona_id];"));
        persona.addCaso(new Option("LISTAR" + PERSONA, PERSONA + "_LIS[];"));
        persona.addCaso(new Option("VER " + PERSONA, PERSONA + "_VER[persona_id];"));

        //Precio   
        CasosUso precio = new CasosUso("CU3. Gestionar Precio");
        precio.addCaso(new Option("Guardar precio", "PRECIO_ADD[precio, detalle_medida];"));
        precio.addCaso(new Option("Modificar precio", "PRECIO_MOD[id,precio, detalle_medida];"));
        precio.addCaso(new Option("Eliminar precio", "PRECIO_DEL[id];"));
        precio.addCaso(new Option("Listar precio", "PRECIO_LIS[];"));
        precio.addCaso(new Option("Ver precio", "PRECIO_VER[id];"));

        //Producto 
        CasosUso producto = new CasosUso("CU4. Gestionar Producto");
        producto.addCaso(new Option("Guardar producto", "PRODUCTO_ADD[nombre,codigo,descripcion,precio,categoria_id,medida_id];"));
        producto.addCaso(new Option("Modificar producto", "PRODUCTO_MOD[id,nombre,codigo,descripcion,precio,categoria_id,medida_id];"));
        producto.addCaso(new Option("Eliminar producto", "PRODUCTO_DEL[id];"));
        producto.addCaso(new Option("Listar producto", "PRODUCTO_LIS[];"));
        producto.addCaso(new Option("Ver producto", "PRODUCTO_VER[id];"));       

        //PROVEEDOR
        CasosUso proveedor = new CasosUso("CU7. GESTIONAR "+PROVEEDOR);
        proveedor.addCaso(new Option("GUARDAR " + PROVEEDOR, PROVEEDOR + "_ADD[id_persona, id_proveedor, tipo_proveedor, descripcion];"));
        proveedor.addCaso(new Option("MODIFICAR " + PROVEEDOR, PROVEEDOR + "_MOD[id_persona, id_proveedor, tipo_proveedor, descripcion];"));
        proveedor.addCaso(new Option("ELIMINAR " + PROVEEDOR, PROVEEDOR + "_DEL[id_persona, id_proveedor];"));
        proveedor.addCaso(new Option("LISTAR" + PROVEEDOR, PROVEEDOR + "_LIS[];"));
        proveedor.addCaso(new Option("VER " + PROVEEDOR, PROVEEDOR + "_VER[id_persona, id_proveedor];"));

        //SEGUIMIENTO
        CasosUso seguimiento = new CasosUso("CU7. GESTIONAR "+SEGUIMIENTO);
        seguimiento.addCaso(new Option("GUARDAR " + SEGUIMIENTO, SEGUIMIENTO + "_ADD[descripcion, ubicacion_actual, estado_actual, envio_id];"));
        seguimiento.addCaso(new Option("MODIFICAR " + SEGUIMIENTO, SEGUIMIENTO + "_MOD[id_seguimiento, descripcion, ubicacion_actual, estado_actual];"));
        seguimiento.addCaso(new Option("ELIMINAR " + SEGUIMIENTO, SEGUIMIENTO + "_DEL[id_seguimiento];"));
        seguimiento.addCaso(new Option("LISTAR" + SEGUIMIENTO, SEGUIMIENTO + "_LIS[];"));
        seguimiento.addCaso(new Option("VER " + SEGUIMIENTO, SEGUIMIENTO + "_VER[id_seguimiento];"));
        
        //TRANSACCIONBANCARIA
        CasosUso transaccionbancaria = new CasosUso("CU7. GESTIONAR "+TRANSACCIONBANCARIA);
        transaccionbancaria.addCaso(new Option("GUARDAR " + TRANSACCIONBANCARIA, TRANSACCIONBANCARIA + "_ADD[num_transaccion, banco_origen, banco_destino, fecha_transaccion];"));
        transaccionbancaria.addCaso(new Option("MODIFICAR " + TRANSACCIONBANCARIA, TRANSACCIONBANCARIA + "_MOD[id_transbanc, num_transaccion, banco_origen, banco_destino, fecha_transaccion];"));
        transaccionbancaria.addCaso(new Option("ELIMINAR " + TRANSACCIONBANCARIA, TRANSACCIONBANCARIA + "_DEL[id_transbanc];"));
        transaccionbancaria.addCaso(new Option("LISTAR" + TRANSACCIONBANCARIA, TRANSACCIONBANCARIA + "_LIS[];"));
        transaccionbancaria.addCaso(new Option("VER " + TRANSACCIONBANCARIA, TRANSACCIONBANCARIA + "_VER[id_transbanc];"));
        
        casos.add(categoria);
        casos.add(medida);
        casos.add(precio);
        casos.add(producto);
        casos.add(almacen);
        casos.add(inventario);
        casos.add(persona);
        casos.add(cliente);

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
