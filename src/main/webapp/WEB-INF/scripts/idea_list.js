function vote(action, comment, data) {
    votedElementId = data.currentTarget.dataset.id;
    trSelected = $("tr:contains('"+votedElementId+"')")[0];
    dataSelected = ideasListTable.fnGetData(trSelected);
    actionType=action;
    $.ajax({
        type: 'POST',
        url: '/Ideas/vote/saveVote/' + data.currentTarget.dataset.id,
        data : {comment:comment, type: action},
        success: function(data, msg, event) {
            if (actionType === "POSITIVE") {
                tdSelected = $($(trSelected).children()[3]);
                /* TODO consider the child votes */
                $(tdSelected).html(parseInt(tdSelected.html()) + 1);
            } else {
                tdSelected = $($(trSelected).children()[4]);
                /* TODO consider the child votes */
                $(tdSelected).html(parseInt(tdSelected.html()) +1);
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("an error occured " + errorThrown);
        }
    });
}

function startIdea(id) {
    $.ajax({
        type: 'POST',
        url: '/Ideas/ideas/start/' + id,
        success: function(dataSucess, msg, event) {
            $("#startMessages p").html('');
            $("#startMessages").append('<p class="notification">Idea started</p>');
            $("#ideaDetails").dialog( { hide: {effect: 'fade', speed: 5000, duration: 5000} } );
            $("#ideaDetails").dialog( "close" );
            // TODO change state to the column.
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("an error occured " + errorThrown);
        }
    });
}