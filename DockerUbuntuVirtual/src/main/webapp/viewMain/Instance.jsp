<%@ page import="com.huicloud.dockerubuntuvirtual.services.InstanceService" %>
<%@ page import="com.huicloud.dockerubuntuvirtual.services.NetworkService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="instances" scope="request" type="java.util.List<com.huicloud.dockerubuntuvirtual.models.Instance>"/>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
<%--<nav class="navbar navbar-expand-lg navbar-light bg-light">--%>
<%--    <a class="navbar-brand" href="#">--%>
<%--        <i class="fa fa-cloud fa-2x" aria-hidden="true"></i>--%>
<%--    </a>--%>
<%--    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"--%>
<%--            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--        <span class="navbar-toggler-icon"></span>--%>
<%--    </button>--%>

<%--    <div class="collapse navbar-collapse" id="navbarSupportedContent"--%>
<%--         style="display: flex; justify-content: space-between">--%>
<%--        <h5>--%>
<%--            Hui & Hiu Cloud--%>
<%--        </h5>--%>
<%--        <h5 class="col-lg-2">--%>
<%--            User name--%>
<%--        </h5>--%>
<%--    </div>--%>
<%--</nav>--%>
<jsp:include page="./../partial/Navication.jsp"></jsp:include>
<div class="container-fluid mt-3 ">
    <div class="row">
        <jsp:include page="../partial/Left.jsp"></jsp:include>
        <div class="col-sm-9">
            <div class="card-header mr-2" style="border-style: solid; border-width: 1px; display: flex; justify-content: space-between">
                <div style="display: flex; justify-content: space-between">
                    <h4>Instance</h4>
                </div>
                <div style="width: 60%">
                    <div class="dropdown" style="width: 100%" align="right">

                        <button id="action" type="button" data-toggle="dropdown" class="btn btn-outline-dark dropdown-toggle" disabled>Actions</button>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/Main/Instance/Launch" role="button"style="width: 150px" >Launch Instance</a>
                        <div class="dropdown-menu">
                            <a id="start" class="dropdown-item" href="#">Start</a>
                            <a  id="stop" class="dropdown-item" href="#">Stop</a>
                            <a  id="terminate"class="dropdown-item" href="#">Terminate</a>
                        </div>
                    </div>

                </div>
            </div>

            <div class="card-body mr-2" style="border-style: solid; border-width: 1px">
                <table style="width: 100%">
                    <tr style="background-color: beige" align="center">
                        <td></td>
                        <td>ID</td>
                        <td>Name</td>
                        <td>State</td>
                        <td>Network</td>
                        <td>Port</td>
                        <td>CPUS</td>
                        <td>Memory</td>
                    </tr>

                    <c:forEach items="${instances}" var="c">
                        <tr align="center">
                            <td><input name="choose" type="radio" onclick="choose(${c.id})" value="Yes"/></td>
                            <td>${c.id}</td>
                            <td>${c.nameInstance.split(0)[1]}</td>
                            <td id="state">${c.state}</td>
                            <td>${c.serverIp()}</td>
                            <td>${c.getport()}</td>
                            <td>${c.cpus}</td>
                            <td>${c.memory}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    function choose(id){
        document.getElementById("start").classList.remove("disabled");
        document.getElementById("stop").classList.remove("disabled");

        // document.getElementById("idInstance").innerText = id
        document.getElementById("action").disabled = false
        if (document.getElementById("state").innerText=='Running') {
            document.getElementById("start").classList.add("disabled");
        }
        if (document.getElementById("state").innerText=='Stopped') {
            document.getElementById("stop").classList.add("disabled");
        }

    }
</script>
</body>
</html>
