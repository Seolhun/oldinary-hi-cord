<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources/" var="RESOURCES" />
<spring:url value="/resources/template" var="template" />
<spring:url value="/po" var="po" />
<tag:layout tab="${target}">
	<!--=== Call To Action ===-->
	<div class="call-action-v1 bg-color-light">
		<div class="container">
			<div class="call-action-v1-box">
				<div class="call-action-v1-in">
					<p>Unify creative technology company providing key digital
						services and focused on helping our clients to build a successful
						business on web and mobile.</p>
				</div>
				<sec:authorize access="hasRole('SUPERADMIN')">
					<div class="call-action-v1-in inner-btn page-scroll">
						<a href="${po }/crawl/add" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">Add New Music</a> 
						<a href="${po }//get" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">Get Music Rank</a>
					</div>
				</sec:authorize>
			</div>
		</div>
	</div>
	<!--=== End Call To Action ===-->
	<div class="container content-xs">
		<div class="row">
			<div class="col-sm-12">
								
			</div>
		</div>
	</div>
</tag:layout>