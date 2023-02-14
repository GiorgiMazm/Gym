<template>
  <v-app>
    <v-card>
      <v-layout>
        <AppHeader>
          <template #nameOfPage>Single Record</template>
        </AppHeader>
        <v-main>
          <div v-if="record" class="single-record">
            <v-card-title class="text-h5"
              >Training from: {{ record.createdAt }}
            </v-card-title>
            <v-card-title> Training Type: {{ record.type }}</v-card-title>
            <v-list-item
              v-for="(exercise, index) in record.exercises"
              density="compact"
              :key="index"
            >
              <v-list-item-title left>
                Exercise {{ index + 1 }}: {{ record.exercises[index].name }}
              </v-list-item-title>

              <v-list-item-subtitle
                v-for="(set, setIndex) in record.exercises[index].sets"
                density="compact"
                :key="setIndex"
                >Set {{ setIndex + 1 }}, <span></span> repetition:
                <span>
                  {{ record.exercises[index].sets[setIndex].repetition }}</span
                >, weight:
                <span>
                  {{ record.exercises[index].sets[setIndex].weight }}</span
                >
                kg
              </v-list-item-subtitle>
            </v-list-item>
            <v-card-actions>
              <router-link to="/records">
                <v-btn text color="deep-purple accent-4"> Back </v-btn>
              </router-link>
            </v-card-actions>
          </div>
          <div v-else>
            <v-card-title> Ooops... there is no such a record</v-card-title>
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
import { useGymStore } from "@/stores/GymStore";
import { mapActions } from "pinia";

export default {
  name: "SingleRecord",
  components: { AppHeader },

  data() {
    return {
      record: {},
    };
  },

  methods: {
    ...mapActions(useGymStore, ["getTrainingDayById"]),

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
