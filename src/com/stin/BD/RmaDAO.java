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
import com.stin.Rma;
import com.stin.pemex.siop.model.Npt;
import com.stin.pemex.siop.util.SiopUtils;
import java.util.List;

/**
 *
 * @author Marco
 */
public class RmaDAO {

    
    
    public static void insertRma(Npt npt) throws Exception{
        Rma rma=new Rma();
                  
            rma.setDateOperacion(SiopUtils.formatDate(npt.getFecha()));
            rma.setFloatTiempo((double)npt.getTiempo());
            rma.setIntCon(npt.getConsecutivo());
            rma.setIntProf(npt.getProfundidad());
            
            Intervencion intervencion=new Intervencion();
            intervencion.setIdIntervencion(npt.getIntervencion());               
            rma.setIntervencion(intervencion);
            
            NptIntervencion nptIntervencion=new NptIntervencion();
            nptIntervencion.setIdNptIntervencion(npt.getIdNptIntervencion());
            rma.setNptIntervencion(nptIntervencion);
            
            rma.setStrResumen(npt.getResumen());            
            rma.setStrDetalle(npt.getStrDetalle());
                     
            CatEtapa catEtapa=new CatEtapa();
            catEtapa.setIdEtapa(npt.getEtapa());
            
            rma.setCatEtapa(catEtapa);
            
            
            CatTr catTr=new CatTr();
            catTr.setIdCatTr(npt.getTr());
            rma.setCatTr(catTr);
            
            GenericDao.newGeneric(rma);
            catEtapa=null;
            catTr=null;
            intervencion=null;
            nptIntervencion=null;
            rma=null;
 
        
    }
    
    
    public static boolean setUpdateRma(Rma rma){
        boolean resp=false;
        try {
            GenericDao.setUpdate(rma);
            resp=true;
        } catch (Exception e) {
            System.out.println("ERROR RmaDAO.setUpdateRma: " + e.getMessage());
        }
        return resp;
    }       
     public static int setDeleteRMA (int id){
        String hql="DELETE Rma R WHERE R.intervencion.idIntervencion="+ id + "";
        try {
            return GenericDao.deleteSpecificDelete(hql);
        } catch (Exception e) {
            System.out.println("Error PerforacionDAO.setDeletePerforacion " + e.getMessage());
        }
        
        return 0;
    }
    

}
