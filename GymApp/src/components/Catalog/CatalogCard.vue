<template>
  <v-card class="mx-auto" width="300">
    <v-card-text v-if="!editMode">
      <p class="text-h4 text--primary catalogCardName">
        {{ filteredExercises[exerciseIndex].name }}
      </p>
      <p class="catalogCardDifficulty">
        Difficulty: {{ filteredExercises[exerciseIndex].difficulty }}
      </p>
      <p class="catalogCardMuscleGroup">
        Muscle group: {{ filteredExercises[exerciseIndex].muscleGroup }}
      </p>

      <p>Description: {{ filteredExercises[exerciseIndex].description }}</p>
    </v-card-text>

    <v-card-actions>
      <router-link :to="'catalog/' + filteredExercises[exerciseIndex].id">
        <v-btn
          v-if="!editMode"
          text
          color="deep-purple accent-4"
          class="d-block"
        >
          Learn more
        </v-btn>
      </router-link>

      <v-btn
        v-if="!editMode"
        @click="changeEditMode"
        text
        color="deep-purple accent-4"
        class="catalogCardEdit"
      >
        Edit
      </v-btn>

      <v-btn
        v-if="!editMode"
        @click="deleteExercise(filteredExercises[exerciseIndex].id)"
        text
        color="deep-purple accent-4"
        class="catalogCardDelete"
      >
        Delete
      </v-btn>
    </v-card-actions>

    <Form name="editExerciseForm" v-if="editMode" @submit="exerciseEdit">
      <Field
        name="exerciseName"
        v-model="filteredExercises[exerciseIndex].name"
        :rules="exist"
        v-slot="{ field, errors }"
      >
        <v-text-field
          v-bind="field"
          name="exerciseName"
          v-model="filteredExercises[exerciseIndex].name"
          label="Exercise's name"
          :error-messages="errors"
        ></v-text-field>
      </Field>

      <Field
        name="difficulty"
        v-model="filteredExercises[exerciseIndex].difficulty"
        :rules="exist"
        v-slot="{ field, errors }"
      >
        <v-select
          v-bind="field"
          name="difficulty"
          v-model="filteredExercises[exerciseIndex].difficulty"
          label="Difficulty"
          :error-messages="errors"
          :items="['Easy', 'Medium', 'Hard']"
        ></v-select>
      </Field>

      <Field
        name="muscleGroup"
        v-model="filteredExercises[exerciseIndex].muscleGroup"
        :rules="exist"
        v-slot="{ field, errors }"
      >
        <v-text-field
          v-bind="field"
          name="muscleGroup"
          v-model="filteredExercises[exerciseIndex].muscleGroup"
          label="Muscle Group"
          :error-messages="errors"
        ></v-text-field>
      </Field>

      <Field
        name="description"
        v-model="filteredExercises[exerciseIndex].description"
        :rules="exist"
        v-slot="{ field, errors }"
      >
        <v-text-field
          v-bind="field"
          name="description"
          v-model="filteredExercises[exerciseIndex].description"
          label="Description"
          :error-messages="errors"
        ></v-text-field>
      </Field>

      <v-card-actions>
        <v-btn color="deep-purple accent-4" type="submit"> Save </v-btn>
      </v-card-actions>
    </Form>
  </v-card>
</template>

<script>
import { mapActions, mapState } from "pinia";
import { useCatalogStore } from "@/stores/CatalogStore";
import { Field, Form } from "vee-validate";

export default {
  name: "CatalogCard",
  components: {
    Form,
    Field,
  },
  data() {
    return {
      editMode: false,
    };
  },
  methods: {
    ...mapActions(useCatalogStore, ["editFilter"]),
    changeEditMode() {
      this.editMode = !this.editMode;
    },
    ...mapActions(useCatalogStore, ["deleteExercise"]),
    ...mapActions(useCatalogStore, ["editExercise"]),

    exerciseEdit() {
      this.editExercise(
        this.filteredExercises[this.exerciseIndex].muscleGroup,
        this.filteredExercises[this.exerciseIndex].name,
        this.filteredExercises[this.exerciseIndex].difficulty,
        this.filteredExercises[this.exerciseIndex].description,
        this.filteredExercises[this.exerciseIndex].id
      );
      this.changeEditMode();
    },

    exist(value) {
      if (value) {
        return true;
      }
      return "Field is required";
    },
  },

  props: {
    exerciseIndex: Number,
  },

  computed: {
    ...mapState(useCatalogStore, ["filteredExercises"]),
  },
};
</script>

<style scoped></style>
