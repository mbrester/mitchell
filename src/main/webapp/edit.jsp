

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap.css" rel="stylesheet" type="text/css"/>
        <style>
            body{
                background-color: lightgrey;
            }  
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script>
            function validateForm() {
                var price = document.forms["form"]["productPrice"].value;
                if (price <= 0 || price === "") {
                    alert("Price must be filled out and larger than 0");
                    return false;
                }
                var name = document.forms["form"]["productName"].value;
                if (name === "") {
                    alert("Product Name Must be filled in.")
                    return false;
                }
                var description = document.forms["form"]["productDescription"].value;
                if (description === "") {
                    alert("Product description Must be filled in.")
                    return false;
                }
                var imageUrl = document.forms["form"]["productImageUrl"].value;
                if (imageUrl === "") {
                    alert("Product Image Url Must be filled in.")
                    return false;
                }
            }
        </script>


        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <title>Supply List</title>
    </head>
    <body>
        <h1>Supply List</h1>
        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <form onsubmit="return validateForm()" name="form" method="POST" action="MainController?action=update">
                        <table >


                            <tr>
                                <td style="" align="left">ID </td>
                                <td align="left"><input type="text" value="${suply.productId}" name="productId" readonly/></td>
                            </tr>         



                            <tr>
                                <td style="" align="left">Name </td>
                                <td align="left"><input type="text" value="${suply.productName}" name="productName" /></td>
                            </tr>
                            <tr>
                                <td style="" align="left">Product Description: </td>
                                <td align="left"><input type="text" value="${suply.productDescription}" name="productDescription" /></td>
                            </tr>
                            <tr>
                                <td style="" align="left">Product Price: </td>
                                <td align="left"><input type="text"  value="${suply.productPrice}" name="productPrice" /></td>
                            </tr>
                            <tr>
                                <td style="" align="left">Name</td>
                                <td align="left"><input type="text" value="${suply.productImageUrl}" name="productImageUrl" /></td>
                            </tr>

                        </table>        



                        <input type="submit" value="Update" name="action" /> <a href="MainController?action=list">Back to List of Supplies</a>


                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
