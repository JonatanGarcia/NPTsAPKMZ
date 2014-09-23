/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.CatEtapa;
import com.stin.CatTr;
import com.stin.DAO.GenericDao;
import com.stin.Intervencion;
import com.stin.NptIntervencion;
import com.stin.Terminacion;
import com.stin.pemex.siop.model.Npt;
import com.stin.pemex.siop.util.SiopUtils;

/**
 *
 * @author Marco
 */
public class TerminacionDAO {
    
    public static void insertTerminacion(Npt npt) throws Exception{
        Terminacion terminacion=new Terminacion();
  
            
            terminacion.setDateOperacion(SiopUtils.formatDate(npt.getFecha()));
            terminacion.setFloatTiempo((double)npt.getTiempo());
            terminacion.setIntCon(npt.getConsecutivo());
            terminacion.setIntProf(npt.getProfundidad());
            
            Intervencion intervencion=new Intervencion();
            intervencion.setIdIntervencion(npt.getIntervencion());            
            terminacion.setIntervencion(intervencion);
            
            NptIntervencion nptIntervencion=new NptIntervencion();
            nptIntervencion.setIdNptIntervencion(npt.getIdNptIntervencion());
            terminacion.setNptIntervencion(nptIntervencion);
            
            terminacion.setStrResumen(npt.getResumen());            
            terminacion.setStrDetalle(npt.getStrDetalle());
            
            CatEtapa catEtapa=new CatEtapa();
            catEtapa.setIdEtapa(npt.getEtapa());
            
            terminacion.setCatEtapa(catEtapa);
            
            
            CatTr catTr=new CatTr();
            catTr.setIdCatTr(npt.getTr());
            terminacion.setCatTr(catTr);
            
            GenericDao.newGeneric(terminacion);
            catEtapa=null;
            catTr=null;
            intervencion=null;
            nptIntervencion=null;
            terminacion=null;
    
             
    }
    
    public static boolean setUpdateTerm(Terminacion t){
        boolean resp=false;
        try {
            GenericDao.setUpdate(t);
            resp=true;
        } catch (Exception e) {
            System.out.print("ERROR TerminacionDAO.setUpdateTerm " + e.getMessage());
        }
        return resp;
    }
    
     
    public static int setDeleteTerminacion (int id){
        String hql="DELETE Terminacion T WHERE T.intervencion.idIntervencion="+ id + "";
        try {
            return GenericDao.deleteSpecificDelete(hql);
        } catch (Exception e) {
            System.out.println("Error PerforacionDAO.setDeletePerforacion " + e.getMessage());
        }
        
        return 0;
    }
    
}
