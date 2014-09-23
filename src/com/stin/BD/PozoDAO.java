/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.DAO.GenericDao;
import com.stin.Intervencion;
import com.stin.Pozo;
import com.stin.pemex.siop.model.Npt;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class PozoDAO {
    
    public static boolean setNewPozo(Pozo pozo,int idIntervencion,int anio, int idCatSubIntervencion,int idCatPlataforma,int idEquipo, boolean insertaPozo){
        boolean respuesta=false;
        
        try {            
            if (insertaPozo){
                GenericDao.newGeneric(pozo);
            }
            IntervencionDAO.insertIntervencionAnio(pozo.getIdPozo(), idIntervencion, anio, idCatSubIntervencion, idCatPlataforma, idEquipo);
            respuesta=true;
        } catch (Exception e) {
            System.out.println("ERROR PozoDAO.setNewPozo: " + e.getMessage());
        }
    
        return respuesta;
    }
    
     
    public static boolean setUpdatePozo(int idIntervencion,Pozo pozo, int idTipo,int anio, int idCatSubIntervencion,int idCatPlataforma,int idEquipo){
        boolean respuesta=false;
        try {
            GenericDao.setUpdate(pozo);
            IntervencionDAO.setUpdateIntervencion(idIntervencion, pozo.getIdPozo(), idTipo, anio, idCatSubIntervencion, idCatPlataforma, idEquipo);
            respuesta=true;
        } catch (Exception e) {
            System.out.println("ERROR PozoDAO.setUpdatePozo: " + e.getMessage());
        }
        return respuesta;
    }
    
    public static List<Intervencion>getListPozoInter(boolean todo,String buscar){
        List<Intervencion> lista=new ArrayList<Intervencion>();
        String hql="SELECT I FROM Intervencion I INNER JOIN FETCH I.catPlataforma PL" +
		  		" INNER JOIN FETCH I.catSubIntervencion SUB " +
		  		" INNER JOIN FETCH I.catEquipo EQ "+
		  		" INNER JOIN FETCH I.pozo P "+ 
		  		" INNER JOIN FETCH I.catIntervencion CATINTER";
        if(!todo){
            hql="SELECT I FROM Intervencion I INNER JOIN FETCH I.catPlataforma PL" +
		  		" INNER JOIN FETCH I.catSubIntervencion SUB " +
		  		" INNER JOIN FETCH I.catEquipo EQ "+
		  		" INNER JOIN FETCH I.pozo P "+ 
		  		" INNER JOIN FETCH I.catIntervencion CATINTER  WHERE P.strNombre LIKE '%"+buscar+"%'";
        }
        try {            
            lista=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR PozoDAO.getListPozo: " + e.getMessage());
        }
        
        return lista;
    }
    
    
    public static List<Intervencion> getListPozoByIntervencion(int idIntervenion, int idSubInter){
    
        String hql="SELECT I FROM Intervencion I " +
		  		" INNER JOIN FETCH I.pozo P "+
		  		" INNER JOIN FETCH I.catIntervencion INTER " +
		  		" INNER JOIN FETCH I.catSubIntervencion SUB " +
		  		" WHERE INTER.idTipo= " + idIntervenion + " AND SUB.idCatSubIntervencion="+ idSubInter +
		  		" ORDER BY P.strNombre";
        
        List<Intervencion>lista=null;
        try {
            lista=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR PozoDAO.getListPozoByIntervencion: "+ e.getMessage());
        }
        return lista;
    }
    
      public static List<Pozo>getListPozo(boolean todo,String buscar){
        List<Pozo> lista=new ArrayList<Pozo>();
        String hql="SELECT P FROM Pozo P ORDER BY P.strNombre";
        if(!todo){
            hql="SELECT P FROM Pozo P WHERE P.strNombre LIKE '%"+buscar+"%'";
        }
        try {            
            lista=GenericDao.GetAllGeneric(hql);
        } catch (Exception e) {
            System.out.println("ERROR PozoDAO.getListPozo: " + e.getMessage());
        }
        
        return lista;
    }
    
    public static Pozo getPozoByNombre(String nombre){
        Pozo pozo=new Pozo();
        try {
            String hql="SELECT P FROM Pozo P WHERE P.strNombre='" + nombre +"'";
            pozo=(Pozo)GenericDao.searchBySpecific(hql);
        } catch (Exception e) {
            System.out.println("ERROR PozoDAO.getPozoByNombre: " + e.getMessage());
        }
        return pozo;
    }
    
    public static int setDeleteIntervencion(int id){
        String hql="DELETE Intervencion I WHERE I.idIntervencion="+ id + "";
        try {
            return GenericDao.deleteSpecificDelete(hql);
        } catch (Exception e) {
            System.out.println("Error PerforacionDAO.setDeleteIntervencion " + e.getMessage());
        }
        
        return 0;
    }
      public static int setDeletePozo(int id){
        String hql="DELETE Pozo P WHERE P.idPozo="+ id + "";
        try {
            return GenericDao.deleteSpecificDelete(hql);
        } catch (Exception e) {
            System.out.println("Error PerforacionDAO.setDeletePozo " + e.getMessage());
        }
        
        return 0;
    }
}

