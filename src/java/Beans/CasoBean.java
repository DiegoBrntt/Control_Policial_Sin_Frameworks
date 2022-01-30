/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Grandalf
 */
public class CasoBean implements Serializable {

    private Integer codigoCaso;
    private LocalDate fechaCaso;
    private Integer numeroJuzgado;
    private Integer codigoSuc;
    private Integer condena;
    private Boolean cumpliendo;
    private Integer tiempoCondena;
    private String nombreJuez;
    private ArrayList<String> implicados;

    private CasoBean() {
    }

    public static CasoBean getInstance() {
        return new CasoBean();
    }

    public Integer getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(Integer codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public LocalDate getFechaCaso() {
        return fechaCaso;
    }

    public void setFechaCaso(LocalDate fechaCaso) {
        this.fechaCaso = fechaCaso;
    }

    public Integer getNumeroJuzgado() {
        return numeroJuzgado;
    }

    public void setNumeroJuzgado(Integer numeroJuzgado) {
        this.numeroJuzgado = numeroJuzgado;
    }

    public Integer getCodigoSuc() {
        return codigoSuc;
    }

    public void setCodigoSuc(Integer codigoSuc) {
        this.codigoSuc = codigoSuc;
    }

    public Integer getCondena() {
        return condena;
    }

    public void setCondena(Integer condena) {
        this.condena = condena;
    }

    public Integer getTiempoCondena() {
        return tiempoCondena;
    }

    public void setTiempoCondena(Integer tiempoCondena) {
        this.tiempoCondena = tiempoCondena;
    }

    public ArrayList<String> getImplicados() {
        return implicados;
    }

    public void setImplicados(ArrayList<String> implicados) {
        this.implicados = implicados;
    }

    public String getNombreJuez() {
        return nombreJuez;
    }

    public void setNombreJuez(String nombreJuez) {
        this.nombreJuez = nombreJuez;
    }

    public Boolean getCumpliendo() {
        return cumpliendo;
    }

    public void setCumpliendo(Boolean cumpliendo) {
        this.cumpliendo = cumpliendo;
    }

    public boolean coincideCondenadoFecha(LocalDate fechaCaso) {
        return this.fechaCaso.isEqual(fechaCaso);
    }

    public boolean estaCondenado(String string) {
        for (String implicado : implicados) {
            if (implicado.equals(string)) {
                return true;
            }
        }
        return false;
    }

}
