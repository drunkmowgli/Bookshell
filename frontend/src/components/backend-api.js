import axios from 'axios'

export const HTTP = axios.create(
    {
        baseURL: '/api/v1'
    });


export default {
    getBooks() {
        return HTTP.get('/books');
    },
    getBook(bookId) {
        return HTTP.get('/books/' + bookId)
    },
    deleteBook(bookId) {
        return HTTP.delete('/books/' + bookId + '/delete')
    },
    getComments(bookId) {
        return HTTP.get('/books/' + bookId +'/comments')
    }
}