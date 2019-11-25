<template>
    <v-container grid-list-md text-center>
        <v-layout align-center justify-end row fill-height>
            <v-dialog v-model="dialog" persistent max-width="600px">
                <template v-slot:activator="{ on }">
                    <v-btn color="primary" dark v-on="on">Add Genre</v-btn>
                </template>
                <v-card>
                    <v-card-title>
                        <span class="headline">Genre information</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container grid-list-md>
                            <v-layout wrap>
                                <v-flex xs12>
                                    <v-text-field
                                            v-model="genre"
                                            label="Genre's name*"
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
                        <v-btn color="blue darken-1" text @click="dialog = false" v-on:click="clearGenreName">Close</v-btn>
                        <v-btn color="blue darken-1" text @click="dialog = false" v-on:click="submitGenre">Save</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-layout>
    </v-container>
</template>

<script>

    import api from './backend-api'

    export default {
        name: "PopupAddGenreItem",
        data() {
            return {
                dialog: false,
                genre: ''
            }
        },
        methods: {
            submitGenre() {
                let genre = this.genre;
                api.addGenre(genre);
                this.clearGenreName();
            },
            clearGenreName() {
                this.genre = ''
            }
        }
    }
</script>

<style scoped>

</style>
