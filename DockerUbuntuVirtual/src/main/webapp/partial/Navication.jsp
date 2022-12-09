<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <a class="navbar-brand" href="#">
    <i class="fa fa-cloud fa-2x" aria-hidden="true"></i>
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent" style="display: flex; justify-content: space-between">
    <h5 style="color: white">
      Hui & Hiu Cloud
    </h5>
    <h5 class="col-lg-2">
      <div class="input-group-append" style="padding-top: 10px">
        <button class="btn btn-outline-light dropdown-toggle" type="button"data-toggle="dropdown" aria-expanded="false">
          <span id="Username">
            Account Settings
          </span>
        </button>
        <div class="dropdown-menu">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/Home/Login">Log out</a>
        </div>
      </div>
    </h5>
  </div>
</nav>

