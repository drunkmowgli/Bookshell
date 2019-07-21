function ajaxDeleteBook(id) {
    $.ajax({
        url: '/api/v1/books/' + id + '/delete',
        type: 'delete'
    }).done(function () {
        location.reload(true)
    });
}