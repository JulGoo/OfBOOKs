<%@page import="java.util.ArrayList"%>
<%@page import="DTO.BookDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<style>
td.center {
	padding: 90px 0;
}
</style>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">도서 검색 결과</h1>

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-hover" id="bookList" style="color: black">
					<thead>
						<tr>
							<td>표지</td>
							<td>제목</td>
							<td>작가</td>
							<td>출판사</td>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="b" items="${bookDTOList }">
							<tr onclick="location.href='bookInfo.do?isbn=${b.isbn }'">
								<td><img src="${b.thumbnail }" alt="Book Thumbnail"
									width="120px" height="174px"></td>
								<td class="center">${b.title }</td>
								<td class="center">${b.author}</td>
								<td class="center">${b.publisher}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<%@include file="footer.jsp"%>