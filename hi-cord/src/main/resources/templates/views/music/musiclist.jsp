<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" />
<spring:url value="/resources/template" var="template"/>
<spring:url value="/music" var="music"/>
<tag:layout tab="${target}">
<!--=== Call To Action ===-->
<div class="call-action-v1 bg-color-light">
	<div class="container">
		<div class="call-action-v1-box">
				<div class="call-action-v1-in">
					<p>Unify creative technology company providing key digital services and focused on helping our clients to build a successful business on web and mobile.</p>
				</div>
			<sec:authorize access="hasRole('SUPERADMIN')">
				<div class="call-action-v1-in inner-btn page-scroll">
					<a href="${music }/add" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">Add New Music</a>
					<a href="${music }/get" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">Get Music Rank</a>
				</div>
			</sec:authorize>
		</div>
	</div>
</div>
<!--=== End Call To Action ===-->
<div class="container content-xs">
		<div class="row">
			<div class="col-sm-12">
			  	<div id="youtubePlay"></div>
				<table class="table table-hover">
		    		<thead>
			      		<tr>
			      			<th>Id</th>
			      		 	<th>Image</th>
			      		 	<th>Title</th>
					        <th>Singer</th>
					        <th>Latest Date</th>
					        <th>URL</th>
					        <sec:authorize access="hasRole('SUPERADMIM')">
					        	<th width="100"></th>
					        </sec:authorize>
					        <sec:authorize access="hasRole('SUPERADMIM')">
					        	<th width="100"></th>
					        </sec:authorize>
						</tr>
			    	</thead>
		    		<tbody>
					<c:forEach items="${musics}" var="music">
						<tr>
							<td id="musicID">${music.id}</td>
							<td>
								<img alt="${music.title} Image" src="${music.image}">
							</td>
							<td>${music.title}</td>
							<td>${music.singer}</td>
							<td>${music.latestDate}</td>
							<td id="musicURL">${music.url}</td>
						    <sec:authorize access="hasRole('SUPERADMIM')">
								<td><a href="<c:url value='${music }/edit-${music.id}' />" class="btn btn-success custom-width">edit</a></td>
					        </sec:authorize>
					        <sec:authorize access="hasRole('SUPERADMIM')">
								<td><a href="<c:url value='${music }/delete-${music.id}' />" class="btn btn-danger custom-width">delete</a></td>
	        				</sec:authorize>
						</tr>
					</c:forEach>
		    		</tbody>
		    	</table>
			</div>
		</div>
   	</div>
</tag:layout>
<script>
    // 2. This code loads the IFrame Player API code asynchronously.
	var tag = document.createElement('script');
	tag.src = "https://www.youtube.com/iframe_api";
    var firstScriptTag = document.getElementsByTagName('script')[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    // 3. This function creates an <iframe> (and YouTube player)
    //    after the API code downloads.
    var player;
    function onYouTubeIframeAPIReady() {
        player = new YT.Player('youtubePlay', {
            height: '460',
            width: '1135',
            videoId: '5XR7naZ_zZA',
            events: {
                'onReady': onPlayerReady,
                'onStateChange' : onPlayerStateChange,
              /*'onPlaybackQualityChange': onPlayerPlaybackQualityChange,
                'onError': onPlayerError
                'onApiChange' : , */
            }
        });
	}

	function onPlayerReady(event) {
		var embedCode = event.target.getVideoEmbedCode();
		console.log(embedCode);
		event.target.playVideo();
		if (document.getElementById('embed-code')) {
			document.getElementById('embed-code').innerHTML = embedCode;
			$('#background').css("background-image", "url(http://img.youtube.com/vi/" + datacode[songNo] + "/0.jpg)");
		}
	}
	
	function onPlayerStateChange(event) {
		if (event.data == YT.PlayerState.ENDED) {
			event.target.playVideo();
		}
	}

	/* 	
	function stopVideo() {
			player.stopVideo();
		} 
	*/
</script>