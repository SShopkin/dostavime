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
	
	
	
//***********************Notice******************************

	function listNotices() {
		return $.ajax("http://localhost:3000/notice", {
			method: "GET",
			dataType: "json"
		});
	}
	
	function readNotice(noticeId) {
		return $.ajax(noticeEndpoint(noticeId), {
			method: "GET",
			dataType: "json"
		});
	}

	function reloadNotices() {
		return listNotices().then(function(response) {
			function addNoticeToList(notice) {
				var newItem = $("<li />");
				newItem.text(notice.header);
				newItem.addClass("list-group-item");
				newItem.attr("data-notice-id", notice.id);
				$("#noticesList").append(newItem);
			}
			$("#noticesList").html("");
			for(var i=0;i<response.length;i++){
				addNoticeToList(response[i]);
			}
		});
	}
	reloadNotices();
	
	function noticeEndpoint(noticeId) {
		return "http://localhost:3000/notice" + "/" + noticeId;
	}
	
	function highlightnoticeInnoticeList(notice) {
		$("#noticesList li[data-notice-id='"+notice.id+"']").addClass("active");
	}
	
	function shownoticeView(notice) {
		$("#readPanel .notice-title").text(notice.header);
		$("#readPanel .notice-description").text(notice.text);
		$("#readPanel .notice-price").text(notice.price);
		$("#readPanel .notice-action-remove").attr("data-notice-id", notice.id);
		$("#readPanel .notice-action-ok").attr("data-notice-id", notice.id);
		$("#readPanel").show();
		$("#noticesList li.active").removeClass("active");
		highlightnoticeInnoticeList(notice);
	}
	
	$(document).on("click", "#noticesList [data-notice-id]", function() {
		var noticeId = $(this).attr("data-notice-id");
		readNotice(noticeId).then(shownoticeView);
	});
	$(".notice-action-cancel").click(function() {
		$("#readPanel").hide();
	});
	
//********************Adding notice******************
	function addNotice(header){
		$.ajax("http://localhost:3000/notice", {
			method: "POST",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify({
				header: header,
				text: $("#description").val(),
				price: $("#price").val(),
				creator: "1",
				rights: "false"
			}),
			dataType: "json"
		})
	}

	$("#addNoticeButton").click(function() {
		var header = $("#title").val()+" От "+$("#from").val()+" За "+$("#to").val()+" Тегло "+$("#weight").val();
		addNotice(header);		
		window.location.href="notices.html";
	});
	
//*****************Delete notice**********************

	function deleteNotice(noticeId) {
		return $.ajax(noticeEndpoint(noticeId), {
			method: "DELETE",
			dataType: "json"
		});
	}

	$(".notice-action-remove").click(function() {
		var noticeId = $(this).attr("data-notice-id");
		deleteNotice(noticeId).then(function() {
			reloadNotices();
			$("#readPanel").hide();
		});
	});
	
	
	
	
	
	
	
	
	
	
});