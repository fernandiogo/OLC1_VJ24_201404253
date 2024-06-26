/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package olc1_vj24_201404253;

import abstracto.Instruccion;
import analisis.parser;
import analisis.scanner;
import excepciones.Errores;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import simbolo.Arbol;
import simbolo.tablaSimbolos;

/**
 *
 * @author Diego
 */
public class P_Inicio extends javax.swing.JFrame {

    /**
     * Creates new form P_Inicio
     */
    public P_Inicio() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        Archivo = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        Pestañas = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        Ejecutar = new javax.swing.JMenu();
        Reportes = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTabbedPane1.addTab("tab1", jScrollPane1);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        Archivo.setText("Archivo");

        jMenu2.setText("Abrir");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        Archivo.add(jMenu2);

        jMenu3.setText("Guardar");
        Archivo.add(jMenu3);

        jMenuBar1.add(Archivo);

        Pestañas.setText("Pestañas");

        jMenu4.setText("jMenu4");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        Pestañas.add(jMenu4);

        jMenuBar1.add(Pestañas);

        Ejecutar.setText("Ejecutar");
        Ejecutar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EjecutarMouseClicked(evt);
            }
        });
        jMenuBar1.add(Ejecutar);

        Reportes.setText("Reportes");
        Reportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReportesMouseClicked(evt);
            }
        });
        jMenuBar1.add(Reportes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
        JFileChooser elige= new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.DF","df");
        elige.setFileFilter (filtro);
        int seleccion=elige.showOpenDialog(this);
        
        if(seleccion==JFileChooser.APPROVE_OPTION){
            File fichero = elige.getSelectedFile();
            try (FileReader fr = new FileReader(fichero)){
                String cadena = "";
                int valor = fr.read();
                while (valor != -1){
                    cadena = cadena + (char)valor;
                    valor = fr.read();
                }
                /*agregar el texto al textarea
                int indice = jTabbedPane1.getSelectedComponent();
                //jTabbedPane1.remove(indice);
                */
                //int indice = jTabbedPane1.getSelectedComponent();
                jTextArea1.setText(cadena);
                
                
            } catch (IOException el) {
                el.printStackTrace();
            }
        }
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        // TODO add your handling code here:
        String nombrePestaña = JOptionPane.showInputDialog(null, "Escriba el nombre la pestaña:", "New Tab", JOptionPane.INFORMATION_MESSAGE);
        UIManager.put("OptionPane.okButtonText", "Ok");
        UIManager.put("OptionPane.cancelButtonText", "Cancel");
        
        if (nombrePestaña!=null){
            
            JLabel tituloPestaña = new JLabel(nombrePestaña);
            JTextArea areaDeTexto = new JTextArea();
            
            jTabbedPane1.addTab(nombrePestaña, areaDeTexto);
            jTabbedPane1.setTabComponentAt(jTabbedPane1.getTabCount()-1, tituloPestaña);
        }
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void EjecutarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EjecutarMouseClicked
        // TODO add your handling code here:
        try{
            String texto = jTextArea1.getText();
            scanner s = new scanner(new BufferedReader(new StringReader(texto)));
            parser p = new parser(s);
            var resultado = p.parse();
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new tablaSimbolos();
            tabla.setNombre("Global");
            ast.setConsola("");
            LinkedList<Errores> listaGlobal = new LinkedList<>();
            listaGlobal.addAll(s.listaErrores);
            listaGlobal.addAll(p.listaErrores);
            for(var a : ast.getInstrucciones()){
                if(a == null){
                    continue;
                }
                var res = a.interpretar(ast, tabla);
                if(res instanceof Errores){
                    listaGlobal.add((Errores)res);
                }
            }
            jTextArea3.setText(ast.getConsola());
            //System.out.println(ast.getConsola());
            for(var i: listaGlobal){
                System.out.println(i);
            }            
        }catch(Exception ex){
            System.out.println("Algo salio mal");
            System.out.println(ex);
        }
    }//GEN-LAST:event_EjecutarMouseClicked

    private void ReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportesMouseClicked
        // TODO add your handling code here:
        try{
            String texto = jTextArea1.getText();
            scanner s = new scanner(new BufferedReader(new StringReader(texto)));
            parser p = new parser(s);
            var resultado = p.parse();
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new tablaSimbolos();
            tabla.setNombre("Global");
            ast.setConsola("");
            LinkedList<Errores> listaGlobal = new LinkedList<>();
            listaGlobal.addAll(s.listaErrores);
            listaGlobal.addAll(p.listaErrores);
            for(var a : ast.getInstrucciones()){
                if(a == null){
                    continue;
                }
                var res = a.interpretar(ast, tabla);
                if(res instanceof Errores){
                    listaGlobal.add((Errores)res);
                }
            }
            //jTextArea3.setText(ast.getConsola());
            //System.out.println(ast.getConsola());
            for(var i: listaGlobal){
                System.out.println(i);
                jTextArea2.setText(i.getTipo()+ "," + i.getDescripcion() +"," + i.getColumna()+"," +i.getLinea());
            }            
        }catch(Exception ex){
            System.out.println("Algo salio mal");
            System.out.println(ex);
        }
    }//GEN-LAST:event_ReportesMouseClicked

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
            java.util.logging.Logger.getLogger(P_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P_Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Archivo;
    private javax.swing.JMenu Ejecutar;
    private javax.swing.JMenu Pestañas;
    private javax.swing.JMenu Reportes;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration//GEN-END:variables
}
