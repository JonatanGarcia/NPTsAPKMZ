/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.CatNptsN2;
import com.stin.CatNptsN3;
import com.stin.DAO.GenericDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class CatalogoN3DAO {
    
    public static boolean setNewCatN3(String N3, int idN2){
        CatNptsN3 catNptsN3=new CatNptsN3();
        CatNptsN2 catNptsN2=new CatNptsN2();
        catNptsN2.setIdNptn2(idN2);
        catNptsN3.setCatNptsN2(catNptsN2);
        catNptsN3.setStrNombre(N3);
        boolean respuesta=false;
        try {
            GenericDao.newGeneric(catNptsN3);
            respuesta=true;
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN3DAO.setNewCatN3: " + e.getMessage());
        }
        return respuesta;                
    }
    
    public static List<CatNptsN3>getListCatN3(boolean todo,String buscar){
        List<CatNptsN3> lista=new ArrayList<CatNptsN3>();
        
        String hql="SELECT N3 FROM CatNptsN3 N3 INNER JOIN FETCH N3.catNptsN2 N2 INNER JOIN FETCH N2.catNptsN1 ORDER BY N3.strNombre";
        if(!todo){
            hql="SELECT N3 FROM CatNptsN3 N3 INNER JOIN FETCH N3.catNptsN2 N2 INNER JOIN FETCH N2.catNptsN1 WHERE N3.strNombre LIKE '%"+ buscar +"%'";
        }
        try {
            lista=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN3DAO.getListCatN3 " + e.getMessage());;
        }        
        return lista;
        
    }
    public static CatNptsN3 getCatN3(int id){
        CatNptsN3 catNptsN3=new CatNptsN3();
        String hql="SELECT N3 FROM CatNptsN3 N3 WHERE N3.idNptn3="+ id + "";
        try {
            catNptsN3=(CatNptsN3)GenericDao.searchBySpecific(hql);            
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN1DAO.getCatN1(): " + e.getMessage());
        }
            
        return catNptsN3;
    }
      public static boolean setUpdate(CatNptsN3 catNptsN3){
        try {
            GenericDao.setUpdate(catNptsN3);
            return true;
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN1DAO.setUpdate: " + e.getMessage());
        }
        return false;
    }
    
}

