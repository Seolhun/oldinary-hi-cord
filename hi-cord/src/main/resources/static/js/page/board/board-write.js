var root, csrfHeader, csrfToken, boardType;
root = "";
csrfHeader = document.getElementById("csrfHeader").content;
csrfToken = document.getElementById("csrfToken").content;

$(document).ready(function() {
	$("#submitBoardBtn").click(function(e) {

		//게시판의 필요한 정보를 담아낸다.
		var form, boardSubject, boardContent, boardType, boardWithFileList, i;
		boardSubject = $("#boardSubject").val();
		boardContent = $("#boardContent").val();
		boardType = $("#boardType").val();
		
		//form 값을 가져와 담는다.
		form = $("#boardForm");
		var boardForm = new FormData(form);

		//Form데이터에 담아서 전송한다.
		boardWithFileList = $('#boardWithFileList')[0].files;
		for(i=0;i<boardWithFileList.length;i++){
			console.log($('#boardWithFileList')[0].files[i]);
			boardForm.append('boardWithFileList', $('#boardWithFileList')[0].files[i]);	
		}
		boardForm.append('boardType', boardType);
		boardForm.append('boardSubject', boardSubject);
		boardForm.append('boardContent', boardContent);
		

		$.ajax({
			type : 'POST',
			url : root + "/board/" + boardType + "/write",
			data : boardForm,
			enctype : 'multipart/form-data',
			cache : false,
			processData : false,
			contentType : false,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success : function(data) {
				if (data.result === "success") {
					console.log('Success');
					swal("", "게시판 등록 성공", "success");
					return;
				} else if(data.reult==="over"){
					console.log('Over');
					swal("", "파일 용량이 초과되었습니다.", "warning");
				} else if(data.reult==="invalid"){
					console.log('Invalid');
					swal("", "파일 형식이 올바르지 않습니다.\n(.은 하나만 존재해야 합니다.)", "warning");
				} else {
					console.log('Invalid');
					swal("", "게시물 저장이 실패되었습니다.", "danger");
				}
			},
			error : function(e) {
				console.log('Fail');
				swal("", "파일전송 실패", "warning");
				return;
			}

		});
	});
});