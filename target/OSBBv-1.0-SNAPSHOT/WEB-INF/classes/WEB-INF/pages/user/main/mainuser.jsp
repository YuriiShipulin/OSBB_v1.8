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

        .table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th {
            background-color: #550055;
            color: #eeeeee;
        }

        .table-striped > tbody > tr:nth-child(odd) > td,
        .table-striped > tbody > tr:nth-child(odd) > th {
            background-color: #3157e5;
        }

        #section1 {
            padding-top: 50px;
            height: 100%;
            color: #fff;
            background-color: #3157e5;
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
            background-color: #6700fb;
        }

        #section41 {
            padding-top: 50px;
            height: 500px;
            color: #fff;
            background-color: #00bcd4;
        }

        #section42 {
            padding-top: 50px;
            height: 100%;
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
                    <li><a href="#section1">Выставленные Счета</a></li>
                    <li><a href="#section2">Внести показания счетчиков</a></li>
                    <li><a href="#section3">Новости</a></li>

                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Соседи<span
                            class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#section41">Пригласить</a></li>
                            <li><a href="#section42">Посмотреть</a></li>
                        </ul>
                    </li>
                    <li><a href="/user/edit">Изменить персональные данные</a></li>
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
        <c:choose>
            <c:when test="${not empty payments}">

                <table class="table table-hover">
                    <thead>
                    <tr>
                        <td><b>Сервис</b></td>
                        <td><b>Прошлые показатели</b></td>
                        <td><b>Текущие показатели</b></td>
                        <td><b>Разница</b></td>
                        <td><b>Тариф</b></td>
                        <td><b>К Оплате</b></td>
                    </tr>
                    </thead>
                    <c:forEach items="${payments}" var="payment">
                        <tr>
                            <td>${payment.serviceName}</td>
                            <td>${payment.prevValue}</td>
                            <td>${payment.currValue}</td>
                            <td>${payment.diff}</td>
                            <td>${payment.rate}</td>
                            <td>${payment.mustPay}</td>
                        </tr>
                    </c:forEach>
                </table>
                <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/user/get/payment"
                      method="post">
                    <p></p>
                    <input type="submit" class="btn btn-success" value="Сформировать платежку">
                </form>
            </c:when>
            <c:otherwise>
                <h2>На данный момент нет платежей</h2>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div id="section2" class="container-fluid">
    <div class="container">
        <h1>Внести показания счетчиков</h1>
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
                        <input type="button" class="btn btn-primary launch-modal"
                               onclick="launchModal('${serviceUser.lastValue}','${serviceUser.serviceId}','${serviceUser.rate}')"
                               value="Внести показания">
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div id="section3" class="container-fluid">
    <div class="container">
        <h1>Новости</h1>

        <p class="lead">"${building.message}"</p>
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
            <h1>Мои соседи:</h1>
            <c:choose>
                <c:when test="${not empty users}">
                    <table class="table table-border table-hover">
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
                </c:when>
                <c:otherwise>
                    <h2>У вас еще нет соседей. <br> Вы можете пригласить соседей в меню <a href="#section41"
                                                                                           style="color: darkblue">Пригласить
                        соседей</a></h2>
                </c:otherwise>
            </c:choose>
            <br>
            <br>
            <br><br><br>
        </div>
    </div>
    <div id="addPerfCounters" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Внесите показатели:</h4>
                </div>
                <div class="modal-body">

                    <form id="current-value-add" role="form" enctype="multipart/form-data" class="form-horizontal"
                          action="/" method="post">
                        <table class="table table-border">
                            <thead>
                            <tr>
                                <td><b>Предыдущие</b></td>
                                <td><b>Текущие</b></td>
                                <td><b>Разница</b></td>
                                <td><b>Тариф</b></td>
                                <td><b>К оплате</b></td>
                            </tr>
                            </thead>
                            <tr>
                                <td><input id="previous-value" type="number" class="form-control" name="previousvalue"
                                           placeholder="Предыдущие"></td>
                                <td><input id="current-value" type="number" onkeyup="calc()" class="form-control"
                                           name="currentvalue" placeholder="Текущие"></td>
                                <td><input id="diff" type="number" class="form-control" name="diff"
                                           placeholder="Разница" readonly></td>
                                <td><input id="rate" type="number" class="form-control" name="rate" placeholder="Тариф"
                                           readonly></td>
                                <td><input id="invoice" type="number" class="form-control" name="invoice"
                                           placeholder="К оплате" readonly></td>
                            </tr>
                        </table>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function launchModal(lastValue, id, rate) {
            $('#addPerfCounters').find('#previous-value').val(lastValue);
            $('#addPerfCounters').find('#current-value-add').attr("action", "/user/add/currentvalue/" + id)
            $('#addPerfCounters').find('#rate').val(rate);
            $('#addPerfCounters').modal({
                backdrop: 'static',
                keyboard: true
            });
        }
        function calc() {
            var prValue = $('#addPerfCounters').find('#previous-value').val();
            var curValue = $('#addPerfCounters').find('#current-value').val();
            var diff = curValue - prValue;
            var rate = $('#addPerfCounters').find('#rate').val();
            var inv = diff * rate;
            $('#addPerfCounters').find('#diff').val(diff);
            var prValue = $('#addPerfCounters').find('#invoice').val(inv);
        }

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
</div>
</body>
</html>
