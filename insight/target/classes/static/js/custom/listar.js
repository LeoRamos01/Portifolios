$(document).ready(function() {

	var data = $('#todas-tags').val();
	
	var array = data.split(',');
	
	$('#tags-input').val(array);
	
});

$('#confirmar-exclusao').on('click', function(event) {
	
	var id = $('#idExcluir').val();
	
	$.ajax({
		type : 'DELETE',
		url : '/portifolio/excluir/' + id,
		cache : false,
		success : function(data) {
			location.reload();
		}
	});
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

$('#baixar-tudo').on('click', function(event) {

	event.stopPropagation();
	event.preventDefault();

	$.ajax({
		type : 'GET',
		url : '/portifolio/download/all',
		cache : false,
		success : function(data) {
			var win;
			
			win = window.open("", "_blank");
	        win.document.body.innerHTML = JSON.stringify(data);
		}
	});

});


function setId(id) {
	$('#idExcluir').val(id);
	
	$('#exclusaoModal').modal('toggle');
	$('#exclusaoModal').modal('show');
}


function detalhe (id) {
	
	var input = '.desc-js[data-id=' + id + ']';
	
	var descricao =$(input).val()
	
	$('#descricao').text(descricao);
	
	$('#detalhe').modal('toggle');
	$('#detalhe').modal('show');
}

function baixar (id) {
	$.ajax({
		type : 'GET',
		url : '/portifolio/download/' + id,
		cache : false,
		success : function(data) {
			var win;
			
			win = window.open("", "_blank");
	        win.document.body.innerHTML = JSON.stringify(data);
		}
	});
}