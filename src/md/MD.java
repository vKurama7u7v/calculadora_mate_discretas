package md;
import com.sun.awt.AWTUtilities;
/**
 *
 * @author vkurama7u7v
 */
public class MD {
    
    public static void main(String[] args) {
        try {
            UI_MD UMD = new UI_MD();
            AWTUtilities.setWindowOpaque(UMD, false);
            UMD.setVisible(true);
        } catch (Exception e) {
            System.out.println("ERROR AL INICIAR UI_MD: " + e);
        }
    }
    
}
