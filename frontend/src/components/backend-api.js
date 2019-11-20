import axios from 'axios'

export const HTTP = axios.create(
    {
        baseURL: '/api/v1'
    });


export default {
    getBooks() {
        return HTTP.get('/books');
    },
    getAuthors() {
        return HTTP.get('/authors');
    },
    getGenres() {
        return HTTP.get('/genres');
    },
    getBook(bookId) {
        return HTTP.get('/books/' + bookId)
    },
    deleteBook(bookId) {
        return HTTP.delete('/books/' + bookId)
    },
    getComments(bookId) {
        return HTTP.get('/books/' + bookId + '/comments')
    },
    addAuthor(authorName) {
        return HTTP.post('/authors', {
            name: authorName
        })
    },
    addComment(comment, bookId) {
        return HTTP.post('/books/' + bookId + '/comments', {
            commentDescription: comment
        })
    },
    addBook(title, authorIds, genreId) {
        return HTTP.post('/books/', {
            title: title, authors: authorIds, genre: genreId
        })
    },
    updateBook(bookId, title, authorIds, genreId) {
        return HTTP.put('/books/' + bookId, {
            title: title, authors: authorIds, genre: genreId
        })
    },
    addGenre(name) {
        return HTTP.post('/genres', {
            name: name
        })
    }
}
