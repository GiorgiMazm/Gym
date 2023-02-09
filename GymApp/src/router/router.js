import { createRouter, createWebHistory } from "vue-router";
import HomePage from "@/components/HomePage.vue";
import GymRecords from "@/components/TrainingDay/GymRecordsPage.vue";
import ExercisesCatalogPage from "@/components/Catalog/ExercisesCatalogPage.vue";
import SingleExercise from "@/components/Catalog/SingleExercise.vue";
import NotFound from "@/components/NotFound.vue";

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

  {
    path: "/catalog/:id",
    component: SingleExercise,
  },

  {
    path: "/:notFound(.*)",
    component: NotFound,
  },
];

const router = createRouter({
  routes,
  history: createWebHistory(),
});

export default router;
