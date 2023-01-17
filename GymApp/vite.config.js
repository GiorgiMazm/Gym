import { fileURLToPath, URL } from "node:url";
import vuetify from "vite-plugin-vuetify";
import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue(), vuetify()],
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", import.meta.url)),
    },
  },
  test: {
    globals: true,
    environment: "jsdom",
    setupFiles: "vuetify.config.js",
    deps: {
      inline: ["vuetify"],
    },
    coverage: {
      provider: "istanbul", // or 'c8'
    },
  },
});
