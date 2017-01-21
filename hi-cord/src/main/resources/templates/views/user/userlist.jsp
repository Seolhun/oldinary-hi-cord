<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources/" var="resources" />
<spring:url value="/admin" var="admin"/>
<spring:url value="/signup" var="signup"/>
<tag:layout tab="${target}">
<div class="call-action-v1 bg-color-light">
	<div class="container">
		<div class="call-action-v1-box">
			<div class="call-action-v1-in">
				<p>Unify creative technology company providing key digital services and focused on helping our clients to build a successful business on web and mobile.</p>
			</div>
			<sec:authorize access="hasRole('SUPERADMIN')">
				<div class="call-action-v1-in inner-btn page-scroll">
			 		<a href="${signup}" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">Add New User</a>
		 		</div>
		 	</sec:authorize>
		</div>
	</div>
</div>
<div class="container content-xs">
	<div class="row">
		<div class="col-sm-12">
			<div>
				<select class="select" name="roleType">
					<option label="-- Role --">
					<c:forEach items="${userProfile}" var="i">
						<option value="${i}" onclick="allWork(${i});">${i}</option>
					</c:forEach>
				</select>
				<select class="select" name="stateType">
					<option label="-- State --">
					<c:forEach items="${state}" var="i">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>
				<button type="button" class="btn-u btn-u-block rounded" id="allSubmit">Submit</button>
			</div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
		      			<th><input type="checkbox" id="allCheck"></th>
				        <th>No.</th>
				        <th>Email.</th>
				        <th>Nickname.</th>
				        <th>State.</th>
				        <th>Role.</th>
				        <th>Email Check</th>
				        <sec:authorize access="hasRole('ADMIN') or hasRole('SUPERADMIN')">
				        	<th>Edit.</th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN') or hasRole('SUPERADMIN')">
				        	<th>Delete.</th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><input type="checkbox" name="check" value="${user.id}"></td>
						<td>${user.id}</td>
						<td>${user.email}</td>
						<td>${user.nickname}</td>
						<td>${user.state}</td>
						<td><c:forEach items="${user.userProfiles}" var="i">${i.type }<br></c:forEach></td>
						<td>${user.checkemail}</td>
						<td><a href="${admin }/edit-${user.email}" class="btn btn-default custom-width rounded">Edit</a></td>
				        <c:choose>
				        	<c:when test="${user.state.equals('Active')}">
				        		<td><a href="${admin }/up-${user.email}?s=d" class="btn-u btn-u-red custom-width rounded confirm">Delete</a></td>
				        	</c:when>
				        	<c:otherwise>
				        		<td><a href="${admin }/up-${user.email}?s=a" class="btn-u btn-u-dark-blue custom-width rounded" id="confirm">Active</a></td>
				        	</c:otherwise>
				        </c:choose>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
	    	<form:form method="GET" class="form-horizontal" action="${admin}/list">
		   		<div style="text-align: center">
		   			<a href="list?cp=${paging.cPage -10 < 1 ? 1 : paging.cPage -10}" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">&larr;&larr;</a>
		   			<a href="list?cp=${paging.cPage -1 < 1 ? 1 : paging.cPage -1}" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">&larr;</a>
		   			&nbsp;
		   			<c:forEach begin="${paging.blockStartNo }" end="${paging.blockEndNo}" varStatus="status">
						<a href="list?cp=${status.index }" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5" <c:if test="${status.index==paging.cPage }">style="color: Green"</c:if>>${status.index}</a>
		   			</c:forEach>
		   			&nbsp;
		   			<a href="list?cp=${paging.cPage +1 > paging.totalPage ? paging.totalPage : paging.cPage +1}" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">&rarr;</a>
		   			<a href="list?cp=${paging.cPage +10 > paging.totalPage ? paging.totalPage : paging.cPage +10 }" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">&rarr;&rarr;</a>
		   		</div>
	   		</form:form>
		</div>
   	</div>
</div>
</tag:layout>
<!-- Custom & Functional JS -->
<script type="text/javascript" src="${resources}/js/user.js"></script>
