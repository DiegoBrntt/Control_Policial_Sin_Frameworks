<%-- 
    Document   : vistaResultado
    Created on : 2 nov. 2021, 23:26:49
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
            <c:when test="${empty entidades}">
                <h2>No hay entidades cargadas.</h2>
            </c:when>
            <c:otherwise>
                <table id="entidades">
                    <CAPTION ALIGN=top>Entidades</CAPTION>
                    <tr>
                        <th>Codigo Entidad</th>
                        <th>Domicilio Central</th>
                        <th>Cantidades de Sucursales</th>
                    </tr>
                    <c:forEach var="entidad" items="${entidades}">
                        <tr>
                            <td>${entidad.codigo}</td>
                            <td>${entidad.domicilio}</td>
                            <td>${entidad.cantSucs}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <br>
        <c:choose>
            <c:when test="${empty entidades}">
                <h2>No hay entidades cargadas.</h2>
            </c:when>
            <c:otherwise>
                <table id="Sucursales">
                    <CAPTION ALIGN=top>Sucursales</CAPTION>
                    <tr>
                        <th>Codigo Entidad</th>
                        <th>Codigo Sucursal</th>
                        <th>Domicilio Central</th>
                        <th>Cantidad de empleados</th>
                    </tr>
                    <c:forEach var="sucursal" items="${sucursales}">
                        <tr>
                            <td>${sucursal.codigoEnt}</td>
                            <td>${sucursal.codigoSuc}</td>
                            <td>${sucursal.domicilio}</td>
                            <td>${sucursal.cantEmpleados}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <br>
        <c:choose>
            <c:when test="${empty contratos}">
                <h2>No hay contratos cargados</h2>
            </c:when>
            <c:otherwise>
                <table id="contratos">
                    <CAPTION ALIGN=top>Contratos</CAPTION>
                    <tr>
                        <th>Contrato</th>
                        <th>Fecha</th>
                        <th>Sucursal</th>
                        <th>Vigilante</th>
                        <th>Dias Contratados</th>
                        <th>Armado</th>
                        <th>Estado</th>
                    </tr>
                    <c:forEach var="contrato" items="${contratos}">
                        <tr>
                            <td>${contrato.codigo_contrato}</td>
                            <td>${contrato.fecha_contratacion}</td>
                            <td>${contrato.codigo_suc}</td>
                            <td>${contrato.codigo_vig}</td>
                            <td>${contrato.dias_contratado}</td>
                            <td>${contrato.armado==true? 'Si':'No'} </td>
                            <td>${contrato.estado==true? 'Vigente':'Caduco'}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <br>
        <c:choose>
            <c:when test="${empty bandas}">
                <h2>No hay bandas cargadas</h2>
            </c:when>
            <c:otherwise>

                <table id="bandas">
                    <CAPTION ALIGN=top>Bandas</CAPTION>
                    <tr>
                        <th>Codigo de banda</th>
                        <th>Cantidad de integrantes</th>
                    </tr>
                    <c:forEach var="banda" items="${bandas}">
                        <tr>
                            <td>${banda.codigo}</td>
                            <td>${banda.cantIntegrantes}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <br>
        <c:choose>
            <c:when test="${empty detenidos}">
                <h2>No hay detenidos cargados</h2>
            </c:when>
            <c:otherwise>

                <table id="detenidos">
                    <CAPTION ALIGN=top>Detenidos</CAPTION>
                    <tr>
                        <th>Codigo de banda</th>
                        <th>Clave del detenido</th>
                        <th>Nombre y apellido</th>
                    </tr>
                    <c:forEach var="detenido" items="${detenidos}">
                        <tr>
                            <td>${detenido.codigoBanda}</td>
                            <td>${detenido.clave}</td>
                            <td>${detenido.nomApe}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <br>
        <c:choose>
            <c:when test="${empty jueces}">
                <h2>No hay jueces cargados</h2>
            </c:when>
            <c:otherwise>

                <table id="jueces">
                    <CAPTION ALIGN=top>Jueces</CAPTION>
                    <tr>
                        <th>Número de Juzgado</th>
                        <th>Nombre y apellido</th>
                        <th>Años de servicio</th>
                    </tr>
                    <c:forEach var="juez" items="${jueces}">
                        <tr>
                            <td>${juez.numeroJuzgado}</td>
                            <td>${juez.nomApe}</td>
                            <td>${juez.tiempoServicio}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <br>
        <c:choose>
            <c:when test="${empty casos}">
                <h2>No hay entidades cargadas.</h2>
            </c:when>
            <c:otherwise>
                <table id="delitos">
                    <CAPTION ALIGN=top>Delitos</CAPTION>
                    <tr>
                        <th>Codigo Caso</th>
                        <th>Fecha del caso</th>
                        <th>Juez encargado</th>
                        <th>Codigo de Sucursal</th>
                        <th>Condenados</th>
                        <th>Condenado</th>
                        <th>Tiempo de Condena</th>
                        <th>Estado actual</th>
                    </tr>
                    <c:forEach var="delito" items="${casos}">
                        <tr>
                            <td>${delito.codigoCaso}</td>
                            <td>${delito.fechaCaso}</td>
                            <td>${delito.nombreJuez}</td>
                            <td>${delito.codigoSuc}</td>
                            <td>${empty delito.implicados?'No hay condenados':delito.implicados}</td>
                            <td>${delito.condena==1? 'Si':'No'} </td>
                            <td>${delito.condena==1? delito.tiempoCondena :'Sin condena'} </td>
                            <td>${delito.condena==1? delito.cumpliendo==true? 'Cumpliendo' :'Cumplida':'-'} </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
                
    </body>
</html>

