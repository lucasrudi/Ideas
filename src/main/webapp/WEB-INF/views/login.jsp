<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/spring.tld" prefix="spring" %>

<!DOCTYPE HTML>
<body>
	<div>
		<form id='form-content' action="/Ideas/login/" method="post" >

			<label for='username'>User Name</label>
			<div class="inputPaddingWrap"> 
				<input id='j_username' class='input required username ' type="text" name="j_username" maxlength="50" />
			</div>
			<label>Password</label> 
			<div class="inputPaddingWrap">
				<input type="password" class='input required' name="j_password" id="j_password" name="j_password" maxlength="25"/>
			</div>
			<div class="errorMessage">Please complete the highlighted fields</div>
			<input type="submit" value="SIGN IN" class="linkButton" />
		</form>
	</div>
</body>
