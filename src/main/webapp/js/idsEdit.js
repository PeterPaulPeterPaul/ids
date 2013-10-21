          
          $(function(){
        	    var dialogOpts = {
        	        modal: true,
        	        width: 800,
        	        height: 106,
        	        position: 'top',
        	        dialogClass: "noOverDialog",
        	        open: function(event,ui){
        	        	$(".noOverDialog").next("div").css({opacity:0.2});
        	        }
        	   };

        	    $("#dialogFilter").dialog(dialogOpts);
        	    
        	    var dialogOpts2 = {
        		        modal: true,
        		        autoOpen: false
        		   };
        		    $("#dialog").dialog(dialogOpts2);
        		    $("#dialog44").dialog(dialogOpts2);
        		    $("#dialogAdd").dialog(dialogOpts2);
        		    
        		
        		    $("#dialogDel").dialog(dialogOpts2);

        	          var dialogOpts3333 = {
        	      			 modal: true,
        	  	            width: 500,
        	  	            height: 300,
        	  	            buttons: [{
        	  	                text: "Create Company",
        	  	                click : function() {    
        	  	                	 $.ajax({
        						         type: 'GET',
        							  url: parm5AjaxPrefix()+"addcompany?company="+$("#companyName").val()+"&accessType="+$("#accessType").val(), 
        					       processData: false,
        					       dataType: 'html',
        					       success: function(data) {  
        					    	  
        					    	   $("#dialogAddCompany").dialog("close");
        					    	   alert("done! (when this msg is closed the page will" +
        					    	   		"  reload allowing you to pick this company from the drop down list)");
        					    	   location.reload();
        					       },
        						    error: function (xhr, ajaxOptions, thrownError) {
        						        alert(xhr.status);
        						        alert(thrownError);
        						      }

        						     
        						     
        						  });
        					   
        	  	                	
        	  	                	 return false;
        	 	                	
        	  	   	    		
        		                }

        		                }, {
        		                text: "Cancel",
        		                click: function() {
        		                  $( this ).dialog( "close" );
        		                    $(this).dialog(dialogOpts3333).dialog("close"); //return false; 
        		                    return false;
        		                } 
        		                
        		                }]
        	  	                };
        	  	                

        		    $("#dialogAddCompany").dialog(dialogOpts3333);
        		    
        	});
          

          $(document).ready(function(){
        	  
        	  $("#accessCurrx").val( $("#accessType").val()   );
        //	  alert( $("#accessType").val());
        	  
        	  $( "#two" ).on("click" , function() {
            	  var mySS1 = $(".viewable1").children(".dropdown1").attr("id");
            	  var mySS2 = $(".viewable2").children(".dropdown2").attr("id");
            	  $("#title1").val(  $.trim($("#accessType option:selected").text()) );
            	  $("#title2").val(  $.trim($("#drop31s option:selected").text()) );
            	//  alert( $.trim($("#"+mySS1+" option:first").text()));
            	  $("#title3").val(  $.trim($("#"+mySS1+" option:first").text()) );
            	  $("#title4").val(  $.trim($("#"+mySS2+" option:first").text()) );
  		    	  $("#printer").submit();
        		  return false;
        		});
        	  $( "#one" ).on("click" , function() {
            	  var mySS1 = $(".viewable1").children(".dropdown1").attr("id");
            	  var mySS2 = $(".viewable2").children(".dropdown2").attr("id");
            	  $("#title11").val(  $.trim($("#accessType option:selected").text()) );
            	  $("#title22").val(  $.trim($("#drop31s option:selected").text()) );
            	  $("#title33").val(  $.trim($("#"+mySS1+" option:first").text()) );
            	  $("#title44").val(  $.trim($("#"+mySS2+" option:first").text()) );
        		  $("#excel1").submit();
        		  return false;
        		});
        	  
        	  
        	
        	  $("#dialogAdd").dialog("close");
        	  $("#dialogDel").dialog("close");
        	  $("#dialogAddCompany").dialog("close");
        	  
        	  $("#twosub").on("click",function(){
        		  $("body").toggleClass("wait");
        		  return true;
        	  });
        	  

        	  $("#addcosub").on("click",function(){ 
        		  $("#dialogAddCompany").dialog("open");
        	  });
        	  
        	  $("#addsub").on("click",function(){ 
        		 
            	  var selectedKey="";
            	  var selectedKey2="";
            	  
       			var dimension1Val="";
       			
        		  var dimension1Name = $(".ui-jqgrid-htable").children("thead").children("tr").children("th:first-child").attr("id");
      			dimension1Name = dimension1Name.replace("list47_","");

      		  var dimension5Val = $(".ui-jqgrid-htable").children("thead").children("tr").children("th:nth-child(2)").attr("id");
    			dimension5Val = dimension5Val.replace("list47_","");

    			var dimension5Name = $("#myDimensionHidden").text();
    			
      			$("#drop211").css("display","none");
      			$("#drop222").css("display","none");
      			$("#drop233").css("display","none");
      			$("#drop244").css("display","none");
 
      		//	var selectedKey="";
    			if (dimension1Name == "country") {
        		  $("#drop211").css("display","block");
        		  selectedKey="drop211s";
    			}
    			if (dimension1Name == "product") {
    			   $("#drop222").css("display","block");
    			   selectedKey="drop222s";
    			}
    			if (dimension1Name == "year") {
    				$("#drop233").css("display","block");
    				selectedKey="drop233s";
    			}
    			if (dimension1Name == "company") {
    				$("#drop244").css("display","block");
    				selectedKey="drop244s";
    			}
    			
      			$("#drop2111").css("display","none");
      			$("#drop2222").css("display","none");
      			$("#drop2333").css("display","none");
      			$("#drop2444").css("display","none");

//    			var selectedKey2="";
    			if (dimension5Name == "Country shortname") {
        		  $("#drop2111").css("display","block");
        		  selectedKey2="drop2111s";
    			}
    			if (dimension5Name == "Product shortname") {
    			   $("#drop2222").css("display","block");
    			   selectedKey2="drop2222s";
    			}
    			if (dimension5Name == "Year") {
    				$("#drop2333").css("display","block");
    				selectedKey2="drop2333s";
    			}
    			if (dimension5Name == "Company shortname") {
    				$("#drop2444").css("display","block");
    				selectedKey2="drop2444s";
    			}
      			
    			var dimension2 = $(".viewable1").attr("id");
    			var text2 = $("#"+dimension2+"sSelectBoxItText").text();
    			var di_name="";
    			if (dimension2 == "drop11") {
    				di_name="Country";
    			}
    			if (dimension2 == "drop12") {
    				di_name="Product";
    			}
    			if (dimension2 == "drop13") {
    				di_name="Year";
    			}
    			if (dimension2 == "drop14") {
    				di_name="Company";
    			}
    			var dimension3 = $(".viewable2").attr("id");
    			var text3 = $("#"+dimension3+"sSelectBoxItText").text();
    			
    			var di_name3="";
    			if (dimension3 == "drop21") {
    				di_name3="Country";
    			}
    			if (dimension3 == "drop22") {
    				di_name3="Product";
    			}
    			if (dimension3 == "drop23") {
    				di_name3="Year";
    			}
    			if (dimension3 == "drop24") {
    				di_name3="Company";
    			}
    			
 
    			dimension1Val = $("#"+selectedKey).val();
    			dimension5Val = $("#"+selectedKey2).val();

    		  	  $("#"+selectedKey).on("change",function(){ 
              		 dimension1Val = $("#"+selectedKey+" option:selected").val();
              	  });
    		  	  
    		  	  $("#"+selectedKey2).on("change",function(){ 
               		 dimension5Val = $("#"+selectedKey2+" option:selected").val();
               	  });
    			
    			var dialogOpts3 = {
    			 modal: true,
	            width: 500,
	            height: 600,
	            buttons: [{
	                text: "Create",
	                click : function() {    

	                	if ($("#quantityAmt").val().length){
	                	if(	$.isNumeric($("#quantityAmt").val())) {
	                	$("#dimension1Val").val(dimension1Val.replace(/^\s+|\s+$/g, ''));
	                	$("#dimension1Name").val(dimension1Name);
	                 	$("#dimension2Val").val(text2.replace(/^\s+|\s+$/g, ''));
	                	$("#dimension2Name").val(di_name);
	                 	$("#dimension3Val").val(text3.replace(/^\s+|\s+$/g, ''));
	                	$("#dimension3Name").val(di_name3);
	                 	$("#dimension4Val").val($("#drop31sSelectBoxItText").text());
	                	$("#dimension4Name").val("PorS");
	                 	$("#dimension5Val").val(dimension5Val.replace(/^\s+|\s+$/g, ''));
	                	$("#dimension5Name").val($("#myDimensionHidden").text());
	                	$("#quantAmt").val($("#quantityAmt").val());
	                	$("#accessCurr").val($("#accessType").val());
	                	$("#addrowForm").submit();
	                	}else {
	                		alert("you must only enter a numeric value");
	                	}
	                	}else{
	                		alert("you must enter a quantity");
	                	}
	      			    return false;
	                	
	    		
	                }

	                }, {
	                text: "Cancel",
	                click: function() {
	                  $( this ).dialog( "close" );
	                    $(this).dialog(dialogOpts3).dialog("close"); //return false; 
	                    return false;
	                } 
	                
	                }]
	       };
    			
        		  $("#dialogAdd").dialog(dialogOpts3).dialog("open");
        		  
        		  
 
    			
        	  });
        	  

      
        	  
        	 
        	  
        	  
        	  $("#delsub").on("click",function(){ 
               	  var selectedKey="";
            	  var selectedKey2="";
            	  
       			var dimension1Val="";
       			
        		  var dimension1Name = $(".ui-jqgrid-htable").children("thead").children("tr").children("th:first-child").attr("id");
      			dimension1Name = dimension1Name.replace("list47_","");


    			
      			$("#drop211z").css("display","none");
      			$("#drop222z").css("display","none");
      			$("#drop233z").css("display","none");
      			$("#drop244z").css("display","none");
 
      		//	var selectedKey="";
    			if (dimension1Name == "country") {
        		  $("#drop211z").css("display","block");
        		  selectedKey="drop211sz";
    			}
    			if (dimension1Name == "product") {
    			   $("#drop222z").css("display","block");
    			   selectedKey="drop222sz";
    			}
    			if (dimension1Name == "year") {
    				$("#drop233z").css("display","block");
    				selectedKey="drop233sz";
    			}
    			if (dimension1Name == "company") {
    				$("#drop244z").css("display","block");
    				selectedKey="drop244sz";
    			}
    			
      			
    			var dimension2 = $(".viewable1").attr("id");
    			var text2 = $("#"+dimension2+"sSelectBoxItText").text();
    			var di_name="";
    			if (dimension2 == "drop11") {
    				di_name="Country";
    			}
    			if (dimension2 == "drop12") {
    				di_name="Product";
    			}
    			if (dimension2 == "drop13") {
    				di_name="Year";
    			}
    			if (dimension2 == "drop14") {
    				di_name="Company";
    			}
    			var dimension3 = $(".viewable2").attr("id");
    			var text3 = $("#"+dimension3+"sSelectBoxItText").text();
    			
    			var di_name3="";
    			if (dimension3 == "drop21") {
    				di_name3="Country";
    			}
    			if (dimension3 == "drop22") {
    				di_name3="Product";
    			}
    			if (dimension3 == "drop23") {
    				di_name3="Year";
    			}
    			if (dimension3 == "drop24") {
    				di_name3="Company";
    			}
    			
 
    			dimension1Val = $("#"+selectedKey).val();

    		  	  $("#"+selectedKey).on("change",function(){ 
              		 dimension1Val = $("#"+selectedKey+" option:selected").text();
              	  });

    			
    			var dialogOpts3 = {
    			 modal: true,
	            width: 500,
	            height: 300,
	            buttons: [{
	                text: "Delete",
	                click : function() {    

	                	$("#dimension11Val").val(dimension1Val.replace(/^\s+|\s+$/g, ''));
	                	$("#dimension11Name").val(dimension1Name);
	                 	$("#dimension22Val").val(text2.replace(/^\s+|\s+$/g, ''));
	                	$("#dimension22Name").val(di_name);
	                 	$("#dimension33Val").val(text3.replace(/^\s+|\s+$/g, ''));
	                	$("#dimension33Name").val(di_name3);
	                 	$("#dimension44Val").val($("#drop31sSelectBoxItText").text());
	                	$("#dimension44Name").val("PorS");
	                	$("#accessCurrr").val($("#accessType").val());
	                	$("#deleterowForm").submit();
	                	
	      			    return false;
	                	
	    		
	                }

	                }, {
	                text: "Cancel",
	                click: function() {
	                  $( this ).dialog( "close" );
	                    $(this).dialog(dialogOpts3).dialog("close"); //return false; 
	                    return false;
	                } 
	                
	                }]
	       };
    			
        		  $("#dialogDel").dialog(dialogOpts3).dialog("open");
        		  
        		  
 
    			
        	  });
        	  

        	  
          	var summary=0;
          	var swapValue="0";

        	  var lastSel;
        	  
        	  var j = new Date().getFullYear();
        	  j=j+20;
        	  var stringList="<option value='-1'>All</option>";
        	   for(var i = 1980; i <= j; i++) {
        		   stringList = stringList + "<option value='"+i+"'>"+i+"</option>";

        	     }
    		   $("#fromdate").append(stringList);
    		   $("#todate").append(stringList);
        	  
    		   
    		   
    		   
        	  var downloadExcel="no";
        	  var rowTotal="on";
            	  
            	  $("#toggleRowTotal").on("click",function(){ 
            		  if ($("#list47_TOTAL").length) {
            		     rowTotal="off";
            		  } else {
            			  rowTotal="on";
            		  }
            		  getGrid();
            	  });
        	  
        	  
        	  $("#toExcel").on("click",function(){ 
        		  downloadExcel="yes";
        		  getGrid();
        	  });

        	  $("#drop11as").dropdownchecklist({ firstItemChecksAll: true, emptyText: "Filter Countries...",  width:200, zIndex:100,maxDropHeight:400
         		 , onItemClick: function(checkbox, selector){ 
         			downloadExcel="no";
        			 getGrid();
        		 }
        	  });
        	  
        	  $("#drop12as").dropdownchecklist({ firstItemChecksAll: true, emptyText: "Filter Products ...",  width:200, zIndex:100,maxDropHeight:400 
         		 , onItemClick: function(checkbox, selector){ 
         			downloadExcel="no";
        			 getGrid();
        		 }
        	  });
        	  $("#drop14as").dropdownchecklist({ firstItemChecksAll: true, emptyText: "Filter Companies ...",  width:200, zIndex:100,maxDropHeight:400
        		 , onItemClick: function(checkbox, selector){ 
        			 downloadExcel="no";
        			 getGrid();
        		 }
        	  
        	  });

        	  $("#dialogFilter").dialog("close");
        	  
		      $("body").removeClass("js");
		      $("#dialog44").dialog(parm4openOrClose());
		   	  $("#dialog").dialog(parm3openOrClose());
        	  
        	  $("#closeit").on("click",function(){
        		  $("body").toggleClass("wait");
        		  $.ajax({
					  url: parm5AjaxPrefix()+'main?exit=yes',
			         type: 'GET',
			       contentType: 'application/html',
			       processData: false,
			       dataType: 'html',
			       success: function(data) {  
			    	   window.location = parm5AjaxPrefix()+'login';
			       },
				    error: function (xhr, ajaxOptions, thrownError) {
				        alert(xhr.status);
				        alert(thrownError);
				        window.location = parm5AjaxPrefix()+'login';
				      }

				  });
        	  });
        	  
       	  $(".dropdown1").selectBoxIt();
        	  $(".dropdown2").selectBoxIt();
        	  $(".dropdown3").selectBoxIt();
        	  $(".dropdown33").selectBoxIt();
        	  
        	  $( "input:button" ).button();
        	  
        	  $(".dropdown1").css("width","182px");
        	  $(".dropdown2").css("width","182px");
        	  
        	  
        	  var mySS1 = $(".viewable1").children(".dropdown1").attr("id");
        	  var mySS2 = $(".viewable2").children(".dropdown2").attr("id");
        	  
        	  $("#title1").val(  $.trim($("#accessType option:selected").text()) );
        	  $("#title2").val(  $.trim($("#drop31s option:selected").text()) );
        	  $("#title3").val(  $.trim($("#"+mySS1+" option:first").text()) );
        	  $("#title4").val(  $.trim($("#"+mySS2+" option:first").text()) );
        	  
        	  $("#title11").val(  $.trim($("#accessType option:selected").text()) );
        	  $("#title22").val(  $.trim($("#drop31s option:selected").text()) );
        	  $("#title33").val(  $.trim($("#"+mySS1+" option:first").text()) );
        	  $("#title44").val(  $.trim($("#"+mySS2+" option:first").text()) );


        	var clickType="";
        	var swapValue="0";

  
        	$(".swap").on("click",function(){
        		if($(this).hasClass("noswap")){
        			swapValue="0"
        		   $(".swap").val("Swap cols/rows");
        			$(".swap").removeClass("noswap");
        		} else {
        			swapValue="1"
        		    $(".swap").val("Clear swap");
        			$(".swap").addClass("noswap");
        		}
        		downloadExcel="no";
        		 getGrid();
        	});
        	
	
	  
	  
	  
	  $("#clearfilter").on("click",function(){
          $("#fromdate").val("-1");
          $("#todate").val("-1");
			$('input[id^="ddcl-drop12as-i"]').each(function( index ) {
				if($(this).prop("checked")){
				   $(this).prop("checked",false); 
			     }
			});
            $('input[id^="ddcl-drop14as-i"]').each(function( index ) {
					if($(this).prop("checked")){
						$(this).prop("checked",false);  
				     }
				});
             $('input[id^="ddcl-drop11as-i"]').each(function( index ) {
					if($(this).prop("checked")){
						$(this).prop("checked",false); 
						$(this).children("span").text();
				     }
				});
				
             $(".ui-dropdownchecklist-text:first").prop("title","Filter Countries...");
             $(".ui-dropdownchecklist-text:first").children("span").remove();
             $(".ui-dropdownchecklist-text:first").text("Filter Countries...");
             $("#ddcl-drop12as").children("span").children(".ui-dropdownchecklist-text").prop("title","Filter Products ...");
             $("#ddcl-drop12as").children("span").children(".ui-dropdownchecklist-text").children("span").remove();
             $("#ddcl-drop12as").children("span").children(".ui-dropdownchecklist-text").text("Filter Products ...");
             
             $("#ddcl-drop14as").children("span").children(".ui-dropdownchecklist-text").prop("title","Filter Companies...");
             $("#ddcl-drop14as").children("span").children(".ui-dropdownchecklist-text").children("span").remove();
             $("#ddcl-drop14as").children("span").children(".ui-dropdownchecklist-text").text("Filter Companies...");
             downloadExcel="no";
             getGrid();
             
          alert("filters cleared");
          $("#clearfilter").fadeOut();
	  });
	  
	  $("#submit1").on("click",function(){
		  $("#dialogFilter").dialog("close");
	  });
	  
	 
	  $(".dropdown33").on("change",function(){
		  downloadExcel="no";
		  getGrid();
	  });
	  
	  $("#fromdate").on("change",function(){
		  downloadExcel="no";
		  getGrid();
	  });
	  
	  $("#todate").on("change",function(){
		  downloadExcel="no";
		  getGrid();
	  });
	  
	  $(".dropdown1").on("change",function(){
		  clickType= $(this).attr('id');
		  downloadExcel="no";
		//  alert("here");
		//  alert(clickType);
		  getGrid();
	  });
	  
	  $(".dropdown2").on("change",function(){
		  clickType=$(this).attr('id');
		  downloadExcel="no";
		  getGrid();
	  });
	  
	  
	  $(".dropdown3").on("change",function(){
		  clickType="myrad";
		  if ($(this).attr("id")=="accessType") {
			  var accessT = $("#accessType").val();
			  $("#fromMain").val(accessT);
			  $("#accessform3").submit();
			  return false;
		  }
		  downloadExcel="no";
		  getGrid(); 
	  });
	  
	  
	  $(".myrad2").on("change",function(){
		  clickType="myrad2";
		  if ($(".myrad2:checked").val()=="4"){ 
		  $(".swap").show();
      } else {
    	  if ( $(".myrad3:checked").val()=="4"){
    		  $(".swap").show(); 
    	  } else {
    	     $(".swap").hide();  
    	  }
      }
      
		  downloadExcel="no";
		  getGrid(); 
	  });
	  
	  $(".myrad3").on("change",function(){
		  clickType="myrad3";

		  if ($(".myrad2:checked").val()=="4"|| 
				  $(".myrad3:checked").val()=="4"){

			      $(".swap").show();

		      } else {
		    	  $(".swap").hide();  
		      }
		  

		  downloadExcel="no";
		  getGrid(); 
	  });
	  
	  function getGrid()
	  {


          
		  
var myId2= $(".viewable2").attr("id");
var mydropdown2 = $("#"+myId2+" option:selected").val();

var firstPartId = $(".myrad2:checked").val();
var secondPartId = $(".myrad3:checked").val();

if (clickType=="myrad3") {

	 mydropdown2 = $("#drop2"+secondPartId+" option:selected").val();

}

var h1 = $("#tempOldHeadings").html();
var h2 = h1;
h1 = h1.substr(0,1);
h2 = h2.substr(1,1);

var myId= $(".viewable1").attr("id");

var mydropdown1 = $("#"+myId+" option:selected").val();

	if 	(firstPartId == secondPartId && clickType=="myrad3" && (summary ==0 || h2 != "4")){
		$("#"+myId).removeClass("viewable1");
		$("#drop1"+h2).addClass("viewable1");
		 mydropdown1 = $("#drop1"+h2+" option:selected").val();
		  $(".myrad2").prop('checked', false);
		  $("#a"+h2).prop('checked', true);
	}




if (clickType=="myrad2") {
	 mydropdown1 = $("#drop1"+firstPartId+" option:selected").val();
} 

if 	(firstPartId == secondPartId && clickType=="myrad2" ){
	$("#"+myId).removeClass("viewable2");
	$("#drop2"+h1).addClass("viewable2");
	 mydropdown2 = $("#drop2"+h1+" option:selected").val();
	  $(".myrad3").prop('checked', false);
	  $("#z"+h1).prop('checked', true);
}

if (clickType=="myrad2" || clickType=="myrad3" ) {
	   $(".showornot1").fadeOut();
	   $(".showornot2").fadeOut();
	   $(".showornot2").removeClass("viewable2");
	   $(".showornot1").removeClass("viewable1");
}

$(".showornot11").fadeOut();
$(".showornot111").fadeOut();
$(".showornot22").fadeOut()

var my_SorP = $('#drop31s').val();
			 
$("#wholescreen").fadeOut();
$("#titleBar").fadeOut();


var dateParm=       validateDates() ;
if (dateParm=="todate must be greater or equal to fromdate") {
	 alert(dateParm);
	 $("#titleBar").fadeIn();
	 $("#wholescreen").fadeIn();
	 
	 $("#clearfilter").fadeIn();
	  

	  
	 return;
}
   

			 $('#gbox_list47').fadeOut().promise().done(function() {  
	

				   $('#gbox_list47').remove();
				   $("#beans").append("<table id='list47'></table><div id='plist47'></div>");


				   
				   
				//   alert('/ids/main?list=2&pors='+my_SorP+'&dropdown1='+mydropdown1+'&dropdown2='+mydropdown2
				//			  +'&radio1='+$(".myrad2:checked").val()+'&radio2='+$(".myrad3:checked").val()+"&clickType="+clickType+
				//			  "&oldHead1="+h1+"&oldHead2="+h2+"&summary="+summary+"&swap="+swapValue);

				var countriesParm="";
				var countriesList="";

				$('input[id^="ddcl-drop11as-i"]').each(function( index ) {
					if($(this).prop("checked")){
					   countriesList= countriesList+$(this).val()+"|"; 
				     }
				});

	               if (countriesList!=""){
	   					countriesParm="&includedCountries=";
                   }
	               
	               
					var productsParm="";
					var productsList="";

					$('input[id^="ddcl-drop12as-i"]').each(function( index ) {
						if($(this).prop("checked")){
						   productsList= productsList+$(this).val()+"|"; 
					     }
					});
	
		               if (productsList!=""){
		   					productsParm="&includedProducts=";
		                   }

						var companiesParm="";
						var companiesList="";

						$('input[id^="ddcl-drop14as-i"]').each(function( index ) {
							if($(this).prop("checked")){
								companiesList= companiesList+$(this).val()+"|"; 
						     }
						});
						
			               if (companiesList!=""){
			      			 companiesParm="&includedCompanies=";
			   				}

			               var fromDate = "";
			               if ($("#fromdate").val() != "-1") {
			            	   fromDate = "&fromDate="+$("#fromdate").val();
			               }
			               var toDate = "";
			               if ($("#todate").val() != "-1") {
			            	   toDate = "&toDate="+$("#todate").val();
			               }
			     
			       		if ($("#fromdate").val() != "-1" ||
			       				$("#todate").val() != "-1"	||
			       				companiesList!="" ||
			       				productsList!="" ||
			       				countriesList!=""){
			       	              $("#clearfilter").fadeIn();
			       		}
			       		
			       		
			       		var saveURL = parm5AjaxPrefix()+'saverow?list=1&pors='+my_SorP+'&dropdown1='+mydropdown1+'&dropdown2='+mydropdown2
						  +'&radio1='+$(".myrad2:checked").val()+'&radio2='+$(".myrad3:checked").val()+"&clickType="+clickType+
						  "&oldHead1="+h1+"&oldHead2="+h2+"&summary="+summary+"&swap="+swapValue+"&access="+$("#accessType").val()
						  +countriesParm+countriesList+productsParm+productsList+
			       		  companiesParm+companiesList+fromDate+toDate+"&excelDownload="+downloadExcel+
						  "&rowTotal="+rowTotal+dateParm;
						

			       		var saveparameters = {
			       				"successfunc" : null,
			       				"url" : saveURL,
			       			        "extraparam" : {},
			       				"aftersavefunc" : null,
			       				"errorfunc": null,
			       				"afterrestorefunc" : null,
			       				"restoreAfterError" : true,
			       				"mtype" : "POST"
			       			}

					  $.ajax({
					         type: 'POST',
						  url: parm5AjaxPrefix()+'editor?list=1&pors='+my_SorP+'&dropdown1='+mydropdown1+'&dropdown2='+mydropdown2
				  +'&radio1='+$(".myrad2:checked").val()+'&radio2='+$(".myrad3:checked").val()+"&clickType="+clickType+
								  "&oldHead1="+h1+"&oldHead2="+h2+"&summary="+summary+"&swap="+swapValue
								  +countriesParm+countriesList+productsParm+productsList+
								  fromDate+toDate+"&rowTotal="+rowTotal+"&excelDownload="+downloadExcel+dateParm,
					       contentType: "application/x-www-form-urlencoded;charset=UTF-8", 
					       processData: false,
					       dataType: 'html',
				       data: JSON.stringify({ "includedCompanies" : companiesList }),
				       success: function(data) {  

				    	   $("#tempStore").html(data);
				    	   var data2 = JSON.parse($("#tempStore #myJson").html());
				    	   
				    	   $("#printDataJson").val($("#tempStore #myJson").html());
				    	   $("#dataJson").val($("#tempStore #myJson").html());
				    	   
				    	   $("#tempOldHeadings").text($("#tempStore #myOldHeadings").html() );
				    	   
				    	   $("#myDimensionHidden").text($("#tempStore #myDimension").html() ) ;

	    			        $(".myrad2").prop('checked', false);
		    				   $(".myrad3").prop('checked', false);
		    				   
		    				   var h1 = $("#tempOldHeadings").html();
		    				   var h2 = h1;
		    				   h1 = h1.substr(0,1);
		    				   h2 = h2.substr(1,1);
		    				   
		    				   $("#a"+h1).prop('checked', true);
		    				   $("#z"+h2).prop('checked', true);
		    				   

	
		    				  var value1 =  $("#tempStore #myDropValue1").html();
		    				  var value2 =  $("#tempStore #myDropValue2").html();
 		    				   $(".showornot1").removeClass("viewable1");
  		    				   $("#drop1"+h1).addClass("viewable1");
  		    				   $(".showornot2").removeClass("viewable2");
  		    				   $("#drop2"+h2).addClass("viewable2");
  		    				   
		    				    if (summary != 1 && summary !=4 && summary != 2 && summary !=5) {
		  		    				   $("#drop1"+h1).fadeIn();
	  		    				    }
		    				    
		    				    if (summary != 3 && summary != 4 && summary != 5) {
		  		    				   $("#drop2"+h2).fadeIn();
		  		    				}
		    				    

		    				   if (clickType=="myrad2"){
	  		    				   $("#drop1"+h1+" option:selected").prop("selected", false);
	  		    				   $("#drop1"+h1+" option[value="+value1+"]").prop("selected", true);
		  		    				$("#drop2"+h2+" option:selected").prop("selected", false);
		  		    				$("#drop2"+h2+" option[value="+value2+"]").prop("selected", true);
		  		    				
		  		    				 $("#title3").val($.trim( $("#drop1"+h1+" option[value="+value1+"]").text() ) );
		  		    				 $("#title33").val($.trim( $("#drop1"+h1+" option[value="+value1+"]").text() ) );
			  		    				$("#title4").val($.trim( $("#drop2"+h2+" option[value="+value2+"]").text()) );
			  		    				$("#title44").val($.trim( $("#drop2"+h2+" option[value="+value2+"]").text()) );


		    				   }  
		    				   
		    				   $("#title2").val(  $.trim($("#drop31s option:selected").text()) );
		    				   $("#title1").val(  $.trim($("#accessType option:selected").text()) );
		    				   $("#title22").val(  $.trim($("#drop31s option:selected").text()) );
		    				   $("#title11").val(  $.trim($("#accessType option:selected").text()) );
		    				   
		    				   if (clickType=="myrad3"){
	  		    				   $("#drop2"+h2+" option:selected").prop("selected", false);
	  		    				   $("#drop2"+h2+" option[value="+value2+"]").prop("selected", true);
			  		    		   $("#drop1"+h1+" option:selected").prop("selected", false);
			  		    		   $("#drop1"+h1+" option[value="+value1+"]").prop("selected", true);
			  		    		   
			  		    		 $("#title4").val($("#drop2"+h2+" option[value="+value2+"]").text());
				  		    		 $("#title3").val($("#drop1"+h1+" option[value="+value1+"]").text());

		    				   }
		    				   
		    				   
		    				   
				    	   
				    	   var myTabData = data2.tabData;
			var cols= myTabData[1].columns;
		    var colModels=myTabData[0].columnModels;
		    var mylocalData=myTabData[2].myData;
		    var mylocalTitle=myTabData[3].title;
		mylocalTitle = JSON.stringify(mylocalTitle)
		mylocalTitle = mylocalTitle.replace(/"/g, '');
		mylocalTitle = mylocalTitle.replace("[", "");
		mylocalTitle = mylocalTitle.replace("]", "");


		var colModels2 = JSON.stringify(colModels);
		  colModels2 = colModels2.replace(/\"formatter\":\"number\"/g,"\"formatter\":\"number\",\"formatoptions\":{\"decimalPlaces\":0,\"defaultValue\":\"0\"}");
		 var  colModels3 = JSON.parse(colModels2);
		  //formatter: 'number', formatoptions: { decimalPlaces: 2 }

		 saveparameters = {
				    successfunc : null,
				    url : parm5AjaxPrefix()+'saverow?access='+$("#accessType").val(),
				    extraparam : {},
				    aftersavefunc : function( response ) {
				                          alert('saved');
				                      },
				    errorfunc: null,
				    afterrestorefunc : null,
				    restoreAfterError : true,
				    mtype : "POST"
				};

				    			        jQuery("#list47").jqGrid({
				    			        	data:mylocalData,
				    			        	datatype: "local",
				    			        	editurl: saveURL,
				    			        	height: 350,
				    			        	cellEdit : true,
				    			        	cellsubmit : 'remote',
				    			        	cellurl : saveURL,
				    			        	afterSaveCell: function(){
				    			        		$("#saveButId").css("display","block");
				    			        	},
				    			        	beforeSubmitCell : function(rowid,celname,value,iRow,iCol) { 
				    			        		//if( some_condition ) { 
				    			        			
		var retVal = $("#list47").children("tbody").children("#"+rowid).children(":first").html();

			var dimension1Name = $(".ui-jqgrid-htable").children("thead").children("tr").children("th:first-child").attr("id");
			dimension1Name = dimension1Name.replace("list47_","");
			var dimension2 = $(".viewable1").attr("id");
			var text2 = $("#"+dimension2+"sSelectBoxItText").text();
			var di_name="";
			if (dimension2 == "drop11") {
				di_name="Country";
			}
			if (dimension2 == "drop12") {
				di_name="Product";
			}
			if (dimension2 == "drop13") {
				di_name="Year";
			}
			if (dimension2 == "drop14") {
				di_name="Company";
			}
			var dimension3 = $(".viewable2").attr("id");
			var text3 = $("#"+dimension3+"sSelectBoxItText").text();
			
			var di_name3="";
			if (dimension3 == "drop21") {
				di_name3="Country";
			}
			if (dimension3 == "drop22") {
				di_name3="Product";
			}
			if (dimension3 == "drop23") {
				di_name3="Year";
			}
			if (dimension3 == "drop24") {
				di_name3="Company";
			}

    		return {
    			     dimension1Val:retVal,
    			     dimension1Name:dimension1Name,
    			     dimension2Val:text2.replace(/^\s+|\s+$/g, ''),
    			     dimension2Name:di_name,
    			     dimension3Val:text3.replace(/^\s+|\s+$/g, ''),
    			     dimension3Name:di_name3,
    			     dimension4Val:$("#drop31sSelectBoxItText").text(),
    			     dimension4Name:"PorS",
    			     dimension5Val:celname,
    			     dimension5Name:$("#myDimensionHidden").text(),
    			     FactVal:value
    		      } 
				    			        	//	} 
				    			        	//	else 
				    			        	//	{ return {} } 
				    			        		} ,
				    			        	
				    			        	rowNum: 30,
				    			        	rowList: [10,20,30],
				    			           	colNames:cols,
				    			           	hidegrid:false,
				    			           	colModel:colModels3,
				    			           	pager: "#plist47",
				    			           	viewrecords: true,
				    			           	caption: mylocalTitle,
				    			         	footerrow: true, 
				    			         	 ignoreCase:true,
				    			         	userDataOnFooter: true,
				    			        	   onSelectRow: function(id){

					    			        	     if(id && id!==lastSel){ 
					    			        	    	 
					    			        	    	 jQuery('#list47').jqGrid('saveRow?access='+ $("#accessType").val(),id ,saveparameters);

					    			        	        lastSel=id; 
					    			        	     }
					    			        	     jQuery('#list47').editRow(id, true); 
					    			        	   },
				    			        });
				    			        


				    			        
				    			        
				    			        
				    			        
			    						  $("#wholescreen").fadeIn();
			    						  
				    			        jQuery("#list47").jqGrid('navGrid','#plist47',{edit:false,add:false,del:false});

				    	
				    			 	   $('#gbox_list47').fadeIn();
				    				   $('#list47').fadeIn();


				    					 var totals = JSON.parse($("#tempStore #myJsonTotals").html());
				    					 $("#totalsJson").val($("#tempStore #myJsonTotals").html());
				    				//	 $("#printDataJson").val($("#tempStore #myJsonTotals").html());
								    	   
								    	   
				    					 
				    						$("#list47").jqGrid('footerData', 'set', 
				    								totals.myTotals[0]); 
				    						
				    					
				    						 $(".ui-jqgrid-titlebar").remove();
				    						 
				    						 
				    						 $('#list47').setGridWidth($('#beans').width());
				    						 $(window).resize(function() {
				    							 $('#list47').setGridWidth($('#beans').width());
				    							});
				    					 
				    						 
				    						  if ($(".myrad2:checked").val()=="4"|| 
				    								  $(".myrad3:checked").val()=="4"){
				    							  $(".swap").show();
				    						  }
				    						  
				    						  $('.footrow').children("td").removeClass("colorWhite"); 
				    						  
				    						  $('.footrow').children('td').each(function( index ) {
				    							  if ( $(this).text()==" " || $(this).text()=='\xa0'){
				    								  $(this).text("0"); 
				    							  }
				    							  
				    							});
				    						  
				    						  
				    						  if ($("#list47_TOTAL").length) {
				    		            		     $("#toggleRowTotal").val("Remove row total");
				    		            		  } else {
				    		            			  $("#toggleRowTotal").val("Add row total");
				    		            		  }
				    						  
				    						  $("#titleBar").fadeIn();

					   },
					    error: function (xhr, ajaxOptions, thrownError) {
					        alert(xhr.status);
					        alert(thrownError);
					      }

					     
					     
					  });
				   
				   
				   
				   
			 });

  
  }  

	  var totals=parm1JsonTotal(); 

  var data = parm2firstTimeFromServer();

  $("#dataJson").val(JSON.stringify(data));
  $("#printDataJson").val(JSON.stringify(data));
  
  $("#totalsJson").val(JSON.stringify(totals));
  
	 $("#printTotalJson").val(JSON.stringify(totals));


	   var myTabData = data.tabData;
		var cols= myTabData[1].columns;
	    var colModels=myTabData[0].columnModels;
	    var mylocalData=myTabData[2].myData;
	    var mylocalTitle=myTabData[3].title;
	mylocalTitle = JSON.stringify(mylocalTitle)
	mylocalTitle = mylocalTitle.replace(/"/g, '');
	mylocalTitle = mylocalTitle.replace("[", "");
	mylocalTitle = mylocalTitle.replace("]", "");

	
	var colModels2 = JSON.stringify(colModels);
	  colModels2 = colModels2.replace(/\"formatter\":\"number\"/g,"\"formatter\":\"number\",\"formatoptions\":{\"decimalPlaces\":0,\"defaultValue\":\"0\"}");
	 var  colModels3 = JSON.parse(colModels2);
	// $("#wholescreen").fadeIn();

      jQuery("#list47").jqGrid({
      	data:mylocalData,
      	datatype: "local",
      	height: 350,
      	cellEdit : true,
    	cellsubmit : 'remote',
    	cellurl :parm5AjaxPrefix()+"saverow?access="+$("#accessType").val(),
    	afterSaveCell: function(){
    		$("#saveButId").css("display","block");
    	},
    	beforeSubmitCell : function(rowid,celname,value,iRow,iCol) { 
    		//if( some_condition ) { 

		var retVal = $("#list47").children("tbody").children("#"+rowid).children(":first").html();

			var dimension1Name = $(".ui-jqgrid-htable").children("thead").children("tr").children("th:first-child").attr("id");
			dimension1Name = dimension1Name.replace("list47_","");
			var dimension2 = $(".viewable1").attr("id");
			var text2 = $("#"+dimension2+"sSelectBoxItText").text();
			var di_name="";
			if (dimension2 == "drop11") {
				di_name="Country";
			}
			if (dimension2 == "drop12") {
				di_name="Product";
			}
			if (dimension2 == "drop13") {
				di_name="Year";
			}
			if (dimension2 == "drop14") {
				di_name="Company";
			}
			var dimension3 = $(".viewable2").attr("id");
			var text3 = $("#"+dimension3+"sSelectBoxItText").text();
			
			var di_name3="";
			if (dimension3 == "drop21") {
				di_name3="Country";
			}
			if (dimension3 == "drop22") {
				di_name3="Product";
			}
			if (dimension3 == "drop23") {
				di_name3="Year";
			}
			if (dimension3 == "drop24") {
				di_name3="Company";
			}

    		return {
    			     dimension1Val:retVal,
    			     dimension1Name:dimension1Name,
    			     dimension2Val:text2.replace(/^\s+|\s+$/g, ''),
    			     dimension2Name:di_name,
    			     dimension3Val:text3.replace(/^\s+|\s+$/g, ''),
    			     dimension3Name:di_name3,
    			     dimension4Val:$("#drop31sSelectBoxItText").text(),
    			     dimension4Name:"PorS",
    			     dimension5Val:celname,
    			     dimension5Name:"Year",
    			     FactVal:value
    		      } 
    	//	} 
    	//	else 
    	//	{ return {} } 
    		} ,
      	rowNum: 30,
      	rowList: [10,20,30],
         	colNames:cols,
         	colModel:colModels3,
         	pager: "#plist47",
         	viewrecords: true,
         	caption: mylocalTitle,
         	hidegrid:false,
         	footerrow: true, 
         	 ignoreCase:true,
         	userDataOnFooter: true
      });
      
      jQuery("#list47").jqGrid('navGrid','#plist47',{edit:false,add:false,del:false});
      
      $("#filter").on("click",function(){
    	  
    	  $("#dialogFilter").dialog("open");
    	  
    	  $("#dialogFilter").removeClass("ui-dialog-content");
    	  $(".ui-dropdownchecklist-text").css("font-weight","lighter");
    	  $(".ui-widget-header").css("background","#FFFF00");
    	  $(".ui-widget-header").css("color","black");
    	  $(".ui-widget-header").css("border","1px solid black");
    	  $(".ui-widget-header").css("font-weight","normal");
       //   jQuery("#list47").jqGrid('searchGrid', 
       	//       {sopt:['eq','ne','cn','bw','bn','gt','lt','the','ge','in','ni','en','ew','nc','noo','nn'], multipleSearch:true, ignoreCase:true}
       //	);
       

    	  
    	  
      });

 
    //  $("#wholescreen").fadeIn();
      
	   $('#gbox_list47').fadeIn();
	   $('#list47').fadeIn();


/*
	   $("#gbox_list47").closest("div.ui-jqgrid-view")
	    .children("div.ui-jqgrid-titlebar")
	    .css("text-align", "center")
	    .children("span.ui-jqgrid-title")
	    .css("float", "none");
	   
	   $("#list47").closest("div.ui-jqgrid-view")
	    .children("div.ui-jqgrid-titlebar")
	    .css("text-align", "center")
	    .children("span.ui-jqgrid-title")
	    .css("float", "none");
	   $(".ui-jqgrid-titlebar").css("color","black");


			*/
			
  		 //   var newHeight = $(window).height() + "px";
		 //   $("body").css("height", newHeight);
		    
	//Every resize of window
//$(window).resize(function() {
 //   var newHeight =$(window).height()  + "px";
 //   $("body").css("height", newHeight);
//});
	
	
	
			
			$("#list47").jqGrid('footerData', 'set', 
					totals.myTotals[0]); 
			
			 $('.footrow').children("td").removeClass("colorWhite"); 
			 
		//	 var myval = $("span.ui-jqgrid-title").text();
		//	 $("#myTitle").text(myval);
			 
			 $(".ui-jqgrid-titlebar").remove();
			
			 
			 $('#list47').setGridWidth($('#beans').width());
			 
			 $(window).resize(function() {
				 $('#list47').setGridWidth($('#beans').width());
				});
			 
			 
			 
			  $('.footrow').children('td').each(function( index ) {


				  if ( $(this).text()==" " || $(this).text()=='\xa0'){
					  $(this).text("0"); 
				  }
				  
				});


		     	 function validateDates() {
		     		 var testing ="";
		     		 if ($("#fromdate").val()=="-1" && $("#todate").val()=="-1"){
		     			 return "";
		     		 }
		     		 if ($("#fromdate").val()=="-1" ||$("#todate").val()=="-1" ){
		     			 if ($("#fromdate").val()=="-1"){
		     				testing ="&dateParm= and a.year <="+$("#todate").val() + " "; 
		     			 } else {
		     				testing ="&dateParm= and a.year >="+$("#fromdate").val() + " ";  
		     			 }
		     		 }else{
		     			 if (parseInt($("#fromdate").val())> parseInt($("#todate").val())){
		     				 testing="todate must be greater or equal to fromdate";
		     			 }
		     			 if ($("#fromdate").val()== $("#todate").val()){
		     				 testing ="&dateParm= and a.year ="+$("#todate").val() + " ";
		     			 }
		     			 if (parseInt($("#fromdate").val())< parseInt($("#todate").val())){
		     				testing = "&dateParm= and a.year between  "+$("#fromdate").val() + " and "+ $("#todate").val() + " ";
		     			}
		     		 }

			   
		    		 return testing;
		    	}
		     	 
		     	 

		     	 
			
  });
  
