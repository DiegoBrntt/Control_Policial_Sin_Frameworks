/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresAltasBajas;

import Modelo.Modelo;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Grandalf
 */
public class ControladorContratos extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String ip = (String) request.getAttribute("dirIP");
        String bd = (String) request.getAttribute("nomBD");
        Modelo m = Modelo.getInstance(ip, bd);

        String codigoSucCon = request.getParameter("codigoSucCon");
        String codigoVigCon = request.getParameter("codigoVigCon");
        String codigoCon = request.getParameter("codigoCon");
        LocalDate fechaContrato = LocalDate.parse(request.getParameter("fechaContrato"));
        Integer diasContrato = Integer.valueOf(request.getParameter("diasContrato"));
        Integer armado;
        try {
            armado = Integer.valueOf(request.getParameter("armado"));
        } catch (Exception e) {
            armado = 0;
        }
        
        request.setAttribute("verificacionConAlta", m.altaContrato(codigoSucCon, codigoVigCon, codigoCon, fechaContrato, diasContrato, armado));
        RequestDispatcher vista;
        m.cargarDatos(request, response);
        vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
        vista.forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String ip = (String) request.getAttribute("dirIP");
        String bd = (String) request.getAttribute("nomBD");
        Modelo m = Modelo.getInstance(ip, bd);

        String accion = request.getParameter("accion");
        RequestDispatcher vista;
        String codigoCon;
        switch (accion) {
            case "Modificar":
                codigoCon = request.getParameter("codigoConBaja");
                
                request.setAttribute("verificacionConBusqueda", m.buscarContrato(codigoCon));
                request.setAttribute("contrato", m.getContrato());
                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaModificacion.jsp");
                vista.forward(request, response);
                break;
            case "Baja":
                codigoCon = request.getParameter("codigoConBaja");
                
                request.setAttribute("verificacionConBaja", m.bajaContrato(codigoCon));
 
                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
                vista.forward(request, response);
        }

    }

}
