var root, csrfHeader, csrfToken, boardType;
root = "";
csrfHeader = document.getElementById("csrfHeader").content;
csrfToken = document.getElementById("csrfToken").content;
boardType = $("#bType").val();

$(document).ready(function() {

	$("#submitBoardBtn").click(function() {
		var board = {}, fileData = {};

		var boardSubject, boardContent, boardType, boardWithFileList;
		boardSubject = $("#boardSubject").val();
		boardContent = $("#boardContent").val();
		boardType = $("#boardType").val();
		
		var boardWithFileList = document.getElementById("boardWithFileList").files || [];
		for (var i = 0; i < boardWithFileList.length; i++) {
		    console.log('found file ' + i + ' = ' + boardWithFileList[i].name);
		}

		board["boardSubject"] = boardSubject;
		board["boardContent"] = boardContent;
		board["boardType"] = boardType;

		board["boardWithFileList"] = boardWithFileList;
		
		$.ajax({
			type : 'POST',
			url : root+"/board/"+boardType+"/write",
			contentType : 'application/json',
			data : JSON.stringify(board),
			dataType : 'JSON',
			beforeSend: function(xhr) {
			    xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success : function(data){
				if (data.result==="success") {
					console.log('Success');
					swal("실패","파일전송 성공","success");
					return;
				}
			}, error : function(e) {
				console.log('Fail');
				swal("","파일전송 실패","warning");
				return;
			}
			
		});
		
	});
});