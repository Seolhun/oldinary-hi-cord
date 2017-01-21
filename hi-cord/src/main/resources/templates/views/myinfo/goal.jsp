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
				<h1>목표</h1>
				<p>
					We Can do it
				</p>
			</div>
		</div>
	</div>
</div>
<!--=== Parallax Quote ===-->
<div class="parallax-quote parallaxBg">
	<div class="container">
		<div class="parallax-quote-in">
			<p>Just Try to be better than me <span class="color-green">you can design</span> everything. <br> Just Believe It.</p>
			<small>- HtmlStream -</small>
		</div>
	</div>
</div>
<!--=== End Parallax Quote ===-->
<!--=== End Call To Action ===-->
<div class="container content-xs">
		<div class="row">
			<div class="col-sm-12">
				<div class="row service-box-v1">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 no-margin-bottom">
						<div class="service-block service-block-default">
							<div class="">
								<img alt="" src="${resources}/img/me.jpg" class="myprofile-img">
							</div>
						</div>
						<div class="service-block service-block-default">
							<div class="">
								<img alt="" src="${resources}/img/me.jpg" class="myprofile-img">
							</div>
						</div>
					</div>
					
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
						<div class="service-block service-block-default myprofile">
							<i class="icon-lg rounded-x icon icon-badge"></i>
							<h2 class="heading-sm"><strong>History &amp; Profile</strong></h2>
							<br>
							<ul class="list-unstyled myprofile-ul">
								<li>한국 지도자 육성 장학재단 41기</li>
								<li>용인대학교 졸업(체육, 경영)</li>
								<li>ROTC(학군단) 51기</li>
								<li>설빙 1기 마케팅 공모전 장려상 수상</li>
								<li>경산 국제 콘텐츠 쇼 본선 진출()</li>
								<li>마이다스 아이티 사관학교 1기</li>
								<li><a href="http://imedisyn.com">(주)아이메디신</a> 연구원 </li>
								<li>대학원</li>
							</ul>
						</div>
					</div>
					<div class="col-sm-12">
						<hr>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="service-block service-block-default no-margin-bottom">
							<i class="icon-lg rounded-x icon-line icon-layers"></i>
							<h2 class="heading-sm">Project &amp; Skill</h2>
							<p><a class="skill">#Java</a><a class="skill">#Spring</a><a class="skill">#Hibernate</a><a class="skill">#Java Script</a><a class="skill">#Ajax</a><a class="skill">#JQuery</a>
							<br><a class="skill">#Oracle</a><a class="skill">#MariaDB</a><a class="skill">#MongoDB</a><a class="skill">#CentOS</a><a class="skill">#NginX</a><a class="skill">#Tomcat</a></p>
							<ul class="list-unstyled">
								<li>Git Hub | <a href="https://github.com/Seolhun">https://github.com/Seolhun</a></li>
								<li>&nbsp;&nbsp;&nbsp;Web&nbsp;&nbsp;&nbsp;| <a href="http://imedisyn.com">http://imedisyn.com</a></li>
								<li>&nbsp;&nbsp;&nbsp;Web&nbsp;&nbsp;&nbsp;| <a href="http://imedisyn.com/isyncbrain">http://imedisyn.com/isyncbrain</a></li>
								<li>&nbsp;&nbsp;&nbsp;Web&nbsp;&nbsp;&nbsp;| <a href="${po}/project/3">SIST Third Project</a></li>
								<li>&nbsp;&nbsp;&nbsp;Web&nbsp;&nbsp;&nbsp;| <a href="${po}/project/2">SIST Two Project</a></li>
								<li>&nbsp;&nbsp;&nbsp;Java&nbsp;&nbsp;| <a href="${po}/project/1">SIST First Project</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
   	</div>
</tag:layout>