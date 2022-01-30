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
import java.time.format.DateTimeFormatter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Grandalf
 */
public class ControladorSucursales extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String ip = (String) request.getAttribute("dirIP");
        String bd = (String) request.getAttribute("nomBD");
        Modelo m = Modelo.getInstance(ip, bd);

        String codigoEnt = request.getParameter("codigoEntSuc");
        String codigoSuc = request.getParameter("codigoSuc");
        String domicilio = request.getParameter("domicilioSuc");
        Integer cantEmpleados = Integer.valueOf(request.getParameter("empleadosSuc"));
        LocalDate fechaContratacion = LocalDate.parse(request.getParameter("fechaContSuc"));
        Integer cfrecuenciaContratacion = Integer.valueOf(request.getParameter("diasContSuc"));

        request.setAttribute("verificacionSucAlta", m.altaSucursal(codigoEnt, codigoSuc, domicilio, cantEmpleados, fechaContratacion, cfrecuenciaContratacion));

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
            case "Modificar":
                String codigoSuc = request.getParameter("codigoSucBaja");
                
                request.setAttribute("verificacionSucBusqueda", m.buscarSucursal(codigoSuc));
                m.consultarEntidades();
                request.setAttribute("sucursal", m.getSucursal());
                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaModificacion.jsp");
                vista.forward(request, response);
                break;
            case "Baja":
                String codSuc = request.getParameter("codigoSucBaja");
                
                request.setAttribute("verificacionSucBaja", m.bajaSucursal(codSuc));

                m.cargarDatos(request, response);
                vista = request.getRequestDispatcher("vistaResultadoAdministrador.jsp");
                vista.forward(request, response);
        }

    }
}
