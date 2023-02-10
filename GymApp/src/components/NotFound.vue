<template>
  <v-app>
    <v-card>
      <v-layout>
        <AppHeader> <template #nameOfPage>404</template></AppHeader>
        <v-main>
          <div>
            <v-card-title>
              Ooops... You seemed to be drunk! Cuz there is no such a
              page</v-card-title
            >

            <router-link to="/">
              <v-btn class="mb-3 ml-3" text color="deep-purple accent-4">
                Back to the home
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
export default {
  name: "NotFound",
  components: {
    AppHeader,
  },
};
</script>

<style scoped></style>
