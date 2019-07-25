<template>
    <div class="BookItem">
        <h1 class="subheading grey--text">Book Card</h1>
        <v-container class="my-5">
            <v-layout row wrap :class="`pa-3 book ${book.title}`">
                <v-flex xs12 md6>
                    <div class="caption grey--text">Title</div>
                    <div>{{ book.title }}</div>
                </v-flex>
                <v-flex xs6 sm4 md2>
                    <div class="caption grey--text">Author(s)</div>
                    <v-list class="caption lighten-5 grey">
                        <v-list-item v-for="author in book.authors"
                                     :key="author.id">

                            <div>{{ author.name }}</div>

                        </v-list-item>
                    </v-list>
                </v-flex>
                <v-flex xs6 sm4 md2>
                    <div class="caption grey--text">Genre</div>
                    <div>{{ book.genre }}</div>
                </v-flex>
            </v-layout>
            <v-divider></v-divider>
            <div class="delete-book">
                <v-btn @click="deleteCurrentBook()" class="red mx0 mt-3">Delete
                </v-btn>
            </div>
        </v-container>

        <router-view></router-view>

        <CommentList :book-id="book.id"></CommentList>
    </div>
</template>

<script>
    import api from './backend-api'
    import CommentList from '@/components/CommentList'


    export default {
        name: 'BookItem',
        props: ['id'],
        components: {
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
                        this.book = response.data;
                    })
                    .catch(e => {
                        this.errors = e
                    })
            },
            deleteCurrentBook() {
                api.deleteBook(this.id);
                this.$router.push('/books')
            }
        }
    }
</script>

<style>
    .BookItem {
        margin: 100px;
    }

    .delete-book {
        float: right;
    }
</style>