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

public class ControladorLogin extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;
    private Modelo m;

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        this.request = request;
        this.response = response;
        String ip = request.getParameter("dirIP");
        String bd = request.getParameter("nomBD");
        request.setAttribute("dirIP", ip);
        request.setAttribute("nomBD", bd);
        String cl = request.getParameter("clave");
        String pa = request.getParameter("pass");
        m = Modelo.getInstance(ip, bd);
        m.addExceptionListener(new ExceptionListener());
        m.consultarUsuarioValido(cl, pa);
        RequestDispatcher vista;
        try {
            switch (m.getUsuarioValido().getTipo()) {
                case "Vigilante":
                    m.consultarContratosVigilante(cl);
                    request.setAttribute("contratosVigilante", m.getContratos());
                    m.cargarDatos(request, response);
                    vista = request.getRequestDispatcher("vistaResultadoVigilante.jsp");
                    vista.forward(request, response);
                    break;
                case "Investigador":
                    m.cargarDatos(request, response);
                    vista = request.getRequestDispatcher("vistaResultadoInvestigador.jsp");
                    vista.forward(request, response);
                    break;
                case "Administrador":
                    m.cargarDatos(request, response);
                    vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
                    vista.forward(request, response);
                    break;
            }
        }  catch(NullPointerException e){
            request.setAttribute("mensajeError", "Usuario y/o contraseña invalido.");
            vista = request.getRequestDispatcher("vistaError.jsp");
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
                Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
