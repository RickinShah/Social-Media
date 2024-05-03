<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile Picture</title>
</head>
<body>
<form name="form" id="form" enctype="multipart/form-data">
    <input type="file" id="image" name="image" />
    <input type="hidden" id="action" name="action" value="uploadProfilePic"/>
    <button id="submitbtn">Submit</button>
</form>


<script src="<c:url value='/assets/vendor/jquery/jquery-3.2.1.min.js'></c:url>"></script>

<script type="text/javascript">
	$("#submitbtn").click(function(event) {
		event.preventDefault();
		var form = $('form')[0];
	    var formData = new FormData(form);
		$.ajax ({
			url: '/Social_Media/login',
			method: 'POST',
			contentType: false,
			processData: false,
			data: formData,
			success: function(response) {
				alert(response.msg);
			},
			error: function(response) {
				alert("Failed");
			}
		});
	});
</script>
</body>
</html>
