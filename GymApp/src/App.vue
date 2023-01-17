<template>
  <router-view />
</template>

<script>
import { mapActions } from "pinia";
import { useCatalogStore } from "@/stores/CatalogStore";

export default {
  name: "App",
  methods: {
    ...mapActions(useCatalogStore, ["getAllExercises"]),
  },
  created() {
    this.getAllExercises();
  },
};
</script>

<style></style>
