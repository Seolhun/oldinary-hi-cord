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
			$("#emailMsg").text(userEmail + " is not valid");
			$("#emailMsg").css("color", "red");
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
		var user = {}
		user["userEmail"] = userEmail;
		$.ajax({
			url : "signup/duplicate/email",
			type : 'POST',
			timeout : 60000,
			data : JSON.stringify(user),
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
	
	//Submit Save
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
			url : "signup/duplicate/phone",
			type : 'POST',
			timeout : 60000,
			data : JSON.stringify(user),
			dataType : 'json',
			dataType : "json",
			beforeSend: function(xhr) {
			    xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success: function(data) {
				if(data){
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