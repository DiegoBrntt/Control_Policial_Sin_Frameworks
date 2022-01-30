/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;

/**
 *
 * @author Grandalf
 */
public class DetenidoBean implements Serializable{
    private Integer clave;
    private Integer codigoBanda;
    private String nomApe;

    private DetenidoBean() {
    }
    
    public static DetenidoBean getInstance(){
        return new DetenidoBean();
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public Integer getCodigoBanda() {
        return codigoBanda;
    }

    public void setCodigoBanda(Integer codigoBanda) {
        this.codigoBanda = codigoBanda;
    }

    public String getNomApe() {
        return nomApe;
    }

    public void setNomApe(String nomApe) {
        this.nomApe = nomApe;
    }
    
    
}
