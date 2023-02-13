<template>
  <v-app>
    <v-card>
      <v-layout>
        <AppHeader> <template #nameOfPage>Single Record</template></AppHeader>
        <v-main>
          <div class="single-record">this is a single record</div>
        </v-main>
      </v-layout>
    </v-card>
  </v-app>
</template>

<script>
import AppHeader from "@/components/AppHeader.vue";
export default {
  name: "SingleRecord",
  components: { AppHeader },
};
</script>

<style scoped></style>
