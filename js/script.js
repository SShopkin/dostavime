$(document).ready(function() {
	"use strict";
//*******************Enter********************	
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
						window.location.href="transport.html";
					}
				}
			}
		});
		allUsers().then(function(response) {
			for(var i=0;i<response.length;i++){
				if((response[i].username)==($("#indexName").val())){
					if((response[i].pass)==($("#indexPassword").val())){
						window.location.href="user.html";
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
	
//*******************Registration********************
	function addUser(ENDPOINT){
		$.ajax(ENDPOINT, {
			method: "POST",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify({
				name: $("#userName").val(),
				pass: $("#inputPassword").val(),
				email: $("#inputEmail").val()
			}),
			dataType: "json"
		})
	}
	
	$("#registrationButton").click(function(){
		var endpoint;
		if($("#dropdownHeader").val()==""){
			alert("Изберете тип");
		} else if ($("#dropdownHeader").val()=="Транспортна фирма"){
			endpoint = "http://localhost:3000/transportLtd";
		} else {
			endpoint = "http://localhost:3000/user";
		};
		if(($("#userName").val()!="")&&($("#inputPassword").val()!="")&&($("#inputEmail").val()!="")){
			if(($("#inputPassword").val())===($("#retryPassword").val())){
				if($("#registrationCheck:checked").length){
					addUser(endpoint);
				} else {
					alert("За да продължите трябва да се съгласите с общите условия");
				}
			} else {
				alert("Паролите трябва да съвпадат");
			}
		} else {
			alert("Всички полета са задължителни");
		}		
	});
	
	
	
	
	
	
	
});