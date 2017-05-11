package ssp.seqmachine;

import ssp.seqmachine.*;

/**
 *
 * @author sergio
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form PpalFrame
     */
    public MainFrame() {
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

        jPanel1 = new javax.swing.JPanel();
        seqMachineCanvas1 = new ssp.seqmachine.SeqMachineCanvas();
        jStatusBar = new javax.swing.JPanel();
        jLabelStatusText = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonNewMachine = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jToggleButtonNewState = new javax.swing.JToggleButton();
        jToggleButtonNewTransition = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuNueva = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SeqMachine");
        setLocation(new java.awt.Point(300, 150));
        setMinimumSize(new java.awt.Dimension(850, 500));
        setPreferredSize(new java.awt.Dimension(850, 500));

        seqMachineCanvas1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Separator.shadow")));
        seqMachineCanvas1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                seqMachineCanvas1PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout seqMachineCanvas1Layout = new javax.swing.GroupLayout(seqMachineCanvas1);
        seqMachineCanvas1.setLayout(seqMachineCanvas1Layout);
        seqMachineCanvas1Layout.setHorizontalGroup(
            seqMachineCanvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 766, Short.MAX_VALUE)
        );
        seqMachineCanvas1Layout.setVerticalGroup(
            seqMachineCanvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jStatusBarLayout = new javax.swing.GroupLayout(jStatusBar);
        jStatusBar.setLayout(jStatusBarLayout);
        jStatusBarLayout.setHorizontalGroup(
            jStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );
        jStatusBarLayout.setVerticalGroup(
            jStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        jLabelStatusText.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelStatusText.setText("Barra de estado");
        jLabelStatusText.setAlignmentY(0.0F);

        jToolBar1.setRollover(true);
        jToolBar1.setFocusable(false);

        jButtonNewMachine.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jButtonNewMachine.setText("M");
        jButtonNewMachine.setToolTipText("Crea una nueva máquina");
        jButtonNewMachine.setFocusable(false);
        jButtonNewMachine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewMachineActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonNewMachine);
        jToolBar1.add(jSeparator2);

        jToggleButtonNewState.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jToggleButtonNewState.setText("S");
        jToggleButtonNewState.setToolTipText("Crea un nuevo estado");
        jToggleButtonNewState.setFocusable(false);
        jToggleButtonNewState.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonNewState.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonNewState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonNewStateActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButtonNewState);

        jToggleButtonNewTransition.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jToggleButtonNewTransition.setText("T");
        jToggleButtonNewTransition.setToolTipText("Crea una nueva transición");
        jToggleButtonNewTransition.setFocusable(false);
        jToggleButtonNewTransition.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonNewTransition.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonNewTransition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonNewTransitionActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButtonNewTransition);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelStatusText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(seqMachineCanvas1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seqMachineCanvas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStatusText, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        jMenuNueva.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuNueva.setText("Nueva");
        jMenuNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNuevaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuNueva);
        jMenu1.add(jSeparator1);

        jMenuSalir.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuSalir.setText("Salir");
        jMenuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSalirActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit...");
        jMenu2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNuevaActionPerformed

    }//GEN-LAST:event_jMenuNuevaActionPerformed


    private void jMenuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuSalirActionPerformed

    private void seqMachineCanvas1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_seqMachineCanvas1PropertyChange
        jLabelStatusText.setText(evt.getNewValue().toString());
    }//GEN-LAST:event_seqMachineCanvas1PropertyChange
    //PRUEBA
    private void jButtonNewMachineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewMachineActionPerformed
        seqMachineCanvas1.creaMaquinaTest();
    }//GEN-LAST:event_jButtonNewMachineActionPerformed

    private void jToggleButtonNewStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonNewStateActionPerformed
    }//GEN-LAST:event_jToggleButtonNewStateActionPerformed

    private void jToggleButtonNewTransitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonNewTransitionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButtonNewTransitionActionPerformed

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNewMachine;
    private javax.swing.JLabel jLabelStatusText;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuNueva;
    private javax.swing.JMenuItem jMenuSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPanel jStatusBar;
    private javax.swing.JToggleButton jToggleButtonNewState;
    private javax.swing.JToggleButton jToggleButtonNewTransition;
    private javax.swing.JToolBar jToolBar1;
    private ssp.seqmachine.SeqMachineCanvas seqMachineCanvas1;
    // End of variables declaration//GEN-END:variables
}