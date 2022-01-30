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
public class ContratoBean implements Serializable {
    private Integer codigo_contrato;
    private LocalDate fecha_contratacion;
    private Integer codigo_suc;
    private String codigo_vig;
    private Integer dias_contratado;
    private Boolean armado;
    private Boolean estado;

    private ContratoBean() {
    }

    public static ContratoBean getInstance(){
        return new ContratoBean();
    }
    
    public Integer getCodigo_contrato() {
        return codigo_contrato;
    }

    public void setCodigo_contrato(Integer codigo_contrato) {
        this.codigo_contrato = codigo_contrato;
    }

    public LocalDate getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(LocalDate fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public Integer getCodigo_suc() {
        return codigo_suc;
    }

    public void setCodigo_suc(Integer codigo_suc) {
        this.codigo_suc = codigo_suc;
    }

    public String getCodigo_vig() {
        return codigo_vig;
    }

    public void setCodigo_vig(String codigo_vig) {
        this.codigo_vig = codigo_vig;
    }

    public Integer getDias_contratado() {
        return dias_contratado;
    }

    public void setDias_contratado(Integer dias_contratado) {
        this.dias_contratado = dias_contratado;
    }

    public Boolean getArmado() {
        return armado;
    }

    public void setArmado(Boolean armado) {
        this.armado = armado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public boolean coincideFechaVigilante(LocalDate fechaContrato, String codigoVig) {
        return fechaContrato.isEqual(fecha_contratacion) && codigoVig.equals(codigo_vig);
    }
    
    
}
