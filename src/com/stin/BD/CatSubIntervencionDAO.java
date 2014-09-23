/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.CatSubIntervencion;
import com.stin.DAO.GenericDao;
import java.util.List;

/**
 *
 * @author Marco
 */
public class CatSubIntervencionDAO {
    public static boolean setNewCatSubIntervencion(CatSubIntervencion catSubIntervencion){
        try {
            GenericDao.newGeneric(catSubIntervencion);
        } catch (Exception e) {
            System.out.println("ERROR CatSubIntervencionDAO.setNewCatSubIntervencion: " + e.getMessage());
        }
        return false;
    }
    
    public static List<CatSubIntervencion> getListCatSubIntervencion(boolean todo, String buscar){
        List<CatSubIntervencion> lista=null;
        String hql="SELECT C FROM CatSubIntervencion C";
        
        if (!todo){
            hql="SELECT C FROM CatSubIntervencion C WHERE C.strNombre LIKE '" + buscar + "'";
        }
        try {
            lista=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR CatSubIntervencionDAO.getListCatSubIntervencion: " + e.getMessage());
        }
        return lista;
    }
    
    public static boolean setUpdateCatSubIntervencion(CatSubIntervencion catSubIntervencion){
        try {
            GenericDao.setUpdate(catSubIntervencion);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
