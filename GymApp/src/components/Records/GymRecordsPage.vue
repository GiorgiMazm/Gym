<template>
  <v-app>
    <v-card>
      <v-layout>
        <AppHeader>
          <template #nameOfPage>Records</template>
        </AppHeader>
        <v-main>
          <router-view />
          <div class="d-flex justify-space-around flex-wrap">
            <TrainingDay
              v-for="(day, index) in getAllTrainingDays"
              :key="index"
              :dayIndex="index"
            ></TrainingDay>
          </div>

          <button
            v-if="!createNew"
            @click="createNewToggle"
            data-test="create-button"
          >
            Create new training day
          </button>

          <Form
            name="newTrainingDayForm"
            data-test="new-training-day-form"
            v-if="createNew"
            class="ml-4 mt-4"
            @submit="
              addTrainingDay(this.exerciseArray, trainingType);
              clearCreateInputs();
              createNewToggle();
            "
          >
            <Field
              name="trainingDayType"
              :rules="rules.exist"
              v-model="trainingType"
              v-slot="{ field, errors }"
            >
              <v-text-field
                v-bind="field"
                data-test="training-day-type"
                class="w-25"
                name="trainingType"
                label="Training Type"
                :error-messages="errors"
              ></v-text-field>
            </Field>

            <div
              class="exercise-wrapper w-25 pt-4 pl-4 pr-4 mb-3"
              v-for="(exercise, index) in exerciseArray"
              v-bind:key="index"
            >
              <div>Exercise {{ index + 1 }}</div>
              <Field
                v-model="exercise.name"
                :name="`exercise-${index}`"
                :rules="rules.exist"
                v-slot="{ field, errors }"
              >
                <v-text-field
                  v-bind="field"
                  label="Exercise name"
                  :error-messages="errors"
                ></v-text-field>
              </Field>

              <div
                class="exercise-set-wrapper"
                v-for="(set, setIndex) in exerciseArray[index].sets"
                v-bind:key="setIndex"
              >
                <Field
                  :name="`exercise-${index}-set-${setIndex}-rep`"
                  v-slot="{ field, errors }"
                  v-model="set.repetition"
                  :rules="[rules.exist, rules.isValidNumber]"
                >
                  <v-text-field
                    v-bind="field"
                    label="Set repetition"
                    :error-messages="errors"
                  ></v-text-field>
                </Field>

                <Field
                  :name="`exercise-${index}-set-${setIndex}-weight`"
                  :rules="[rules.exist, rules.isValidNumber]"
                  v-slot="{ field, errors }"
                  v-model="set.weight"
                >
                  <v-text-field
                    v-bind="field"
                    label="Set weight"
                    :error-messages="errors"
                  ></v-text-field>
                </Field>

                <button @click.prevent="deleteExerciseSet(index, setIndex)">
                  Delete set
                </button>
              </div>
              <button class="mr-5" @click.prevent="addSet(index)">
                Add set
              </button>

              <button @click.prevent="deleteExercise(index)">
                Delete exercise
              </button>
            </div>

            <button class="d-block" @click.prevent="addExercise">
              Add exercise
            </button>

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
import AppHeader from "../AppHeader.vue";
import TrainingDay from "./TrainingDay.vue";
import { useGymStore } from "@/stores/GymStore";
import { mapActions, mapState } from "pinia";
import { Field, Form } from "vee-validate";
import rules from "@/Validation/rules";

export default {
  name: "GymRecords",
  components: {
    AppHeader,
    Form,
    Field,
    TrainingDay,
  },
  data() {
    return {
      trainingType: "",
      createNew: false,
      exerciseArray: [{ name: "", sets: [{ weight: 0, repetition: 0 }] }],
      rules: rules,
    };
  },

  methods: {
    ...mapActions(useGymStore, ["loadAllTrainingDays"]),
    ...mapActions(useGymStore, ["addTrainingDay"]),

    createNewToggle() {
      this.createNew = !this.createNew;
    },
    clearCreateInputs() {
      this.trainingType = "";
      this.exerciseArray = [{ name: "", sets: [{ weight: 0, repetition: 0 }] }];
    },
    addExercise() {
      this.exerciseArray.push({
        name: "",
        sets: [{ weight: 0, repetition: 0 }],
      });
    },

    addSet(index) {
      this.exerciseArray[index].sets.push({
        repetition: 0,
        weight: 0,
      });
    },

    deleteExercise(index) {
      this.exerciseArray.splice(index, 1);
    },
    deleteExerciseSet(index, setIndex) {
      this.exerciseArray[index].sets.splice(setIndex, 1);
    },
  },

  computed: {
    ...mapState(useGymStore, ["getAllTrainingDays"]),
  },
  created() {
    this.loadAllTrainingDays();
  },
};
</script>

<style>
.exercise-wrapper {
  border: 2px black solid;
}
</style>
