$(document).ready(function(){
	
	let newcompany = {};
	newcompany['companyname'] = $("#company").val();
	
	let masteradmin = {};
	masteradmin['fname'] = $("#firstname").val();
	masteradmin['lname'] = $("#lastname").val();
	masteradmin['useremail'] = $("#useremail").val();
	masteradmin['contact'] = $("#contact").val();
	
	$("#addcomp").submit(function(){
		$.post($(this).attr("action"), $(this).serialize(),function(result){
			alert("Company and Master Admin successfully added!");
			window.location.reload();
		});
		return false;
	});
	
	
});