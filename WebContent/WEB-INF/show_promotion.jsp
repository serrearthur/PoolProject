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
                <a class="navbar-brand" href="<c:url value="home"/>">When Is My Code Review?</a>
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
                    <h1 class="page-header">Gestion de la promo "${controller.getClassName(promoId) }"</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">
                    <!-- /.panel -->
                    <div class="panel panel-default">
                    	<!-- /.panel-body -->
                        <div class="panel-body">
                            <!-- /.row -->
                            <div class="row">
                            	<!-- /.col-lg-12 -->
                            	<div class="col-lg-12">
                            		<!-- /.table-responsive -->
                                    <div class="table-responsive">
                                        <table class="table table-hover table-striped">
                                        	<thead>
                                        		<tr>                                           
                                                    <th>Nom</th>
                                                    <th>Email</th>
                                                    <th>Date de naissance</th>
                                                    <th class="text-right">Action</th>                                                   
                                                </tr>
                                        	</thead>
                                        	<tbody>
                                        		
	                                        		<c:forEach items="${controller.getMembersClass(promoId)}" var="m">
		                                        		<tr>
		                                        			<form action="" method="post" class="">
			                                        			<td>${m.name }</td>
			                                        			<td>${m.email }</td>
			                                        			<td>${m.birthdate }
			                                        				<input type="hidden" name="memberId" value="${m.id}"/>
			                                        			</td>
			                                        			<td  class="text-right">
			                                        				<button type="submit" class="btn btn-sm btn-danger" name="submit" value="delete"> <i class="fa fa-trash"></i></button>
			                                        			</td>
		                                        			</form>
		                                        		</tr>
		                                        	</c:forEach>
                                        	</tbody>
                                        </table>
                                        <div>
                                        	<form action="" method="post" class="">
												<select name="memberId" class="input-sm form-control" style="width: 20%; display: inline">
													<c:forEach items="${controller.getMembers()}" var="m">
														<c:if test="${m.getCrclassId() != promoId}">
															<option value="${m.id }">
																${m.name}
															</option>
														</c:if>
		                                        	</c:forEach>
	                                        	</select>
	                                        	<button type="submit" class="btn btn-default" name="submit" value="adduser">Ajouter un utilisateur</button>
	                                        </form>
                                        </div>
                                    </div>
                                    <!-- /.table-responsive -->
                               </div>
                               <!-- /.col-lg-12 --> 
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
