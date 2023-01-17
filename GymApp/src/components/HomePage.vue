<template>
  <v-app>
    <v-card>
      <v-layout>
        <AppHeader> <template #nameOfPage>Home</template></AppHeader>
        <v-main> <teleport to="body">This is home page!</teleport> </v-main>
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
