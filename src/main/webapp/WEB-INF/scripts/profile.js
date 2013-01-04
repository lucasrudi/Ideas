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