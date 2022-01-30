<%-- 
    Document   : vistaResultadoAdministrador
    Created on : 10 nov. 2021, 17:45:27
    Author     : Grandalf
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/css/estilos.css">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <nav>
                <a href="index.jsp" >Cerrar Sesion</a>
                <a href="#entidades">Entidades</a>
                <a href="#sucursales">Sucursales</a>
                <a href="#contratos">Contratos</a>
                <a href="#detenidos">Detenidos</a>
                <a href="#jueces">Jueces</a>
                <a href="#delitos">Casos/Delitos</a>
            </nav>
        </header>

        <h1>Bienevenido/a ${usuario.tipo} ${usuario.clave}</h1>
        <!-- Entidades -->
        <form action="ControladorUsuarios" method="post">
            <div class="divTable4">
                <div class="captionD" id="usuarios">
                    <h3>Usuarios</h3>
                </div>    
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Nombre de Usuario</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="text" name="nombreUsu" required>
                        </div>
                        <div class="divTableCell">
                            <h3>Contraseña</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="text" name="passUsu" required>
                        </div>
                        <div class="divTableCell">
                            <h3>Edad</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="number" name="edadUsu" required>
                        </div>
                        <div class="divTableCell">
                            <h3>Tipo</h3>
                        </div>
                        <div class="divTableCell">
                            <select name="tipoUsu" required>
                                <option value="Administrador">Administrador</option>
                                <option value="Investigador">Investigador</option>
                                <option value="Vigilante">Vigilante</option>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <button name="altaUsu">Alta Usuario</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${verificacionUsuAlta==null}">
                </c:when>
                <c:when test="${verificacionUsuAlta=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Alta de Usuario realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionUsuAlta}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
        </form>
        <br>
        <form action="ControladorUsuarios" method="get">
            <div class="divTable5">
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Nombre de Usuario</h3>
                        </div>
                        <div class="divTableCell">
                            <select name="bajaUsu" required>
                                <c:forEach var="usuario2" items="${usuarios}">
                                    <option value="${usuario2.clave}">${usuario2.clave}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <button name="accion" value="Baja">Baja Usuario</button>
                        </div>
                        <div class="divTableCell2">
                            <button name="accion" value="Modificacion">Modificar usuario</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${verificacionUsuBaja==null}">
                </c:when>
                <c:when test="${verificacionUsuBaja=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Baja de Usuario realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionUsuBaja}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionUsuMod==null}">
                </c:when>
                <c:when test="${verificacionUsuMod=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Modificacion de Usuario realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionUsuMod}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionUsuBusqueda==null}">
                </c:when>
                <c:when test="${verificacionUsuBusqueda=='OK'}">
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionUsuBusqueda}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </form>
        <br>
        <!-- Entidades -->
        <form action="ControladorEntidades" method="post">
            <div class="divTable4">
                <div class="captionD" id="entidades">
                    <h3>Entidades</h3>
                </div>    
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Codigo de Entidad</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="number" name="codigoEnt" required>
                        </div>
                        <div class="divTableCell">
                            <h3>Domicilio Central</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="text" name="central" required>
                        </div>
                        <div class="divTableCell">
                            <button name="altaEnt">Alta entidad</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${verificacionEntAlta==null}">
                </c:when>
                <c:when test="${verificacionEntAlta=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Alta de Entidad realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionEntAlta}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
        </form>
        <br>
        <form action="ControladorEntidades" method="get">
            <div class="divTable5">
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Codigo de Entidad</h3>
                        </div>
                        <div class="divTableCell">
                            <select name="bajaEnt" required>
                                <c:forEach var="ent" items="${entidades}">
                                    <option value="${ent.codigo}">${ent.codigo}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <button name="accion" value="Baja">Baja entidad</button>
                        </div>
                        <div class="divTableCell2">
                            <button name="accion" value="Modificacion">Modificar entidad</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${verificacionEntBaja==null}">
                </c:when>
                <c:when test="${verificacionEntBaja=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Baja de Entidad realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionEntBaja}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionEntMod==null}">
                </c:when>
                <c:when test="${verificacionEntMod=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Modificacion de Entidad realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionEntMod}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionEntBusqueda==null}">
                </c:when>
                <c:when test="${verificacionEntBusqueda=='OK'}">
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionEntBusqueda}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </form>
        <br>
        <!-- Sucursales -->
        <form action="ControladorSucursales" method="post">
            <div class="divTable4">
                <div class="captionD" id="sucursales">
                    <h3>Sucursales</h3>
                </div>    
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Codigo de Entidad</h3>
                        </div>
                        <div class="divTableCell">
                            <select name="codigoEntSuc" required>
                                <c:forEach var="entidad" items="${entidades}">
                                    <option value="${entidad.codigo}">${entidad.codigo}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <h3>Codigo de Sucursal</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="number" name="codigoSuc" required>
                        </div>
                        <div class="divTableCell">
                            <h3>Domicilio Sucursal</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="text" name="domicilioSuc" required>
                        </div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Fecha de contratación</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="date" name="fechaContSuc" required>
                        </div>
                        <div class="divTableCell">
                            <h3>Frecuencia de contratacion (en dias)</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="number" name="diasContSuc" required>
                        </div>
                        <div class="divTableCell">
                            <h3>cantidad de empleados</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="number" name="empleadosSuc" required>
                        </div>
                        <div class="divTableCell">
                            <button name="altaSuc">Alta sucursal</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${verificacionSucAlta==null}">
                </c:when>
                <c:when test="${verificacionSucAlta=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Alta de Sucursal realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionSucAlta}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
        </form>
        <br>
        <form action="ControladorSucursales" method="get">
            <div class="divTable5">
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Codigo de Sucursal</h3>
                        </div>
                        <div class="divTableCell">
                            <select name="codigoSucBaja" required>
                                <c:forEach var="sucursal" items="${sucursales}">
                                    <option value="${sucursal.codigoSuc}">${sucursal.codigoSuc}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <button name="accion" value="Baja">Baja sucursal</button>
                        </div>
                        <div class="divTableCell2">
                            <button name="accion" value="Modificar">Modificar sucursal</button>
                        </div>
                    </div>
                </div>
            </div>
           <c:choose>
                <c:when test="${verificacionSucBaja==null}">
                </c:when>
                <c:when test="${verificacionSucBaja=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Baja de Sucursal realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionSucBaja}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionSucMod==null}">
                </c:when>
                <c:when test="${verificacionSucMod=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Modificacion de Sucursal realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionSucMod}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionSucBusqueda==null}">
                </c:when>
                <c:when test="${verificacionSucBusqueda=='OK'}">
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionSucBusqueda}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </form>
        <br>
        <!-- Contratos -->
        <form action="ControladorContratos" method="post">
            <div class="divTable4">
                <div class="captionD" id="contratos">
                    <h3>Contratos</h3>
                </div>    
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Codigo de Sucursal</h3>
                        </div>
                        <div class="divTableCell">
                            <select name="codigoSucCon" required>
                                <c:forEach var="sucursal" items="${sucursales}">
                                    <option value="${sucursal.codigoSuc}">${sucursal.codigoSuc}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <h3>Codigo de Vigilante</h3>
                        </div>
                        <div class="divTableCell">
                            <select name="codigoVigCon" required>
                                <c:forEach var="vigilante" items="${vigilantes}">
                                    <option value="${vigilante.clave}">${vigilante.clave}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <h3>Codigo de Contrato</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="number" name="codigoCon">
                        </div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Fecha del contrato</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="date" name="fechaContrato">
                        </div>
                        <div class="divTableCell">
                            <h3>Cantidad de dias contratado</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="number" name="diasContrato">
                        </div>
                        <div class="divTableCell">
                            <h3>Posesión de arma</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="checkbox" value="1" name="armado">
                        </div>
                        <div class="divTableCell">
                            <button name="altaCon">Alta contrato</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${verificacionConAlta==null}">
                </c:when>
                <c:when test="${verificacionConAlta=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Alta de Contrato realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionConAlta}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
        </form>
        <br>
        <form action="ControladorContratos" method="get">
            <div class="divTable5">
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Codigo de Contrato para dar de baja</h3>
                        </div>
                        <div class="divTableCell">
                            <select name="codigoConBaja" required>
                                <c:forEach var="contrato" items="${contratos}">
                                    <option value="${contrato.codigo_contrato}">${contrato.codigo_contrato}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <button name="accion" value="Baja">Baja contrato</button>
                        </div>
                        <div class="divTableCell2">
                            <button name="accion" value="Modificar">Modificar contrato</button>
                        </div>
                    </div>
                </div>
            </div>

            <c:choose>
                <c:when test="${verificacionConBaja==null}">
                </c:when>
                <c:when test="${verificacionConBaja=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Baja de Contrato realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionConBaja}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionConMod==null}">
                </c:when>
                <c:when test="${verificacionConMod=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Modificacion de Contrato realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionConMod}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionConBusqueda==null}">
                </c:when>
                <c:when test="${verificacionConBusqueda=='OK'}">
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionConBusqueda}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </form>
        <br>
        <!-- Detenidos / Bandas -->
        <form action="ControladorDetenidos" method="post">
            <div class="divTable4">
                <div class="captionD" id="detenidos">
                    <h3>Detenidos</h3>
                </div>    
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Codigo de Banda</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="number" name="codigoBan" required>
                        </div>
                        <div class="divTableCell">
                            <h3>Nombre y apellido del integrante</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="text" name="nomApeDet" required>
                        </div>
                        <div class="divTableCell">
                            <h3>Clave del detenido</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="number" name="claveDet" required>
                        </div>
                        <div class="divTableCell">
                            <button name="altaDet">Alta detenido</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${verificacionDetAlta==null}">
                </c:when>
                <c:when test="${verificacionDetAlta=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Alta de Entidad realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionDetAlta}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
        </form>
        <br>
        <form action="ControladorDetenidos" method="get">
            <div class="divTable5">
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Codigo de Banda para dar de baja</h3>
                        </div>
                        <div class="divTableCell">

                            <select name="codigoBanBaja" required>
                                <c:forEach var="banda" items="${bandas}">
                                    <option value="${banda.codigo}">${banda.codigo}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <h3>Codigo de Detenido para dar de baja</h3>
                        </div>
                        <div class="divTableCell">

                            <select name="codigoDetBaja" required>
                                <c:forEach var="detenido" items="${detenidos}">
                                    <option value="${detenido.clave}">${detenido.clave}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <button name="accion" value="Baja banda">Baja banda</button>
                            <button name="accion" value="Baja detenido">Baja detenido</button>
                        </div>
                        <div class="divTableCell2">
                            <button name="accion" value="Modificar banda">Modificar banda</button>
                            <button name="accion" value="Modificar detenido">Modificar detenido</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${verificacionDetBaja==null}">
                </c:when>
                <c:when test="${verificacionDetBaja=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Baja de Detenido realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionDetBaja}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionDetMod==null}">
                </c:when>
                <c:when test="${verificacionDetMod=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Modificacion de Detenido realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionDetMod}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionDetBusqueda==null}">
                </c:when>
                <c:when test="${verificacionDetBusqueda=='OK'}">
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionDetBusqueda}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionBanBaja==null}">
                </c:when>
                <c:when test="${verificacionBanBaja=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Baja de Banda realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionBanBaja}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionBanMod==null}">
                </c:when>
                <c:when test="${verificacionBanMod=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Modificacion de Banda realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionBanMod}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionBanBusqueda==null}">
                </c:when>
                <c:when test="${verificacionBanBusqueda=='OK'}">
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionBanBusqueda}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </form>
        <br>
        <!-- Jueces -->
        <form action="ControladorJueces" method="post">
            <div class="divTable4">
                <div class="captionD" id="jueces">
                    <h3>Jueces</h3>
                </div>    
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Número de juzgado</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="number" name="numeroJuz" required>
                        </div>
                        <div class="divTableCell">
                            <h3>Nombre y apellido del juez</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="text" name="nomApeJuez" required>
                        </div>
                        <div class="divTableCell">
                            <h3>Años de servicio</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="number" name="tiempoServ" required>
                        </div>
                        <div class="divTableCell">
                            <button name="altaJuez">Alta Juez</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${verificacionJuezAlta==null}">
                </c:when>
                <c:when test="${verificacionJuezAlta=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Alta de Juez realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionJuezAlta}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </form>
        <br>
        <form action="ControladorJueces" method="get">
            <div class="divTable5">
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Número de Juzgado</h3>
                        </div>
                        <div class="divTableCell">

                            <select name="codigoJuzgadoBaja" required>
                                <c:forEach var="juez" items="${jueces}">
                                    <option value="${juez.numeroJuzgado}">${juez.numeroJuzgado}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <h3>Codigo de Juez</h3>
                        </div>
                        <div class="divTableCell">
                            <select name="codigoJuezBaja" required>
                                <c:forEach var="juez" items="${jueces}">
                                    <option value="${juez.nomApe}">${juez.nomApe}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <button name="accion" value="Baja juez">Baja Juez</button>
                            <button name="accion" value="Baja juzgado">Baja Juzgado</button>
                        </div>
                        <div class="divTableCell2">
                            <button name="accion" value="Modificar juzgado">Modificar Juzgado</button>
                            <button name="accion" value="Modificar juez">Modificar Juez</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${verificacionJuzBaja==null}">
                </c:when>
                <c:when test="${verificacionJuzBaja=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Baja de Juzgado realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionJuzBaja}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionJuzMod==null}">
                </c:when>
                <c:when test="${verificacionJuzMod=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Modificacion de Juzgado realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionJuzMod}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionJuzBusqueda==null}">
                </c:when>
                <c:when test="${verificacionJuzBusqueda=='OK'}">
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionJuzBusqueda}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionJuezBaja==null}">
                </c:when>
                <c:when test="${verificacionJuezBaja=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Baja de Juez realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionJuezBaja}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionJuezMod==null}">
                </c:when>
                <c:when test="${verificacionJuezMod=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Modificacion de Juez realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionJuezMod}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionJuezBusqueda==null}">
                </c:when>
                <c:when test="${verificacionJuezBusqueda=='OK'}">
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionJuezBusqueda}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </form>
        <br>
        <!-- Casos -->
        <form action="ControladorCasos" method="post">
            <div class="divTable4">
                <div class="captionD" id="casos">
                    <h3>Casos/Delitos</h3>
                </div>    
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Codigo de Sucursal</h3>
                        </div>
                        <div class="divTableCell">
                            <select name="codigoSucDel" required>
                                <c:forEach var="sucursal" items="${sucursales}">
                                    <option value="${sucursal.codigoSuc}">${sucursal.codigoSuc}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <h3>Juez encargado</h3>
                        </div>
                        <div class="divTableCell">
                            <select name="juezEncargado" required>
                                <c:forEach var="juez" items="${jueces}">
                                    <option value="${juez.numeroJuzgado}">${juez.nomApe}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <h3>Codigo del Caso</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="number" name="codigoDel" required>
                        </div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Fecha del hecho</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="date" name="fechaDelito" required>
                        </div>
                        <div class="divTableCell">
                            <h3>Condena</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="checkbox" name="condena" value="1">
                        </div>
                        <div class="divTableCell">
                            <h3>Meses de condena</h3>
                        </div>
                        <div class="divTableCell">
                            <input type="number" name="tiempoCondena" required>
                        </div>
                    </div>
                </div>
            </div>
            <div class="divTable4">
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <c:choose>
                                <c:when test="${empty detenidos}">
                                    <h2>No posee contratos</h2>
                                </c:when>
                                <c:otherwise>

                                    <table id="implicados">
                                        <CAPTION ALIGN=top>Implicados</CAPTION>
                                        <tr>
                                            <th>Implicado</th>
                                            <th>Codigo de banda</th>
                                            <th>Clave del detenido</th>
                                            <th>Nombre y apellido</th>
                                        </tr>
                                        <c:forEach var="detenido" items="${detenidos}">
                                            <tr>
                                                <td><input type="checkbox" name="implicados" value="${detenido.clave}"></td>
                                                <td>${detenido.codigoBanda}</td>
                                                <td>${detenido.clave}</td>
                                                <td>${detenido.nomApe}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="divTableCell">
                            <button name="altaDel">Alta caso</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${verificacionCasoAlta==null}">
                </c:when>
                <c:when test="${verificacionCasoAlta=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Alta de Caso realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionCasoAlta}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </form>
        <br>
        <form action="ControladorCasos" method="get">
            <div class="divTable5">
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell">
                            <h3>Codigo de Caso</h3>
                        </div>
                        <div class="divTableCell">
                            <select name="codigoDelBaja" required>
                                <c:forEach var="caso" items="${casos}">
                                    <option value="${caso.codigoCaso}">${caso.codigoCaso}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="divTableCell">
                            <button name="accion" value="Baja">Baja caso</button>
                        </div>
                        <div class="divTableCell2">
                            <button name="accion" value="Modificar">Modificar caso</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${verificacionCasoBaja==null}">
                </c:when>
                <c:when test="${verificacionCasoBaja=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Baja de Caso realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionCasoBaja}</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionCasoMod==null}">
                </c:when>
                <c:when test="${verificacionCasoMod=='OK'}">
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Modificacion de Caso realizada correctamente.</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionCasoMod}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${verificacionCasoBusqueda==null}">
                </c:when>
                <c:when test="${verificacionCasoBusqueda=='OK'}">
                </c:when>
                <c:otherwise>
                    <div class="divTable5">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>${verificacionCasoBusqueda}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </form>
    </body>
</html>
