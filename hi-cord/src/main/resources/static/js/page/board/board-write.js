var root, csrfHeader, csrfToken, boardType;
root = "";
csrfHeader = document.getElementById("csrfHeader").content;
csrfToken = document.getElementById("csrfToken").content;

$(document).ready(function() {
	$("#submitBoardBtn").click(function(e) {
		
//		fileSave();
		boardSave();
	});
	
	function fileSave(){
		//게시판의 필요한 정보를 담아낸다.
		var form, boardWithFileList, i;
		
		//form 값을 가져와 담는다.
		form = $("#boardForm");
		var boardForm = new FormData(form);

		//Form데이터에 담아서 전송한다.
		boardWithFileList = $('#boardWithFileList')[0].files;
		for(i=0;i<boardWithFileList.length;i++){
			console.log($('#boardWithFileList')[0].files[i]);
			boardForm.append('boardWithFileList', $('#boardWithFileList')[0].files[i]);	
		}

		$.ajax({
			type : 'POST',
			url : root + "/file/save",
			data : boardForm,
			dataType : 'text',
			enctype : 'multipart/form-data',
			cache : false,
			processData : false,
			contentType : false,
			beforeSend: function(xhr) {
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
	}
	
	function boardSave(){
		//게시판 인풋 값 변수 선언.
		var boardSubject, boardContent, boardType, board={};
		//변수를 가져온다.
		boardSubject = $("#boardSubject").val();
		boardContent = $("#boardContent").val();
		boardType = $("#boardType").val();
		//변수를 게시판 오브젝트에 담는다.
		board["boardType"]= boardType;
		board["boardContent"]=boardContent;
		board["boardSubject"]=boardSubject;
		//Ajax로 요청한다.
		$.ajax({
			type : 'POST',
			url : root + "/board/"+boardType+"/write",
			data : JSON.stringify(board),
			dataType : 'application/json',
			beforeSend: function(xhr) {
			    xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success : function(data) {
				if (data.result === "success") {
					console.log('Success');
					swal("", "게시물 등록 성공", "success");
					return;
				} else if(data.reult==="invalid"){
					console.log('Invalid');
					swal("", "게시물 형식이 올바르지 않습니다.\n(.은 하나만 존재해야 합니다.)", "warning");
					return;
				} else {
					console.log('fail');
					swal("", "게시물 저장이 실패되었습니다.", "danger");
					return;
				}
			},
			error : function(e) {
				console.log('ERROR');
				swal("", "게시물 등록 실패", "danger");
				return;
			}
		});
	}
});