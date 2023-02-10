<template>
  <v-app>
    <v-card>
      <v-layout>
        <AppHeader> <template #nameOfPage>Edit Exercise</template></AppHeader>
        <v-main>
          <Form class="mt-4" name="editExerciseForm" @submit="exerciseEdit">
            <Field
              name="exerciseName"
              v-model="exercise.name"
              :rules="exist"
              v-slot="{ field, errors }"
            >
              <v-text-field
                v-bind="field"
                name="exerciseName"
                v-model="exercise.name"
                label="Exercise's name"
                :error-messages="errors"
              ></v-text-field>
            </Field>

            <Field
              name="difficulty"
              v-model="exercise.difficulty"
              :rules="exist"
              v-slot="{ field, errors }"
            >
              <v-select
                v-bind="field"
                name="difficulty"
                v-model="exercise.difficulty"
                label="Difficulty"
                :error-messages="errors"
                :items="['Easy', 'Medium', 'Hard']"
              ></v-select>
            </Field>

            <Field
              name="muscleGroup"
              v-model="exercise.muscleGroup"
              :rules="exist"
              v-slot="{ field, errors }"
            >
              <v-text-field
                v-bind="field"
                name="muscleGroup"
                v-model="exercise.muscleGroup"
                label="Muscle Group"
                :error-messages="errors"
              ></v-text-field>
            </Field>

            <Field
              name="description"
              v-model="exercise.description"
              :rules="exist"
              v-slot="{ field, errors }"
            >
              <v-textarea
                v-bind="field"
                name="description"
                v-model="exercise.description"
                label="Description"
                :error-messages="errors"
              ></v-textarea>
            </Field>

            <v-card-actions>
              <v-btn color="deep-purple accent-4" type="submit"> Save </v-btn>
              <v-btn color="deep-purple accent-4" @click="$router.back()">
                Back
              </v-btn>
            </v-card-actions>
          </Form>
        </v-main>
      </v-layout>
    </v-card>
  </v-app>
</template>

<script>
import { Field, Form } from "vee-validate";
import { mapActions } from "pinia";
import { useCatalogStore } from "@/stores/CatalogStore";
import AppHeader from "@/components/AppHeader.vue";

export default {
  name: "EditExercise",
  components: {
    Form,
    Field,
    AppHeader,
  },
  data() {
    return {
      exercise: {},
    };
  },
  methods: {
    ...mapActions(useCatalogStore, ["getExerciseById"]),
    ...mapActions(useCatalogStore, ["editExercise"]),

    async loadExercise() {
      const response = await this.getExerciseById(this.$route.params.id);
      this.exercise = response.data;
    },
    exist(value) {
      if (value) {
        return true;
      }
      return "Field is required";
    },

    exerciseEdit() {
      this.editExercise(
        this.exercise.muscleGroup,
        this.exercise.name,
        this.exercise.difficulty,
        this.exercise.description,
        this.exercise.id
      );
      this.$router.back();
    },
  },

  created() {
    this.loadExercise();
  },
};
</script>

<style scoped></style>
