<%@ page import="com.huicloud.dockerubuntuvirtual.services.SSHKeyService" %>
<%@ page import="com.huicloud.dockerubuntuvirtual.models.SSHKey" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>




<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin SSH Keys</title>
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
<%--<nav class="navbar navbar-expand-lg navbar-light bg-light">--%>
<%--  <a class="navbar-brand" href="#">--%>
<%--    <i class="fa fa-cloud fa-2x" aria-hidden="true"></i>--%>
<%--  </a>--%>
<%--  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--    <span class="navbar-toggler-icon"></span>--%>
<%--  </button>--%>

<%--  <div class="collapse navbar-collapse" id="navbarSupportedContent" style="display: flex; justify-content: space-between">--%>
<%--    <h5>--%>
<%--      Hui & Hiu Cloud--%>
<%--    </h5>--%>
<%--    <h5 class="col-lg-2">--%>
<%--      User name--%>
<%--    </h5>--%>
<%--  </div>--%>
<%--</nav>--%>
<jsp:include page="./../partial/Navication.jsp"></jsp:include>

<div class="container-fluid ">
  <div class="row">
    <jsp:include page="../partial/Left.jsp"></jsp:include>
    <div class="col-sm-10 m-0 p-0">
      <div class="card-header"
           style=" display: flex; justify-content: space-between; border-style: none">

        <h4>Manager SSH Key</h4>

        <div align="right">

          <a class="btn btn-primary" href="${pageContext.request.contextPath}/Main/SSH/Create" role="button"style="width: 150px" >Create SSHKey</a>

        </div>
      </div>
      <div class="card-body">
        <table style="width: 100%">
          <tr style="background-color: lightgray" align="center">
            <td>ID</td>
            <td>Name</td>
            <td>Algorithm</td>
          </tr>
          <% for (SSHKey n : SSHKeyService.findAllById((Integer) request.getSession().getAttribute("userId"))) {%>
          <tr align="center">
            <td> <%=n.getNameKey()%></td>
            <td> <%=n.getId()%></td>
            <td>RSA 4096 bits (sha256withrsa)</td>
          </tr>
          <% } %>
        </table>
      </div>
    </div>
  </div>
</div>
</body>
</html>
