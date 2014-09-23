/*
 * TestIisopApp.java
 */

package testiisop;

import com.stin.test.Licencia;
import javax.swing.JOptionPane;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class TestIisopApp extends SingleFrameApplication {
    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        System.setProperty("java.net.useSystemProxies","false");/*LINEA PARA EVITAR EL PROXI Y PERMITIR CONEXIONES REMOTAS*/
                
        try {
            if(!(Licencia.getLic())){
               JiLic l=new JiLic();
               l.setVisible(true);

            }else{ 
            SplashF splashF=new SplashF();
            //splashF.setExtendedState(splashF.MAXIMIZED_BOTH);
            splashF.setVisible(true);
            }
            
        } catch (Exception e) {
            System.out.println("ENTRO AL ERROR");
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), Menu.nombreSistema, JOptionPane.ERROR_MESSAGE);            
        } 
        
    }
   

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of TestIisopApp
     */
    public static TestIisopApp getApplication() {
        return Application.getInstance(TestIisopApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(TestIisopApp.class, args);
    }
}
