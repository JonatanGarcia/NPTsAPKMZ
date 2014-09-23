/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.model;

import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author Marco
 */
public class ModelList {
    
    private DefaultListModel listall;
    private DefaultListModel listContent;
    private List<Integer> listIdByContent;
    private List<Integer> listIbByNoContent;
    
    public ModelList(){}
    public ModelList(DefaultListModel listall, DefaultListModel listContent, List<Integer> listIdByContent,List<Integer> listIdByNoContent) {
        this.listall = listall;
        this.listContent = listContent;
        this.listIdByContent=listIdByContent;
        this.listIbByNoContent=listIdByNoContent;
    }


    public DefaultListModel getListContent() {
        return listContent;
    }

    public DefaultListModel getListall() {
        return listall;
    }

    public List<Integer> getListIbByNoContent() {
        return listIbByNoContent;
    }

    public List<Integer> getListIdByContent() {
        return listIdByContent;
    }

    
    public void setListContent(DefaultListModel listContent) {
        this.listContent = listContent;
    }

    public void setListall(DefaultListModel listall) {
        this.listall = listall;
    }

    public void setListIbByNoContent(List<Integer> listIbByNoContent) {
        this.listIbByNoContent = listIbByNoContent;
    }

    public void setListIdByContent(List<Integer> listIdByContent) {
        this.listIdByContent = listIdByContent;
    }
    
    
}
