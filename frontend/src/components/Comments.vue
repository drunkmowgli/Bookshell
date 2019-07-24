<template>
    <div class="Comments">
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
        name: "Comments",
        props: {
            bookId: Number
        },
        data() {
            return {
                comments: '',
                errors: ''
            }
        },
        methods: {
            showComments() {
                return api.getComments(this.bookId)
                    .then(response => {
                        this.comments = response.data
                    })
                    .catch(e => {
                        this.errors = e
                    })
            }
        },
        watch: {
            'bookId': function () {
                this.showComments();
            }
        }
    }
</script>

<style scoped>

</style>