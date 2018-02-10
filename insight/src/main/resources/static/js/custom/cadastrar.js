$(document).ready(function() {

	var data = $('#todas-tags').val();
	
	data = data.replace('[', '');
	data = data.replace(']', '');
	
	var array = data.split(',');
	
	$('#tags-input').val(array);

	if (window.location.href.indexOf("editar") >= 0) {
		window.history.pushState("", "", '/portifolio/cadastrar');
	}
	
});

$('#tags-input').on('change', function() {
	
	var listaTags = $('#tags-input').val();

	$.ajax({
		type : 'POST',
		url : '/portifolio/tags',
		data : {
			'tags' : listaTags
		},
		success : function(data) {

		}
	});
});

$('#file').on('change', function() {
	
	 var arquivo = new FormData();
	 arquivo.append("file", file.files[0]);
	 
	  $.ajax({
	    url: '/portifolio/upload',
	    cache: false,
	    contentType: false,
	    processData: false,
	    data: arquivo,
	    type: 'post',
	    success: function(data) {

	    }
	  });
});