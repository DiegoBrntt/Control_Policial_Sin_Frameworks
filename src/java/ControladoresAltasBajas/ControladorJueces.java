/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresAltasBajas;

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
public class ControladorJueces extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String ip = (String) request.getAttribute("dirIP");
        String bd = (String) request.getAttribute("nomBD");
        Modelo m = Modelo.getInstance(ip, bd);

        String numeroJuz = request.getParameter("numeroJuz");
        String nomApeJuez = request.getParameter("nomApeJuez");
        String tiempoServ = request.getParameter("tiempoServ");
        
        request.setAttribute("verificacionJuezAlta", m.altaJuez(numeroJuz, nomApeJuez, tiempoServ));

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
        RequestDispatcher vista;
        switch (request.getParameter("accion")) {
            case "Baja juez":
                String codigoJuezBaja = request.getParameter("codigoJuezBaja");
                
                request.setAttribute("verificacionJuezBaja", m.bajaJuez(codigoJuezBaja));
                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
                vista.forward(request, response);
                break;
            case "Baja juzgado":
                Integer codigoJuzgadoBaja = Integer.valueOf(request.getParameter("codigoJuzgadoBaja"));
                
                request.setAttribute("verificacionJuzBaja", m.bajaJuzgado(codigoJuzgadoBaja));
                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
                vista.forward(request, response);
                break;
            case "Modificar juez":
                String codigoJuezMod = request.getParameter("codigoJuezBaja");
                
                request.setAttribute("verificacionJuezBusqueda", m.buscarJuez(codigoJuezMod));
                request.setAttribute("juez", m.getJuez());
                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaModificacion.jsp");
                vista.forward(request, response);
                break;
            case "Modificar juzgado":
                try{
                Integer codigoJuzgadoMod = Integer.valueOf(request.getParameter("codigoJuzgadoBaja"));
                request.setAttribute("juzgado", codigoJuzgadoMod);
                request.setAttribute("verificacionJuzBusqueda", "OK");
                }catch(Exception e){
                    request.setAttribute("verificacionJuzBusqueda", "No hay juzgados disponibles.");
                }
                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaModificacion.jsp");
                vista.forward(request, response);
                break;
        }

        

    }
}
