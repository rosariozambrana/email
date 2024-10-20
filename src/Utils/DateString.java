
package Utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;



/**
 *
 * @author angela
 */
public class DateString {
    public static Calendar StringToDate(String date){
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
        try{
            Calendar dt = Calendar.getInstance();
            dt.setTime(format.parse(date));
            return dt;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String DateToString(Calendar date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            String dt = format.format(date);
            return dt;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static Calendar StringToDatetime(String date){
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        try{
            Calendar dt = Calendar.getInstance();
            dt.setTime(format.parse(date));
            return dt;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String DatetimeToString(Calendar date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            String dt = format.format(date);
            return dt;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
   
    public static Date StringToDateSQL(String date)throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
        Date dt = new Date(format.parse(date).getTime());
        return dt;
    }
    
    public static String DateSQLToString(Date date)throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dt = format.format(date);
        return dt;
    }
    
    public static Timestamp StringToDatetimeSQL(String date)throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Timestamp ts = new Timestamp(format.parse(date).getTime());
        return ts;
    }
    
    public static String DatetimeSQLToString(Timestamp date)throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String dt = format.format(date);
        return dt;
    }

    public static Date DateSQLToString(String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
