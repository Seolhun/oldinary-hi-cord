//This Page Default Variable
var root = "/tunner";
var csrfToken=$("#csrfToken").val();
var csrfHeader=$("#csrfHeader").val();

//---------------------------------------------------------------------달력 JS 시작---------------------------------------------------------------------
//전역변수 수정필요 - CancleBinding으로 인해 전역변수화;
var static_binding=[];
$(document).ready(function() {
	
	var static_patientResult={};
	//moment.min.js error 해결 function(지우면 안됨)
	moment.createFromInputFallback = function(config) {
		// unreliable string magic, or
		config._d = new Date(config._i);
	};
	
	//오늘 날짜 가져오기. (달력 연관)
    var dateObj = new Date();
	var year = dateObj.getFullYear();
	var month = dateObj.getMonth()+1;
	var day = dateObj.getDate();
	var today = moment(dateObj).format('YYYY-MM-DD');
	
    //환자 리스트와 진료 내역 Grid 초기호출
	pList(today);
	
	//설문지 가져오기.
	surveyJsGrid();
	
    //달력 프로세스
	/*Start --------------------------------------------------------------------*/
	$('#calendar').fullCalendar({
		monthNames: ["01","02","03","04","05","06","07","08","09","10","11","12"],
		year:year,
		month:month,
		date:day,
		dayOfMonthFormat: 'ddd DD/MM',
		selectable:{
            month:false
        },
		dayClick: function(date, allDay, jsEvent, view) {
        	var dateResult = date.format();

//        	//Date 클릭 이벤트
        	$('.patientDetail').empty();
        	$('.patientDetail').text("환자를 선택하세요.");
        	pList(dateResult);
	    },
	    eventClick: function (calEvent, jsEvent, view) {
	    	var dateResult = moment(calEvent.start._d).format('YYYY-MM-DD');
	    	
	    	//Date 클릭 이벤트
        	$('.patientDetail').empty();
        	$('.patientDetail').text("환자를 선택하세요.");
        	pList(dateResult);
        },
		editable: false,
		eventLimit: true, // allow "more" link when too many events
		eventSources: [
			{
				url: '/tunner/diagnosis/dateEvent', 
				type: 'POST',
				data: function() {
					var fecha = $('#calendar').fullCalendar('getDate');
					return {
						month: fecha._d.getMonth()+1,
						year: fecha._d.getFullYear(),
						start: 'start',
						end: 'end',
						title: 'title'
					}
				},
				error: function() {
					alert('카운트 이벤트 에러 발생');
				}
			}
		]
	});
	/*달력 End--------------------------------------------------------------------*/
	
	function pList(dateResult){
		//동적 환자 리스트 DataTable
	    $("#patientGrid").jsGrid({
	    	height : "400",
	    	width : "100%",
	    	selecting : true,
	    	autoload : true,
	    	filtering: true,
	        editing: true,
	        sorting : true,
        	paging : true,
            pagerContainer: null,
            pageIndex: 1,
            pageSize: 10,
            pageButtonCount: 15,
            pagerFormat: "Pages: {prev} {pages} {next} : {pageIndex} of {pageCount}",
            pagePrevText: "Prev",
            pageNextText: "Next",
            pageNavigatorNextText: "...",
            pageNavigatorPrevText: "...",
	        noDataContent : "환자 데이터가 존재하지 않습니다.",

	        rowClick: function(args) {
	            
	        	var item = args.item;
	        	var id = item.PATIENT_IDX;
        		static_patientResult=item;
        		
        		//선택한 인원 체크하기.
        		var $row = this.rowByItem(args.item);
        		//클릭되어있지 않으면 false, 클릭된것을 다시 누르면 true.
        		if($row.hasClass("highlighted")){
        			$row.toggleClass("highlighted");
        		} else {
        			$(".highlighted").toggleClass("highlighted");
        			$row.toggleClass("highlighted");
        		}
	        },
	    	controller : {
	    		loadData : function(filter) {
	                 var d = $.Deferred();
	                 $.ajax({
	    				type : "GET",
	    				contentType : "application/json; charset=utf-8",
	    				url : "/tunner/patient/listJSON", 
	    				dataType : "json",
	    				data: {
	     	                "DIAGNOSIS_CREATED_DATE" : dateResult
	     	            }
	    			}).done(function (response) {
	                    response = $.grep(response, function (item) {
                            return (!filter.PATIENT_KO_NAME || (item.PATIENT_KO_NAME === null ? "" : item.PATIENT_KO_NAME).toLowerCase().indexOf(filter.PATIENT_KO_NAME.toLowerCase()) > -1)
                                && (!filter.PATIENT_IDX || (item.PATIENT_IDX === null ? "" : item.PATIENT_IDX).toLowerCase().indexOf(filter.PATIENT_IDX.toLowerCase()) > -1)
                                && (!filter.PATIENT_GENDER || (item.PATIENT_GENDER === null ? "" : item.PATIENT_GENDER).toLowerCase().indexOf(filter.PATIENT_GENDER.toLowerCase()) > -1)
                                && (!filter.PATIENT_FAMILY_DR || (item.PATIENT_FAMILY_DR === null ? "" : item.PATIENT_FAMILY_DR).toLowerCase().indexOf(filter.PATIENT_FAMILY_DR.toLowerCase()) > -1)
                                && (!filter.PATIENT_BIRTH || (item.PATIENT_BIRTH === null ? "" : item.PATIENT_BIRTH).toLowerCase().indexOf(filter.PATIENT_BIRTH.toLowerCase()) > -1);
	                    });
	                    d.resolve(response);
	    			});
	    			return d.promise();
    			}
	    	},
	    	fields : [{
	    		name : "PATIENT_IDX",
	    		title: "No",
	    		type : "text",
	    		width : 30,
	    		filtering: false
	    	}, {
	    		name : "PATIENT_KO_NAME",
	    		title: "이름",
	    		type : "text",
	    		width : 60,
	    		align : "center"
	    	},  {
	    		name : "PATIENT_BIRTH",
	    		title: "생년월일",
	    		type : "text",
	    		width : 80,
	    		align : "center",
	    		itemTemplate: function(value, item) {
	                return moment(value).format('YYYY-MM-DD');
	    		}
	    	},  {
	    		name : "PATIENT_GENDER",
	    		title: "성별",
	    		type : "text",
	    		align: "center",
	    		width : 30
	    	},  {
	    		name : "PATIENT_FAMILY_DR",
	    		title: "담당의",
	    		type : "text",
	    		width : 60,
	    		align : "center"
	    	},  {
	    		name : "PATIENT_CREATED_DATE",
	    		title: "등록일",
	    		type : "date",
	    		width : 100,
	    		align : "center",
	    		itemTemplate: function(value, item) {
	                return moment(value).format('YYYY-MM-DD HH:mm');
	    		}
	    	}]
		});
	}
	
	//해당 병원의 설문지 그룹 출력 
	printGroup();
	
	//****설문지 리스트 가져오기
	function surveyJsGrid(result){
		$("#surveyJsGrid").jsGrid({
			height : "400",
			width : "100%",
			
			selecting : true,
			sorting : true,
			autoload : true,
		    
			paging : true,
		    pagerContainer: null,
		    pageIndex: 1,
		    pageSize: 10,
		    pageButtonCount: 15,
//		    pagerFormat: "Pages: {first} {prev} {pages} {next} {last}	{pageIndex} of {pageCount}",
		    pagerFormat: "Pages: {prev} {pages} {next}					{pageIndex} of {pageCount}",
		    pagePrevText: "Prev",
		    pageNextText: "Next",
		    pageFirstText: "First",
		    pageLastText: "Last",
		    pageNavigatorNextText: "...",
		    pageNavigatorPrevText: "...",
			noDataContent : "No Record Found",
			
			loadIndication : true,
			loadIndicationDelay : 500,
			loadMessage : "Please, wait...",
			loadShading : true,
			
	        rowClick: function(args) {
	        	var item=args.item;
	        	surveyAddInBinding(item);
	        },
			controller : {
				loadData : function(filter) {
					return $.ajax({
						type : "GET",
						contentType : "application/json; charset=utf-8",
						url : root+"/survey/list-json",
						dataType : "json",
					});
				}
			},
			fields : [{
				name : "surveyId",
				title: "번호",
				type : "number",
				align: "center",
				width: 50
			}, {
				name : "surveyType",
				title: "유형",
				type : "text",
				align: "center",
				width: 50
			},  {
				name : "surveyName",
				title: "이름",			
				type : "text",
				align: "center",
				width: 100
			},  {
				name : "surveyHowToSubject",
				title: "방법",
				type : "text",
				align: "center",
				width: 100
			}]
		});
	}
	
	//****설문지 묶음 만들기****   	    
	function surveyAddInBinding(item){
		//이름과 번호를 같이 묶어준다.
		var surveyBindingRefer={};
		surveyBindingRefer["surveyId"]=item.surveyId;
		surveyBindingRefer["surveyName"]=item.surveyName;
		
		//유효성 체크해준다.
		for(var i=0;i<static_binding.length;i++){
			if(static_binding[i].surveyId===surveyBindingRefer.surveyId){
				return swal("","중복되어있습니다.");	
			}
		}
		
		static_binding.push(surveyBindingRefer);
		
		var html="";
		html=	"<div class='col-sm-3' id='surveyIdDiv"+item.surveyId+"'>" +
					"<div class='margin-bottom-10' name='surveyIdInBinding'>"+item.surveyId+"</div>" +
					"<div class='margin-bottom-10' name='surveyNameInBinding'>"+item.surveyName+"</div>" +
					"<div class='margin-bottom-10' name='surveyBtnInBinding'><button class='btn btn-danger' onclick='cancleBinding("+item.surveyId+")'>취소</button></div>" +
				"</div>";
		$("#surveyBindingDiv").append(html);	
	}
	
	$("button[name=resetBinding]").click(function(){	
		resetBinding();
	});

	//묶은 설문지 진단에 묶어주기.
	$("button[name=addSurveyBinding]").click(function(){
		//프론트와 백엔드 처리 완료
		console.log("static_binding : "+static_binding);
		
		var surveyBinding={};
		surveyBinding["surveyBindingList"]=static_binding;
		surveyBinding["diagnosisIdxInSurveyBinding"]=static_patientResult.DIAGNOSIS_IDX;
		surveyBinding["surveyBindingPatientKoName"]=static_patientResult.PATIENT_KO_NAME;
		surveyBinding["surveyBindingPatientEmail"]=static_patientResult.PATIENT_EMAIL;
		console.log(static_patientResult.DIAGNOSIS_IDX);
		if(static_patientResult.DIAGNOSIS_IDX==undefined || static_patientResult.DIAGNOSIS_IDX<1){
			swal("","환자를 꼭 선택해주세요.","warning");
			return;
		}
		
		$.ajax({
			type : "POST",
			url : root + "/survey/binding/add",
			timeout : 600000,
			contentType : 'application/json',
			data : JSON.stringify(surveyBinding),
			dataType : "json",
			success : function(data) {
				if (data.result==="success") {
					console.log('Success');
					swal(""," 설문지 요청 성공.","success");
					resetBinding();
				} else {
					console.log('Fail');
					swal("","설문지를 묶음에 추가해주세요.","warning");
					return;
				}
			},
			error : function(e) {
				console.log('Error');
				return;
			}// end success
		});
	});
	
	//병원 별 설문지 묶음 그룹만들어주기 
	$("button[name=addSurveyGroup]").click(function(){
		var surveyBindingGroupName;
		surveyBindingGroupName=$("#surveyBindingGroupName").val();
		
		//그룹 추가시 설문지 묶음 유효성 체크
		console.log(static_binding.length);
		if(surveyBindingGroupName==null || surveyBindingGroupName.length<1){
			swal("","설문지 그룹 이름이 비어있습니다.", "warning");
			return;
		} else if(static_binding.length<1){
			swal("","설문지 묶음이 비어있습니다.", "warning");
			return;
		}
		
		$.ajax({
			type : "POST",
			url : root + "/survey/binding/add/group",
			timeout : 600000,
			contentType : 'application/json',
			data : JSON.stringify({
				"surveyBindingGroups": static_binding,
				//병원번호 넣기
				"surveyBindingGroupName" : surveyBindingGroupName
			}),
			dataType : "json",
			success : function(data) {
				if (data.result=="success") {
					console.log('Success');
					swal("","설문지를 그룹에 추가하였습니다.");
					
					//묶음비워주기
					resetBinding();
					
					//그룹추가 완료시 그룹리스트 출력;
					printGroup();
					
					//모달 close 및 reset + binding reset
					closeGroupModal();
					
				} else if(data.result=="invalid"){
					console.log('Invalid');
					swal("","이미 존재하는 그룹입니다.");
				} else {
					console.log('Fail');
					return;
				}
			},
			error : function(e) {
				console.log('Error');
				return;
			}// end success
		});
	});
	
	$("#closeGroupModal").click(function(){
		$("#surveyBindingGroupName").val("");
	});
	
	function closeGroupModal(){
		$("#closeGroupModal").click();
		$("#surveyBindingGroupName").val("");
	}
	
	//****설문지 묶음지 초기화****
	function resetBinding(){
		//static_binding안에 있는 surveyId값으로 생성된 surveyIdDiv(설문지 아이디값)를 찾아서 지운다.
		for(var i=0;i<static_binding.length;i++){
			$("#surveyIdDiv"+static_binding[i].surveyId).remove();	
		}
		static_binding=[];
	}
	
	//환자 조회 팝업창 click event
	$('.patientSearch').click('click', function () {
		var w = 800; //팝업창의 너비
		var h = 600; //팝업창의 높이
		var LeftPosition=200;
		var TopPosition=(screen.height-h)/2;
		var url = "/tunner/patient/search";
		
//		window.open("http://www.naver.net", "신규 환자 등록", "width=500, height=600, toolbar=no, menubar=no, scrollbars=no, resizable=yes" );
		window.name = "parentOpener";
		window.open(url, "_blank",
				"width="+w+",height="+h+",top="+TopPosition+",left="+LeftPosition+", toolbar=no, menubar=no, scrollbars=no");
	});
});

//onclick 작동으로 클로져 안에 넣을시 인식 실패.
//****Bind 창에 설문지 추가된것 단일 취소****
function cancleBinding(surveyId){
	static_binding.splice($.inArray(surveyId, static_binding),1);
	$("#surveyIdDiv"+surveyId).remove();
};

//Bind창에 있는 것을 초기화해주고 다시 출력한다.
function printGroup(){
	var html="",i,j;
	$("#surveyInGroup").empty();
	$.ajax({
		type : "GET",
		url : root + "/survey/binding/print/group",
		timeout : 600000,
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			for(i=0;i<data.length;i++){
				html+="<div class='col-sm-12'>";
				html+=(i+1)+". "+data[i].surveyBindingGroupName+" : ";
				for(j=0;j<data[i].surveyBindingGroups.length;j++){
					html+=data[i].surveyBindingGroups[j].SURVEY_BINDING_GROUP_SURVEY_NAME;
					if(j+1!=data[i].surveyBindingGroups.length){
						html+=" >> ";	
					} else {
						html+="&nbsp;&nbsp;&nbsp;<button class='btn btn-primary' name='bindGroupSurvey' value='"+data[i].surveyBindingGroupId+"' onclick='bindGroupSurvey("+data[i].surveyBindingGroupId+")'>추가</button> ";
						html+="&nbsp;&nbsp;&nbsp;<button class='btn btn-danger' name='removeGroupSurvey' value='"+data[i].surveyBindingGroupId+"' onclick='removeGroupSurvey("+data[i].surveyBindingGroupId+")'>삭제</button> ";
					}
				}
				html+="</div>";					
			}
			$("#surveyInGroup").append(html);
		},
		error : function(e) {
			console.log('Error');
			return;
		}// end success
	});
}

//그룹 삭제시 삭제를 실행한다.
function removeGroupSurvey(surveyBindingGroupId){
	$.ajax({
		type : "GET",
		url : root + "/survey/binding/remove/group",
		timeout : 600000,
		contentType : 'application/json',
		data : {
			"surveyBindingGroupId" : surveyBindingGroupId
		},
		dataType : "json",
		success : function(data) {
			if (data.result==="success") {
				console.log('Success');
				swal(""," 그룹 삭제 완료되었습니다.","success");
				printGroup()
			} else if(data.result==="invalid") {
				console.log('invalid');
				swal("","그룹 삭제 권한이 없습니다..","warning");
				return;
			} else {
				console.log('Fail');
				swal("","그룹 삭제 실패.","danger");
				return;
			}
		},
		error : function(e) {
			console.log('Error');
			return;
		}// end success
	});
};

//Group에 추가를 누를 시 바인드 창에 뿌려주는 것.
function bindGroupSurvey(surveyBindingGroupId){
	$.ajax({
		type : "GET",
		url : root + "/survey/binding/bind/group",
		timeout : 600000,
		contentType : 'application/json',
		data : {
			"surveyBindingGroupId" : surveyBindingGroupId
		},
		dataType : "json",
		success : function(data) {
			data.forEach(function(data, status, index){
				printGetGroup(data);	
			});
		},
		error : function(e) {
			console.log('Error');
			return;
		}// end success
	});
};

//binding에 프린트해주는 것 bindGroupSurvey의 데이터를 뿌려주는 역할.
function printGetGroup(item){
	//이름과 번호를 같이 묶어준다.
	var surveyBindingRefer={}, surveyId, surveyName;
	surveyId=item.SURVEY_BINDING_GROUP_SURVEY_ID;
	surveyName=item.SURVEY_BINDING_GROUP_SURVEY_NAME;
	
	surveyBindingRefer["surveyId"]=surveyId;
	surveyBindingRefer["surveyName"]=surveyName;
	
	//유효성 체크해준다.
	for(var i=0;i<static_binding.length;i++){
		if(static_binding[i].surveyId===surveyBindingRefer.surveyId){
			return swal("","중복되어있습니다.");	
		}
	}
	
	static_binding.push(surveyBindingRefer);
	
	var html="";
	html=	"<div class='col-sm-3' id='surveyIdDiv"+surveyId+"'>" +
				"<div class='margin-bottom-10' name='surveyIdInBinding'>"+surveyId+"</div>" +
				"<div class='margin-bottom-10' name='surveyNameInBinding'>"+surveyName+"</div>" +
				"<div class='margin-bottom-10' name='surveyBtnInBinding'><button class='btn btn-danger' onclick='cancleBinding("+surveyId+")'>취소</button></div>" +
			"</div>";
	$("#surveyBindingDiv").append(html);	
}

