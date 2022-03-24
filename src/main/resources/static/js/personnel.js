
$(document).ready(function() {



	var cptable = $("#cptable").DataTable({
	
        responsive: true,
		"serverSide": false,
		"columnDefs": [{
			targets: -1,
			className: 'dt-right'
		}],
		'ajax': {
			url: '/personnel/mycompanypeople', type: 'GET', dataSrc: ""
		},
		"pageLength": 5,
		"lengthChange": false,
		columns: [{
			title: "Id",
			data: 'id'
		}, {
			title: "Full Name",
			data: null,
			render: function(data, type, row) {
				if (row.enabled) {
					return row.firstname + ' ' + row.lastname
				}
				else
					return "<p style='opacity : 0.3'>" + row.firstname + ' ' + row.lastname + "</p>";
			}
		}, {
			data: "company.compname",
			render: function(data, type, row) {
				if (data == null) {
					return "Does not belong to any company";
				}
				else {
					return "<p style='color :" + row.company.color + " '> " + data + "</p>";
				}
			}
		}, {
			data: "userrole",
			render: function(data, type, row) {
				if (row.enabled)
					return data[0].role.rolename;
				else {
					return "<p style='opacity : 0.3'>" + data[0].role.rolename + "</p>";
				}
			}
		}, {
			data: 'id',
			render: function(data, type, row) {
				if (row.enabled) {
					return "<button class='btn btn-primary'> View Details </button> <button class='btn btn-secondary addscorecard' data-bs-toggle='modal' data-bs-target='#addscorecard'>Add Scorecard</button>";
				}
				else {
					return "<button class='btn btn-primary' disabled> View Details </button> <button class='btn btn-primary' disabled> Add Scorecard </button>";
				}

			}
		}]
	});
	$("#cptable tbody").on('click', '.addscorecard', function() {
		var data = cptable.row($(this).parents('tr')).data();
	});


	var table = $('#mytable').DataTable({
		
		"scrollY": "350px",
		"scrollCollapse": true,
		'ajax': { url: '/personnel/datatable', type: "GET" },
		'serverSide': true,
		"pageLength": 5,
		"lengthChange": false,
		"columnDefs": [{
			targets: -2,
			className: 'dt-right'
		}],
		columns: [{
			data: 'id'
		}, {
			data: 'firstname',
			render: function(data, type, row) {
				if (row.enabled) {
					return row.firstname + ' ' + row.lastname;
				}
				else
					return "<p style='opacity : 0.3'>" + row.firstname + ' ' + row.lastname + "</p>";

			}
		}, {
			data: "company.compname",
			render: function(data, type, row) {
				if (data == null) {
					return "Does not belong to any company";
				}
				else {
					return "<p style='color :" + row.company.color + " '> " + data + "</p>";
				}
			}
		}, {
			data: "userrole",
			render: function(data, type, row) {
				if (row.enabled)
					return data[0].role.rolename;
				else {
					return "<p style='opacity : 0.3'>" + data[0].role.rolename + "</p>";
				}
			}
		}, {
			data: 'id',
			render: function(data, type, row) {
				if (row.enabled) {
					return "<button class='btn btn-primary'> View Details </button> <button class='btn btn-secondary addscorecard' data-bs-toggle='modal' data-bs-target='#addscorecard'>Add Scorecard</button>";
				}
				else {
					return "<button class='btn btn-primary' disabled> View Details </button> <button class='btn btn-secondary' disabled> Add Scorecard </button>";
				}
			}
		}, {
			data: 'lastname',
			visible: false
		}]
	});
	
	let id = 0;
	
	$("#mytable tbody").on('click', 'button', function() {
		var data = table.row($(this).parents('tr')).data();
		id = data.id;

	});
	
	$("#save").click(function(){
		$.ajax({
			type : "POST",
			url : "/personnel/savecard",
			contentType : "application/json",
			success : function(result){
				alert(result);
			}
		})
	});
});
