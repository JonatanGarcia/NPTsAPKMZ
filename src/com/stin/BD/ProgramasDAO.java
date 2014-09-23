/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.DAO.GenericDao;
import com.stin.Programas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class ProgramasDAO {
    
     public static boolean setNewProgramas(Programas programas) throws Exception{
      
            GenericDao.newGeneric(programas);
            return true;
    }
     
     public static List<Programas> getListProgramas(int idIntervencion){
         List<Programas>lista=new ArrayList<Programas>();
         String hql="SELECT P FROM Programas P INNER JOIN FETCH P.catTr INNER JOIN FETCH P.catEtapa WHERE P.intervencion.idIntervencion=" + idIntervencion +"" ;
         try {
             lista=GenericDao.GetAllGeneric(hql);
         } catch (Exception e) {
             System.out.println("Error ProgramasDAO.getListProgramas: " + e.getMessage());
         }
         return lista;
     }
      public static int setDeletePrograma (int id){
        String hql="DELETE Programas P WHERE P.intervencion.idIntervencion="+ id + "";
        try {
            return GenericDao.deleteSpecificDelete(hql);
        } catch (Exception e) {
            System.out.println("Error PerforacionDAO.setDeletePerforacion " + e.getMessage());
        }
        
        return 0;
    }
}
