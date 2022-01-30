/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Beans.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Modelo {

    private String jdbcDriver;
    private String dbName;
    private String urlRoot;
    private static Modelo m;
    // Informacion
    private UsuarioBean resultadoUsuario;
    private ArrayList<UsuarioBean> resultadoVigilantes;
    private ArrayList<UsuarioBean> resultadoUsuarios;
    private ArrayList<EntidadBean> resultadoEntidades;
    private ArrayList<SucursalBean> resultadoSucursales;
    private ArrayList<ContratoBean> resultadoContratos;
    private ArrayList<BandaBean> resultadoBandas;
    private ArrayList<DetenidoBean> resultadoDetenidos;
    private ArrayList<JuezBean> resultadoJueces;
    private ArrayList<CasoBean> resultadoCasos;
    // Busquedas
    private UsuarioBean busquedaUsuarios;
    private EntidadBean busquedaEntidades;
    private SucursalBean busquedaSucursales;
    private ContratoBean busquedaContratos;
    private BandaBean busquedaBandas;
    private DetenidoBean busquedaDetenidos;
    private JuezBean busquedaJueces;
    private CasoBean busquedaCasos;
    private ActionListener listener;

    private Modelo(String url, String dbName) {
        jdbcDriver = "com.mysql.cj.jdbc.Driver";
        urlRoot = "jdbc:mysql://" + url + "/";
        this.dbName = dbName;
        listener = null;

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {

            reportException(e.getMessage());
        }
    }

    public static Modelo getInstance(String url, String dbName) {
        if (m == null) {
            m = new Modelo(url, dbName);
        }
        return m;
    }

    public Modelo getModelo() {
        return m;
    }

    public void cargarDatos(HttpServletRequest request, HttpServletResponse response) {
        m.consultarUsuarios();
        m.consultarVigilantes();
        m.consultarEntidades();
        m.consultarSucursales();
        m.consultarContratos();
        m.consultarBandas();
        m.consultarDetenidos();
        m.consultarJueces();
        m.consultarCasos();

        request.setAttribute("usuario", m.getUsuarioValido());
        request.setAttribute("usuarios", m.getUsuarios());
        request.setAttribute("modelo", m);
        request.setAttribute("vigilantes", m.getVigilantes());
        request.setAttribute("entidades", m.getEntidades());
        request.setAttribute("sucursales", m.getSucursales());
        request.setAttribute("contratos", m.getContratos());
        request.setAttribute("bandas", m.getBandas());
        request.setAttribute("detenidos", m.getDetenidos());
        request.setAttribute("jueces", m.getJueces());
        request.setAttribute("casos", m.getCasos());
    }

    // usuarios
    public String altaUsuario(String nombreUsu, String passUsu, String tipoUsu, Integer edadUsu) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO `usuarios`(`clave`, `pass`, `edad`, `tipo`)"
                    + " VALUES ('" + nombreUsu + "','" + passUsu + "','" + edadUsu + "','" + tipoUsu + "');");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String bajaUsuario(String nombreUsu) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM `usuarios` WHERE usuarios.clave='" + nombreUsu + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String actualizarUsuario(String nombreUsu, String passUsu, String tipoUsu, Integer edadUsu, String nombreUsuMod) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE `usuarios` SET `clave`='" + nombreUsu + "',`pass`='" + passUsu + "',"
                    + "`edad`='" + edadUsu + "',`tipo`='" + tipoUsu + "' WHERE clave='" + nombreUsuMod + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String buscarUsuario(String codigoUsuMod) {
        resultadoUsuario = UsuarioBean.getInstance();
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT clave, pass, edad, tipo FROM usuarios WHERE clave='" + codigoUsuMod + "';");
            rs.next();

            busquedaUsuarios = UsuarioBean.getInstance();
            busquedaUsuarios.setClave(rs.getString(1));
            busquedaUsuarios.setPass(rs.getString(2));
            busquedaUsuarios.setEdad(rs.getInt(3));
            busquedaUsuarios.setTipo(rs.getString(4));

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public void consultarUsuarioValido(String clave, String pass) {
        resultadoUsuario = UsuarioBean.getInstance();
        resultadoUsuario.setClave(clave);
        resultadoUsuario.setPass(pass);
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tipo FROM usuarios WHERE clave='" + clave + "' AND pass='" + pass + "';");
            while (rs.next()) {

                resultadoUsuario.setTipo(rs.getString(1));
            }

            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
    }

    public void consultarVigilantes() {
        resultadoVigilantes = FactoriaArrayList.getInstance();
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT clave FROM usuarios WHERE tipo='Vigilante' ORDER BY clave ASC;");
            while (rs.next()) {

                UsuarioBean vigilante = UsuarioBean.getInstance();
                vigilante.setClave(rs.getString(1));
                resultadoVigilantes.add(vigilante);
            }

            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
    }

    public void consultarUsuarios() {
        resultadoUsuarios = FactoriaArrayList.getInstance();
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT clave FROM usuarios ORDER BY clave ASC;");
            while (rs.next()) {

                UsuarioBean usuario = UsuarioBean.getInstance();
                usuario.setClave(rs.getString(1));
                resultadoUsuarios.add(usuario);
            }

            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
    }

    public UsuarioBean getUsuarioValido() {
        return resultadoUsuario;
    }

    public ArrayList<UsuarioBean> getVigilantes() {
        return resultadoVigilantes;
    }

    public ArrayList<UsuarioBean> getUsuarios() {
        return resultadoUsuarios;
    }

    public UsuarioBean getUsuario() {
        return busquedaUsuarios;
    }

    // entidades
    public String altaEntidad(String codigoEnt, String central) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO `entidades` VALUES ('" + codigoEnt + "','" + central + "');");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String bajaEntidad(String codigoEnt) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM `entidades` WHERE entidades.codigo='" + codigoEnt + "';");
            stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM `sucursales` WHERE sucursales.codigo_ent='" + codigoEnt + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String actualizarEntidad(String codigoEnt, String central, String codigoEntNuevo) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE `entidades` SET `domicilio`='" + central + "', `codigo`='" + codigoEntNuevo + "' WHERE entidades.codigo='" + codigoEnt + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String buscarEntidades(String codigoEnt) {

        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT codigo, domicilio FROM entidades WHERE entidades.codigo='" + codigoEnt + "';");
            rs.next();

            busquedaEntidades = EntidadBean.getInstance();
            busquedaEntidades.setCodigo(rs.getString(1));
            busquedaEntidades.setDomicilio(rs.getString(2));

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public void consultarEntidades() {
        resultadoEntidades = FactoriaArrayList.getInstance();
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT codigo, domicilio FROM entidades ORDER BY codigo ASC;");
            while (rs.next()) {

                EntidadBean entidad = EntidadBean.getInstance();
                entidad.setCodigo(rs.getString(1));
                entidad.setDomicilio(rs.getString(2));
                resultadoEntidades.add(entidad);
            }

            for (EntidadBean resultadoEntidade : resultadoEntidades) {
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT COUNT(codigo_ent) FROM sucursales "
                        + "WHERE codigo_ent='" + resultadoEntidade.getCodigo() + "';");
                rs.next();
                resultadoEntidade.setCantSucs(rs.getInt(1));
            }

            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
    }

    public ArrayList<EntidadBean> getEntidades() {
        return resultadoEntidades;
    }

    public EntidadBean getEntidad() {
        return busquedaEntidades;
    }

    // sucursales
    public String altaSucursal(String codigoEnt, String codigoSuc, String domicilio, Integer cantEmpleados, LocalDate fechaContratacion, Integer frecuenciaContratacion) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO `sucursales`(`codigo_ent`, `codigo_suc`, `domicilio`, `empleados`, `fecha_contratacion`, `frecuencia_contratacion`)"
                    + " VALUES ('" + codigoEnt + "','" + codigoSuc + "','" + domicilio + "','" + cantEmpleados + "','" + fechaContratacion + "','" + frecuenciaContratacion + "')");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String bajaSucursal(String codigoSuc) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM `sucursales` WHERE sucursales.codigo_suc='" + codigoSuc + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String actualizarSucursal(String codigoEnt, String codigoSuc, String domicilio, Integer cantEmpleados, LocalDate fechaContratacion, Integer cfrecuenciaContratacion, Integer codSucMod) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE `sucursales` SET `codigo_ent`='" + codigoEnt + "',`codigo_suc`='" + codigoSuc + "',"
                    + "`domicilio`='" + domicilio + "',`empleados`='" + cantEmpleados + "',"
                    + "`fecha_contratacion`='" + fechaContratacion + "',`frecuencia_contratacion`='" + cfrecuenciaContratacion + "' "
                    + "WHERE sucursales.codigo_suc='" + codSucMod + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String buscarSucursal(String codigoSuc) {

        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `codigo_ent`, `codigo_suc`, `domicilio`, `empleados`, `fecha_contratacion`, `frecuencia_contratacion` FROM `sucursales`"
                    + " WHERE sucursales.codigo_suc='" + codigoSuc + "';");
            rs.next();

            busquedaSucursales = SucursalBean.getInstance();
            busquedaSucursales.setCodigoEnt(rs.getInt(1));
            busquedaSucursales.setCodigoSuc(rs.getInt(2));
            busquedaSucursales.setDomicilio(rs.getString(3));
            busquedaSucursales.setCantEmpleados(rs.getInt(4));
            busquedaSucursales.setFechaContratacion(LocalDate.parse(rs.getString(5)));
            busquedaSucursales.setFrecuenciaContratacion(rs.getInt(6));

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public void consultarSucursales() {
        resultadoSucursales = FactoriaArrayList.getInstance();
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT codigo_ent, codigo_suc, domicilio, empleados FROM sucursales ORDER BY codigo_ent ASC;");
            while (rs.next()) {

                SucursalBean sucursal = SucursalBean.getInstance();
                sucursal.setCodigoEnt(rs.getInt(1));
                sucursal.setCodigoSuc(rs.getInt(2));
                sucursal.setDomicilio(rs.getString(3));
                sucursal.setCantEmpleados(rs.getInt(4));
                resultadoSucursales.add(sucursal);
            }

            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
    }

    public ArrayList<SucursalBean> getSucursales() {
        return resultadoSucursales;
    }

    public SucursalBean getSucursal() {
        return busquedaSucursales;
    }

    // contratos
    public String altaContrato(String codigoSucCon, String codigoVigCon, String codigoCon, LocalDate fechaContrato, Integer diasContrato, Integer armado) {
        try {
            Integer vigencia;

            if (fechaContrato.plusDays(diasContrato).isAfter(LocalDate.now()) || fechaContrato.plusDays(diasContrato).isEqual(LocalDate.now())) {
                vigencia = 1;
            } else {
                vigencia = 0;
            }

            buscarSucursal(codigoSucCon);
            LocalDate fechaC = getSucursal().getFechaContratacion();
            Integer frecue = getSucursal().getFrecuenciaContratacion();

            int i = 0;
            if (fechaC.plusDays(frecue * i).isAfter(fechaContrato)) {
                while (fechaC.minusDays(frecue * i).isAfter(fechaContrato)) {
                    i++;
                }

                if (!fechaC.minusDays(frecue * i).isEqual(fechaContrato)) {
                    return "Fecha de contrato invalida para la sucursal deseada.";
                }

            }
            if (fechaC.plusDays(frecue * i).isBefore(fechaContrato)) {
                while (fechaC.plusDays(frecue * i).isBefore(fechaContrato)) {
                    i++;
                }

                if (!fechaC.plusDays(frecue * i).isEqual(fechaContrato)) {
                    return "Fecha de contrato invalida para la sucursal deseada.";
                }

            }
            consultarContratosVigilante(codigoVigCon);
            for (ContratoBean contrato : getContratos()) {
                if (contrato.coincideFechaVigilante(fechaContrato, codigoVigCon)) {
                    return "El vigilante deseado ya esta contratado dicha fecha";
                }
            }

            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();

            stmt.executeUpdate("INSERT INTO `contratos`(`codigo_contrato`, `fecha_contratacion`, `codigo_suc`, `codigo_vig`, `dias_contratados`, `armado`, `estado`)"
                    + " VALUES ('" + codigoCon + "','" + fechaContrato + "','" + codigoSucCon + "','" + codigoVigCon + "','" + diasContrato + "','" + armado + "','" + vigencia + "')");

            con.close();
            return "OK";
        } catch (SQLException e) {
            reportException(e.getMessage());
            return e.getMessage();
        }
    }

    public String bajaContrato(String codigoCon) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM `contratos` WHERE contratos.codigo_contrato='" + codigoCon + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String actualizarContrato(String codigoSucCon, String codigoVigCon, String codigoCon, LocalDate fechaContrato, Integer diasContrato, Integer armado, String codigoConMod) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE `contratos` SET `codigo_contrato`='" + codigoCon + "',`fecha_contratacion`='" + fechaContrato + "',"
                    + "`codigo_suc`='" + codigoSucCon + "',`codigo_vig`='" + codigoVigCon + "',"
                    + "`dias_contratados`='" + diasContrato + "',`armado`='" + armado + "'"
                    + " WHERE contratos.codigo_contrato='" + codigoConMod + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String buscarContrato(String codigoCon) {

        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `codigo_contrato`, `fecha_contratacion`, `codigo_suc`, `codigo_vig`, `dias_contratados`, `armado`, `estado` FROM `contratos`"
                    + " WHERE contratos.codigo_contrato='" + codigoCon + "';");
            rs.next();

            busquedaContratos = ContratoBean.getInstance();
            busquedaContratos.setCodigo_contrato(rs.getInt(1));
            busquedaContratos.setFecha_contratacion(LocalDate.parse(rs.getString(2)));
            busquedaContratos.setCodigo_suc(rs.getInt(3));
            busquedaContratos.setCodigo_vig(rs.getString(4));
            busquedaContratos.setDias_contratado(rs.getInt(5));
            busquedaContratos.setArmado(rs.getBoolean(6));

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public void consultarContratosVigilante(String codigoVigilante) {
        resultadoContratos = FactoriaArrayList.getInstance();
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT codigo_contrato, fecha_contratacion, codigo_suc, dias_contratados, armado, estado FROM contratos WHERE codigo_vig='" + codigoVigilante + "';");
            while (rs.next()) {

                ContratoBean contrato = ContratoBean.getInstance();
                contrato.setCodigo_vig(codigoVigilante);
                contrato.setCodigo_contrato(rs.getInt(1));
                contrato.setFecha_contratacion(LocalDate.parse(rs.getString(2)));
                contrato.setCodigo_suc(rs.getInt((3)));
                contrato.setDias_contratado(rs.getInt(4));
                contrato.setArmado(rs.getBoolean(5));
                contrato.setEstado(LocalDate.parse(rs.getString(2)).plusDays(rs.getInt(4)).isAfter(LocalDate.now()));
                resultadoContratos.add(contrato);
            }

            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
    }

    public void consultarContratos() {
        resultadoContratos = FactoriaArrayList.getInstance();
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT codigo_vig, codigo_contrato, fecha_contratacion, codigo_suc, dias_contratados, armado, estado FROM contratos ORDER BY codigo_vig ASC;");
            while (rs.next()) {

                ContratoBean contrato = ContratoBean.getInstance();
                contrato.setCodigo_vig(rs.getString(1));
                contrato.setCodigo_contrato(rs.getInt(2));
                contrato.setFecha_contratacion(LocalDate.parse(rs.getString(3)));
                contrato.setCodigo_suc(rs.getInt((4)));
                contrato.setDias_contratado(rs.getInt(5));
                contrato.setArmado(rs.getBoolean(6));
                contrato.setEstado(LocalDate.parse(rs.getString(3)).plusDays(rs.getInt(5)).isAfter(LocalDate.now()));
                resultadoContratos.add(contrato);
            }

            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
    }

    public ArrayList<ContratoBean> getContratos() {
        return resultadoContratos;
    }

    public ContratoBean getContrato() {
        return busquedaContratos;
    }

    // bandas
    public String bajaBanda(String codigoBanBaja) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM `detenidos` WHERE detenidos.codigo_banda='" + codigoBanBaja + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String actualizarBanda(Integer codigoBan, Integer codigoBanMod) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE `detenidos` SET codigo_banda='" + codigoBan + "'"
                    + " WHERE detenidos.codigo_banda='" + codigoBanMod + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String buscarBanda(String codigoBanBaja) {
        try {
            busquedaBandas = BandaBean.getInstance();
            busquedaBandas.setCodigo(Integer.valueOf(codigoBanBaja));
            return "OK";
        } catch (Exception e) {
            return "Banda inexistente.";
        }
    }

    public void consultarBandas() {
        resultadoBandas = FactoriaArrayList.getInstance();
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT codigo_banda FROM detenidos WHERE codigo_banda ORDER BY codigo_banda ASC;");
            while (rs.next()) {

                BandaBean banda = BandaBean.getInstance();
                banda.setCodigo(rs.getInt(1));

                resultadoBandas.add(banda);

            }

            for (BandaBean banda : resultadoBandas) {

                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT COUNT(codigo_banda) FROM detenidos WHERE codigo_banda='" + banda.getCodigo() + "' ORDER BY codigo_banda ASC;");
                rs.next();
                banda.setCantIntegrantes(rs.getInt(1));
            }

            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
    }

    public ArrayList<BandaBean> getBandas() {
        return resultadoBandas;
    }

    public BandaBean getBanda() {
        return busquedaBandas;
    }

    // detenidos
    public String altaDetenido(String codigoBan, String nomApeDet, String claveDet) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO `detenidos`(`codigo_det`, `codigo_banda`, `nombre_ape`)"
                    + " VALUES ('" + claveDet + "','" + codigoBan + "','" + nomApeDet + "')");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String bajaDetenido(String claveDet) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM `detenidos` WHERE detenidos.codigo_det='" + claveDet + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String actualizarDetenido(Integer codigoBan, String nomApeDet, Integer claveDet, Integer codigoDetMod) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE `detenidos` SET `codigo_det`='" + claveDet + "',"
                    + "`codigo_banda`='" + codigoBan + "',"
                    + "`nombre_ape`='" + nomApeDet + "' WHERE detenidos.codigo_det='" + codigoDetMod + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String buscarDetenido(String codigoDetBaja) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT codigo_det, codigo_banda, nombre_ape FROM detenidos"
                    + " WHERE detenidos.codigo_det='" + codigoDetBaja + "';");

            rs.next();
            busquedaDetenidos = DetenidoBean.getInstance();
            busquedaDetenidos.setClave(rs.getInt(1));
            busquedaDetenidos.setCodigoBanda(rs.getInt(2));
            busquedaDetenidos.setNomApe(rs.getString(3));

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public void consultarDetenidos() {
        resultadoDetenidos = FactoriaArrayList.getInstance();
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT codigo_det, codigo_banda, nombre_ape FROM detenidos ORDER BY codigo_banda ASC;");
            while (rs.next()) {

                DetenidoBean detenido = DetenidoBean.getInstance();
                detenido.setClave(rs.getInt(1));
                detenido.setCodigoBanda(rs.getInt(2));
                detenido.setNomApe(rs.getString(3));
                resultadoDetenidos.add(detenido);
            }

            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
    }

    public ArrayList<DetenidoBean> getDetenidos() {
        return resultadoDetenidos;
    }

    public DetenidoBean getDetenido() {
        return busquedaDetenidos;
    }

    // jueces
    public String altaJuez(String numeroJuz, String nomApeJuez, String tiempoServ) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO `jueces`(`numero_juzgado`, `nombre_ape`, `años_serv`)"
                    + " VALUES ('" + numeroJuz + "','" + nomApeJuez + "','" + tiempoServ + "')");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String bajaJuez(String nomApeJuez) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM `jueces` WHERE jueces.nombre_ape='" + nomApeJuez + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String bajaJuzgado(Integer numeroJuz) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM `jueces` WHERE jueces.numero_juzgado='" + numeroJuz + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String buscarJuez(String codigoJuezBaja) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT numero_juzgado, nombre_ape, años_serv FROM jueces "
                    + "WHERE jueces.nombre_ape='" + codigoJuezBaja + "';");
            rs.next();

            busquedaJueces = JuezBean.getInstance();
            busquedaJueces.setNumeroJuzgado(rs.getInt(1));
            busquedaJueces.setTiempoServicio(rs.getInt(3));
            busquedaJueces.setNomApe(rs.getString(2));

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String actualizarJuez(Integer numeroJuz, String nomApeJuez, Integer tiempoServ, String nomApeJuezMod) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE `jueces` SET `numero_juzgado`='" + numeroJuz + "',"
                    + "`nombre_ape`='" + nomApeJuez + "',"
                    + "`años_serv`='" + tiempoServ + "' WHERE jueces.nombre_ape='" + nomApeJuezMod + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String actualizarJuzagado(Integer numeroJuz, Integer numeroJuzMod) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE `jueces` SET `numero_juzgado`='" + numeroJuz + "' "
                    + "WHERE jueces.numero_juzgado='" + numeroJuzMod + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public void consultarJueces() {
        resultadoJueces = FactoriaArrayList.getInstance();
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT numero_juzgado, nombre_ape, años_serv FROM jueces ORDER BY numero_juzgado ASC;");
            while (rs.next()) {

                JuezBean juez = JuezBean.getInstance();
                juez.setNumeroJuzgado(rs.getInt(1));
                juez.setTiempoServicio(rs.getInt(3));
                juez.setNomApe(rs.getString(2));
                resultadoJueces.add(juez);
            }

            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
    }

    public ArrayList<JuezBean> getJueces() {
        return resultadoJueces;
    }

    public JuezBean getJuez() {
        return busquedaJueces;
    }

    // casos
    public String altaCaso(String codigoSucCaso, String juezEncargado, String codigoCaso, LocalDate fechaCaso, Integer tiempoCondena, Integer condena, ArrayList<String> implicadosArray) {
        try {

            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            if (condena == 1) {
                if (!implicadosArray.isEmpty()) {
                    consultarCasos();
                    for (CasoBean caso : resultadoCasos) {
                        if (caso.coincideCondenadoFecha(fechaCaso)) {
                            for (String string : implicadosArray) {
                                if (caso.getImplicados().contains(string)) {
                                    return "Algun condenado ya tenia una condena por dicha fecha";
                                }
                            }
                        }

                    }
                    stmt.executeUpdate("INSERT INTO `casos`(`codigo_caso`, `fecha_caso`, `codigo_juzgado`, `codigo_suc`, `condena`, `tiempo_condena`)"
                            + " VALUES ('" + codigoCaso + "','" + fechaCaso + "','" + juezEncargado + "','" + codigoSucCaso + "','" + condena + "','" + tiempoCondena + "')");

                    for (String string : implicadosArray) {
                        stmt = con.createStatement();
                        stmt.executeUpdate("INSERT INTO `condena_det`(`codigo_caso`, `codigo_det`) "
                                + "VALUES ('" + codigoCaso + "','" + string + "')");
                    }
                } else {
                    return "Si esta tildado el check de condena debe elegir condenados";
                }
            } else {
                stmt.executeUpdate("INSERT INTO `casos`(`codigo_caso`, `fecha_caso`, `codigo_juzgado`, `codigo_suc`, `condena`, `tiempo_condena`)"
                        + " VALUES ('" + codigoCaso + "','" + fechaCaso + "','" + juezEncargado + "','" + codigoSucCaso + "','" + condena + "','" + tiempoCondena + "')");

            }
            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String bajaCaso(Integer codigoDelBaja) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM `casos` WHERE casos.codigo_caso='" + codigoDelBaja + "';");
            stmt.executeUpdate("DELETE FROM `condena_det` WHERE condena_det.codigo_caso='" + codigoDelBaja + "';");

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String actualizarCaso(String codigoSucCaso, String juezEncargado, Integer codigoCaso, LocalDate fechaCaso, Integer tiempoCondena, Integer condena, ArrayList<String> implicadosArray, Integer codigoCasoMod) {
        try {

            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            if (condena == 1) {
                if (!implicadosArray.isEmpty()) {
                    consultarCasos();
                    for (CasoBean caso : getCasos()) {
                        if (caso.coincideCondenadoFecha(fechaCaso)) {
                            for (String string : implicadosArray) {
                                if (caso.getImplicados().contains(string)) {
                                    return "Algun condenado ya tenia una condena por dicha fecha";
                                }
                            }
                        }

                    }
                    stmt.executeUpdate("UPDATE `casos` SET `codigo_caso`='" + codigoCaso + "',`fecha_caso`='" + fechaCaso + "',"
                            + "`codigo_juzgado`='" + juezEncargado + "',`codigo_suc`='" + codigoSucCaso + "',"
                            + "`condena`='" + condena + "',`tiempo_condena`='" + tiempoCondena + "'"
                            + " WHERE casos.codigo_caso='" + codigoCasoMod + "'");

                    stmt = con.createStatement();
                    stmt.executeUpdate("DELETE FROM `condena_det` WHERE condena_det.codigo_caso='" + codigoCasoMod + "';");
                    for (String string : implicadosArray) {
                        stmt = con.createStatement();
                        stmt.executeUpdate("INSERT INTO `condena_det`(`codigo_caso`, `codigo_det`) "
                                + "VALUES ('" + codigoCaso + "','" + string + "')");
                    }
                } else {
                    return "Si esta tildado el check de condena debe elegir condenados";
                }
            } else {
                stmt.executeUpdate("UPDATE `casos` SET `codigo_caso`='" + codigoCaso + "',`fecha_caso`='" + fechaCaso + "',"
                        + "`codigo_juzgado`='" + juezEncargado + "',`codigo_suc`='" + codigoSucCaso + "',"
                        + "`condena`='" + condena + "',`tiempo_condena`='0'"
                        + " WHERE casos.codigo_caso='" + codigoCasoMod + "'");
            }
            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String buscarCaso(Integer codigoCasoMod) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT codigo_caso, fecha_caso, codigo_juzgado, codigo_suc, condena, tiempo_condena FROM casos "
                    + "WHERE casos.codigo_caso='" + codigoCasoMod + "';");
            rs.next();

            busquedaCasos = CasoBean.getInstance();
            busquedaCasos.setCodigoCaso(rs.getInt(1));
            busquedaCasos.setFechaCaso(LocalDate.parse(rs.getString(2)));
            busquedaCasos.setNumeroJuzgado(rs.getInt(3));
            busquedaCasos.setCodigoSuc(rs.getInt(4));
            busquedaCasos.setCondena(rs.getInt(5));
            busquedaCasos.setTiempoCondena(rs.getInt(6));
            ArrayList<String> implicados = FactoriaArrayList.getInstance();
            ArrayList<Integer> implicadosAux = FactoriaArrayList.getInstance();

            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT codigo_det FROM condena_det "
                    + "WHERE condena_det.codigo_caso='" + codigoCasoMod + "';");

            while (rs.next()) {
                implicadosAux.add(rs.getInt(1));
            }
            for (Integer integer : implicadosAux) {
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT nombre_ape FROM detenidos "
                        + "WHERE detenidos.codigo_det='" + integer + "';");
                rs.next();

                implicados.add(rs.getString(1));

            }

            busquedaCasos.setImplicados(implicados);

            con.close();
            return "OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public void consultarCasos() {
        resultadoCasos = FactoriaArrayList.getInstance();
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT codigo_caso, fecha_caso, codigo_juzgado, codigo_suc, condena, tiempo_condena FROM casos ORDER BY codigo_caso ASC;");
            while (rs.next()) {

                CasoBean caso = CasoBean.getInstance();
                caso.setCodigoCaso(rs.getInt(1));
                caso.setFechaCaso(LocalDate.parse(rs.getString(2)));
                caso.setCumpliendo(LocalDate.parse(rs.getString(2)).plusMonths(rs.getInt(6)).isAfter(LocalDate.now()));
                caso.setNumeroJuzgado(rs.getInt(3));
                caso.setCodigoSuc(rs.getInt(4));
                caso.setCondena(rs.getInt(5));
                caso.setTiempoCondena(rs.getInt(6));
                resultadoCasos.add(caso);
            }

            for (CasoBean caso : resultadoCasos) {

                ArrayList<String> implicados = FactoriaArrayList.getInstance();
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT nombre_ape, detenidos.codigo_det FROM detenidos INNER JOIN condena_det "
                        + "WHERE condena_det.codigo_caso='" + caso.getCodigoCaso() + "' AND detenidos.codigo_det=condena_det.codigo_det;");
                while (rs.next()) {
                    implicados.add(rs.getString(1));

                }
                caso.setImplicados(implicados);

                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT nombre_ape FROM jueces"
                        + " WHERE jueces.numero_juzgado='" + caso.getNumeroJuzgado() + "';");
                rs.next();
                caso.setNombreJuez(rs.getString(1));
            }

            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
    }

    public ArrayList<CasoBean> getCasos() {
        return resultadoCasos;
    }

    public CasoBean getCaso() {
        return busquedaCasos;
    }

    // excepcion 
    private void reportException(String exception) {
        if (listener != null) {
            ActionEvent evt = new ActionEvent(this, 0, exception);
            listener.actionPerformed(evt);
        }
    }

    public void addExceptionListener(ActionListener listener) {
        this.listener = listener;
    }

}
