
package connection;

import Utils.ConstSMPT;
import java.io.IOException;
/**
 *
 * @author angela
 */
public class Smtp extends Conexion {
   
    
      public Smtp(String servidor, int port) throws IOException {
        super(servidor, port);
    }
    
    String comando = "";

    public void helo() throws IOException {
        comando = ConstSMPT.HELO + servidor + ConstSMPT.FINLINE;
        System.out.println("C: " + comando);
        salida.writeBytes(comando);
        System.out.println("S: " + entrada.readLine());
    }

    public void mailFrom(String emailEmisor) throws IOException {
        comando = ConstSMPT.MAIL_FROM + emailEmisor + ConstSMPT.FINLINE;
        System.out.println("C: " + comando);
        salida.writeBytes(comando);
        System.out.println("S: " + entrada.readLine());
    }
    
    
    public void rcptTo(String emailReceptor) throws IOException {
        comando = ConstSMPT.RCPT_TO + emailReceptor + ConstSMPT.FINLINE;
        System.out.println("C: " + comando);
        salida.writeBytes(comando);
        System.out.println("S: " + entrada.readLine());
    }

    public void data() throws IOException {
        comando = ConstSMPT.DATA;
        System.out.println("C: " + comando);
        salida.writeBytes(comando);
        System.out.println("S: " + entrada.readLine());
    }
    
    

    public void subject(String title, String message) throws IOException {
        comando = ConstSMPT.SUBJECT + title + ConstSMPT.FINLINE;
        comando += message + ConstSMPT.FINLINE;
        comando += ConstSMPT.FINSUBJECT;
        System.out.println("C: " + comando);
        salida.writeBytes(comando);
        System.out.println("S: " + entrada.readLine());
        System.out.println("");
    }

    public void quit() throws IOException {
        comando = ConstSMPT.QUIT;
        System.out.println("C: " + comando);
        salida.writeBytes(comando);
        System.out.println("S: " + entrada.readLine());
    }
    
    public void sendMail(String emisor,String rcpto,String subject,String mensaje) throws IOException{
        mailFrom(emisor);
        rcptTo(rcpto);
        data();
        subject(subject, mensaje);
        quit();
    }
    
    
    
    
}
