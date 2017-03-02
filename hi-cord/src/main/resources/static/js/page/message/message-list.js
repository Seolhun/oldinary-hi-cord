var csrfToken = document.getElementById("csrfToken").content;
var csrfHeader = document.getElementById("csrfHeader").content;

var stompClient = null;
function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('chatDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('chatRes').innerHTML = '';
}

function connect() {
//	var url='https://'+ window.location.host + '/imedisyn/message';
//	var url='http://'+ window.location.host + '/message';
	/* Stomp Client Created */;
	
    var socket = new SockJS('/gs-guide-websocket');
	/* Stomp Client Created */;
	stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        //Value of @SendTo in Controller
        stompClient.subscribe('/queue/message', function(calResult){
        	showResult(JSON.parse(calResult.body));
        });
    });
}

function disconnect() {
    stompClient.disconnect();
    setConnected(false);
    console.log("Disconnected");
}

/* 설정부분에서의 application-destination-prefix 부분 */
function sendText() {
	var text = document.getElementById('text').value;
    //Message Broker + MessageMapping -> SendTo (subscribe);
    stompClient.send("/app/send", {}, JSON.stringify({ 'text': text }));
    document.getElementById('text').value = '';
    document.getElementById('text').focus();
}

function showResult(message) {
    var response = document.getElementById('chatRes');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message.message));
    response.appendChild(p);
}