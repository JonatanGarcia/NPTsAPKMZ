/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.CatPlataforma;
import com.stin.DAO.GenericDao;
import java.util.List;

/**
 *
 * @author Marco
 */
public class CatPlataformaDAO {
    
     public static boolean setNewCatPlataforma(String nombrePlataforma){
        try {
            CatPlataforma catPlataforma=new CatPlataforma();
            catPlataforma.setStrNombre(nombrePlataforma);
            GenericDao.newGeneric(catPlataforma);
            catPlataforma=null;
            return true;
        } catch (Exception e) {
            System.out.println("ERROR CatPlataformaDAO.setNewCatEquipo: " + e.getMessage());
        }
        return false;
    }
    
    public static List<CatPlataforma> getListCatPlataforma(boolean todo, String buscar){
        List<CatPlataforma>lista=null;
        String hql="SELECT C FROM CatPlataforma C ORDER BY C.strNombre";
        if(!todo){
            hql="SELECT C FROM CatPlataforma C WHERE C.strNombre LIKE '%" + buscar + "%'";
        }
        try {
            lista=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR CatPlataformaDAO.getListCatPlataforma: " + e.getMessage());
        }
        return lista;
    }
    public static boolean setUpdateCatPlataforma(CatPlataforma catPlataforma){
        try {
            GenericDao.setUpdate(catPlataforma);
            return true;
        } catch (Exception e) {
            System.out.println("ERROR CatPlataformaDAO.setUpdateCatPlataforma: " + e.getMessage());
        }
        return false;
    }
}
