<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Post</title>

<style>
.button-9 {
  appearance: button;
  backface-visibility: hidden;
  background-color: #405cf5;
  border-radius: 6px;
  border-width: 0;
  box-shadow: rgba(50, 50, 93, .1) 0 0 0 1px inset,rgba(50, 50, 93, .1) 0 2px 5px 0,rgba(0, 0, 0, .07) 0 1px 1px 0;
  box-sizing: border-box;
  color: #fff;
  cursor: pointer;
  font-family: -apple-system,system-ui,"Segoe UI",Roboto,"Helvetica Neue",Ubuntu,sans-serif;
  font-size: 100%;
  height: 25px;
  line-height: 1.15;
  margin: 12px 195px 0;
  outline: none;
  overflow: hidden;
  padding: 0px 12px;
  position: relative;
  text-align: center;
  text-transform: none;
  transform: translateZ(0);
  transition: all .2s,box-shadow .08s ease-in;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  width: 20%;
}

.button-9:disabled {
  cursor: default;
}

.button-9:focus {
  box-shadow: rgba(50, 50, 93, .1) 0 0 0 1px inset, rgba(50, 50, 93, .2) 0 6px 15px 0, rgba(0, 0, 0, .1) 0 2px 2px 0, rgba(50, 151, 211, .3) 0 0 0 4px;
}
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
            width: 250px;
            height: 225px;
            margin-top: 10px;
            margin-left: 120px;
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
            <div class="con" >
                <div class="post">
					<form name="form" id="form" enctype="multipart/form-data" >
						<input type="file" accept="image/*" id="postImage" name="postImage" />
						<input type="hidden" id="action" name="action" value="Image"/><br />
						<img id="preview" alt="" class="post-image" style="display: none;"/>
						<button id="imageID" class="button-9">Submit</button>
					</form>
                </div>
                </div>
            <div id="divContainer"></div>

</body>

<script src="<c:url value='/assets/vendor/jquery/jquery-3.2.1.min.js'></c:url>"></script>

<script type="text/javascript">
	$("#imageID").click(function(event) {
		event.preventDefault();
		var form = $('form')[1];
	    var formData = new FormData(form);
		$.ajax ({
			url: '/Social_Media/login',
			method: 'POST',
			contentType: false,
			processData: false,
			data: formData,
			success: function(response) {
				alert(response.msg);
				window.location.href = "/Social_Media/dashboard?action=1";
			},
			error: function(response) {
				alert("Failed");
			}
		});
	});
	postImage.onchange = evt => {
		const [file] = postImage.files;
		if(file) {
			preview.src = URL.createObjectURL(file);
			preview.style.display = "inline";
		}
	}
</script>
</body>
</html>