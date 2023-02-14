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
            class="single-record mt-5"
            name="editExerciseForm"
            @submit="
              editTrainingDay(record);
              this.$router.back();
            "
          >
            <Field
              name="recordDate"
              v-model="record.createdAt"
              :rules="rules.exist"
              v-slot="{ field, errors }"
            >
              <v-text-field
                v-bind="field"
                name="recordDate"
                v-model="record.createdAt"
                label="Date of record"
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

            <v-list-item
              v-for="(exercise, index) in record.exercises"
              density="compact"
              :key="index"
            >
              <Field
                :name="`exercise-${index}`"
                v-model="exercise.name"
                :rules="rules.exist"
                v-slot="{ field, errors }"
              >
                <v-text-field
                  v-bind="field"
                  name="recordExercise"
                  v-model="exercise.name"
                  label="Exercise name"
                  :error-messages="errors"
                ></v-text-field>
              </Field>

              <v-list-item
                v-for="(set, setIndex) in exercise.sets"
                :key="setIndex"
              >
                <Field
                  :name="`exercise-${index}-set-${setIndex}-rep`"
                  v-model="set.repetition"
                  :rules="rules.exist"
                  v-slot="{ field, errors }"
                >
                  <v-text-field
                    v-bind="field"
                    name="recordSetRep"
                    v-model="set.repetition"
                    label="Set repetition"
                    :error-messages="errors"
                  ></v-text-field>
                </Field>

                <Field
                  :name="`exercise-${index}-set-${setIndex}-weight`"
                  v-model="set.weight"
                  :rules="rules.exist"
                  v-slot="{ field, errors }"
                >
                  <v-text-field
                    v-bind="field"
                    name="recordSetWeight"
                    v-model="set.weight"
                    label="Set weight"
                    :error-messages="errors"
                  ></v-text-field>
                </Field>
              </v-list-item>
            </v-list-item>
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
  },

  created() {
    this.loadTrainingDay();
  },
};
</script>

<style scoped></style>
