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

            <div
              class="exercise-wrapper w-25 pt-4 pl-4 pr-4 mb-3"
              v-for="(exercise, index) in record.exercises"
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
                  v-model="exercise.name"
                  v-bind="field"
                  label="Exercise name"
                  :error-messages="errors"
                ></v-text-field>
              </Field>

              <div
                class="exercise-set-wrapper"
                v-for="(set, setIndex) in record.exercises[index].sets"
                v-bind:key="setIndex"
              >
                <Field
                  :name="`exercise-${index}-set-${setIndex}-rep`"
                  v-slot="{ field, errors }"
                  v-model="set.repetition"
                  :rules="[rules.exist, rules.isValidNumber]"
                >
                  <v-text-field
                    v-model="set.repetition"
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
                    v-model="set.weight"
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

export default {
  name: "EditRecord",
  components: {
    AppHeader,
    Form,
    Field,
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

    addSet(index) {
      this.record.exercises[index].sets.push({
        repetition: "",
        weight: "",
      });
    },

    deleteExercise(index) {
      this.record.exercises.splice(index, 1);
    },
    deleteExerciseSet(index, setIndex) {
      this.record.exercises[index].sets.splice(setIndex, 1);
    },
  },

  created() {
    this.loadTrainingDay();
  },
};
</script>

<style scoped></style>
