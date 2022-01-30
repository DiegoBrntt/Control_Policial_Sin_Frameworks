/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresModificaciones;

import Modelo.Modelo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Grandalf
 */
public class ControladorModificacionesEntidades extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;
    
    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String ip = (String) request.getAttribute("dirIP");
        String bd = (String) request.getAttribute("nomBD");
        Modelo m = Modelo.getInstance(ip, bd);

        RequestDispatcher vista;
        String codigoEntNuevo = request.getParameter("codigoEntNuevo");
        String codigoEnt = request.getParameter("codigoEnt");
        String central = request.getParameter("central");
        
        request.setAttribute("verificacionEntMod", m.actualizarEntidad(codigoEnt, central, codigoEntNuevo));
        m.cargarDatos(request, response);
        vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
        vista.forward(request, response);

    }
}
