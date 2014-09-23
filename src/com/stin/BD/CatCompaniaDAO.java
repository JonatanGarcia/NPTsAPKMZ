/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.CatCompania;
import com.stin.DAO.GenericDao;
import java.util.List;

/**
 *
 * @author Marco
 */
public class CatCompaniaDAO {
   
    public static boolean setNewCatCompania(String nombreCompania){
        try {
            CatCompania catCompania=new CatCompania();
            catCompania.setStrNombre(nombreCompania);
            GenericDao.newGeneric(catCompania);
            catCompania=null;
            return true;
        } catch (Exception e) {
            System.out.println("ERROR CatCompaniaDAO.setNewCatCompania: " + e.getMessage());
        }
        return false;
    }
   
    public static List<CatCompania> getListCatCompania(boolean todo, String buscar){
        String hql="SELECT C FROM CatCompania C ORDER BY C.strNombre";
        if(!todo){
            hql="SELECT C FROM CatCompania C WHERE C.strNombre LIKE '%"+ buscar +"%'";
        }
        List<CatCompania>lista=null;
        try {
            lista=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR CatCompaniaDAO.getListCatCompania: " + e.getMessage());
        }  
        return lista;
    }
    
    public static boolean setUpdateCatCompania(CatCompania catCompania){
        boolean respuesta=false;
        try {
            GenericDao.setUpdate(catCompania);
            respuesta=true;
        } catch (Exception e) {
            System.out.println("ERROR CatCompaniaDAO.setUpdateCatCompania: " + e.getMessage());
        }
        return respuesta;
    }
    
    
}
