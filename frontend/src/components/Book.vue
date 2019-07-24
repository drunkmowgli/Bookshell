<template>
    <div id="book">
        <h1>Book Page</h1>
        <table>
            <tr>
                <th>{{ book.id }}</th>
                <th> {{ book.title }}</th>
                <th v-for="author in book.authors"
                    :key="author.id">
                    {{ author.name }}
                </th>
                <th>
                    {{ book.genre }}
                </th>
                <th>
                    <router-link :to="{ name: 'BookList' }">
                        <button v-on:click="deleteCurrentBook()">Delete</button>
                    </router-link>
                </th>
            </tr>
        </table>

        <h1>Comments</h1>
        <ul>
            <li v-for="comment in comments"
                :key="comment.id">
                {{ comment.commentDescription }}
            </li>
        </ul>
    </div>
</template>

<script>
    import api from './backend-api'

    export default {
        name: 'Book',
        props: ['id'],
        data() {
            return {
                book: this.showBookDetails(),
                comments: this.showComments(),
                errors: ''
            }
        },
        methods: {
            showBookDetails() {
                return api.getBook(this.id)
                    .then(response => {
                        this.book = response.data
                    })
                    .catch(e => {
                        this.errors = e
                    })
            },
            deleteCurrentBook() {
                api.deleteBook(this.id)
            },
            showComments() {
                return api.getComments(this.id)
                    .then(response => {
                        this.comments = response.data
                    })
                    .catch(e => {
                        this.errors = e
                    })
            }
        }
    }
</script>