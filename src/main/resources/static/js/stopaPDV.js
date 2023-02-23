$(".createStopaPDVDate").click(function(e){
	var today = new Date().toISOString().split('T')[0];
$(".createStopaPDVDate").attr('min', today);
	});