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
public class EntidadBean implements Serializable{
    private String codigo;
    private Integer cantSucs;
    private String domicilio;

    private EntidadBean() {
    }

    public static EntidadBean getInstance(){
        return new EntidadBean();
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getCantSucs() {
        return cantSucs;
    }

    public void setCantSucs(Integer cantSucs) {
        this.cantSucs = cantSucs;
    }
    
    
}
