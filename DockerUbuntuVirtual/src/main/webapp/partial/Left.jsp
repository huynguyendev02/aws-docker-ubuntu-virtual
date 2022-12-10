<%@ page import="com.huicloud.dockerubuntuvirtual.services.UserService" %>
<div class="col-sm-2 m-0 p-0" >
    <div class="card" style="width: 100%">
        <div class="card-header" style="background-color: white">
            <h4><i class="fa fa-google" aria-hidden="true">&ensp;<span id="title"> <%=UserService.check()%> </span> </i></h4>

        </div>
        <div class="">
            <div >
                <a href="${pageContext.request.contextPath}/Main/Instance"
                   class="btn btn-outline-primary btn-lg" role="button"  style="width: 100%; text-align: start ; border-style: none; border-left-style: solid; border-left-width: 5px"> &ensp;<i class="fa fa-microchip" aria-hidden="true"></i> &ensp;Instances</a>
            </div>
            <div >
                <a href="${pageContext.request.contextPath}/Main/Snapshot"
                   class="btn btn-outline-secondary btn-lg" role="button"  style="width: 100%; text-align: start; border-style: none; border-left-style: solid; border-left-width: 5px">&ensp;<i class="fa fa-tags" aria-hidden="true"></i>&ensp; Snapshots </a>

            </div>
            <div >
                <a href="${pageContext.request.contextPath}/Main/Image" class="btn btn-outline-success btn-lg"
                   role="button"  style="width: 100%; text-align: start; border-style: none; border-left-style: solid; border-left-width: 5px">&ensp;<i class="fa fa-linux" aria-hidden="true"></i>&ensp; Image</a>

            </div>
            <div >
                <a href="${pageContext.request.contextPath}/Main/SSH" class="btn btn-outline-danger btn-lg"
                   role="button"  style="width: 100%; text-align: start; border-style: none; border-left-style: solid; border-left-width: 5px">&ensp;<i class="fa fa-key" aria-hidden="true"></i>&ensp; SSH Key</a>

            </div>
            <div>
                <a href="${pageContext.request.contextPath}/Main/Network" class="btn btn-outline-info btn-lg"
                   role="button" style=" width: 100%; text-align: left; border-style: none; border-left-style: solid; border-left-width: 5px"> &ensp;<i class="fa fa-rss" aria-hidden="true"></i> &ensp; Network
                </a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/Main/Server" class="btn btn btn-outline-warning btn-lg"
                   role="button" style=" width: 100%; text-align: left; border-style: none; border-left-style: solid; border-left-width: 5px"> &ensp;<i class="fa fa-server" aria-hidden="true"></i> &ensp; Server
                </a>
            </div>
            <div id="user">
                <a href="${pageContext.request.contextPath}/Main/Server" class="btn btn-outline-dark btn-lg"
                   role="button" style=" width: 100%; text-align: left; border-style: none; border-left-style: solid; border-left-width: 5px"> &ensp;<i class="fa fa-user" aria-hidden="true"></i> &ensp; User
                </a>
            </div>

        </div>
    </div>
</div>
<script>
    window.onload = function (){
        if (document.getElementById("title").innerText==0){
            document.getElementById("title").innerText = 'Manager'
            document.getElementById("user").style.display = 'block'
        }
        else{
            document.getElementById("title").innerText = 'Service'
            document.getElementById("user").style.display = 'none'
        }
    }
</script>