import { defineStore } from "pinia";
import { useLocalStorage } from "@vueuse/core";
import axios from "axios";

export const useCatalogStore = defineStore("CatalogStore", {
  state: () => {
    return {
      exercises: [],
      filter: useLocalStorage("filter", "All"),
    };
  },
  actions: {
    async deleteExercise(id) {
      try {
        await axios.delete("http://localhost:8080/deleteExercise/" + id);
        await this.getAllExercises();
      } catch (error) {
        alert(error);
        console.log(error);
      }
    },

    editFilter(filter) {
      this.filter = filter;
    },

    async getAllExercises() {
      try {
        this.exercises = (
          await axios.get("http://localhost:8080/allExercises")
        ).data;
      } catch (error) {
        alert(error);
        console.log(error);
      }
    },
    async addExercise(muscleGroup, name, difficulty, description) {
      const exercise = {
        name: name,
        muscleGroup: muscleGroup,
        description: description,
        difficulty: difficulty,
      };
      try {
        await axios.post("http://localhost:8080/newExercise", exercise);
        await this.getAllExercises();
      } catch (error) {
        alert(error);
        console.log(error);
      }
    },

    async editExercise(muscleGroup, name, difficulty, description, id) {
      const exercise = {
        name: name,
        muscleGroup: muscleGroup,
        description: description,
        difficulty: difficulty,
      };
      try {
        await axios.put("http://localhost:8080/editExercise/" + id, exercise);
        await this.getAllExercises();
      } catch (error) {
        alert(error);
        console.log(error);
      }
    },
  },

  getters: {
    filteredExercises() {
      if (this.filter === "All") {
        return this.exercises;
      }
      return this.exercises.filter(
        (exercise) => exercise.difficulty === this.filter
      );
    },
  },
});
