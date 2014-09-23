/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.CatNptsN3;
import com.stin.CatNptsN4;
import com.stin.DAO.GenericDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class CatalogoN4DAO {
    
    public static boolean setNewCatN4(String N4,int idN3){
        
        CatNptsN4 catNptsN4=new CatNptsN4();
        CatNptsN3 catNptsN3=new CatNptsN3();
        catNptsN3.setIdNptn3(idN3);
        
        catNptsN4.setCatNptsN3(catNptsN3);
        catNptsN4.setStrNombre(N4);
        
        
        
        boolean respuesta=false;        
        try {
            GenericDao.newGeneric(catNptsN4);                        
            respuesta=true;
        } catch (Exception e) {
          System.out.println("ERROR CatalogoN4DAO.setNewCatN4: " + e.getMessage());
        }        
        return respuesta;
    }
    public static List<CatNptsN4>getListNptnIntervencion(int idActividad,int idTipo){
        List<CatNptsN4>lista=new ArrayList<CatNptsN4>();
        String hql="SELECT N4 FROM CatNptsN4 N4 WHERE N4.idNptn4 NOT IN(SELECT NP.catNptsN4.idNptn4 FROM NptIntervencion NP WHERE NP.catActividades.idActividad="+idActividad+" AND NP.catIntervencion.idTipo="+idTipo+") ORDER BY N4.strNombre";        
        try {
            lista=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN4DAO.getListNptnIntervencion: " + e.getMessage());
        }
        return lista;
    }
    public static List<CatNptsN4>getListCatN4(boolean todo, String buscar){
        List<CatNptsN4>lista=new ArrayList<CatNptsN4>();
        String hql="SELECT N4 FROM CatNptsN4 N4 INNER JOIN FETCH N4.catNptsN3 N3 INNER JOIN FETCH N3.catNptsN2 N2 INNER JOIN FETCH N2.catNptsN1 ORDER BY N4.strNombre";
        if(!todo){
            hql="SELECT N4 FROM CatNptsN4 N4 INNER JOIN FETCH N4.catNptsN3 N3 INNER JOIN FETCH N3.catNptsN2 N2 INNER JOIN FETCH N2.catNptsN1 WHERE N4.strNombre LIKE '%" + buscar + "%'";
        }
        try {
            lista=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN4DAO.getListCatn4: " + e.getMessage());
        }
        return lista;
    }
    
    public static CatNptsN4 getCatN4(int id){
        CatNptsN4 catNptsN4=new CatNptsN4();
        String hql="SELECT N4 FROM CatNptsN4 N4 WHERE N4.idNptn4="+ id + "";
        try {
            catNptsN4=(CatNptsN4)GenericDao.searchBySpecific(hql);            
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN4DAO.getCatN1(): " + e.getMessage());
        }
            
        return catNptsN4;
    }
    public static boolean setUpdate(CatNptsN4 catNptsN4){
        try {
            GenericDao.setUpdate(catNptsN4);
            return true;
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN4DAO.setUpdate: " + e.getMessage());
        }
        return false;
    }
    public static int getIdNpts (String busca) {
        int id=0;
        if(busca.equals(""))
            return 0;
            
        String hql="SELECT N4.idNptn4 FROM CatNptsN4 N4 WHERE N4.strNombre ='"+ busca.trim().toUpperCase() + "'" ;        
        try {
            id=(Integer)GenericDao.searchBySpecific(hql);
        } catch (Exception e) {
            System.out.println("ERROR CatalogoN4DAO.getIdNpts: " + e.getMessage());
        }
                
        return id;
    }
            
}
