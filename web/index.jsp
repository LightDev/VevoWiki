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
        <div id="page">
            <div id="simpleHeader">
                <form method="GET" action="NewServlet">
                    <ul>
                        <!--value="search for band..."-->
                        <li><img style="padding-top: 10px;display: inline;" src="img/vevowiki.png" width="70" height="30" /></li>
                        <li><input type="text" name="keyword" /></li>
                        <li><input type="submit"  value="Search" class="button" /></li>
                    </ul>
                </form>
            </div>


            <h1 class="h2_decoration ">${band.name}</h1>
            <h2 class="h2_decoration2">Band Bio</h2>
            <p>${band.description}</p>
            <h2 class="h2_decoration2">Most popular</h2>
            <h2 class="h2_decoration2">Album timeline</h2>

            <c:forEach var="entry" items="${albumsTab}" varStatus="status">
                <h2 class="h2_decoration2" >${entry.key.releaseYear} - ${entry.key.name}</h2>
                <ul>
                    <c:forEach var="track" items="${entry.value}" varStatus="status2">
                        <li style="display: block">$<track.title></li>
                        </c:forEach>
                </ul>
            </c:forEach>



            <div>
                <p style="margin-top: 10px;border-top: 1px solid #ddd; color: #999;">8SOFT | All registered trademarks and resources belong to their respective owners.</p>
            </div>
        </div>
    </body>
</html>
