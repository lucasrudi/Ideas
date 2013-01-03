
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
                tdSelected = $($(trSelected).children()[2]);
                $(tdSelected).html(parseInt(tdSelected.html()) + 1);
            } else {
            	tdSelected = $($(trSelected).children()[3]);
            	$(tdSelected).html(parseInt(tdSelected.html()) +1);
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("an error occured " + errorThrown);
        }
    });
}