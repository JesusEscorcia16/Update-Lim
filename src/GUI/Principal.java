package GUI;

import Mysql.Database;

public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
        lblCargando.setVisible(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        lblCargando = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Descargar Actualizacion");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        lblCargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/gifSplash.gif"))); // NOI18N
        lblCargando.setText("Descargando Archivo...");
        jPanel1.add(lblCargando, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        lblCargando.setVisible(true);
        new Thread(new Descargar()).start();
    }//GEN-LAST:event_jButton1ActionPerformed
    private class Descargar implements Runnable {

        @Override
        public void run() {
            Database.getJar();
            lblCargando.setVisible(false);
        }
        
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCargando;
    // End of variables declaration//GEN-END:variables
}
