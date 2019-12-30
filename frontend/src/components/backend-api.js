import axios from 'axios'

export const HTTP = axios.create(
    {
        baseURL: '/api/v1'
    });


export default {
    login() {
        return HTTP.post('/login')
    },
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
        return HTTP.get('/books/' + bookId +'/comments')
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
    addBook(title, authorIds, genre) {
        return HTTP.post('/books', {
            title: title, authors: authorIds, genre: genre
        })
    },
    updateBook(bookId, title, authorsIds, genre) {
        return HTTP.put('/books/' + bookId, {
            title: title, authors: authorsIds, genre: genre
        })
    }
}
