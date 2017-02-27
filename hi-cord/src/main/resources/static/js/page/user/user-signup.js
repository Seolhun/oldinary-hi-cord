var csrfToken = document.getElementById("csrfToken").content;
var csrfHeader = document.getElementById("csrfHeader").content;

$(document).ready(function(){
	// validate Email
	function validateEmail(email) {
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;
		return re.test(email);
	}

	// validate Phone
	function validatePhone(phone) {
		var re = /^\+?1?\s*?\(?\d{3}(?:\)|[-|\s])?\s*?\d{3,4}[-|\s]?\d{4}$/;
		return re.test(phone);
	}

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

	$('#userEmail').on("keyup",function(){
		var userEmail = $("#userEmail").val();
		if (!validateEmail(userEmail)) {
			$('#emailMsg').text(userEmail + " is not valid");
			$('#emailMsg').css("color", "red");
			$("#emailDupl").prop("disabled", true);
			return;
		} else {
			$("#emailMsg").text(userEmail + " is valid");
			$("#emailMsg").css("color", "blue");
			$("#emailDupl").prop("disabled", false);
		}
	});
	
	$('#userPhone').on("keyup",function(){
		var userPhone = $("#userPhone").val();
		if (!validatePhone(userPhone)) {
			$("#phoneMsg").text(userPhone + " is not valid");
			$("#phoneMsg").css("color", "red");
			$("#phoneDupl").prop("disabled", true);
			return;
		} else {
			$("#phoneMsg").text(userPhone + " is valid");
			$("#phoneMsg").css("color", "blue");
			$("#phoneDupl").prop("disabled", false);
		}
	});

	// Submit Save
	$('#emailDupl').click(function(){
		var userEmail = $("#userEmail").val();
		if (!validateEmail(userEmail)) {
			if(userEmail.length==0){
				$("#emailMsg").text("Email is empty");
				$("#emailMsg").css("color", "red");
				return;
			}
			$("#emailMsg").text(userEmail + " is not valid");
			$("#emailMsg").css("color", "red");
			return;
		}
		var user = {}
		user["userEmail"] = userEmail;
		$.ajax({
			url : "/user/signup/duplicate/email",
			type : 'POST',
			timeout : 60000,
			data : JSON.stringify(user),
			dataType : "json",
			beforeSend: function(xhr) {
			    xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success: function(data) {
				console.log(data);
				if(data.result=="success"){
					$("#emailMsg").text(userEmail + " could be used");
					$("#emailMsg").css("color", "blue");				
				} else {
					$("#emailMsg").text(userEmail + " could't be used");
					$("#emailMsg").css("color", "red");	
				}
			},
			error : function(e){
				swal("Error","이메일 중복확인이 올바르게 작동하지 않았습니다.");
			}
		});	
	});
	
	// Submit Save
	$('#phoneDupl').click(function(){
		var userPhone = $("#userPhone").val();
		if (!validatePhone(userPhone)) {
			if(userPhone.length==0){
				$("#phoneMsg").text("Phone is empty");
				$("#phoneMsg").css("color", "red");
				return;
			}
			$("#phoneMsg").text(userPhone + " is not valid");
			$("#phoneMsg").css("color", "red");
			return;
		}
		var user = {}
		user["userPhone"] = userPhone;
		$.ajax({
			url : "/user/signup/duplicate/phone",
			type : 'POST',
			timeout : 60000,
			data : JSON.stringify(user),
			dataType : "json",
			beforeSend: function(xhr) {
			    xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success: function(data) {
				console.log(data);
				if(data.result=="success"){
					$("#phoneMsg").text(userPhone + " could be used");
					$("#phoneMsg").css("color", "blue");				
				} else {
					$("#phoneMsg").text(userPhone + " could't be used");
					$("#phoneMsg").css("color", "red");	
				}
			},
			error : function(e){
				swal("Error","핸드폰 중복확인이 올바르게 작동하지 않았습니다.");
			}
		});	
	});

	$("input[name=saveUser]").click(function(){
		var emailDupl=$("#emailDupl");
		var phoneDupl=$("#phoneDupl");
		var userEmail=$("#userEmail");
		var userPhone=$("#userPhone");
		if(!agreeCheck()){
			return;
		} else if(userEmail.disabled){
			userEmail.focus();
			return;
		}  else if(userPhone.disabled){
			userPhone.focus();
			return;
		} 
		$("#saveUserFrm").submit();
	});
})


// 본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
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
                document.getElementById('userZipCode').value = data.zonecode; // 5자리
																				// 새우편번호
																				// 사용
                document.getElementById('userAddress').value = fullRoadAddr;
                document.getElementById('userAddress2').value = data.jibunAddress;

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