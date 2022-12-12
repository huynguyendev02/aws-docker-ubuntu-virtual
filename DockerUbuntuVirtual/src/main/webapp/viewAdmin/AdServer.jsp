<%@ page import="com.huicloud.dockerubuntuvirtual.services.ServerServices" %>
<%@ page import="com.huicloud.dockerubuntuvirtual.models.Server" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="Servers" scope="request" type="java.util.List<com.huicloud.dockerubuntuvirtual.models.Server>"/>


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
<jsp:include page="./../partial/Navication.jsp"></jsp:include>

<div class="container-fluid ">
  <div class="row">
    <jsp:include page="../partial/Left.jsp"></jsp:include>
    <div class="col-sm-10 m-0 p-0">
      <form action="" method="post" style="width: 100%">
      <div class="card-header"
           style=" display: flex; justify-content: space-between; border-style: none">
        <div style="display: flex; justify-content: space-between">
          <h4>Manager Server</h4>
        </div>
        <div>
          <button id="Create" onclick="Create()" type="button" class="btn btn-primary" style="width: 150px">Create Server</button>
        </div>

      </div>

      <div class="card-body">
        <table id="tableInfo" style="width: 100%">
          <tr style="background-color: lightgray" align="left">
            <td>ID Server</td>
            <td>IP Server</td>
            <td>State</td>
            <td>&ensp;</td>
          </tr>
          <input name="IdServer" id="IdServer" type="text" style="display: none">
          <input name="IdAction" id="IdAction" type="text" style="display: none">
          <c:forEach items="${Servers}" var="c">
            <tr align="left">
              <td>${c.id}</td>
              <td>${c.ipServer}</td>
              <td> State </td>
              <td><button  class="btn btn-outline-danger" style="border-style: none" onclick="deleteClick('${c.id}')" type="submit"><b>Delete</b>
              </button></td>
            </tr>
          </c:forEach>
        </table>

        <div id="tableCreateServer" style="display: none">
          <div class="input-group flex-nowrap">
            <div class="input-group-prepend">
              <span class="input-group-text" id="addon-wrapping">ServerName</span>
            </div>
            <input name="ServerName" type="text" class="form-control" placeholder="ServerName"
                   aria-label="ServerName"
                   aria-describedby="addon-wrapping" required>
          </div>
          <br>
          <div align="right">
            <button id="btCreate" type="submit" class="btn btn-success">Create</button>
          </div>
        </div>
      </div>
      </form>
    </div>
  </div>
</div>
</body>
<script>
    document.getElementById('Create').onclick =
      function Create(){
      document.getElementById('tableInfo').style.display = 'none'
      document.getElementById('tableCreateServer').style.display = 'block'
      document.getElementById('IdAction').value = 1
    }

    function deleteClick(id){
    document.getElementById('IdServer').value = id
    document.getElementById('IdAction').value = 0
  }
</script>
</html>
