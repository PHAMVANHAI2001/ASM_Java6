$('#createBtn').click(function() {
	console.log("oke");
	$('html, body').animate({
		scrollTop: $('#card-form').offset().top
	}, 1000);
	$('#form :input').prop('readonly', false);
	$('#floatingCategory').prop('disabled', false);
	$('#floatingDiscount').prop('disabled', false);
	$('#saveBtn').prop('disabled', false);
});

$('#saveBtn').click(function(){
	$('#form').submit();
	$('#form').prop('action','/dashboard/products/save');
});

function editProduct(slug) {
	var url = '/dashboard/products/edit?slug=' + slug;
	$.get(url).done(function(data) {
		$('#form').replaceWith(data);
		$('html, body').animate({
			scrollTop: $('#card-form').offset().top
		}, 1000);
		$('#form :input').prop('readonly', false);
		$('#floatingCategory').prop('disabled', false);
		$('#floatingDiscount').prop('disabled', false);
		$('#saveBtn').prop('disabled', false);
		$('#form').prop('action','/dashboard/products/edit');
	});
}