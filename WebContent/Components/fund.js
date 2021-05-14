$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});




//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateFundForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }

var type = ($("#hidFundIdSave").val() == "") ? "POST" : "PUT";


$.ajax(
		{
		 url : "FundAPI",
		 type : type,
		 data : $("#formFund").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onFundSaveComplete(response.responseText, status);
		 }
		});

});
function onFundSaveComplete(response, status)
{
if (status == "success")
 {
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		
		$("#divFundGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
 	} else if (status == "error")
 	{
 		$("#alertError").text("Error while saving.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while saving..");
 		$("#alertError").show();
 	}
		$("#hidFundIdSave").val("");
		$("#formFund")[0].reset();
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidFundIdSave").val($(this).closest("tr").find('#hidFundIdSave').val());
 $("#FundingAgency").val($(this).closest("tr").find('td:eq(0)').text());
 $("#FAddress").val($(this).closest("tr").find('td:eq(1)').text());
 $("#FPhone").val($(this).closest("tr").find('td:eq(2)').text());
 $("#FProjectID").val($(this).closest("tr").find('td:eq(3)').text());
 $("#Fund").val($(this).closest("tr").find('td:eq(4)').text());
 

});


$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "FundAPI",
		 type : "DELETE",
		 data : "FundId=" + $(this).data("itemid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onDoctorDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onFundDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divFundGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

//CLIENTMODEL=========================================================================
function validateFundForm()
{
	

if ($("#FundingAgency").val().trim() == "")
{
return "Insert FundingAgency.";
}

var letterReg1 = /^[a-zA-Z ]+$/;
var tmpfName =  $("#FundingAgency").val().trim();
if(!tmpfName.match(letterReg1)){
	return "First Letter must have alphabet charaters only...!";
}

if ($("#FAddress").val().trim() == "")
{
return "Insert FAddress.";
}


if ($("#FPhone").val().trim() == "") {
	return "Insert FPhone.";
}
var contactReg = /^\d{10}$/;
var tmpPhone =  $("#FPhone").val().trim();
if(!tmpPhone.match(contactReg)){
	return "Insert a valid Phone Number...!";
}

if ($("#FProjectID").val().trim() == "")
{
return "Insert FProjectID.";
} 


if ($("#Fund").val().trim() == "")
{
return "Insert Fund.";
}

return true;
}


