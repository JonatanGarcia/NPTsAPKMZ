/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JiNpts.java
 *
 * Created on 30/05/2013, 07:36:05 AM
 */
package testiisop;

import com.stin.BD.CatEtapaDAO;
import com.stin.BD.CatIntervencionDAO;
import com.stin.BD.CatTrDAO;
import com.stin.BD.PerforacionDAO;
import com.stin.BD.PozoDAO;
import com.stin.BD.RmaDAO;
import com.stin.BD.TerminacionDAO;
import com.stin.CatEtapa;
import com.stin.CatIntervencion;
import com.stin.CatTr;
import com.stin.Intervencion;
import com.stin.NptIntervencion;
import com.stin.Perforacion;
import com.stin.Pozo;
import com.stin.Rma;
import com.stin.Terminacion;
import com.stin.model.Multivalor;
import com.stin.pemex.siop.util.SiopUtils;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marco
 */
public class JiNpts extends javax.swing.JInternalFrame {

    /** Creates new form JiNpts */   
    int id=0;
    int idIntervencion=0;
    int idNptIntervencion=0;
    int idTipoInter=-1;
    public static int idIntervencionSeleccionado=0;
    public static String newCatNpt="";
    
    public JiNpts() {
        initComponents();
        ComboPozo();
        ComboIntervencion();
        ComboEtapa();
        ComboTr();
        //Llenar(((Multivalor)JcPozo.getSelectedItem()).getIndice(),1,0);
    }

        
         
    public void ComboPozo(){
        List<Pozo>listaPozo=PozoDAO.getListPozo(true, "");
        for(Pozo p:listaPozo){
            JcPozo.addItem(new Multivalor(p.getIdPozo(),p.getStrNombre()));
        }
        listaPozo=null;
    }
    public void ComboIntervencion(){
        List<CatIntervencion>listaCat=CatIntervencionDAO.getListCatIntervencion();
        for(CatIntervencion cat:listaCat){            
            JcInter.addItem(new Multivalor(cat.getIdTipo(), cat.getStrNombre()));
        }
        listaCat=null;
    }
    
    public void ComboTr(){
        List<CatTr> listaTr=CatTrDAO.getListCatTr(true, "");
        for(CatTr catTr: listaTr){
            JcmbDiametro.addItem(new Multivalor(catTr.getIdCatTr(), catTr.getStrNombre()));
        }
        listaTr=null;
    }
    
    public void ComboEtapa(){
        List<CatEtapa>listaEtapa=CatEtapaDAO.getListCatEtapa(true, "");
        for(CatEtapa catEtapa: listaEtapa){
            JcmbEtapa.addItem(new Multivalor(catEtapa.getIdEtapa(), catEtapa.getStrNombre()));
        }
        listaEtapa=null;
    }
    
     public void Llenar(int idPozo,int idTipo, int consecutivo){                  
         DefaultTableModel model;         
         model = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int columna) {
                if (columna > 11) {
                    return true;
                }
                return false;
            }
        };
        model.setColumnCount(0);
        model.addColumn("Id");          
        model.addColumn("Consecutivo");        
        model.addColumn("Fecha Operacion");
        model.addColumn("Profundidad");
        model.addColumn("Tiempo");
        model.addColumn("Actividades /NPT");
        model.addColumn("Resumen");
        model.addColumn("Pozo");
        model.addColumn("Detalle");
        model.addColumn("Tipo RMA");
        model.addColumn("Etapa");
        model.addColumn("Diametro Tr");
        
        
        int index=JcInter.getSelectedIndex();
        System.out.println("INDEX: " + index);
        String tabla="";
        if(index==0){
            tabla="Perforacion";
        }else if(index==1){
            tabla="Terminacion";
        }else{
            
            tabla="Rma";
        }
         Perforacion p=null;
         Terminacion t=null;
         Rma r=null;
        try {                     
            List<Object>lista=PerforacionDAO.getGenericIntervencion(idPozo,idTipo,consecutivo,tabla);
            model.setNumRows(lista.size());
            int i=0;
            for(Object o: lista){
                if(o instanceof Perforacion){                    
                    //PERFORACION                    
                    p=(Perforacion)o;
                    model.setValueAt(p.getIdPerforacion(), i, 0);
                    model.setValueAt(p.getIntCon(), i, 1);
                    model.setValueAt(p.getDateOperacion(), i, 2);
                    model.setValueAt(p.getIntProf(), i, 3);
                    model.setValueAt(p.getFloatTiempo(), i, 4);
                    model.setValueAt(new Multivalor(p.getNptIntervencion().getIdNptIntervencion(),
                        p.getNptIntervencion().getCatActividades().getStrNombre()+" / " +
                        p.getNptIntervencion().getCatNptsN4().getStrNombre()),i,5);
                    model.setValueAt(p.getStrResumen(), i, 6);
                    model.setValueAt(new Multivalor(p.getIntervencion().getIdIntervencion(), p.getIntervencion().getPozo().getStrNombre()), i, 7);
                    model.setValueAt(p.getStrDetalle(), i, 8);
                    model.setValueAt(new Multivalor(p.getIntervencion().getCatSubIntervencion().getIdCatSubIntervencion(), p.getIntervencion().getCatSubIntervencion().getStrNombre()), i, 9);
                    model.setValueAt(new Multivalor(p.getCatEtapa().getIdEtapa(),p.getCatEtapa().getStrNombre()),i,10);
                    model.setValueAt(new Multivalor(p.getCatTr().getIdCatTr(),p.getCatTr().getStrNombre()),i,11);
                    p=null;
                    
                }else if(o instanceof Terminacion){
                    //TERMINACION
                    t=(Terminacion)o;
                    model.setValueAt(t.getIdTerminacion(), i, 0);
                    model.setValueAt(t.getIntCon(), i, 1);
                    model.setValueAt(t.getDateOperacion(), i, 2);
                    model.setValueAt(t.getIntProf(), i, 3);
                    model.setValueAt(t.getFloatTiempo(), i, 4);
                    model.setValueAt(new Multivalor(t.getNptIntervencion().getIdNptIntervencion(),
                        t.getNptIntervencion().getCatActividades().getStrNombre()+" / " +
                        t.getNptIntervencion().getCatNptsN4().getStrNombre()),i,5);
                    model.setValueAt(t.getStrResumen(), i, 6);  
                    model.setValueAt(new Multivalor(t.getIntervencion().getIdIntervencion(), t.getIntervencion().getPozo().getStrNombre()), i, 7);
                    model.setValueAt(t.getStrDetalle(), i, 8);
                    model.setValueAt(new Multivalor(t.getIntervencion().getCatSubIntervencion().getIdCatSubIntervencion(), t.getIntervencion().getCatSubIntervencion().getStrNombre()), i, 9);
                    model.setValueAt(new Multivalor(t.getCatEtapa().getIdEtapa(),t.getCatEtapa().getStrNombre()),i,10);
                    model.setValueAt(new Multivalor(t.getCatTr().getIdCatTr(),t.getCatTr().getStrNombre()),i,11);
                    t=null;
                }else if(o instanceof Rma){
                    //RMA                    
                    r=(Rma)o;
                    model.setValueAt(r.getIdRma(), i, 0);
                    model.setValueAt(r.getIntCon(), i, 1);
                    model.setValueAt(r.getDateOperacion(), i, 2);
                    model.setValueAt(r.getIntProf(), i, 3);
                    model.setValueAt(r.getFloatTiempo(), i, 4);
                    model.setValueAt(new Multivalor(r.getNptIntervencion().getIdNptIntervencion(),
                        r.getNptIntervencion().getCatActividades().getStrNombre()+" / " +
                        r.getNptIntervencion().getCatNptsN4().getStrNombre()),i,5);
                    model.setValueAt(r.getStrResumen(), i, 6);  
                    model.setValueAt(new Multivalor(r.getIntervencion().getIdIntervencion(), r.getIntervencion().getPozo().getStrNombre()), i, 7);
                    model.setValueAt(r.getStrDetalle(), i, 8);  
                    model.setValueAt(new Multivalor(r.getIntervencion().getCatSubIntervencion().getIdCatSubIntervencion(), r.getIntervencion().getCatSubIntervencion().getStrNombre()), i, 9);
                    model.setValueAt(new Multivalor(r.getCatEtapa().getIdEtapa(),r.getCatEtapa().getStrNombre()),i,10);
                    model.setValueAt(new Multivalor(r.getCatTr().getIdCatTr(),r.getCatTr().getStrNombre()),i,11);
                    r=null;

                }                                                            
                i++;
            }
         
            this.jTable1.setModel(model);
            lista=null;
            p=null;
            t=null;
            r=null;
         } catch (Exception e) {
             System.out.println("ERROR JiNpts.Llenar " +e.getMessage());
         }
     }

     public void Limpiar(){
        id=0;
        idIntervencion=0;
        idNptIntervencion=0;
        idTipoInter=-1;
        idIntervencionSeleccionado=0;
        String newCatNpt="";
        JtxtBuscaConse.setText("");
        JtxtCat.setText("");
        JtxtConsecutivo.setText("");
        JtxtProf.setText("");
        JtxtTiempo.setText("");
        Jtxtresumen.setText("");
        Jtxtdetalle.setText("");
     }
     
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        JtxtConsecutivo = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        JtxtProf = new javax.swing.JTextField();
        JtxtTiempo = new javax.swing.JTextField();
        JtxtCat = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jtxtresumen = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Jtxtdetalle = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        JcmbEtapa = new javax.swing.JComboBox();
        JcmbDiametro = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        JcInter = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JcPozo = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        JtxtBuscaConse = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(testiisop.TestIisopApp.class).getContext().getResourceMap(JiNpts.class);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        JtxtConsecutivo.setText(resourceMap.getString("JtxtConsecutivo.text")); // NOI18N
        JtxtConsecutivo.setName("JtxtConsecutivo"); // NOI18N

        jDateChooser1.setName("jDateChooser1"); // NOI18N

        JtxtProf.setText(resourceMap.getString("JtxtProf.text")); // NOI18N
        JtxtProf.setName("JtxtProf"); // NOI18N

        JtxtTiempo.setText(resourceMap.getString("JtxtTiempo.text")); // NOI18N
        JtxtTiempo.setName("JtxtTiempo"); // NOI18N

        JtxtCat.setText(resourceMap.getString("JtxtCat.text")); // NOI18N
        JtxtCat.setName("JtxtCat"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        Jtxtresumen.setColumns(20);
        Jtxtresumen.setRows(5);
        Jtxtresumen.setAutoscrolls(false);
        Jtxtresumen.setName("Jtxtresumen"); // NOI18N
        jScrollPane1.setViewportView(Jtxtresumen);

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        Jtxtdetalle.setColumns(20);
        Jtxtdetalle.setRows(5);
        Jtxtdetalle.setAutoscrolls(false);
        Jtxtdetalle.setName("Jtxtdetalle"); // NOI18N
        jScrollPane3.setViewportView(Jtxtdetalle);

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        JcmbEtapa.setName("JcmbEtapa"); // NOI18N

        JcmbDiametro.setName("JcmbDiametro"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JcmbEtapa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JtxtConsecutivo)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JtxtTiempo)
                            .addComponent(JtxtCat, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                            .addComponent(JtxtProf, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JcmbDiametro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(JtxtConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(JtxtProf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JtxtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(JtxtCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1))
                            .addComponent(jLabel9)))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JcmbEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JcmbDiametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel3.setBackground(resourceMap.getColor("jLabel3.background")); // NOI18N
        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel3.setName("jLabel3"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        JcInter.setName("JcInter"); // NOI18N
        JcInter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcInterActionPerformed(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        JcPozo.setName("JcPozo"); // NOI18N
        JcPozo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcPozoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JcInter, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JcPozo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(367, 367, 367))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(JcPozo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(JcInter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel3.border.title"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        JtxtBuscaConse.setText(resourceMap.getString("JtxtBuscaConse.text")); // NOI18N
        JtxtBuscaConse.setName("JtxtBuscaConse"); // NOI18N
        JtxtBuscaConse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtBuscaConseKeyReleased(evt);
            }
        });

        jScrollPane2.setName("jScrollPane2"); // NOI18N

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
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JtxtBuscaConse, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(JtxtBuscaConse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

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

        jSeparator1.setName("jSeparator1"); // NOI18N
        jMenu1.add(jSeparator1);

        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JcPozoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcPozoActionPerformed
        // TODO add your handling code here:        
        try {
            Llenar(((Multivalor)JcPozo.getSelectedItem()).getIndice(),((Multivalor)JcInter.getSelectedItem()).getIndice(),0);
        } catch (Exception e) {
            System.out.println("ERROR:( " + e.getMessage());
        }
        
        
    }//GEN-LAST:event_JcPozoActionPerformed

    private void JtxtBuscaConseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtBuscaConseKeyReleased
        // TODO add your handling code here:
        try {
            
            if(!(JtxtBuscaConse.getText().equals(""))){                
                Llenar(((Multivalor)JcPozo.getSelectedItem()).getIndice(),((Multivalor)JcInter.getSelectedItem()).getIndice(),Integer.parseInt(JtxtBuscaConse.getText()));                
            }else{
                Llenar(((Multivalor)JcPozo.getSelectedItem()).getIndice(),((Multivalor)JcInter.getSelectedItem()).getIndice(),0);
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Inserte Valores Numericos en Buscar Consecutivo", Menu.nombreSistema, JOptionPane.INFORMATION_MESSAGE);             
            JtxtBuscaConse.selectAll();
        }
        
    }//GEN-LAST:event_JtxtBuscaConseKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:      
        
        if(evt.getClickCount()==2){
            Multivalor combo=null;
            int fila=jTable1.rowAtPoint(evt.getPoint());
            if (fila>-1){
                id=Integer.parseInt(jTable1.getValueAt(fila, 0).toString());  
                
                JtxtConsecutivo.setText(jTable1.getValueAt(fila, 1).toString());
                jDateChooser1.setDate(SiopUtils.dateFechaPerf(jTable1.getValueAt(fila, 2).toString()));
                JtxtProf.setText(jTable1.getValueAt(fila, 3).toString());
                JtxtTiempo.setText(jTable1.getValueAt(fila, 4).toString());
                JtxtCat.setText(jTable1.getValueAt(fila, 5).toString());
                idNptIntervencion=((Multivalor)jTable1.getValueAt(fila, 5)).getIndice();
                Jtxtresumen.setText(jTable1.getValueAt(fila, 6).toString());  
                idIntervencion=((Multivalor)jTable1.getValueAt(fila, 7)).getIndice();
                idTipoInter=JcInter.getSelectedIndex();
                Jtxtdetalle.setText(jTable1.getValueAt(fila, 8).toString()); 
                combo=(Multivalor)jTable1.getValueAt(fila, 10);
                JcmbEtapa.setSelectedItem(combo);
                combo=(Multivalor)jTable1.getValueAt(fila, 11);
                JcmbDiametro.setSelectedItem(combo);
                //10etapa
                //11tr
                
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    public boolean ActualizaPerf(){
        
        Intervencion intervencion=new Intervencion();
        intervencion.setIdIntervencion(idIntervencion);
        
        NptIntervencion nptIntervencion=new NptIntervencion();
        nptIntervencion.setIdNptIntervencion(idNptIntervencion);
        
        CatEtapa catEtapa=new CatEtapa();
        catEtapa.setIdEtapa(((Multivalor)JcmbEtapa.getSelectedItem()).getIndice());
        
        CatTr catTr=new CatTr();
        catTr.setIdCatTr(((Multivalor)JcmbDiametro.getSelectedItem()).getIndice());
        
        Perforacion perforacion=new Perforacion();
        perforacion.setIdPerforacion(id);        
        perforacion.setIntervencion(intervencion);
        perforacion.setIntCon(Integer.parseInt(JtxtConsecutivo.getText()));
        perforacion.setDateOperacion(jDateChooser1.getDate());
        perforacion.setIntProf(Integer.parseInt(JtxtProf.getText()));
        perforacion.setStrResumen(Jtxtresumen.getText());
        perforacion.setFloatTiempo(Double.parseDouble(JtxtTiempo.getText()));
        perforacion.setNptIntervencion(nptIntervencion);
        perforacion.setStrDetalle(Jtxtdetalle.getText());
        perforacion.setCatEtapa(catEtapa);
        perforacion.setCatTr(catTr);
        boolean res=PerforacionDAO.setUpdatePerforacion(perforacion);
        
        
        intervencion=null;
        nptIntervencion=null;
        perforacion=null;
        catEtapa=null;
        catTr=null;
        return res;
    }
     public boolean ActualizaTerm(){
        Intervencion intervencion=new Intervencion();
        intervencion.setIdIntervencion(idIntervencion);
        
        NptIntervencion nptIntervencion=new NptIntervencion();
        nptIntervencion.setIdNptIntervencion(idNptIntervencion);
        
        CatEtapa catEtapa=new CatEtapa();
        catEtapa.setIdEtapa(((Multivalor)JcmbEtapa.getSelectedItem()).getIndice());
        
        CatTr catTr=new CatTr();
        catTr.setIdCatTr(((Multivalor)JcmbDiametro.getSelectedItem()).getIndice());
        
        Terminacion terminacion=new Terminacion();
        terminacion.setIdTerminacion(id);        
        terminacion.setIntervencion(intervencion);
        terminacion.setIntCon(Integer.parseInt(JtxtConsecutivo.getText()));
        terminacion.setDateOperacion(jDateChooser1.getDate());
        terminacion.setIntProf(Integer.parseInt(JtxtProf.getText()));
        terminacion.setStrResumen(Jtxtresumen.getText());
        terminacion.setFloatTiempo(Double.parseDouble(JtxtTiempo.getText()));
        terminacion.setNptIntervencion(nptIntervencion);
        terminacion.setStrDetalle(Jtxtdetalle.getText());
        terminacion.setCatEtapa(catEtapa);
        terminacion.setCatTr(catTr);
        boolean res=TerminacionDAO.setUpdateTerm(terminacion);
        
        intervencion=null;
        nptIntervencion=null;
        terminacion=null;
        catEtapa=null;
        catTr=null;
        return res;
    }          
     
     
     public boolean ActualizaRMA(){
       Intervencion intervencion=new Intervencion();
        intervencion.setIdIntervencion(idIntervencion);
        
        NptIntervencion nptIntervencion=new NptIntervencion();
        nptIntervencion.setIdNptIntervencion(idNptIntervencion);
                
        CatEtapa catEtapa=new CatEtapa();
        catEtapa.setIdEtapa(((Multivalor)JcmbEtapa.getSelectedItem()).getIndice());
        
        CatTr catTr=new CatTr();
        catTr.setIdCatTr(((Multivalor)JcmbDiametro.getSelectedItem()).getIndice());
        
        Rma reparacion=new Rma();
        reparacion.setIdRma(id);        
        reparacion.setIntervencion(intervencion);
        reparacion.setIntCon(Integer.parseInt(JtxtConsecutivo.getText()));
        reparacion.setDateOperacion(jDateChooser1.getDate());
        reparacion.setIntProf(Integer.parseInt(JtxtProf.getText()));
        reparacion.setStrResumen(Jtxtresumen.getText());
        reparacion.setFloatTiempo(Double.parseDouble(JtxtTiempo.getText()));
        reparacion.setNptIntervencion(nptIntervencion); 
        reparacion.setStrDetalle(Jtxtdetalle.getText());
        reparacion.setCatEtapa(catEtapa);
        reparacion.setCatTr(catTr);
        
        boolean res=RmaDAO.setUpdateRma(reparacion);
        
        intervencion=null;
        nptIntervencion=null;
        reparacion=null;
        catEtapa=null;
        catTr=null;
        return res;
    }
     
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if(!(id==0)){
            System.out.println("idTipoInter: " + idTipoInter);
            switch(idTipoInter){        
                case 0:
                    if(ActualizaPerf())
                        JOptionPane.showMessageDialog(rootPane, "Perforación Modificada con Éxito",Menu.nombreSistema, JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(rootPane, "Error al Modificar la Perforación", Menu.nombreSistema, JOptionPane.ERROR_MESSAGE);
                    break;
                case 1:
                    if (ActualizaTerm())
                        JOptionPane.showMessageDialog(rootPane, "Terminación Modificada con Éxito", Menu.nombreSistema, JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(rootPane, "Error al Modificar la Terminación", Menu.nombreSistema, JOptionPane.ERROR_MESSAGE);
                    break;
                case 2: 
                     if (ActualizaRMA())
                        JOptionPane.showMessageDialog(rootPane, "RMA Modificada con Éxito", Menu.nombreSistema, JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(rootPane, "Error al Modificar la RMA", Menu.nombreSistema, JOptionPane.ERROR_MESSAGE);
                    break;                    
                default:
                    System.out.println(":P");
                    break;

            }
            Limpiar();
            Llenar(((Multivalor)JcPozo.getSelectedItem()).getIndice(),((Multivalor)JcInter.getSelectedItem()).getIndice(),0);
        }else{
            JOptionPane.showMessageDialog(rootPane, "Seleccione un consecutivo de la Lista NPTS", Menu.nombreSistema, JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

   
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JiBuscaNptIntervencion jifNptsActividades=new JiBuscaNptIntervencion(((Multivalor)JcInter.getSelectedItem()).getIndice());                         
        jifNptsActividades.addInternalFrameListener(new InternalFrameAdapter() {                                 
             public void internalFrameClosing (InternalFrameEvent e) {
                //((JiBuscaNptIntervencion) e.getSource()).setVisible(true);
                 if(idIntervencionSeleccionado>0){
                    idNptIntervencion= idIntervencionSeleccionado;
                    JtxtCat.setText(newCatNpt);
                    System.out.println("ID SELECCIONADO :D " + idIntervencionSeleccionado);
                 }
                 
            }            	
            
        });        
        
        jifNptsActividades.show();
       
        
        Menu.jDesktopPane1.add(jifNptsActividades);                     
        jifNptsActividades.toFront();       
        try {
            jifNptsActividades.setMaximum(true);         
        } catch (Exception e) {
            System.out.println("NANI");
        }        
        jifNptsActividades.show(); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:                
        dispose();
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        Menu.posX--;
        Menu.menuNpts=3;
    }//GEN-LAST:event_formInternalFrameClosed

    private void JcInterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcInterActionPerformed
        // TODO add your handling code here:
         try {
            Llenar(((Multivalor)JcPozo.getSelectedItem()).getIndice(),((Multivalor)JcInter.getSelectedItem()).getIndice(),0);
        } catch (Exception e) {
            System.out.println("ERROR:( " + e.getMessage());
        }
        
    }//GEN-LAST:event_JcInterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JcInter;
    private javax.swing.JComboBox JcPozo;
    private javax.swing.JComboBox JcmbDiametro;
    private javax.swing.JComboBox JcmbEtapa;
    private javax.swing.JTextField JtxtBuscaConse;
    private javax.swing.JTextField JtxtCat;
    private javax.swing.JTextField JtxtConsecutivo;
    private javax.swing.JTextField JtxtProf;
    private javax.swing.JTextField JtxtTiempo;
    private javax.swing.JTextArea Jtxtdetalle;
    private javax.swing.JTextArea Jtxtresumen;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
