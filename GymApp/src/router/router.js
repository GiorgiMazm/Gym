import { createRouter, createWebHistory } from "vue-router";
import HomePage from "@/components/HomePage.vue";
import GymRecords from "@/components/GymRecordsPage.vue";
import ExercisesCatalogPage from "@/components/ExercisesCatalogPage.vue";

const routes = [
  {
    path: "/records",
    component: GymRecords,
  },
  {
    path: "/",
    component: HomePage,
  },

  {
    path: "/catalog",
    component: ExercisesCatalogPage,
  },
];

const router = createRouter({
  routes,
  history: createWebHistory(),
});

export default router;
