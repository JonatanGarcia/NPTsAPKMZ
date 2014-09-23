/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.CatEtapa;
import com.stin.DAO.GenericDao;
import java.util.List;

/**
 *
 * @author Marco
 */
public class CatEtapaDAO {
    
    public static boolean setNewEtapa(String nombreEtapa){
        CatEtapa catEtapa=new CatEtapa();
        catEtapa.setStrNombre(nombreEtapa);
        try {
            GenericDao.newGeneric(catEtapa);
            return true;
        } catch (Exception e) {
            System.out.println("ERROR CatEtapaDAO.setNewEtapa: " + e.getMessage());
        }
        return false;
    }
    
    public static List<CatEtapa> getListCatEtapa(boolean todo, String buscar){
        List<CatEtapa>lista=null;
        String hql="SELECT C FROM CatEtapa C ORDER BY C.idEtapa";
        if(!todo){
            hql="SELECT C FROM CatEtapa C WHERE C.strNombre LIKE '%" + buscar + "%'";
        }
        try {
            lista=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR CatEtapaDAO.getListCatEtapa: " + e.getMessage());
        }
        return lista;
    }
    
    public static boolean setUpdateCatEtapa(CatEtapa catEtapa){
        try {
            GenericDao.setUpdate(catEtapa);
            return true;
        } catch (Exception e) {
            System.out.println("EEROR CatEtapaDAO.setUpdateCatEtapa: " + e.getMessage());
        }
        return false;
    }
     public static int searchIdEtapa(String tr){
        int idInter=0;
        String hql="SELECT C.idEtapa FROM CatEtapa C WHERE C.strNombre='"+ tr +"'";
        try {
            idInter=(Integer) GenericDao.searchBySpecific(hql);
        } catch (Exception e) {
            System.out.println("ERROR IntervencionDAO.searchIntervencionByTipoAndPozo: " + e.getMessage());
        }
        return idInter;    
    }
      public static CatEtapa getCatEtapa(String strNombre) throws Exception{
        CatEtapa catEtapa=null;
            String hql="SELECT Cat FROM CatEtapa Cat WHERE Cat.strNombre='" + strNombre + "'";
            catEtapa=(CatEtapa)GenericDao.searchBySpecific(hql);
        return catEtapa;
    }
     
}
