var root = "/tunner";
var csrfToken = $("#csrfToken").val();
var csrfHeader = $("#csrfHeader").val();

/* AJAX 통신 처리 */
var SurveyResultApp = angular.module('SurveyResultApp', []);
SurveyResultApp.controller('SurveyResultCtrl', function ($scope, $http) {
    $http({
		method : 'GET', // 방식
		url : root+"/survey/list-json", /* 통신할 URL */
		headers : {
			'Content-Type' : 'application/json; charset=utf-8'
		}
    }).then(function (success){
    	console.log("Success");
    	
    	var responseData=success.data;
    	$scope.surveys=responseData;
    	
    	//Ajax결과 출력    	
    	responseData.forEach(function(data, index, status){
    		console.log(data);
    	})
    	
    },function (error){
    	console.log("Error"+error);
    });
});
