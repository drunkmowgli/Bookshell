<template>
  <v-container grid-list-md text-center>
    <v-layout align-center justify-end row fill-height>
      <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on }">
          <v-btn color="primary" dark v-on="on">Add Author</v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">Author information</span>
          </v-card-title>
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12>
                  <v-text-field
                    v-model="author"
                    label="Author's name*"
                    required
                    clearable
                  ></v-text-field>
                </v-flex>
              </v-layout>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog = false" v-on:click="clearAuthorName">Close</v-btn>
            <v-btn color="blue darken-1" text @click="dialog = false" v-on:click="submitAuthor">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout>
  </v-container>
</template>

<script>

    import api from './backend-api'

    export default {
        name: "PopupAddAuthorItem",
        data() {
            return {
                dialog: false,
                author: ''
            }
        },
        methods: {
            submitAuthor() {
                let author = this.author;
                api.addAuthor(author);
                this.clearAuthorName();
            },
            clearAuthorName() {
                this.author = ''
            }
        }
    }
</script>

<style scoped>

</style>
