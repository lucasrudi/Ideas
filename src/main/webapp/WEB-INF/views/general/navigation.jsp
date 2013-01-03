<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
</head>
<div class="container">
    <div class="navigation">
        <a href="ideas/createForm" >Create new Idea</a> |
        <a href="ideas/getAll" >List Ideas</a> |
        <a href="ideas/myIdeas" >My Ideas</a>
        <a href="logout" >Logout</a> |
    </div>
</div>