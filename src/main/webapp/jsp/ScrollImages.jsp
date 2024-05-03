<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
          .posts{
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            

          }
          .con {
            max-width: 600px;
            margin: 20px auto;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            
           
        }

        .post {
            border-bottom: 1px solid #eee;
            padding: 20px;
            margin-left: 30px;
        }

        .user-info {
            display: flex;
            align-items: center;
        }

        .user-info img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .user-info h2 {
            margin: 0;
        }

        .post-image {
            width: 500px;
            height: 450px;
            margin-top: 10px;
            object-fit: cover;
        }

        .post-actions {
            margin-top: 10px;
        }

        .post-actions a {
            color: #333;
            text-decoration: none;
            margin-right: 20px;
        }

        .post-actions a:hover {
            color: #ff69b4;
        }
        </style>
</head>
<body>
        <div class="post_section">
		  <c:forEach items="${postModel}" var="post">
            <div class="con" >
                <div class="post">
                    <div class="user-info">
                        <img src="/Social_Media/file?path=${post.profilePic}&action=4" alt="User 1" style="object-fit: cover;">
                        <h3>${post.username }</h3>
                    </div>
                    <img class="post-image" src="/Social_Media/file?path=${post.postName}&action=1" alt="Post 1">
                    <div class="post-actions">
                        <a href="#">Like</a>
                        <a href="#">Comment</a>
                        <a href="#">Share</a>
                    </div>
                </div>
                </c:forEach>
                </div>
            <div id="divContainer"></div>

</body>
</html>