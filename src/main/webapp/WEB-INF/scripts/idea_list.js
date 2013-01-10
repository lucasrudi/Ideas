function vote(action, comment, data) {
    trSelected = $(data.target).closest('tr') [0];
    votedElementId = $('#ideas_list').dataTable().fnGetData( trSelected)[0];
    dataSelected = ideasListTable.fnGetData(trSelected);
    actionType=action;
    $.ajax({
        type: 'POST',
        url: '/Ideas/vote/saveVote/' + votedElementId,
        data : {comment:comment, type: action},
        success: function(data, msg, event) {
            if (actionType === "POSITIVE") {
                /* TODO consider the child votes */
                $( $(trSelected).children()[3] ).html( parseInt($( $(trSelected).children()[3]).text()) +1 );
            } else {
                /* TODO consider the child votes */
                $( $(trSelected).children()[4] ).html( parseInt($( $(trSelected).children()[4]).text()) +1 );
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

function initRowFunctions() {
    $('.ideaRow').draggable();
    $('.ideaRow').droppable({
        accept: ".ideaRow",
        activeClass: "ui-state-hover",
        hoverClass: "ui-state-active",
        drop: function( event, ui ) {
            origin = $('#ideas_list').dataTable().fnGetData(ui.draggable[0]).id;
            destination =$('#ideas_list').dataTable().fnGetData($(this)[0]).id;
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

    $('.ideaRow').click(function(data) {
        rowData = $('#ideas_list').dataTable().fnGetData(data.currentTarget);
        id = rowData[0];
        if (rowData[1] === 'AVAILABLE') {
            $("#ideaDetails").data("id", id).dialog("open");
        }
    });

    $('.voteDown').click(function(data) {
        dataSelected = data;
        data.stopPropagation();
        $("#dialog").dialog( {
            close: function(dialogdata) {
                vote('NEGATIVE', $("#commentDialog").val(), dataSelected);
            }
        });
        $("#dialog").dialog( "open");
    });

    $('.voteUp').click(function(data, event) {
        dataSelected = data;
        data.stopPropagation();
        $("#dialog").dialog( {
            close: function(dialogdata) {
                vote('POSITIVE', $("#commentDialog").val(), dataSelected);
                $("#commentDialog").val('')
            }
        });
        $("#dialog").dialog( "open");
    });
}

function initPage() {
    ideasListTable = $('#ideas_list').dataTable({
            "aoColumnDefs": [
                              { "fnRender": function (o, val) { return o.aData.id;},"sDefaultContent": "", "bVisible": false, "aTargets": [ 0 ] } ,
                              { "fnRender": function (o, val) { return o.aData.title;}, "sDefaultContent": "", "sTitle": "Title", "aTargets": [ 1 ] } ,
                              { "fnRender": function (o, val) { return o.aData.status;}, "sDefaultContent": "", "sTitle": "Status", "aTargets": [ 2 ] } ,
                              { "fnRender": function (o, val) { return o.aData.description;}, "sDefaultContent": "", "sTitle": "Description", "aTargets": [ 3 ] } ,
                              { "fnRender": function (o, val) { return o.aData.positiveVotes + " - (" + o.aData.agregattedPositivePoints + ")";}, "sDefaultContent": "", "sTitle": "Positive Votes", "aTargets": [ 4 ] } ,
                              { "fnRender": function (o, val) { return o.aData.negativeVotes + " - (" + o.aData.agregattedNegativePoints + ")";}, "sDefaultContent": "", "sTitle": "Negative Votes", "aTargets": [ 5 ] } ,
                              { "sTitle": null, "bSortable": false, "fnRender": function (o, val) { return "";}, "sDefaultContent": "", "aTargets": [ 6 ], "sClass" : "voteUp" } ,
                              { "sTitle": null, "bSortable": false, "fnRender": function (o, val) { return "";}, "sDefaultContent": "", "aTargets": [ 7 ], "sClass" : "voteDown" } ,
                            ],
            "bProcessing": true,
            "bServerSide": false,
            "bDeferRender": true,
            "sAjaxDataProp": "ideas",
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                $(nRow).addClass('ideaRow');
                return nRow;
            },
            "fnDrawCallback": function( oSettings ) {
              initRowFunctions();
            },
            "sAjaxSource": '/Ideas/ideas/getActiveIdeas'
    });

    $("#dialog").dialog({ 
        autoOpen: false,
        show: "blind",
        hide: "explode",
        draggable: false,
        buttons: [ { text: "Ok", click: function() { $( this ).dialog( "close" ); } }]
    });
    
    $("#ideaDetails").dialog({ 
        title: "Start the Idea",
        autoOpen: false,
        show: "blind",
        hide: "explode",
        draggable: false,
        buttons: [ { text: "Ok", click: function(dataDialog, ui) { 
            startIdea($( this ).data("id")); 
            } },
                   { text: "Cancel", click: function() { $( this ).dialog( "close" ); } }
                 ]
    });

}
