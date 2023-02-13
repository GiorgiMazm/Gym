<template>
  <v-list-item density="compact">
    <v-list-item-title left>
      {{ getCurrentExercise.name }}
    </v-list-item-title>

    <ExerciseSet
      v-for="(set, index) in getCurrentExercise.sets"
      :key="index"
      :exerciseIndex="exerciseIndex"
      :dayIndex="dayIndex"
      :setIndex="index"
    >
    </ExerciseSet>
  </v-list-item>
</template>

<script>
import ExerciseSet from "./ExerciseSet.vue";
import { mapState } from "pinia";
import { useGymStore } from "@/stores/GymStore";
export default {
  name: "GymExercise",
  props: {
    exerciseIndex: Number,
    dayIndex: Number,
  },
  data() {
    return {
      quantity: 5,
    };
  },
  components: {
    ExerciseSet,
  },

  computed: {
    ...mapState(useGymStore, ["trainingDays"]),
    getCurrentExercise() {
      return this.trainingDays[this.dayIndex].exercises[this.exerciseIndex];
    },
  },
};
</script>

<style scoped></style>
