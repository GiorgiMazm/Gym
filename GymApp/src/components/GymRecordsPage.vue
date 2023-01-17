<template>
  <v-app>
    <v-card>
      <v-layout>
        <AppHeader>
          <template #nameOfPage>Records</template>
        </AppHeader>
        <v-main>
          <router-view />
          <div class="d-flex justify-space-around flex-wrap">
            <TrainingDay
              v-for="(day, index) in trainingDays"
              :key="index"
              :dayIndex="index"
            ></TrainingDay>
          </div>
        </v-main>
      </v-layout>
    </v-card>
  </v-app>
</template>

<script>
import AppHeader from "./AppHeader.vue";
import TrainingDay from "./TrainingDay.vue";
import { useGymStore } from "@/stores/GymStore";
import { mapState } from "pinia";

export default {
  name: "GymRecords",
  components: {
    AppHeader,
    TrainingDay,
  },
  computed: {
    ...mapState(useGymStore, ["trainingDays"]),
  },
};
</script>

<style></style>
