/* eslint-env node */
require("@rushstack/eslint-patch/modern-module-resolution");

module.exports = {
  root: true,
  extends: [
    "plugin:vue/vue3-essential",
    "eslint:recommended",
    "@vue/eslint-config-prettier",
  ],

  "env": {
    "cypress/globals": true
  },
    "plugins": [
    "cypress"
  ],
  parserOptions: {
    ecmaVersion: "latest",
  },
};
