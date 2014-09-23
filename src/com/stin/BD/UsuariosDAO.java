/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

import com.stin.DAO.GenericDao;
import com.stin.Usuarios;

/**
 *
 * @author Marco
 */
public class UsuariosDAO {
    public static int getUsuarioPermiso(String user, String password){
        Usuarios usuarios=new Usuarios();
        
        try {
            String hql="SELECT u FROM Usuarios u WHERE u.strUser='" + user + "' AND u.strPassword='" + password + "'";
            usuarios=(Usuarios)GenericDao.searchBySpecific(hql);
            if (usuarios==null){
                return 0;
            }
        } catch (Exception e) {
            System.out.println("ERROR UsuariosDAO.getUsuarioPermiso: " + e.getMessage());
        }
        
        return usuarios.getIntPermiso();
    }
}
