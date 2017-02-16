var root = "/tunner";
var csrfToken = $('#csrfToken').val();
var csrfHeader = $('#csrfHeader').val();

$(document).ready(function() {
	$('.confirmAlert').confirmation({
		container : 'body',
		placement : 'left',
		title : 'Are you sure?',
		singleton : true,
		onConfirm : function() {
			alert('You choosed ' + currency);
		},
		onCancel : function() {
			alert('You didn\'t choose');
		},
		buttons : [ {
			label : 'Yes',
			class : 'btn btn-primary',
			icon : 'glyphicon glyphicon-ok',
			onClick : function() {
				currency = 'US Dollar';
			}
		}, {
			label : 'No',
			class : 'btn btn-danger',
			icon : 'glyphicon glyphicon-remove',
			cancel : true,
		} ]
	});
});
