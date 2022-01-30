/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Grandalf
 */
public class SucursalBean implements Serializable{
    private Integer codigoEnt;
    private Integer codigoSuc;
    private String domicilio;
    private Integer cantEmpleados;
    private LocalDate fechaContratacion;
    private Integer frecuenciaContratacion;

    private SucursalBean() {
    }

    public static SucursalBean getInstance(){
        return new SucursalBean();
    }
    
    public Integer getCodigoEnt() {
        return codigoEnt;
    }

    public void setCodigoEnt(Integer codigoEnt) {
        this.codigoEnt = codigoEnt;
    }

    
    public Integer getCodigoSuc() {
        return codigoSuc;
    }

    public void setCodigoSuc(Integer codigoSuc) {
        this.codigoSuc = codigoSuc;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getCantEmpleados() {
        return cantEmpleados;
    }

    public void setCantEmpleados(Integer cantEmpleados) {
        this.cantEmpleados = cantEmpleados;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Integer getFrecuenciaContratacion() {
        return frecuenciaContratacion;
    }

    public void setFrecuenciaContratacion(Integer frecuenciaContratacion) {
        this.frecuenciaContratacion = frecuenciaContratacion;
    }

    public boolean coincedeCodigo(Integer codigoSucCon) {
        return codigoSuc.equals(codigoSucCon);
    }
    
    
}
