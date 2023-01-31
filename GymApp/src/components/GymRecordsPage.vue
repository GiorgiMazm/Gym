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
              v-for="(day, index) in trainingDays"
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
          >
            <Field name="muscleGroup" v-slot="{ field, errors }">
              <v-text-field
                data-test="training-day-type"
                v-bind="field"
                class="w-25"
                name="trainingType"
                label="Training Type"
                v-model="trainingType"
                :error-messages="errors"
              ></v-text-field>
            </Field>

             <button class="d-block">Add exercise</button>

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
import AppHeader from "./AppHeader.vue";
import TrainingDay from "./TrainingDay.vue";
import { useGymStore } from "@/stores/GymStore";
import { mapState } from "pinia";
import { Field, Form } from "vee-validate";

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
      chosenFilter: false,
      trainingType: "",
      createNew: false,
      exerciseArray: [{ name: "Bench", sets: [{ weight: 10, repetition: 7 }] }],

      exerciseName: "",
      setWeight: 0,
      setRepetition: 0
    };
  },

  methods: {
    createNewToggle() {
      this.createNew = !this.createNew;
    },
    clearCreateInputs() {
      this.trainingType = "";
    },
  },

  computed: {
    ...mapState(useGymStore, ["trainingDays"]),
  },
};
</script>

<style></style>
