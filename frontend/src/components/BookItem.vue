<template>
    <v-container grid-list-md>
        <h1 class="subheading grey--text">Book Card</h1>
        <v-layout row wrap>
            <v-flex
                    xs12
                    md6

                    offset-sm-1
                    offset-md-2
            >
                <div class="caption grey--text">Title</div>
                <div>{{ book.title }}</div>
            </v-flex>
            <v-flex xs12
                    md6
                    offset-sm-1
                    offset-md-2
            >
                <div class="caption grey--text">Author(s)</div>
                <v-list class="caption lighten-5 grey">
                    <v-list-item v-for="author in book.authors"
                                 :key="author.id">

                        <div class="headline">{{ author.name }}</div>

                    </v-list-item>
                </v-list>
            </v-flex>
            <v-flex
                    xs12
                    md6
                    offset-sm-1
                    offset-md-2
            >
                <div class="caption grey--text">Genre</div>
                <div>
                    <span v-if="book.genre">
                        {{ book.genre.genreName }}
                    </span>
                </div>
            </v-flex>
            <v-flex>
                <PopupEditBookItem :book-id="book.id" v-on:bookUpdatedEvent="bookUpdatedResponse"></PopupEditBookItem>
            </v-flex>
            <v-flex>
                <v-btn @click="deleteCurrentBook()" class="red mx0 mt-3">Delete</v-btn>
            </v-flex>
        </v-layout>
        <v-spacer></v-spacer>
        <CommentList :book-id="book.id"></CommentList>
    </v-container>
</template>

<script>
    import api from './backend-api'
    import CommentList from './CommentList'
    import PopupEditBookItem from "./PopupEditBookItem";


    export default {
        name: 'BookItem',
        props: ['id'],
        components: {
            PopupEditBookItem,
            CommentList
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
                api.deleteBook(this.id);
                this.$router.push('/books')
            },
            bookUpdatedResponse() {
                this.showBookDetails();
            }
        }
    }
</script>

<style scoped>

</style>
