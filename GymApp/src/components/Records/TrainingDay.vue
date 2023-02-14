<template>
  <v-card class="mt-4" width="400px">
    <v-card-item>
      <v-card-title class="text-h5"
        >Day {{ getCurrentDay.createdAt }}
      </v-card-title>

      <v-card-subtitle>
        <v-icon
          icon="mdi-dumbbell"
          size="18"
          color="error"
          class="mr-1 pb-1"
        ></v-icon>

        Trainings information
      </v-card-subtitle>
    </v-card-item>

    <v-card-text class="py-0">
      <v-row align="center" hide-gutters>
        <v-col class="text-h2">
          {{ getCurrentDay.type }}
        </v-col>
      </v-row>
      <v-list-item-subtitle class="mt-2">
        Exercise quantity: {{ getCurrentDay.exercises.length }}
      </v-list-item-subtitle>
    </v-card-text>

    <v-card-actions>
      <router-link :to="'record/' + getCurrentDay.id">
        <v-btn text color="deep-purple accent-4" class="d-block">
          Learn more
        </v-btn>
      </router-link>

      <v-btn @click="deleteTrainingDay(getCurrentDay.id)"> Delete Day</v-btn>

      <router-link :to="'record/edit/' + getCurrentDay.id">
        <v-btn text color="deep-purple accent-4" class="d-block"> Edit</v-btn>
      </router-link>
    </v-card-actions>
  </v-card>
</template>

<script>
import { useGymStore } from "@/stores/GymStore";
import { mapActions, mapState } from "pinia";

export default {
  name: "TrainingDay",
  props: {
    dayIndex: Number,
  },
  computed: {
    ...mapState(useGymStore, ["trainingDays"]),
    getCurrentDay() {
      return this.trainingDays[this.dayIndex];
    },
  },
  methods: {
    ...mapActions(useGymStore, ["deleteTrainingDay"]),
  },
};
</script>

<style scoped></style>
