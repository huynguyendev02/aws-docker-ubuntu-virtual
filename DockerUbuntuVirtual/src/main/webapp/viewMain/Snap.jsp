<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

<div class="container-fluid mt-3">
  <div class="row">
    <div class="col-sm-3">
      <div class="card">
        <div class="card-header">
          Featured
        </div>
        <div class="card-body">
          <div>
            <a href="${pageContext.request.contextPath}/Main/Instance" class="btn btn-primary btn-lg active bt" role="button" aria-pressed="true" style="background: white; width: 100%; color: black">Instance</a>

          </div>
          <div>
            <a href="${pageContext.request.contextPath}/Main/Snapshot" class="btn btn-primary btn-lg active bt" role="button" aria-pressed="true" style="background: white; width: 100%; color: black">Snapshot</a>

          </div>
          <div>
            <a href="${pageContext.request.contextPath}/Main/SSH" class="btn btn-primary btn-lg active bt" role="button" aria-pressed="true" style="background: white; width: 100%; color: black">SSH Key</a>

          </div>
          <div>
            <a href="${pageContext.request.contextPath}/Main/Network" class="btn btn-primary btn-lg active bt"
               role="button" aria-pressed="true" style="background: white; width: 100%; color: black">Network
            </a>
          </div>

        </div>
      </div>
    </div>
    <div class="col-sm-9">
      <div class="card-header mr-2" style="border-style: solid; border-width: 1px">
        <span id="home"> Snapshot </span>
      </div>
      <div class="card-body mr-2" style="border-style: solid; border-width: 1px">
        Coming Soon !
      </div>
    </div>
  </div>
</div>
</body>
</html>

