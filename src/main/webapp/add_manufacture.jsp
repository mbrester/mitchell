

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

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

        <title>New Manufacturer</title>
        <script>
            function validateForm() {
               
                var name = document.forms["form"]["manufactureName"].value;
                if (name === "") {
                    alert("Manufacture Name Must be filled in.");
                    return false;
                }
                var description = document.forms["form"]["manufactureCity"].value;
                if (description === "") {
                    alert("Manufacture City Must be filled in.");
                    return false;
                }
                
            }
        </script>
        <style>
            body{
                background-color: lightgrey;
            }  
        </style>

    </head>
    <body>
        <h1>New Magic Supply</h1>
        <div class="row">
            <div class="box">
                <div class="col-lg-12">

                    <form onsubmit="return validateForm()" name="form"  method="POST" action="ManController?action=test">
                        <p>
                        <table  class="table-bordered table-hover table" >

                            <tr>
                                <td >ID</td>
                                <td align="left"><input type="text" value="" name="manufactureId" readonly/></td>
                            </tr>         


                            <tr>
                                <td  align="left">Name:</td>
                                <td align="left"><input type="text" value="" name="manufactureName" /></td>
                            </tr>
                            <tr>
                                <td  align="left">City:</td>
                                <td align="left"><input type="text" value="" name="manufactureCity" /></td>
                            </tr>
                          

                            <tr>
                        </table>  <br>
                        
                        <input type="submit" value="Add Manufacture" name="action" /> <a href="ManController?action=list">Back to List of Supplies</a>


                        </p>
                    </form>
                                Logged in as: <sec:authentication property="principal.username"></sec:authentication> ::
            <a href='<%= this.getServletContext().getContextPath() + "/j_spring_security_logout"%>'>Log Me Out</a>
         
                </div>
            </div>
        </div>
    </body>
</html>
