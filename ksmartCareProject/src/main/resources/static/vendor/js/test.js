/**
 * 
 */

$(document).ready(function(){
	var now = new Data();
	var before = new Data('December 9, 1991');
	
	//날짜 간격을 구합니다
	var interval = now.getTime() - before.getTime();
	interval = Math.floor(interval /(1000 * 60 * 60 * 24));
	
	alert(interval+"일");
})

