<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources/" var="resources" />
<spring:url value="/resources/template" var="template"/>
<spring:url value="/music" var="music"/>
<tag:layout tab="${target}">
 	<div class="generic-container">
		<div class="well lead">Music Registration Form</div>
	 	<form:form method="POST" modelAttribute="music" class="form-horizontal" style="margin: 5%;">
			<form:input type="hidden" path="id" id="id"/>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-2 col-sm-2 col-lg-2 control-lable" for="singer">Singer</label>
					<div class="col-md-10 col-sm-10 col-lg-10">
						<form:input type="text" path="singer" id="singer" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="singer" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-2 col-sm-2 col-lg-2 control-lable" for="title">Title</label>
					<div class="col-md-10 col-sm-10 col-lg-10">
						<form:input type="text" path="title" id="title" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="title" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-2 col-sm-2 col-lg-2 control-lable" for="lyrics">Lyrics</label>
					<div class="col-md-10 col-sm-10 col-lg-10">
						<form:input type="text" path="lyrics" id="lyrics" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="lyrics" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-2 col-sm-2 col-lg-2 control-lable" for="url">URL</label>
					<div class="col-md-10 col-sm-10 col-lg-10">
						<form:input type="text" path="url" id="url" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="url" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-2 col-sm-2 col-lg-2 control-lable" for="image">Image</label>
					<div class="col-md-10 col-sm-10 col-lg-10">
						<form:input type="text" path="image" id="image" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="image" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='${music }/list' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='${music }/list' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</tag:layout>