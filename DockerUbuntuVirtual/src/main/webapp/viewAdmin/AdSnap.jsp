<%@ page import="com.huicloud.dockerubuntuvirtual.services.InstanceService" %>
<%@ page import="com.huicloud.dockerubuntuvirtual.services.NetworkService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="Snapshots" scope="request" type="java.util.List<com.huicloud.dockerubuntuvirtual.models.Snapshot>"/>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin Snapshots</title>
  <style>
    a.disabled {
      pointer-events: none;
      cursor: default;
    }
  </style>
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
      <form action="" method="post">
        <div class="card-header"
             style=" display: flex; justify-content: space-between; border-style: none">
          <div style="display: flex; justify-content: space-between">
            <h4>Manager Snapshot</h4>
          </div>

          <div style="width: 60%">
            <div class="dropdown" style="width: 100%" align="right">
              <button id="Launch" onclick="launch()" type="submit" class="btn btn-primary" style="width: 150px;visibility: hidden">Restore</button>
            </div>
          </div>
        </div>
        <input name="IdSnapshot" id="IdSnapshot" type="text" style="display: none">
        <input name="idAction" id="idAction" type="text" style="display: none">

        <div class="card-body">
          <table style="width: 100%">
            <tr style="background-color: lightgray" align="center">
              <td>&emsp;</td>
              <td>ID</td>
              <td>Name</td>
              <td>Base Instance</td>
              <td>Server</td>
              <td>Created at</td>
              <td>&emsp;</td>

            </tr>

            <c:forEach items="${Snapshots}" var="c">
              <tr align="center">
                <td><input name="chooseInstance" type="radio" onclick="choose(${c.id})" value="Yes"/></td>
                <td>${c.id}</td>
                <td>${c.nameSnapshot}</td>
                <td>${c.nameInstance.split("0")[1]}</td>
                <td>${c.IPServer}</td>
                <td>${c.createdAt}</td>
                <td>
                  <button type="submit" class="btn btn-outline-danger" style="border-style: none" onclick="deleteClick(${c.id})"><b>Delete</b>
                  </button>
                </td>
              </tr>
            </c:forEach>
          </table>
        </div>
      </form>
    </div>
  </div>
</div>
<script>
  function deleteClick(id) {
    document.getElementById("IdSnapshot").value = id;
    document.getElementById("idAction").value = 0;
  }

  function launch(){
    document.getElementById("idAction").value = 1;
  }
  function choose(n) {
    document.getElementById("IdSnapshot").value = n
    document.getElementById("Launch").style.visibility = 'visible'
  }

</script>
</body>
</html>