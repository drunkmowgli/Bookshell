<template>
    <v-container grid-list-md>
        <v-layout align-center justify-space-around row fill-height>
            <v-card>
                <v-card-title>
                    <h2>Add New Comment</h2>
                </v-card-title>
                <v-card-text>
                    <v-form class="px-3">
                        <v-textarea v-model="comment" label="Enter your comment" maxlength="140">
                        </v-textarea>
                        <v-spacer></v-spacer>
                        <v-btn v-on:click="submitComment()" class="success mx0 mt-3">Submit comment</v-btn>
                    </v-form>
                </v-card-text>
            </v-card>
        </v-layout>
    </v-container>
</template>

<script>
    import api from './backend-api'

    export default {
        name: "CommentAddForm",
        props: {
            bookId: Number
        },
        data() {
            return {
                comment: ''
            }
        },
        methods: {
            submitComment() {
                let comment = this.comment;
                let bookId = this.bookId;
                return api.addComment(comment, bookId)
                    .then( () => {
                        this.$emit('commentSubmitted');
                    })
            }
        }
    }
</script>

<style scoped>

</style>
