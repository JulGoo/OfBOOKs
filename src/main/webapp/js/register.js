var authKey = null;
var isCodeSame = false;

//이메일 전송
function sendCode() {
	var email = $("#userEmail").val();
	if (email == "") {
		alert("이메일을 입력하세요.");
		return;
	}

	$.ajax({
		url: "sendEmail.do",
		type: "post",
		data: { "email": email },
		dataType: "text",
		success: function(data) {
			alert("이메일이 전송되었습니다.");
			authKey = data;
		},
		error: function(request, status, error) {
			alert("이메일 인증에 실패했습니다. 다시 시도해주세요.");
			console.log("code : " + request.status);
			console.log("message : " + request.responseText);
			console.log("error : " + error);
		}
	});
}

//입력 코드 확인
function checkCode() {
	var code = $("#chkUserEmail").val();
	if (code == authKey) {
		alert("이메일 인증 완료");
		document.getElementById("chkCodeBtn").disabled = true;
		document.getElementById("sendEmailBtn").disabled = true;
		document.getElementById("userEmail").readOnly = true;
		document.getElementById("chkUserEmail").readOnly = true;
		isCodeSame = true;
		return;
	} else {
		alert("인증번호가 일치하지 않습니다.");
		return;
	}
}

//회원가입 유효성 검사
function validateR() {
	var id = document.getElementById("userID");
	var name = document.getElementById("userName");
	var email = document.getElementById("userEmail");
	var pw = document.getElementById("userPW");
	var chkpw = document.getElementById("chkUserPW");

	if (id.value == "") {
		alert("아이디를 입력하세요");
		id.focus();
		return false;
	}
	
	if (name.value == "") {
		alert("이름을 입력하세요");
		name.focus();
		return false;
	}
	
	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if (!exptext.test(email.value)) {
		alert("올바른 형식의 이메일이 아닙니다");
		email.focus();
		return false;
	}
	
	if (isCodeSame == false) {
		alert("이메일 인증을 완료해주세요");
		return false;
	}
	
	if (pw.value == "") {
		alert("비밀번호를 입력하세요");
		pw.focus();
		return false;
	}
	
	var pwform = /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,15}$/;
	if (!pwform.test(pw.value)) {
		alert("영문자+숫자 조합으로 6~15자리 사용해야 합니다");
		pw.focus();
		return false;
	}
	
	if (pw.value !== chkpw.value) {
		alert("비밀번호가 일치하지 않습니다!");
		pw.focus();
		return false;
	}

	document.register.submit();
}

