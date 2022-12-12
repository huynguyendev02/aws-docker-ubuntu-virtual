<%@ page import="com.huicloud.dockerubuntuvirtual.services.InstanceService" %>
<%@ page import="com.huicloud.dockerubuntuvirtual.services.NetworkService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="instances" scope="request" type="java.util.List<com.huicloud.dockerubuntuvirtual.models.Instance>"/>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Instances</title>
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
            <form action="" method="post" style="width: 100%">
                <div class="card-header"
                     style=" display: flex; justify-content: space-between; border-style: none">
                    <div style="display: flex; justify-content: space-between">
                        <h4>Instance</h4>
                    </div>

                    <div style="width: 60%">
                        <input name="State" id="ipState" type="text" style="display: none">
                        <input name="idTerminate" id="idTerminate" type="text" style="display: none">
                        <input name="IdInstance" id="IdInstance" type="text" style="display: none">

                        <input name="IdAction" id="IdAction" type="text" style="display: none">
                        <div style="width: 100%">
                            <div class="dropdown" style="width: 100%" align="right">

                                <button id="btCreateImage" type="button" class="btn btn-outline-primary" style="border-style: none" disabled> <b><i class="fa fa-windows" aria-hidden="true"></i>  Make Image</b> </button>
                                <button id="btCreateSnap" type="button" class="btn btn-outline-primary" style="border-style: none" disabled> <b><i class="fa fa-tag" aria-hidden="true"></i> Make Snapshot</b> </button>
                                </button>
                                <button id="action" type="button" data-toggle="dropdown"
                                        class="btn btn-outline-dark dropdown-toggle" disabled>Actions
                                </button>
                                &ensp;
                                <div class="dropdown-menu">
                                    <button id="start" class="dropdown-item">Start</button>
                                    <button id="stop" class="dropdown-item">Stop</button>
                                    <button id="terminate" class="dropdown-item">Terminate</button>
                                    <button id="turnOffTerminate" class="dropdown-item">Turn Off protection</button>
                                </div>

                                <a id="Launch" class="btn btn-primary"
                                   href="${pageContext.request.contextPath}/Main/Instance/Launch"
                                   role="button" style="width: 150px">Launch Instance</a>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="card-body">
                    <table id="tableLaunchInstance" style="width: 100%">
                        <tr style="background-color: lightgray" align="center">
                            <td></td>
                            <td>ID</td>
                            <td>Name</td>
                            <td>State</td>
                            <td>Network</td>
                            <td>Server</td>
                            <td>Port</td>
                            <td>CPUS</td>
                            <td>Memory</td>
                        </tr>

                        <c:forEach items="${instances}" var="c">
                            <tr align="center">
                                <td><input name="hehe" type="radio"
                                           onclick="choose(${c.id},'${c.nameInstance}','${c.serverIp()}',${c.terminate})" value="Yes"/></td>
                                <td>${c.id}</td>
                                <td>${c.nameInstance.split("0")[1]}</td>
                                <td id="state">${c.state}</td>
                                <td>${c.networkId}</td>
                                <td>${c.serverIp()}</td>
                                <td>${c.getport()}</td>
                                <td>${c.cpus}</td>
                                <td>${c.memory}</td>
                            </tr>
                        </c:forEach>
                    </table>

                    <div id="tableCreateSnap" style="display: none">
                        <h6 id="InstanceName"></h6>
                        <br>
                        <div class="input-group flex-nowrap">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="addon-wrapping">SnapshotName</span>
                            </div>
                            <input name="SnapshotName" type="text" class="form-control" placeholder="SnapshotName"
                                   aria-label="SnapshotName"
                                   aria-describedby="addon-wrapping">
                        </div>
                        <br>
                        <div align="right">
                            <button type="submit" class="btn btn-success">Create</button>
                        </div>
                    </div>

                    <div id="tableCreateImage" style="display: none">
                        <h6 id="InstanceServer"></h6>
                        <br>
                        <div class="input-group flex-nowrap">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="tbCreateImage">ImageName</span>
                            </div>
                            <input name="ImageName" type="text" class="form-control" placeholder="ImageName"
                                   aria-label="ImageName"
                                   aria-describedby="addon-wrapping">
                        </div>
                        <br>
                        <div align="right">
                            <button type="submit" class="btn btn-success">Create</button>
                        </div>

                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    document.getElementById("start").onclick =
        function clickStart() {
            document.getElementById("IdAction").value = 1;
            document.getElementById("ipState").value = 0;
            document.getElementById("start").setAttribute("type", "submit")
        }

    document.getElementById("stop").onclick =
        function clickStop() {
            document.getElementById("IdAction").value = 2;
            document.getElementById("ipState").value = 0;
            document.getElementById("stop").setAttribute("type", "submit")
        }
    document.getElementById("terminate").addEventListener('click', function clickTerminate(event) {
        document.getElementById("IdAction").value = 3;
        document.getElementById("ipState").value = 0;
        if (document.getElementById("idTerminate").value == 1) {
            event.preventDefault()
            alert("Can't terminate this instance")
            return
        }
        document.getElementById("terminate").setAttribute("type", "submit")
    })

    // document.getElementById("terminate").onclick =
    //     function clickTerminate(event) {
    //
    //     }

    document.getElementById("turnOffTerminate").onclick =
        function clickTerminate() {
            document.getElementById("IdAction").value = 4;
            document.getElementById("ipState").value = 0;
            document.getElementById("turnOffTerminate").setAttribute("type", "submit")
        }

    function choose(id, instanceName, serverName, terminate) {
        document.getElementById("IdInstance").value = id

        document.getElementById("idTerminate").value = terminate

        document.getElementById("btCreateImage").disabled = false
        document.getElementById("btCreateSnap").disabled = false
        document.getElementById("action").disabled = false
        document.getElementById("InstanceName").innerText = "InstanceName: " + instanceName
        document.getElementById("InstanceServer").innerText = "Instance Server: " + serverName

        // document.getElementById("start").classList.remove("disabled");
        // document.getElementById("stop").classList.remove("disabled");

        // if (document.getElementById("state").innerText==='Running') {
        //     document.getElementById("start").classList.add("disabled");
        //     // alert(document.getElementById("state").innerText)
        // }
        // if (document.getElementById("state").innerText==='Stopped') {
        //     document.getElementById("stop").classList.add("disabled");
        //     // alert(document.getElementById("state").innerText)
        // }
    }

    document.getElementById("btCreateSnap").onclick = function createSnap() {
        document.getElementById("ipState").value = 1;
        document.getElementById("tableLaunchInstance").style.display = 'none'
        document.getElementById("btCreateSnap").style.display = 'none'
        document.getElementById("btCreateImage").style.display = 'none'
        document.getElementById("action").style.display = 'none'
        document.getElementById("Launch").style.display = 'none'
        document.getElementById("tableCreateImage").style.display = 'none'
        document.getElementById("tableCreateSnap").style.display = 'block'
    }

    document.getElementById("btCreateImage").onclick = function createImage() {
        document.getElementById("ipState").value = 2;
        document.getElementById("tableLaunchInstance").style.display = 'none'
        document.getElementById("btCreateSnap").style.display = 'none'
        document.getElementById("btCreateImage").style.display = 'none'
        document.getElementById("action").style.display = 'none'
        document.getElementById("Launch").style.display = 'none'
        document.getElementById("tableCreateSnap").style.display = 'none'
        document.getElementById("tableCreateImage").style.display = 'block'
    }
</script>
</body>
</html>
