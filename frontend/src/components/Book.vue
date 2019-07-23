<template>
    <div id="book">
        <h1>Book Page</h1>
        {{ book.id }}
        {{ book.title }}
        {{ book.authors }}
        {{ book.genre }}
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