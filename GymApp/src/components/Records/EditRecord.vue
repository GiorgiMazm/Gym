<template>
  <v-app>
    <v-card>
      <v-layout>
        <AppHeader>
          <template #nameOfPage>Edit Single Record</template>
        </AppHeader>
        <v-main>
          <Form
            v-if="record"
            class="single-record mt-5 ml-5"
            name="editExerciseForm"
            @submit="
              editTrainingDay(record);
              this.$router.back();
            "
          >
            <Field
              name="recordDate"
              v-model="record.trainingDate"
              :rules="rules.exist"
              v-slot="{ field, errors }"
            >
              <v-text-field
                v-bind="field"
                name="recordDate"
                v-model="record.trainingDate"
                label="Date"
                type="date"
                :error-messages="errors"
              ></v-text-field>
            </Field>

            <Field
              name="recordType"
              v-model="record.type"
              :rules="rules.exist"
              v-slot="{ field, errors }"
            >
              <v-text-field
                v-bind="field"
                name="recordType"
                v-model="record.type"
                label="Training type"
                :error-messages="errors"
              ></v-text-field>
            </Field>

            <ExercisesArray :exercise-array="record.exercises"></ExercisesArray>

            <button class="d-block" @click.prevent="addExercise">
              Add exercise
            </button>

            <v-card-actions>
              <v-btn text type="submit" color="deep-purple accent-4">
                Save</v-btn
              >
              <v-btn text color="deep-purple accent-4" @click="$router.back()">
                Back</v-btn
              >
            </v-card-actions>
          </Form>
          <div v-else>
            <v-card-title>
              Ooops... you can not edit this record... It does not even
              exist!</v-card-title
            >
            <router-link to="/records">
              <v-btn class="mb-3 ml-3" text color="deep-purple accent-4">
                Back to list of records
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
import { useGymStore } from "@/stores/GymStore";
import { Field, Form } from "vee-validate";
import rules from "@/Validation/rules";
import ExercisesArray from "@/components/Records/ExercisesArray.vue";

export default {
  name: "EditRecord",
  components: {
    AppHeader,
    Form,
    Field,
    ExercisesArray,
  },

  data() {
    return {
      record: {},
      rules: rules,
    };
  },

  methods: {
    ...mapActions(useGymStore, ["getTrainingDayById"]),
    ...mapActions(useGymStore, ["editTrainingDay"]),

    async loadTrainingDay() {
      const response = await this.getTrainingDayById(this.$route.params.id);
      this.record = response.data;
    },

    addExercise() {
      this.record.exercises.push({
        name: "",
        sets: [{ weight: "", repetition: "" }],
      });
    },
  },

  created() {
    this.loadTrainingDay();
  },
};
</script>

<style scoped></style>
