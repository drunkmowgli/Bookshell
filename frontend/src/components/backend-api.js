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
    }
}