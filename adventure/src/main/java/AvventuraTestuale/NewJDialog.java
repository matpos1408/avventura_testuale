/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package AvventuraTestuale;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Mattia e Vito
 */
public class NewJDialog extends javax.swing.JDialog {
       
        
        // Caricamento immagini
        ImageIcon bagno = new ImageIcon("img/bagno.png");
        ImageIcon salotto = new ImageIcon("img/salotto.png");
        ImageIcon cabinaArmadio1 = new ImageIcon("img/cabina_armadio1.png");
        ImageIcon cabinaArmadio2 = new ImageIcon("img/cabina_armadio2.png");
        ImageIcon cameraDaLetto1 = new ImageIcon("img/camera1.png");
        ImageIcon cameraDaLetto2 = new ImageIcon("img/camera2.png");
        ImageIcon cameraDaLetto3 = new ImageIcon("img/camera3.png");
        ImageIcon stanzetta1 = new ImageIcon("img/cameretta1.png");
        ImageIcon stanzetta2 = new ImageIcon("img/cameretta2.png");
        ImageIcon stanzetta3 = new ImageIcon("img/cameretta3.png");
        ImageIcon corridoio = new ImageIcon("img/corridoio.png");
        ImageIcon cucina1 = new ImageIcon("img/cucina1.png");
        ImageIcon cucina2 = new ImageIcon("img/cucina2.png");
        ImageIcon sgabuzzino1 = new ImageIcon("img/stanzino1.png");
        ImageIcon sgabuzzino2 = new ImageIcon("img/stanzino2.png");
        ImageIcon sgabuzzino3 = new ImageIcon("img/stanzino3.png");
        ImageIcon studio1 = new ImageIcon("img/studio1.png");
        ImageIcon studio2 = new ImageIcon("img/studio2.png");
        ImageIcon studio3 = new ImageIcon("img/studio3.png");
        ImageIcon studio4 = new ImageIcon("img/studio4.png");
        ImageIcon cella = new ImageIcon("img/inventario/cella.png");
        ImageIcon foglio = new ImageIcon("img/inventario/foglietto.png");
        ImageIcon chiave = new ImageIcon("img/inventario/chiave.png");
        ImageIcon pergamena = new ImageIcon("img/inventario/pergamena.png");

        
    
        
        
       private class ClientThread extends Thread{
        private  BufferedReader in = null;
        private JTextArea textArea;
        private boolean run = true;
        public ClientThread(BufferedReader in,JTextArea textArea){
            this.in =in;
            this.textArea = textArea;
        }
        
       
        @Override
        public void run(){
          //Settaggio di una copertina casuale
          int random = (int) (Math.random() * 6) + 1;
          ImageIcon copertina = new ImageIcon("img/copertina/copertina" + random +".png");
          jLabel1.setIcon(copertina);  
          // Appena il gioco si apre richiede il nome utente 
          jTextArea1.setText("\n Inserire username con il comando: #name 'username' ");
          int indexCella=0;
          String items ="";
          while(run){
                try{
                // Carica il file del font TrueType
                File fontFile = new File("font/pixelfont.ttf");
                // Crea una nuova istanza di Font dal file
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                // Registra il font nel GraphicsEnvironment
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
                // Utilizza il font personalizzato
                Font font = new Font(customFont.getFontName(), Font.PLAIN, 10);  
                jTextArea1.setFont(font);
                jTextField1.setFont(font);
                jButton1.setFont(font);
                String cmd=in.readLine();
           
         
                        // Visualizzazione immagine a seconda della stanza 
                       if(cmd.equals("salotto")){
                            jLabel1.setIcon(salotto);
                        }
                       if(cmd.equals("corridoio")){
                           jLabel1.setIcon(corridoio);
                       }
                       if(cmd.equals("studio")){
                           jLabel1.setIcon(studio1);
                       }
                       if(cmd.equals("studio ")){
                           jLabel1.setIcon(studio2);
                       }
                       if(cmd.equals("studio  ")){
                           jLabel1.setIcon(studio3);
                       }
                       if(cmd.equals("studio   ")){
                           jLabel1.setIcon(studio4);
                        
                       }
                       if(cmd.equals("stanzetta")){
                           jLabel1.setIcon(stanzetta1);
                       }
                       if(cmd.equals("stanzetta ")){
                           jLabel1.setIcon(stanzetta2);
                       }
                       if(cmd.equals("stanzetta  ")){
                           jLabel1.setIcon(stanzetta3);
                        
                       }
                        if(cmd.equals("cabinaArmadio")){
                           jLabel1.setIcon(cabinaArmadio1);
                       }
                         if(cmd.equals("cabinaArmadio ")){
                           jLabel1.setIcon(cabinaArmadio2);
                         
                       }
                         if(cmd.equals("bagno")){
                           jLabel1.setIcon(bagno);
                       }
                          if(cmd.equals("cameraDaLetto")){
                           jLabel1.setIcon(cameraDaLetto1);
                       }
                          if(cmd.equals("cameraDaLetto ")){
                           jLabel1.setIcon(cameraDaLetto3);
                            
                       }
                           if(cmd.equals("cameraDaLetto  ")){
                           jLabel1.setIcon(cameraDaLetto2);
                       
                       }
                           if(cmd.equals("cucina")){
                           jLabel1.setIcon(cucina1);
                       }
                           if(cmd.equals("cucina ")){
                           jLabel1.setIcon(cucina2);
                      
                       }
                            if(cmd.equals("sgabuzzino")){
                           jLabel1.setIcon(sgabuzzino1);
                       }
                            if(cmd.equals("sgabuzzino ")){
                           jLabel1.setIcon(sgabuzzino2);
                       }
                            if(cmd.equals("sgabuzzino  ")){
                           jLabel1.setIcon(sgabuzzino3);
                      
                       }
                            
                        
                 
                       else{
                           textArea.setText(textArea.getText() + cmd + "\n");
                           textArea.setCaretPosition(textArea.getDocument().getLength());
                       }
                     
                }
                catch(IOException ex){
                    System.err.println(ex);
                } catch (FontFormatException ex) {
                  Logger.getLogger(NewJDialog.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
          
           
        }
        
        
    }
    
     
       
       
    private  BufferedReader in = null;
    
    private PrintWriter out = null;
    

    /**
     * Creates new form NewJDialog
     */
    public NewJDialog(java.awt.Frame parent, boolean modal) throws IOException {
        super(parent, modal);
        initComponents();
        initConnection();
    }
    
     private void initConnection() throws IOException{
            try{
                InetAddress addr = InetAddress.getByName("localhost");
                Socket socket = new Socket(addr,6666);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter( new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                ClientThread ct = new ClientThread(in,jTextArea1);
               
                ct.start();
                
                
               
            }catch(IOException ex){
                
            }
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(39, 27, 32));
        jPanel1.setForeground(new java.awt.Color(39, 27, 32));

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(39, 2, 32)));
        jTextField1.setCaretColor(new java.awt.Color(226, 209, 192));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                none(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(39, 2, 32)));
        jTextArea1.setCaretColor(new java.awt.Color(226, 209, 192));
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(39, 2, 32));
        jButton1.setText("Invia");
        jButton1.setToolTipText("Clicca per inviare");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(39, 2, 32)));
        jButton1.setName(""); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(39, 2, 32));
        jLabel1.setForeground(new java.awt.Color(39, 27, 32));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String message = jTextField1.getText();

        if( message != null && message.length()>0){
            out.println(message);
            jTextField1.setText("");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            jButton1.doClick();

        }
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE){
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void none(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_none
        // TODO add your handling code here:
    }//GEN-LAST:event_none
          
    
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
            java.util.logging.Logger.getLogger(NewJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NewJDialog dialog = new NewJDialog(new javax.swing.JFrame(), true);
                    
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(NewJDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

