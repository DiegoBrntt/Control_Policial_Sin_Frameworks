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
public class JuezBean implements Serializable{
    private Integer numeroJuzgado;
    private String nomApe;
    private Integer tiempoServicio;

    private JuezBean() {
    }

    public static JuezBean getInstance(){
        return new JuezBean();
    }
    
    public Integer getNumeroJuzgado() {
        return numeroJuzgado;
    }

    public void setNumeroJuzgado(Integer numeroJuzgado) {
        this.numeroJuzgado = numeroJuzgado;
    }

    public String getNomApe() {
        return nomApe;
    }

    public void setNomApe(String nomApe) {
        this.nomApe = nomApe;
    }

    public Integer getTiempoServicio() {
        return tiempoServicio;
    }

    public void setTiempoServicio(Integer tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }
    
    
}
