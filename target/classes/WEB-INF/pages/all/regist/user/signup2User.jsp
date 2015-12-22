<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>OSBB Главная страница. Ведение учета вашего ОСББ</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <style>
        body {
            position: relative;
        }

        #section1 {
            padding-top: 50px;
            height: 100%;
            color: #fff;
            background: url("../../../resources/images/signin.jpg");
            background-size: cover
        }
    </style>

</head>
<body data-spy="scroll" data-target=".navbar" data-offset="50">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">ОСББ</a>
        </div>
    </div>
</nav>
<div id="section1" class="container-fluid">
    <div class="container">
        <div class="container">
            <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/all/reg/add/userInfo"
                  method="post">
                <div class="form-group">
                    <div class="col-md-4">
                        <h3>Укажите персональные данные</h3>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="name" placeholder="Имя" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="lastName" placeholder="Фамилия" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="secondName" placeholder="Отчество" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="phone" placeholder="Номер телефона" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <select class="selectpicker form-control" data-live-search="true" name="flatNum">
                            <c:forEach items="${listFlat}" var="flat">
                                <option value="${flat.id}">${flat.flatNumber}</option>
                            </c:forEach>
                            <p class="help-block">укажите номер квартиры</p>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <input type="submit" class="btn btn-primary" value="Готово!">
                    </div>
                </div>
                <div class="form-group"></div>
            </form>
        </div>
    </div>
</div>
<script>
    $('.selectpicker').selectpicker();
    $('#basic2').selectpicker({
        liveSearch: true,
        maxOptions: 1
    });
</script>
<div class="navbar navbar-inverse navbar-fixed-bottom" style="opacity: 0.8; height: 5% ">
    <div class="container-fluid">
        <div class="navbar-text  pull-left">
            <h5>Project by Maxim & Yurii</h5>
        </div>
        <div class="navbar-text pull-right">
            <h5>Наши контакты: mbratiuk@gmail.com , yurii.shipulin.31@gmail.com</h5>
        </div>
    </div>
</div>
</body>
</html>
