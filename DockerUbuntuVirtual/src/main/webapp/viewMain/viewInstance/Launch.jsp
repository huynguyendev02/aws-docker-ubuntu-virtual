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
<jsp:include page="./../../partial/Navication.jsp"></jsp:include>

<div class="container-fluid mt-3">
    <div class="row">
        <jsp:include page="../../partial/Left.jsp"></jsp:include>
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
                        <input name="NameInstance" type="text" class="form-control" placeholder="NameInstance"
                               aria-label="NameInstance"
                               aria-describedby="addon-wrapping">
                    </div>
                    <br>

                    <div style="display: flex; justify-content: space-between">
                        <h5>Application and Os images</h5>
                        <input style="visibility: hidden" id="OS" name="OS" type="text"/>
                    </div>
                    <div class="input-group">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-expanded="false" id="ChooseOS">Choose OS
                            </button>
                            <div class="dropdown-menu">
                                <a onclick="UbuntuClick()" class="dropdown-item">Ubuntu</a>
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
                            <input name="Memory" type="text" class="form-control" placeholder="Memory"
                                   aria-label="Memory"
                                   aria-describedby="basic-addon1">
                        </div>

                        <div>
                            <input name="SSHKey" id="SSHKey" type="text" style="visibility: hidden">
                            <h5>SSH Key</h5>
                        </div>

                        <div style="display: flex">
                            <div class="input-group-append">
                                <button id="btSSHKey" class="btn btn-outline-secondary dropdown-toggle" type="button"
                                        data-toggle="dropdown" aria-expanded="false">Choose
                                </button>
                                <div class="dropdown-menu">
                                    <a name="" class="dropdown-item" onclick="SSKeyList()">SSH Key</a>
                                    <a name="" class="dropdown-item" onclick="Password()">Password</a>
                                </div>
                            </div>
                            <div id="textPassword" style="display: none; padding-left: 30px" align="center">
                                <span style="padding-top: 9px"><h6>Your default password is: defaultpassword</h6></span>
                            </div>

                            <div id="SSHKeyList" class="input-group-append" style="padding-left: 30px; display: none">
                                <button id="btSSHKeyList" class="btn btn-outline-secondary dropdown-toggle"
                                        type="button"
                                        data-toggle="dropdown" aria-expanded="false">Choose SSHKey
                                </button>
                                <div class="dropdown-menu">
                                    <c:forEach items="${SSHKeys}" var="c">
                                        <a name="" class="dropdown-item"
                                           onclick="ChooseSSHKey(${c.id},${c.nameKey})">${c.nameKey}</a>
                                    </c:forEach>
                                </div>
                            </div>

                        </div>
                    </div>
                    <br>
                    <div style="display: flex; justify-content: space-between">
                        <h5>Termination protection</h5>
                        <input name="terminate" id="terminate" style="visibility: hidden"/>
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
                        <input name="Network" id="Network" type="text" style="visibility: hidden">
                        <h5>Network</h5>
                    </div>

                    <div>
                        <div class="input-group-append">
                            <button id="btNetwork" class="btn btn-outline-secondary dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-expanded="false">Choose
                            </button>
                            <div class="dropdown-menu">
                                <c:forEach items="${Networks}" var="c">

                                    <a name="" class="dropdown-item" onclick="Network('${c.id}','${c.nameNetwork}')">${c.nameNetwork}</a>

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

                    <div align="right">

                        <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/Main/Instance" role="button">Cancel</a>
                        <button type="submit" class="btn btn-primary" style="width: 150px;">
                            Launch Instance
                        </button>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function UbuntuClick() {
        document.getElementById("OS").value = "1"
        document.getElementById("ChooseOS").innerText = "Ubuntu";
    }

    function CentClick() {
        document.getElementById("OS").value = "2";
        document.getElementById("ChooseOS").innerText = "CentOS";
    }

    function enable() {
        document.getElementById("terminate").value = "1"
        document.getElementById("ChooseTer").innerText = "Enable";
    }

    function disable() {
        document.getElementById("terminate").value = "2"
        document.getElementById("ChooseTer").innerText = "Disable";
    }

    function Network(i, name) {
        document.getElementById("btNetwork").innerText = name;
        document.getElementById("Network").value = i;
    }

    function SSKeyList() {
        document.getElementById("btSSHKey").innerText = 'SSHKey';
        document.getElementById("textPassword").style.display = 'none';
        document.getElementById("SSHKeyList").style.display = 'flex';
    }

    function Password() {
        document.getElementById("btSSHKey").innerText = 'Password';
        document.getElementById("SSHKeyList").style.display = 'none';
        document.getElementById("textPassword").style.display = 'flex';
        document.getElementById("SSHKey").value = 0;
    }

    function ChooseSSHKey(id, name) {
        document.getElementById("btSSHKeyList").innerText = name;
        document.getElementById("SSHKey").value = id;
    }
</script>
</html>