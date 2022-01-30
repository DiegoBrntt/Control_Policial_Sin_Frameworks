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
public class ControladorModificacionesSucursales extends HttpServlet {

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
        String codigoEnt = request.getParameter("codigoEntSuc");
        String codigoSuc = request.getParameter("codigoSuc");
        String domicilio = request.getParameter("domicilioSuc");
        Integer cantEmpleados = Integer.valueOf(request.getParameter("empleadosSuc"));
        LocalDate fechaContratacion = LocalDate.parse(request.getParameter("fechaContSuc"));
        Integer cfrecuenciaContratacion = Integer.valueOf(request.getParameter("diasContSuc"));
        Integer codSucMod = Integer.valueOf(request.getParameter("codSuc"));
        
        request.setAttribute("VerificacionSucMod", m.actualizarSucursal(codigoEnt, codigoSuc, domicilio, cantEmpleados, fechaContratacion, cfrecuenciaContratacion, codSucMod));
        m.cargarDatos(request, response);
        vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
        vista.forward(request, response);

    }

}
