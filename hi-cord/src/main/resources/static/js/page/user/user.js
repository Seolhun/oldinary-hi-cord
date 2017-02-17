var csrfToken = document.getElementById("csrfToken").content;
var csrfHeader = document.getElementById("csrfHeader").content;

$(document).ready(function(){
	//validate Email 
	function validateEmail(email) {
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;
		return re.test(email);
	}

	//validate Phone 
	function validatePhone(phone) {
		var re = /^\+?1?\s*?\(?\d{3}(?:\)|[-|\s])?\s*?\d{3,4}[-|\s]?\d{4}$/;
		return re.test(phone);
	}

	//Validate Agree of sign up
	function agreeCheck() {
		var checked = $("[name='signAgree']");
		for (var i = 0; i < checked.length; i++) {
			if (!checked[i].checked) {
				checked[i].focus();
				return false;
			}
		}
		return true;
	}

	$('#userEmail').on("keyup",function(){
		var inputEmail = $("#userEmail").val();
		if (!validateEmail(inputEmail)) {
			$("#emailMsg").text(inputEmail + " is not valid");
			$("#emailMsg").css("color", "red");
			$("#emailDupl").prop("disabled", true);
			return;
		} else {
			$("#emailMsg").text(inputEmail + " is valid");
			$("#emailMsg").css("color", "blue");
			$("#emailDupl").prop("disabled", false);
		}
	});

	//Submit Save
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
		var data = {}
		data["userEmail"] = userEmail;
		data["csrfToken"] = csrfToken;
		data["csrfHeader"] = csrfHeader;
		$.ajax({
			url : "signup/duplicate/email",
			type : 'POST',
			timeout : 60000,
			data : JSON.stringify(data),
			dataType : 'json',
			dataType : "json",
			beforeSend: function(xhr) {
			    xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success: function(data) {
				if(data){
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

	$("input[name=saveUser]").click(function(){
		var emailDupl=$("#emailDupl");
		var email=$("#userEmail");
		if(!agreeCheck()){
			return;
		} else if(emailDupl.disabled){
			email.focus();
			return;
		} 
		$("#saveUserFrm").submit();
	});
})