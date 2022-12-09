<%@ page import="com.huicloud.dockerubuntuvirtual.services.SSHKeyService" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="./../../partial/Navication.jsp"></jsp:include>
<div class="container-fluid ">
    <div class="row">
        <jsp:include page="../../partial/Left.jsp"></jsp:include>
        <div class="col-sm-10 m-0 p-0">
            <div class="card-header" style="display: flex; justify-content: space-between">
                <div><h4>Create SSH</h4></div>
                <div style="width: 60%">

                </div>
            </div>
            <div class="card-body ">
                <form action="" method="post">
                    <div class="input-group flex-nowrap">
                        <div class="input-group-prepend">
                            <span class="input-group-text" >Name key</span>
                        </div>
                        <input name="nameKey" type="text" class="form-control" placeholder="Name key" aria-label="Username"
                               aria-describedby="addon-wrapping">
                    </div>
                    <div><br></div>
                    <div class="input-group flex-nowrap">
                        <div class="input-group-prepend">
                            <span class="input-group-text" >Algorithm</span>
                        </div>
                        <input type="text" class="form-control" placeholder="RSA-4096 bits (sha256withrsa)" aria-label="Username"
                               aria-describedby="addon-wrapping" readonly="True">
                    </div>
                    <div>
                        <br>

                        <div align="right">



                            <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/Main/SSH" role="button">Cancel</a>
                            <button type="submit" class="btn btn-primary" style="width: 150px;">
                                Create
                            </button>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script>

</script>
</html>
