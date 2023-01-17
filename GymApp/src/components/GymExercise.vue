<template>
  <v-list-item density="compact">
    <v-list-item-title left v-if="!editMode">
      {{ getCurrentExercise.name }}
    </v-list-item-title>
    <v-text-field
      v-if="editMode"
      v-model="getCurrentExercise.name"
      label="Exercise"
    ></v-text-field>

    <ExerciseSet
      v-for="(set, index) in getCurrentExercise.sets"
      :key="index"
      :exerciseIndex="exerciseIndex"
      :dayIndex="dayIndex"
      :setIndex="index"
      :editMode="editMode"
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
    editMode: Boolean,
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
