/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;


import com.stin.CatNptsN1;
import com.stin.DAO.GenericDao;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marco
 */
public class CatalogoN1DAO {
    
       public static boolean setNewCatN1(String N1){
        CatNptsN1 catNptsN1=new CatNptsN1();
        catNptsN1.setBitVisible(true);
        catNptsN1.setStrNombre(N1);
        boolean respuesta=false;
        try {
            GenericDao.newGeneric(catNptsN1);
            respuesta=true;
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN1DAO.setNewCatN1: " + e.getMessage());
        }
        return respuesta;
        
    }
    public static List<CatNptsN1>getListCatN1(boolean todo, String buscar){
        String hql="SELECT N1 FROM CatNptsN1 N1 ORDER BY N1.strNombre";
        if (!todo){
            hql="SELECT N1 FROM CatNptsN1 N1 WHERE N1.strNombre LIKE '%"+ buscar +"%'";
        }
/*        }else{
            hql="SELECT N1 FROM CatNptsN1 N1 WHERE N1.strNombre LIKE '%"+ buscar +"%'";
        }*/
   
        List<CatNptsN1> lista=new ArrayList<CatNptsN1>();
        try {
            lista=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN1DAO.getListCatN1(): " + e.getMessage());
        }               
        return lista;
    }
        
    public static CatNptsN1 getCatN1(int id){
        CatNptsN1 catNptsN1=new CatNptsN1();
        String hql="SELECT N1 FROM CatNptsN1 N1 WHERE N1.idNptn1="+ id + "";
        try {
            catNptsN1=(CatNptsN1)GenericDao.searchBySpecific(hql);            
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN1DAO.getCatN1(): " + e.getMessage());
        }
            
        return catNptsN1;
    }
    
    public static boolean setUpdate(CatNptsN1 catNptsN1){
        try {
            GenericDao.setUpdate(catNptsN1);
            return true;
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN1DAO.setUpdate: " + e.getMessage());
        }
        return false;
    }
    
}
