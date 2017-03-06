var csrfToken = document.getElementById("csrfToken").content;
var csrfHeader = document.getElementById("csrfHeader").content;

$(document).ready(function(){
	// Validate Agree of sign up
	function agreeCheck() {
		var checked = $(".signAgree");
		for (var i = 0; i < checked.length; i++) {
			if (!checked[i].checked) {
				checked[i].focus();
				return false;
			}
		}
		return true;
	}

	// Submit Save
	$('#nameDupl').click(function(){
		var sportsClubName = $("#sportsClubName").val();
//		if (!validateEmail(userEmail)) {
//			if(userEmail.length==0){
//				$("#nameMsg").text("Name is empty");
//				$("#nameMsg").css("color", "red");
//				return;
//			}
//			$("#nameMsg").text(sportsClubName + " is not valid");
//			$("#nameMsg").css("color", "red");
//			return;
//		}
//		
		var sportsClub = {}
		sportsClub["sportsClubName"] = sportsClubName;
		$.ajax({
			url : "/club/insert/duplicate/name-json",
			type : 'POST',
			timeout : 60000,
			data : JSON.stringify(sportsClub),
			dataType : "json",
			beforeSend: function(xhr) {
			    xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success: function(data) {
				console.log(data);
				if(data.result=="success"){
					$("#nameMsg").text(sportsClubName + " could be used");
					$("#nameMsg").css("color", "blue");				
				} else {
					$("#nameMsg").text(sportsClubName + " could't be used");
					$("#nameMsg").css("color", "red");	
				}
			},
			error : function(e){
				swal("Error","이름 중복확인이 올바르게 작동하지 않았습니다.");
			}
		});	
	});
	
	// Submit Save
	$('#telDupl').click(function(){
		var sportsClubTel = $("#sportsClubTel").val();
//		if (!validateTel(sportsClubTel)) {
//			if(sportsClubTel.length==0){
//				$("#telMsg").text("Phone is empty");
//				$("#telMsg").css("color", "red");
//				return;
//			}
//			$("#telMsg").text(sportsClubTel + " is not valid");
//			$("#telMsg").css("color", "red");
//			return;
//		}
//		
		var sportsClub = {}
		sportsClub["sportsClubTel"] = sportsClubTel;
		$.ajax({
			url : "/club/insert/duplicate/tel-json",
			type : 'POST',
			timeout : 60000,
			data : JSON.stringify(sportsClub),
			dataType : "json",
			beforeSend: function(xhr) {
			    xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success: function(data) {
				console.log(data);
				if(data.result=="success"){
					$("#telMsg").text(sportsClubTel + " could be used");
					$("#telMsg").css("color", "blue");				
				} else {
					$("#telMsg").text(sportsClubTel + " could't be used");
					$("#telMsg").css("color", "red");	
				}
			},
			error : function(e){
				swal("Error", "핸드폰 중복확인이 올바르게 작동하지 않았습니다.");
			}
		});	
	});

	$("input[name=saveSportsClub]").click(function(){
		var nameDupl=$("#emailDupl");
		var telDupl=$("#phoneDupl");
		var sportsClubName=$("#sportsClubName");
		var sportsClubTel=$("#sportsClubTel");
		if(!agreeCheck()){
			return;
		} else if(sportsClubName.disabled){
			sportsClubName.focus();
			return;
		}  else if(sportsClubTel.disabled){
			sportsClubTel.focus();
			return;
		} 
		$("#insertClubFrm").submit();
	});
})


// 본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function daumZipCode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if(fullRoadAddr !== ''){
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sportsClubZipCode').value = data.zonecode; // 5자리
																			// 새우편번호
																			// 사용
            document.getElementById('sportsClubAddress').value = fullRoadAddr;
            document.getElementById('sportsClubAddress2').value = data.jibunAddress;

            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                // 예상되는 도로명 주소에 조합형 주소를 추가한다.
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

            } else {
                document.getElementById('guide').innerHTML = '';
            }
        }
    }).open();
}