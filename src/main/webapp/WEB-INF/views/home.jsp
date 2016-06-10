<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body style="text-align: center">
	<form action="/ComponentInfo/services/getcomponentinfo" method="GET">
		keyword: <br>
		<input type="text" name="keyword"><br>
		document type:<br>
		<select multiple name="componentType">
			<option value="selectall">--Select All--</option>
			<option value="footnote">Footnote</option>
			<option value="bridgehead">SubHead</option>
			<option value="para">Text</option>
			<option value="note">Note</option>
			<option value="table">Table</option>
			<option value="graphic">Image</option>
			<option value="entity">Variables</option>
		</select> 
		<br>
		<input type="submit" value="Submit">
	</form>

	<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
