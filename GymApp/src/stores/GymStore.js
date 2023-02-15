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

    async getTrainingDayById(id) {
      try {
        return await axios.get("http://localhost:8080/trainingDay/" + id);
      } catch (error) {
        alert(error);
        console.log(error);
      }
    },

    async deleteTrainingDay(id) {
      try {
        await axios.delete("http://localhost:8080/deleteDay/" + id);
        await this.loadAllTrainingDays();
      } catch (error) {
        alert(error);
        console.log(error);
      }
    },

    async addTrainingDay(exerciseArray, trainingType, trainingDate) {
      const trainingDay = {
        type: trainingType,
        exercises: exerciseArray,
        trainingDate: trainingDate,
      };
      try {
        await axios.post("http://localhost:8080/newTrainingDay", trainingDay);
        await this.loadAllTrainingDays();
      } catch (error) {
        alert(error);
        console.log(error);
      }
    },

    async editTrainingDay(trainingDay) {
      try {
        await axios.put(
          "http://localhost:8080/editTrainingDay/" + trainingDay.id,
          trainingDay
        );
        await this.loadAllTrainingDays();
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
