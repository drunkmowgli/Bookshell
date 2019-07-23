<template>
    <div id="book">
        <h1>Book Page</h1>
        <!--        {{ book.id }}-->
        <!--        {{ book.title }}-->
        <!--        {{ book.authors }}-->
        <!--        {{ book.genre }}-->
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
            </tr>
        </table>
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
            }
        }
    }
</script>