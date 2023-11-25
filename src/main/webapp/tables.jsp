<%@page import="DAO.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header_copy.jsp"%>

<div class="container-fluid">
	<!-- 도서 정보 -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">도서 정보</h6>
		</div>
		<div class="card-body">
			<!-- 시작 -->
			<div >
				<div class="row g-0">
					<div class="col-md-3">
						<img src="img/단비.png" class="img-fluid rounded-start" alt="BOOK" style="height:500px;">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<h5 class="card-title">Card title</h5>
							<p class="card-text">This is a wider card with supporting
								text below as a natural lead-in to additional content. This
								content is a little bit longer.</p>
							<p class="card-text">
								<small class="text-muted">Last updated 3 mins ago</small>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 도서관 정보 -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">서울교육청 소장 도서관</h6>
		</div>
		<div class="card-body">

			<div id="map" style="width: 800px; height: 500px;"></div>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cf00b9fe7782ac5083b85fcad53a301f"></script>
			<script>
				var container = document.getElementById('map');
				var options = {
					center : new kakao.maps.LatLng(37.570758, 126.966893),
					level : 7
				};

				var map = new kakao.maps.Map(container, options);
			</script>
		</div>
	</div>


	<!-- 리뷰 -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">리뷰</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">


				</table>
			</div>
		</div>
	</div>
</div>

<%@include file="footer_copy.jsp"%>