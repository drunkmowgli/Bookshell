<template>
    <div>
        <div class="comments">
            <h1>Comments</h1>
            <ul>
                <li v-for="comment in comments"
                    :key="comment.id">
                    {{ comment.commentDescription }}
                </li>
            </ul>
        </div>
        <CommentAddForm :book-id="bookId"></CommentAddForm>
    </div>
</template>

<script>
    import api from './backend-api'
    import CommentAddForm from "./CommentAddForm";

    export default {
        name: "CommentList",
        components: {CommentAddForm},
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