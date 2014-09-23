/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.CatEquipo;
import com.stin.CatIntervencion;
import com.stin.CatPlataforma;
import com.stin.CatSubIntervencion;
import com.stin.DAO.GenericDao;
import com.stin.Intervencion;
import com.stin.Pozo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class IntervencionDAO {
    
    
    public static Intervencion insertIntervencion(int tipoInter,int idPozo) throws DBException{
        Intervencion intervencion=new Intervencion();
        CatIntervencion catIntervencion=new CatIntervencion(tipoInter);
        Pozo pozo=new Pozo();
        pozo.setIdPozo(idPozo);
        intervencion.setPozo(pozo);
        intervencion.setCatIntervencion(catIntervencion);        
        try {
            GenericDao.newGeneric(intervencion);
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return intervencion;
    }
    
     public static boolean insertIntervencionAnio(int idPozo,int tipoInter, int anio, int idCatSubIntervencion,int idCatPlataforma,int idEquipo) throws DBException{
         boolean res=false;
         Intervencion intervencion=new Intervencion();         
         CatIntervencion catIntervencion=new CatIntervencion(tipoInter);        
         Pozo pozo=new Pozo();
         pozo.setIdPozo(idPozo);
         
         CatSubIntervencion catSubintervencion=new CatSubIntervencion();
         catSubintervencion.setIdCatSubIntervencion(idCatSubIntervencion);
         
         CatPlataforma catPlataforma=new CatPlataforma();
         catPlataforma.setIdPlataforma(idCatPlataforma);
         
         CatEquipo catEquipo=new CatEquipo();
         catEquipo.setIdEquipo(idEquipo);
         
         
         intervencion.setPozo(pozo);
         intervencion.setCatIntervencion(catIntervencion);   
         intervencion.setAnio(anio);
         intervencion.setCatSubIntervencion(catSubintervencion);
         intervencion.setCatPlataforma(catPlataforma);
         intervencion.setCatEquipo(catEquipo);
         
         
         try {               
             GenericDao.newGeneric(intervencion);                 
             res=true;
             intervencion=null;
             catSubintervencion=null;
             catPlataforma=null;
             catEquipo=null;
             pozo=null;
         } catch (Exception e) {                
             throw new DBException(e.getMessage(), e);            
         }            
         return res;
    }
    
     
    public static boolean setUpdateIntervencion(int idIntervencion,int idPozo,int idTipo,int anio, int idCatSubIntervencion,int idCatPlataforma,int idEquipo){
        boolean res=false;
        Intervencion intervencion=new Intervencion();
        
        
        Pozo pozo=new Pozo();
        pozo.setIdPozo(idPozo);        
        
        
        CatIntervencion catIntervencion=new CatIntervencion(idTipo);
        
        CatPlataforma catPlataforma=new CatPlataforma();
        catPlataforma.setIdPlataforma(idCatPlataforma);
        
        CatEquipo catEquipo=new CatEquipo();
        catEquipo.setIdEquipo(idEquipo);
        
        CatSubIntervencion catSubIntervencion=new CatSubIntervencion();
        catSubIntervencion.setIdCatSubIntervencion(idCatSubIntervencion);
        
        intervencion.setIdIntervencion(idIntervencion);
        intervencion.setCatIntervencion(catIntervencion);
        intervencion.setPozo(pozo);
        intervencion.setAnio(anio);
        intervencion.setCatPlataforma(catPlataforma);
        intervencion.setCatEquipo(catEquipo);
        intervencion.setCatSubIntervencion(catSubIntervencion);
        
        try {
            GenericDao.setUpdate(intervencion);
            intervencion=null;
            pozo=null;
            catIntervencion=null;
            res=true;
        } catch (Exception e) {
            System.out.println("ERROR IntervencionDAO.setUpdateIntervencion: " + e.getMessage());
        }
        
        return res;                        
    }
    
     
    
    public static int searchIntervencionByTipoAndPozo(int idPozo,int tipo , int idSubInter){
        int intervencion=0;
        String hql="SELECT MAX(I.idIntervencion) FROM Intervencion I  WHERE I.pozo.idPozo="+idPozo+" AND I.catIntervencion.idTipo="+tipo +
                " AND I.catSubIntervencion.idCatSubIntervencion=" +idSubInter;
        try {
            intervencion=(Integer) GenericDao.searchBySpecific(hql);
        } catch (Exception e) {
            System.out.println("ERROR IntervencionDAO.searchIntervencionByTipoAndPozo: " + e.getMessage());
        }
        return intervencion;    
    }
    
    public static Long searchCountIntervencion(int idPozo){
        String hql="SELECT COUNT(I) FROM Intervencion I WHERE I.pozo.idPozo="+ idPozo +"";
        try {
            return (Long)GenericDao.searchBySpecific(hql);
        } catch (Exception e) {
            System.out.println("ERROR IntervencionDAO.searchCountIntervencion: "+ e.getMessage());
        }
        return 0l;
    }
    
}


