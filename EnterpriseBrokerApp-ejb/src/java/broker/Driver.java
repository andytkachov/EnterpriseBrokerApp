
package broker;
public class Driver {
    private static Boolean isDriverLoad = false;
    private static void init(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            isDriverLoad = true;
        } catch (ClassNotFoundException ex) {
        }
    } 
    
    public static void LoadDriver(){
        if(isDriverLoad == false)
            init();
    }
}
