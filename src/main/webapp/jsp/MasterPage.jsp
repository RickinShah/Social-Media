<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Social Media</title>
    <link href="style.css" rel="stylesheet" />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;400;700&display=swap"
      rel="stylesheet"
    />
        <style>
          body {
            font-family: Poppins;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
          }

          header {
            background-color: #333;
            color: #fff;
            padding: 10px 0;
            text-align: center;
          }

          header h1 {
            margin: 0;
          }

          .container {
            max-width: 800px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
          }

          .user-section {
            text-align: right;
          }

          .user-section a {
            color: #333;
            text-decoration: none;
            padding: 10px;
            margin-right: 10px;
          }

          .user-section a:hover {
            background-color: #eee;
            border-radius: 5px;
          }
          .user-section {
            display: flex;
            justify-content: space-between;
          }
          .user-section img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
          }
        </style>
      </head>
      <body>
        <c:if test="${empty name }">
        <jsp:forward page="/jsp/Login.jsp"></jsp:forward>
        </c:if>
        <form id="form">
        <div class="container">
          <div class="user-section">
            <div>
              <ol>
              <li style="display:flex; align-items:center; justify-content: flex-start; flex-direction: column; ">
                <p>
                Welcome, <b><c:out value="${name }"></c:out></b>
                </p>
              </li>
              </ol>
            </div>
                    <div class="post-actions" style="display: flex; align-content: center; justify-content: center; flex-direction: row;" >
                        <a href="/Social_Media/dashboard?action=1" style="margin: 1rem;">Home</a>
                        <a href="/Social_Media/jsp/MasterPage.jsp?action=2" style="margin: 1rem;">Post</a>
                        <a href="/Social_Media/jsp/MasterPage.jsp?action=3" style="margin: 1rem;">Profile</a>
                    </div>
            <div style="display: flex">
              <p>
                <div onclick='window.location.href="/Social_Media/jsp/MasterPage.jsp?action=3"' style="cursor: pointer;">
                <img src="<c:out value="/Social_Media/file?path=${profilePic }&action=4"></c:out>" alt="" style="margin: 15px 10px 0px 0px; object-fit:cover;height: 3rem; width: 3rem;" /></div>
                <div style="margin: 25px 0px 16px;"><c:out value="${username}"></c:out> 
                <button id="submitbtn" style="border: none; background-color: white; margin: 0px 0px 0px 20px; cursor: pointer;">
                <svg
                    fill="#000000"
                    height="20px"
                    width="20px"
                    version="1.1"
                    id="Capa_1"
                    xmlns="http://www.w3.org/2000/svg"
                    xmlns:xlink="http://www.w3.org/1999/xlink"
                    viewBox="0 0 384.971 384.971"
                    xml:space="preserve"
                  >
                    <g>
                      <g id="Sign_Out">
                        <path
                          d="M180.455,360.91H24.061V24.061h156.394c6.641,0,12.03-5.39,12.03-12.03s-5.39-12.03-12.03-12.03H12.03
                         C5.39,0.001,0,5.39,0,12.031V372.94c0,6.641,5.39,12.03,12.03,12.03h168.424c6.641,0,12.03-5.39,12.03-12.03
                         C192.485,366.299,187.095,360.91,180.455,360.91z"
                        />
                        <path
                          d="M381.481,184.088l-83.009-84.2c-4.704-4.752-12.319-4.74-17.011,0c-4.704,4.74-4.704,12.439,0,17.179l62.558,63.46H96.279
                         c-6.641,0-12.03,5.438-12.03,12.151c0,6.713,5.39,12.151,12.03,12.151h247.74l-62.558,63.46c-4.704,4.752-4.704,12.439,0,17.179
                         c4.704,4.752,12.319,4.752,17.011,0l82.997-84.2C386.113,196.588,386.161,188.756,381.481,184.088z"
                        />
                      </g>
                      <g></g>
                      <g></g>
                      <g></g>
                      <g></g>
                      <g></g>
                      <g></g>
                    </g></svg>
                </button></div>
            </div>
          </div></div></form>
          <c:choose>
            <c:when test="${param.action == 1}">
              <script type="text/javascript">  
              if(performance.navigation.type ==  2){
            	   location.reload(true);
            	}
              </script>
            <jsp:include page="/jsp/ScrollImages.jsp"></jsp:include>
            
            </c:when>
            <c:when test="${param.action == 2}">
            <jsp:include page="/jsp/Image.jsp"></jsp:include>
            </c:when>
            <c:when test="${param.action == 3}">
            <jsp:include page="/jsp/ProfilePicUpload.jsp"></jsp:include>
            </c:when>
          
          </c:choose>
          
         
	<script src="<c:url value='/assets/vendor/jquery/jquery-3.2.1.min.js'></c:url>"></script>
	<script type="text/javascript">
	loading = false;
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
		   console.log(window.location.pathname + window.location.search);
		   if((window.location.pathname + window.location.search) === "/Social_Media/dashboard?action=1") {
		    if (!loading && $(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
		        loading = true;
		        $.ajax({
		            url: '/Social_Media/dashboard',
		            method: 'GET',
		            success: function (data) {
		                const posts = data.postModel;
		                console.log(posts[1].postId);
		                for (let i = 0; i < 3; i++) {
		                    const postContainer = $('<div id="' + posts[i].postId + '"></div>');

		                    $.get('/Social_Media/jsp/RemainingPosts.jsp', function (template) {
		                        const imageName = template.replace(/\imageName/g, posts[i].postName);
		                        const username = imageName.replace(/\usernameOfTheUser/g, posts[i].username);
		                        const profilePic = username.replace(/\profilePicture/g, posts[i].profilePic);
		                        postContainer.append(profilePic);
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
		   }
		});
	   </script>
      </body>
    </html>
    