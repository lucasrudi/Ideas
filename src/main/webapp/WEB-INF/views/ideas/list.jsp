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
<link rel="Stylesheet" type="style" href="../static/css/jquery-ui-1.9.2.custom.min.css"/>
</head>

<body>
    <jsp:include page="../general/navigation.jsp"></jsp:include>
    <div class="container">
        <h1>Ideas</h1>
        <div id="list">
            <table class="display" id="data_table">
                <thead>
                    <tr>
                        <th width="20%">Title</th>
                        <th width="10%">Status</th>
                        <th width="25%">Description</th>
                        <th width="20%">Positive Votes</th>
                        <th width="20%">Negative Votes</th>
                        <th width="10%">Positive</th>
                        <th width="10%">Negative</th>
                        <th width="10%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="idea" items="${ideasList}">
                        <tr class="ideaRow">
                            <td>${idea.title}</td>
                            <td>${idea.status}</td>
                            <td>${idea.description}</td>
                            <td>${idea.positiveVotes} <c:if test="idea.agregattedPositivePoints > 0"> (<c:out value="${idea.agregattedPositivePoints}"/>) </c:if> </td>
                            <td>${idea.negativeVotes} <c:if test="idea.agregattedNegativePoints > 0"> (<c:out value="${idea.agregattedNegativePoints}"/>) </c:if></td>
                            <td class="voteUp" data-id="${idea.id}" onclick="javascript:void(0)" ><p/></td>
                            <td class="voteDown" data-id="${idea.id}" onclick="javascript:void(0)" ><p/></td>
                            <td hidden="true">${idea.id}</td>
                        </tr>
                    </c:forEach>
                </tbody>
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
</body>

<script type="text/javascript">
$(document).ready(function() {
    ideasListTable = $('#data_table').dataTable();
    $('.ideaRow').draggable();
    $('.ideaRow').droppable({
        accept: ".ideaRow",
        activeClass: "ui-state-hover",
        hoverClass: "ui-state-active",
        drop: function( event, ui ) {
            origin = ui.draggable[0].cells[7].innerText;
            destination = $(this)[0].cells[7].innerText;
            $(ui.draggable).hide(1000);
            $.ajax({
                type: 'POST',
                url: '/Ideas/ideas/merge',
                data : {"origin": origin, "destination": destination},
                success: function(msg) {
                    document.getElementById("voteForm").reset();
                },
                error : function(XMLHttpRequest, textStatus, errorThrown){
                    alert("an error occured " + errorThrown);
                }
            });
        }
    });
    $("#dialog").dialog({ 
        autoOpen: false , 
        show: "blind",
        hide: "explode",
        draggable: false,
        buttons: [ { text: "Ok", click: function() { $( this ).dialog( "close" ); } }]
    });

    $('.voteDown').click(function(data) {
        $("#dialog").dialog( {
            close: function(dialogdata) {
                vote('NEGATIVE', $("#commentDialog").val(), data);
            }
        });
        $("#dialog").dialog( "open");
    });

    $('.voteUp').click(function(data) {
        $("#dialog").dialog( {
            close: function(dialogdata) {
                vote('POSITIVE', $("#commentDialog").val(), data);
                $("#commentDialog").val('')
            }
        });
        $("#dialog").dialog( "open");
    });
});
</script>

</html>
