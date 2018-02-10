<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Register</title>
<script>

        // Put event listeners into place
        window.addEventListener("DOMContentLoaded", function () {
            // Grab elements, create settings, etc.
            var canvas = document.getElementById("canvas"),
                    context = canvas.getContext("2d"),
                    video = document.getElementById("video"),
                    videoObj = {"video": true},
            errBack = function (error) {
                console.log("Video capture error: ", error.code);
            };

            // Put video listeners into place
            if (navigator.getUserMedia) { // Standard
                navigator.getUserMedia(videoObj, function (stream) {
                    video.src = stream;
                    video.play();
                }, errBack);
            } else if (navigator.webkitGetUserMedia) { // WebKit-prefixed
                navigator.webkitGetUserMedia(videoObj, function (stream) {
                    video.src = window.webkitURL.createObjectURL(stream);
                    video.play();
                }, errBack);
            } else if (navigator.mozGetUserMedia) { // WebKit-prefixed
                navigator.mozGetUserMedia(videoObj, function (stream) {
                    video.src = window.URL.createObjectURL(stream);
                    video.play();
                }, errBack);
            }

            // Trigger photo take
            document.getElementById("snap").addEventListener("click", function () {
                context.drawImage(video, 0, 0, 400, 200);
                document.getElementById('canvasImg').src = canvas.toDataURL("image/png");
              document.getElementById('ImageData').value = canvas.toDataURL("image/png");
              document.getElementById('snap').style.display="none";   
              document.getElementById('video').style.display = "none";  // hide the live image video portin after click on take picture
            });



        }, false);

    </script>
</head>
<body>
	<h1>Registration Form</h1>
	
	<c:if test="${errmsg != null}">
		<c:out value="${errmsg}"/>
	</c:if>
	<video id="video" width="400" height="200" autoplay></video>
    <button id="snap">Capture</button>
	<form action="/SpringMVCBasic/submitForm.html" method="post">
 <canvas id="canvas" width="400" height="200"  name="ImageFile1" style="display: none;"></canvas>
		<input type="hidden" name="ImageData" id="ImageData" />
        <img id="canvasImg" name="ImageFile"><img> 
    
		<table>
			<tr>

				<td>Name :</td>
				<td><input type="text" name="Name" /></td>

			</tr>

			<tr>

				<td>Email Id :</td>
				<td><input type="text" name="Email" /></td>

			</tr>

			<tr>

				<td>Password :</td>
				<td><input type="password" name="password" /></td>

			</tr>

			<tr>

				<td>Re-enter Password :</td>
				<td><input type="password" name="confpassword" /></td>

			</tr>

			<tr>

				<td>Mobile :</td>
				<td><input type="text" name="Mobile" /></td>

			</tr>
		</table>
		<table>
			<tr>
				<td>Address :</td>
			</tr>

			<tr>
				<td>Country: <input type="text" name="Address.country" /></td>
				<td>City: <input type="text" name="Address.city" /></td>
				<td>Street: <input type="text" name="Address.street" /></td>
				<td>Pin-code: <input type="text" name="Address.pincode" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="submit" /></td>
			</tr>

		</table>

	</form>
</body>
</html>