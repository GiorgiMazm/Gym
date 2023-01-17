<template>
  <v-app>
    <v-card>
      <v-layout>
        <AppHeader>
          <template #nameOfPage>Exercises Catalog</template>
        </AppHeader>
        <v-main class="ma-4">
          <button class="ma-2" @click="editFilter('Easy')">Easy</button>
          <button class="ma-2" @click="editFilter('Medium')">Meduim</button>
          <button class="ma-2" @click="editFilter('Hard')">Hard</button>
          <button class="ma-2" @click="editFilter('All')">All</button>

          <div class="d-flex justify-space-around flex-wrap">
            <CatalogCard
              :exerciseIndex="index"
              v-for="(exercise, index) in filteredExercises"
              :key="index"
            >
            </CatalogCard>
          </div>

          <button
            v-if="!createNew"
            @click="createNewToggle"
            data-test="create-button"
          >
            New exercise
          </button>
          <v-form
            @submit.prevent="
              addExercise(muscleGroup, exercisesName, difficulty, description);
              createNewToggle();
              clearCreateInputs();
            "
          >
            <v-text-field
              data-test="muscle-group"
              class="w-25"
              v-if="createNew"
              label="Muscle Group"
              v-model="muscleGroup"
            ></v-text-field>

            <v-text-field
              class="w-25"
              v-if="createNew"
              label="Exercise's Name"
              v-model="exercisesName"
              data-test="exercise-name"
            ></v-text-field>

            <v-select
              class="w-25"
              v-if="createNew"
              v-model="difficulty"
              label="Difficulty"
              data-test="difficulty"
              :items="['Easy', 'Medium', 'Hard']"
            ></v-select>

            <v-text-field
              class="w-25"
              v-if="createNew"
              label="Description"
              v-model="description"
              required
            ></v-text-field>

            <button type="submit" v-if="createNew" data-test="save-button">
              Save
            </button>

            <button
              class="ma-2"
              v-if="createNew"
              @click="
                createNewToggle();
                clearCreateInputs();
              "
              data-test="cancel-button"
            >
              Cancel
            </button>
          </v-form>
        </v-main>
      </v-layout>
    </v-card>
  </v-app>
</template>

<script>
import AppHeader from "@/components/AppHeader.vue";
import { useCatalogStore } from "@/stores/CatalogStore";
import { mapActions, mapState } from "pinia";
import CatalogCard from "@/components/CatalogCard.vue";

export default {
  name: "ExercisesCatalogPage",
  components: {
    AppHeader,
    CatalogCard,
  },

  data() {
    return {
      chosenFilter: false,
      muscleGroup: "",
      exercisesName: "",
      description: "",
      difficulty: "",
      createNew: false,
    };
  },

  computed: {
    ...mapState(useCatalogStore, ["filteredExercises"]),
  },

  methods: {
    ...mapActions(useCatalogStore, ["addExercise"]),
    ...mapActions(useCatalogStore, ["editFilter"]),
    createNewToggle() {
      this.createNew = !this.createNew;
    },

    lol(event) {
      console.log(event);
    },

    clearCreateInputs() {
      this.muscleGroup = "";
      this.exercisesName = "";
      this.difficulty = "";
      this.description = "";
    },
  },
};
</script>

<style scoped></style>
