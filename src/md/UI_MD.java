package md;

import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import METODOS_DE_VALIDACIONES.VALIDACIONES_PARA_CAMPOS_DE_TEXTO;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author vkurama7u7v
 */
public class UI_MD extends javax.swing.JFrame {
    // VARIABLES GLOBALES :D
    int PX, PY;
    int cont = 1;
    
    // INSTANCIAS
    VALIDACIONES_PARA_CAMPOS_DE_TEXTO validar = new VALIDACIONES_PARA_CAMPOS_DE_TEXTO();
    String [] TITULOS_TABLAS_VERDAD = {"P","Q","R","S","RESULTADO"};
    DefaultTableModel TABLAS_VERDAD = new DefaultTableModel(null,TITULOS_TABLAS_VERDAD);
    ClaseConjunto Muestra = new ClaseConjunto();
    
    String[] PI =    {"V","F","V","F","V","F","V","F","V","F","V","F","V","F","V","F"};
    String[] PQI =   {"V","V","F","F","V","V","F","F","V","V","F","F","V","V","F","F"};
    String[] PQRI =  {"V","V","V","V","F","F","F","F","V","V","V","V","F","F","F","F"};
    String[] PQRSI = {"V","V","V","V","V","V","V","V","F","F","F","F","F","F","F","F"};
    
    public UI_MD() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/IconoApp.png")));
        BOTONES_TRANSPARENTES();
        VALIDACIONES_TXT();
        TablasDeVerdad_tblRESULTADOS.setModel(TABLAS_VERDAD);
    }
    
    // Diseño de botones transparentes
    public void BOTONES_TRANSPARENTES(){
        // BOTON CERRAR
        BTN_CERRAR.setOpaque(false);
        BTN_CERRAR.setContentAreaFilled(false);
        BTN_CERRAR.setBorderPainted(false);
        // BOTON MINIMIZAR
        BTN_MINIMIZAR.setOpaque(false);
        BTN_MINIMIZAR.setContentAreaFilled(false);
        BTN_MINIMIZAR.setBorderPainted(false);
        // BOTON NAVBAR SALIR
        BTN_NAVBAR_Salir.setOpaque(false);
        BTN_NAVBAR_Salir.setContentAreaFilled(false);
        BTN_NAVBAR_Salir.setBorderPainted(false);
        // BOTON NAVBAR DASHBOARD
        BTN_NAVBAR_Dashboard.setOpaque(false);
        BTN_NAVBAR_Dashboard.setContentAreaFilled(false);
        BTN_NAVBAR_Dashboard.setBorderPainted(false);
        // BOTON NAVBAR DIAGRAMA_VENN
        BTN_NAVBAR_DiagramaVenn.setOpaque(false);
        BTN_NAVBAR_DiagramaVenn.setContentAreaFilled(false);
        BTN_NAVBAR_DiagramaVenn.setBorderPainted(false);
        // BOTON NAVBAR TBLS_VERDAD
        BTN_NAVBAR_TablasVerdad.setOpaque(false);
        BTN_NAVBAR_TablasVerdad.setContentAreaFilled(false);
        BTN_NAVBAR_TablasVerdad.setBorderPainted(false);
        // BOTON NAVBAR OP_CONJUNTOS
        BTN_NAVBAR_OperacionesConjuntos.setOpaque(false);
        BTN_NAVBAR_OperacionesConjuntos.setContentAreaFilled(false);
        BTN_NAVBAR_OperacionesConjuntos.setBorderPainted(false);
    }
    
    // Validaciones
    public void VALIDACIONES_TXT(){
        // Diagramas de Venn (3 Conjuntos)
        validar.VALIDAR_SOLO_NUMEROS(txtDiagramaVenn3_Universal);
        validar.VALIDAR_SOLO_NUMEROS(txtDiagramaVenn3_ConjuntoA);
        validar.VALIDAR_SOLO_NUMEROS(txtDiagramaVenn3_ConjuntoB);
        validar.VALIDAR_SOLO_NUMEROS(txtDiagramaVenn3_ConjuntoC);
        validar.VALIDAR_SOLO_NUMEROS(txtDiagramaVenn3_ValorAuB);
        validar.VALIDAR_SOLO_NUMEROS(txtDiagramaVenn3_ValorBuC);
        validar.VALIDAR_SOLO_NUMEROS(txtDiagramaVenn3_ValorCuA);
        validar.VALIDAR_SOLO_NUMEROS(txtDiagramaVenn3_ValorAuBuC);
        
        // Operaciones De Conjuntos
        validar.VALIDAR_SOLO_LETRAS_Y_NUMEROS(OperacionesConjuntos_txtConjuntoA);
        validar.VALIDAR_SOLO_LETRAS_Y_NUMEROS(OperacionesConjuntos_txtConjuntoB);
        validar.VALIDAR_SOLO_LETRAS_Y_NUMEROS(OperacionesConjuntos_txtConjuntoC);
        
        
    }
    
    public void LIMPIAR_TABLA(){
        TABLAS_VERDAD = (DefaultTableModel) TablasDeVerdad_tblRESULTADOS.getModel();
        int fila = TablasDeVerdad_tblRESULTADOS.getRowCount();
        for(int i = fila-1; i>=0; i--){
            TABLAS_VERDAD.removeRow(i);
        }
    }
    
    public void VALIDAR_EXPRESION_TABLAS_DE_VERDAD(){
        String EXPRESION;
        AnalizadorExpresiones analizar = new AnalizadorExpresiones();
        if (txtTablasVerdad_Consola.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "No hay Expresión");
        } else {
            EXPRESION = txtTablasVerdad_Consola.getText().trim();
            EXPRESION = EXPRESION.toUpperCase();
            System.out.println(EXPRESION);
            
            int NVAR = 0;
            int P = 0;
            int Q = 0;
            int R = 0;
            int S = 0;
            int E = 0;
            System.out.println("EXPRESION.length() " + EXPRESION.length());
            
            for (int i = 0; i < EXPRESION.length(); i++) {
                char CHAR = EXPRESION.charAt(i);
                System.out.println("CHAR: " + CHAR);
                if (CHAR == 'P') {
                    P += 1;
                } else if (CHAR == 'Q') {
                    Q += 1;
                } else if (CHAR == 'R') {
                    R += 1;
                } else if (CHAR == 'S') {
                    S += 1;
                } else if (CHAR == '≡') {
                    E += 1;
                } else {
                    System.out.println("LEL");
                }
            }

            if (P > 0) {
                NVAR += 1;
            }

            if (Q > 0) {
                NVAR += 1;
            }

            if (R > 0) {
                NVAR += 1;
            }

            if (S > 0) {
                NVAR += 1;
            }
            
            int REPETICIONES = (int) Math.pow(2, NVAR);
            
            try {
                lblTablasVerdad_Resultado.setText(EXPRESION);
                for (int k = 0; k < REPETICIONES; k++) {
                    String PE = "";
                    String QE = "";
                    String RE = "";
                    String SE = "";
                    if (P > 0) {
                        PE = PI[k];
                    }

                    if (Q > 0) {
                        QE = PQI[k];
                    }

                    if (R > 0) {
                        RE = PQRI[k];
                    }

                    if (S > 0) {
                        SE = PQRSI[k];
                    }
                    
                    String RESULTADO = String.valueOf(analizar.EVALUAR(EXPRESION, NVAR));
                    String ELEMENTOS[] ={PE,QE,RE,SE,RESULTADO};
                    TABLAS_VERDAD.addRow(ELEMENTOS);
                    TablasDeVerdad_tblRESULTADOS.setModel(TABLAS_VERDAD);
                }
                
                if (E > 0) {
                    int FILAS = TablasDeVerdad_tblRESULTADOS.getRowCount();
                    boolean bool;
                    for (int i = 0; i < FILAS; i++) {
                        bool = Boolean.parseBoolean(TablasDeVerdad_tblRESULTADOS.getValueAt(i, 4).toString());
                        if (bool == false) {
                            lblTablasVerdad_Equiv.setText("∴ No es equivalente");
                            break;
                        } else {
                            lblTablasVerdad_Equiv.setText("∴ Es equivalente");
                        }
                    }
                }
                
            } catch (Exception e) {
                System.out.println("VALIDAR_EXPRESION_TABLAS_DE_VERDAD(): " + e);
            }
        }
    }
    
    public void TABLAS_DE_VERDAD_BTN_DELELTE(){
        String CONSOLA;
        if (txtTablasVerdad_Consola.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "No hay Expresión");
        } else {
            CONSOLA = txtTablasVerdad_Consola.getText().trim();
            int ULTIMO_CHAR = CONSOLA.length();
            System.out.println("TABLAS_DE_VERDAD_BTN_DELELTE(" + CONSOLA.length() + ")");
            String EXPR = "";
            for (int i = 0; i < CONSOLA.length(); i++) {
                if (ULTIMO_CHAR -1 != i) {
                    EXPR = EXPR + CONSOLA.charAt(i);
                }
            }
            lblTablasVerdad_Resultado.setText("");
            lblTablasVerdad_Equiv.setText("");
            txtTablasVerdad_Consola.setText(EXPR);
            LIMPIAR_TABLA();
        }
    }
    
    
    
    public void LIMPIAR_CAMPOSTXT_TABLAS_VERDAD(){
        txtTablasVerdad_Consola.setText("");
        lblTablasVerdad_Resultado.setText("");
        lblTablasVerdad_Equiv.setText("");
        LIMPIAR_TABLA();
    }
    
    public void LIMPIAR_CAMPOSTXT_OP_CONJUNTOS(){
        txtOPConjuntos_Consola.setText("");
    }
    
    public void LIMPIAR_CAMPOS_OPCONJUNTOS(){
        OperacionesConjuntos_txtConjuntoA.setText("");
        OperacionesConjuntos_txtConjuntoA.setEnabled(false);
        btnEnabled_CA.setEnabled(true);
        btnSave_Ca.setText("Dato: 1/0");
        btnSave_Ca.setEnabled(false);
        TextArea_A.setText("");
        
        OperacionesConjuntos_txtConjuntoB.setText("");
        OperacionesConjuntos_txtConjuntoB.setEnabled(false);
        btnEnabled_CB.setEnabled(true);
        btnSave_Cb.setText("Dato: 1/0");
        btnSave_Cb.setEnabled(false);
        TextArea_B.setText("");
        
        OperacionesConjuntos_txtConjuntoC.setText("");
        OperacionesConjuntos_txtConjuntoC.setEnabled(false);
        btnEnabled_CC.setEnabled(true);
        btnSave_Cc.setText("Dato: 1/0");
        btnSave_Cc.setEnabled(false);
        TextArea_C.setText("");
    }
    
    public void OP_CONJUNTOS_BTN_DELELTE(){
        String CONSOLA;
        if (txtOPConjuntos_Consola.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "No hay Expresión");
        } else {
            CONSOLA = txtOPConjuntos_Consola.getText().trim();
            int ULTIMO_CHAR = CONSOLA.length();
            System.out.println("OP_CONJUNTOS_BTN_DELELTE(" + CONSOLA.length() + ")");
            String EXPR = "";
            for (int i = 0; i < CONSOLA.length(); i++) {
                if (ULTIMO_CHAR -1 != i) {
                    EXPR = EXPR + CONSOLA.charAt(i);
                }
            }
            txtOPConjuntos_Consola.setText(EXPR);
        }
    }
    
    public void VALIDAR_INFO_OP_CONJUNTOS(){
        String EXPRESION;
        if (txtOPConjuntos_Consola.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "No hay Expresión");
        } else {
            EXPRESION = txtOPConjuntos_Consola.getText().trim();
            EXPRESION = EXPRESION;
            System.out.println(EXPRESION);
            try {
                TextArea_Consola.setText("{" + Muestra.Evaluar(EXPRESION) + "}");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public void CALCULAR_DIAGRAMA_VENN(){
        if (txtDiagramaVenn3_ConjuntoA.getText().equals("")
            || txtDiagramaVenn3_ConjuntoB.getText().equals("")
            || txtDiagramaVenn3_ConjuntoC.getText().equals("")
            || txtDiagramaVenn3_Universal.getText().equals("")
            || txtDiagramaVenn3_ValorAuB.getText().equals("")
            || txtDiagramaVenn3_ValorBuC.getText().equals("")
            || txtDiagramaVenn3_ValorCuA.getText().equals("")
            || txtDiagramaVenn3_ValorAuBuC.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Campos Obligatorios");
        } else {
            int A = Integer.parseInt(txtDiagramaVenn3_ConjuntoA.getText().trim());
            int B = Integer.parseInt(txtDiagramaVenn3_ConjuntoB.getText().trim());
            int C = Integer.parseInt(txtDiagramaVenn3_ConjuntoC.getText().trim());
            int U = Integer.parseInt(txtDiagramaVenn3_Universal.getText().trim());
            int AB = Integer.parseInt(txtDiagramaVenn3_ValorAuB.getText().trim());
            int BC = Integer.parseInt(txtDiagramaVenn3_ValorBuC.getText().trim());
            int CA = Integer.parseInt(txtDiagramaVenn3_ValorCuA.getText().trim());
            int ABC = Integer.parseInt(txtDiagramaVenn3_ValorAuBuC.getText().trim());
            
            int OP_AC = CA - ABC;
            int OP_AB = AB - ABC;
            int OP_BC = BC - ABC;
            int OP_A = A - OP_AC - OP_AB - ABC;
            int OP_B = B - OP_AB - OP_BC - ABC;
            int OP_C = C - OP_BC - OP_AC - ABC;
            int OP_UNIVERSAL = OP_AC + OP_AB + OP_BC + OP_A + OP_B + OP_C + ABC;
            int OP_SOBRANTE = U - OP_UNIVERSAL;
            
            lblA.setText(String.valueOf(OP_A));
            lblB.setText(String.valueOf(OP_B));
            lblC.setText(String.valueOf(OP_C));
            lblABC.setText(String.valueOf(ABC));
            lblAB.setText(String.valueOf(OP_AB));
            lblBC.setText(String.valueOf(OP_BC));
            lblCA.setText(String.valueOf(OP_AC));
            lblSobrante.setText(String.valueOf(OP_SOBRANTE));
            lblUniversal.setText(String.valueOf(U));
        }
    }
    
    public void LIMPIAR_DATOS_DIAGRAMA_VENN(){
        txtDiagramaVenn3_Universal.setText("0");
        txtDiagramaVenn3_ConjuntoA.setText("0");
        txtDiagramaVenn3_ConjuntoB.setText("0");
        txtDiagramaVenn3_ConjuntoC.setText("0");
        txtDiagramaVenn3_ValorAuB.setText("0");
        txtDiagramaVenn3_ValorBuC.setText("0");
        txtDiagramaVenn3_ValorCuA.setText("0");
        txtDiagramaVenn3_ValorAuBuC.setText("0");
        
        lblUniversal.setText("0");
        lblA.setText("0");
        lblB.setText("0");
        lblC.setText("0");
        lblAB.setText("0");
        lblBC.setText("0");
        lblCA.setText("0");
        lblABC.setText("0");
        lblSobrante.setText("0");
    }
    
    // EMPIEZA RELLENO
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel49 = new javax.swing.JLabel();
        BTN_MINIMIZAR = new javax.swing.JButton();
        BTN_CERRAR = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jPanel_NavBar = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        BTN_NAVBAR_Dashboard = new javax.swing.JButton();
        BTN_NAVBAR_DiagramaVenn = new javax.swing.JButton();
        BTN_NAVBAR_TablasVerdad = new javax.swing.JButton();
        BTN_NAVBAR_OperacionesConjuntos = new javax.swing.JButton();
        BTN_NAVBAR_Salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        PANEL_VENTANAS = new javax.swing.JTabbedPane();
        jPanel_Dashboard = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel_DiagramaVenn_3Conjuntos = new javax.swing.JPanel();
        DV_1Conjunto_btnCalcular1 = new javax.swing.JButton();
        DV_1Conjunto_btnLimpiar2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        txtDiagramaVenn3_Universal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDiagramaVenn3_ConjuntoA = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtDiagramaVenn3_ConjuntoB = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtDiagramaVenn3_ConjuntoC = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtDiagramaVenn3_ValorAuB = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtDiagramaVenn3_ValorBuC = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtDiagramaVenn3_ValorCuA = new javax.swing.JTextField();
        txtDiagramaVenn3_ValorAuBuC = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblUniversal = new javax.swing.JLabel();
        lblCA = new javax.swing.JLabel();
        lblSobrante = new javax.swing.JLabel();
        lblA = new javax.swing.JLabel();
        lblC = new javax.swing.JLabel();
        lblB = new javax.swing.JLabel();
        lblABC = new javax.swing.JLabel();
        lblAB = new javax.swing.JLabel();
        lblBC = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel_TablasDeVerdad_Calculadora = new javax.swing.JPanel();
        lblTablasVerdad_Equiv = new javax.swing.JLabel();
        lblTablasVerdad_Resultado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablasDeVerdad_tblRESULTADOS = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnTablasVerdad_S = new javax.swing.JButton();
        btnTablasVerdad_R = new javax.swing.JButton();
        btnTablasVerdad_Q = new javax.swing.JButton();
        btnTablasVerdad_P = new javax.swing.JButton();
        btnTablasVerdad_ParentesisAbierto = new javax.swing.JButton();
        btnTablasVerdad_ParentesisCerrado = new javax.swing.JButton();
        btnTablasVerdad_Bicondicional = new javax.swing.JButton();
        btnTablasVerdad_XOR = new javax.swing.JButton();
        btnTablasVerdad_Igual = new javax.swing.JButton();
        btnTablasVerdad_AND = new javax.swing.JButton();
        btnTablasVerdad_OR = new javax.swing.JButton();
        btnTablasVerdad_Condicional = new javax.swing.JButton();
        btnTablasVerdad_Negacion = new javax.swing.JButton();
        btnTablasVerdad_Equivalente = new javax.swing.JButton();
        btnTablasVerdad_Delete = new javax.swing.JButton();
        btnTablasVerdad_AC = new javax.swing.JButton();
        txtTablasVerdad_Consola = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel_OperacionesConjuntos = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnOPConjuntos_ConjuntoC = new javax.swing.JButton();
        btnOPConjuntos_ConjuntoB = new javax.swing.JButton();
        btnOPConjuntos_ConjuntoA = new javax.swing.JButton();
        btnOPConjuntos_AbreParentesis = new javax.swing.JButton();
        btnOPConjuntos_CierraParentesis = new javax.swing.JButton();
        btnOPConjuntos_ConjuntoPorPartes = new javax.swing.JButton();
        btnOPConjuntos_Igual = new javax.swing.JButton();
        btnOPConjuntos_Complemento = new javax.swing.JButton();
        btnOPConjuntos_Diferencia = new javax.swing.JButton();
        btnOPConjuntos_Interseccion = new javax.swing.JButton();
        btnOPConjuntos_Union = new javax.swing.JButton();
        btnOPConjuntos_Delete = new javax.swing.JButton();
        btnOPConjuntos_AC = new javax.swing.JButton();
        txtOPConjuntos_Consola = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        TextArea_Consola = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        OperacionesConjuntos_txtConjuntoA = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSave_Ca = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        OperacionesConjuntos_txtConjuntoB = new javax.swing.JTextField();
        btnSave_Cb = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        OperacionesConjuntos_txtConjuntoC = new javax.swing.JTextField();
        btnSave_Cc = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        TextArea_C = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        TextArea_A = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        TextArea_B = new javax.swing.JTextArea();
        btnEnabled_CC = new javax.swing.JButton();
        btnEnabled_CB = new javax.swing.JButton();
        btnEnabled_CA = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        btnOPConjuntos_LIMPIAR = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();

        jLabel49.setBackground(new java.awt.Color(52, 55, 56));
        jLabel49.setForeground(new java.awt.Color(52, 55, 56));
        jLabel49.setText("jLabel40");
        jLabel49.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(0, 0, 0)));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_MINIMIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/EXIT_MIN/BTN_Minimizar.png"))); // NOI18N
        BTN_MINIMIZAR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_MINIMIZAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTN_MINIMIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_MINIMIZARActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_MINIMIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 10, 60, 60));

        BTN_CERRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/EXIT_MIN/BTN_Salir.png"))); // NOI18N
        BTN_CERRAR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_CERRAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTN_CERRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CERRARActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_CERRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 10, 60, 60));

        jLabel44.setText("jLabel40");
        jLabel44.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 1270, 10));

        jLabel43.setText("jLabel40");
        jLabel43.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1260, 10));

        jLabel51.setBackground(new java.awt.Color(52, 55, 56));
        jLabel51.setForeground(new java.awt.Color(52, 55, 56));
        jLabel51.setText("jLabel40");
        jLabel51.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 80, 10, 620));

        jPanel_NavBar.setBackground(new java.awt.Color(35, 33, 56));
        jPanel_NavBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(255, 180, 0));
        jButton2.setForeground(new java.awt.Color(255, 178, 0));
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel_NavBar.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 50));

        BTN_NAVBAR_Dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/btn_navbar_dashboard.png"))); // NOI18N
        BTN_NAVBAR_Dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_NAVBAR_Dashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTN_NAVBAR_Dashboard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTN_NAVBAR_Dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BTN_NAVBAR_DashboardMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_NAVBAR_DashboardMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BTN_NAVBAR_DashboardMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BTN_NAVBAR_DashboardMouseEntered(evt);
            }
        });
        BTN_NAVBAR_Dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NAVBAR_DashboardActionPerformed(evt);
            }
        });
        jPanel_NavBar.add(BTN_NAVBAR_Dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 155, 50));

        BTN_NAVBAR_DiagramaVenn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/btn_navbar_diagramavenn.png"))); // NOI18N
        BTN_NAVBAR_DiagramaVenn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_NAVBAR_DiagramaVenn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTN_NAVBAR_DiagramaVenn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTN_NAVBAR_DiagramaVenn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BTN_NAVBAR_DiagramaVennMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BTN_NAVBAR_DiagramaVennMouseEntered(evt);
            }
        });
        BTN_NAVBAR_DiagramaVenn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NAVBAR_DiagramaVennActionPerformed(evt);
            }
        });
        jPanel_NavBar.add(BTN_NAVBAR_DiagramaVenn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 155, 50));

        BTN_NAVBAR_TablasVerdad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/btn_navbar_tblverdad.png"))); // NOI18N
        BTN_NAVBAR_TablasVerdad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_NAVBAR_TablasVerdad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTN_NAVBAR_TablasVerdad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTN_NAVBAR_TablasVerdad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BTN_NAVBAR_TablasVerdadMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BTN_NAVBAR_TablasVerdadMouseEntered(evt);
            }
        });
        BTN_NAVBAR_TablasVerdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NAVBAR_TablasVerdadActionPerformed(evt);
            }
        });
        jPanel_NavBar.add(BTN_NAVBAR_TablasVerdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 155, 50));

        BTN_NAVBAR_OperacionesConjuntos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/btn_navbar_opconjuntos.png"))); // NOI18N
        BTN_NAVBAR_OperacionesConjuntos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_NAVBAR_OperacionesConjuntos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTN_NAVBAR_OperacionesConjuntos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTN_NAVBAR_OperacionesConjuntos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BTN_NAVBAR_OperacionesConjuntosMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BTN_NAVBAR_OperacionesConjuntosMouseEntered(evt);
            }
        });
        BTN_NAVBAR_OperacionesConjuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NAVBAR_OperacionesConjuntosActionPerformed(evt);
            }
        });
        jPanel_NavBar.add(BTN_NAVBAR_OperacionesConjuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 155, 50));

        BTN_NAVBAR_Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/btn_navbar_salir.png"))); // NOI18N
        BTN_NAVBAR_Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_NAVBAR_Salir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTN_NAVBAR_Salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTN_NAVBAR_Salir.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BTN_NAVBAR_SalirMouseMoved(evt);
            }
        });
        BTN_NAVBAR_Salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BTN_NAVBAR_SalirMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BTN_NAVBAR_SalirMouseEntered(evt);
            }
        });
        BTN_NAVBAR_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NAVBAR_SalirActionPerformed(evt);
            }
        });
        jPanel_NavBar.add(BTN_NAVBAR_Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 155, 50));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/img/LogoMDCalculator.png"))); // NOI18N
        jPanel_NavBar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 155, 110));

        getContentPane().add(jPanel_NavBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 155, 610));

        PANEL_VENTANAS.setBackground(new java.awt.Color(39, 40, 68));
        PANEL_VENTANAS.setToolTipText("");
        PANEL_VENTANAS.setEnabled(false);

        jPanel_Dashboard.setBackground(new java.awt.Color(39, 40, 68));
        jPanel_Dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/img/DASHBOARD copia.png"))); // NOI18N
        jPanel_Dashboard.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 1020, 80));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/btnOPConjuntos.png"))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel_Dashboard.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, 320, 400));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/btnDiagramaDeVeen.png"))); // NOI18N
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel_Dashboard.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 320, 400));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/btnTablasDeVerdad.png"))); // NOI18N
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel_Dashboard.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 320, 400));

        PANEL_VENTANAS.addTab("                            ", jPanel_Dashboard);

        jPanel_DiagramaVenn_3Conjuntos.setBackground(new java.awt.Color(39, 40, 68));
        jPanel_DiagramaVenn_3Conjuntos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DV_1Conjunto_btnCalcular1.setText("Calcular");
        DV_1Conjunto_btnCalcular1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DV_1Conjunto_btnCalcular1ActionPerformed(evt);
            }
        });
        jPanel_DiagramaVenn_3Conjuntos.add(DV_1Conjunto_btnCalcular1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 510, 130, 30));

        DV_1Conjunto_btnLimpiar2.setText("Limpiar");
        DV_1Conjunto_btnLimpiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DV_1Conjunto_btnLimpiar2ActionPerformed(evt);
            }
        });
        jPanel_DiagramaVenn_3Conjuntos.add(DV_1Conjunto_btnLimpiar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 510, 130, 30));

        jScrollPane3.setBackground(new java.awt.Color(73, 84, 158));
        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel3.setBackground(new java.awt.Color(73, 84, 158));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txtDiagramaVenn3_Universal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 320, 30));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Valor Universal (U):");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 180, 30));
        jPanel3.add(txtDiagramaVenn3_ConjuntoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 320, 30));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Valor Conjunto B:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 180, 30));
        jPanel3.add(txtDiagramaVenn3_ConjuntoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 320, 30));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Valor Conjunto A:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 180, 30));
        jPanel3.add(txtDiagramaVenn3_ConjuntoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 320, 30));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Valor Conjunto C:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 180, 30));
        jPanel3.add(txtDiagramaVenn3_ValorAuB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 320, 30));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Valor A ∩ B:");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 180, 30));
        jPanel3.add(txtDiagramaVenn3_ValorBuC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 320, 30));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Valor B ∩ C:");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 180, 30));
        jPanel3.add(txtDiagramaVenn3_ValorCuA, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 320, 30));
        jPanel3.add(txtDiagramaVenn3_ValorAuBuC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 320, 30));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Valor C ∩ A:");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 180, 30));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Valor A ∩ B ∩ C:");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 180, 30));

        jScrollPane3.setViewportView(jPanel3);

        jPanel_DiagramaVenn_3Conjuntos.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 360, 350));

        lblUniversal.setBackground(new java.awt.Color(255, 255, 255));
        lblUniversal.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblUniversal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel_DiagramaVenn_3Conjuntos.add(lblUniversal, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, 90, 20));

        lblCA.setBackground(new java.awt.Color(255, 255, 255));
        lblCA.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblCA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel_DiagramaVenn_3Conjuntos.add(lblCA, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 320, 100, 20));

        lblSobrante.setBackground(new java.awt.Color(255, 255, 255));
        lblSobrante.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblSobrante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel_DiagramaVenn_3Conjuntos.add(lblSobrante, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 500, 90, 20));

        lblA.setBackground(new java.awt.Color(255, 255, 255));
        lblA.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel_DiagramaVenn_3Conjuntos.add(lblA, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 250, 90, 20));

        lblC.setBackground(new java.awt.Color(255, 255, 255));
        lblC.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel_DiagramaVenn_3Conjuntos.add(lblC, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 410, 90, 20));

        lblB.setBackground(new java.awt.Color(255, 255, 255));
        lblB.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel_DiagramaVenn_3Conjuntos.add(lblB, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 410, 90, 20));

        lblABC.setBackground(new java.awt.Color(255, 255, 255));
        lblABC.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblABC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel_DiagramaVenn_3Conjuntos.add(lblABC, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 350, 100, 20));

        lblAB.setBackground(new java.awt.Color(255, 255, 255));
        lblAB.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblAB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel_DiagramaVenn_3Conjuntos.add(lblAB, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 320, 100, 20));

        lblBC.setBackground(new java.awt.Color(255, 255, 255));
        lblBC.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblBC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel_DiagramaVenn_3Conjuntos.add(lblBC, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 390, 100, 20));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/img/DV.png"))); // NOI18N
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_DiagramaVenn_3Conjuntos.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, 380, 380));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/img/DV-2.png"))); // NOI18N
        jPanel_DiagramaVenn_3Conjuntos.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 550, 440));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/img/DV-1titulo.png"))); // NOI18N
        jPanel_DiagramaVenn_3Conjuntos.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 1020, 80));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/img/DV-1.png"))); // NOI18N
        jPanel_DiagramaVenn_3Conjuntos.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 440, 440));

        PANEL_VENTANAS.addTab("                            ", jPanel_DiagramaVenn_3Conjuntos);

        jPanel_TablasDeVerdad_Calculadora.setBackground(new java.awt.Color(39, 40, 68));
        jPanel_TablasDeVerdad_Calculadora.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTablasVerdad_Equiv.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_TablasDeVerdad_Calculadora.add(lblTablasVerdad_Equiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 490, 20));

        lblTablasVerdad_Resultado.setBackground(new java.awt.Color(0, 0, 0));
        lblTablasVerdad_Resultado.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_TablasDeVerdad_Calculadora.add(lblTablasVerdad_Resultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 490, 20));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablasDeVerdad_tblRESULTADOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(TablasDeVerdad_tblRESULTADOS);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 470, 330));

        jScrollPane1.setViewportView(jPanel4);

        jPanel_TablasDeVerdad_Calculadora.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 495, 355));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTablasVerdad_S.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_S.png"))); // NOI18N
        btnTablasVerdad_S.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_S.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_SActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_S, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 50, 50));

        btnTablasVerdad_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_R.png"))); // NOI18N
        btnTablasVerdad_R.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_RActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_R, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 50, 50));

        btnTablasVerdad_Q.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_Q.png"))); // NOI18N
        btnTablasVerdad_Q.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_Q.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_QActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_Q, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 50, 50));

        btnTablasVerdad_P.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_P.png"))); // NOI18N
        btnTablasVerdad_P.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_P.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_PActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_P, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 50, 50));

        btnTablasVerdad_ParentesisAbierto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_ParentesisAbierto.png"))); // NOI18N
        btnTablasVerdad_ParentesisAbierto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_ParentesisAbierto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_ParentesisAbiertoActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_ParentesisAbierto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 50, 50));

        btnTablasVerdad_ParentesisCerrado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_ParentesisCerrado.png"))); // NOI18N
        btnTablasVerdad_ParentesisCerrado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_ParentesisCerrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_ParentesisCerradoActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_ParentesisCerrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 50, 50));

        btnTablasVerdad_Bicondicional.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_Bicondicional.png"))); // NOI18N
        btnTablasVerdad_Bicondicional.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_Bicondicional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_BicondicionalActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_Bicondicional, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 50, 50));

        btnTablasVerdad_XOR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_XOR.png"))); // NOI18N
        btnTablasVerdad_XOR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_XOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_XORActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_XOR, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 50, 50));

        btnTablasVerdad_Igual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_IgualV2.png"))); // NOI18N
        btnTablasVerdad_Igual.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_Igual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_IgualActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_Igual, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 50, 230));

        btnTablasVerdad_AND.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_AND.png"))); // NOI18N
        btnTablasVerdad_AND.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_AND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_ANDActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_AND, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 50, 50));

        btnTablasVerdad_OR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_OR.png"))); // NOI18N
        btnTablasVerdad_OR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_OR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_ORActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_OR, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 50, 50));

        btnTablasVerdad_Condicional.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_Condicional.png"))); // NOI18N
        btnTablasVerdad_Condicional.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_Condicional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_CondicionalActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_Condicional, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 50, 50));

        btnTablasVerdad_Negacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_Negacion.png"))); // NOI18N
        btnTablasVerdad_Negacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_Negacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_NegacionActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_Negacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 50, 50));

        btnTablasVerdad_Equivalente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_Equivalente.png"))); // NOI18N
        btnTablasVerdad_Equivalente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_Equivalente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_EquivalenteActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_Equivalente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 50, 50));

        btnTablasVerdad_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_Eliminar.png"))); // NOI18N
        btnTablasVerdad_Delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_DeleteActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 50, 50));

        btnTablasVerdad_AC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_AC.png"))); // NOI18N
        btnTablasVerdad_AC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablasVerdad_AC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasVerdad_ACActionPerformed(evt);
            }
        });
        jPanel5.add(btnTablasVerdad_AC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 110, 50));

        txtTablasVerdad_Consola.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtTablasVerdad_Consola.setForeground(new java.awt.Color(0, 0, 0));
        txtTablasVerdad_Consola.setEnabled(false);
        jPanel5.add(txtTablasVerdad_Consola, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 290, 60));

        jPanel_TablasDeVerdad_Calculadora.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 310, 350));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/img/DV-2.png"))); // NOI18N
        jPanel_TablasDeVerdad_Calculadora.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 550, 440));

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/img/TablasDeVerdad_Titulo.png"))); // NOI18N
        jPanel_TablasDeVerdad_Calculadora.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 1020, 80));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/img/DV-1.png"))); // NOI18N
        jPanel_TablasDeVerdad_Calculadora.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 440, 440));

        PANEL_VENTANAS.addTab("                            ", jPanel_TablasDeVerdad_Calculadora);

        jPanel_OperacionesConjuntos.setBackground(new java.awt.Color(39, 40, 68));
        jPanel_OperacionesConjuntos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOPConjuntos_ConjuntoC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_C.png"))); // NOI18N
        btnOPConjuntos_ConjuntoC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOPConjuntos_ConjuntoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_ConjuntoCActionPerformed(evt);
            }
        });
        jPanel6.add(btnOPConjuntos_ConjuntoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 50, 50));

        btnOPConjuntos_ConjuntoB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_B.png"))); // NOI18N
        btnOPConjuntos_ConjuntoB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOPConjuntos_ConjuntoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_ConjuntoBActionPerformed(evt);
            }
        });
        jPanel6.add(btnOPConjuntos_ConjuntoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 50, 50));

        btnOPConjuntos_ConjuntoA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_A.png"))); // NOI18N
        btnOPConjuntos_ConjuntoA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOPConjuntos_ConjuntoA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_ConjuntoAActionPerformed(evt);
            }
        });
        jPanel6.add(btnOPConjuntos_ConjuntoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 50, 50));

        btnOPConjuntos_AbreParentesis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_ParentesisAbierto.png"))); // NOI18N
        btnOPConjuntos_AbreParentesis.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOPConjuntos_AbreParentesis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_AbreParentesisActionPerformed(evt);
            }
        });
        jPanel6.add(btnOPConjuntos_AbreParentesis, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 50, 50));

        btnOPConjuntos_CierraParentesis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_ParentesisCerrado.png"))); // NOI18N
        btnOPConjuntos_CierraParentesis.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOPConjuntos_CierraParentesis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_CierraParentesisActionPerformed(evt);
            }
        });
        jPanel6.add(btnOPConjuntos_CierraParentesis, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 50, 50));

        btnOPConjuntos_ConjuntoPorPartes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_ProductoCartesiano.png"))); // NOI18N
        btnOPConjuntos_ConjuntoPorPartes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOPConjuntos_ConjuntoPorPartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_ConjuntoPorPartesActionPerformed(evt);
            }
        });
        jPanel6.add(btnOPConjuntos_ConjuntoPorPartes, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 50, 50));

        btnOPConjuntos_Igual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_Igual.png"))); // NOI18N
        btnOPConjuntos_Igual.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOPConjuntos_Igual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_IgualActionPerformed(evt);
            }
        });
        jPanel6.add(btnOPConjuntos_Igual, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 50, 110));

        btnOPConjuntos_Complemento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_Complemento.png"))); // NOI18N
        btnOPConjuntos_Complemento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOPConjuntos_Complemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_ComplementoActionPerformed(evt);
            }
        });
        jPanel6.add(btnOPConjuntos_Complemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 50, 50));

        btnOPConjuntos_Diferencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_Diferencial.png"))); // NOI18N
        btnOPConjuntos_Diferencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOPConjuntos_Diferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_DiferenciaActionPerformed(evt);
            }
        });
        jPanel6.add(btnOPConjuntos_Diferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 50, 50));

        btnOPConjuntos_Interseccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_Interseccion.png"))); // NOI18N
        btnOPConjuntos_Interseccion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOPConjuntos_Interseccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_InterseccionActionPerformed(evt);
            }
        });
        jPanel6.add(btnOPConjuntos_Interseccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 50, 50));

        btnOPConjuntos_Union.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_Union.png"))); // NOI18N
        btnOPConjuntos_Union.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOPConjuntos_Union.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_UnionActionPerformed(evt);
            }
        });
        jPanel6.add(btnOPConjuntos_Union, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 50, 50));

        btnOPConjuntos_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_EliminarV2.png"))); // NOI18N
        btnOPConjuntos_Delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOPConjuntos_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_DeleteActionPerformed(evt);
            }
        });
        jPanel6.add(btnOPConjuntos_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 110, 50));

        btnOPConjuntos_AC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_AC.png"))); // NOI18N
        btnOPConjuntos_AC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOPConjuntos_AC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_ACActionPerformed(evt);
            }
        });
        jPanel6.add(btnOPConjuntos_AC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 110, 50));

        txtOPConjuntos_Consola.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtOPConjuntos_Consola.setForeground(new java.awt.Color(0, 0, 0));
        txtOPConjuntos_Consola.setEnabled(false);
        jPanel6.add(txtOPConjuntos_Consola, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 230, 60));

        TextArea_Consola.setColumns(20);
        TextArea_Consola.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        TextArea_Consola.setRows(5);
        TextArea_Consola.setEnabled(false);
        jScrollPane5.setViewportView(TextArea_Consola);

        jPanel6.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 250, 310));

        jPanel_OperacionesConjuntos.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 510, 350));

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        OperacionesConjuntos_txtConjuntoA.setEnabled(false);
        jPanel9.add(OperacionesConjuntos_txtConjuntoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 170, 30));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Conjunto C:");
        jPanel9.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 110, -1));

        btnSave_Ca.setText("Dato: 1/0");
        btnSave_Ca.setEnabled(false);
        btnSave_Ca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave_CaActionPerformed(evt);
            }
        });
        jPanel9.add(btnSave_Ca, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 90, 30));

        jLabel23.setText("Conjunto B:");
        jPanel9.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 170, -1));

        OperacionesConjuntos_txtConjuntoB.setEnabled(false);
        jPanel9.add(OperacionesConjuntos_txtConjuntoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, 30));

        btnSave_Cb.setText("Dato: 1/0");
        btnSave_Cb.setEnabled(false);
        btnSave_Cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave_CbActionPerformed(evt);
            }
        });
        jPanel9.add(btnSave_Cb, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 90, 30));

        jLabel25.setText("Conjunto C:");
        jPanel9.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 170, -1));

        OperacionesConjuntos_txtConjuntoC.setEnabled(false);
        jPanel9.add(OperacionesConjuntos_txtConjuntoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 170, 30));

        btnSave_Cc.setText("Dato: 1/0");
        btnSave_Cc.setEnabled(false);
        btnSave_Cc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave_CcActionPerformed(evt);
            }
        });
        jPanel9.add(btnSave_Cc, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 90, 30));

        jScrollPane6.setEnabled(false);

        TextArea_C.setColumns(20);
        TextArea_C.setRows(5);
        TextArea_C.setEnabled(false);
        jScrollPane6.setViewportView(TextArea_C);

        jPanel9.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 110, 120));

        TextArea_A.setColumns(20);
        TextArea_A.setRows(5);
        TextArea_A.setEnabled(false);
        jScrollPane7.setViewportView(TextArea_A);

        jPanel9.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 110, 120));

        TextArea_B.setColumns(20);
        TextArea_B.setRows(5);
        TextArea_B.setEnabled(false);
        jScrollPane8.setViewportView(TextArea_B);

        jPanel9.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 110, 120));

        btnEnabled_CC.setText("Enabled");
        btnEnabled_CC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnabled_CCActionPerformed(evt);
            }
        });
        jPanel9.add(btnEnabled_CC, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 90, 30));

        btnEnabled_CB.setText("Enabled");
        btnEnabled_CB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnabled_CBActionPerformed(evt);
            }
        });
        jPanel9.add(btnEnabled_CB, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 90, 30));

        btnEnabled_CA.setText("Enabled");
        btnEnabled_CA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnabled_CAActionPerformed(evt);
            }
        });
        jPanel9.add(btnEnabled_CA, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 90, 30));

        jLabel26.setText("Conjunto A:");
        jPanel9.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 170, -1));

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Conjunto A:");
        jPanel9.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 110, -1));

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Conjunto B:");
        jPanel9.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 110, -1));

        jPanel_OperacionesConjuntos.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 390, 350));

        btnOPConjuntos_LIMPIAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/CalculadoraTablasDeVerdad/btn_Limpiar.png"))); // NOI18N
        btnOPConjuntos_LIMPIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOPConjuntos_LIMPIARActionPerformed(evt);
            }
        });
        jPanel_OperacionesConjuntos.add(btnOPConjuntos_LIMPIAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 510, 100, 40));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/img/DV-2.png"))); // NOI18N
        jPanel_OperacionesConjuntos.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 550, 440));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/img/DV-1.png"))); // NOI18N
        jPanel_OperacionesConjuntos.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 440, 440));

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/img/OPERACIONES DE CONJUNTOS Titulo.png"))); // NOI18N
        jPanel_OperacionesConjuntos.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 1020, 80));

        PANEL_VENTANAS.addTab("                            ", jPanel_OperacionesConjuntos);

        getContentPane().add(PANEL_VENTANAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 1100, 620));

        jPanel1.setBackground(new java.awt.Color(39, 40, 68));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1250, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 1250, 610));

        jLabel52.setBackground(new java.awt.Color(52, 55, 56));
        jLabel52.setForeground(new java.awt.Color(52, 55, 56));
        jLabel52.setText("jLabel40");
        jLabel52.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 10, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // TERMINA RELLENO
    
    
    
    
    
    
    
    
    private void BTN_CERRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CERRARActionPerformed
    int RESPUESTA = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas salir?", "¡Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        System.out.println("RESPUESTA: " + RESPUESTA);
        // SI = 0, NO = 1
        
        if (RESPUESTA == 0) {
            System.out.println("SE CERRO UI_MD");
            System.exit(0);
        } else {
            System.out.println("NO SE CERRO UI_MD");
        }
        
    }//GEN-LAST:event_BTN_CERRARActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BTN_MINIMIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_MINIMIZARActionPerformed
        setExtendedState(JFrame.CROSSHAIR_CURSOR);
    }//GEN-LAST:event_BTN_MINIMIZARActionPerformed

    private void BTN_NAVBAR_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_SalirActionPerformed
        int RESPUESTA = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas salir?", "¡Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        System.out.println("RESPUESTA: " + RESPUESTA);
        // SI = 0, NO = 1
        
        if (RESPUESTA == 0) {
            System.out.println("SE CERRO UI_MD");
            System.exit(0);
        } else {
            System.out.println("NO SE CERRO UI_MD");
        }
    }//GEN-LAST:event_BTN_NAVBAR_SalirActionPerformed

    private void BTN_NAVBAR_DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_DashboardActionPerformed
        PANEL_VENTANAS.setSelectedIndex(0);
        
        
    }//GEN-LAST:event_BTN_NAVBAR_DashboardActionPerformed

    private void BTN_NAVBAR_DiagramaVennActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_DiagramaVennActionPerformed
        PANEL_VENTANAS.setSelectedIndex(1);
    }//GEN-LAST:event_BTN_NAVBAR_DiagramaVennActionPerformed

    private void BTN_NAVBAR_TablasVerdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_TablasVerdadActionPerformed
        PANEL_VENTANAS.setSelectedIndex(2);
    }//GEN-LAST:event_BTN_NAVBAR_TablasVerdadActionPerformed

    private void BTN_NAVBAR_OperacionesConjuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_OperacionesConjuntosActionPerformed
        PANEL_VENTANAS.setSelectedIndex(3);
    }//GEN-LAST:event_BTN_NAVBAR_OperacionesConjuntosActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        PX = evt.getX();
        PY = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int Posicion_X = evt.getXOnScreen() - PX;
        int Posicion_Y = evt.getYOnScreen() - PY;
        this.setLocation(Posicion_X, Posicion_Y);
    }//GEN-LAST:event_formMouseDragged

    private void BTN_NAVBAR_SalirMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_SalirMouseMoved
        
    }//GEN-LAST:event_BTN_NAVBAR_SalirMouseMoved

    private void BTN_NAVBAR_SalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_SalirMouseExited
        BTN_NAVBAR_Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/btn_navbar_salir.png")));
    }//GEN-LAST:event_BTN_NAVBAR_SalirMouseExited

    private void BTN_NAVBAR_SalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_SalirMouseEntered
        BTN_NAVBAR_Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/hover/btn_navbar_salir.png")));
    }//GEN-LAST:event_BTN_NAVBAR_SalirMouseEntered

    private void BTN_NAVBAR_DashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_DashboardMouseEntered
        BTN_NAVBAR_Dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/hover/btn_navbar_dashboard.png")));
    }//GEN-LAST:event_BTN_NAVBAR_DashboardMouseEntered

    private void BTN_NAVBAR_DashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_DashboardMouseExited
        BTN_NAVBAR_Dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/btn_navbar_dashboard.png")));
    }//GEN-LAST:event_BTN_NAVBAR_DashboardMouseExited

    private void BTN_NAVBAR_DiagramaVennMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_DiagramaVennMouseEntered
        BTN_NAVBAR_DiagramaVenn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/hover/btn_navbar_diagramavenn.png")));
    }//GEN-LAST:event_BTN_NAVBAR_DiagramaVennMouseEntered

    private void BTN_NAVBAR_DiagramaVennMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_DiagramaVennMouseExited
        BTN_NAVBAR_DiagramaVenn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/btn_navbar_diagramavenn.png")));
    }//GEN-LAST:event_BTN_NAVBAR_DiagramaVennMouseExited

    private void BTN_NAVBAR_TablasVerdadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_TablasVerdadMouseEntered
        BTN_NAVBAR_TablasVerdad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/hover/btn_navbar_tblverdad.png")));
    }//GEN-LAST:event_BTN_NAVBAR_TablasVerdadMouseEntered

    private void BTN_NAVBAR_TablasVerdadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_TablasVerdadMouseExited
        BTN_NAVBAR_TablasVerdad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/btn_navbar_tblverdad.png")));
    }//GEN-LAST:event_BTN_NAVBAR_TablasVerdadMouseExited

    private void BTN_NAVBAR_OperacionesConjuntosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_OperacionesConjuntosMouseEntered
        BTN_NAVBAR_OperacionesConjuntos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/hover/btn_navbar_opconjuntos.png")));
    }//GEN-LAST:event_BTN_NAVBAR_OperacionesConjuntosMouseEntered

    private void BTN_NAVBAR_OperacionesConjuntosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_OperacionesConjuntosMouseExited
        BTN_NAVBAR_OperacionesConjuntos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/btn/normal/btn_navbar_opconjuntos.png")));
    }//GEN-LAST:event_BTN_NAVBAR_OperacionesConjuntosMouseExited

    private void BTN_NAVBAR_DashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_DashboardMouseClicked

    }//GEN-LAST:event_BTN_NAVBAR_DashboardMouseClicked

    private void BTN_NAVBAR_DashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_NAVBAR_DashboardMousePressed

    }//GEN-LAST:event_BTN_NAVBAR_DashboardMousePressed

    private void btnTablasVerdad_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_DeleteActionPerformed
        LIMPIAR_TABLA();
        TABLAS_DE_VERDAD_BTN_DELELTE();
    }//GEN-LAST:event_btnTablasVerdad_DeleteActionPerformed

    private void btnTablasVerdad_EquivalenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_EquivalenteActionPerformed
        txtTablasVerdad_Consola.setText(txtTablasVerdad_Consola.getText()+"≡");
    }//GEN-LAST:event_btnTablasVerdad_EquivalenteActionPerformed

    private void btnTablasVerdad_NegacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_NegacionActionPerformed
        txtTablasVerdad_Consola.setText(txtTablasVerdad_Consola.getText()+"¬");
    }//GEN-LAST:event_btnTablasVerdad_NegacionActionPerformed

    private void btnTablasVerdad_CondicionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_CondicionalActionPerformed
        txtTablasVerdad_Consola.setText(txtTablasVerdad_Consola.getText()+"→");
    }//GEN-LAST:event_btnTablasVerdad_CondicionalActionPerformed

    private void btnTablasVerdad_ANDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_ANDActionPerformed
        txtTablasVerdad_Consola.setText(txtTablasVerdad_Consola.getText()+"∧");
    }//GEN-LAST:event_btnTablasVerdad_ANDActionPerformed

    private void btnTablasVerdad_ORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_ORActionPerformed
        txtTablasVerdad_Consola.setText(txtTablasVerdad_Consola.getText()+"∨");
    }//GEN-LAST:event_btnTablasVerdad_ORActionPerformed

    private void btnTablasVerdad_ParentesisAbiertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_ParentesisAbiertoActionPerformed
        txtTablasVerdad_Consola.setText(txtTablasVerdad_Consola.getText()+"(");
    }//GEN-LAST:event_btnTablasVerdad_ParentesisAbiertoActionPerformed

    private void btnTablasVerdad_ParentesisCerradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_ParentesisCerradoActionPerformed
        txtTablasVerdad_Consola.setText(txtTablasVerdad_Consola.getText()+")");
    }//GEN-LAST:event_btnTablasVerdad_ParentesisCerradoActionPerformed

    private void btnTablasVerdad_BicondicionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_BicondicionalActionPerformed
        txtTablasVerdad_Consola.setText(txtTablasVerdad_Consola.getText()+"↔");
    }//GEN-LAST:event_btnTablasVerdad_BicondicionalActionPerformed

    private void btnTablasVerdad_XORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_XORActionPerformed
        txtTablasVerdad_Consola.setText(txtTablasVerdad_Consola.getText()+"⊻");
    }//GEN-LAST:event_btnTablasVerdad_XORActionPerformed

    private void btnTablasVerdad_IgualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_IgualActionPerformed
        LIMPIAR_TABLA();
        VALIDAR_EXPRESION_TABLAS_DE_VERDAD();
    }//GEN-LAST:event_btnTablasVerdad_IgualActionPerformed

    private void btnTablasVerdad_SActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_SActionPerformed
        txtTablasVerdad_Consola.setText(txtTablasVerdad_Consola.getText()+"s");
    }//GEN-LAST:event_btnTablasVerdad_SActionPerformed

    private void btnTablasVerdad_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_RActionPerformed
        txtTablasVerdad_Consola.setText(txtTablasVerdad_Consola.getText()+"r");
    }//GEN-LAST:event_btnTablasVerdad_RActionPerformed

    private void btnTablasVerdad_QActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_QActionPerformed
        txtTablasVerdad_Consola.setText(txtTablasVerdad_Consola.getText()+"q");
    }//GEN-LAST:event_btnTablasVerdad_QActionPerformed

    private void btnTablasVerdad_PActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_PActionPerformed
        txtTablasVerdad_Consola.setText(txtTablasVerdad_Consola.getText()+"p");
    }//GEN-LAST:event_btnTablasVerdad_PActionPerformed

    private void btnTablasVerdad_ACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasVerdad_ACActionPerformed
        LIMPIAR_CAMPOSTXT_TABLAS_VERDAD();
        
    }//GEN-LAST:event_btnTablasVerdad_ACActionPerformed

    
    
    private void btnOPConjuntos_ConjuntoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_ConjuntoCActionPerformed
        txtOPConjuntos_Consola.setText(txtOPConjuntos_Consola.getText()+"C");
    }//GEN-LAST:event_btnOPConjuntos_ConjuntoCActionPerformed

    private void btnOPConjuntos_ConjuntoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_ConjuntoBActionPerformed
        txtOPConjuntos_Consola.setText(txtOPConjuntos_Consola.getText()+"B");
    }//GEN-LAST:event_btnOPConjuntos_ConjuntoBActionPerformed

    private void btnOPConjuntos_ConjuntoAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_ConjuntoAActionPerformed
        txtOPConjuntos_Consola.setText(txtOPConjuntos_Consola.getText()+"A");
    }//GEN-LAST:event_btnOPConjuntos_ConjuntoAActionPerformed

    private void btnOPConjuntos_AbreParentesisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_AbreParentesisActionPerformed
        txtOPConjuntos_Consola.setText(txtOPConjuntos_Consola.getText()+"(");
    }//GEN-LAST:event_btnOPConjuntos_AbreParentesisActionPerformed

    private void btnOPConjuntos_CierraParentesisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_CierraParentesisActionPerformed
        txtOPConjuntos_Consola.setText(txtOPConjuntos_Consola.getText()+")");
    }//GEN-LAST:event_btnOPConjuntos_CierraParentesisActionPerformed

    private void btnOPConjuntos_ConjuntoPorPartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_ConjuntoPorPartesActionPerformed
        txtOPConjuntos_Consola.setText(txtOPConjuntos_Consola.getText()+"×");
    }//GEN-LAST:event_btnOPConjuntos_ConjuntoPorPartesActionPerformed

    private void btnOPConjuntos_IgualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_IgualActionPerformed
        VALIDAR_INFO_OP_CONJUNTOS();
    }//GEN-LAST:event_btnOPConjuntos_IgualActionPerformed

    private void btnOPConjuntos_ComplementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_ComplementoActionPerformed
        txtOPConjuntos_Consola.setText(txtOPConjuntos_Consola.getText()+"´");
    }//GEN-LAST:event_btnOPConjuntos_ComplementoActionPerformed

    private void btnOPConjuntos_DiferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_DiferenciaActionPerformed
        txtOPConjuntos_Consola.setText(txtOPConjuntos_Consola.getText()+"-");
    }//GEN-LAST:event_btnOPConjuntos_DiferenciaActionPerformed

    private void btnOPConjuntos_InterseccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_InterseccionActionPerformed
        txtOPConjuntos_Consola.setText(txtOPConjuntos_Consola.getText()+"∩");
    }//GEN-LAST:event_btnOPConjuntos_InterseccionActionPerformed

    private void btnOPConjuntos_UnionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_UnionActionPerformed
        txtOPConjuntos_Consola.setText(txtOPConjuntos_Consola.getText()+"∪");
    }//GEN-LAST:event_btnOPConjuntos_UnionActionPerformed

    private void btnOPConjuntos_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_DeleteActionPerformed
        OP_CONJUNTOS_BTN_DELELTE();       
    }//GEN-LAST:event_btnOPConjuntos_DeleteActionPerformed

    private void btnOPConjuntos_ACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_ACActionPerformed
        LIMPIAR_CAMPOSTXT_OP_CONJUNTOS();
    }//GEN-LAST:event_btnOPConjuntos_ACActionPerformed

    private void btnEnabled_CAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnabled_CAActionPerformed
        boolean LEL = true;
        while (LEL) {            
            String INPUT = JOptionPane.showInputDialog("Ingresa el número de valores del Conjunto A:");
            try {
                int N_VALORES = Integer.parseInt(INPUT);
                Muestra.ConjuntoCa(N_VALORES);
                btnSave_Ca.setText("Dato: 1/" + Muestra.LongA());
                btnSave_Ca.setEnabled(true);
                btnEnabled_CA.setEnabled(false);
                OperacionesConjuntos_txtConjuntoA.setEnabled(true);
                OperacionesConjuntos_txtConjuntoA.requestFocus();
                LEL = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Ingresa un dato de tipo númerico");
                System.out.println(e);
            }
        }
        
    }//GEN-LAST:event_btnEnabled_CAActionPerformed

    private void btnSave_CaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave_CaActionPerformed
        String DATO = OperacionesConjuntos_txtConjuntoA.getText().trim();
        Muestra.Ca(DATO,cont - 1);
        OperacionesConjuntos_txtConjuntoA.setText(null);
        OperacionesConjuntos_txtConjuntoA.requestFocus();
        cont++;
        if (cont > Muestra.LongA()) {
            for (int i = 1; i <= Muestra.LongA(); i++) {
                OperacionesConjuntos_txtConjuntoA.setText(OperacionesConjuntos_txtConjuntoA.getText() + Muestra.Ca(i-1) + ";");
            }
            TextArea_A.setText("{" + OperacionesConjuntos_txtConjuntoA.getText() + "}");
            OperacionesConjuntos_txtConjuntoA.setText(null);
            cont = 1;
            btnSave_Ca.setEnabled(false);
        } else {
            btnSave_Ca.setText("Dato: " + cont + "/" + Muestra.LongA());
        }
        
    }//GEN-LAST:event_btnSave_CaActionPerformed

    private void btnOPConjuntos_LIMPIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOPConjuntos_LIMPIARActionPerformed
        LIMPIAR_CAMPOS_OPCONJUNTOS();
    }//GEN-LAST:event_btnOPConjuntos_LIMPIARActionPerformed

    private void btnEnabled_CBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnabled_CBActionPerformed
        boolean LEL = true;
        while (LEL) {            
            String INPUT = JOptionPane.showInputDialog("Ingresa el número de valores del Conjunto B:");
            try {
                int N_VALORES = Integer.parseInt(INPUT);
                Muestra.ConjuntoCb(N_VALORES);
                btnSave_Cb.setText("Dato: 1/" + Muestra.LongB());
                btnSave_Cb.setEnabled(true);
                btnEnabled_CB.setEnabled(false);
                OperacionesConjuntos_txtConjuntoB.setEnabled(true);
                OperacionesConjuntos_txtConjuntoB.requestFocus();
                LEL = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Ingresa un dato de tipo númerico");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnEnabled_CBActionPerformed

    private void btnSave_CbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave_CbActionPerformed
        String DATO = OperacionesConjuntos_txtConjuntoB.getText().trim();
        Muestra.Cb(DATO,cont - 1);
        OperacionesConjuntos_txtConjuntoB.setText(null);
        OperacionesConjuntos_txtConjuntoB.requestFocus();
        cont++;
        if (cont > Muestra.LongB()) {
            for (int i = 1; i <= Muestra.LongB(); i++) {
                OperacionesConjuntos_txtConjuntoB.setText(OperacionesConjuntos_txtConjuntoB.getText() + Muestra.Cb(i-1) + ";");
            }
            TextArea_B.setText("{" + OperacionesConjuntos_txtConjuntoB.getText() + "}");
            OperacionesConjuntos_txtConjuntoB.setText(null);
            cont = 1;
            btnSave_Cb.setEnabled(false);
        } else {
            btnSave_Cb.setText("Dato: " + cont + "/" + Muestra.LongB());
        }
    }//GEN-LAST:event_btnSave_CbActionPerformed

    private void btnEnabled_CCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnabled_CCActionPerformed
        boolean LEL = true;
        while (LEL) {            
            String INPUT = JOptionPane.showInputDialog("Ingresa el número de valores del Conjunto C:");
            try {
                int N_VALORES = Integer.parseInt(INPUT);
                Muestra.ConjuntoCc(N_VALORES);
                btnSave_Cc.setText("Dato: 1/" + Muestra.LongC());
                btnSave_Cc.setEnabled(true);
                btnEnabled_CC.setEnabled(false);
                OperacionesConjuntos_txtConjuntoC.setEnabled(true);
                OperacionesConjuntos_txtConjuntoC.requestFocus();
                LEL = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Ingresa un dato de tipo númerico");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnEnabled_CCActionPerformed

    private void btnSave_CcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave_CcActionPerformed
        String DATO = OperacionesConjuntos_txtConjuntoC.getText().trim();
        Muestra.Cc(DATO,cont - 1);
        OperacionesConjuntos_txtConjuntoC.setText(null);
        OperacionesConjuntos_txtConjuntoC.requestFocus();
        cont++;
        if (cont > Muestra.LongC()) {
            for (int i = 1; i <= Muestra.LongC(); i++) {
                OperacionesConjuntos_txtConjuntoC.setText(OperacionesConjuntos_txtConjuntoC.getText() + Muestra.Cc(i-1) + ";");
            }
            TextArea_C.setText("{" + OperacionesConjuntos_txtConjuntoC.getText() + "}");
            OperacionesConjuntos_txtConjuntoC.setText(null);
            cont = 1;
            btnSave_Cc.setEnabled(false);
        } else {
            btnSave_Cc.setText("Dato: " + cont + "/" + Muestra.LongC());
        }
    }//GEN-LAST:event_btnSave_CcActionPerformed

    private void DV_1Conjunto_btnCalcular1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DV_1Conjunto_btnCalcular1ActionPerformed
        CALCULAR_DIAGRAMA_VENN();
    }//GEN-LAST:event_DV_1Conjunto_btnCalcular1ActionPerformed

    private void DV_1Conjunto_btnLimpiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DV_1Conjunto_btnLimpiar2ActionPerformed
        LIMPIAR_DATOS_DIAGRAMA_VENN();
    }//GEN-LAST:event_DV_1Conjunto_btnLimpiar2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        PANEL_VENTANAS.setSelectedIndex(1);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        PANEL_VENTANAS.setSelectedIndex(2);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PANEL_VENTANAS.setSelectedIndex(3);
    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI_MD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI_MD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI_MD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI_MD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_MD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_CERRAR;
    private javax.swing.JButton BTN_MINIMIZAR;
    private javax.swing.JButton BTN_NAVBAR_Dashboard;
    private javax.swing.JButton BTN_NAVBAR_DiagramaVenn;
    private javax.swing.JButton BTN_NAVBAR_OperacionesConjuntos;
    private javax.swing.JButton BTN_NAVBAR_Salir;
    private javax.swing.JButton BTN_NAVBAR_TablasVerdad;
    private javax.swing.JButton DV_1Conjunto_btnCalcular1;
    private javax.swing.JButton DV_1Conjunto_btnLimpiar2;
    private javax.swing.JTextField OperacionesConjuntos_txtConjuntoA;
    private javax.swing.JTextField OperacionesConjuntos_txtConjuntoB;
    private javax.swing.JTextField OperacionesConjuntos_txtConjuntoC;
    private javax.swing.JTabbedPane PANEL_VENTANAS;
    private javax.swing.JTable TablasDeVerdad_tblRESULTADOS;
    private javax.swing.JTextArea TextArea_A;
    private javax.swing.JTextArea TextArea_B;
    private javax.swing.JTextArea TextArea_C;
    private javax.swing.JTextArea TextArea_Consola;
    private javax.swing.JButton btnEnabled_CA;
    private javax.swing.JButton btnEnabled_CB;
    private javax.swing.JButton btnEnabled_CC;
    private javax.swing.JButton btnOPConjuntos_AC;
    private javax.swing.JButton btnOPConjuntos_AbreParentesis;
    private javax.swing.JButton btnOPConjuntos_CierraParentesis;
    private javax.swing.JButton btnOPConjuntos_Complemento;
    private javax.swing.JButton btnOPConjuntos_ConjuntoA;
    private javax.swing.JButton btnOPConjuntos_ConjuntoB;
    private javax.swing.JButton btnOPConjuntos_ConjuntoC;
    private javax.swing.JButton btnOPConjuntos_ConjuntoPorPartes;
    private javax.swing.JButton btnOPConjuntos_Delete;
    private javax.swing.JButton btnOPConjuntos_Diferencia;
    private javax.swing.JButton btnOPConjuntos_Igual;
    private javax.swing.JButton btnOPConjuntos_Interseccion;
    private javax.swing.JButton btnOPConjuntos_LIMPIAR;
    private javax.swing.JButton btnOPConjuntos_Union;
    private javax.swing.JButton btnSave_Ca;
    private javax.swing.JButton btnSave_Cb;
    private javax.swing.JButton btnSave_Cc;
    private javax.swing.JButton btnTablasVerdad_AC;
    private javax.swing.JButton btnTablasVerdad_AND;
    private javax.swing.JButton btnTablasVerdad_Bicondicional;
    private javax.swing.JButton btnTablasVerdad_Condicional;
    private javax.swing.JButton btnTablasVerdad_Delete;
    private javax.swing.JButton btnTablasVerdad_Equivalente;
    private javax.swing.JButton btnTablasVerdad_Igual;
    private javax.swing.JButton btnTablasVerdad_Negacion;
    private javax.swing.JButton btnTablasVerdad_OR;
    private javax.swing.JButton btnTablasVerdad_P;
    private javax.swing.JButton btnTablasVerdad_ParentesisAbierto;
    private javax.swing.JButton btnTablasVerdad_ParentesisCerrado;
    private javax.swing.JButton btnTablasVerdad_Q;
    private javax.swing.JButton btnTablasVerdad_R;
    private javax.swing.JButton btnTablasVerdad_S;
    private javax.swing.JButton btnTablasVerdad_XOR;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_Dashboard;
    private javax.swing.JPanel jPanel_DiagramaVenn_3Conjuntos;
    private javax.swing.JPanel jPanel_NavBar;
    private javax.swing.JPanel jPanel_OperacionesConjuntos;
    private javax.swing.JPanel jPanel_TablasDeVerdad_Calculadora;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblA;
    private javax.swing.JLabel lblAB;
    private javax.swing.JLabel lblABC;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblBC;
    private javax.swing.JLabel lblC;
    private javax.swing.JLabel lblCA;
    private javax.swing.JLabel lblSobrante;
    public javax.swing.JLabel lblTablasVerdad_Equiv;
    private javax.swing.JLabel lblTablasVerdad_Resultado;
    private javax.swing.JLabel lblUniversal;
    private javax.swing.JTextField txtDiagramaVenn3_ConjuntoA;
    private javax.swing.JTextField txtDiagramaVenn3_ConjuntoB;
    private javax.swing.JTextField txtDiagramaVenn3_ConjuntoC;
    private javax.swing.JTextField txtDiagramaVenn3_Universal;
    private javax.swing.JTextField txtDiagramaVenn3_ValorAuB;
    private javax.swing.JTextField txtDiagramaVenn3_ValorAuBuC;
    private javax.swing.JTextField txtDiagramaVenn3_ValorBuC;
    private javax.swing.JTextField txtDiagramaVenn3_ValorCuA;
    private javax.swing.JTextField txtOPConjuntos_Consola;
    private javax.swing.JTextField txtTablasVerdad_Consola;
    // End of variables declaration//GEN-END:variables


}
