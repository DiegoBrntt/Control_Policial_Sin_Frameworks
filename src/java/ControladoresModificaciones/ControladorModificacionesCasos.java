/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresModificaciones;

import Beans.FactoriaArrayList;
import Modelo.Modelo;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Grandalf
 */
public class ControladorModificacionesCasos extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String ip = (String) request.getAttribute("dirIP");
        String bd = (String) request.getAttribute("nomBD");
        Modelo m = Modelo.getInstance(ip, bd);

        String codigoSucDel = request.getParameter("codigoSucDel");
        String juezEncargado = request.getParameter("juezEncargado");
        Integer codigoCaso = Integer.valueOf(request.getParameter("codigoDel"));
        LocalDate fechaDelito = LocalDate.parse(request.getParameter("fechaDelito"));
        Integer tiempoCondena = Integer.valueOf(request.getParameter("tiempoCondena"));
        ArrayList<String> implicadosArray;
        try {
            String[] implicados = request.getParameterValues("implicados");
            implicadosArray = FactoriaArrayList.getInstance();
            implicadosArray.addAll(Arrays.asList(implicados));
        } catch (Exception e) {
            implicadosArray = FactoriaArrayList.getInstance();
        }
        Integer condena;
        try {
            condena = Integer.valueOf(request.getParameter("condena"));
        } catch (Exception e) {
            condena = 0;
        }
        
        
        
        Integer codigoCasoMod = Integer.valueOf(request.getParameter("codigoCasoMod"));

        request.setAttribute("verificacionCasoMod", m.actualizarCaso(codigoSucDel, juezEncargado, codigoCaso, fechaDelito, tiempoCondena, condena, implicadosArray, codigoCasoMod));

        RequestDispatcher vista;
        m.cargarDatos(request, response);
        vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
        vista.forward(request, response);
    }

}
