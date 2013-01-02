<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/spring.tld" prefix="spring" %>

<!DOCTYPE HTML>
<body>
	<div>
		<form id='form-content' action="/Ideas/register" method="post" >

			<label for='name'>Name</label>
			<div class="inputPaddingWrap"> 
				<input id='name' class='input required name ' type="text" name="name" maxlength="50" />
			</div>
			<label>Email</label> 
			<div class="inputPaddingWrap">
				<input type="email" class='input required email' name="mail" id="mail" name="mail" maxlength="25"/>
			</div>
			<div class="errorMessage">Please complete the highlighted fields</div>
			<input type="submit" value="register" class="linkButton" />
		</form>
	</div>
</body>
