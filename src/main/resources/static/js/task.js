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
	 
	
	
	
	
	// HTTP Requests
	
	$("#addform").submit(function(e){
		e.preventDefault();
	
		var task = {};
		task['title'] = $("#title").val();
		task['taskdetail'] = $("#detail").val();
		task['recurring'] = $("input[name='variation']:checked").val();
		task['until'] = $("#date").val();
		task['note'] = $("#note").val();
	
		
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