<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
  <head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  </head>

  <body>
  
  <p>Query Images:</p>
  <form action="/doimage" method="get" accept-charset="utf-8">
  	<div><input type="submit" value="Query Images" /></div>
  </form>
  
  <p>Upload Images:</p>
  <form action="/doimage" method="post" enctype="multipart/form-data">
	File 1:<input type="file" name="file1"><br>
	File 2:<input type="file" name="file2"><br>
	File 3:<input type="file" name="file3"><br>
	<input type="submit" name="Submit" value="Upload Images">
  </form>
  
  <hr>
  
  <p>Query Addresses:</p>
  <form action="/doaddress" method="get" accept-charset="utf-8">
  	<div><input type="submit" value="Query Addresses" /></div>
  </form>
  
  <p>Create Address:</p>
  <form action="/doaddress" method="post" >
	<table>
		<tr>
			<td><label for="buildingname">Building Name</label></td>
			<td><input type="text" name="buildingname" id="buildingname" size="65"/></td>
		</tr>
		<tr>
			<td><label for="streetname">Street Name</label></td>
			<td><input type="text" name="streetname" id="streetname" size="65"/></td>
		</tr>
		<tr>
			<td><label for="unitno">Unit Number</label></td>
			<td><input type="text" name="unitno" id="unitno" size="20"/></td>
		</tr>
		<tr>
			<td><label for="block">Block</label></td>
			<td><input type="text" name="block" id="block" size="20"/></td>
		</tr>
		<tr>
			<td><label for="postalcode">Postal Code</label></td>
			<td><input type="text" name="postalcode" id="postalcode" size="20"/></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" name="Submit" value="Create Address"></td>
		</tr>
	</table>
  </form>
  
  <hr>
  
  <p>Query Loyalty Promotion:</p>
  <form action="/doloyaltypromotion" method="get" accept-charset="utf-8">
  	<div><input type="submit" value="Query Loyalty Promotion" /></div>
  </form>
  
  <p>Create Loyalty Promotion:</p>
  <form action="/doloyaltypromotion" method="post" >
	<table>
		<tr>
			<td><label for="reward">Reward</label></td>
			<td><input type="text" name="reward" id="reward" size="20"/></td>
		</tr>
		<tr>
			<td><label for="block">Block</label></td>
			<td><input type="text" name="block" id="block" size="20"/></td>
		</tr>
		<tr>
			<td><label for="streetname">Street Name</label></td>
			<td><input type="text" name="streetname" id="streetname" size="20"/></td>
		</tr>
		<tr>
			<td><label for="postalcode">Postal Code</label></td>
			<td><input type="text" name="postalcode" id="postalcode" size="20"/></td>
		</tr>
		<tr>
			<td><label for="tnc">Terms and Conditions</label></td>
			<td><textarea rows="4" cols="50" name="tnc" id="tnc"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" name="Submit" value="Create Loyalty Promotion"></td>
		</tr>
	</table>
  </form>
  
  <hr>
  
  <p>Query Categories:</p>
  <form action="/docategory" method="get" accept-charset="utf-8">
  	<div><input type="submit" value="Query Categories" /></div>
  </form>
  
  <p>Create Category:</p>
  <form action="/docategory" method="post" >
	<table>
		<tr>
			<td><label for="name">Name</label></td>
			<td><input type="text" name="name" id="name" size="65"/></td>
		</tr>
		<tr>
			<td colspan="2" align="left"><input type="submit" name="Submit" value="Create category"></td>
		</tr>
	</table>
  </form>
  
  <hr>
  
  <p>Query Promotion:</p>
  <form action="/dopromotion" method="get" accept-charset="utf-8">
  	<div><input type="submit" value="Query Promotion" /></div>
  </form>
  
  <p>Create Promotion:</p>
  <form action="/dopromotion" method="post" >
	<table>
		<tr>
			<td><label for="title">Title</label></td>
			<td><input type="text" name="title" id="title" size="65"/></td>
		</tr>
		<tr>
			<td><label for="category">Category</label></td>
			<td><input type="text" name="category" id="category" size="20"/></td>
		</tr>
		<tr>
			<td><label for="merchant">Merchant</label></td>
			<td><input type="text" name="merchant" id="merchant" size="20"/></td>
		</tr>
		<tr>
			<td><label for="startdate">Start Date</label></td>
			<td><input type="text" name="startdate" id="startdate" size="20"/></td>
		</tr>
		<tr>
			<td><label for="enddate">End Date</label></td>
			<td><input type="text" name="enddate" id="enddate" size="20"/></td>
		</tr>
		<tr>
			<td><label for="isLive">Is Live</label></td>
			<td><div align="center">
			<input type="radio" name="group1" value="False"> False<br>
			<input type="radio" name="group1" value="True"> True<br>
			</div></td>
		</tr>
		<tr>
			<td><label for="description">Description</label></td>
			<td><textarea rows="4" cols="50" name="description" id="description"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" name="Submit" value="Create Promotion"></td>
		</tr>
	</table>
  </form>
  
  <hr>
  
  <p>Query Merchants:</p>
  <form action="/domerchant" method="get" accept-charset="utf-8">
  	<div><input type="submit" value="Query Merchants" /></div>
  </form>
  
  <p>Create Merchant:</p>
  <form action="/domerchant" method="post" accept-charset="utf-8">
	<table>
		<tr>
			<td><label for="name">Name</label></td>
			<td><input type="text" name="name" id="name" size="65"/></td>
		</tr>
		<tr>
			<td><label for="hasLoyalty">On Loyalty Program</label></td>
			<td><div align="center">
			<input type="radio" name="group1" value="False"> False<br>
			<input type="radio" name="group1" value="True"> True<br>
			</div></td>
		</tr>
		<tr>
			<td><label for="contactNo">Contact Number</label></td>
			<td><input type="text" name="contactNo" id="contactNo" size="65"/></td>
		</tr>
		<tr>
			<td valign="description"><label for="description">Description</label></td>
			<td><textarea rows="4" cols="50" name="description" id="description"></textarea></td>
		</tr>
		<tr>
			<td><label for="promotions">Promotion/s</label></td>
			<td><input type="text" name="promotions" id="promotions" size="65" /></td>
		</tr>
		<tr>
			<td><label for="address1">Address1</label></td>
			<td><input type="text" name="address1" id="address1" size="65" /></td>
		</tr>
		<tr>
			<td><label for="address2">Address2</label></td>
			<td><input type="text" name="address2" id="address2" size="65" /></td>
		</tr>
		<tr>
			<td><label for="loyaltyPromo">Loyalty Promotion</label></td>
			<td><input type="text" name="loyaltyPromo" id="loyaltyPromo" size="20" /></td>
		</tr>
		<tr>
			<td><label for="brandImage">Brand Image/s</label></td>
			<td><input type="text" name="brandImage" id="brandImage" size="65" /></td>
		</tr>
		<tr>
			<td><label for="image">Image/s</label></td>
			<td><input type="text" name="image" id="image" size="65" /></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" value="Create Merchant"/></td>
		</tr>
	</table>
</form>
  
  </body>
</html>