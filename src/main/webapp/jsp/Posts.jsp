<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form id="form">
    <div id = "divContainer">
		<c:forEach items="${postModel}" var="post">
			<div id="postsImages">
				<img alt="img" src="/Social_Media/file?path=${post.postName}&action=1" width=500 height=300 />
				<div>
					<button name="like" value="like">👍 Like</button>
					<button name="comment" value="comment">🗨 Comment</button>
					<button name="share" value="share">👍 Like</button>
				</div>
			</div>
	   </c:forEach>
    </div>
    </form>

</body>
</html>