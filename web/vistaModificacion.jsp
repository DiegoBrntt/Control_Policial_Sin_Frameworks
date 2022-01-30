<%-- 
    Document   : vistaModificacion
    Created on : 12 nov. 2021, 15:28:05
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
        <c:choose>
            <c:when test="${not empty usuarioMod}">
                <form action="ControladorModificacionesUsuarios" method="post">
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
                                    <input type="text" name="nombreUsu" value="${usuarioMod.clave}" required>
                                </div>
                                <div class="divTableCell">
                                    <h3>Contraseña Usuario</h3>
                                </div>
                                <div class="divTableCell">
                                    <input type="text" name="passUsu" value="${usuarioMod.pass}" required>
                                </div>
                                <div class="divTableCell">
                                    <h3>Edad</h3>
                                </div>
                                <div class="divTableCell">
                                    <input type="number" name="edadUsu" value="${usuarioMod.edad}" required>
                                </div>
                                <div class="divTableCell">
                                    <h3>Tipo</h3>
                                </div>
                                <div class="divTableCell">
                                    <select name="tipoUsu" required>
                                        <option value="Administrador" ${usuarioMod.tipo=='Administrador'?'Selected':''}>Administrador</option>
                                        <option value="Investigador" ${usuarioMod.tipo=='Investigador'?'Selected':''}>Investigador</option>
                                        <option value="Vigilante" ${usuarioMod.tipo=='Vigilante'?'Selected':''}>Vigilante</option>
                                    </select>
                                </div>
                                <div class="divTableCell">
                                    <input type="hidden" name="nombreUsuMod" value="${usuarioMod.clave}">
                                    <button name="modUsu">Modificar Usuario</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </c:when>
            <c:when test="${not empty entidad}">
                <form action="ControladorModificacionesEntidades" method="post">
                    <div class="divTable4">
                        <div class="captionD" id="entidades">
                            <h3>Entidades</h3>
                        </div>    
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell2">
                                    <h3>Codigo de Entidad</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="number" name="codigoEntNuevo" value="${entidad.codigo}" required>
                                </div>
                                <div class="divTableCell2">
                                    <h3>Domicilio Central</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="hidden" name="codigoEnt" value="${entidad.codigo}">
                                    <input type="text" name="central" value="${entidad.domicilio}" required>
                                </div>
                                <div class="divTableCell2">
                                    <button name="modificarEnt">Modificar entidad</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </c:when>
            <c:when test="${not empty sucursal}">
                <form action="ControladorModificacionesSucursales" method="post">
                    <div class="divTable4">
                        <div class="captionD" id="sucursales">
                            <h3>Sucursales</h3>
                        </div>    
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell2">
                                    <h3>Codigo de Entidad</h3>
                                </div>
                                <div class="divTableCell2">
                                    <select name="codigoEntSuc">
                                        <c:forEach var="entidad" items="${entidades}">
                                            <option value="${entidad.codigo}" ${entidad.codigo == sucursal.codigoSuc?"selected":""}>${entidad.codigo}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="divTableCell2">
                                    <h3>Codigo de Sucursal</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="number" name="codigoSuc" value="${sucursal.codigoSuc}" required>
                                </div>
                                <div class="divTableCell2">
                                    <h3>Domicilio Sucursal</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="text" name="domicilioSuc" value="${sucursal.domicilio}" required>
                                </div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell2">
                                    <h3>Fecha de contratación</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="date" name="fechaContSuc" value="${sucursal.fechaContratacion}" required>
                                </div>
                                <div class="divTableCell2">
                                    <h3>Frecuencia de contratacion (en dias)</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="number" name="diasContSuc" value="${sucursal.frecuenciaContratacion}" required>
                                </div>
                                <div class="divTableCell2">
                                    <h3>cantidad de empleados</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="number" name="empleadosSuc" value="${sucursal.cantEmpleados}" required>
                                </div>
                                <div class="divTableCell2">
                                    <input type="hidden" name="codSuc" value="${sucursal.codigoSuc}">
                                    <button name="modSuc">Modificar sucursal</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </c:when>
            <c:when test="${not empty contrato}">
                <form action="ControladorModificacionesContratos" method="post">
                    <div class="divTable4">
                        <div class="captionD" id="contratos">
                            <h3>Contratos</h3>
                        </div>    
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell2">
                                    <h3>Codigo de Sucursal</h3>
                                </div>
                                <div class="divTableCell2">
                                    <select name="codigoSucCon">
                                        <c:forEach var="sucursal" items="${sucursales}">
                                            <option value="${sucursal.codigoSuc}" ${contrato.codigo_suc == sucursal.codigoSuc?"selected":""}>${sucursal.codigoSuc}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="divTableCell2">
                                    <h3>Codigo de Vigilante</h3>
                                </div>
                                <div class="divTableCell2">
                                    <select name="codigoVigCon">
                                        <c:forEach var="vigilante" items="${vigilantes}">
                                            <option value="${vigilante.clave}" ${contrato.codigo_vig == vigilante.clave?"selected":""}>${vigilante.clave}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="divTableCell2">
                                    <h3>Codigo de Contrato</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="number" name="codigoCon" value="${contrato.codigo_contrato}">
                                </div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell2">
                                    <h3>Fecha del contrato</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="date" name="fechaContrato" value="${contrato.fecha_contratacion}">
                                </div>
                                <div class="divTableCell2">
                                    <h3>Cantidad de dias contratado</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="number" name="diasContrato" value="${contrato.dias_contratado}">
                                </div>
                                <div class="divTableCell2">
                                    <h3>Posesión de arma</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="checkbox" value="1" name="armado" ${contrato.armado==true?'checked':''}>
                                </div>
                                <div class="divTableCell2">
                                    <input type="hidden" value="${contrato.codigo_contrato}" name="codigoConMod">
                                    <button name="modificarCon">Modificar contrato</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </c:when>
            <c:when test="${not empty banda}">
                <form action="ControladorModificacionesDetenidos" method="post">
                    <div class="divTable4">
                        <div class="captionD" id="bandas">
                            <h3>Banda</h3>
                        </div>    
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell2">
                                    <h3>Codigo de Banda</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="number" name="codigoBan" value="${banda.codigo}" required>
                                </div>
                                <div class="divTableCell2">
                                    <input type="hidden" name="codigoBanMod" value="${banda.codigo}">
                                    <button name="accion" value="Modificar banda">Modificar banda</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </c:when>
            <c:when test="${not empty detenido}">
                <form action="ControladorModificacionesDetenidos" method="post">
                    <div class="divTable4">
                        <div class="captionD" id="detenidos">
                            <h3>Detenidos</h3>
                        </div>    
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell2">
                                    <h3>Codigo de Banda</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="number" name="codigoBan" value="${detenido.codigoBanda}" required>
                                </div>
                                <div class="divTableCell2">
                                    <h3>Nombre y apellido del integrante</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="text" name="nomApeDet" value="${detenido.nomApe}" required>
                                </div>
                                <div class="divTableCell2">
                                    <h3>Clave del detenido</h3>
                                </div>
                                <div class="divTableCell2">
                                    <input type="number" name="claveDet" value="${detenido.clave}" required>
                                </div>
                                <div class="divTableCell2">
                                    <input type="hidden" name="codigoDetMod" value="${detenido.clave}">
                                    <button name="accion" value="Modificar detenido">Modificar detenido</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </c:when>
            <c:when test="${not empty juzgado}">
                <form action="ControladorModificacionesJueces" method="post">
                    <div class="divTable4">
                        <div class="captionD" id="jueces">
                            <h3>Jueces / Juzgado</h3>
                        </div>    
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Número de juzgado</h3>
                                </div>
                                <div class="divTableCell">
                                    <input type="number" name="numeroJuz" value="${juzgado}" required>
                                </div>
                                <input type="hidden" name="numeroJuzMod" value="${juzgado}">
                                <button name="accion" value="Modificar juzgado">Modificar Juzgado</button>
                            </div>
                        </div>
                    </div>
                </form>
            </c:when>
            <c:when test="${not empty juez}">
                <form action="ControladorModificacionesJueces" method="post">
                    <div class="divTable4">
                        <div class="captionD" id="jueces">
                            <h3>Jueces / Juzgado</h3>
                        </div>    
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Número de juzgado</h3>
                                </div>
                                <div class="divTableCell">
                                    <input type="number" name="numeroJuz" value="${juez.numeroJuzgado}" required>
                                </div>
                                <div class="divTableCell">
                                    <h3>Nombre y apellido del juez</h3>
                                </div>
                                <div class="divTableCell">
                                    <input type="text" name="nomApeJuez" value="${juez.nomApe}" required>
                                </div>
                                <div class="divTableCell">
                                    <h3>Años de servicio</h3>
                                </div>
                                <div class="divTableCell">
                                    <input type="number" name="tiempoServ" value="${juez.tiempoServicio}" required>
                                </div>
                                <div class="divTableCell">
                                    <input type="hidden" name="nomApeJuezMod" value="${juez.nomApe}">
                                    <button name="accion" value="Modificar juez">Modificar Juez</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </c:when>
            <c:when test="${not empty caso}">
                <form action="ControladorModificacionesCasos" method="post">
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
                                    <select name="codigoSucDel" required="">
                                        <c:forEach var="sucursal" items="${sucursales}">
                                            <option value="${sucursal.codigoSuc}" ${caso.codigoSuc == sucursal.codigoSuc?"selected":""}>${sucursal.codigoSuc}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="divTableCell">
                                    <h3>Juez encargado</h3>
                                </div>
                                <div class="divTableCell">
                                    <select name="juezEncargado" required>
                                        <c:forEach var="juez" items="${jueces}">
                                            <option value="${juez.numeroJuzgado}" ${caso.numeroJuzgado == juez.numeroJuzgado?"selected":""}>${juez.nomApe}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="divTableCell">
                                    <h3>Codigo del Caso</h3>
                                </div>
                                <div class="divTableCell">
                                    <input type="number" name="codigoDel" value="${caso.codigoCaso}" required>
                                </div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    <h3>Fecha del hecho</h3>
                                </div>
                                <div class="divTableCell">
                                    <input type="date" name="fechaDelito" value="${caso.fechaCaso}" required>
                                </div>
                                <div class="divTableCell">
                                    <h3>Condena</h3>
                                </div>
                                <div class="divTableCell">
                                    <input type="checkbox" name="condena" ${caso.condena==1?'checked':''} value="1">
                                </div>
                                <div class="divTableCell">
                                    <h3>Meses de condena</h3>
                                </div>
                                <div class="divTableCell">
                                    <input type="number" name="tiempoCondena" value="${caso.tiempoCondena}" required>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="divTable4">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">
                                    ${caso.implicados}
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
                                    <input type="hidden" name="codigoCasoMod" value="${caso.codigoCaso}">
                                    <button name="modCaso">Modificar caso</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </c:when>
        </c:choose>
    </body>
</html>