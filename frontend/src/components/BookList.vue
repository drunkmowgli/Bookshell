<template>
    <v-container grid-list-md>
        <v-layout row wrap>
            <v-flex v-for="book in books"
                    :key="book.id"
                    xs12
                    sm10
                    md8
                    offset-sm1
                    offset-md-2
            >
                <v-card color="indigo" class="white--text">
                    <v-container fluid>
                        <v-layout row>
                            <v-flex
                                    xs8
                                    md9
                            >
                                <v-card-title>
                                    <div>
                                        <div class="headline">{{ book.title }}</div>
                                    </div>
                                </v-card-title>
                                <v-card-actions>
                                    <v-btn router :to="{ name: 'BookItem', params: { id: book.id }}" class="primary">
                                        Open
                                    </v-btn>
                                </v-card-actions>
                            </v-flex>
                        </v-layout>
                    </v-container>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
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