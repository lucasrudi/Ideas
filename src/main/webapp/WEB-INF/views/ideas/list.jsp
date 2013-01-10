<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<title>List Ideas</title>
<script type="text/javascript" src="<c:url value="../static/scripts/jquery-1.8.3.js" /> "></script>
<script type="text/javascript" src="<c:url value="../static/scripts/json.min.js" /> "></script>
<script type="text/javascript" src="<c:url value="../static/scripts/jquery-ui-1.9.2.custom.min.js" /> "></script>
<script type="text/javascript" src="<c:url value="../static/scripts/idea_list.js" /> "></script>
<script type="text/javascript" src="<c:url value="../static/scripts/jquery.datatables.min.js" /> "></script>
<link rel="Stylesheet" type="style" href="../static/css/ideas.css"/>
<link rel="Stylesheet" type="style" href="../static/css/demo.css"/>
<link rel="Stylesheet" type="style" href="../static/css/jquery-ui.css"/>
</head>

<body>
    <jsp:include page="../general/navigation.jsp"></jsp:include>
    <div class="container">
        <h1>Ideas</h1>
        <div id="list">
            <table class="display" id="ideas_list">
            </table>
        </div>
        <div style="display:none">
            <form id='voteForm'>
                <input id="type" type="hidden" />
                <input id="comment" type="hidden" value="acomment "/>
            </form>
        </div>
    </div>
    <div id="dialog">
        <input type="text" id="commentDialog" />
    </div>
    <div id="ideaDetails">
        <div id="ideaDetailsContainer">
        </div>
        <p>Start the Idea</p>
        <div id="startMessages"></div>
    </div>
</body>

<script type="text/javascript">
$(document).ready(function() {
    initPage();
});
</script>

</html>
