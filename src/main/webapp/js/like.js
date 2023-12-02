//페이지 로딩 시 추천 확인 후 버튼 처리
window.addEventListener('DOMContentLoaded', function() {
	var isbn = document.getElementById("isbn").value;
	isLiked(isbn);
});

//추천 클릭 후 총 개수 가져오기
function likeAction(isbn) {
	$.ajax({
		type: "POST",
		url: "like.do",
		dataType: "json",
		data: {
			"isbn": isbn
		},
		success: function(response) {
			if(response == -1) {
				location.href='bookInfo.do?isbn='+isbn+'&msg=NoLogin';
				return;
			}
			updateLikeCount(response,isbn);
		},
		error: function(error) {
			console.error("Error toggling like:", error);
		}
	});
}

// 추천 수 업데이트
function updateLikeCount(likeCnt, isbn) {
	document.getElementById("likeCount").innerText = likeCnt;
	isLiked(isbn);
}

//추천 상태 확인
function isLiked(isbn) {
	$.ajax({
		type: "GET",
		url: "isLiked.do",
		data: {
			isbn: isbn,
		},
		success: function(response) {
			updateLikeBtn(response);
		},
		error: function(error) {
			console.error("Error checking like status:", error);
		}
	});
}

//추천 상태로 버튼 업데이트
function updateLikeBtn(status) {
	var btn = $("#likeBtn");
        if (status === "1") {
            btn.removeClass("btn-outline-danger").addClass("btn-danger");
        } else {
            btn.removeClass("btn-danger").addClass("btn-outline-danger");
        }
}