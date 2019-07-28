<template>
    <v-container>
        <v-layout>
            <v-list class="caption lighten-5 grey">
                <v-list-item v-for="comment in comments"
                             :key="comment.id"
                             class="item"
                >
                    <v-flex xs12
                            md6
                            offset-sm-1
                            offset-md-2
                    >
                        <div class="headline">{{ comment.commentDescription }}</div>
                    </v-flex>
                </v-list-item>
            </v-list>
            <v-spacer></v-spacer>
        </v-layout>
        <CommentAddForm :book-id="bookId" v-on:commentSubmitted="commentSubmittedResponse()"></CommentAddForm>
    </v-container>
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