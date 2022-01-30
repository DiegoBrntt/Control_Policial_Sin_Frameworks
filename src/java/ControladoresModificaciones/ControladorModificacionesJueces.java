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
public class ControladorModificacionesJueces extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String ip = (String) request.getAttribute("dirIP");
        String bd = (String) request.getAttribute("nomBD");
        Modelo m = Modelo.getInstance(ip, bd);
        Integer numeroJuz;
        RequestDispatcher vista;
        switch (request.getParameter("accion")) {

            case "Modificar juzgado":
                numeroJuz = Integer.valueOf(request.getParameter("numeroJuz"));
                Integer numeroJuzMod = Integer.valueOf(request.getParameter("numeroJuzMod"));
                
                request.setAttribute("verificacionJuzMod",m.actualizarJuzagado(numeroJuz, numeroJuzMod));
                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
                vista.forward(request, response);
                break;
            case "Modificar juez":
                numeroJuz = Integer.valueOf(request.getParameter("numeroJuz"));
                String nomApeJuez = request.getParameter("nomApeJuez");
                Integer tiempoServ = Integer.valueOf(request.getParameter("tiempoServ"));
                String nomApeJuezMod = request.getParameter("nomApeJuezMod");
                
                request.setAttribute("verificacionJuezMod",m.actualizarJuez(numeroJuz, nomApeJuez, tiempoServ, nomApeJuezMod));
                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
                vista.forward(request, response);

        }

    }

}
