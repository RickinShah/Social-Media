<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="<c:url value='/assets/images/icons/favicon.ico'></c:url>"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/vendor/bootstrap/css/bootstrap.min.css'></c:url>">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css'></c:url>">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/vendor/animate/animate.css'></c:url>">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/vendor/css-hamburgers/hamburgers.min.css'></c:url>">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/vendor/select2/select2.min.css'></c:url>">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/css/util.css'></c:url>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/css/main.css'></c:url>">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">

				<form class="login100-form validate-form" id="form">
					<span class="login100-form-title">
						Create Account
					</span>

					<div class="wrap-input100 validate-input" >
						<input class="input100" type="text" name="name" placeholder="Name">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>
					<div class="wrap-input100 validate-input" >
						<input class="input100" type="text" name="username" placeholder="Username">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input" >
						<input class="input100" type="text" name="email" id="email" placeholder="E-mail">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input" >
						<input class="input100" type="text" name="dob" id="dob" placeholder="Date Of Birth" onfocus="(this.type='date')" onblur="(this.type='text')">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input" >
						<input class="input100" type="text" name="age" id="age" placeholder="Age" min="1" max="150">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Password is required">
						<input class="input100" type="password" name="password" id="password" placeholder="Password">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					<div class="wrap-input100 validate-input" data-validate = "Password is required">
						<input class="input100" type="password" name="password1" id="password1" placeholder="Re-enter Password" onkeyup="verifyPass()">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					<div class="text-center p-t-12">
							<span id="msg" style="color: red">&nbsp;</span>
					</div>

					
					<div class="container-login100-form-btn">
						<button class="login100-form-btn" id="submitbtn">	
							Create Account
						</button>
					</div>

					<input type="hidden" id="action" name="action" value="createAccount">
				</form>
			</div>
		</div>
	</div>
	
	

	
<!--===============================================================================================-->	
	<script src="<c:url value='/assets/vendor/jquery/jquery-3.2.1.min.js'></c:url>"></script>
<!--============================================================================================-->
	<script src="<c:url value='/assets/vendor/bootstrap/js/popper.js'></c:url>"></script>
	<script src="<c:url value='/assets/vendor/bootstrap/js/bootstrap.min.js'></c:url>"></script>
<!--===============================================================================================-->
	<script src="<c:url value='/assets/vendor/select2/select2.min.js'></c:url>"></script>
<!--===============================================================================================-->
	<script src="<c:url value='/assets/vendor/tilt/tilt.jquery.min.js'></c:url>"></script>
	<script >
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
<!--===============================================================================================-->
	<script src="assets/js/main.js"></script>

	<script type="text/javascript">
		$("#submitbtn").click(function(event) {
		    event.preventDefault(); // Prevent the default form submission behavior
			$.ajax ({
				url: '/Social_Media/login',
				method: 'POST',
				data: $('#form').serialize(),
				success: function(response) {
					alert(response.msg);
					if(response.success)
						window.location.href = "/Social_Media/";
				},
			});
		});
		function verifyPass() {
			var password = document.getElementById("password").value;
			var password1 = document.getElementById("password1").value;
			if(password1 != password)
				document.getElementById("msg").innerHTML = "Password doesn't match";
			else
				document.getElementById("msg").innerHTML = "&nbsp;";
		}
		
		function passVisible() {
			var visible = document.getElementById("password");
			if(visible.type == "password")
				visible.type = "text";
			else
				visible.type = "password";
		}

	</script>

</body>
</html>