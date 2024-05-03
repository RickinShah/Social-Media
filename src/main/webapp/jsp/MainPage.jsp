<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Social Media</title>
</head>
<body>
    <c:if test="${empty name}">
        <c:redirect url="/file?action=3"></c:redirect>
    </c:if>

    <span>
        <c:out value="${username}" /><br /> 
        Welcome, <c:out value="${name}" /> <br />
    </span>
    <a href="/Social_Media/jsp/Posts.jsp" >Home</a>
    <a href="/Social_Media/jsp/Image.jsp" >Upload Image</a>
    <button id="submitbtn" value="logout">Logout</button>
    <jsp:include page="/jsp/Posts.jsp"></jsp:include>

	<script src="<c:url value='/assets/vendor/jquery/jquery-3.2.1.min.js'></c:url>"></script>

	<script type="text/javascript">
	let loading  = false;
	   $("#submitbtn").click(function(event) {
		    event.preventDefault();
			$.ajax ({
				url: '/Social_Media/login?action=logout',
				method: 'GET',
				data: $('#form').serialize(),
				success: function(response) {
					if(response.success) {
					    alert(response.msg);
					    window.location.href = "/Social_Media/";
					}
					else
						document.getElementById("msg").innerHTML = response.msg;
				},
				error: function(response) {
					alert("Failed");
				}
			});
		});
	   $(window).scroll(function () {
		    if (!loading && $(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
		        loading = true;
		        $.ajax({
		            url: '/Social_Media/dashboard',
		            method: 'GET',
		            success: function (data) {
		                const posts = data.postModel;
		                for (let i = 0; i < 3; i++) {
		                    const postContainer = $('<div id="' + posts[i].postId + '"></div>');

		                    $.get('/Social_Media/jsp/PostImage.jsp', function (template) {
		                        const postHtml = template.replace(/\imageName/g, posts[i].postName);
		                        postContainer.append(postHtml);
		                    });

		                    $('#divContainer').append(postContainer);
		                }

		                loading = false;
		            },
		            error: function (xhr, status, error) {
		            	console.error("AJAX request failed: " + textStatus, errorThrown);
		            }
		        });
		    }
		});
	</script>
</body>
</html>