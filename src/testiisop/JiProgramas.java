/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JiProgramas.java
 *
 * Created on 19/02/2014, 12:25:52 PM
 */
package testiisop;

import com.stin.BD.CatIntervencionDAO;
import com.stin.BD.CatSubIntervencionDAO;
import com.stin.BD.IntervencionDAO;
import com.stin.BD.PozoDAO;
import com.stin.BD.ProgramasDAO;
import com.stin.CatIntervencion;
import com.stin.CatSubIntervencion;
import com.stin.Intervencion;
import com.stin.Pozo;
import com.stin.Programas;
import com.stin.model.Multivalor;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marco
 */
public class JiProgramas extends javax.swing.JInternalFrame {

    /** Creates new form JiProgramas */
    public JiProgramas() {
        initComponents();
         llenarIntervencion();
         comboSubIntervencion();
         JcmbInter.setSelectedIndex(0);
         Llenar(0);
    }
    
     public void Llenar(int idIntervencion){
         DefaultTableModel model;        
         model = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int columna) {
                if (columna > 4) {
                    return true;
                }
                return false;
            }
        };
        model.setColumnCount(0);
        model.addColumn("Tiempo");
        model.addColumn("Profundidad");
        model.addColumn("Etapa");
        model.addColumn("Diamétro TR");
        if (idIntervencion>0){
            try {

            List<Programas> listProgram=ProgramasDAO.getListProgramas(idIntervencion);
            model.setNumRows(listProgram.size());
            for(int i=0;i<listProgram.size();i++){            
                model.setValueAt(new Multivalor(listProgram.get(i).getIntervencion().getIdIntervencion(),listProgram.get(i).getFloatTiempo()+""), i, 0);
                model.setValueAt(listProgram.get(i).getIntProf(), i, 1);
                model.setValueAt(listProgram.get(i).getCatEtapa().getStrNombre(), i, 2);
                model.setValueAt(listProgram.get(i).getCatTr().getStrNombre(), i, 3);
            }
            listProgram=null;
            } catch (Exception e) {
                System.out.println("ERROR: "+ e.getMessage());
            }
        }
        this.jTable1.setModel(model);
        model=null;
    }
      public int getIntervencion(int index,int tipo){
        int idSubInter=1;
        int verfica=((Multivalor)JcmbInter.getSelectedItem()).getIndice();
        if (verfica==3){
            idSubInter=((Multivalor)JcbCatRma.getSelectedItem()).getIndice();
        }
        return IntervencionDAO.searchIntervencionByTipoAndPozo(index,tipo,idSubInter);
    }
    
     public void llenarIntervencion() {
        try {              
            JcmbInter.removeAllItems();
            List<CatIntervencion> lista=CatIntervencionDAO.getListCatIntervencion();                 
            for(CatIntervencion catInter: lista){            
                JcmbInter.addItem(new Multivalor(catInter.getIdTipo(), catInter.getStrNombre()));                
            }                     
        } catch (Exception e) {
            System.out.println("ERROR llenarIntervencion: " + e.getMessage());
        }
        
    }
   public void comboSubIntervencion(){
         JcbCatRma.removeAllItems();
        List<CatSubIntervencion>lista=CatSubIntervencionDAO.getListCatSubIntervencion(true, "");
        for(CatSubIntervencion c:lista){
            JcbCatRma.addItem(new Multivalor(c.getIdCatSubIntervencion(), c.getStrNombre()));
        }
        lista=null;
    }
     
        public void cargarPozos(){
        try {
                //CARGA COMBO BOX CON POZOS
                JcmbPozo.removeAllItems();
                List<Intervencion> pozos = PozoDAO.getListPozoByIntervencion(((Multivalor)JcmbInter.getSelectedItem()).getIndice(),
                        ((Multivalor)JcbCatRma.getSelectedItem()).getIndice());//getListPozo(true,"");
                //jComboBox1.setEnabled(true);
                JcmbPozo.addItem(new Multivalor(0, "--- Seleccione pozo ---"));
                for(Intervencion p:pozos){                                    
                    JcmbPozo.addItem(new Multivalor(p.getPozo().getIdPozo(), p.getPozo().getStrNombre()));
                }
                //cnn.close();
            } catch (Exception e) {
                System.out.println("ERROR-Combobox: " + e.getMessage());
            }
    }
        
        
        public void limpiar(){
            ocultaSubInter();
            JcmbInter.setSelectedIndex(0);
            JcmbPozo.setSelectedIndex(0);
            Llenar(0);
        }
    
        public void ocultaSubInter(){
            JcbCatRma.setSelectedIndex(0);
            jLabel5.setVisible(false);
            JcbCatRma.setVisible(false);
        }
        public void muestraSubInter(){
            jLabel5.setVisible(true);
            JcbCatRma.setVisible(true);
        }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JcmbInter = new javax.swing.JComboBox();
        JcmbPozo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        JcbCatRma = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();

        setBackground(new java.awt.Color(204, 204, 204));
        setClosable(true);
        setIconifiable(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testiisop/resources/pemex/logo_pemex.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel2.setForeground(new java.awt.Color(38, 148, 105));
        jLabel2.setText("Programas");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda"));

        jLabel3.setText("Intervencion");

        jLabel4.setText("Pozo");

        JcmbInter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbInterActionPerformed(evt);
            }
        });

        JcmbPozo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbPozoActionPerformed(evt);
            }
        });

        jLabel5.setText("Tipo Intervencion");

        JcbCatRma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcbCatRmaActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(17, 17, 17)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel3)
                    .add(jLabel5)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, JcmbPozo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, JcmbInter, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, JcbCatRma, 0, 145, Short.MAX_VALUE))
                .add(290, 290, 290))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(JcmbInter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(JcbCatRma, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(JcmbPozo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Programas"));

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
        jScrollPane1.setViewportView(jTable1);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("Menú");

        jMenuItem1.setText("Eliminar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem2.setText("Salir");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 230, Short.MAX_VALUE)
                        .add(jLabel2))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel1)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JcmbInterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbInterActionPerformed
        // TODO add your handling code here:
         try {
            JcbCatRma.setSelectedIndex(0);
            cargarPozos();
            if (JcmbInter.getSelectedIndex()==2){
                muestraSubInter();
            }else{
                ocultaSubInter();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_JcmbInterActionPerformed

    private void JcbCatRmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcbCatRmaActionPerformed
        // TODO add your handling code here:
         try {   
             cargarPozos();
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_JcbCatRmaActionPerformed

    private void JcmbPozoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbPozoActionPerformed
        // TODO add your handling code here:
            //int idPozo=0;
            //int idInter=0;
        if (JcmbPozo.getSelectedIndex()>0){
             int idPozo=((Multivalor)JcmbPozo.getSelectedItem()).getIndice();
             int idInter=((Multivalor)JcmbInter.getSelectedItem()).getIndice();
            Llenar(getIntervencion(idPozo,idInter));
            //System.out.println(":" + JcmbPozo.getSelectedIndex()+ " " + " idIntervencion: " + getIntervencion(idPozo, idInter));
        }
    }//GEN-LAST:event_JcmbPozoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Multivalor pozo=(Multivalor)JcmbPozo.getSelectedItem();
          int seleccion =JOptionPane.showOptionDialog(
                            this, // Componente padre
                            "¿Desea Eliminar el programa del pozo: "+pozo.getValor()+"?", //Mensaje
                            "Seleccione una opción", // Título
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,    // null para icono por defecto.
                            new Object[] { "Si", "No"},    // null para YES, NO y CANCEL
                            "Si");
          if(seleccion==0){
              //System.out.println(((Multivalor)jTable1.getValueAt(0, 0)).getIndice());
              int res= ProgramasDAO.setDeletePrograma(((Multivalor)jTable1.getValueAt(0, 0)).getIndice());
              if (res>0){
                  JOptionPane.showMessageDialog(rootPane, "Información Eliminada con Éxito", Menu.nombreSistema, JOptionPane.INFORMATION_MESSAGE);
                  limpiar();
              }else{
                  JOptionPane.showMessageDialog(rootPane, "Error al intentar eliminar la información", Menu.nombreSistema, JOptionPane.ERROR_MESSAGE);
              }
           }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JcbCatRma;
    private javax.swing.JComboBox JcmbInter;
    private javax.swing.JComboBox JcmbPozo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

