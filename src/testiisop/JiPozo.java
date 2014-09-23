/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JiPozo.java
 *
 * Created on 2/05/2013, 12:09:11 PM
 */
package testiisop;

import com.stin.BD.CatEquipoDAO;
import com.stin.BD.CatIntervencionDAO;
import com.stin.BD.CatPlataformaDAO;
import com.stin.BD.CatSubIntervencionDAO;
import com.stin.BD.IntervencionDAO;
import com.stin.BD.PerforacionDAO;
import com.stin.BD.PozoDAO;
import com.stin.BD.RmaDAO;
import com.stin.BD.TerminacionDAO;
import com.stin.CatEquipo;
import com.stin.CatIntervencion;
import com.stin.CatPlataforma;
import com.stin.CatSubIntervencion;
import com.stin.Intervencion;
import com.stin.Pozo;
import com.stin.model.Multivalor;
import com.stin.pemex.siop.util.SiopUtils;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marco
 */
public class JiPozo extends javax.swing.JInternalFrame {

    /** Creates new form JiPozo */
    
    int idPozo=0;
    int idInter=0;
    int idTipoInter=0;
    String nombrePozo="";
    public JiPozo() {
        initComponents();
        comboSubIntervencion();
        Llenar(true,"");
        comboIntervencion();
        comboPlataforma();
        comboEquipo();
        
        JtxtNombre.setVisible(false);
        jDateChooser1.setDate(new Date());
        jDateChooser1.setDateFormatString("yyyy");
        cargarPozos();
    }
    
    public void comboIntervencion(){
        List<CatIntervencion>listaInter=CatIntervencionDAO.getListCatIntervencion();//IntervencionDAO.getListIntervencion();
        for(CatIntervencion catInt: listaInter){
            JcInter.addItem(new Multivalor(catInt.getIdTipo(), catInt.getStrNombre()));
        }
        listaInter=null;
    }
    
    public void comboPlataforma(){
        List<CatPlataforma>lista=CatPlataformaDAO.getListCatPlataforma(true, "");
        for(CatPlataforma c: lista){
            JcbPlataforma.addItem(new Multivalor(c.getIdPlataforma(), c.getStrNombre()));
        }
        lista=null;
    }
    
    public void comboEquipo(){
        List<CatEquipo>lista=CatEquipoDAO.getListCatEquipo(true, "");
        for(CatEquipo c: lista){
            JcbEquipo.addItem(new Multivalor(c.getIdEquipo(), c.getStrNombre()));
        }
        lista=null;
    }
    
    public void comboSubIntervencion(){
        List<CatSubIntervencion>lista=CatSubIntervencionDAO.getListCatSubIntervencion(true, "");
        for(CatSubIntervencion c:lista){
            JcbCatRma.addItem(new Multivalor(c.getIdCatSubIntervencion(), c.getStrNombre()));
        }
        lista=null;
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
        model.addColumn("Pozo");
        model.addColumn("Intervencion"); 
        model.addColumn("Año");
        model.addColumn("Tipo");
        model.addColumn("Plataforma");
        model.addColumn("Equipo");
        /*
         catIntervencion
         * pozo
         * anio colocar el id de la intervencion
         * catsubintervencion
         * plataforma
         * equipo
         */
        try {                       
            List<Intervencion>listPozo=PozoDAO.getListPozoInter(tipoBuscar, buscar);
            model.setNumRows(listPozo.size());
            for(int i=0;i<listPozo.size();i++){
                model.setValueAt(new Multivalor(listPozo.get(i).getPozo().getIdPozo(), listPozo.get(i).getPozo().getStrNombre()), i, 0);
                model.setValueAt(new Multivalor(listPozo.get(i).getCatIntervencion().getIdTipo(), listPozo.get(i).getCatIntervencion().getStrNombre()), i, 1);
                model.setValueAt(new Multivalor(listPozo.get(i).getIdIntervencion(),listPozo.get(i).getAnio()+""), i, 2);
                model.setValueAt(new Multivalor(listPozo.get(i).getCatSubIntervencion().getIdCatSubIntervencion(), listPozo.get(i).getCatSubIntervencion().getStrNombre()), i, 3);
                model.setValueAt(new Multivalor(listPozo.get(i).getCatPlataforma().getIdPlataforma(),listPozo.get(i).getCatPlataforma().getStrNombre()), i, 4);
                model.setValueAt(new Multivalor(listPozo.get(i).getCatEquipo().getIdEquipo(), listPozo.get(i).getCatEquipo().getStrNombre()), i, 5);
            }
        this.jTable1.setModel(model);
        listPozo=null;
        model=null;
        } catch (Exception e) {
            System.out.println("ERROR: " +e.getMessage());
        }
    }
    public boolean Validar(){
        if (insertaPozo){ // Agregar Pozo
            if(JtxtNombre.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane,"Nombre Vacio",Menu.nombreSistema, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }else{ // Agregar Intervencion
            if (jComboBox1.getSelectedIndex()==0){
                JOptionPane.showMessageDialog(rootPane,"Seleccione un pozo",Menu.nombreSistema, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        Multivalor inter=(Multivalor)JcInter.getSelectedItem();
        Multivalor catInter=(Multivalor)JcbCatRma.getSelectedItem();
        if (inter.getIndice()==3 && catInter.getIndice()==1){
            JOptionPane.showMessageDialog(rootPane,"Seleccione un tipo RMA distinto de NA",Menu.nombreSistema, JOptionPane.ERROR_MESSAGE);     
            return false;
        }
         inter=null;
         catInter=null;
        return true;
    }
    public void Limpiar(){
        idPozo=0;
        idInter=0;
        idTipoInter=0;
        nombrePozo="";
        cargarPozos();
        Llenar(true,"");
        JtxtNombre.setText("");
        JtxBuscar.setText("");
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
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        JtxBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        JcInter = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        JcbPlataforma = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        JcbEquipo = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        JcbCatRma = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        jRadioButton1 = new javax.swing.JRadioButton();
        JtxtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(testiisop.TestIisopApp.class).getContext().getResourceMap(JiPozo.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setClosable(true);
        setIconifiable(true);
        setName("Form"); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        JtxBuscar.setName("JtxBuscar"); // NOI18N
        JtxBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxBuscarKeyReleased(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

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
        jTable1.setName("jTable1"); // NOI18N
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        JcInter.setName("JcInter"); // NOI18N
        JcInter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcInterActionPerformed(evt);
            }
        });

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jDateChooser1.setName("jDateChooser1"); // NOI18N

        JcbPlataforma.setName("JcbPlataforma"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        JcbEquipo.setName("JcbEquipo"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        JcbCatRma.setName("JcbCatRma"); // NOI18N

        jComboBox1.setToolTipText(resourceMap.getString("jComboBox1.toolTipText")); // NOI18N
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox1.setName("jComboBox1"); // NOI18N

        jRadioButton1.setText(resourceMap.getString("jRadioButton1.text")); // NOI18N
        jRadioButton1.setName("jRadioButton1"); // NOI18N
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        JtxtNombre.setText(resourceMap.getString("JtxtNombre.text")); // NOI18N
        JtxtNombre.setName("JtxtNombre"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jRadioButton1))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addComponent(JcInter, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addGap(46, 46, 46)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addGap(13, 13, 13)
                .addComponent(JcbPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addGap(33, 33, 33)
                .addComponent(JcbEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addGap(20, 20, 20)
                .addComponent(JcbCatRma, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(JtxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(JtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jRadioButton1))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(JcInter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7))
                    .addComponent(JcbPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8))
                    .addComponent(JcbEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9))
                    .addComponent(JcbCatRma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(JtxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testiisop/resources/pemex/logo_pemex.png"))); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel3.setBackground(resourceMap.getColor("jLabel3.background")); // NOI18N
        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel3.setName("jLabel3"); // NOI18N

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jMenu1.add(jSeparator2);

        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 473, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3))
                    .addComponent(jLabel5))
                .addGap(11, 11, 11)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void cargarPozos(){
        try {
                //CARGA COMBO BOX CON POZOS
                jComboBox1.removeAllItems();
                List<Pozo> pozos = PozoDAO.getListPozo(true,"");
                //jComboBox1.setEnabled(true);
                jComboBox1.addItem(new Multivalor(0, "--- Seleccione pozo ---"));
                for(Pozo p:pozos){                                    
                    jComboBox1.addItem(new Multivalor(p.getIdPozo(), p.getStrNombre()));
                }
                //cnn.close();
            } catch (Exception e) {
                System.out.println("ERROR-Combobox: " + e.getMessage());
            }
    }
     boolean insertaPozo=false;
    public boolean VerificaNombre(){
        //a.toUpperCase().contains("KU")
        if(JtxtNombre.getText().toUpperCase().contains("KU")){
            return true;
        }
        if(JtxtNombre.getText().toUpperCase().contains("MALOOB")){
            return true;
        }
        if(JtxtNombre.getText().toUpperCase().contains("ZAAP")){
            return true;
        }
        if(JtxtNombre.getText().toUpperCase().contains("BACAB")){
            return true;
        }
        JOptionPane.showMessageDialog(rootPane, "Solo se pueden agregar pozos de KU-MALOOB-ZAAP", Menu.nombreSistema, JOptionPane.ERROR_MESSAGE);
        return false;
    }
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:                
                
        if(Validar()){
            Pozo pozo=new Pozo();
                if (insertaPozo){
                    if(VerificaNombre()){
                        pozo.setStrNombre(JtxtNombre.getText());
                        pozo.setBitVisible(true);
                    }else{
                        return;
                    }
                }else{
                    pozo.setIdPozo(((Multivalor)jComboBox1.getSelectedItem()).getIndice());
                }
                if(PozoDAO.setNewPozo(pozo,((Multivalor)JcInter.getSelectedItem()).getIndice(),
                                        jDateChooser1.getDate().getYear()+1900,
                                        ((Multivalor)JcbCatRma.getSelectedItem()).getIndice(),
                                        ((Multivalor)JcbPlataforma.getSelectedItem()).getIndice(),
                                        ((Multivalor)JcbEquipo.getSelectedItem()).getIndice(),insertaPozo)){
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
          /*  
         model.addColumn("Pozo");
        model.addColumn("Intervencion"); 
        model.addColumn("Año");
        model.addColumn("Tipo");
        model.addColumn("Plataforma");
        model.addColumn("Equipo");
        */
        
        String desc="";
        Multivalor combo=null;
        if(evt.getClickCount()==2){
            int fila=jTable1.rowAtPoint(evt.getPoint());
            if(fila>-1){
                idPozo=((Multivalor)jTable1.getValueAt(fila, 0)).getIndice();
                idInter=((Multivalor)jTable1.getValueAt(fila, 2)).getIndice();
                idTipoInter=((Multivalor)jTable1.getValueAt(fila, 1)).getIndice();
                nombrePozo=((Multivalor)jTable1.getValueAt(fila, 0)).getValor();
                System.out.println("IDINTERVENCION: " + idInter);
                insertaPozo=true;
               // jComboBox1.setSelectedItem((Multivalor)jTable1.getValueAt(fila, 0));
                JtxtNombre.setVisible(true);
                jComboBox1.setVisible(false);
                jRadioButton1.setSelected(true);
                JtxtNombre.setText(((Multivalor)jTable1.getValueAt(fila, 0)).getValor());
                JcInter.setSelectedItem((Multivalor)jTable1.getValueAt(fila, 1));
                JcbPlataforma.setSelectedItem((Multivalor)jTable1.getValueAt(fila, 4));
                JcbEquipo.setSelectedItem((Multivalor)jTable1.getValueAt(fila, 5));
                JcbCatRma.setSelectedItem((Multivalor)jTable1.getValueAt(fila, 3));
                jDateChooser1.setDate(SiopUtils.dateFechaYear(((Multivalor)jTable1.getValueAt(fila, 2)).getValor()));
            }            
            combo=null;
        }        
    }//GEN-LAST:event_jTable1MouseClicked
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        if(Validar()){
            if(idPozo!=0){ 
                if(VerificaNombre()){
                    Pozo pozo=new Pozo();
                    pozo.setIdPozo(idPozo);
                    pozo.setStrNombre(JtxtNombre.getText());
                    PozoDAO.setUpdatePozo(idInter,pozo,((Multivalor)JcInter.getSelectedItem()).getIndice(),
                             jDateChooser1.getDate().getYear()+1900,
                                        ((Multivalor)JcbCatRma.getSelectedItem()).getIndice(),
                                        ((Multivalor)JcbPlataforma.getSelectedItem()).getIndice(),
                                        ((Multivalor)JcbEquipo.getSelectedItem()).getIndice());

                    JOptionPane.showMessageDialog(rootPane, "Información Modificada con Éxito", Menu.nombreSistema, JOptionPane.INFORMATION_MESSAGE);                                    
                    Limpiar();
                    pozo=null;
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Seleccione un pozo para modificar", Menu.nombreSistema, JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void JtxBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxBuscarKeyReleased
        // TODO add your handling code here:
        Llenar(false,JtxBuscar.getText());
    }//GEN-LAST:event_JtxBuscarKeyReleased

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        Menu.posX--;
        
        Menu.menuPozo=3;
    }//GEN-LAST:event_formInternalFrameClosed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton1.isSelected()){
            insertaPozo=true;
            //jComboBox1.setEditable(true);
            jComboBox1.setVisible(false);
            JtxtNombre.setVisible(true);
            //jComboBox1.removeAllItems();
            //jComboBox1.setSelectedIndex(0);
        }else{
            insertaPozo=false;
            //jComboBox1.setEditable(false);
            jComboBox1.setVisible(true);
            JtxtNombre.setVisible(false);
            //cargarPozos();
        }
        System.out.println("SELECTD: " + insertaPozo);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void JcInterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcInterActionPerformed
        // TODO add your handling code here:
        if (((Multivalor)JcInter.getSelectedItem()).getIndice()!=3){
            JcbCatRma.setSelectedIndex(0);
            JcbCatRma.setEnabled(false);
        }else{
            JcbCatRma.setEnabled(true);
        }
        
    }//GEN-LAST:event_JcInterActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        
        if (idInter>0){
            int seleccion =JOptionPane.showOptionDialog(
                            this, // Componente padre
                            "¿Desea Eliminar el pozo: "+nombrePozo+"?", //Mensaje
                            "Seleccione una opción", // Título
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,    // null para icono por defecto.
                            new Object[] { "Si", "No"},    // null para YES, NO y CANCEL
                            "Si");
            if (seleccion==0){
                String mensaje="";
                int res=0;
                long numeroIntervecion=IntervencionDAO.searchCountIntervencion(idPozo);
                
                switch (idTipoInter){
                    case 1:
                        res=PerforacionDAO.setDeletePerforacion(idInter);
                        res=PozoDAO.setDeleteIntervencion(idInter);
                        mensaje="Perforación del pozo: "+ nombrePozo +" eliminada";
                        break;
                    case 2:
                        res=TerminacionDAO.setDeleteTerminacion(idInter);
                        res=PozoDAO.setDeleteIntervencion(idInter);
                        mensaje="Terminación del pozo: "+ nombrePozo +" eliminada";
                        break;
                    case 3:
                        res=RmaDAO.setDeleteRMA(idInter);
                        res=PozoDAO.setDeleteIntervencion(idInter);
                        mensaje="RMA del pozo: "+ nombrePozo +" eliminada";
                        break;
                    default:
                        System.out.println("No existe Intervención");
                        break;
                }
              
                if (numeroIntervecion==1){
                    res=PozoDAO.setDeletePozo(idPozo);
                    mensaje="Pozo: "+ nombrePozo +" eliminado con éxito";
                }
                
                if(res>0){
                    JOptionPane.showMessageDialog(rootPane, mensaje, Menu.nombreSistema, JOptionPane.INFORMATION_MESSAGE);
                    Limpiar();
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Error al intertar eliminar el pozo", Menu.nombreSistema, JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Seleccione el pozo a elminar", Menu.nombreSistema, JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JcInter;
    private javax.swing.JComboBox JcbCatRma;
    private javax.swing.JComboBox JcbEquipo;
    private javax.swing.JComboBox JcbPlataforma;
    private javax.swing.JTextField JtxBuscar;
    private javax.swing.JTextField JtxtNombre;
    private javax.swing.JComboBox jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
