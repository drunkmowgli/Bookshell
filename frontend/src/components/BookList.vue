<template>
    <div id="books">
        <h1 v-if="books"></h1>
        <ul>
            <li v-for="book in books"
                :key="book.id">
                <router-link :to="{ name: 'Book', params: {id: book.id} }">
                    <a href="#">{{ book.title }}</a>
                </router-link>
            </li>
        </ul>
    </div>
</template>

<script>
    import api from './backend-api';

    export default {
        name: 'BookList',
        data() {
            return {
                books: this.showBooks(),
                errors: ''
            }
        },
        methods: {
            showBooks() {
                return api.getBooks()
                    .then(response => {
                        this.books = response.data
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