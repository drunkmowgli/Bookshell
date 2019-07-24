<template>
    <div>
        <div class="book">
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
        </div>
        <Comments :bookId="book.id"></Comments>
    </div>

</template>

<script>
    import api from './backend-api'
    import Comments from '@/components/Comments'


    export default {
        name: 'Book',
        props: ['id'],
        components: {
            Comments
        },
        data() {
            return {
                book: this.showBookDetails(),
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
            }
        }
    }
</script>