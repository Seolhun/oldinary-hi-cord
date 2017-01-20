var csrfToken = document.getElementById("csrfToken").content;
var csrfHeader = document.getElementById("csrfHeader").content;
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
	var checked = document.getElementsByName('signAgree');
	for (var i = 0; i < checked.length; i++) {
		if (!checked[i].checked) {
			checked[i].focus();
			return false;
		}
	}
	return true;
}

$('#email').on("keyup",function(){
	var inputEmail = $("#email").val();
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

$('#phone').on("keyup",function(){
	var inputPhone = $("#phone").val();
	if (!validatePhone(inputPhone)) {
		$("#phoneMsg").text(inputPhone + " is not valid");
		$("#phoneMsg").css("color", "red");
		$("#phoneDupl").prop("disabled", true);
		return;
	} else {
		$("#phoneMsg").text(inputPhone + " is valid");
		$("#phoneMsg").css("color", "blue");
		$("#phoneDupl").prop("disabled", false);
	}
});

//Submit Save
$('#emailDupl').click(function(){
	var inputEmail = $("#email").val();
	if (!validateEmail(inputEmail)) {
		if(inputEmail.length==0){
			$("#emailMsg").text("Email is empty");
			$("#emailMsg").css("color", "red");
			return;
		}
		$("#emailMsg").text(inputEmail + " is not valid");
		$("#emailMsg").css("color", "red");
		return;
	}
	var data = {}
	data["email"] = inputEmail;
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
				$("#emailMsg").text(inputEmail + " could be used");
				$("#emailMsg").css("color", "blue");				
			} else {
				$("#emailMsg").text(inputEmail + " could't be used");
				$("#emailMsg").css("color", "red");	
			}
		},
		error : function(e){
			swal("Error","이메일 중복확인이 올바르게 작동하지 않았습니다.");
		}
	});	
});

$('#phoneDupl').click(function(){
	var inputPhone = $("#phone").val();
	if (!validatePhone(inputPhone)) {
		if(inputPhone.length==0){
			$("#phoneMsg").text("Phone is empty");
			$("#phoneMsg").css("color", "red");
			return;
		}
		$("#phoneMsg").text(inputPhone + " is not valid");
		$("#phoneMsg").css("color", "red");		
		return;
	} 
	var data = {}
	data["phone"] = inputPhone;
	data["csrfToken"] = csrfToken;
	data["csrfHeader"] = csrfHeader;
	$.ajax({
		url : "signup/duplicate/phone",
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
				$("#phoneMsg").text(inputPhone + " could be used");
				$("#phoneMsg").css("color", "blue");				
			} else {
				$("#phoneMsg").text(inputPhone + " could't be used");
				$("#phoneMsg").css("color", "red");	
			}
		},
		error : function(e){
			swal("Error","핸드폰 중복확인이 올바르게 작동하지 않았습니다.");
		}
	});	
});

$("input[name=saveUser]").click(function(){
	var emailDupl=document.getElementById('emailDupl');
	var phoneDupl=document.getElementById('phoneDupl');
	var email=document.getElementById('email');
	var phone=document.getElementById('phone');
	if(!agreeCheck()){
		return;
	} else if(emailDupl.disabled){
		email.focus();
		return;
	} else if(phoneDupl.disabled){
		phone.focus();
		return;
	}
	document.getElementById("formSignup").submit();
});
