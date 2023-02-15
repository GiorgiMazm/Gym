<template>
  <v-app-bar color="info">
    <v-app-bar-title
      >Gym! <v-icon icon="mdi-weight-lifter"></v-icon>
      <slot name="nameOfPage"> </slot> page!
    </v-app-bar-title>
    <div class="links">
      <router-link class="ma-2" to="/">home</router-link>
      <router-link to="/records">records</router-link>
      <router-link class="ma-2" to="/catalog">catalog</router-link>
    </div>
  </v-app-bar>
</template>

<script>
export default {
  name: "AppHeader",
};
</script>

<style scoped></style>
