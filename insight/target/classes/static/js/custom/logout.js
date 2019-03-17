function logout() {
	var login = window.location.origin + '/login';
	window.open(login, '_self', 'location=no');
}