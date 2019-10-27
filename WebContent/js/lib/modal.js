function openModal(){
	$('#modal').show();
}

function closeModal(){
	$('#modal').hide();
}

$(function(){
	$('#btnModalClose').on('click', function(event) {
		closeModal();
	});
});