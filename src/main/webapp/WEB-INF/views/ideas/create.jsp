<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <title>Create Idea</title>
        <script type="text/javascript" src="<c:url value="../static/scripts/jquery-1.4.min.js" /> "></script>
        <script type="text/javascript" src="<c:url value="../static/scripts/json.min.js" /> "></script>
        <script type="text/javascript" src="<c:url value="../static/scripts/idea_create.js" /> "></script>
    </head>
    <body>
        <div class="container">
            <h1>
                Create Idea
            </h1>
            <div class="span-12 last">
                <form:form modelAttribute="ideas" action="store" method="post">
                    <fieldset>
                        <legend>Ideas Fields</legend>
                        <p>
                            <form:label for="title" path="title" cssErrorClass="error">Title</form:label><br/>
                            <form:input path="title" /><form:errors path="title" />
                        </p>
                        <p>
                            <form:label for="description" path="description" cssErrorClass="error">Description</form:label><br/>
                            <form:input path="description" /><form:errors path="description" />
                        </p>
                        <p>Tags</p><br/>
                        <input type="text" id="tagstring" name="tagstring" /> <p class="comment">comma separated tags</p>
                        <p>
                            <input id="create" type="submit" value="Create" />
                        </p>
                    </fieldset>
                </form:form>
            </div>
        </div>
        <div id="mask" style="display: none;"></div>
        <div id="popup" style="display: none;">
            <div class="span-8 last">
                <h3>Idea Created Successfully</h3>
                <a href="#" onclick="closePopup();">Close</a>
            </div>
        </div>
    </body>
 
    <script type="text/javascript">
        $(document).ready(function() {
            $("#ideas").submit(function() {
                var ideas = $(this).serializeObject();
                $.postJSON("store", ideas, function(data) {
                    $("#assignedId").val(data);
                    showPopup();
                });
                return false;
            });
        });

        $( "#tag" ).autocomplete({source: function( request, response ) {
            $.getJSON( "search.php", {
                term: extractLast( request.term )
              }, response );
            },
            select: function( event, ui ) {
                var terms = split( this.value );
                // remove the current input
                terms.pop();
                // add the selected item
                terms.push( ui.item.value );
                // add placeholder to get the comma-and-space at the end
                terms.push( "" );
                this.value = terms.join( ", " );
                return false;
              }
        });
    </script>

</html>
