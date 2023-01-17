import { defineStore } from "pinia";
import trainingDay from "../data/TrainingDays.json";
/*
import { useLocalStorage } from "@vueuse/core";
*/

export const useGymStore = defineStore("GymStore", {
  state: () => {
    return trainingDay;
  },
});
