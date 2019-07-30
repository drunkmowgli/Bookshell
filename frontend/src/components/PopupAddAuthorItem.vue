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
                  <v-text-field label="Author's name*" required></v-text-field>
                </v-flex>
              </v-layout>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout>
  </v-container>
</template>

<script>

    export default {
        name: "PopupAddAuthorItem",
        data() {
            return {
                dialog: false
            }
        },
    }
</script>

<style scoped>

</style>
