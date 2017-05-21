$(function(){
	window.setInterval(function(){
		//利用ajax 
		//user/heartbeat.do
		console.log("Test!");
		var url = 'user/heartbeat.do';
		//var data = {'t':new Date().getTime()};
		$.post(url, function(result){
			console.log(result);
		});
	}, 1000*5);
});