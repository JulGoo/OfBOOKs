<%@page import="DTO.LibraryDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="DTO.BookDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<%
BookDTO bookDTO = (BookDTO) request.getAttribute("bookDTO");
ArrayList<LibraryDTO> list = (ArrayList<LibraryDTO>) request.getAttribute("libraryDTOList");
String userID = (String) session.getAttribute("userID");

Date datetime = bookDTO.getDatetime();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
String formattedDate = dateFormat.format(datetime);

%>

<link href="css/comment.css" rel="stylesheet">
<script type="text/javascript" src="js/like.js"></script>


<div class="container-fluid">
	<!-- 도서 정보 -->
	<div class="card shadow mb-4">
		<div class="card-header py-3" style="display: flex;">
			<h6 class="m-0 font-weight-bold text-primary">도서 정보</h6>
		</div>
		<div class="card-body">
			<!-- 시작 -->
			<div>
				<div class="row g-0" style="color: black">
					<div class="col-md-3">
						<img src="<%=bookDTO.getThumbnail()%>" alt="Book Thumbnail"
							class="img-fluid rounded-start"
							style="width: 300px; height: 435px;">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<h3 class="card-title" style="font-weight: bold"><%=bookDTO.getTitle()%></h3>
							<br>
							<table class="table table-borderless"
								style="color: black; width: 1000px">
								<tr>
									<td style="width: 20%">저자</td>
									<td><%=bookDTO.getAuthor()%></td>
								</tr>
								<tr>
									<td>출판사</td>
									<td><%=bookDTO.getPublisher()%></td>
								</tr>
								<tr>
									<td>출판 날짜</td>
									<td><%=formattedDate%></td>
								</tr>
								<tr>
									<td>도서 판매가</td>
									<td><%=bookDTO.getSale_price()%>원</td>
								</tr>
								<tr>
									<td>도서 소개</td>
									<td><%=bookDTO.getContents()%>...</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="col-md-1">
						<input type="text" id="isbn" value="<%=bookDTO.getIsbn() %>" style="display:none;">
						<button class="btn btn-outline-danger" style="float: right"
							id="likeBtn" onclick="likeAction(<%=bookDTO.getIsbn() %>);">
							추천 +<span id="likeCount">${likeCnt }</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 도서관 정보 -->
	<!-- 소장 도서관 정보가 없을 경우 소장 도서관 div 생략 -->
	<%
	if (!list.isEmpty()) {
	%>
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">서울특별시 소장 도서관</h6>
		</div>
		<!-- 서울시 소장 도서관 목록 -->
		<div class="tbl_scroll_box mgb_30 own_rslt">
			<table class="table table-border">
				<thead>
					<tr>
						<th></th>
						<th>No</th>
						<th>도서관명</th>
						<th>홈페이지</th>
						<th>주소</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (int i = 0; i < list.size(); i++) {
					%>
					<tr>
						<td></td>
						<td><%=i + 1%></td>
						<td><%=list.get(i).getLibName()%></td>
						<td><a href="<%=list.get(i).getHomepage()%>" target="_blank"><%=list.get(i).getHomepage()%></a></td>
						<td><%=list.get(i).getAddress()%></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>

		<div id="map"
			style="width: 1000px; height: 500px; margin: 50px 0 70px 300px"></div>
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cf00b9fe7782ac5083b85fcad53a301f"></script>
		<script>
                var container = document.getElementById('map');
                var options = {
                    center: new kakao.maps.LatLng(<%=list.get(0).getLatitude()%>, <%=list.get(0).getLongitude()%>),
                    
                    level: 8
                };

                var map = new kakao.maps.Map(container, options);

                var positions = [];

                <%
                for (int i = 0; i < list.size(); i++) {
                %>
                positions[<%=i%>] = {
                        content: '<div style="font-weight:bold; font-size:larger;"><%=list.get(i).getLibName()%></div>',
                        latlng: new kakao.maps.LatLng(<%=list.get(i).getLatitude()%>, <%=list.get(i).getLongitude()%>)
                    };
				<%
				}
				%>
			for (var i = 0; i < positions.length; i++) {
				// 마커를 생성합니다
				var marker = new kakao.maps.Marker({
					map : map, // 마커를 표시할 지도
					position : positions[i].latlng
				});

				// 마커에 표시할 인포윈도우를 생성
				var infowindow = new kakao.maps.InfoWindow({
					content : positions[i].content
				});

				// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록
				// 이벤트 리스너로는 클로저를 만들어 등록
				// for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됨
				kakao.maps.event.addListener(marker, 'mouseover',
						makeOverListener(map, marker, infowindow));
				kakao.maps.event.addListener(marker, 'mouseout',
						makeOutListener(infowindow));
			}

			// 인포윈도우를 표시하는 클로저를 만드는 함수
			function makeOverListener(map, marker, infowindow) {
				return function() {
					infowindow.open(map, marker);
				};
			}

			// 인포윈도우를 닫는 클로저를 만드는 함수
			function makeOutListener(infowindow) {
				return function() {
					infowindow.close();
				};
			}
		</script>

	</div>
	<%} %>

	<!-- 리뷰 -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">리뷰</h6>
		</div>
		<div class="card-body">
			<div class="panel">
				<form method="post"
					action="reviewSend.do?isbn=<%=bookDTO.getIsbn()%>">
					<div class="panel-body">
						<textarea class="form-control" rows="2" placeholder="리뷰를 남겨주세요."
							name="comment"></textarea>
						<div class="mar-top clearfix">
							<button class="btn btn-sm btn-primary pull-right" type="submit"
								style="float: right">작성</button>
						</div>
					</div>
				</form>
			</div>
			<div class="panel">
				<div class="panel-body">
					<!-- 댓글 -->
					<c:forEach var="r" items="${reviewDTOList }">
						<div class="media-block"
							style="display: flex; flex-direction: row;">
							<img class="img-circle img-sm" alt="Profile Picture"
								style="margin-right: 40px" src="uploadProfile/${r.fileName }"></a>
							<div class="media-body">
								<div class="mar-btm" style="font-weight:bold">
									${r.userID }
									<p class="text-muted text-sm">${r.date }</p>
									
								<c:if test="${r.userID.equals(userID) }">
								<button class="btn btn-sm btn-danger pull-right" type="submit"
								onclick="confirmReviewDelete(${r.reviewNo}, ${r.isbn });"
								style="float: right">삭제</button>
								</c:if>
								
								</div>
								<p style="color:black">${r.comment }</p>
							</div>
						</div>
						<hr>
					</c:forEach>
				</div>
			</div>

		</div>

		<%@include file="footer.jsp"%>