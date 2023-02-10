<template>
  <v-card class="mx-auto" width="300">
    <v-card-text>
      <p class="text-h4 text--primary catalogCardName">
        {{ filteredExercises[exerciseIndex].name }}
      </p>
      <p class="catalogCardDifficulty">
        Difficulty: {{ filteredExercises[exerciseIndex].difficulty }}
      </p>
      <p class="catalogCardMuscleGroup">
        Muscle group: {{ filteredExercises[exerciseIndex].muscleGroup }}
      </p>

      <p>
        Description:
        {{ filteredExercises[exerciseIndex].description.slice(0, 20) }}...
      </p>
    </v-card-text>

    <v-card-actions>
      <router-link :to="'catalog/' + filteredExercises[exerciseIndex].id">
        <v-btn text color="deep-purple accent-4" class="d-block">
          Learn more
        </v-btn>
      </router-link>

      <router-link :to="'catalog/edit/' + filteredExercises[exerciseIndex].id">
        <v-btn text color="deep-purple accent-4" class="d-block"> Edit </v-btn>
      </router-link>

      <v-btn
        @click="deleteExercise(filteredExercises[exerciseIndex].id)"
        text
        color="deep-purple accent-4"
        class="catalogCardDelete"
      >
        Delete
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { mapActions, mapState } from "pinia";
import { useCatalogStore } from "@/stores/CatalogStore";

export default {
  name: "CatalogCard",
  methods: {
    ...mapActions(useCatalogStore, ["deleteExercise"]),
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
