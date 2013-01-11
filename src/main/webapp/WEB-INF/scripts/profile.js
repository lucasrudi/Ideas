function deleteIdea(data) {
    trRow = data;
    rowData = $('#ideas_list').dataTable().fnGetData(data[0]);
    $.ajax({
        type: 'DELETE',
        url: '/Ideas/ideas/delete/' + rowData[0],
        success: function(dataResponse, msg, event) {
            $(trRow).hide(1000);
            //TODO redraw pending merges list
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("an error occured " + errorThrown);
        }
    });
};

function acceptMerge(data) {
    trRow = data;
    rowData = $('#pending_request').dataTable().fnGetData(data[0]);
    $.ajax({
        type: 'POST',
        url: '/Ideas/ideas/mergeAccept/' + rowData[0],
        success: function(dataResponse, msg, event) {
            $(trRow).hide(1000);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("an error occured " + errorThrown);
        }
    });
};

function rejectMerge(data) {
    trRow = data;
    rowData = $('#pending_request').dataTable().fnGetData(data[0]);
    $.ajax({
        type: 'POST',
        url: '/Ideas/ideas/mergeReject/' + rowData[0],
        success: function(dataResponse, msg, event) {
            $(trRow).hide(1000);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("an error occured " + errorThrown);
        }
    });
};

function initPage() {
    ideasListTable = $('#ideas_list').dataTable({
            "aoColumnDefs": [
                              { "fnRender": function (o, val) { return o.aData.id;},"sDefaultContent": "", "bVisible": false, "aTargets": [ 0 ] } ,
                              { "fnRender": function (o, val) { return o.aData.title;}, "sDefaultContent": "", "sTitle": "Title", "aTargets": [ 1 ] } ,
                              { "fnRender": function (o, val) { return o.aData.status;}, "sDefaultContent": "", "sTitle": "Status", "aTargets": [ 2 ] } ,
                              { "fnRender": function (o, val) { return o.aData.description;}, "sDefaultContent": "", "sTitle": "Description", "aTargets": [ 3 ] } ,
                              { "fnRender": function (o, val) { return o.aData.positiveVotes + " - (" + o.aData.agregattedPositivePoints + ")";}, "sDefaultContent": "", "sTitle": "Positive Votes", "aTargets": [ 4 ] } ,
                              { "fnRender": function (o, val) { return o.aData.negativeVotes + " - (" + o.aData.agregattedNegativePoints + ")";}, "sDefaultContent": "", "sTitle": "Negative Votes", "aTargets": [ 5 ] } ,
                              { "sTitle": null, "bSortable": false, "fnRender": function (o, val) { return "";}, "sDefaultContent": "", "aTargets": [ 6 ], "sClass" : "delete" } ,
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
              initIdeasRowFunctions();
            },
            "sAjaxSource": '/Ideas/profile/ideasList'
    });

    pendigRequestTable = $('#pending_request').dataTable({
            "aoColumnDefs": [
                             { "fnRender": function (o, val) { return o.aData.id;},"sDefaultContent": "", "bVisible": false, "aTargets": [ 0 ] } ,
                             { "fnRender": function (o, val) { return o.aData.originIdea.title;}, "sDefaultContent": "", "sTitle": "Your Idea", "aTargets": [ 1 ] } ,
                             { "fnRender": function (o, val) { return o.aData.destinationIdea.title;}, "sDefaultContent": "", "sTitle": "Your Friend's Idea", "aTargets": [ 2 ] } ,
                             { "fnRender": function (o, val) { return "";}, "sDefaultContent": "", "sTitle": "Accept", "aTargets": [ 3 ], "sClass": "accept"} ,
                             { "fnRender": function (o, val) { return "";}, "sDefaultContent": "", "sTitle": "Reject", "aTargets": [ 4 ], "sClass": "reject" } ,
                           ],
           "bProcessing": true,
           "bServerSide": false,
           "bDeferRender": true,
           "sAjaxDataProp": "merge",
           "fnRowCallback": function (nRow, aData, iDisplayIndex) {
               $(nRow).addClass('mergeRow');
               return nRow;
           },
           "fnDrawCallback": function( oSettings ) {
             initMergeRowFunctions();
           },
           "sAjaxSource": '/Ideas/profile/pendingMerges'
    });

}

function initIdeasRowFunctions() {
    $('.delete').click(function(data) {
        rowData = $(data.currentTarget).closest('tr');
        $("#dialog").dialog( {
            autoOpen: false , 
            show: "blind",
            hide: "explode",
            draggable: false,
            buttons: [ { text: "Ok", click: function() { deleteIdea(rowData); $( this ).dialog( "close" ); } },
                       { text: "Cancel", click: function() { $( this ).dialog( "close" ); } }],
            title: "are you sure that you want to delete this idea?",
        });
        $("#dialog").dialog( "open");
    });
}

function initMergeRowFunctions() {
    $('.reject').click(function(data) {
        rowData = $(data.currentTarget).closest('tr');
        rejectMerge(rowData);
    });

    $('.accept').click(function(data) {
        rowData = $(data.currentTarget).closest('tr');
        acceptMerge(rowData);
    });
}
