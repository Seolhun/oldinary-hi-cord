<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources/" var="resources" />
<spring:url value="/resources/template" var="template"/>
<spring:url value="/myinfo" var="myinfo"/>
<spring:url value="/po" var="po"/>
<tag:layout tab="${target}">
<!--=== Call To Action ===-->
<div class="call-action-v1 bg-color-light">
	<div class="container">
		<div class="call-action-v1-box">
			<div class="call-action-v1-in main-head">
				<h1>가치관</h1>
				<p>개인적으로 지향하는 가치관을 소개하는 공간입니다.<br>
					가치관의 영향을 준 인물과 그의 인용구를 통해 개인적인 가치관을 나누고자 합니다.
				</p>
			</div>
		</div>
	</div>
</div>
<!--=== End Call To Action ===-->
<div class="container content-xs">
	<div class="row">
		<div class="col-sm-12">
			<div class="row service-box-v1">
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
					<div class="service-block service-block-default">
						<div class="">
							정직<br>Stay Foolish
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
					<div class="service-block service-block-default">
						<div class="">
							열정<br>Stay Hungry					
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
					<div class="service-block service-block-default">
						<div class="">
							책임<br>Follow your heart
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-12">
			<div class="row service-box-v1">	
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 no-margin-bottom">
					<div class="service-block service-block-default">
						<div class="">
							<img alt="" src="${resources}/img/jobs.jpg" class="attitude-img">
						</div>
					</div>
				</div>
				<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
					<div class="service-block service-block-default no-margin-bottom">
						<i class="icon-lg rounded-x icon-line icon-layers"></i>
						<br>
						<h2 class="heading-sm">정직 &amp; Stay Foolish</h2>
							<p>
								You have to trust in something — your gut, destiny, life, karma, whatever. This approach has never let me down, and it has made all the difference in my life.
							</p>
						<h2 class="heading-sm">열정 &amp; Stay Hungry</h2>
							<p>
								You’ve got to find what you love. And that is as true for your work as it is for your lovers. Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work.
							</p>	
						<h2 class="heading-sm">책임 &amp; Follow your heart</h2>
							<p>
								Remembering that you are going to die is the best way I know to avoid the trap of thinking you have something to lose. You are already naked. There is no reason not to follow your heart.
							</p>
					</div>
				</div>
			</div>
			<hr>
		</div>
	</div>
</div>
</tag:layout>