<template>
  <v-app>
    <v-card>
      <v-layout>
        <AppHeader> <template #nameOfPage>Home</template></AppHeader>
        <v-main> This is home page! </v-main>
      </v-layout>
    </v-card>
  </v-app>
</template>

<script>
import AppHeader from "@/components/AppHeader.vue";

export default {
  name: "HomePage",
  components: {
    AppHeader,
  },
};
</script>

<style scoped></style>
