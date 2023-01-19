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
          <Form
            name="newExerciseForm"
            v-if="createNew"
            @submit="
              addExercise(muscleGroup, exercisesName, difficulty, description);
              createNewToggle();
              clearCreateInputs();
            "
          >
            <Field name="muscleGroup" :rules="exist" v-slot="{ field, errors }">
              <v-text-field
                data-test="muscle-group"
                v-bind="field"
                class="w-25"
                name="muscleGroup"
                label="Muscle Group"
                v-model="muscleGroup"
                :error-messages="errors"
              ></v-text-field>
            </Field>

            <Field
              name="exercisesName"
              :rules="exist"
              v-slot="{ field, errors }"
            >
              <v-text-field
                data-test="exercises-name"
                v-bind="field"
                class="w-25"
                name="exercisesName"
                label="Exercise's Name"
                v-model="exercisesName"
                :error-messages="errors"
              ></v-text-field>
            </Field>

            <Field name="difficulty" :rules="exist" v-slot="{ field, errors }">
              <v-select
                data-test="difficulty"
                v-bind="field"
                class="w-25"
                name="difficulty"
                label="Difficulty"
                v-model="difficulty"
                :error-messages="errors"
                :items="['Easy', 'Medium', 'Hard']"
              ></v-select>
            </Field>

            <Field name="username" :rules="exist" v-slot="{ field, errors }">
              <v-text-field
                v-bind="field"
                class="w-25"
                name="username"
                label="Description"
                v-model="description"
                :error-messages="errors"
              ></v-text-field>
            </Field>

            <button type="submit" data-test="save-button">Save</button>

            <button
              class="ma-2"
              @click="
                createNewToggle();
                clearCreateInputs();
              "
              data-test="cancel-button"
            >
              Cancel
            </button>
          </Form>
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
import { Field, Form } from "vee-validate";

export default {
  name: "ExercisesCatalogPage",
  components: {
    AppHeader,
    CatalogCard,
    Form,
    Field,
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
    ...mapActions(useCatalogStore, ["getAllExercises"]),
    ...mapActions(useCatalogStore, ["addExercise"]),
    ...mapActions(useCatalogStore, ["editFilter"]),
    createNewToggle() {
      this.createNew = !this.createNew;
    },

    exist(value) {
      if (value) {
        return true;
      }
      return "Field is required";
    },

    clearCreateInputs() {
      this.muscleGroup = "";
      this.exercisesName = "";
      this.difficulty = "";
      this.description = "";
    },
  },
  created() {
    this.getAllExercises();
  },
};
</script>

<style scoped></style>
