<%@ page import="com.huicloud.dockerubuntuvirtual.services.ServerServices" %>
<%@ page import="com.huicloud.dockerubuntuvirtual.models.Server" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="Users" scope="request" type="java.util.List<com.huicloud.dockerubuntuvirtual.models.User>"/>


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
            <h4>Manager User</h4>
          </div>
          <div>
            <button id="Create" type="button" class="btn btn-primary" style="width: 150px">Create User</button>
          </div>

        </div>

        <div class="card-body">
          <table id="tableInfo" style="width: 100%">
            <tr style="background-color: lightgray" align="center">
              <td>ID User</td>
              <td>Username</td>
              <td>Password</td>
              <td>Role</td>
              <td>&ensp;</td>
              <td>&ensp;</td>
            </tr>
            <input name="IdUser" id="IdUser" type="text" style="display: none">
            <input name="IdAction" id="IdAction" type="text" style="display: none">
            <c:forEach items="${Users}" var="c">
              <tr align="center">
                <td>${c.id}</td>
                <td>${c.username}</td>
                <td>${c.password}</td>
                <td>${c.type==0 ? "Admin":"Role"}</td>
                <td><button  class="btn btn-outline-success" style="border-style: none" onclick="editClick('${c.id}')" type="button"><b>Edit</b>
                </button></td>
                <td><button  class="btn btn-outline-danger" style="border-style: none" onclick="deleteClick('${c.id}')" type="submit"><b>Delete</b>
                </button></td>
              </tr>
            </c:forEach>
          </table>

          <div id="tableCreateUser" style="display: none">
            <div class="input-group flex-nowrap">
              <div class="input-group-prepend">
                <span class="input-group-text" id="addon-wrapping">Username</span>
              </div>
              <input name="Username" type="text" class="form-control" placeholder="Username"
                     aria-label="Username"
                     aria-describedby="addon-wrapping" >
            </div>
            <br>
            <div class="input-group flex-nowrap">
              <div class="input-group-prepend">
                <span class="input-group-text" >Password</span>
              </div>
              <input name="Password" type="text" class="form-control" placeholder="Password"
                     aria-label="Password"
                     aria-describedby="addon-wrapping" >
            </div>
            <br>
            <div align="right">
              <button id="btCreate" type="submit" class="btn btn-success">Create</button>
            </div>
          </div>

          <div id="tableEditUser" style="display: none">
            <div class="input-group flex-nowrap">
              <div class="input-group-prepend">
                <span class="input-group-text">New Username</span>
              </div>
              <input name="NewUsername" type="text" class="form-control" placeholder="New Username"
                     aria-label="NewUsername"
                     aria-describedby="addon-wrapping" >
            </div>
            <br>
            <div class="input-group flex-nowrap">
              <div class="input-group-prepend">
                <span class="input-group-text" >New Password</span>
              </div>
              <input name="NewPassword" type="text" class="form-control" placeholder="New Password"
                     aria-label="NewPassword"
                     aria-describedby="addon-wrapping" >
            </div>
            <br>
            <div class="input-group flex-nowrap">
              <div class="input-group-prepend">
                <span class="input-group-text">New Role</span>
              </div>
              <input name="NewRole" type="number" class="form-control" placeholder="New Role"
                     aria-label="NewRole"
                     aria-describedby="addon-wrapping" >
            </div>
            <div align="right">
              <button id="btSave" type="submit" class="btn btn-success">Save</button>
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
            document.getElementById('tableCreateUser').style.display = 'block'
            document.getElementById('IdAction').value = 2
          }

  function editClick(id){
    document.getElementById('Create').style.visibility = 'hidden'
    document.getElementById('IdUser').value = id
    document.getElementById('IdAction').value = 1
    document.getElementById('tableInfo').style.display = 'none'
    document.getElementById('tableEditUser').style.display = 'block'
  }

  function deleteClick(id){
    document.getElementById('IdUser').value = id
    document.getElementById('IdAction').value = 0
  }
</script>
</html>