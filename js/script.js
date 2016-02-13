$(document).ready(function() {
	"use strict";
	
	function allTrasportLtd() {
		return $.ajax("http://localhost:3000/transportLtd", {
			method: "GET",
			dataType: "json"
		});
	}	
	function allUsers() {
		return $.ajax("http://localhost:3000/user", {
			method: "GET",
			dataType: "json"
		});
	}
	function isItRegistred() {
		allTrasportLtd().then(function(response) {
			for(var i=0;i<response.length;i++){
				if((response[i].username)==($("#indexName").val())){
					if((response[i].pass)==($("#indexPassword").val())){
						window.location.href="main.html";
					}
				}
			}
		});
		allUsers().then(function(response) {
			for(var i=0;i<response.length;i++){
				if((response[i].username)==($("#indexName").val())){
					if((response[i].pass)==($("#indexPassword").val())){
						window.location.href="main.html";
					}
				}
			}
		});
	}
	
	$("#enterButton").click(function(){
		isItRegistred();
	});
	
	$(".dropdown-menu li").click(function(){
      $("#dropdownHeader").text($(this).text());
      $("#dropdownHeader").val($(this).text());
   });
	
	
	
	
	
	
	
	
});