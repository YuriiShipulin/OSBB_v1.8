<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
            height: 500px;
            color: #fff;
            background-color: #1E88E5;
        }

        #section2 {
            padding-top: 50px;
            height: 500px;
            color: #fff;
            background-color: #673ab7;
        }

        #section3 {
            padding-top: 50px;
            height: 500px;
            color: #fff;
            background-color: #5424ff;
        }

        #section41 {
            padding-top: 50px;
            height: 500px;
            color: #fff;
            background-color: #00bcd4;
        }

        #section42 {
            padding-top: 50px;
            height: 500px;
            color: #fff;
            background-color: #009688;
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
        <div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li><a href="#section1">Сформировать платежки</a></li>
                    <li><a href="#section2">Сообщение жильцам</a></li>
                    <li><a href="#section3">Показания счетчиков</a></li>

                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Жильцы<span
                            class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#section41">Пригласить</a></li>
                            <li><a href="#section42">Посмотреть</a></li>
                        </ul>
                    </li>
                    <li><a href="/admin/edit"> Изменить тарифы</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a aria-label="Left Align" href="<c:url value="/j_spring_security_logout"/>"><span
                                class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>
<div id="section1" class="container-fluid">
    <div class="container">
        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/add/payments"
              method="post">
            <h1>Сформировать платежи</h1>
            <table class="table table-border" style="width: auto">
                <thead>
                <tr>
                    <td><b>Сервис</b></td>
                    <td><b></b></td>
                    <td><b></b></td>
                </tr>
                </thead>
                <c:forEach items="${servicesList}" var="serviceUser">
                    <tr>
                        <td>${serviceUser.name}</td>
                        <td>
                            <input type="submit" formaction="/admin/add/payments/${serviceUser.serviceId}"
                                   formmethod="post" class="btn btn-primary launch-modal" value="Сформировать платежки">
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
    <div id="section2" class="container-fluid">
        <div class="container">
            <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/add/message"
                  method="post">
                <h1>Отправить сообщение жильцам</h1>
                <input type="text" class="form-control" name="message" value="${building.message}">

                <p></p>
                <input type="submit" class="btn btn-primary" value="Отправить!">
            </form>
        </div>
    </div>
    <div id="section3" class="container-fluid">
        <div class="container">
            <h1>Показания счетчиков жильцов</h1>
            <table class="table table-border">
                <thead>
                <tr>
                    <td><b>Номер квартиры</b></td>
                    <td><b>Имя</b></td>
                    <td><b>Фамилия</b></td>
                    <td><b>Сервис</b></td>
                    <td><b>Ткущие показания</b></td>
                </tr>
                </thead>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.flatNum}</td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>

                        <c:forEach items="${user.currentCounter}" var="counters">
                            <td>${counters.key}</td>
                            <td>${counters.value}</td>
                        </c:forEach>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
    <div id="section41" class="container-fluid">
        <div class="container">
            <h1>Пригласите своих соседей!</h1>
            <p>Просто укажите email адрес:</p>
            <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/inviteusers" method="post">
                <input type="text" class="form-control" name="email" placeholder="@Email">

                <p></p>
                <input type="submit" class="btn btn-primary" value="Пригласить!">
            </form>
        </div>
    </div>

    <div id="section42" class="container-fluid">
        <div class="container">
            <h1>Мои Жильцы</h1>
            <table class="table table-border">
                <thead>
                <tr>
                    <td><b>Номер квартиры</b></td>
                    <td><b>Имя</b></td>
                    <td><b>Фамилия</b></td>
                    <td><b>Тел.</b></td>
                    <td><b>Емейл</b></td>
                    <td><b>Площадь</b></td>
                    <td><b>Проживает</b></td>
                </tr>
                </thead>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.flatNum}</td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.phone}</td>
                        <td>${user.email}</td>
                        <td>${user.area}</td>
                        <td>${user.peopleCNT}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

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
</div>
</body>
</html>

