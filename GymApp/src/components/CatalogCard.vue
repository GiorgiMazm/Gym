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
    <v-text-field
      v-if="editMode"
      v-model="filteredExercises[exerciseIndex].name"
      label="Exercise's Name"
    ></v-text-field>

    <v-select
      v-if="editMode"
      v-model="filteredExercises[exerciseIndex].difficulty"
      label="Difficulty"
      :items="['Easy', 'Medium', 'Hard']"
    ></v-select>

    <v-text-field
      v-if="editMode"
      v-model="filteredExercises[exerciseIndex].muscleGroup"
      label="Muscle Group"
    ></v-text-field>

    <v-text-field
      v-if="editMode"
      v-model="filteredExercises[exerciseIndex].description"
      label="Description"
    ></v-text-field>
    <v-card-actions>
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
      <v-btn
        v-if="editMode"
        @click="editExercise1"
        text
        color="deep-purple accent-4"
      >
        Save
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { mapActions, mapState } from "pinia";
import { useCatalogStore } from "@/stores/CatalogStore";

export default {
  name: "CatalogCard",
  data() {
    return {
      editMode: false,
    };
  },
  methods: {
    ...mapActions(useCatalogStore, ["editFilter"]),
    changeEditMode() {
      this.editMode = !this.editMode;
      setTimeout(() => this.editFilter("All"), 2000);
    },
    ...mapActions(useCatalogStore, ["deleteExercise"]),
    ...mapActions(useCatalogStore, ["editExercise"]),

    editExercise1() {
      this.editExercise(
        this.filteredExercises[this.exerciseIndex].muscleGroup,
        this.filteredExercises[this.exerciseIndex].name,
        this.filteredExercises[this.exerciseIndex].difficulty,
        this.filteredExercises[this.exerciseIndex].description,
        this.filteredExercises[this.exerciseIndex].id
      );
      this.changeEditMode();
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
