// User management
$('#saveUserForm').on('submit', function(e) {
	e.preventDefault();
	var data = new FormData($(this)[0]);
	$.ajax({
		url: '/api/admin/user/save',
		type: 'POST',
		enctype: 'multipart/form-data',
		data: data,
		contentType: false,
		processData: false,
		cache: false,
		timeout: 1000000,
		success: function(data) {
			window.location.href = '/admin/list-user';
		},
		error: function(err) {
			iziToast.error({
				position: 'topRight',
				message: err.responseJSON.message,
			});
		}
	});
});