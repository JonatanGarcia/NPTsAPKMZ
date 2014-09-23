/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.BD;

/**
 *
 * @author Marco
 */
public class DBException extends Exception{
     public DBException(String error) {
        super(error);
    }

    public DBException(String error, Exception e) {
        super(error, e);
    }
}
