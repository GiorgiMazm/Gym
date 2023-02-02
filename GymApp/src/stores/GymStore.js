import { defineStore } from "pinia";
import axios from "axios";

export const useGymStore = defineStore("GymStore", {
  state: () => {
    return { trainingDays: [] };
  },

  actions: {
    async loadAllTrainingDays() {
      try {
        this.trainingDays = (
          await axios.get("http://localhost:8080/allTrainingDays")
        ).data;
      } catch (error) {
        alert(error);
        console.log(error);
      }
    },
  },

  getters: {
    getAllTrainingDays() {
      return this.trainingDays;
    },
  },
});
