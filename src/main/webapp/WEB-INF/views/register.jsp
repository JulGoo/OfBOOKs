<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="error.jsp"%>

<!DOCTYPE html>
<html lang="uft-8" style="background-color: rosybrown">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>OfBOOKs - Register</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body style="background-color: rosybrown">

	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"
						style="background-image: url(img/rose.jpg)"></div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
							</div>
							<br>
							<form class="user" method="post" action="register.do" name="register">
								<div class="form-group">
									<input type="text" class="form-control form-control-user"
										id="userID" name="userID" placeholder="ID" style="font-size: 17px;">
								</div>

								<div class="form-group">
									<input type="text" class="form-control form-control-user"
										id="userName" name="userName" placeholder="Name" style="font-size: 17px;">
								</div>


								<div class="form-group row">
									<div class="col-sm-9 mb-3 mb-sm-0">
										<input type="email" class="form-control form-control-user"
										id="userEmail" name="userEmail" placeholder="E-mail Address"
										style="font-size: 17px;">
									</div>
									<div class="col-sm-3 mb-3 mb-sm-0">
										<button type="button" id="sendEmailBtn" onclick="sendCode();"
											class="btn btn-primary btn-user btn-block"
											style="font-size: 17px;">전송</button>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-9 mb-3 mb-sm-0">
										<input type="text" class="form-control form-control-user"
											id="chkUserEmail" placeholder="Enter your Code from E-mail"
											style="font-size: 17px;">
									</div>
									<div class="col-sm-3 mb-3 mb-sm-0">
										<button type="button" id="chkCodeBtn" onclick="checkCode();"
											class="btn btn-primary btn-user btn-block"
											style="font-size: 17px;">확인</button>
									</div>
								</div>


								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="password" class="form-control form-control-user"
											id="userPW" name="userPW" placeholder="Password" style="font-size: 17px;">
									</div>
									<div class="col-sm-6">
										<input type="password" class="form-control form-control-user"
											id="chkUserPW" placeholder="Repeat Password"
											style="font-size: 17px;">
									</div>
								</div>
								<br>

								<input type="button" id="register" onclick="validateR();"
									class="btn btn-primary btn-user btn-block"
									style="font-size: 17px;" value="Register Account"/>
							</form>
							<hr>
							<div class="text-center">
								<a class="small" href="forgot-password.do"
									style="font-size: 15px;">Forgot Password?</a>
							</div>
							<div class="text-center">
								<a class="small" href="login.do" style="font-size: 15px;">Already
									have an account? Login!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

	<script src="js/register.js"></script>
</body>

</html>