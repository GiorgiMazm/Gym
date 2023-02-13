<template>
  <v-list-item-subtitle
    >Set {{ setIndex + 1 }} <span></span>, repetition:
    <span> {{ getCurrentSet.repetition }}</span
    >, weight:
    <span> {{ getCurrentSet.weight }}</span>
    kg
  </v-list-item-subtitle>
</template>

<script>
import { mapState } from "pinia";
import { useGymStore } from "@/stores/GymStore";

export default {
  name: "ExerciseSet",
  props: {
    exerciseIndex: Number,
    dayIndex: Number,
    setIndex: Number,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(useGymStore, ["trainingDays"]),
    getCurrentSet() {
      return this.trainingDays[this.dayIndex].exercises[this.exerciseIndex]
        .sets[this.setIndex];
    },
  },
};
</script>

<style scoped></style>
