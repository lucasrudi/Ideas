function deleteIdea(data) {
	$.ajax({
        type: 'DELETE',
        url: '/Ideas/ideas/delete/' + data.currentTarget.dataset.id,
        success: function(dataResponse, msg, event) {
        	$(data.currentTarget).closest('tr').hide(1000);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("an error occured " + errorThrown);
        }
    });
};

function acceptMerge(data) {
	$.ajax({
        type: 'POST',
        url: '/Ideas/ideas/mergeAccept/' + data.currentTarget.dataset.id,
        success: function(dataResponse, msg, event) {
        	$(data.currentTarget).closest('tr').hide(1000);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("an error occured " + errorThrown);
        }
    });
};

function rejectMerge(data) {
	$.ajax({
        type: 'POST',
        url: '/Ideas/ideas/mergeReject/' + data.currentTarget.dataset.id,
        success: function(dataResponse, msg, event) {
        	$(data.currentTarget).closest('tr').hide(1000);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("an error occured " + errorThrown);
        }
    });
};