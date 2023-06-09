<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div class="dashboard-content">

		<!-- Titlebar -->
		<div id="titlebar">
			<div class="row">
				<div class="col-md-12">
					<h2>Messages</h2>
					<!-- Breadcrumbs -->
					<nav id="breadcrumbs">
						<ul>
							<li><a href="#">Home</a></li>
							<li><a href="#">Dashboard</a></li>
							<li>Messages</li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
		
		<div class="row">
			<!-- Listings -->
			<div class="col-lg-12 col-md-12">
				<div class="messages-container margin-top-0">
					<div class="messages-headline">
						<h4>Kathy Brown</h4>
						<a href="#" class="message-action"><i class="sl sl-icon-trash"></i> Delete Conversation</a>
					</div>

					<div class="messages-container-inner">
						<!-- Messages -->
						<div class="messages-inbox">
							<ul>
								<c:forEach items="${chatList }" var="chat">
								<li class="unread">
									<a href="/chat/chatDetail?chatId=${chat.chatId }">
										<div class="message-avatar">
											<!-- 상대방 프로필 사진 -->
											<c:if test="${chat.userId eq userId }">
												<img src="${pageContext.request.contextPath}/resources/upload/${chat.hostPicture }" alt="" />
											</c:if>
											<c:if test="${chat.hostId eq userId }">
												<img src="${pageContext.request.contextPath}/resources/upload/${chat.userPicture }" alt="" />
											</c:if>
										</div>
	
										<div class="message-by">
											<c:if test="${chat.userId eq userId }">
												<div class="message-by-headline">
													<h5>${chat.hostId }<i>Unread</i></h5>
													<span>몇시간 전인지 입력란</span>
												</div>
												<p>최근 내용입력란</p>
											</c:if>
											<c:if test="${chat.hostId eq userId }">
												<div class="message-by-headline">
													<h5>${chat.userId }<i>Unread</i></h5>
													<span>몇시간 전인지 입력란</span>
												</div>
												<p>최근 내용입력란</p>
											</c:if>
										</div>
									</a>
								</li>
							</c:forEach>
							</ul>
						</div>
						<!-- Messages / End -->

						<!-- Message Content -->
						<div class="message-content">
							<div id="messageTextArea"></div>

							<!-- Reply Area -->
							<div class="clearfix"></div>
							<div class="message-reply">
								<textarea cols="40" rows="3" placeholder="Your Message" id="chatContent"></textarea>
								<button class="button" id="send">Send Message</button>
							</div>
							
						</div>
						<!-- Message Content -->
					</div>
				</div>
			</div>

			<!-- Copyrights -->
			<div class="col-md-12">
				<div class="copyrights">© 2021 Listeo. All Rights Reserved.</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
$(function() {
	let webSocket = null;
    user = "${userId}";		// 현재 세션 저장
    connect();
    
	$("#send").on("click", sendMessage);	// 채팅 버튼 누르면 메시지 보내기
    $("#chatContent").on("keydown", enter);	// 텍스트 박스에 키가 눌리면 enter함수 실행
});

function connect() {
	webSocket = new WebSocket("ws://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/echo");
  
	webSocket.onopen = function(message) {
		
	    let key = {		// 접속 초기 데이터 작성
		    chatId : "${chatId}",
		    userId : user,
		    state : 0
	    };
    
    	webSocket.send(JSON.stringify(key));	// 접속 메시지 보내기
    };
    
  	webSocket.onclose = function(message) {};	// 닫힐 때 메세지
	webSocket.onerror = function(message) {};	// 에러 시 메세지
  
	webSocket.onmessage = function(message) {	// 서버로 부터 메시지가 오면 실행되는 이벤트
	  
    let messageTextArea = document.getElementById("messageTextArea");	// 채팅 내용 박스 객체 취득
    messageTextArea.innerHTML += message.data;	// 데이터 작성

		$('.message-bubble').each(function() {	// 모든 message-bubble div 요소 선택
			
			var userId = $(this).data('userid');	// 현재 요소의 data-userId 값 가져오기
		
		    if (userId == user) {	// data-userId 값이 세션 값과 일치하는 경우 클래스 변경
				$(this).addClass(' me');
			}
		});
	};
}

// 메시지 보내기 함수
function sendMessage() {
	let message = document.getElementById("chatContent");	// 텍스트 박스 객체 취득
	
	let key = {		// 메시지 전송 객체 만들기
	    chatId : "${chat.chatId}",
	    userId : user,
	    state : 1, // state는 1
	    value : message.value // 메시지 내용
	}
	
	webSocket.send(JSON.stringify(key));	// 메시지 보내기
	message.value = "";		// 텍스트 박스 초기화
}

function enter() {		// 텍스트 박스에서 엔터키가 오면 메시지 전송
	if (event.keyCode === 13) {
		sendMessage();
		return false;
	}
	return true;
};
</script>