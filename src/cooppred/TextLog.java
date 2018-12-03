
package cooppred;

/**
 *
 * @author Kenny
 */
public class TextLog {
    
    public static String texto="";
    
    public static void add(String textoadd){
        texto = texto + "\r\n" + textoadd;
    }
    
    public static String get(){
        return texto;
    }
    
    public static void delete(){
        texto = "";
    }
    
}
