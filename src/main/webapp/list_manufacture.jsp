
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap.css" rel="stylesheet" type="text/css"/>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <title>Magic Supply Admin tool</title>
        <style>
            body{
                background-color: lightgrey;
            }  
            </style>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
            
            
    </head>
    <body>
        <h1>Manufacture List</h1>
        <div class="row">
            <div class="box">
                <div class="col-lg-12">
        <table border="1"  class="table-bordered table-hover table">
            <tr>
                <th align="left" class="tableHead">ID</th>
                <th align="left" class="tableHead">Manufacture Name</th>
                <th align="left" class="tableHead">Manufacture City</th>
                <th align="right" class="tableHead"></th>
            </tr>
        <c:forEach var="s" items="${manufactures}" varStatus="rowCount">
            <c:choose>
                <c:when test="${rowCount.count % 2 == 0}">
                    <tr style="background-color: white;">
                </c:when>
                <c:otherwise>
                    <tr style="background-color: cyan;">
                </c:otherwise>
            </c:choose>
            <td align="left">${s.manufactureId}</td>
            <td align="left">${s.name}</td>
            <td align="left">${s.city}</td>

            <td align ="right"> <a href="ManController?action=edit&manufactureId=${s.manufactureId}"> edit </a> <a href="ManController?action=delete&manufactureId=${s.manufactureId}"> delete </a> 
        </tr>
        </c:forEach>
        </table>
        <br>
        
        <form method="POST" action="ManController?action=add">
            <input type="submit" value="add"/>
        </form>
         
        
        
            Logged in as: <sec:authentication property="principal.username"></sec:authentication> ::
            <a href='<%= this.getServletContext().getContextPath() + "/j_spring_security_logout"%>'>Log Me Out</a>
          <a href="/AdminService/">Go Back to Main Page</a>
            
        <c:if test="${errMsg != null}">
            <p style="font-weight: bold;color: red;">Sorry, data was unable to be retrieved:<br>
                ${errMsg}</p>
        </c:if>
                </div>
            </div>
        </div>
        
    </body>
</html>
