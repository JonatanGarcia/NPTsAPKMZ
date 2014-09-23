/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;


import com.stin.CatIntervencion;
import com.stin.DAO.GenericDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class CatIntervencionDAO {
    
    
    public static boolean setNewCatIntervencion(CatIntervencion catIntervencion){
        boolean respuesta=false;
        try {
            GenericDao.newGeneric(catIntervencion);
            respuesta=true;
        } catch (Exception e) {
            System.out.print("ERROR CatIntervencionDAO.setNewCatIntervencion: " + e.getMessage());
        }
        return respuesta;
    }
    
     public static List<CatIntervencion>getListCatIntervencion(){
        String hql="SELECT I FROM CatIntervencion I";
        
        List<CatIntervencion> lista=new ArrayList<CatIntervencion>();
        try {
            lista=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN1DAO.getListCatN1(): " + e.getMessage());
        }               
        return lista;
    }
    
}
