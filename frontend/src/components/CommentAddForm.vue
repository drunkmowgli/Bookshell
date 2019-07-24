<template>
    <div>
        <div class="form-group">
            <!--            <label for="commentDescription">CommentDescription</label>-->
            <input type="text" name="CommentDescription" id="Comment" v-model="comment" class="form-control">
        </div>
        <div class="form-group">
            <input type="submit" @click="submitComment()" name="submit" value="Submit" class="btn btn-default"/>
        </div>
    </div>
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
                console.log(comment);
                console.log(bookId);
                return api.addComment(comment, bookId)
            }
        }
    }
</script>

<style scoped>

</style>