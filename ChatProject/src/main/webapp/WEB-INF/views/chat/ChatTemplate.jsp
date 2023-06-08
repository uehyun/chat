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
						<h4>Inbox</h4>
					</div>
					<div class="messages-inbox">
						<ul>
							<c:forEach items="${chatList }" var="chat">
								<li class="unread">
									<a href="/chat/chatMessage?chatId=${chat.chatId }">
										<div class="message-avatar">
											<!-- 상대방 프로필 사진 -->
											<c:if test="${chat.userId eq userId }">
												<img src="../resources/upload/${chat.hostPicture }" alt="" />
											</c:if>
											<c:if test="${chat.hostId eq userId }">
												<img src="../resources/upload/${chat.userPicture }" alt="" />
											</c:if>
										</div>
	
										<div class="message-by">
											<div class="message-by-headline">
												<h5>대화 상대방 이름<i>Unread</i></h5>
												<span>몇시간 전인지 입력란</span>
											</div>
											<p>내용입력란</p>
										</div>
									</a>
								</li>
							</c:forEach>
<!-- 							<li class="unread"> -->
<!-- 								<a href="/chat/chatMessage"> -->
<!-- 									<div class="message-avatar"><img src="http://www.gravatar.com/avatar/00000000000000000000000000000000?d=mm&amp;s=70" alt="" /></div> -->

<!-- 									<div class="message-by"> -->
<!-- 										<div class="message-by-headline"> -->
<!-- 											<h5>John Doe <i>Unread</i></h5> -->
<!-- 											<span>4 hours ago</span> -->
<!-- 										</div> -->
<!-- 										<p>Hello, I want to talk about your great listing! Morbi velit eros, sagittis in facilisis non, rhoncus posuere ultricies...</p> -->
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</li> -->
						</ul>
					</div>
				</div>

				<!-- Pagination -->
				<div class="clearfix"></div>
				<div class="pagination-container margin-top-30 margin-bottom-0">
					<nav class="pagination">
						<ul>
							<li><a href="#" class="current-page">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#"><i class="sl sl-icon-arrow-right"></i></a></li>
						</ul>
					</nav>
				</div>
				<!-- Pagination / End -->

			</div>

			<!-- Copyrights -->
			<div class="col-md-12">
				<div class="copyrights">© 2023 Travel Maker</div>
			</div>
		</div>

	</div>
	<!-- Content / End -->
