<template>
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
        v-model="exercise.name"
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
    <button class="mr-5" @click.prevent="addSet(index)">Add set</button>

    <button @click.prevent="deleteExercise(index)">Delete exercise</button>
  </div>
</template>

<script>
import rules from "@/Validation/rules";
import { Field, Form } from "vee-validate";
export default {
  name: "ExercisesArray",
  data() {
    return {
      rules: rules,
    };
  },

  components: {
    Form,
    Field,
  },
  props: {
    exerciseArray: Array,
  },
  methods: {
    addSet(index) {
      this.exerciseArray[index].sets.push({
        repetition: "",
        weight: "",
      });
    },

    deleteExercise(index) {
      this.exerciseArray.splice(index, 1);
    },
    deleteExerciseSet(index, setIndex) {
      this.exerciseArray[index].sets.splice(setIndex, 1);
    },
  },
};
</script>

<style scoped></style>
