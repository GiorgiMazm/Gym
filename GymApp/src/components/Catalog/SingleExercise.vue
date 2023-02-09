<template>
  <v-app>
    <v-card>
      <v-layout>
        <AppHeader> <template #nameOfPage>Home</template></AppHeader>
        <v-main> This is one exercise page! </v-main>
      </v-layout>
    </v-card>
  </v-app>
</template>

<script>
import AppHeader from "@/components/AppHeader.vue";

export default {
  name: "SingleExercise",
  components: {
    AppHeader,
  },

  mounted() {
    console.log(this.$route.params.id);
  },
};
</script>

<style scoped></style>
