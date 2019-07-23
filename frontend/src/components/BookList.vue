<template>
    <div id="books">
        <button @click="showBooks()">Get Books</button>
        <h1 v-if="books"></h1>
        <ul>
            <li v-for="book in books"
                :key="book.id">
                <a href="#" @click="showBookDetails(book.id)">{{ book.title }}</a>
            </li>
        </ul>
    </div>
</template>

<script>
    import api from './backend-api';

    export default {
    name: 'BookList',
    data () {
        return {
            book: '',
            books: [],
            errors: ''
        }
    },
    methods: {
        showBooks () {
            api.getBooks()
                .then(response => {
                    this.books = response.data
                })
                .catch(e => {
                    this.errors = e
                })
        },
        showBookDetails (bookId) {
            api.getBook(bookId)
                .then(response => {
                    this.book = response.data
                })
                .catch(e => {
                    this.errors = e
                })
        }
    }
}
</script>

<style scoped>
    .error {
        color: red;
    }
</style>