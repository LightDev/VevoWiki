<%--
    Document   : index
    Created on : Dec 12, 2013, 11:21:18 PM
    Author     : paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VevoWiki</title>
        <link href="css/StyleSheet1.css" rel="stylesheet" type="text/css">

    </head>
    <body>

        <form action="NewServlet">
            <h1>VevoWiki</h1>
            <input type="text" />
            <input type="submit" name="keyword" value="Szukaj" />
        </form>
        <c:forEach var="v" items="${vevoVideo}">

            ${v}
        </c:forEach>
        <c:forEach var="videoLink" items="${requestScope.vidLinks}">
            <object width="640" height="390">
                <param name="movie" value="<c:out value="${videoLink}" />" />
                <param name="wmode" value="transparent" />
                <script>document.write('<embed src="<c:out value="${videoLink}" />" type="application/x-shockwave-flash" wmode="transparent" width="640" height="390"></embed>')</script>
            </object>
        </c:forEach>
    </body>
</html>
