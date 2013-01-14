<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <title>Create Idea</title>
        <script type="text/javascript" src="<c:url value="../static/scripts/jquery-1.8.3.js" /> "></script>
        <script type="text/javascript" src="<c:url value="../static/scripts/jquery-ui-1.9.2.custom.min.js" /> "></script>
        <script type="text/javascript" src="<c:url value="../static/scripts/json.min.js" /> "></script>
        <script type="text/javascript" src="<c:url value="../static/scripts/idea_create.js" /> "></script>
        <script type="text/javascript" src="<c:url value="../static/scripts/json.min.js" /> "></script>
        <link rel="Stylesheet" type="style" href="../static/css/ideas.css"/>
        <link rel="Stylesheet" type="style" href="../static/css/demo.css"/>
        <link rel="Stylesheet" type="style" href="../static/css/jquery-ui.css"/>
    </head>
    <body>
        <jsp:include page="../general/navigation.jsp"></jsp:include>
        <div class="container">
            <h1>
                Create Idea
            </h1>
            <div class="span-12 last">
                <form:form modelAttribute="ideas" action="ideas/store" method="post">
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

        $( "#tagstring" )
        // don't navigate away from the field on tab when selecting an item
        .bind( "keydown", function( event ) {
          if ( event.keyCode === $.ui.keyCode.TAB &&
              $( this ).data( "autocomplete" ).menu.active ) {
            event.preventDefault();
          }
        })
        .autocomplete({
          source: function( request, response ) {
            $.getJSON( "tags/getAllTags", {
                tags: extractLast( request.term )
            }, response );
          },
          search: function() {
            // custom minLength
            var tags = extractLast( this.value );
            if ( tags.length < 2 ) {
              return false;
            }
          },
          focus: function( event, ui ) {
              $( "#tagstring" ).val( ui.item.name );
              return false;
            },
          select: function( event, ui ) {
            var tags = split( this.value );
            // remove the current input
            tags.pop();
            // add the selected item
            tags.push( ui.item.name );
            // add placeholder to get the comma-and-space at the end
            tags.push( "" );
            this.value = tags.join( ", " );
            return false;
          }
        });
    </script>

</html>
