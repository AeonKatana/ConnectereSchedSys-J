
		$(document).ready(function(){
			
			var table = $('#mytable').DataTable({
				"scrollY":        "350px",
		        "scrollCollapse": true,
			    'ajax' : { url : '/personnel/datatable' , type : "GET" },
			    'serverSide' : true,
			    "pageLength": 10,
				"lengthChange": false,
			    columns : [{
			      data : 'id'
			    }, {
			      data : 'firstname',
			      render : function(data, type ,row){
					  if(row.enabled){
						 return  row.firstname + ' ' +row.lastname;
					  }
					  else
					  	return "<p style='opacity : 0.3'>" + row.firstname + ' ' + row.lastname + "</p>";
			    	 
			      }
			    },{
			    	data : "company.compname",
			    	render : function(data, type ,row){
			    		if(data == null){
			    			return "Does not belong to any company";
			    		}
			    		else{
			    			return "<p style='color :"+ row.company.color +" '> "+ data + "</p>";
			    		}
			    	}
			    },{
			    	data : "userrole",
			    	render : function(data, type ,row){
						if(row.enabled)
			    		return data[0].role.rolename;
			    		else{
							return "<p style='opacity : 0.3'>" + data[0].role.rolename + "</p>";
						}
			    	}
			    },{
			    	data : 'id',
			    	render : function(data, type, row){
						if(row.enabled)
			    		return "<button class='btn btn-primary'> View Details </button>";
			    		else{
						return "<button class='btn btn-primary' disabled> View Details </button>";
						}
			    		
			    	}
			    },{
					data : 'lastname',
					visible : false
				}]
			  });
			 $("#mytable tbody").on('click','button',function(){
				 var data = table.row($(this).parents('tr')).data();
				 console.log(data.company);
				 
			 });
		});
		