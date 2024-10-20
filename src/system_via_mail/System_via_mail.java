
package system_via_mail;

import Consultas.Consulta;
import Utils.ConstGlobal;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Object.Mensaje;
/**
 *
 * @author angela
 */
public class System_via_mail {

    public static void main(String[] args) {
        // TODO code application logic here

        try {         
            Consulta manage = new Consulta();
            int cantidadMail = manage.getCantidadMails();
            while (true) {                
                int newCantidadEmail = manage.getCantidadMails();
                if(cantidadMail != newCantidadEmail){
                    System.out.println("Se encontro un nuevo Mensaje");
                    cantidadMail = newCantidadEmail;
                    manage.newMensaje();
                }
                System.out.println("No se encontro nuevo mensaje");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(System_via_mail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
