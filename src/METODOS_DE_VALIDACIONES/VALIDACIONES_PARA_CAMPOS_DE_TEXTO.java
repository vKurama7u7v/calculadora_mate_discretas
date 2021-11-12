package METODOS_DE_VALIDACIONES;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author vkurama7w7v
 */
public class VALIDACIONES_PARA_CAMPOS_DE_TEXTO {
    
    // METODO PARA ESCRIBIR SOLO CARÁCTERES DE TIPO NÚMERO
    public void VALIDAR_SOLO_NUMEROS(JTextField CAMPO){
        CAMPO.addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent e){
            char V = e.getKeyChar();
            if (!Character.isDigit(V)){ 
                e.consume();
                System.out.println("'"+ V + "' NO ES UN CARÁCTER DE TIPO NÚMERO\n");
            }
        }
        });
    }
    
        // METODO PARA ESCRIBIR SOLO LETRAS DE (A - Z) & (0 - 9)
    public void VALIDAR_SOLO_NUMEROS_CON_PUNTO(JTextField CAMPO){
        CAMPO.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                char V = e.getKeyChar();
                if (V != 48 && V != 49 && V != 50 && V != 51 && V != 52
                        && V != 53 && V != 54 && V != 55 && V != 56 && V != 57
                        && V != 46){
                    // CODIGO ASCII --> A-Z & 0-9;
                    e.consume();
                    System.out.println("'"+ V + "' NO ES UN CARÁCTER DE TIPO LETRA\n");
                }
        }
        });
    }

    // METODO PARA ESCRIBIR SOLO LETRAS DE (A - Z)
    public void VALIDAR_SOLO_LETRAS(JTextField CAMPO){
        CAMPO.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                char V = e.getKeyChar();
                // char Ñ = '\u00d1', ñ = '\u00f1';; // Ñ EN UNICODE
                if (V != 65 && V != 66 && V != 67 && V != 68 && V != 69 && V != 70
                        && V != 71 && V != 72 && V != 73 && V != 74 && V != 75 
                        && V != 76 && V != 77 && V != 78 && V != 79 && V != 80
                        && V != 81 && V != 82 && V != 83 && V != 84 && V != 85
                        && V != 86 && V != 87 && V != 88 && V != 89 && V != 90 
                        && V != 97 && V != 98 && V != 99 && V != 100
                        && V != 101 && V != 102 && V != 103 && V != 104 && V != 105
                        && V != 106 && V != 107 && V != 108 && V != 109 && V != 110
                        && V != 111 && V != 112 && V != 113 && V != 114 && V != 115
                        && V != 116 && V != 117 && V != 118 && V != 119 && V != 120
                        && V != 121 && V != 122
                        && V != 32 && V != 8){
                    // CODIGO ASCII --> A-Z;
                    e.consume();
                    System.out.println("'"+ V + "' NO ES UN CARÁCTER DE TIPO LETRA\n");
                }
        }
        });
    }
    
    // METODO PARA ESCRIBIR SOLO LETRAS DE (A - Z) & (0 - 9)
    public void VALIDAR_SOLO_LETRAS_Y_NUMEROS(JTextField CAMPO){
        CAMPO.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                char V = e.getKeyChar();
                // char Ñ = '\u00d1', ñ = '\u00f1'; // Ñ EN UNICODE
                if (V != 65 && V != 66 && V != 67 && V != 68 && V != 69 && V != 70
                        && V != 71 && V != 72 && V != 73 && V != 74 && V != 75 
                        && V != 76 && V != 77 && V != 78 && V != 79 && V != 80
                        && V != 81 && V != 82 && V != 83 && V != 84 && V != 85
                        && V != 86 && V != 87 && V != 88 && V != 89 && V != 90 
                        && V != 97 && V != 98 && V != 99 && V != 100 && V != 101 
                        && V != 102 && V != 103 && V != 104 && V != 105 && V != 106
                        && V != 107 && V != 108 && V != 109 && V != 110 && V != 111
                        && V != 112 && V != 113 && V != 114 && V != 115 && V != 116
                        && V != 117 && V != 118 && V != 119 && V != 120 && V != 121
                        && V != 122 && V != 32 && V != 8
                        && V != 48 && V != 49 && V != 50 && V != 51 && V != 52
                        && V != 53 && V != 54 && V != 55 && V != 56 && V != 57){
                    // CODIGO ASCII --> A-Z & 0-9;
                    e.consume();
                    System.out.println("'"+ V + "' NO ES UN CARÁCTER DE TIPO LETRA\n");
                }
        }
        });
    }

    // METODO PARA LIMITAR EL NÚMERO DE CARÁCTERES EN LOS CAMPOS DE TEXTO
    public void LIMITAR_CARACTERES(JTextField CAMPO, int CANTIDAD){
        CAMPO.addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent e){
            char V = e.getKeyChar();
            int T = CAMPO.getText().length();
            if (T >= CANTIDAD){
                e.consume();
                System.out.println("SOLO SE PERMÍTEN "+ CANTIDAD +" CARÁCTERES EN ESTE CAMPO\n");
            }
        }
        });
    }
    
    
    
    
    
}
