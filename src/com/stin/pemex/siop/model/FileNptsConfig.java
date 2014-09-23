/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.pemex.siop.model;

import com.stin.BD.DBException;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Marco
 */
public class FileNptsConfig {
    private String ruta;
    private String configFile;
    public FileNptsConfig(){}
    
    public FileNptsConfig(String configFile){
        this.configFile=configFile;
    }
    public void loadConfig() throws DBException{
        Properties properties;
        try {
            properties=new Properties();
            
            properties.loadFromXML(new FileInputStream(configFile));            
            ruta=properties.getProperty("RUTA");            
        } catch (Exception e) {
             throw new DBException("Error while trying to load the properties file '" + configFile + "': "
                    + e.getMessage(), e);
        }
    }
    
    public String getRuta(){
        return ruta;
    }    
    
}
