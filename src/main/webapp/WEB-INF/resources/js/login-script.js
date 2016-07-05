$(document).ready(function() {
	// --------------- ICON AND ANIMATION URLs -------------------
	var loaderUrl = "../resources/icons/ajax-loader.gif";
	var successUrl = "../resources/icons/success-icon.png";
	var emptyIconUrl = "../resources/icons/empty-icon.gif";
	
	$("#loading-anim").attr("src", emptyIconUrl);
	
	// Onclick actions for the Login button
	$('#login-form').submit(function(event) {
		$("#loading-anim").attr("src", loaderUrl);
	});

});