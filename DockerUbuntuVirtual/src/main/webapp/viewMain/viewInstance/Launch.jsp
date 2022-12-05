<%@ page import="com.huicloud.dockerubuntuvirtual.models.Instance" %>
<%@ page import="com.huicloud.dockerubuntuvirtual.services.InstanceService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="SSHKeys" scope="request" type="java.util.List<com.huicloud.dockerubuntuvirtual.models.SSHKey>"/>

<jsp:useBean id="Networks" scope="request" type="java.util.List<com.huicloud.dockerubuntuvirtual.models.Network>"/>


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
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <i class="fa fa-cloud fa-2x" aria-hidden="true"></i>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent"
         style="display: flex; justify-content: space-between">
        <h5>
            Hui & Hiu Cloud
        </h5>
        <h5 class="col-lg-2">
            User name
        </h5>
    </div>
</nav>
<div class="container-fluid mt-3">
    <div class="row">
        <div class="col-sm-3">
            <div class="card">
                <div class="card-header">
                    Featured
                </div>
                <div class="card-body">
                    <div>
                        <a href="${pageContext.request.contextPath}/Main/Instance"
                           class="btn btn-primary btn-lg active bt" role="button" aria-pressed="true"
                           style="background: white; width: 100%; color: black">Instance</a>

                    </div>
                    <div>
                        <a href="${pageContext.request.contextPath}/Main/Snapshot"
                           class="btn btn-primary btn-lg active bt" role="button" aria-pressed="true"
                           style="background: white; width: 100%; color: black">Snapshot</a>

                    </div>
                    <div>
                        <a href="${pageContext.request.contextPath}/Main/SSH" class="btn btn-primary btn-lg active bt"
                           role="button" aria-pressed="true" style="background: white; width: 100%; color: black">SSH
                            Key</a>

                    </div>

                </div>
            </div>
        </div>
        <div class="col-sm-9">
            <div class="card-header mr-2"
                 style="border-style: solid; border-width: 1px; display: flex; justify-content: space-between">
                <div><h2>Launch Instance</h2></div>
                <div style="width: 60%">

                </div>
            </div>
            <div class="card-body mr-2" style="border-style: solid; border-width: 1px">

                <form action="" method="POST">
                    <div class="input-group flex-nowrap">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="addon-wrapping">NameInstance</span>
                        </div>
                        <input name="NameInstance" type="text" class="form-control" placeholder="NameInstance" aria-label="NameInstance"
                               aria-describedby="addon-wrapping">
                    </div>
                    <br>

                    <div style="display: flex; justify-content: space-between">
                        <h5>Application and Os images</h5>
                        <input style="visibility: hidden" id="OS" name="OS" type="text" />
                    </div>
                    <div class="input-group">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-expanded="false" id="ChooseOS">Choose OS
                            </button>
                            <div class="dropdown-menu">
                                <a onclick="UbuntuClick()" class="dropdown-item" >Ubuntu</a>
                                <a onclick="CentClick()" class="dropdown-item">CentOS</a>
                            </div>
                        </div>
                    </div>
                    <div>

                        <div>
                            <br>
                            <h5>Instance type:</h5>
                        </div>
                        <div>
                        </div>
                        <div>
                            <input name="CPUS" type="text" class="form-control" placeholder="CPUS" aria-label="CPUS"
                                   aria-describedby="basic-addon1">
                            <br>
                        </div>
                        <div>
                            <input name="Memory" type="text" class="form-control" placeholder="Memory" aria-label="Memory"
                                   aria-describedby="basic-addon1">
                        </div>

                        <div>
                            <br>
                            <h5>SSH Key</h5>
                            <input id="SSK" type="text" style="visibility: hidden">
                        </div>

                        <div>
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                        data-toggle="dropdown" aria-expanded="false">Choose
                                </button>
                                <div class="dropdown-menu">
                                <c:forEach items="${SSHKeys}" var="c">

                                        <a name="" class="dropdown-item" onclick="SSHKey(${c.id})">${c.nameKey}</a>

                                </c:forEach>
                                </div>
                            </div>
                        </div>

                        <br>

                        <div style="display: flex; justify-content: space-between">
                            <h5>Termination protection</h5>
                            <input id="terminate" style="visibility: hidden"/>
                        </div>
                        <div>
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                        data-toggle="dropdown" aria-expanded="false" id="ChooseTer">Choose
                                </button>
                                <div class="dropdown-menu">
                                    <a name="Termination" onclick="enable()" class="dropdown-item">Enable</a>
                                    <a name="Termination" onclick="disable()" class="dropdown-item">Disable</a>
                                </div>
                            </div>
                        </div>

                        <div>
                            <br>
                            <h5>Network</h5>
                            <input id="Network" type="text" style="visibility: hidden">
                        </div>

                        <div>
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                        data-toggle="dropdown" aria-expanded="false">Choose
                                </button>
                                <div class="dropdown-menu">
                                    <c:forEach items="${Networks}" var="c">

                                        <a name="" class="dropdown-item" onclick="Network(${c.id})">${c.nameNetwork}</a>

                                    </c:forEach>
                                </div>
                            </div>
                        </div>


                        <div>
                            <br>
                            <h6>Userdata</h6>
                        </div>
                        <div>

                            <textarea name="UserData" rows="8" cols="50">
                             </textarea>
                        </div>
                    </div>

                    <div align="right">
                        <a href="${pageContext.request.contextPath}/Main/Instance"
                           class="btn btn-primary btn-lg active bt" role="button" aria-pressed="true"
                           style="background-color: darkgrey; width: 150px; height: 45px; color: black">Exit</a>

                        <button type="submit" style="background-color: darkorange; width: 150px; height: 45px;">Launch</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function UbuntuClick(){
        document.getElementById("OS").value = "1"
        document.getElementById("ChooseOS").innerText = "Ubuntu";
    }
    function CentClick(){
        document.getElementById("OS").value = "2";
        document.getElementById("ChooseOS").innerText = "CentOS";
    }
    function enable(){
        document.getElementById("terminate").value = "1"
        document.getElementById("ChooseTer").innerText = "Enable";
    }
    function disable(){
        document.getElementById("terminate").value = "2"
        document.getElementById("ChooseTer").innerText = "Disable";
    }
    function SSHKey(i){
        document.getElementById("SSK").value = i;
    }

    function Network(i){
        document.getElementById("Network").value = i;
    }
</script>
</html>
