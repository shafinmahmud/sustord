$(document).ready(function() {
	// --------------- ICON AND ANIMATION URLs -------------------
	var loaderUrl = "/sustord/resources/icons/ajax-loader.gif";
		
	// Onclick actions for the Login button
	$('#login-form').submit(function(event) {
		$("#loading-anim").attr("src", loaderUrl);
	});
});