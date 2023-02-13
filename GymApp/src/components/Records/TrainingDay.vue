<template>
  <v-card class="mt-4" width="400px">
    <v-card-item>
      <v-card-title v-if="!editMode" class="text-h5"
        >Day {{ getCurrentDay.createdAt }}
      </v-card-title>

      <v-text-field
        v-if="editMode"
        v-model="getCurrentDay.createdAt"
        label="Training day"
      ></v-text-field>

      <v-card-subtitle>
        <v-icon
          icon="mdi-dumbbell"
          size="18"
          color="error"
          class="mr-1 pb-1"
        ></v-icon>

        Trainings information
      </v-card-subtitle>
    </v-card-item>

    <v-card-text class="py-0">
      <v-row align="center" hide-gutters no-gutters>
        <v-col v-if="!editMode" class="text-h2" cols="6">
          {{ getCurrentDay.type }}
        </v-col>
        <v-text-field
          v-if="editMode"
          v-model="getCurrentDay.type"
          label="Training Type"
        ></v-text-field>
      </v-row>
    </v-card-text>

    <GymExercise
      v-for="(exercise, index) in getCurrentDay.exercises"
      :key="index"
      :exerciseIndex="index"
      :dayIndex="dayIndex"
      :editMode="editMode"
    ></GymExercise>
    <v-divider></v-divider>

    <v-card-actions>
      <router-link :to="'record/' + getCurrentDay.id">
        <v-btn text color="deep-purple accent-4" class="d-block">
          Learn more
        </v-btn>
      </router-link>

      <v-btn v-if="!editMode" @click="changeEditMode"> Edit Day </v-btn>
      <v-btn v-if="!editMode" @click="deleteTrainingDay(getCurrentDay.id)">
        Delete Day
      </v-btn>
      <v-btn
        v-if="editMode"
        @click="
          changeEditMode();
          trainingDayEdit();
        "
      >
        Save Day
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import GymExercise from "./GymExercise.vue";
import { useGymStore } from "@/stores/GymStore";
import { mapActions, mapState } from "pinia";
export default {
  name: "TrainingDay",
  props: {
    dayIndex: Number,
  },
  components: {
    GymExercise,
  },
  data() {
    return {
      editMode: false,
    };
  },
  computed: {
    ...mapState(useGymStore, ["trainingDays"]),
    getCurrentDay() {
      return this.trainingDays[this.dayIndex];
    },
  },
  methods: {
    ...mapActions(useGymStore, ["deleteTrainingDay"]),
    ...mapActions(useGymStore, ["editTrainingDay"]),

    changeEditMode() {
      this.editMode = !this.editMode;
    },
    trainingDayEdit() {
      this.editTrainingDay(this.getCurrentDay);
    },
  },
};
</script>

<style scoped></style>
