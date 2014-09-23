/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MenuP.java
 *
 * Created on 23/06/2013, 04:37:07 PM
 */
package testiisop;

import com.stin.BD.ActividadesDAO;
import com.stin.BD.CatalogoN4DAO;
import com.stin.BD.NptsIntervencionDAO;
import com.stin.pemex.siop.model.Npt;
import com.stin.pemex.siop.util.MenuActionListener;
import com.stin.pemex.siop.util.SiopUtils;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Marco
 */
public class MenuP extends javax.swing.JFrame {

    /** Creates new form MenuP */
    int xPosition = 30, yPosition = 30;
    int iP = 0;
    int openFrameCount;

    public MenuP() {
        initComponents();
//    for(int i=0; i<5; i++) {
//      JInternalFrame frame
//        = new JInternalFrame(("Internal Frame " + i),
//                             true, true, true, true);
//      frame.setLocation(i*50+10, i*50+10);
//      frame.setSize(200, 150);
//      frame.setBackground(Color.white);
//      desktop.add(frame);
//      frame.moveToFront();
//    }
//    setVisible(true);
//        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        desktopPane.setName("desktopPane"); // NOI18N

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setMnemonic('f');
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(testiisop.TestIisopApp.class).getContext().getResourceMap(MenuP.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        openMenuItem.setMnemonic('o');
        openMenuItem.setText(resourceMap.getString("openMenuItem.text")); // NOI18N
        openMenuItem.setName("openMenuItem"); // NOI18N
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText(resourceMap.getString("saveMenuItem.text")); // NOI18N
        saveMenuItem.setName("saveMenuItem"); // NOI18N
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText(resourceMap.getString("saveAsMenuItem.text")); // NOI18N
        saveAsMenuItem.setName("saveAsMenuItem"); // NOI18N
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText(resourceMap.getString("editMenu.text")); // NOI18N
        editMenu.setName("editMenu"); // NOI18N

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText(resourceMap.getString("cutMenuItem.text")); // NOI18N
        cutMenuItem.setName("cutMenuItem"); // NOI18N
        cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText(resourceMap.getString("copyMenuItem.text")); // NOI18N
        copyMenuItem.setName("copyMenuItem"); // NOI18N
        copyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText(resourceMap.getString("pasteMenuItem.text")); // NOI18N
        pasteMenuItem.setName("pasteMenuItem"); // NOI18N
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText(resourceMap.getString("deleteMenuItem.text")); // NOI18N
        deleteMenuItem.setName("deleteMenuItem"); // NOI18N
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText(resourceMap.getString("contentMenuItem.text")); // NOI18N
        contentMenuItem.setName("contentMenuItem"); // NOI18N
        contentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        helpMenu.add(jMenuItem1);

        jSeparator1.setName("jSeparator1"); // NOI18N
        helpMenu.add(jSeparator1);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void cutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItemActionPerformed
        // TODO add your handling code here:
        JiNpts a = new JiNpts();
        // a.setLocation(iP*50+10, iP*50+10);
        //iP++;
        desktopPane.add(a);

        a.show();
        JMenuItem jm = new JMenuItem();
        jm.setText("Agregado");
        jm.addActionListener(new MenuActionListener(a));

        helpMenu.add(jm);
    }//GEN-LAST:event_cutMenuItemActionPerformed

    private void copyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuItemActionPerformed
        // TODO add your handling code here:
        JiActividades b = new JiActividades();
        //b.setLocation(iP*50+10, iP*50+10);
        //iP++;
        desktopPane.add(b);
        b.setVisible(true);
    }//GEN-LAST:event_copyMenuItemActionPerformed

    private void contentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentMenuItemActionPerformed
        // TODO add your handling code here:
        JInternalFrame[] a = desktopPane.getAllFrames();
        for (int i = 0; i < a.length; i++) {
            a[i].setLocation(i * 50, i * 50);
            a[i].toFront();
        }


    }//GEN-LAST:event_contentMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JInternalFrame[] a = desktopPane.getAllFrames();
        int pos = 0;
        for (int i = 0; i < a.length; i++) {
            a[i].dispose();
        }


    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    //public static void getall(String archivoDestino, int idPozo, int intervencion, int tipoIntervencion, boolean existe, String interBuscar,boolean visualiza,int ultimoConse) {
    public static void getall() {

        List npts = new ArrayList();
        int error = 1;

        /*borrar*/
        String interBuscar = "PERFORACIÓN";
        boolean visualiza = true;
        int tipoIntervencion = 1;
        int ultimoConse=2;
        int intervencion=12;
        int idPozo=1;
        /*borrar*/


        HSSFWorkbook myWorkBook;
        try {


            FileInputStream myInput = new FileInputStream("C:/Users/Marco/Documents/PEMEX/GARCIA/NPT´S 29-04-2013/19JUNIO-2013/PLANTILLA NPTs PERF-TERM M-415-D.xls");
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            myWorkBook = new HSSFWorkbook(myFileSystem);
            boolean excelSinNpt = false, excelConNpt = true, continua = true;

            List<Integer> catNpts = new ArrayList();
            List<Integer> catActividades = new ArrayList<Integer>();
            List<String> catNptsString = new ArrayList<String>();
            List<String> catActividadesString = new ArrayList<String>();
            List<String> catDetalle = new ArrayList<String>();


             int verificaConsecu = 0;
             
            System.out.println("numero de hojas: " + myWorkBook.getNumberOfSheets());
            error = 2;
            for (int i = 0; i < myWorkBook.getNumberOfSheets(); i++) {
                org.apache.poi.ss.usermodel.Sheet sheet = myWorkBook.getSheetAt(i);
                System.out.println("NOMBRE DE LA HOJA: " + sheet.getSheetName() + " ****************************** ");


                if (sheet.getSheetName().toString().equals("Real")) {
                    //System.out.println("FILA) " + sheet.getLastRowNum());
                    //System.out.println("COL: " + sheet.getRow(1).getLastCellNum());

                    int filaBuscar = 0;
                    System.out.println("3");
                    error = 3;

                    for (int fila = 0; fila < sheet.getLastRowNum(); fila++) {
                        if (!(sheet.getRow(fila).getCell(1) == null)) {
                            if (sheet.getRow(fila).getCell(1).toString().equalsIgnoreCase(interBuscar)) {
                                System.out.println("ENCONTRADO POS: " + fila);
                                filaBuscar = fila;
                                continua = false;
                                break;
                            }
                        }
                    } // 3)NO ENCONTRO EL TEXTO DE PERFORACION, TERMINACION, RMA
                    if (continua) {
                        throw new Exception("");
                    }
                    continua = true;
                    error = 4;


                    int numColumnas = sheet.getRow(filaBuscar + 1).getLastCellNum();
                    int numFilas = sheet.getLastRowNum();
                    System.out.println("COLUMNA= " + numColumnas);
                    System.out.println("FILAS= " + numFilas);

                    if (visualiza) {
                        for (int columna = 5; columna < numColumnas; columna++) { // Recorre cada las columnas                                

                            if (!(sheet.getRow(filaBuscar).getCell(columna) == null) || !(sheet.getRow(filaBuscar + 1).getCell(columna) == null)
                                    || !(sheet.getRow(filaBuscar + 2).getCell(columna) == null)) {
                                /*System.out.println("*********** COLUMNA " + columna + " *************** FILA: "+ filaBuscar );                                
                                System.out.println(sheet.getRow(filaBuscar).getCell(columna) == null);
                                System.out.println("\nNPT" + sheet.getRow(filaBuscar + 1).getCell(columna).toString());
                                System.out.println("DETALLE : " + sheet.getRow(filaBuscar + 2).getCell(columna).toString());                                 
                                 */
                                if(!(tipoIntervencion==1)){                                    
                                    System.out.println("ACTIVIDAD: " + sheet.getRow(filaBuscar).getCell(columna).toString());
                                    catActividades.add(ActividadesDAO.getIdActividades(sheet.getRow(filaBuscar).getCell(columna).toString(), tipoIntervencion));//TENIA (COLUMNA,1)
                                }else{
                                    catActividades.add(ActividadesDAO.getIdActividades("", tipoIntervencion));//TENIA (COLUMNA,1)
                                }                                                                                                                                
                                catNpts.add(CatalogoN4DAO.getIdNpts(sheet.getRow(filaBuscar + 1).getCell(columna).toString()));//TENIA (COLUMNA,0)                                
                                catDetalle.add(sheet.getRow(filaBuscar + 2).getCell(columna).toString());
                            } else {
                                System.out.println("A SALIR ");
                                numColumnas=columna;
                                break;
                            }
                        }
                    } else {
                        for (int columna = 5; columna < numColumnas; columna++) {    //                            
//                            if(!(sheet.getRow(filaBuscar + 1).getCell(1).toString().equals(""))){
//                                continua=false;
//                            }//            
                            if (!(sheet.getRow(filaBuscar).getCell(columna) == null) || !(sheet.getRow(filaBuscar + 1).getCell(columna) == null)
                                    || !(sheet.getRow(filaBuscar + 2).getCell(columna) == null)) {
                                
                                catNptsString.add(sheet.getRow(filaBuscar + 1).getCell(columna).toString());
                                catDetalle.add(sheet.getRow(filaBuscar + 2).getCell(columna).toString());
                                
                                if (tipoIntervencion == 1) {
                                    catActividadesString.add("Perforación");
                                } else {
                                    catActividadesString.add(sheet.getRow(filaBuscar).getCell(columna).toString());
                                }
                            }else {
                                System.out.println("A SALIR ");
                                numColumnas=columna;
                                break;
                            }
                        }
                    }
                    
                    
                    int aux = 0;                                                                                                  
                    filaBuscar = filaBuscar + 3;
                    System.out.println("FILABUSCAR:" + filaBuscar);
                    System.out.println("NUEVA COLUMNA: " + numColumnas);
                     
                    System.out.println("5");                     
                    error=5;
                    
                    
                    for (int fila = filaBuscar; fila < numFilas; fila++) { // Recorre cada fila de la hoja                        
                        aux = 0;
                        excelSinNpt = false;
                        excelConNpt = true;

                        //System.out.println("ULTIMO: " + ultimoConse);
                        //System.out.println("EXCEL: " + sheet.getRow(filaBuscar).getCell(1).toString());     
                        
                        if(sheet.getRow(fila).getCell(1).toString().equals("")){  
                                                
                            break;
                        }
                        verificaConsecu = Integer.parseInt(sheet.getRow(fila).getCell(1).toString().replace(".0", ""));
                        
                        if (ultimoConse < verificaConsecu) {                            
                            for (int columna = 5; columna < numColumnas; columna++) { // Recorre cada    fila de la columna                                  
                                 //System.out.println("INICIANDO2"); 
                                //if (!(sheet.getRow(1).getCell(columna).toString().equals(""))) {
                                if (!(sheet.getRow(1).getCell(columna)==null)) {                                    
                                    //if (!(hoja.getColumnView(columna).isHidden())) { //VERIFICA COLUMNA OCULTA                                                                                
                                    if (!(sheet.getRow(fila).getCell(3)==null)) { // VERFICA QUE NO ESTE VACIO EL CONSECUTIVO
                                        //if (!(sheet.getRow(fila).getCell(3).toString().equals(""))) { // VERFICA QUE NO ESTE VACIO EL CONSECUTIVO
                                            //System.out.println("fila " + fila + " col= " + columna);
                                            
                                            if (!(sheet.getRow(fila).getCell(columna)==null)) { //VERIFICA QUE TENGA HORAS REPORTADAS
                                                if(!(sheet.getRow(fila).getCell(columna).toString().equals(""))){
                                                excelConNpt = false;                                                
                                                if (visualiza){
                                                    /*
                                                    System.out.println("fila " + fila + " col= " + columna + " ="+ sheet.getRow(fila).getCell(columna).toString()+",");                        
                                                   
                                                    System.out.println("CONSE: " + sheet.getRow(fila).getCell(1).toString() + ": " + catNpts.get(aux));
                                                    System.out.println("1: " +Integer.parseInt(sheet.getRow(fila).getCell(1).toString().replace(".0", "")));
                                                    System.out.println("2: " +SiopUtils.dateFechaOperacionNPTS(sheet.getRow(fila).getCell(2).toString()));
                                                    System.out.println("3: " +Integer.parseInt(sheet.getRow(fila).getCell(3).toString().replace(".0", "")));
                                                    System.out.println("4: " +Float.parseFloat(sheet.getRow(fila).getCell(columna).toString().replace(".0", "")));
                                                    System.out.println("5: " +NptsIntervencionDAO.getIdNptIntervencion(catNpts.get(aux), catActividades.get(aux), tipoIntervencion));
                                                    System.out.println("6: " +sheet.getRow(fila).getCell(4).toString().replace("'", ""));
                                                    System.out.println("7: " +catDetalle.get(aux));
                                                    */
                                                    
                                                       /* npts.add(new Npt(idPozo, intervencion,
                                                            Integer.parseInt(sheet.getRow(fila).getCell(1).toString().replace(".0", "")), SiopUtils.dateFechaOperacionNPTS(sheet.getRow(fila).getCell(2).toString()),
                                                            Integer.parseInt(sheet.getRow(fila).getCell(3).toString().replace(".0", "")), Float.parseFloat(sheet.getRow(fila).getCell(columna).toString().replace(".0", "")),
                                                            NptsIntervencionDAO.getIdNptIntervencion(catNpts.get(aux), catActividades.get(aux), tipoIntervencion), sheet.getRow(fila).getCell(4).toString().replace("'", ""),
                                                                catDetalle.get(aux)));  
                                                        //System.out.println("SALIO");
                                                        //System.out.println("VALOR: " +sheet.getRow(fila).getCell(columna).toString()+"aja");
                                                     * */
                                                     
                                                }else{                                                
                                                    npts.add(new Npt(idPozo, intervencion,
                                                            Integer.parseInt(sheet.getRow(fila).getCell(1).toString().replace(".0", "")), SiopUtils.dateFechaOperacionNPTS(sheet.getRow(fila).getCell(2).toString()),
                                                            Integer.parseInt(sheet.getRow(fila).getCell(3).toString().replace(".0", "")), Float.parseFloat(sheet.getRow(fila).getCell(columna).toString().replace(".0", "")),
                                                            sheet.getRow(fila).getCell(4).toString().replace("'", ""),catActividadesString.get(aux),catNptsString.get(aux)));                                                            
                                                }                                                

                                                }else{
                                                    excelSinNpt = true;  //Indica que no tiene horas de NPTS                                    
                                                }
                                            } else {
                                                excelSinNpt = true;  //Indica que no tiene horas de NPTS                                    
                                            }

                                        } else {
                                            numFilas = 0;
                                        }

                                    //}//IS HIDDEN
                                }
                                aux++;
                            }//FOR COLUMNAS
                        }//IF ULTIMO CONSECUTIVO
                        //aux++;
                        if (excelSinNpt && excelConNpt) {                            
                            try {
                                System.out.println("6");
                                error=6;
                                //if (!(sheet.getRow(fila).getCell(2).toString().equals(""))) {
                                if (!(sheet.getRow(fila).getCell(2)==null)) {
                                    
                                    if (visualiza){
                                        System.out.println("***** VACIO: " + sheet.getRow(fila).getCell(1).toString());
                                        /*
                                        npts.add(new Npt(idPozo, intervencion,
                                                Integer.parseInt(sheet.getRow(fila).getCell(1).toString().replace(".0", "")), SiopUtils.dateFechaOperacionNPTS(sheet.getRow(fila).getCell(2).toString()),
                                                Integer.parseInt(sheet.getRow(fila).getCell(3).toString().replace(".0", "")), 0,
                                                NptsIntervencionDAO.getIdNptIntervencionNA(tipoIntervencion), sheet.getRow(fila).getCell(4).toString().replace("'", ""),"")); //24 debe ser el id NA 
                                          
                                         */
                                    }else{
                                        npts.add(new Npt(idPozo, intervencion,
                                                Integer.parseInt(sheet.getRow(fila).getCell(1).toString().replace(".0", "")), SiopUtils.dateFechaOperacionNPTS(sheet.getRow(fila).getCell(2).toString()),
                                                Integer.parseInt(sheet.getRow(fila).getCell(3).toString().replace(".0", "")), 0,
                                                sheet.getRow(fila).getCell(4).toString().replace("'", ""),"NA","CO")); //24 debe ser el id NA 
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Verifica: " + e.getMessage());
                            }

                        }
                    }
                                                                                
                        
                    for (int ii = 0; ii < npts.size(); ii++) {
                        //if (((Npt) npts.get(i)).getIdNpt() == 0) {
                        System.out.println("Consecutivo " + ((Npt) npts.get(ii)).getConsecutivo()
                                + " , FECHA " + ((Npt) npts.get(ii)).getFecha()
                                + " , PROFUNDIDAD " + ((Npt) npts.get(ii)).getProfundidad()
                                + " , IDNPTINTERVERNCION " + ((Npt) npts.get(ii)).getIdNptIntervencion()
                                + ", TIEMPO " + ((Npt) npts.get(ii)).getTiempo()
                                + ", INTERVENCION " + ((Npt) npts.get(ii)).getIntervencion()
                                + "detalle " + ((Npt) npts.get(ii)).getStrDetalle());
                        //}
                    }
                    break; // para que no siga recorriendo las hojas de excel
 

                }//QUE SEA IGUAL A REAL LA HOJA

            }// RECORRIDO POR HOJA
        } catch (Exception e) {
            System.out.println(" ERROR AL LEER: " + e.getMessage());
             npts=new ArrayList();
            npts.add(new Npt(0,error, -100, "0", 0, 0, "", "ERROR ", ""));            
            System.out.println("ERROR: " + e.getLocalizedMessage() + " TAMAÑO " + npts.size());            
            
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    getall();
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                }

                // new MenuP().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables
}
/*
class MenuActionListener implements ActionListener {
private JInternalFrame a;

public MenuActionListener(JInternalFrame a){
this.a=a;
}
public void actionPerformed(JiNpts ji, ActionEvent e) {
System.out.println("LOL: ");

}

public void actionPerformed(ActionEvent e) {

System.out.println("Selected222: " +  e.getActionCommand());        
a.toFront();
}
 * 
}*/