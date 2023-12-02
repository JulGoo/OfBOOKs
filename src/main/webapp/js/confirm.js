function confirmBbsDelete(bbsNo){
	var confirmYes = confirm('정말 삭제하시겠습니까?');

        if (confirmYes) {
            location.href = 'boardDelete.do?bbsNo=' + bbsNo;
        } else {
            return;
        }
}

function confirmReviewDelete(reviewNo, isbn){
	var confirmYes = confirm('정말 삭제하시겠습니까?');

        if (confirmYes) {
            location.href = 'reviewDelete.do?reviewNo=' + reviewNo + '&isbn=' + isbn;
        } else {
            return;
        }
}