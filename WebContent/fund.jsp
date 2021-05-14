<%@page import="com.Fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Funds Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery.min.js"></script>
<script src="Components/fund.js"></script>
<style>
body{
background-color:#ddd;
}
header {
  background-color:#666362;
  padding: 0.5px;
  text-align: center;
  font-size: 20px;
  color: white;
  height:102px;
}
/* Style the top navigation bar */
.topnav {
    overflow: hidden;
    background-color:#3BB9FF;	
}

/* Style the topnav links */
.topnav a {
    float: right;
    display: block;
    color: #f2f2f2;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;	
}

/* Change color on hover */
.topnav a:hover {
    background-color: #ddd;
    color: black;	
}
/* Style the footer */
footer {
  background-color:#666362;
  padding: 20px;
  text-align: center; 
}
</style>

 
</head>
<header>
<h2>GADGETBADGET</h2>
  <div class="topnav">
	  <a href="#">PAYMENT</a>
	  <a href="#">PROJECT</a>
	  <a href="fund.jsp">FUNDS</a>
	  <a href="#">REGISTRATION</a>
	  <a href="#">HOME</a>
 </div>
 </header>
</head>
<body>


	<div class="row">
		<div class="col-lg-12">
			<div class="card m-b-32">
				<div class="card-body">
					<H1>Funds Management</H1>
					<br>
					<form id="formFund" name="formFund" method="post"action="index.jsp">

						<label>Funding Agency</label> <input id="FundingAgency"name="FundingAgency" type="text"class="form-control form-control-sm">
						<br> 
						<label>Address</label><input id="FAddress"name="FAddress" type="text"class="form-control form-control-sm">
						<br>
						<label>Phone</label> <input id="FPhone" name="FPhone"type="text" class="form-control form-control-sm"> 
						<br>
						<label>Project ID</label> <input id="FProjectID" name="FProjectID"type="text" class="form-control form-control-sm"> 
						<br>
						<label>Fund</label><input id="Fund" name="Fund"type="text" class="form-control form-control-sm"> 
						<br>
						<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary"> <input type="hidden"id="hidFundIdSave" name="hidFundIdSave" value="">
							 
					</form>
					<br>
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br> <br>
					
					<div id="divFundGrid">
						<%
							Fund funObj = new Fund();
							out.print(funObj.readFunds());
						%>
					</div>
				</div>
</div>
</div>
</div>
<footer>
  <div class="footer1">
    <a href="#">ABOUTUS</a>
    <a href="#">NEED HELP</a>
    <a href="#">CONTACT</a>
    <a href="#">PRIVACY</a>
  </div>

</footer>
	
</body>
</html>