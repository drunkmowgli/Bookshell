<template>
  <v-container grid-list-md text-center>
    <v-layout align-center justify-end row fill-height>
      <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on }">
          <v-btn color="primary" dark v-on="on" v-on:click="onBookAddBtnClick">Add book</v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">Book information</span>
          </v-card-title>
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12>
                  <v-text-field label="Book title*" required v-model="title"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6>
                  <v-autocomplete
                    :items="authors"
                    item-value="id"
                    item-text="name"
                    label="Authors"
                    multiple
                    v-model="selectedAuthors"
                  ></v-autocomplete>
                </v-flex>
                <v-flex xs12 sm6>
                  <v-autocomplete
                    :items="genres"
                    item-value="id"
                    item-text="name"
                    label="Genre"
                    v-model="selectedGenre"
                  ></v-autocomplete>
                </v-flex>
              </v-layout>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog = false" v-on:click="clearBookInfo">Close</v-btn>
            <v-btn color="blue darken-1" text @click="dialog = false" v-on:click="submitBook">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout>
  </v-container>
</template>

<script>
  import api from './backend-api'

  export default {
        name: "PopupAddBookItem",
        data() {
            return {
                dialog: false,
                title: '',
                authors: [],
                selectedAuthors: [],
                genres: [],
                selectedGenre: ''
            }
        },
        methods: {
            fillGenresList() {
                return api.getGenres()
                    .then(response => {
                        this.genres = response.data;
                    })
            },
            fillAuthorsList() {
                return api.getAuthors()
                    .then(response => {
                        this.authors = response.data;
                    })
            },
            onBookAddBtnClick() {
                this.fillAuthorsList();
                this.fillGenresList();
            },
            submitBook() {
                let title = this.title;
                let selectedAuthors = this.selectedAuthors;
                let selectedGenre = this.selectedGenre;
                return api.addBook(title, selectedAuthors, selectedGenre)
                    .then( () => {
                      this.$root.$emit('bookSubmittedEvent');
                      this.clearBookInfo();
                    })
            },
          clearBookInfo() {
            this.selectedAuthors = [];
            this.selectedGenre = '';
            this.title = ''
          }
        }
    }
</script>

<style scoped>

</style>
