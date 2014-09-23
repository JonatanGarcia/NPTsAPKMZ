/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JiCatalogoN4.java
 *
 * Created on 25/04/2013, 07:53:29 AM
 */

package testiisop;

import com.stin.BD.CatalogoN3DAO;
import com.stin.BD.CatalogoN4DAO;
import com.stin.model.Multivalor;
import com.stin.CatNptsN3;
import com.stin.CatNptsN4;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marco
 */
public class JiCatalogoN4 extends javax.swing.JInternalFrame {

    /** Creates new form JiCatalogoN4 */    
    int id=0; 
    public JiCatalogoN4() {
        initComponents();
        Llenar(true, "");
        LlenarCombo();
    }
     public void Llenar(boolean tipoBuscar,String buscar){
         DefaultTableModel model;        
         model = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int columna) {
                if (columna > 6) {
                    return true;
                }
                return false;
            }
        };
        model.setColumnCount(0);
        model.addColumn("Id");
        model.addColumn("Catálogo N4");
        model.addColumn("Catálogo N3");
        model.addColumn("Catálogo N2");
        model.addColumn("Catálogo N1");
        try {
            
        List<CatNptsN4> listN1=CatalogoN4DAO.getListCatN4(tipoBuscar,buscar);
        model.setNumRows(listN1.size());
        for(int i=0;i<listN1.size();i++){            
            model.setValueAt(listN1.get(i).getIdNptn4(), i, 0);
            model.setValueAt(listN1.get(i).getStrNombre(), i, 1);
            model.setValueAt(new Multivalor(listN1.get(i).getCatNptsN3().getIdNptn3(), listN1.get(i).getCatNptsN3().getStrNombre()), i, 2);
            model.setValueAt(listN1.get(i).getCatNptsN3().getCatNptsN2().getStrNombre(), i, 3);
            model.setValueAt(listN1.get(i).getCatNptsN3().getCatNptsN2().getCatNptsN1().getStrNombre(), i, 4);
        }
        this.jTable1.setModel(model);
        listN1=null;
        model=null;
        
        } catch (Exception e) {
            System.out.println("ERROR: "+ e.getMessage());
        }
    }
    public void LlenarCombo(){
        List<CatNptsN3>listaN2=CatalogoN3DAO.getListCatN3(true, "");
        for(CatNptsN3 o:listaN2){
            JcbCatN2.addItem(new Multivalor(o.getIdNptn3(), o.getStrNombre()));
        }
        listaN2=null;
    }
    public boolean Validar(){
        if(JtxNombre.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Nombre Vacio",Menu.nombreSistema, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public void Limpiar(){
        id=0;
        JtxNombre.setText("");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        JtxNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        JcbCatN2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        JtxBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        Modificar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();

        setBackground(new java.awt.Color(204, 204, 204));
        setClosable(true);
        setIconifiable(true);
        setTitle("Catálogo N4");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("N4"));

        jLabel1.setText("Nombre");

        jLabel4.setText("Catálogo N3");

        JtxBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxBuscarKeyReleased(evt);
            }
        });

        jLabel2.setText("Busqueda");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
                        .add(jLabel2)
                        .add(18, 18, 18)
                        .add(JtxBuscar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 468, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
                            .add(jLabel4)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                            .add(JcbCatN2, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
                            .add(jLabel1)
                            .add(31, 31, 31)
                            .add(JtxNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 465, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(JtxNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(JcbCatN2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(JtxBuscar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel3.setForeground(new java.awt.Color(38, 148, 105));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nivel 4");
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testiisop/resources/pemex/logo_pemex.png"))); // NOI18N

        jMenu1.setText("Menú");

        jMenuItem1.setText("Guardar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });
        jMenu1.add(Modificar);
        jMenu1.add(jSeparator2);

        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(18, 18, 18)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 326, Short.MAX_VALUE)
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 99, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(77, 77, 77))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel3)
                    .add(jLabel5))
                .add(18, 18, 18)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if(Validar()){
            Multivalor valor=(Multivalor)JcbCatN2.getSelectedItem();
            int id=valor.getIndice();
            boolean respuesta=CatalogoN4DAO.setNewCatN4(JtxNombre.getText(),id);
            if(respuesta){
                Llenar(true, "");
                JOptionPane.showMessageDialog(rootPane, "Información Agregada con Éxito", Menu.nombreSistema, JOptionPane.INFORMATION_MESSAGE);
                Limpiar();
            }else{
                JOptionPane.showMessageDialog(rootPane, "Error al Agregar la Información", Menu.nombreSistema, JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        String desc="";
        Multivalor combo=null;
        if(evt.getClickCount()==2){
            int fila=jTable1.rowAtPoint(evt.getPoint());
            if(fila>-1){
                desc=jTable1.getValueAt(fila, 1).toString();
                id =Integer.parseInt(jTable1.getValueAt(fila, 0).toString());
                combo=(Multivalor)jTable1.getValueAt(fila, 2);
                JcbCatN2.setSelectedItem(combo);
                JtxNombre.setText(desc);
            }
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        // TODO add your handling code here:
        if(id!=0){
            if(Validar()){
                CatNptsN4 catNptsN4=CatalogoN4DAO.getCatN4(id);
                catNptsN4.setStrNombre(JtxNombre.getText());                
                CatNptsN3 catNptsN3=new CatNptsN3();
                catNptsN3.setIdNptn3(((Multivalor)JcbCatN2.getSelectedItem()).getIndice());
                catNptsN4.setCatNptsN3(catNptsN3);
                CatalogoN4DAO.setUpdate(catNptsN4);
                Limpiar();
                catNptsN4=null;
                catNptsN3=null;
                JOptionPane.showMessageDialog(rootPane, "Información Modificada con Éxito", Menu.nombreSistema, JOptionPane.INFORMATION_MESSAGE);            
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Seleccione de la Lista de Nivel 4", Menu.nombreSistema, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ModificarActionPerformed

    private void JtxBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxBuscarKeyReleased
        // TODO add your handling code here:
        Llenar(false, JtxBuscar.getText());        
    }//GEN-LAST:event_JtxBuscarKeyReleased

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        Menu.posX--;
        Menu.menuN4=3;
    }//GEN-LAST:event_formInternalFrameClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JcbCatN2;
    private javax.swing.JTextField JtxBuscar;
    private javax.swing.JTextField JtxNombre;
    private javax.swing.JMenuItem Modificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}