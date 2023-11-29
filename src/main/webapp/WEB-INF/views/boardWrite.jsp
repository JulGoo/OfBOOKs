<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">



	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">문의게시판</h1>
	<p class="mb-4">질문을 등록해주세요. 3~5일내로 답변드리겠습니다.</p>

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-body">
			<form method="post" action="boardWrite.do">
				<div class="table-responsive">
					<table class="table table-bordered" id="dataTable">

						<tr>
							<td style="font-weight: bold; font-size: larger; color: black;">제목</td>
							<td><input type="text" class="form-control border-0"
								name="title"></td>
						</tr>
						<tr>
							<td style="font-weight: bold; font-size: larger; color: black;">내용</td>
							<td><textarea class="form-control border-0" name="content"
									style="height: 350px;"></textarea></td>
						</tr>
						<tr>
							<td style="font-weight: bold; font-size: larger; color: black;">첨부파일</td>
							<td><input type="file" class="form-control border-0"
								name="fileName"></td>
						</tr>
						</tbody>
					</table>
					<button type="submit" class="btn btn-primary btn-lg" id="BtnSave"
						style="margin-bottom: 15px; float: right;">저장</button>
				</div>
			</form>
		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<%@include file="footer.jsp"%>