<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = request.getParameter("msg");
	if(msg != null) {
		switch(msg) {
			case "WrongPW":
				out.println("<script>alert('비밀번호가 틀렸습니다.');history.back();</script>");
				out.close();
				break;
			case "NoUserInfo":
				out.println("<script>alert('회원정보가 존재하지 않습니다.');history.back();</script>");
				out.close();
				break;
			case "DatabaseError":
				out.println("<script>alert('데이터베이스 에러 발생, 관리자에게 문의하세요.');history.back();</script>");
				out.close();
				break;
			case "EmptyInput":
				out.println("<script>alert('입력되지 않은 칸이 있습니다.');history.back();</script>");
				out.close();
				break;
			case "Error":
				out.println("<script>alert('오류');history.back();</script>");
				out.close();
				break;
			case "Success":
				out.println("<script>alert('완료했습니다.');location.href='index.do';</script>");
				out.close();
				break;
		}
	}
%>