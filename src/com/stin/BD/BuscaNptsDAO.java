/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.DAO.GenericDao;

/**
 *
 * @author Marco
 */
public class BuscaNptsDAO {
    static String hql="";
    public static String fechaInicio="";
    public static String fechaFin="";
    
    public static Double getPOPerforacion(int idPozo,boolean ultimos) {		
		double valor=0;
                System.out.println("LAS FECHAS: " + fechaInicio + " , "+ fechaFin);
		hql="SELECT sum(PE.floatTiempo)/24 FROM Perforacion PE " +
				"INNER JOIN  PE.intervencion I " +
				"INNER JOIN  I.pozo P " +
				"INNER JOIN  PE.nptIntervencion NP " +
				"INNER JOIN  NP.catNptsN4 N4 " +
				"INNER JOIN  N4.catNptsN3 N3 " +
				"INNER JOIN  N3.catNptsN2 N2 " + 
				"INNER JOIN  N2.catNptsN1 N1 " + 
				"WHERE P.idPozo="+idPozo+" AND N1.idNptn1=2";
		try {
			if(!ultimos){
				valor=(Double)GenericDao.searchBySpecific(hql);
		}else{                            
			hql=hql + " and PE.dateOperacion between '"+ fechaInicio +"' AND '"+fechaFin+"'";
			valor=(Double)GenericDao.searchBySpecific(hql);
		}
		} catch (Exception e) {
			// TODO: handle exception
                    System.out.println("ERROR getPOP: " + e.getMessage());
		}
 		
		
		return valor;
	}
	
	public static Double getFallaEquipo(int idPozo,boolean ultimos){
		double valor=0;
		hql="SELECT sum(PE.floatTiempo)/24 FROM Perforacion PE " +
				"INNER JOIN  PE.intervencion I " +
				"INNER JOIN  I.pozo P " +
				"INNER JOIN  PE.nptIntervencion NP " +
				"INNER JOIN  NP.catNptsN4 N4 " +
				"INNER JOIN  N4.catNptsN3 N3 " +
				"INNER JOIN  N3.catNptsN2 N2 " + 
				"WHERE P.idPozo="+idPozo+" AND N2.idNptn2=1";
		try {
			if(!ultimos){
				valor=(Double)GenericDao.searchBySpecific(hql);
		}else{
			hql=hql + " and PE.dateOperacion between '"+ fechaInicio +"' AND '"+fechaFin+"'";
			valor=(Double)GenericDao.searchBySpecific(hql);
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return valor;
		
	}
	
	public static Double getEsperas(int idPozo,boolean ultimos)throws Exception{
		double valor=0;
		hql="SELECT sum(PE.floatTiempo)/24 FROM Perforacion PE " +
				"INNER JOIN  PE.intervencion I " +
				"INNER JOIN  I.pozo P " +
				"INNER JOIN  PE.nptIntervencion NP " +
				"INNER JOIN  NP.catNptsN4 N4 " +
				"INNER JOIN  N4.catNptsN3 N3 " +				
				"WHERE P.idPozo="+idPozo+" AND N3.idNptn3=9 OR P.idPozo="+idPozo+"AND N3.idNptn3=8";
		try {
			if(!ultimos){
				valor=(Double)GenericDao.searchBySpecific(hql);
		}else{
			hql="SELECT sum(PE.floatTiempo)/24 FROM Perforacion PE " +
					"INNER JOIN  PE.intervencion I " +
					"INNER JOIN  I.pozo P " +
					"INNER JOIN  PE.nptIntervencion NP " +
					"INNER JOIN  NP.catNptsN4 N4 " +
					"INNER JOIN  N4.catNptsN3 N3 " +				
					"WHERE P.idPozo="+idPozo+" AND N3.idNptn3=9   and PE.dateOperacion between '"+ fechaInicio +
					"' AND '"+fechaFin+"' OR P.idPozo=1 AND N3.idNptn3=8   and PE.dateOperacion between '"+ fechaInicio +"' AND '"+fechaFin+"'";			
			valor=(Double)GenericDao.searchBySpecific(hql);
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return valor;
	}
	
	public static Double getMCC(int idPozo,boolean ultimos)throws Exception{
		double valor=0;
		hql="SELECT sum(PE.floatTiempo)/24 FROM Perforacion PE " +
				"INNER JOIN  PE.intervencion I " +
				"INNER JOIN  I.pozo P " +
				"INNER JOIN  PE.nptIntervencion NP " +
				"INNER JOIN  NP.catNptsN4 N4 " +
				"INNER JOIN  N4.catNptsN3 N3 " +				
				"WHERE P.idPozo="+idPozo+" AND N3.idNptn3=10";
		try {
			if(!ultimos){
				valor=(Double)GenericDao.searchBySpecific(hql);
		}else{
			hql=hql + " and PE.dateOperacion between '"+ fechaInicio +"' AND '"+fechaFin+"'";
			valor=(Double)GenericDao.searchBySpecific(hql);
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return valor;
	}
	
	public static Long getNormal(int idPozo)throws Exception{
		hql="SELECT COUNT(DISTINCT PE.dateOperacion)  FROM Perforacion PE " +
				"INNER JOIN  PE.intervencion I " +
				"INNER JOIN  I.pozo P "+
				"WHERE P.idPozo="+idPozo+"";
		long valor=(Long)GenericDao.searchBySpecific(hql);
		return valor;
	}
	
    
    
}
