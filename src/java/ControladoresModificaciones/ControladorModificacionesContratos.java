/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresModificaciones;

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
public class ControladorModificacionesContratos extends HttpServlet {

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
        String codigoSucCon = request.getParameter("codigoSucCon");
        String codigoVigCon = request.getParameter("codigoVigCon");
        String codigoCon = request.getParameter("codigoCon");
        String codigoConMod = request.getParameter("codigoConMod");
        LocalDate fechaContrato = LocalDate.parse(request.getParameter("fechaContrato"));
        Integer diasContrato = Integer.valueOf(request.getParameter("diasContrato"));
        Integer armado;
        try {
            armado = Integer.valueOf(request.getParameter("armado"));
        } catch (Exception e) {
            armado = 0;
        }
        
        request.setAttribute("verificacionConMod", m.actualizarContrato(codigoSucCon, codigoVigCon, codigoCon, fechaContrato, diasContrato, armado, codigoConMod));
        m.cargarDatos(request, response);
        vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
        vista.forward(request, response);

    }

}
