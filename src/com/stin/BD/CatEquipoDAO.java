/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.CatCompania;
import com.stin.CatEquipo;
import com.stin.DAO.GenericDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class CatEquipoDAO {
    
    public static boolean setNewCatEquipo(String nombreEquipo,int idCompania){
        try {
            CatEquipo catEquipo=new CatEquipo();
            catEquipo.setStrNombre(nombreEquipo);
            
            CatCompania catCompania=new CatCompania();
            catCompania.setIdCatCompania(idCompania);
            catEquipo.setCatCompania(catCompania);
            
            GenericDao.newGeneric(catEquipo);
            catEquipo=null;
            catCompania=null;
            return true;
        } catch (Exception e) {
            System.out.println("ERROR CatEquipoDAO.setNewCatEquipo: " + e.getMessage());
        }
        return false;
    }
    
    public static List<CatEquipo> getListCatEquipo(boolean todo, String buscar){
        List<CatEquipo>lista=new ArrayList<CatEquipo>();
        String hql="SELECT C FROM CatEquipo C INNER JOIN FETCH C.catCompania C2 ORDER BY C.strNombre";
        if(!todo){
            hql="SELECT C FROM CatEquipo C INNER JOIN FETCH C.catCompania C2 WHERE C.strNombre LIKE '%" + buscar + "%'";
        }
        try {
            lista=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR CatEquipoDAO.getListCatEquipo: " + e.getMessage());
        }
        return lista;
    }
    public static boolean setUpdateCaTEquipo(CatEquipo catEquipo){
        try {
            GenericDao.setUpdate(catEquipo);
            return true;
        } catch (Exception e) {
            System.out.println("EEROR CatEquipoDAO.setUpdateCaTEquipo: " + e.getMessage());
        }
        return false;
    }
    
}
