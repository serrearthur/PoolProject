<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>When Is My Code Review?</title>

    <!-- Bootstrap CSS -->
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="./resources/css/style.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top container-fluid" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value="/home"/>">When Is My Code Review?</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle navlink" data-toggle="dropdown" href="#">
                        <i class="fa fa-gear fa-fw"></i> Gérer les code reviews <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="<c:url value="/add_promotion"/>"><i class="fa fa-users fa-fw"></i> Ajouter une promotion</a>
                        </li>
                        <li><a href="<c:url value="/add_member"/>"><i class="fa fa-user fa-fw"></i> Ajouter un membre</a>
                        </li>
                        <li><a href="<c:url value="/add_event"/>"><i class="fa fa-calendar fa-fw"></i> Créer un rendez-vous</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>

        <div id="page-wrapper" class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Ajouter une code review</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-12">
                                    <form action="" method="post" class="">
                                        <div class="form-group">
                                            <label for="name">Nom</label>
                                            <input type="text" class="input-lg form-control" id="name" placeholder="Nom" name="eventName" value="<c:out value="${param.eventName}"/>">
                                        </div>
                                        <div class="form-group">
                                            <label for="date">Date</label>
                                            <input type="datetime" class="input-lg form-control" id="date" placeholder="Date de l'évènement (dd/mm/yyyy)" name="eventDate" value="<c:out value="${param.eventDate}"/>">
                                        </div>
                                        <div class="form-group">
                                            <label for="date">Description</label>
                                            <input type="datetime" class="input-lg form-control" id="description" placeholder="Description de l'évènement" name="eventDescription" value="<c:out value="${param.eventDescription}"/>">
                                        </div>
                                        <div class="form-group">
                                            <label for="promotion">Promotion</label>
                                            <select class="input-lg form-control" id="promotion" name="eventPromotion">
                                                <c:forEach items="${controller.getClasses()}" var="p">
                                                	<option>${p.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="text-right">
                                            <button type="submit" class="btn btn-lg btn-primary">Enregistrer</button>
                                        </div>
                                        <p style="${empty errors ? "color:green" : "color:red"}">
                                        	<c:out value="${result }"/><br/>
                                        	<c:forEach items="${errors }" var="e">
                                        		<c:out value="${e }"/><br/>
                                        	</c:forEach>
                                        </p>
                                    </form>
                                </div>
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    <footer class="footer">
        <div class="container">
            <div class="row text-center">
                <img src="./resources/img/ebusiness.png" class="logo" alt=""> &bullet; 2017
            </div>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="./resources/js/jquery-3.1.1.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="./resources/js/bootstrap.min.js"></script>

</body>

</html>