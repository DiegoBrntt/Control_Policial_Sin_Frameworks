/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresAltasBajas;

import Modelo.Modelo;
import java.awt.event.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ControladorEntidades extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String ip = (String) request.getAttribute("dirIP");
        String bd = (String) request.getAttribute("nomBD");
        Modelo m = Modelo.getInstance(ip, bd);

        String codigoEnt = request.getParameter("codigoEnt");
        String central = request.getParameter("central");
        
        request.setAttribute("verificacionEntAlta", m.altaEntidad(codigoEnt, central));
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
        switch (accion) {
            case "Modificacion":
                String codigoEnt = request.getParameter("bajaEnt");
                
                request.setAttribute("verificacionEntBusqueda", m.buscarEntidades(codigoEnt));
                request.setAttribute("entidad", m.getEntidad());
                vista = request.getRequestDispatcher("vistaModificacion.jsp");
                vista.forward(request, response);
                break;
            case "Baja":
                String codigoEntBaja = request.getParameter("bajaEnt");
                
                request.setAttribute("verificacionEntBaja", m.bajaEntidad(codigoEntBaja));

                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
                vista.forward(request, response);
        }

    }

    private class ExceptionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String exception = event.getActionCommand();
            request.setAttribute("mensajeError", exception);
            RequestDispatcher vista = request.getRequestDispatcher("vistaError.jsp");
            try {
                vista.forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(ControladorEntidades.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ControladorEntidades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
