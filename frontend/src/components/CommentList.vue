<template>
    <v-app class="comment-list">
        <v-content class="caption grey--text">
            <v-list class="caption lighten-5 grey">
                <v-list-item v-for="comment in comments"
                             :key="comment.id" class="item">
                    <div>
                        {{ comment.commentDescription }}
                    </div>
                </v-list-item>
            </v-list>
            <CommentAddForm :book-id="bookId" @commentSubmitted="commentSubmittedResponse"></CommentAddForm>
        </v-content>
    </v-app>
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
            },
            commentSubmittedResponse() {
                this.showComments();
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