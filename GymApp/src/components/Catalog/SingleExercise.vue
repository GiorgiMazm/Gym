<template>
  <v-app>
    <v-card>
      <v-layout>
        <AppHeader> <template #nameOfPage>Single Exercise</template></AppHeader>
        <v-main>
          <div v-if="exercise" class="single-exercise">
            <v-card-title> Exercise: {{ exercise.name }} </v-card-title>
            <v-card-subtitle
              >This exercise is for
              {{ exercise.muscleGroup }} muscles.</v-card-subtitle
            >

            <v-card-subtitle
              >Difficulty: {{ exercise.difficulty }}. {{ getDifficultyHelp }}
            </v-card-subtitle>
            <v-card-text class="w-50">
              Description: {{ exercise.description }}
            </v-card-text>
            <v-card-actions>
              <router-link to="/catalog">
                <v-btn text color="deep-purple accent-4"> Back </v-btn>
              </router-link>
            </v-card-actions>
          </div>
          <div v-else>
            <v-card-title> Ooops... there is no such an exercise</v-card-title>

            <router-link to="/catalog">
              <v-btn class="mb-3 ml-3" text color="deep-purple accent-4">
                Back to the catalog
              </v-btn>
            </router-link>
          </div>
        </v-main>
      </v-layout>
    </v-card>
  </v-app>
</template>

<script>
import AppHeader from "@/components/AppHeader.vue";
import { mapActions } from "pinia";
import { useCatalogStore } from "@/stores/CatalogStore";

export default {
  name: "SingleExercise",
  components: {
    AppHeader,
  },

  data() {
    return {
      exercise: {},
    };
  },
  methods: {
    ...mapActions(useCatalogStore, ["getExerciseById"]),

    async loadExercise() {
      const response = await this.getExerciseById(this.$route.params.id);
      this.exercise = response.data;
    },
  },

  computed: {
    getDifficultyHelp() {
      if (this.exercise.difficulty === "Easy") {
        return "This is an exercise for beginner. You don't need any help from trainer. You can do it alone.";
      } else if (this.exercise.difficulty === "Medium") {
        return "This is an exercise for intermediate level. If you are a beginner, better to do it with trainer and carefully";
      }
      return (
        "This is an exercise for advanced level. It is hard to do it properly, so don't try to do it alone, otherwise you have " +
        "high risk to end up with an injury. Ask your trainer for help with it"
      );
    },
  },
  created() {
    this.loadExercise();
  },
};
</script>

<style scoped></style>
