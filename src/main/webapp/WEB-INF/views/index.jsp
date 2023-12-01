<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<!-- Begin Page Content -->
<div
	style="width: auto; height: 90%; background-image: url(img/book.jpg); opacity: 80%;">
	<div class="container-fluid"
		style="position: relative; left: auto; top: 270px">
		<form method="get" action="bookList.do">
			<div class="input-group"
				style="width: 900px; height: 70px; left: 350px">
				<input class="form-control form-control-lg" id="emailAddressBelow"
					name="title" type="text" placeholder="도서 제목을 입력하세요."
					style="height: 70px" />+
				<div class="input-group-append">
					<button class="btn btn-primary btn-lg" id="submitButton"
						type="submit">
						검색 <i class="fas fa-search fa-sm"></i>
					</button>
				</div>
			</div>
		</form>

		<!-- Content Row -->
		<div class="row"></div>

	</div>
	<!-- /.container-fluid -->
</div>

</div>
<!-- End of Main Content -->

<%@ include file="footer.jsp"%>