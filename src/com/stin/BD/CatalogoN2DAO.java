/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.CatNptsN1;
import com.stin.CatNptsN2;
import com.stin.DAO.GenericDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class CatalogoN2DAO {
    
    public static boolean setNewCatN2(String N2,int idN1){
        CatNptsN2 catNptsN2=new CatNptsN2();
        CatNptsN1 catNptsN1=new CatNptsN1(idN1);        
        catNptsN2.setStrNombre(N2);
        catNptsN2.setCatNptsN1(catNptsN1);
        boolean respuesta =false;
        try {
            GenericDao.newGeneric(catNptsN2);
            respuesta=true;
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN2DAO.setNewCatN1(): " + e.getMessage());
        }
        return respuesta;
    }
    public static List<CatNptsN2>getListCatN2(boolean todo,String buscar){
        List<CatNptsN2>lista=new ArrayList<CatNptsN2>();        
        //String hql="SELECT N2 FROM CatNptsN2 N2,CatNptsN1 N1 WHERE N1.idNptn1=N2.catNptsN1.idNptn1";
        String hql="SELECT N2 FROM CatNptsN2 N2 INNER JOIN FETCH N2.catNptsN1 ORDER BY N2.strNombre";
        if(!todo){
            //hql="SELECT N2 FROM CatNptsN2 N2,CatNptsN1 N1 WHERE N1.idNptn1=N2.catNptsN1.idNptn1 and N2.strNombre LIKE '%"+ buscar +"%'";
            //hql="SELECT N2 FROM CatNptsN2 N2 WHERE N2.strNombre LIKE '%"+ buscar +"%'";
            hql="SELECT N2 FROM CatNptsN2 N2 INNER JOIN FETCH N2.catNptsN1 WHERE N2.strNombre LIKE '%"+ buscar +"%'";
        }

            try {
                lista=GenericDao.GetAllGeneric(hql);
            } catch (Exception e) {
                System.out.println("ERROR CatalogoN2DAO.getListCatN2(): " + e.getMessage());
            }
        return lista;
    }
       public static CatNptsN2 getCatN2(int id){
        CatNptsN2 catNptsN2=new CatNptsN2();
        String hql="SELECT N2 FROM CatNptsN2 N2 WHERE N2.idNptn2="+ id + "";
        try {
            catNptsN2=(CatNptsN2)GenericDao.searchBySpecific(hql);            
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN1DAO.getCatN1(): " + e.getMessage());
        }
            
        return catNptsN2;
    }
       public static boolean setUpdate(CatNptsN2 catNptsN2){
        try {
            GenericDao.setUpdate(catNptsN2);
            return true;
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN2DAO.setUpdate: " + e.getMessage());
        }
        return false;
    }
}
