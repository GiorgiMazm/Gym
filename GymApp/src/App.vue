<template>
  <router-view />
</template>

<script>
import { mapActions } from "pinia";
import { useCatalogStore } from "@/stores/CatalogStore";

export default {
  name: "App",
};
</script>

<style></style>
