function ajaxGetBook(id) {
    $.ajax({
        url: '/api/v1/books/' + id,
        type: 'get',
        dataType: "json",
        success: function (book) {
            $(".book-id").append("<p>" + book.id + "</p>");
            $(".book-title").append("<p>" + book.title + "</p>");
            $.each(book.authors, function (key, value) {
                $(".book-authors").append("<li>" + value.name + "</li>")
            });
            $.each(book.genres, function (key, value) {
                $(".book-genres").append("<li>" + value.genreName + "</li>")
            })
        }
    });
}