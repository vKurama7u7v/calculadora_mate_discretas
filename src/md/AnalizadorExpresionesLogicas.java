package md;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import md.UI_MD;
/**
 *
 * @author vkurama7u7v
 */
class Excepciones extends Exception {
    String ErrorSTR;
    public Excepciones (String str){
        ErrorSTR = str;
    }
    public String toString(){
        return ErrorSTR;
    }
}

class AnalizadorExpresiones {
    UI_MD UI = new UI_MD();
    
    // Tipos de Token
    final int NINGUNO = 0;
    final int DELIMITADOR = 1;
    final int VARIABLE = 2;
    final int NUMERO = 3;

    // Tipos de errores de Sintaxis
    final  int SYNTAXIS = 0;
    final  int PARENTESIS = 1;
    final  int SINEXP = 2;
    final  int DIVENTRESERO = 3;

    // Token indican fin de la Expresion
    final String FINEXP = "\0";

    private String exp;
    private int expIndice;
    private String token;
    private int tipoToken;
    String equiv;
    int N_Variables = 0;
    int CONTADOR = 1;
    int POSICION_ARRAY = 0;
    String[] P =    {"true","false","true","false","true","false","true","false","true","false","true","false","true","false","true","false"};
    String[] PQ =   {"true","true","false","false","true","true","false","false","true","true","false","false","true","true","false","false"};
    String[] PQR =  {"true","true","true","true","false","false","false","false","true","true","true","true","false","false","false","false"};
    String[] PQRS = {"true","true","true","true","true","true","true","true","false","false","false","false","false","false","false","false"};

    private String var[] = new String[100];
    private boolean ValorVar[] = new boolean[100];

    int i = 0;
    int j = 0;

    public boolean EVALUAR(String EXPRESION_STR,int NVAR) throws Excepciones{
        boolean resultado;
        
        exp = EXPRESION_STR;
        N_Variables = NVAR;
        expIndice = 0;
        System.out.println("AnalizadorExpresiones N_Variables: " + N_Variables);

        obtieneToken();
        if (token.equals(FINEXP)) {
            obtieneError(SINEXP);   // No hay Expresion escrita
        }

        // Analiza y Evalua la Expresion
        resultado = evaluarExp();
        System.out.println(exp);
        if (!token.equals(FINEXP)) {
            obtieneError(SYNTAXIS); 
        }
        i = 0;
        return resultado;
    }

    // Equivalente ≡
    private boolean evaluarExp() throws Excepciones {
        char op;
        boolean resultado;
        boolean resultadoParcial;
        
        resultado = evaluarExp1();

        while ((op = token.charAt(0)) == '≡') {            
            obtieneToken();
            resultadoParcial = evaluarExp1();

            switch (op) {
                case '≡':
                    System.out.println(resultado + " ≡ " + resultadoParcial);
                    if (resultado == resultadoParcial) {
                        resultado = true;
                        equiv = "∴ Es equivalente";
                    } else {
                        resultado = false;
                        equiv = "∴ No es equivalente";
                    }
                    System.out.println(equiv);
                    break;
            }
        }
        return resultado;
    }

    // Bicondicional
    // p↔q≡(p∧q)∨(﹁p∧﹁q)
    // r = (p & q) | (!p & !q);
    private boolean evaluarExp1() throws Excepciones {
        char op;
        boolean resultado;
        boolean resultadoParcial;
        
        resultado = evaluarExp2();

        while ((op = token.charAt(0)) == '↔') {            
            obtieneToken();
            resultadoParcial = evaluarExp2();

            switch (op) {
                case '↔':
                    System.out.println(resultado + " ↔ " + resultadoParcial);
                    resultado = (resultado & resultadoParcial) | (!resultado & !resultadoParcial);
                    break;
            }
        }
        return resultado;
    }

    // XOR
    private boolean evaluarExp2() throws Excepciones {
        char op;
        boolean resultado;
        boolean resultadoParcial;

        resultado = evaluarExp3();

        while ((op = token.charAt(0)) == '⊻') {            
            obtieneToken();
            resultadoParcial = evaluarExp3();

            switch (op) {
                case '⊻':
                    System.out.println(resultado + " ⊻ " + resultadoParcial);
                    resultado = resultado ^ resultadoParcial;
                    break;
            }
        }
        return resultado;
    }

    // Condicional
    // p→q≡﹁p∨q
    // boolean r = !p | q;
    private boolean evaluarExp3() throws Excepciones {
        char op;
        boolean resultado;
        boolean resultadoParcial;

        resultado = evaluarExp4();

        while ((op = token.charAt(0)) == '→') {            
            obtieneToken();
            resultadoParcial = evaluarExp4();

            switch (op) {
                case '→':
                    System.out.println(resultado + " → " + resultadoParcial);
                    resultado = !resultado | resultadoParcial;
                    break;
            }
        }
        return resultado;
    }

    // OR
    private boolean evaluarExp4() throws Excepciones {
        char op;
        boolean resultado;
        boolean resultadoParcial;

        resultado = evaluarExp5();

        while ((op = token.charAt(0)) == '∨') {            
            obtieneToken();
            resultadoParcial = evaluarExp5();

            switch (op) {
                case '∨':
                    System.out.println(resultado + " ∨ " + resultadoParcial);
                    resultado = resultado | resultadoParcial;
                    break;
            }
        }
        return resultado;
    }

    // AND
    private boolean evaluarExp5() throws Excepciones {
        char op;
        boolean resultado;
        boolean resultadoParcial;

        resultado = evaluarExp6();

        while ((op = token.charAt(0)) == '∧') {            
            obtieneToken();
            resultadoParcial = evaluarExp6();

            switch (op) {
                case '∧':
                    System.out.println(resultado + " ∧ " + resultadoParcial);
                    resultado = resultado & resultadoParcial;
                    break;
            }
        }
        return resultado;
    }

    // Negación
    private boolean evaluarExp6() throws Excepciones{
        boolean resultado;
        String op;
        op = "";

        if ((tipoToken == DELIMITADOR) && token.equals("¬")) {
            op = token;
            obtieneToken();
        }
        resultado = evaluarExp7();
        if (op.equals("¬")) {
            System.out.println("¬" + resultado);
            resultado = !resultado;
        }
        return resultado;
    }

    // Procesar Expresion con Parentesis
    private boolean evaluarExp7() throws Excepciones{
        boolean resultado;

        if (token.equals("(")) {
            obtieneToken();
            resultado = evaluarExp();
            if (!token.equals(")")) {
                obtieneError(PARENTESIS);
            }
            obtieneToken();
        } else {
            resultado = valor();
        }
        return resultado;
    }


    private boolean valor() throws Excepciones{
        boolean resultado = false;

        switch (tipoToken){
            case NUMERO:
                try {
                    resultado = Boolean.parseBoolean(token);
                } catch (Exception e) {
                    System.out.println("Error valor(): " + e);
                    obtieneError(SYNTAXIS);
                }
                obtieneToken();
                break;

            case VARIABLE:
                var[i] = token;
                j = 0;


                while (j<i) {                    
                    if (var[j].equals(token)) {
                        resultado = ValorVar[j];
                        break;
                    }
                    j++;
                }
                System.out.println(i + " - " + j);
                if (j == i) {
                    BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
                    //System.out.println("Ingresa el valor booleano de " + token);
                    String bool = "";
                    /*try {
                        bool = BR.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    if (N_Variables == 1) {
                        if (CONTADOR == 1) {
                            bool = P[POSICION_ARRAY];
                            ValorVar[i] = Boolean.parseBoolean(bool);
                            resultado = Boolean.parseBoolean(bool);
                            POSICION_ARRAY += 1;
                        }
                    } else if (N_Variables == 2) {
                        if (CONTADOR == 1) {
                            bool = P[POSICION_ARRAY];
                            ValorVar[i] = Boolean.parseBoolean(bool);
                            resultado = Boolean.parseBoolean(bool);
                            CONTADOR += 1;
                        } else if (CONTADOR == 2) {
                            bool = PQ[POSICION_ARRAY];
                            ValorVar[i] = Boolean.parseBoolean(bool);
                            resultado = Boolean.parseBoolean(bool);
                            CONTADOR = 1;
                            POSICION_ARRAY += 1;
                        }
                    } else if (N_Variables == 3) {
                        if (CONTADOR == 1) {
                            bool = P[POSICION_ARRAY];
                            ValorVar[i] = Boolean.parseBoolean(bool);
                            resultado = Boolean.parseBoolean(bool);
                            CONTADOR += 1;
                        } else if (CONTADOR == 2) {
                            bool = PQ[POSICION_ARRAY];
                            ValorVar[i] = Boolean.parseBoolean(bool);
                            resultado = Boolean.parseBoolean(bool);
                            CONTADOR += 1;
                        } else if (CONTADOR == 3) {
                            bool = PQR[POSICION_ARRAY];
                            ValorVar[i] = Boolean.parseBoolean(bool);
                            resultado = Boolean.parseBoolean(bool);
                            CONTADOR = 1;
                            POSICION_ARRAY += 1;
                        }
                    } else if (N_Variables == 4) {
                        if (CONTADOR == 1) {
                            bool = P[POSICION_ARRAY];
                            ValorVar[i] = Boolean.parseBoolean(bool);
                            resultado = Boolean.parseBoolean(bool);
                            CONTADOR += 1;
                        } else if (CONTADOR == 2) {
                            bool = PQ[POSICION_ARRAY];
                            ValorVar[i] = Boolean.parseBoolean(bool);
                            resultado = Boolean.parseBoolean(bool);
                            CONTADOR += 1;
                        } else if (CONTADOR == 3) {
                            bool = PQR[POSICION_ARRAY];
                            ValorVar[i] = Boolean.parseBoolean(bool);
                            resultado = Boolean.parseBoolean(bool);
                            CONTADOR += 1;
                        } else if (CONTADOR == 4) {
                            bool = PQRS[POSICION_ARRAY];
                            ValorVar[i] = Boolean.parseBoolean(bool);
                            resultado = Boolean.parseBoolean(bool);
                            CONTADOR = 1;
                            POSICION_ARRAY += 1;
                        }
                    } else if (N_Variables >= 5) {
                        obtieneError(SYNTAXIS);
                    }
                    
                }
                i++;
                System.out.println(i + " - " + j);
                obtieneToken();
                break;

            default:
                obtieneError(SYNTAXIS);
                break;
        }
        return resultado;
    }

    // MANEJA UN ERROR
    private void obtieneError(int error) throws Excepciones{
        
        int ERR = error;
        String[] err={
                        "ERROR DE SYNTAXIS",
                        "PARENTESIS NO BALANCEADOS",
                        "NO EXISTE EXPRESION",
                        "DIVISION POR CERO"};
        JOptionPane.showMessageDialog(UI,err[ERR]);
                throw new Excepciones(err[error]);
    }

    // Obtiene un token
    private void obtieneToken(){
        tipoToken = NINGUNO;
        token = "";

        // Busca el final de la expresion

        if (expIndice == exp.length()) {
            token = FINEXP;
            return;
        }

        // Omite el espacio en blanco
        while (expIndice < exp.length() && Character.isWhitespace(exp.charAt(expIndice))) ++expIndice;

        // Espacio en blanco termina la expresión

        if (expIndice == exp.length()) {
            token = FINEXP;
            return;
        }

        if (esDelimitador(exp.charAt(expIndice))) {
            // Es operador
            token += exp.charAt(expIndice);
            expIndice++;
            tipoToken = DELIMITADOR;

        } else if (Character.isLetter(exp.charAt(expIndice))) { // Es variable
            while (!esDelimitador(exp.charAt(expIndice))) {                
                token += exp.charAt(expIndice);
                expIndice++;

                if (expIndice >= exp.length()) {
                    break;
                } 
            }
            tipoToken = VARIABLE;

        } else if (Character.isDigit(exp.charAt(expIndice))) { // Es Número
            while (!esDelimitador(exp.charAt(expIndice))) {                
                token += exp.charAt(expIndice);
                expIndice++;
                if (expIndice >= exp.length()) {
                    break;
                }
            }
            tipoToken = NUMERO;
        } else {
            token = FINEXP;
            return;
        }
    }

    private boolean esDelimitador(char c){
        if (("≡↔⊻→∨∧¬()".indexOf(c) != -1)) {
            return true;
        } else {
            return false;
        }
    }


}
