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
public class BandaBean implements Serializable{
    private Integer codigo;
    private Integer cantIntegrantes;

    private BandaBean() {
    }

    public static BandaBean getInstance(){
        return new BandaBean();
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCantIntegrantes() {
        return cantIntegrantes;
    }

    public void setCantIntegrantes(Integer cantIntegrantes) {
        this.cantIntegrantes = cantIntegrantes;
    }
    
    
}
