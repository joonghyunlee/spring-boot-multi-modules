var messageArea = document.querySelector('.msg_history');
 
var stompClient = null;
var username = null;
  
 
function connect() {
	username = $('.chat_username').text().trim();
      
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
 
    stompClient.connect({}, onConnected, onError);
}
 
// Connect to WebSocket Server.
connect();
 
function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/publicChatRoom', onMessageReceived);
 
    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )
}
 
 
function onError(error) {
	alert('Could not connect to WebSocket server.\nPlease refresh this page to try again!')
}
 
 
function sendMessage() {
    var messageContent = $('.write_msg').val().trim();
    console.log(messageContent);
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageContent,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        $('.write_msg').val('');
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
 
    if (message.type == 'CHAT' && message.sender == username) {
    	messageElement = createSendedMessageElement(message)
    } else if (message.type == 'CHAT' && message.sender != username) {
    	messageElement = createRecievedMessageElement(message)
    } else if (message.type == 'JOIN' || message.type == 'LEAVE') {
    	messageElement = createSystemMessageElement(message);
    }
 
    messageArea.append(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function createRecievedMessageElement(message) {
	var messageElement = document.createElement('div');
	messageElement.classList.add('incoming_msg');
	
	var incomingMessageImage = $('<div>', {
		class: 'incoming_msg_img'
	}).appendTo(messageElement);
	
	$('<img>', {
		src: 'https://ptetutorials.com/images/user-profile.png',
		alt: message.sender
	}).appendTo(incomingMessageImage);
	
	var receivedMessage = $('<div>', {
		class: 'received_msg'
	}).appendTo(messageElement);
	$('<p>', {
		text: message.sender
	}).appendTo(receivedMessage);
	
	var receivedMessageText = $('<div>', {
		class: 'received_withd_msg'
	}).appendTo(receivedMessage);
	
	$('<p>', {
		text: message.content
	}).appendTo(receivedMessageText);
	
	$('<span>', {
		class: 'time_date',
		text: getCurrentTime()
	}).appendTo(receivedMessage);
	
	return messageElement;
}

function createSendedMessageElement(messageText) {
	var messageElement = document.createElement('div');
	messageElement.classList.add('outgoing_msg');

	var messageContent = $('<div>', {
		class: 'sent_msg'
	}).appendTo(messageElement);
	
	$('<p>', {
		text: messageText.content
	}).appendTo(messageContent);
	
	$('<span>', {
		class: 'time_date',
		text: getCurrentTime()
	}).appendTo(messageContent);

	return messageElement;
}

var months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];

function getCurrentTime() {
	var today = new Date();
	var secondsSinceEpoch = (today.getTime() / 1000) | 0;
	var secondsInDay = ((secondsSinceEpoch % 86400) + 86400) % 86400;
	var seconds = secondsInDay % 60;
	var minutes = ((secondsInDay / 60) | 0) % 60;
	var hours = (secondsInDay / 3600) | 0;
	
	var dd = today.getDate();
	var mm = months[today.getMonth()];
		
	return hours + (minutes < 10 ? ":0" : ":")
		+ minutes + (seconds < 10 ? ":0" : ":")
		+ seconds + "     |      "
		+ mm + " " + dd;
}

function createSystemMessageElement(message) {
	var messageElement = document.createElement('div');
	messageElement.classList.add('system_message');
	
	if (message.type == 'JOIN') {
		$('<p>', {
			text: message.sender + ' joined.'
		}).appendTo(messageElement);
	} else if (message.type == 'LEAVE') {
		$('<p>', {
			text: message.sender + ' left.'
		}).appendTo(messageElement);
	}
	
	return messageElement;
}

$('.send_msg_form').submit(sendMessage);