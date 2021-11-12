package METODOS_DE_VALIDACIONES;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author vkurama7w7v
 */
public class CONVERTIR_MAYUSCULAS extends PlainDocument{
    public void insertString(int offset, String str, AttributeSet attr) 
            throws BadLocationException{
        super.insertString(offset, str.toUpperCase(), attr);
    }
}
