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
public class ControladorDetenidos extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String ip = (String) request.getAttribute("dirIP");
        String bd = (String) request.getAttribute("nomBD");
        Modelo m = Modelo.getInstance(ip, bd);

        String codigoBan = request.getParameter("codigoBan");
        String nomApeDet = request.getParameter("nomApeDet");
        String claveDet = request.getParameter("claveDet");
       
        request.setAttribute("verificacionDetAlta",  m.altaDetenido(codigoBan, nomApeDet, claveDet));
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

        String codigoBanBaja = request.getParameter("codigoBanBaja");
        String codigoDetBaja = request.getParameter("codigoDetBaja");

        RequestDispatcher vista;
        switch (request.getParameter("accion")) {
            case "Baja banda":
                
                request.setAttribute("verificacionBanBaja", m.bajaBanda(codigoBanBaja));
                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
                vista.forward(request, response);
                break;
            case "Baja detenido":
                
                request.setAttribute("verificacionDetBaja", m.bajaDetenido(codigoDetBaja));
                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
                vista.forward(request, response);
                break;
            case "Modificar banda":
                
                request.setAttribute("verificacionBanBusqueda", m.buscarBanda(codigoBanBaja));
                request.setAttribute("banda", m.getBanda());
                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaModificacion.jsp");
                vista.forward(request, response);
                break;
            case "Modificar detenido":
                
                request.setAttribute("verificacionDetBusqueda", m.buscarDetenido(codigoDetBaja));
                request.setAttribute("detenido", m.getDetenido());
                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaModificacion.jsp");
                vista.forward(request, response);
                break;
        }
    }
}
