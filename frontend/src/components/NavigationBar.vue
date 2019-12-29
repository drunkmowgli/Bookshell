<template>
  <div id="app">
    <v-navigation-drawer
      v-model="drawer"
      app
    >
      <v-list dense>
        <v-list-item router :to="{ name: 'Home' }">
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item router :to="{ name: 'BookList' }">
          <v-list-item-action>
            <v-icon>mdi-library</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Book list</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      app
      color="indigo"
      dark
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title class="headline">Library</v-toolbar-title>
      <v-layout align-start justify-end wrap>
        <v-flex xs6 md2 order-md3 order-sm1>
          <PopupAddBookItem></PopupAddBookItem>
        </v-flex>
        <v-flex xs6 md2 order-md2 order-sm1>
          <PopupAddAuthorItem></PopupAddAuthorItem>
        </v-flex>
      </v-layout>
    </v-app-bar>
  </div>
</template>

<script>
    import PopupAddBookItem from "./PopupAddBookItem";
    import PopupAddAuthorItem from "./PopupAddAuthorItem";

    export default {
        name: "AppHeader",
        components: {PopupAddAuthorItem, PopupAddBookItem},
        data() {
            return {
                drawer: true
            }
        }
    }
</script>

<style scoped>

</style>
