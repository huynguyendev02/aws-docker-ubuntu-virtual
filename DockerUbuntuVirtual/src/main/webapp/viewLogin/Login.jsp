<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <style>
    @import url('https://fonts.googleapis.com/css?family=Abel|Abril+Fatface|Alegreya|Arima+Madurai|Dancing+Script|Dosis|Merriweather|Oleo+Script|Overlock|PT+Serif|Pacifico|Playball|Playfair+Display|Share|Unica+One|Vibur');

    * {
      padding: 0;
      margin: 0;
      box-sizing: border-box;
    }

    body {
      background-image: linear-gradient(-225deg, #E3FDF5 0%, #FFE6FA 100%);
      background-image: linear-gradient(to top, #a8edea 0%, #fed6e3 100%);
      background-attachment: fixed;
      background-repeat: no-repeat;

      font-family: 'Vibur', cursive;
      font-family: 'Abel', sans-serif;
      opacity: .95;
    }

    form {
      width: 450px;
      min-height: 500px;
      height: auto;
      border-radius: 5px;
      margin: 2% auto;
      box-shadow: 0 9px 50px hsla(20, 67%, 75%, 0.31);
      padding: 2%;
      background-image: linear-gradient(-225deg, #E3FDF5 50%, #FFE6FA 50%);
    }

    form .con {
      display: -webkit-flex;
      display: flex;

      -webkit-justify-content: space-around;
      justify-content: space-around;

      -webkit-flex-wrap: wrap;
      flex-wrap: wrap;

      margin: 0 auto;
    }

    header {
      margin: 2% auto 10% auto;
      text-align: center;
    }

    header h2 {
      font-size: 250%;
      font-family: 'Playfair Display', serif;
      color: #3e403f;
    }

    header p {
      letter-spacing: 0.05em;
    }

    .input-item {
      background: #fff;
      color: #333;
      padding: 14.5px 0px 15px 9px;
      border-radius: 5px 0px 0px 5px;
    }

    #eye {
      background: #fff;
      color: #333;

      margin: 5.9px 0 0 0;
      margin-left: -20px;
      padding: 15px 9px 19px 0px;
      border-radius: 0px 5px 5px 0px;

      float: right;
      position: relative;
      right: 1%;
      top: -.2%;
      z-index: 5;

      cursor: pointer;
    }

    input[class="form-input"] {
      width: 240px;
      height: 50px;

      margin-top: 2%;
      padding: 15px;

      font-size: 16px;
      font-family: 'Abel', sans-serif;
      color: #5E6472;

      outline: none;
      border: none;

      border-radius: 0px 5px 5px 0px;
      transition: 0.2s linear;

    }

    input[id="txt-input"] {
      width: 280px;
    }

    input[id="pwd"] {
      width: 280px;
    }

    /* focus  */
    input:focus {
      transform: translateX(-2px);
      border-radius: 5px;
    }

    button {
      display: inline-block;
      color: #252537;

      width: 280px;
      height: 50px;

      padding: 0 20px;
      background: #fff;
      border-radius: 5px;

      outline: none;
      border: none;

      cursor: pointer;
      text-align: center;
      transition: all 0.2s linear;

      margin: 7% auto;
      letter-spacing: 0.05em;
    }

    .submits {
      width: 150px;
      height: 50px;
      display: inline-block;
      float: left;
      margin-left: 2%;
    }

    .frgt-pass {
      background: transparent;
    }

    .bt {
      width: 100px;
      background: #B8F2E6;
    }


    button:hover {
      transform: translatey(3px);
      box-shadow: none;
    }

    button:hover {
      animation: ani9 0.4s ease-in-out infinite alternate;
    }

    @keyframes ani9 {
      0% {
        transform: translateY(3px);
      }
      100% {
        transform: translateY(5px);
      }
    }

  </style>
</head>
<body>
<div class="overlay">
  <form action="" method="post" >
    <div class="con">
      <header class="head-form">
        <h2>Log In</h2>
        <p>Login here using your username and password</p>
      </header>
      <br>
      <div class="field-set">
        </span>
        <input name="username" class="form-input" id="txt-input" type="text" placeholder="@UserName" required>
        <br>
        <input class="form-input" id="pwd" name="password" type="password" placeholder="Password" required>
        <br>
      </div>
      <div class="other" style="padding-top: 50px">
        <button type="submit" style="background: #B8F2E6; width: 100px; color: black">Login</button>
        <a href="${pageContext.request.contextPath}/Home/Register" class="btn btn-primary btn-lg active bt" role="button" aria-pressed="true" style="background: #B8F2E6; width: 100px; color: black">Register</a>
      </div>
    </div>
  </form>
</div>
</body>
</html>
