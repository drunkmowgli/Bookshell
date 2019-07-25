<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title class="headline text-uppercase">
                <span>Library</span>
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn router :to="{ name: 'BookList' }" class="mr-2">List Book</v-btn>
        </v-app-bar>
        <router-view></router-view>
    </v-app>
</template>

<script>
    export default {
        name: 'App'
    }
</script>
