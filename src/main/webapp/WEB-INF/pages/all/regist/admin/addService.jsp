<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <div class="row">
            <div class="control-group" id="fields">
                <div class="controls">
                    <form role="form" enctype="multipart/form-data" class="form-horizontal"
                          action="/all/reg/add/admin/service" method="post">
                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-4">
                                    <h3>Выберите сервисы, которые буду учитываться в вашем доме.</h3>
                                </div>
                            </div>
                        </div>
                        <c:forEach items="${services}" var="service">
                            <div class="entry input-group">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <input class="form-control" name="${service.id}" type="number"
                                                   placeholder="${service.name}"/>
                                        </div>
                                        <div class="col-md-4">
                     <span class="input-group-btn">
                     <button class="btn btn-success btn-remove btn-danger" type="button">
                         <span class="glyphicon glyphicon-minus"></span>
                     </button>
                     </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-4">
                                    <input type="submit" class="btn btn-success" value="Готово!">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $(document).on('click', '.btn-remove', function (e) {
            $(this).parents('.entry:first').remove();

            e.preventDefault();
            return false;
        });
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
