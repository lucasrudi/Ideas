<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<title>My Profile</title>
<script type="text/javascript" src="<c:url value="../static/scripts/jquery-1.8.3.js" /> "></script>
<script type="text/javascript" src="<c:url value="../static/scripts/json.min.js" /> "></script>
<script type="text/javascript" src="<c:url value="../static/scripts/jquery-ui-1.9.2.custom.min.js" /> "></script>
<script type="text/javascript" src="<c:url value="../static/scripts/profile.js" /> "></script>
<script type="text/javascript" src="<c:url value="../static/scripts/jquery.datatables.min.js" /> "></script>
<link rel="Stylesheet" type="style" href="../static/css/ideas.css"/>
<link rel="Stylesheet" type="style" href="../static/css/demo.css"/>
<link rel="Stylesheet" type="style" href="../static/css/jquery-ui.css"/>
</head>

<body>
    <jsp:include page="../general/navigation.jsp"></jsp:include>
    <div class="container">
    <div id="list">
            <table class="display" id="ideas_list">
            </table>
            <table class="display" id="pending_request">
            </table>
        </div>
    </div>
    <div id="dialog">
    </div>
</body>

<script type="text/javascript">
$(document).ready(function() {
    initPage();
});
</script>

</html>
