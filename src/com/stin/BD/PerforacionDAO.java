/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.CatEtapa;
import com.stin.CatTr;
import com.stin.pemex.siop.model.Npt;
import com.stin.pemex.siop.util.SiopUtils;
import com.stin.DAO.GenericDao;
import com.stin.Intervencion;
import com.stin.NptIntervencion;
import com.stin.Perforacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class PerforacionDAO {
    
    public static int searchPerfConseByInter(int idIntervencion,int tipoIntervencion){
        int id=0;        
        String hql="";
        
        if (tipoIntervencion==1){
            hql="SELECT MAX(p.intCon) FROM Perforacion p WHERE p.intervencion.idIntervencion="+ idIntervencion + "";            
        }else if(tipoIntervencion==2){
            hql="SELECT MAX(p.intCon) FROM Terminacion p WHERE p.intervencion.idIntervencion="+ idIntervencion + "";            
        }else{
            hql="SELECT MAX(p.intCon) FROM Rma p WHERE p.intervencion.idIntervencion="+ idIntervencion + "";
        }
        //System.out.println("-------->"+hql);
        try {                               
            id=(Integer) GenericDao.searchBySpecific(hql);
        } catch (Exception e) {
            System.out.println("ERROR PerforacionDAO.searchPerfConseByInter " + e.getMessage());
        }
        return id;
    }
    public static void insertPerforacion(Npt npt) throws Exception{
        Perforacion perforacion=new Perforacion();
            perforacion.setDateOperacion(SiopUtils.formatDate(npt.getFecha()));
            perforacion.setFloatTiempo((double)npt.getTiempo());
            perforacion.setIntCon(npt.getConsecutivo());
            perforacion.setIntProf(npt.getProfundidad());
            
            Intervencion intervencion=new Intervencion();
            intervencion.setIdIntervencion(npt.getIntervencion());            
            perforacion.setIntervencion(intervencion);
            
            NptIntervencion nptIntervencion=new NptIntervencion();
            nptIntervencion.setIdNptIntervencion(npt.getIdNptIntervencion());
            perforacion.setNptIntervencion(nptIntervencion);
            
            perforacion.setStrResumen(npt.getResumen());            
            perforacion.setStrDetalle(npt.getStrDetalle());     
            
            //
            
            CatEtapa catEtapa=new CatEtapa();
            catEtapa.setIdEtapa(npt.getEtapa());
            
            perforacion.setCatEtapa(catEtapa);
            
            
            CatTr catTr=new CatTr();
            catTr.setIdCatTr(npt.getTr());
            perforacion.setCatTr(catTr);
            
            GenericDao.newGeneric(perforacion);
            catEtapa=null;
            catTr=null;
            intervencion=null;
            nptIntervencion=null;
        perforacion=null;
    }
    /*
    public static List<Npt> getListNpts(int idPozo, int idIntervencion){
        List<Npt> listNpts=new ArrayList<Npt>();
        
        String hql="SELECT P FROM Perforacion P WHERE P.intervencion.idIntervencion=" + idIntervencion ;
        List<Perforacion> listaP=new ArrayList<Perforacion>();
        try {
            listaP=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR  PerforacionDAO.getListNpts: " + e.getMessage());
        }
        double tiempo=0;
        for(Perforacion p : listaP){
            tiempo=p.getFloatTiempo();
            listNpts.add(new Npt(idPozo,idIntervencion,p.getIntCon(),"",
                    p.getIntProf(),(float)tiempo,p.getNptIntervencion().getIdNptIntervencion(),
                    p.getStrResumen(),p.getStrDetalle()));
        }
        
        for(int i=0;i<listaP.size();i++){
            System.out.println(listaP.get(i).getIdPerforacion() + " "+
                    listaP.get(i).getIntervencion().getIdIntervencion() + "" +
                    listaP.get(i).getIntCon()+""+
                    listaP.get(i).getDateOperacion() + " " + 
                    listaP.get(i).getIntProf() + " "+ 
                    listaP.get(i).getStrResumen() + " " +
                    listaP.get(i).getFloatTiempo()+" "+
                    listaP.get(i).getNptIntervencion().getIdNptIntervencion() +" " +
                    listaP.get(i).getStrDetalle());
        }
        
        /*
       int pozo, 
         * int intervencion,
         * int consecutivo,
         * String fecha, 
         * int profundidad, 
         * float tiempo, 
         * int idNptIntervencion, 
         * String resumen,
         * String strDetalle
         */
        
//        return listNpts;
                
 //   }



    public static List<Object> getGenericIntervencion(int idPozo, int idTipo, int consecutivo, String tabla){
        List<Object> listaPer=new ArrayList<Object>();
        try {
            String hql="SELECT P FROM "+tabla+" P INNER JOIN FETCH P.intervencion I INNER JOIN FETCH I.pozo Po "+
	               " INNER JOIN FETCH P.nptIntervencion NP INNER JOIN FETCH NP.catActividades ACT "+
	               " INNER JOIN FETCH NP.catNptsN4 N4 INNER JOIN FETCH I.catSubIntervencion CSI "+
                       " INNER JOIN FETCH P.catEtapa CE INNER JOIN FETCH P.catTr CT " + 
                       " WHERE Po.idPozo="+idPozo+" AND I.catIntervencion.idTipo="+idTipo + " ORDER BY P.intCon" ;	
            if(consecutivo>0){
                hql="SELECT P FROM "+tabla+" P INNER JOIN FETCH P.intervencion I INNER JOIN FETCH I.pozo Po "+
	               " INNER JOIN FETCH P.nptIntervencion NP INNER JOIN FETCH NP.catActividades ACT "+
	               " INNER JOIN FETCH NP.catNptsN4 N4 INNER JOIN FETCH I.catSubIntervencion CSI " +
                       " INNER JOIN FETCH P.catEtapa CE INNER JOIN FETCH P.catTr CT " + 
                       " WHERE Po.idPozo="+idPozo+" AND I.catIntervencion.idTipo="+idTipo + " AND P.intCon="+consecutivo;
            }
            
            listaPer=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR  PerforacionDAO.getPerforacion: " + e.getMessage());
        }
        System.out.println("TAMAÑO DE LA LISTA: " + listaPer.size());
        return listaPer;
    }
    
    public static boolean setUpdatePerforacion(Perforacion P){
        boolean res=false;
        
        try {            
            GenericDao.setUpdate(P);
            res=true;
        } catch (Exception e) {
             System.out.println("ERROR  PerforacionDAO.setUpdatePerforacion"+e.getMessage());
        }
        return res;
    }
    
    public static int setDeletePerforacion (int id){
        String hql="DELETE Perforacion P WHERE P.intervencion.idIntervencion="+ id + "";
        try {
            return GenericDao.deleteSpecificDelete(hql);
        } catch (Exception e) {
            System.out.println("Error PerforacionDAO.setDeletePerforacion " + e.getMessage());
        }
        
        return 0;
    }
}
