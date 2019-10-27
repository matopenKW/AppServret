
$(function(){
	$(document).on('click','#return', function(){
		var form = $('form')[0];
		form.action += "/minutesList";
		form.submit();
	});

	$(document).on('click', '#btnUpdate', function(){
		$('#mode').val('UPDATE');
		var form = $('form')[0];
		form.action += "/edit";
		form.submit();
	});

	$(document).on('click', '#btnDelete', function(){
		$('#mode').val('DELETE');
		var form = $('form')[0];
		form.action += "/edit";
		form.submit();
	});

	$(document).on('click', '#btnAddSyain', function(){
		openModal();
	});

	$(document).on('click', '#btnModalChoice', function(){

		var $ol = $('#participant-list ol');
		$.each($('#content-list ol li'), function(){
			if($(this).children('[type="checkbox"]').prop('checked')){
				$ol.append($(this).clone());
			}
		});

		// チェックを全て外す
		$('.participant-list ol li [type="checkbox"]').prop('checked', false);

		closeModal();
	});

	$(document).on('click', '#btnDeleteSyain', function(){
		$.each($('#participant-list ol li'), function(){
			if($(this).children('[type="checkbox"]').prop('checked')){
				$(this).remove();
			}
		});
	});
});