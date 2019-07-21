function ajaxGetAllBooks() {
    $.get('/api/v1/books').done(function (books) {
        books.forEach(function (book) {
            let book_authors = "";
            $.each(book.authors, function (key, value) {
                book_authors += value.name + "<br/>";
                console.log(book_authors); //   debug
            });
            $('tbody').append(`
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book_authors}</td>
                        <td>${book.genre.genreName}</td>
                        <td>
                            <div class="btn-group mr-2" role="group" aria-label="Action Group">
                            <button class='show-book' type='button' class='btn btn-primary' onclick='ajaxGetBook(${book.id})'>Edit</button>
                            <button type='button' class='btn btn-secondary' onclick='ajaxDeleteBook(${book.id})'>Delete</button>
                            </div>
                        </td>
                    </tr>
                `)
        });
        ajaxGetBook();
        ajaxDeleteBook()
    })
}