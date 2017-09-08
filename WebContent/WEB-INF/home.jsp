<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <a class="navbar-brand" href="<c:url value="/home" />">When Is My Code Review?</a>
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
                    <h1 class="page-header">Panneau d'administration</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-users fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"> <c:out value="${controller.classes.size()}"/></div>
                                    <div class="huge-label">Promotions</div>
                                </div>
                            </div>
                        </div>
                        <a href="<c:url value="/add_promotion" />">
                            <div class="panel-footer">
                                <span class="pull-left">Ajouter une promotion</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-user fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><c:out value="${controller.members.size()}"/></div>
                                    <div class="huge-label">Membres inscrits</div>
                                </div>
                            </div>
                        </div>
                        <a href="<c:url value="/add_member" />">
                            <div class="panel-footer">
                                <span class="pull-left">Ajouter un membre</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-calendar fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><c:out value="${controller.codeReviews.size()}"/></div>
                                    <div class="huge-label">Code reviews programmées</div>
                                </div>
                            </div>
                        </div>
                        <a href="<c:url value="/add_event" />">
                            <div class="panel-footer">
                                <span class="pull-left">Ajouter une code review</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-8">
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-user fa-fw"></i> Gestion des membres
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="table-responsive">
                                        <table class="table table-hover table-striped">
                                            <thead>
                                                <tr>                                                
                                                    <th>Nom</th>
                                                    <th>Email</th>
                                                    <th>Promotion</th>
                                                    <th class="text-right">Action</th>                                                   
                                                </tr>
                                            </thead>
                                            <tbody>
												<c:forEach items="${currentMembers}" var="m">
													<tr>
													<form action="" method="post" class="">
													    <td><input type="text" class="input-lg form-control" id="name" name="memberName" placeholder="Nom" value="<c:out value="${m.name}"/>"></td>
													    <td><input type="email" class="input-lg form-control" id="email" name="memberEmail" placeholder="Adresse Email" value="<c:out value="${m.email}"/>">
													    <input type="hidden" class="input-lg form-control" id="id" name="memberId" value="<c:out value="${m.id}"/>"></td>
													    <td><select class="input-lg form-control" id="promotion" name="memberPromotion">
			                                                <c:forEach items="${controller.getClasses()}" var="p">
			                                                	<option <c:if test="${p.getId()==m.getCrclassId()}"> selected </c:if> >
			                                                	${p.getName()}
			                                                	</option>
			                                                </c:forEach>
			                                            </select></td>
													    <td  class="text-right">
													        <button type="submit" class="btn btn-sm btn-warning" name="modifier"> <i class="fa fa-pencil"></i> Modifier</button>
 													        <button type="submit" class="btn btn-sm btn-danger" name="supprimer"><i class="fa fa-trash"></i> Supprimer</button>
													    </td>
													</form>
													</tr>
												</c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="text-center">
                                            <ul class="pagination">
                                           		<c:forEach var="i" begin="1" end="${memberTotalPages}">
                                           			<li><a href="
                                           				<c:url value="/home">
                                           					<c:param name="memberPage" value="${i}"/>	
                                           				</c:url>">
                                           				${i}
                                           			</a></li>
                                           		</c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- /.table-responsive -->
                                </div>
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-8 -->
                <div class="col-lg-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-calendar fa-fw"></i> Codes reviews programmées
                        </div>
                        <div class="panel-body">
                            <table class="table table-striped">
 								<c:forEach items="${controller.codeReviews}" var="cr">
	 								<tr>
	                                    <td><c:out value="${cr.name}" /></td>
	                                    <td>Promotion <c:out value="${controller.getClassName(cr.getCrclassId())}" /></td>
	                                    <td class="text-right"><span class="text-muted small"><c:out value="${cr.dateTime}" /></span></td>
	                                </tr>
	                            </c:forEach>
                            </table>
                            <a href="<c:url value="/add_event"/>" class="btn btn-default btn-block">Programmer une code review</a>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-users fa-fw"></i> Gestion des promotions
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="list-group">
                            	<c:forEach items="${controller.classes}" var="cl">
	                            	<a href="#" class="list-group-item">
	                                    <i class="fa fa-users fa-fw"></i> Promotion <c:out value="${cl.name}" />
	                                    <span class="pull-right text-muted small"><em><c:out value="${cl.getCount()}" /> membres</em>
	                                    </span>
	                                </a>
                            	</c:forEach>
                            </div>
                            <!-- /.list-group -->
                            <a href="<c:url value="/add_promotion"/>" class="btn btn-default btn-block">Créer une nouvelle promotion</a>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-4 -->
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