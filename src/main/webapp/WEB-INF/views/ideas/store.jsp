<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<% response.sendRedirect("/Ideas"); %>

<html>
<head>
	<title>stored</title>
</head>
<body>
	<jsp:include page="../general/navigation.jsp"></jsp:include>
</body>
</html>