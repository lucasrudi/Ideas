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
<link rel="Stylesheet" type="style" href="../static/css/jquery-ui-1.9.2.custom.min.css"/>
</head>

<body>
    <jsp:include page="../general/navigation.jsp"></jsp:include>
    <div class="container">
    <div id="list">
            <table class="display" id="ideas_list">
                <thead>
                    <tr>
                        <th width="20%">Title</th>
                        <th width="10%">Status</th>
                        <th width="25%">Description</th>
                        <th width="15%">Positive Votes</th>
                        <th width="15%">Negative Votes</th>
                        <th width="10%">Delete</th>
                        <th width="10%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="idea" items="${ideasList}">
                        <tr>
                            <td>${idea.title}</td>
                            <td>${idea.status}</td>
                            <td>${idea.description}</td>
                            <td>${idea.positiveVotes} <c:if test="idea.agregattedPositivePoints > 0"> (<c:out value="${idea.agregattedPositivePoints}"/>) </c:if> </td>
                            <td>${idea.negativeVotes} <c:if test="idea.agregattedNegativePoints > 0"> (<c:out value="${idea.agregattedNegativePoints}"/>) </c:if></td>
                            <td class="delete" data-id="${idea.id}" onclick="javascript:void(0)" ><p/></td>
                            <td hidden="true">${idea.id}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <table class="display" id="pending_request">
                <thead>
                    <tr>
                        <th width="35%">Your Idea</th>
                        <th width="35%">Your Friends Idea</th>
                        <th width="15%">Accept</th>
                        <th width="15%">Reject</th>
                        <th width="0"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="merge" items="${pendingMerges}">
                        <tr>
                            <td>${merge.originIdea.title}</td>
                            <td>${merge.destinationIdea.title}</td>
                            <td class="accept" data-id="${merge.id}" onclick="javascript:void(0)" ><p/></td>
                            <td class="reject" data-id="${merge.id}" onclick="javascript:void(0)" ><p/></td>
                            <td hidden="true">${idea.id}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div id="dialog">
    </div>
</body>

<script type="text/javascript">
$(document).ready(function() {
    ideasListTable = $('#ideas_list').dataTable();
    ideasListTable = $('#pending_request').dataTable();
    $('.delete').click(function(data) {
        $("#dialog").dialog( {
            autoOpen: false , 
            show: "blind",
            hide: "explode",
            draggable: false,
            buttons: [ { text: "Ok", click: function() { deleteIdea(data); $( this ).dialog( "close" ); } },
                       { text: "Cancel", click: function() { $( this ).dialog( "close" ); } }],
            title: "are you sure that you want to delete this idea?",
        });
        $("#dialog").dialog( "open");
    });
    $('.reject').click(function(data) {
        rejectMerge(data);
    });

    $('.accept').click(function(data) {
        acceptMerge(data);
    });
});
</script>

</html>
