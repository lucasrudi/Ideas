function showPopup() {
    $('#popup').fadeIn('fast');
    $('#mask').fadeIn('fast');
}

function closePopup() {
    $('#popup').fadeOut('fast');
    $('#mask').fadeOut('fast');
    resetForm();
}

function resetForm() {
    $('#ideas')[0].reset();
}