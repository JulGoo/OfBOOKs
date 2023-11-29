<%@page import="DTO.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

<%
	ArrayList<BoardDTO> boardList = (ArrayList<BoardDTO>)request.getAttribute("boardDTO");
%>

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">문의게시판</h1>
	<p class="mb-4">질문을 등록해주세요. 3~5일내로 답변드리겠습니다.</p>

	<button class="btn btn-primary btn-lg" id="BtnWrite"
		onclick="location.href='boardWrite.do';" style="margin-bottom: 15px;">작성</button>

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<%
                            for (int i = 0; i < boardList.size(); i++) {
                    %>
					<tbody onclick="location.href='boardDetail.do?bbsNo=<%=boardList.get(i).getBbsNo() %>'">
						<tr>
							<td><%=boardList.get(i).getBbsNo() %></td>
							<td><%=boardList.get(i).getTitle() %></td>
							<td><%=boardList.get(i).getUserID() %></td>
							<td><%=boardList.get(i).getDate() %></td>
						</tr>
					</tbody>
					<%
                            }
					%>
				</table>
			</div>
		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<%@include file="footer.jsp"%>