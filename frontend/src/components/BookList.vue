<template>
    <v-app class="grey lighten-5">
        <v-content class="caption grey--text">
            <v-list class="caption lighten-5 grey">
                <v-list-item v-for="book in books"
                             :key="book.id" class="item">
                    <v-btn router :to="{ name: 'BookItem', params: { id: book.id }}" class="mr-2">
                        {{ book.title }}
                    </v-btn>
                </v-list-item>
            </v-list>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import api from './backend-api';

    export default {
        name: 'BookList',
        data() {
            return {
                books: this.showBooks(),
                errors: ''
            }
        },
        methods: {
            showBooks() {
                return api.getBooks()
                    .then(response => {
                        this.books = response.data
                    })
                    .catch(e => {
                        this.errors = e
                    })
            }
        }
    }
</script>

<style scoped>
    .item {
        margin: 5px;
        border-radius: 4px;
    }

    .item:hover {
        background: dodgerblue;
    }

    .book-item-list {
        background: grey;
    }
</style>