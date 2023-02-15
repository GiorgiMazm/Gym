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
              addTrainingDay(this.exerciseArray, trainingType, trainingDate);
              clearCreateInputs();
              createNewToggle();
            "
          >
            <Field
              name="recordDate"
              v-model="trainingDate"
              :rules="rules.exist"
              v-slot="{ field, errors }"
            >
              <v-text-field
                v-bind="field"
                name="recordDate"
                v-model="trainingDate"
                label="Date"
                type="date"
                :error-messages="errors"
                class="w-25"
              ></v-text-field>
            </Field>

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

            <ExercisesArray :exercise-array="exerciseArray"></ExercisesArray>

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
import ExercisesArray from "@/components/Records/ExercisesArray.vue";

export default {
  name: "GymRecords",
  components: {
    AppHeader,
    Form,
    Field,
    TrainingDay,
    ExercisesArray,
  },
  data() {
    return {
      trainingType: "",
      trainingDate: new Date().toJSON().slice(0, 10),
      createNew: false,
      exerciseArray: [{ name: "", sets: [{ weight: "", repetition: "" }] }],
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
      this.exerciseArray = [
        { name: "", sets: [{ weight: "", repetition: "" }] },
      ];
    },
    addExercise() {
      this.exerciseArray.push({
        name: "",
        sets: [{ weight: "", repetition: "" }],
      });
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
