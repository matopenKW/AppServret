
$(function(){
	$(document).on('click','#btnNew', function(){
		$('#mode').val('SELECT');
		$('#targetSeqno').val(0);

		var form = $('form')[0];
		form.action += "/edit";
		form.submit();
	});

	$(document).on('click','.minutes-list li', function(){
		var index = $(this).index('.minutes-list li');
		var targetSeqno = $('.minutes-list li').eq(index).children('[type="hidden"]').val();
		$('#targetSeqno').val(targetSeqno);

		$('#mode').val('SELECT');
		var form = $('form')[0];
		form.action += "/edit";
		form.submit();
	});


});