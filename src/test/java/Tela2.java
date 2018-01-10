
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author c007329
 */
public class Tela2 extends javax.swing.JFrame {

    /**
     * Creates new form Tela2
     */
    public Tela2() {
        initComponents();
        final String propertyName = "text";
        label.setTransferHandler(new TransferHandler(propertyName));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                JComponent comp = (JComponent) evt.getSource();
                TransferHandler th = comp.getTransferHandler();

                th.exportAsDrag(comp, evt, TransferHandler.COPY);
            }
        });
        panel.setLayout(new GridLayout(1, 1));
        panel.setTransferHandler(new TransferHandler() {

            public boolean canImport(TransferHandler.TransferSupport info) {
                return getPropertyDataFlavor(String.class, info.getDataFlavors()) != null;
            }

            public boolean importData(TransferHandler.TransferSupport info) {

                if (!info.isDrop()) {
                    System.out.println("deu merda");
                    return false;
                }

                // Check for String flavor
                /*if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    System.out.println("deu merda");
                    displayDropLocation("List doesn't accept a drop of this type.");
                    return false;
                }*/
                // Get the string that is being dropped.
                Transferable t = info.getTransferable();
                String data = "";
                try {

                    data = (String) t.getTransferData(getPropertyDataFlavor(String.class, info.getDataFlavors()));
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }

                System.out.println("texto: " + data);

                /**
                 * This is commented out for the basicdemo.html tutorial page. *
                 * If you add this code snippet back and delete the * "return
                 * false;" line, the list will accept drops * of type string. //
                 * Perform the actual import. if (insert) { listModel.add(index,
                 * data); } else { listModel.set(index, data); } return true;
                 */
                return false;
            }

            public int getSourceActions(JComponent c) {
                return COPY;
            }

            protected Transferable createTransferable(JComponent c) {
                JLabel lab = (JLabel) c;
                System.out.println("Aqui: " + lab.getText());
                return new StringSelection(lab.getText());
            }
        });
        //panel.add(new Panel());
    }

    private DataFlavor getPropertyDataFlavor(Class<?> k, DataFlavor[] flavors) {
        for (int i = 0; i < flavors.length; i++) {
            DataFlavor flavor = flavors[i];
            if ("application".equals(flavor.getPrimaryType())
                    && "x-java-jvm-local-objectref".equals(flavor.getSubType())
                    && k.isAssignableFrom(flavor.getRepresentationClass())) {
                return flavor;
            }
        }
        return null;
    }

    private void displayDropLocation(final String string) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog(null, string);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label.setText("Label Teste");

        panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("07:00 16:00"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 113, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("15:00 00:00"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 313, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addGap(35, 35, 35)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Tela2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
