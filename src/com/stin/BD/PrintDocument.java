/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.Programas;
import com.stin.pemex.siop.model.Npt;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Marco
 */
public class PrintDocument {    
    public static void Print(List<Npt>listaNpt,int tipo,String namePozo,String inter,int lastCon){
        Map pars=new HashMap();   
         try {                
             pars.put("namePozo", namePozo);
             pars.put("intervencion", inter);
             pars.put("error", "");
             pars.put("lastCon", lastCon);
             if(listaNpt.size()>0){
                 if(listaNpt.get(0).getConsecutivo()==-100){                                  
                    pars.put("error" ,"ERROR NÚMERO: "+listaNpt.get(0).getIntervencion() + " Fila: " + listaNpt.get(0).getProfundidad() + " Columna " + listaNpt.get(0).getTiempo());
                 }else{                                 
                    pars.put("listNpt", new JRBeanCollectionDataSource(listaNpt));                                          
                }             
             }
             
             JasperReport jasperReport = null;             
             jasperReport = (JasperReport) JRLoader.loadObject("report/MuestraInter.jasper");             
             JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, pars, new JREmptyDataSource());            
             JasperExportManager.exportReportToPdfFile(jasperPrint,"report/Visualiza"+tipo+".pdf");   
             JasperViewer.viewReport(jasperPrint, false);   
                      
             //
             String csvFileName = "report/lol.xls";
            JRExporter exporter = new JRCsvExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, csvFileName);
            exporter.exportReport();
             
                     
             //
                  
             
             
                     
         } catch (Exception e) {         
             System.out.print("el error " +e.getMessage());             
         }
        
    }
    
    public static void PrintPrograma(List<Programas>listaProgramas,int tipo,String namePozo,String inter){
        Map pars=new HashMap();   
         try {                
             pars.put("namePozo", namePozo);
             pars.put("intervencion", inter);
             pars.put("error", "");             
                                            
             pars.put("listProgramas", new JRBeanCollectionDataSource(listaProgramas));
              
             JasperReport jasperReport = null;             
             jasperReport = (JasperReport) JRLoader.loadObject("report/MuestraPrograma.jasper");             
             JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, pars, new JREmptyDataSource());            
             JasperExportManager.exportReportToPdfFile(jasperPrint,"report/VisualizaPrograma"+tipo+".pdf");
             JasperViewer.viewReport(jasperPrint, false);             
         } catch (Exception e) {         
             System.out.print("el error " +e.getMessage());             
         }
        
    }
    
    
}
