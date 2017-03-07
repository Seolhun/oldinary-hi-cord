// 메세지 알림창.
$(document).ready(function(){
	//WebSocket서버 연결
	MessageModule.connectMessage();
	//Ajax에서 결과값가져와서 Message창에 뿌려주기.
	var $popover=$('#openPopover').popover({
		placement: 'left',
		template: '<div class="alertPopOver popover"><div class="popover-title"></div><div class="popover-content" id="popover-content"></div></div></div>',
		html: '알림창',
		title : '<span class="text-info">알림창</span>'+
				'<button type="button" id="closePopover" class="close">&times;</button>',
		content : MessageModule.getMessageList()
	}).on('shown.bs.popover', function () {
		var $popup = $(this);
		$(this).next('.popover').find('button.close').click(function (e) {
			$popup.popover('hide');
		});
	})
});

//Message Module
var MessageModule=(function(){
	var stompClient = null;
	//private Method
	var _getMessageList = function (){
		var innerHtml="";
		$.ajax({
			async : false,
			global : false,
			type : "GET",
			url : "/tunner/message/from/user",
			success : function(response) {
				if(response.length==0){
					return innerHtml+='<div class="row margin-top-10 margin-left-5">알림이 없습니다.</div>';
				}
				response.forEach(function(message, status, index){
					console.log();
					if(message.messageReadFlag=='N'){
						innerHtml+='<div class="row margin-bottom-10"><a class="untilNotRead" href="/tunner/message/move/'+message.messageIdx+'"><span class="col-sm-9">'+message.messageContent+'</span><span class="col-sm-3 text-center">'+_getTimeBetweenObjectAndDb(message.messageCreatedDate)+'</span></a></div>';						
					}else {
						innerHtml+='<div class="row margin-bottom-10"><a class="moveMessageHref" href="/tunner/message/move/'+message.messageIdx+'"><span class="col-sm-9">'+message.messageContent+'</span><span class="col-sm-3 text-center">'+_getTimeBetweenObjectAndDb(message.messageCreatedDate)+'</span></a></div>';
					}
				});
			},
			error : function(e) {
				
			}
		})
		return innerHtml;
	};
	
	var _getTimeBetweenObjectAndDb = function (dbDateValue){
		var currentTime = new Date();
		currentTime= _customDateformat(currentTime, 'yyyy-MM-dd-hh-mm-ss');
		
		//convert DB Timestamp value to JS Date 
		var dbTime = new Date(dbDateValue);
		dbTime=_customDateformat(dbTime, 'yyyy-MM-dd-hh-mm-ss');
		
		var valuableTime=__getValueBetweenTimes(currentTime, dbTime);
		
		return valuableTime;
		
		function __getValueBetweenTimes(currentTime, oldTime) {
			var x=currentTime.split("-");
			var y=oldTime.split("-");
			var timeType=["년 전","월 전","일 전","시간 전","분 전", "초 전"];
			for (var i=0;i<x.length;i++){
				var time=x[i]-y[i];
				if(time>0){
					return time+timeType[i];
				}
			}
			return "방금 전";
		}
	};
	
	//Build Date Format
	var _customDateformat = function(date, format) {
		//currentTime build timeObject
		var z = {
			M : date.getMonth() + 1,
			d : date.getDate(),
			h : date.getHours(),
			m : date.getMinutes(),
			s : date.getSeconds()
		};
		
		//timeObject will be timeFormat I want.
		format = format.replace(/(M+|d+|h+|m+|s+)/g, function(v) {
			return ((v.length > 1 ? "0" : "") + eval('z.' + v.slice(-1))).slice(-2)
		});
		
		//Year of timeFormat will be changed. 
		return format.replace(/(y+)/g, function(v) {
			return date.getFullYear().toString().slice(-v.length)
		});
	}
	
	//private Method
	var _showGetMessage = function (message){
		var innerHtml="";
			if(message.messageReadFlag=='N'){
				innerHtml+='<div class="row margin-bottom-10"><a class="untilNotRead" href="/tunner/message/move/'+message.messageIdx+'"><span class="col-sm-9">'+message.messageContent+'</span><span class="col-sm-3 text-center">'+_getTimeBetweenObjectAndDb(message.messageCreatedDate)+'</span></a></div>';						
			}else {
				innerHtml+='<div class="row margin-bottom-10"><a class="moveMessageHref" href="/tunner/message/move/'+message.messageIdx+'"><span class="col-sm-9">'+message.messageContent+'</span><span class="col-sm-3 text-center">'+_getTimeBetweenObjectAndDb(message.messageCreatedDate)+'</span></a></div>';
			}
		$("#popover-content").prepend(innerHtml);
		
		//Last Message Remove		
		$('.popover-content').children().last().remove();
		
		//Popover의 새로운 메세지리스트 출력하기.
		var popover = $('#openPopover').data('bs.popover');
	    popover.options.content = _getMessageList();
	}
	
	//public Method
	var sendMessage = function(){
		var message = {}, messageLogName;
		messageToUser = $('#loginUser').html();
		
		console.log(messageToUser);
		
		message['createdBy'] = messageToUser;
		
		stompClient.send("/hi-cord/whisper-user", {}, JSON.stringify(message));
	}
	
	var getMessageList = function(){
		return _getMessageList();
	}
	
	var connectMessage = function(){
		var url = 'http://' + window.location.host + '/message';
	
		/* Stomp Client Created */;
		var socket = new SockJS(url);

		/* Stomp Client Created */;
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			stompClient.subscribe("/user-message/queue/whisper-message", function(calResult) {
				console.log(calResult);
				console.log("subscribe 정상 작동");
				_showGetMessage(JSON.parse(calResult.body));
			});
		});
	}
	
	return {
		connectMessage : connectMessage,
		getMessageList : getMessageList,
		sendMessage : sendMessage
	};
})();