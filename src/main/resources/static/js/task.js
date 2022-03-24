$(document).ready(function(){
	
	$("#radio1").click(function(){
		
		$("#date").prop("disabled",false);
	});
	
	$("#radio2").click(function(){
		$("#date").prop("disabled",true);
	});
	
	$(".btncancel").click(function(){
		$("#radio2").attr("checked", true);
		$(this).parent().parent().parent().remove();
		window.parent.document.getElementById('framebtn').removeAttribute("disabled");
		window.parent.document.getElementById('myframe').setAttribute("height", 0);
	});
	 
	
	// Mention testing
	$('textarea.mention').mentionsInput({
	  onDataRequest:function (mode, query, callback) {
		  $.getJSON('/personnel/people', function(responseData) {
		        responseData = _.filter(responseData, function(item) { return item.name.toLowerCase().indexOf(query.toLowerCase()) > -1 });
		        callback.call(this, responseData);
		      });
	  }
	});	

$("#shet").click(function(){
	 
});
	
	
	// HTTP Requests
	
	$("#addform").submit(function(e){
		e.preventDefault();
	
		$('textarea.mention').mentionsInput('getMentions', function(data) {
		
		var task = {};
		task['title'] = $("#title").val();
		task['taskdetail'] = $("#verb").val() + " " + $("#number").val() + " " + $("#what").val();
		task['until'] = $("#date").val();
		task['mentions'] = data;
		
		$.ajax({
			type : "POST",
			url : "/task/savemytask",
			contentType : "application/json",
			data : JSON.stringify(task),
			success: function(result){
				parent.location.reload();
				$("#addform").parent().parent().remove();	
				alert("Task Added!");
			}
		})
		
    });
		
	});
	
	
});