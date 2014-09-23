/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.util;

import com.stin.model.ModelList;
import com.stin.model.Multivalor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;



/**
 *
 * @author Marco
 */
public class OrdenModel {
    
    private static List<Integer>listIdContent=new ArrayList<Integer>();
    private static List<Integer>listIdNoContent=new ArrayList<Integer>();
    public static ModelList getAdd(JList jList1,JList jList2){        
        DefaultListModel list2=new DefaultListModel();
        DefaultListModel newList=new DefaultListModel();
        Multivalor id=null;        
        if(jList2.getModel().getSize()>0){
            list2=(DefaultListModel) jList2.getModel();
        }        
        System.out.println("TAMAÑO: "+list2.size());
        for(int i=0;i<jList1.getModel().getSize();i++){
            if(jList1.isSelectedIndex(i)){    
                id=(Multivalor)jList1.getModel().getElementAt(i); 
                listIdNoContent.add(id.getIndice());
                //System.out.println("AddList2: " + id.getIndice() + " : " + id.getValor() );//jList1.getModel().getElementAt(i).toString());
                list2.addElement(jList1.getModel().getElementAt(i));
            }else{          
                //System.out.println("AddnewList: " + jList1.getModel().getElementAt(i).toString());
                newList.addElement(jList1.getModel().getElementAt(i));
            }            
        }
        //ModelList listAdd=new ModelList(newList,list2,null,null);
        
        //return listAdd;                
        return getInicializa(newList,list2);
    }
    private static ModelList getInicializa(DefaultListModel list1,DefaultListModel list2){
        ModelList listAll=new ModelList(list1,list2,listIdContent,listIdNoContent);
        return listAll;
        
    }
    public static  ModelList getRemove(JList jList1,JList jList2){                
        DefaultListModel list2=new DefaultListModel();
        DefaultListModel newList=new DefaultListModel();
        Multivalor id=null;
        if(jList1.getModel().getSize()>0){
            newList=(DefaultListModel) jList1.getModel();
        }
        
        System.out.println("TAMAÑO: "+list2.size());
        for(int i=0;i<jList2.getModel().getSize();i++){
            if(jList2.isSelectedIndex(i)){  
                id=(Multivalor)jList2.getModel().getElementAt(i);               
               // System.out.println("REMOVEnewList: " + id.getIndice() + " : " + id.getValor());//jList2.getModel().getElementAt(i).toString());
                listIdContent.add(id.getIndice());
                newList.addElement(jList2.getModel().getElementAt(i));                
            }else{                
                //System.out.println("REMOVEList2: " + jList2.getModel().getElementAt(i).toString());
                list2.addElement(jList2.getModel().getElementAt(i));
            }            
        }       
        //ModelList listRemove=new  ModelList(list2,newList,null,null);
        //return listRemove;
        return getInicializa(list2,newList);
    }
    
}
