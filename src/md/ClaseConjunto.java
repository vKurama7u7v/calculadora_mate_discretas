package md;

import javax.lang.model.type.TypeKind;

class ClaseConjunto {
    
    private String [] _Ca,_Cb,_Cc,_Cr;
    private int _LongA,_LongB,_LongC;
    
    //estos son los tipos de token
    final int NINGUNO = 0;
    final int DELIMITADOR = 1;
    final int VARIABLE = 2;
    final int NUMERO = 3;

    //estos son los tipos de errores de sintaxis
    final int SYNTAXIS = 0;
    final int PARENTESIS = 1;
    final int SINEXP = 2;

    //Estas token indican fin de la expresion
    final String FINEXP = "\0";

    private String exp; // alude a la cadena de expresion
    private int expIndice; // indice actual de la expresion
    private String token; // contiene token actual
    private int tipoToken; // contien tipo de token
    //ocupados para las variables
    private String var[] = new String[100];
    private String valorVar[] = new String[100];
    int i=0;            
    int j=0;
    

    
    //Constructor    
    public void ConjuntoCa(int LongA)
    {
        _LongA=LongA;
        _Ca = new String [_LongA];
    }
    
    public void ConjuntoCb(int LongB)
    {
        _LongB = LongB;
        _Cb = new String [_LongB];
    }
    
    public void ConjuntoCc(int LongC)
    {
        _LongC = LongC;
        _Cc = new String [_LongC];
    }
    
    //Metodos Set
    public void Ca(String Ca,int Indice)
    {
        _Ca[Indice]=Ca;
    }
    
    public void Ca(String []Ca)
    {
        _Ca=Ca;
    }
    
    public void Cb(String Cb,int Indice)
    {
        _Cb[Indice] = Cb;
    }
    
    public void Cb(String []Cb)
    {
        _Cb = Cb;
    }
    
    public void Cc(String Cc,int Indice)
    {
        _Cc[Indice]=Cc;
    }
    
    public void Cc(String []Cc)
    {
        _Cb=Cc;
    }
    
    //Metodos Get
    
    public int LongA()
    {
        return _LongA;
    }
    
    public int LongB()
    {
        return _LongB;
    }
    
    public int LongC()
    {
        return _LongC;
    }
    
    public String Ca(int Indice)
    {
        return _Ca[Indice];
    }
    
    public String [] Ca()
    {
        return _Ca;
    }
    
    public String Cb(int Indice)
    {
        return _Cb[Indice];
    }
    
    public String [] Cb()
    {
        return _Cb;
    }
    
    public String Cc(int Indice)
    {
        return _Cc[Indice];
    }
    
    public String [] Cc()
    {
        return _Cc;
    }
   
    //OPERACIONES DE CONJUNTOS
    
    public String Evaluar(String Exp) throws Excepciones{
        
        String resultado;
        exp = Exp;
        expIndice = 0;
        
        obtieneToken();
        if (token.equals(FINEXP)) {
            obtieneError(SINEXP);
        }
        
        resultado = PRODUCTO_CARTESIANO();
        if (!token.equals(FINEXP)) {
            obtieneError(SYNTAXIS);
        }
        i = 0;
        return resultado;
    }

    
    // PRODUCTO CARTESIANO() ×
    private String PRODUCTO_CARTESIANO() throws Excepciones {
        char op;
        String [] R;
        String [] RP;
        String [] AUX1;
        String [] AUX2;
        String [] AUX;
        
        String resultado;
        String resultadoParcial;
        
        resultado = DIFERENCIA();
        
        R = new String[_LongA+_LongB+_LongC];
        String Conjunto1 = "";
        int x = 0;
        for (int k = 0; k < resultado.length(); k++) {
            if (resultado.charAt(k) != ';') {
                Conjunto1 = Conjunto1 + resultado.charAt(k);
            } else {
                R[x] = Conjunto1;
                Conjunto1 = "";
                x += 1;
            }
        }
        
        while ((op = token.charAt(0)) == '×') {
            obtieneToken();
            resultadoParcial = DIFERENCIA();
            
            RP = new String[_LongA+_LongB+_LongC];
            String Conjunto2 = "";
            int y = 0;
            for (int k = 0; k < resultadoParcial.length(); k++) {
                if (resultadoParcial.charAt(k) != ';') {
                    Conjunto2 = Conjunto2 + resultadoParcial.charAt(k);
                } else {
                    RP[y] = Conjunto2;
                    Conjunto2 = "";
                    y += 1;
                }
            }
            
            System.out.println(resultado.length());
            System.out.println(resultadoParcial.length());
            switch (op){
                case '×':
                    int contador = 0;
                    String LEL = "";
                    String resultadoFinal = "";
                    
                    for (int k = 0; k < R.length; k++) {
                        if (R[k] != null) {
                            LEL = LEL + R[k] + ";";
                            contador += 1;
                        }
                    }
                    
                    AUX1 = new String[contador];
                    int z = 0;
                    for (int k = 0; k < LEL.length(); k++) {
                        if (LEL.charAt(k) != ';') {
                            resultadoFinal = resultadoFinal + LEL.charAt(k);
                        } else {
                            AUX1[z] = resultadoFinal;
                            resultadoFinal = "";
                            z += 1;
                        }
                    }
                    
                    for (int k = 0; k < contador; k++) {
                        System.out.println(AUX1[k]);
                    }
                    
                    z = 0;
                    LEL = "";
                    contador = 0;
                    
                    
                    for (int k = 0; k < RP.length; k++) {
                        if (RP[k] != null) {
                            LEL = LEL + RP[k] + ";";
                            contador += 1;
                        }
                    }
                    
                    AUX2 = new String[contador];
                    for (int k = 0; k < LEL.length(); k++) {
                        if (LEL.charAt(k) != ';') {
                            resultadoFinal = resultadoFinal + LEL.charAt(k);
                        } else {
                            AUX2[z] = resultadoFinal;
                            resultadoFinal = "";
                            z += 1;
                        }
                    }
                    
                    for (int k = 0; k < contador; k++) {
                        System.out.println(AUX2[k]);
                    }
                    
                    int _CONTADOR = 0;
                    int _BAND = 0;
                    String _CADENA = "";

                    System.out.println(AUX1.length);
                    System.out.println(AUX2.length);
                    AUX = new String [AUX1.length * AUX2.length]; // M x N
                    String _ProductCartes = "";
                    
                    for (int k = 0; k < AUX1.length; k++) {
                        for (int l = 0; l < AUX2.length; l++) {
                            _ProductCartes = "(" + AUX1[k] + "," + AUX2[l] + ")";
                            AUX[_CONTADOR] = _ProductCartes;
                            _CONTADOR++;
                        }
                    }
                    
                    for (int k = 0; k < AUX1.length * AUX2.length; k++) {
                        if (AUX[k] != null) {
                            _CADENA = _CADENA + AUX[k] + ";";
                        }
                    }
                    
                    resultado = _CADENA;
                    System.out.println(resultado);
                    break;
            }
        }
        return resultado;
    }
    
    // DIFERENCIA -
    private String DIFERENCIA() throws Excepciones {
        char op;
        String [] R;
        String [] RP;
        String [] AUX1;
        String [] AUX2;
        String [] AUX;
        
        String resultado;
        String resultadoParcial;
        
        resultado = INTERSECCION();
        
        R = new String[_LongA+_LongB+_LongC];
        String Conjunto1 = "";
        int x = 0;
        for (int k = 0; k < resultado.length(); k++) {
            if (resultado.charAt(k) != ';') {
                Conjunto1 = Conjunto1 + resultado.charAt(k);
            } else {
                R[x] = Conjunto1;
                Conjunto1 = "";
                x += 1;
            }
        }
        
        while ((op = token.charAt(0)) == '-') {
            obtieneToken();
            resultadoParcial = INTERSECCION();
            
            RP = new String[_LongA+_LongB+_LongC];
            String Conjunto2 = "";
            int y = 0;
            for (int k = 0; k < resultadoParcial.length(); k++) {
                if (resultadoParcial.charAt(k) != ';') {
                    Conjunto2 = Conjunto2 + resultadoParcial.charAt(k);
                } else {
                    RP[y] = Conjunto2;
                    Conjunto2 = "";
                    y += 1;
                }
            }
            
            System.out.println(resultado.length());
            System.out.println(resultadoParcial.length());
            switch (op){
                case '-':
                    int contador = 0;
                    String LEL = "";
                    String resultadoFinal = "";
                    
                    for (int k = 0; k < R.length; k++) {
                        if (R[k] != null) {
                            LEL = LEL + R[k] + ";";
                            contador += 1;
                        }
                    }
                    
                    AUX1 = new String[contador];
                    int z = 0;
                    for (int k = 0; k < LEL.length(); k++) {
                        if (LEL.charAt(k) != ';') {
                            resultadoFinal = resultadoFinal + LEL.charAt(k);
                        } else {
                            AUX1[z] = resultadoFinal;
                            resultadoFinal = "";
                            z += 1;
                        }
                    }
                    
                    for (int k = 0; k < contador; k++) {
                        System.out.println(AUX1[k]);
                    }
                    
                    z = 0;
                    LEL = "";
                    contador = 0;
                    
                    
                    for (int k = 0; k < RP.length; k++) {
                        if (RP[k] != null) {
                            LEL = LEL + RP[k] + ";";
                            contador += 1;
                        }
                    }
                    
                    AUX2 = new String[contador];
                    for (int k = 0; k < LEL.length(); k++) {
                        if (LEL.charAt(k) != ';') {
                            resultadoFinal = resultadoFinal + LEL.charAt(k);
                        } else {
                            AUX2[z] = resultadoFinal;
                            resultadoFinal = "";
                            z += 1;
                        }
                    }
                    
                    for (int k = 0; k < contador; k++) {
                        System.out.println(AUX2[k]);
                    }
                    
                    int _CONTADOR = 0;
                    int _BAND = 0;
                    String _CADENA = "";

                    System.out.println(AUX1.length);
                    System.out.println(AUX2.length);
                    AUX = new String [AUX1.length + AUX2.length];
                    
                    for (int k = 0; k < AUX1.length; k++) {
                        _BAND = 0;
                        for (int l = 0; l < AUX2.length; l++) {
                            if (AUX1[k].equals(AUX2[l])) {
                                _BAND = 1;
                            }
                        }
                        if (_BAND == 0) {
                            AUX[_CONTADOR] = AUX1[k];
                            _CONTADOR++;
                        }
                    }
                    
                    for (int k = 0; k < AUX1.length + AUX2.length; k++) {
                        if (AUX[k] != null) {
                            _CADENA = _CADENA + AUX[k] + ";";
                        }
                    }
                    
                    resultado = _CADENA;
                    System.out.println(resultado);
                    break;
            }
        }
        return resultado;
    }
    
    // INTERSECCION ∩
    private String INTERSECCION() throws Excepciones {
        char op;
        String [] R;
        String [] RP;
        String [] AUX1;
        String [] AUX2;
        String [] AUX;
        
        String resultado;
        String resultadoParcial;
        
        resultado = UNION();
        
        R = new String[_LongA+_LongB+_LongC];
        String Conjunto1 = "";
        int x = 0;
        for (int k = 0; k < resultado.length(); k++) {
            if (resultado.charAt(k) != ';') {
                Conjunto1 = Conjunto1 + resultado.charAt(k);
            } else {
                R[x] = Conjunto1;
                Conjunto1 = "";
                x += 1;
            }
        }
        
        while ((op = token.charAt(0)) == '∩') {
            obtieneToken();
            resultadoParcial = UNION();
            
            RP = new String[_LongA+_LongB+_LongC];
            String Conjunto2 = "";
            int y = 0;
            for (int k = 0; k < resultadoParcial.length(); k++) {
                if (resultadoParcial.charAt(k) != ';') {
                    Conjunto2 = Conjunto2 + resultadoParcial.charAt(k);
                } else {
                    RP[y] = Conjunto2;
                    Conjunto2 = "";
                    y += 1;
                }
            }
            
            System.out.println(resultado.length());
            System.out.println(resultadoParcial.length());
            switch (op){
                case '∩':
                    int contador = 0;
                    String LEL = "";
                    String resultadoFinal = "";
                    
                    for (int k = 0; k < R.length; k++) {
                        if (R[k] != null) {
                            LEL = LEL + R[k] + ";";
                            contador += 1;
                        }
                    }
                    
                    AUX1 = new String[contador];
                    int z = 0;
                    for (int k = 0; k < LEL.length(); k++) {
                        if (LEL.charAt(k) != ';') {
                            resultadoFinal = resultadoFinal + LEL.charAt(k);
                        } else {
                            AUX1[z] = resultadoFinal;
                            resultadoFinal = "";
                            z += 1;
                        }
                    }
                    
                    for (int k = 0; k < contador; k++) {
                        System.out.println(AUX1[k]);
                    }
                    
                    z = 0;
                    LEL = "";
                    contador = 0;
                    
                    
                    for (int k = 0; k < RP.length; k++) {
                        if (RP[k] != null) {
                            LEL = LEL + RP[k] + ";";
                            contador += 1;
                        }
                    }
                    
                    AUX2 = new String[contador];
                    for (int k = 0; k < LEL.length(); k++) {
                        if (LEL.charAt(k) != ';') {
                            resultadoFinal = resultadoFinal + LEL.charAt(k);
                        } else {
                            AUX2[z] = resultadoFinal;
                            resultadoFinal = "";
                            z += 1;
                        }
                    }
                    
                    for (int k = 0; k < contador; k++) {
                        System.out.println(AUX2[k]);
                    }
                    
                    int _CONTADOR = 0;
                    int _BAND = 0;
                    String _CADENA = "";

                    System.out.println(AUX1.length);
                    System.out.println(AUX2.length);
                    AUX = new String [AUX1.length+AUX2.length];
                    
                    for (int k = 0; k < AUX1.length; k++) {
                        _BAND = 0;
                        for (int l = 0; l < AUX2.length; l++) {
                            if (AUX1[k].equals(AUX2[l])) {
                                _BAND = 1;
                            }
                        }
                        if (_BAND == 1) {
                            AUX[_CONTADOR] = AUX1[k];
                            _CONTADOR ++;
                        }
                    }
                    for (int k = 0; k < AUX1.length+AUX2.length; k++) {
                        if (AUX[k] != null) {
                            _CADENA = _CADENA + AUX[k] + ";";
                        }
                    }
                    
                    resultado = _CADENA;
                    System.out.println(resultado);
                    break;
            }
        }
        return resultado;
    }
    
    // UNION ∪
    private String UNION() throws Excepciones {
        char op;
        String [] R;
        String [] RP;
        String [] AUX1;
        String [] AUX2;
        String [] AUX;
        
        String resultado;
        String resultadoParcial;
        
        resultado = PARENTESIS();
        
        R = new String[_LongA+_LongB+_LongC];
        String Conjunto1 = "";
        int x = 0;
        for (int k = 0; k < resultado.length(); k++) {
            if (resultado.charAt(k) != ';') {
                Conjunto1 = Conjunto1 + resultado.charAt(k);
            } else {
                R[x] = Conjunto1;
                Conjunto1 = "";
                x += 1;
            }
        }
        
        while ((op = token.charAt(0)) == '∪') {
            obtieneToken();
            resultadoParcial = PARENTESIS();
            
            RP = new String[_LongA+_LongB+_LongC];
            String Conjunto2 = "";
            int y = 0;
            for (int k = 0; k < resultadoParcial.length(); k++) {
                if (resultadoParcial.charAt(k) != ';') {
                    Conjunto2 = Conjunto2 + resultadoParcial.charAt(k);
                } else {
                    RP[y] = Conjunto2;
                    Conjunto2 = "";
                    y += 1;
                }
            }
            
            System.out.println(resultado.length());
            System.out.println(resultadoParcial.length());
            switch (op){
                case '∪':
                    int contador = 0;
                    String LEL = "";
                    String resultadoFinal = "";
                    
                    for (int k = 0; k < R.length; k++) {
                        if (R[k] != null) {
                            LEL = LEL + R[k] + ";";
                            contador += 1;
                        }
                    }
                    
                    AUX1 = new String[contador];
                    int z = 0;
                    for (int k = 0; k < LEL.length(); k++) {
                        if (LEL.charAt(k) != ';') {
                            resultadoFinal = resultadoFinal + LEL.charAt(k);
                        } else {
                            AUX1[z] = resultadoFinal;
                            resultadoFinal = "";
                            z += 1;
                        }
                    }
                    
                    for (int k = 0; k < contador; k++) {
                        System.out.println(AUX1[k]);
                    }
                    
                    z = 0;
                    LEL = "";
                    contador = 0;
                    
                    
                    for (int k = 0; k < RP.length; k++) {
                        if (RP[k] != null) {
                            LEL = LEL + RP[k] + ";";
                            contador += 1;
                        }
                    }
                    
                    AUX2 = new String[contador];
                    for (int k = 0; k < LEL.length(); k++) {
                        if (LEL.charAt(k) != ';') {
                            resultadoFinal = resultadoFinal + LEL.charAt(k);
                        } else {
                            AUX2[z] = resultadoFinal;
                            resultadoFinal = "";
                            z += 1;
                        }
                    }
                    
                    for (int k = 0; k < contador; k++) {
                        System.out.println(AUX2[k]);
                    }
                    
                    int _CONTADOR = 0;
                    int _BAND = 0;
                    String _CADENA = "";

                    System.out.println(AUX1.length);
                    System.out.println(AUX2.length);
                    AUX = new String [AUX1.length + AUX2.length];
                    
                    for (int k = 0; k < AUX1.length; k++) {
                        AUX[k] = AUX1[k];
                    }
                    
                    _CONTADOR = AUX1.length;
                    
                    for (int k = 0; k < AUX2.length; k++) {
                        _BAND = 0;
                        for (int l = 0; l < AUX1.length + AUX2.length; l++) {
                            if (AUX2[k].equals(AUX[l])) {
                                _BAND = 1;
                            }
                        }
                        
                        if (_BAND == 0) {
                            AUX[_CONTADOR] = AUX2[k];
                            _CONTADOR ++;
                        }
                    }
                    
                    for (int k = 0; k < AUX1.length + AUX2.length; k++) {
                        if (AUX[k] != null) {
                            _CADENA = _CADENA + AUX[k] + ";";
                        }
                    }
                    
                    resultado = _CADENA;
                    System.out.println(resultado);
                    break;
            }
        }
        return resultado;
    }
    
    // PARENTESIS ( )
    private String PARENTESIS() throws Excepciones {
        String resultado;
        if (token.equals("(")) {
            obtieneToken();
            resultado = PRODUCTO_CARTESIANO();
            if (!token.equals(")")) {
                obtieneError(PARENTESIS);
            }
            obtieneToken();
        } else {
            resultado = Valor();
        }
        return resultado;
    }
    
    private String Valor() throws Excepciones {
        String resultado = "";
        switch (tipoToken){
            case VARIABLE:
                var[i] = token;
                j = 0;
                while (j < i) {                    
                    if (var[j].equals(token)) {
                        resultado = valorVar[j];
                        System.out.println(resultado);
                        break;
                    }
                    j++;
                }
                System.out.println("valorVar");
                
                if (j == i) {
                    System.out.println("Conjunto " + token);
                    String dato = "";
                    char OP = token.charAt(0);
                    if (OP == 'A') {
                        if (_LongA != 0) {
                            for (int k = 0; k < _LongA; k++) {
                                dato = dato + _Ca[k] + ";";
                            }
                        }
                        valorVar[i] = dato;
                        resultado = dato;
                        
                    } else if (OP == 'B') {
                        if (_LongB != 0) {
                            for (int k = 0; k < _LongB; k++) {
                                dato = dato + _Cb[k] + ";";
                            }
                        }
                        valorVar[i] = dato;
                        resultado = dato;
                    } else if (OP == 'C') {
                        if (_LongC != 0) {
                            for (int k = 0; k < _LongC; k++) {
                                dato = dato + _Cc[k] + ";";
                            }
                        }
                        valorVar[i] = dato;
                        resultado = dato;
                    } else {
                        obtieneError(SYNTAXIS);
                        break;
                    }
                    System.out.println(resultado);
                    
                }
                i++;
                obtieneToken();
                break;
            default:
                obtieneError(SYNTAXIS);
                break;
        }
        return resultado;
    }
    
    
    //MANEJA UN ERROR
    private void obtieneError(int error)throws Excepciones {
        String[] err={
                "ERROR DE SYNTAXIS",
                "PARENTESIS NO BALANCEADOS",
                "NO EXISTE EXPRESION"};
        throw new Excepciones(err[error]);
    }

    //obtiene la siguiente token
    private void obtieneToken() {
	tipoToken = NINGUNO;
	token = "";

	//Busca el final de la expresion
	
	if(expIndice == exp.length()){
            token = FINEXP;
            return;
	}

	//Omite el espacio en blanco
	
	while(expIndice < exp.length() && Character.isWhitespace(exp.charAt(expIndice))) ++expIndice;

	//Espacio en blanco termina la expresion
	
	if (expIndice == exp.length()){
            token = FINEXP;
            return;
	}

	if(esDelimitador(exp.charAt(expIndice))) { //es operador
            token += exp.charAt(expIndice);
            expIndice++;
            tipoToken = DELIMITADOR;
        } else if (Character.isLetter(exp.charAt(expIndice))){ //es variable
            while(!esDelimitador(exp.charAt(expIndice))){
                token += exp.charAt(expIndice);
                expIndice++;
                if(expIndice >= exp.length()){ break;}
            }
            tipoToken = VARIABLE;
        }else if (Character.isDigit(exp.charAt(expIndice))){ //es numero
            while(!esDelimitador(exp.charAt(expIndice))){
                token += exp.charAt (expIndice);
                expIndice++;
                if(expIndice >= exp.length()){ break;}
            }
            tipoToken = NUMERO;
        }else{ //caracter desconocido termina la expresion
            token = FINEXP;
            return;
        }
    }

    //Devuelve true si c es un delimitardor
    private boolean esDelimitador (char c){
        if (("×´-∩∪()".indexOf (c) != -1)) {
            return true;
        } else {
            return false;
        }
    } 
}
