<%@page import="DAO.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<div class="container-xl px-4 mt-4">
	<hr class="mt-0 mb-4">
	<div class="row">

		<form method="post" action="profile.do" name="register" enctype="multipart/form-data">

			<div class="col-xl-4">
				<!-- Profile picture card-->
				<div class="card mb-4 mb-xl-0">
					<div class="card-header">Profile Picture</div>
					<div class="card-body text-center">

						<!-- Profile picture image-->
						<img class="img-account-profile rounded-circle mb-2" id="preview"
							src="uploadProfile/${userDTO.fileName }" alt="" width="300px"
							height="300px">

						<!-- Profile picture help block-->
						<div class="small font-italic text-muted mb-4">JPG or PNG no
							larger than 5 MB</div>

						<!-- Profile picture upload button-->
						<input type="file" name="file" />
					</div>
				</div>
			</div>
			<div class="col-xl-8">
				<!-- Account details card-->
				<div class="card mb-4">
					<div class="card-header">Account Details</div>
					<div class="card-body">

						<!-- Form Group (ID)-->
						<div class="mb-3">
							<label class="small mb-1" for="userID">ID</label> <input
								class="form-control" id="userID" type="text" name="userID"
								value="${userDTO.userID }" readonly>
						</div>

						<!-- Form Group (name)-->
						<div class="mb-3">
							<label class="small mb-1" for="inputName">Name</label> <input
								class="form-control" id="userName" type="text"
								placeholder="Enter your name" name="userName"
								value="${userDTO.userName }">
						</div>

						<!-- Form Group (email address)-->
						<div class="mb-3">
							<label class="small mb-1" for="inputEmailAddress">Email
								address</label> <input class="form-control" id="userEmail" type="email"
								name="userEmail" value="${userDTO.userEmail }">
							<button class="btn btn-primary" type="button"
								onclick="sendCode();" style="float: right;" id="sendEmailBtn">전송</button>
						</div>
						<div class="mb-3">
							<label class="small mb-1" for="inputEmailAddress">Enter
								the Code</label> <input class="form-control" id="chkUserEmail"
								type="text" name="userEmail"
								placeholder="Enter the Code from E-mail">
							<button class="btn btn-primary" type="button" id="chkCodeBtn"
								onclick="checkCode();" style="float: right;">확인</button>
						</div>

						<!-- Form Group (PW)-->
						<div class="mb-3">
							<label class="small mb-1" for="inputName">Password</label> <input
								class="form-control" id="userPW" type="password"
								placeholder="Enter your Password" name="userPW">
						</div>
						<div class="mb-3">
							<label class="small mb-1" for="inputName">Repeat Password</label>
							<input class="form-control" id="chkUserPW" type="password"
								placeholder="Repeat your Password" name="chkuserPW">
						</div>

						<br>
						<!-- Save changes button-->
						<input type="button" id="saveProfile" onclick="validateR();"
							class="btn btn-primary" style="font-size: 17px;" value="저장" />

					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<script src="js/register.js"></script>

<%@include file="footer.jsp"%>