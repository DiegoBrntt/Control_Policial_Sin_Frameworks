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
public class ControladorModificacionesUsuarios extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String ip = (String) request.getAttribute("dirIP");
        String bd = (String) request.getAttribute("nomBD");
        Modelo m = Modelo.getInstance(ip, bd);

        String nombreUsu = request.getParameter("nombreUsu");
        String nombreUsuMod = request.getParameter("nombreUsuMod");
        String passUsu = request.getParameter("passUsu");
        String tipoUsu = request.getParameter("tipoUsu");
        Integer edadUsu = Integer.valueOf(request.getParameter("edadUsu"));
        
        request.setAttribute("verificacionUsuMod",m.actualizarUsuario(nombreUsu, passUsu, tipoUsu, edadUsu, nombreUsuMod));
        RequestDispatcher vista;
        m.cargarDatos(request, response);
        vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
        vista.forward(request, response);
    }

}
