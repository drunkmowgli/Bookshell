<template>
    <v-app>
        <NavigationBar></NavigationBar>

        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>

    import NavigationBar from "./components/NavigationBar";

    export default {
        components: {
            NavigationBar
        },
        name: 'App'
    }
</script>
