/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stin.model;

/**
 *
 * @author Marco
 */
public class Multivalor {
    int indice;
    String valor;
    
    public Multivalor(int indice, String valor){
        this.indice=indice;
        this.valor=valor;       
    }
    public String toString() {
      return this.valor;
    }
    public int getIndice() {
        return indice;
    }

    public String getValor() {
        return valor;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    ////
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Multivalor)) {
            return false;
        }
       Multivalor provincia = (Multivalor) o;

        if (indice != provincia.indice) {
            return false;
        }
        if (valor != null ? !valor.equals(provincia.valor) : provincia.valor!= null) {
            return false;
        }

        return true;
    }
     public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.valor != null ? this.valor.hashCode() : 0);
        hash = 89 * hash + this.indice;
        return hash;
    }
     ///
}
