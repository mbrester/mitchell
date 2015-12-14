

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap.css" rel="stylesheet" type="text/css"/>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

        <title>New Magic Supply</title>
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

                    <form onsubmit="return validateForm()" name="form"  method="POST" action="MainController?action=test">
                        <p>
                        <table >

                            <tr>
                                <td >ID</td>
                                <td align="left"><input type="text" value="" name="productId" readonly/></td>
                            </tr>         


                            <tr>
                                <td  align="left">Name:</td>
                                <td align="left"><input type="text" value="" name="productName" /></td>
                            </tr>
                            <tr>
                                <td  align="left">Description:</td>
                                <td align="left"><input type="text" value="" name="productDescription" /></td>
                            </tr>
                            <tr>
                                <td  align="left">Price</td>
                                <td align="left"><input type="text" value="" name="productPrice" /></td>
                            </tr>
                            <tr>
                                <td  align="left">Image Url:</td>
                                <td align="left"><input type="text" value="" name="productImageUrl" /></td>
                            </tr>





                            <tr>
                        </table>  <br>

                        <input type="submit" value="Add Supply" name="action" /> <a href="MainController?action=list">Back to List of Supplies</a>



                        </p>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
