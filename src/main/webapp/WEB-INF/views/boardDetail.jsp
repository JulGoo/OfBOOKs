<%@page import="DTO.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

	<%
	BoardDTO boardDTO = (BoardDTO) request.getAttribute("boardDTO");
	String userID = (String) session.getAttribute("userID");
	%>

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">문의게시판</h1>
	<p class="mb-4">질문을 등록해주세요. 3~5일내로 답변드리겠습니다.</p>

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable">

					<tr>
						<td
							style="font-weight: bold; font-size: larger; color: black; width: 30%;">제목</td>
						<td><%=boardDTO.getTitle()%></td>
					</tr>
					<tr>
						<td style="font-weight: bold; font-size: larger; color: black;">내용</td>
						<td style="height: 200px"><%=boardDTO.getContent()%></td>
					</tr>
					<tr>
						<td style="font-weight: bold; font-size: larger; color: black;">첨부파일</td>
						<td><img src="uploadProfile/<%=boardDTO.getFileName()%>" width="1200px"></td>
					</tr>
					</tbody>
				</table>

				<%
				//System.out.println(userID);
				//System.out.println(boardDTO.getUserID());

				if (userID != null && userID.equals(boardDTO.getUserID())) {
				%>

				<button type="button" class="btn btn-danger btn-lg" id="BtnDelete"
					onclick="confirmBbsDelete(<%=boardDTO.getBbsNo() %>);"
					style="margin-bottom: 15px; float: right;">삭제</button>

				<button type="button" class="btn btn-primary btn-lg" id="BtnUpdate"
					onclick="location.href='boardUpdate.do?bbsNo=<%=boardDTO.getBbsNo() %>';"
					style="margin-bottom: 15px; float: right; margin-right: 10px;">수정</button>

				<%
				}
				%>

			</div>
		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<%@include file="footer.jsp"%>