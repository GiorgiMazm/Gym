<template>
  <v-app>
    <v-card>
      <v-layout>
        <AppHeader> <template #nameOfPage>Home</template></AppHeader>
        <v-main> This is one exercise page! {{ exercise }} </v-main>
      </v-layout>
    </v-card>
  </v-app>
</template>

<script>
import AppHeader from "@/components/AppHeader.vue";
import { mapActions } from "pinia";
import { useCatalogStore } from "@/stores/CatalogStore";

export default {
  name: "SingleExercise",
  components: {
    AppHeader,
  },

  data() {
    return {
      exercise: {},
    };
  },
  methods: {
    ...mapActions(useCatalogStore, ["getExerciseById"]),

    async loadExercise() {
      const response = await this.getExerciseById(this.$route.params.id);
      this.exercise = response.data;
    },
  },

  created() {
    this.loadExercise();
  },
};
</script>

<style scoped></style>
