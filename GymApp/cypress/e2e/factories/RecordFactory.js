export default {
  create: function ({ type, exercise, date, repetition, weight }) {
    cy.get("button").contains("Create new training day").click();
    if (type) cy.get('input[name="trainingType"').type(type);
    if (date) cy.get('input[name="recordDate"').type(date);
    if (exercise) cy.get('input[name="exercise-0"').type(exercise);
    if (repetition)
      cy.get('input[name="exercise-0-set-0-rep"').type(repetition);
    if (weight) cy.get('input[name="exercise-0-set-0-weight"').type(weight);

    cy.get("[data-test='save-button']").click();
  },

  delete: function (type) {
    cy.get(".v-card-text")
      .contains(type)
      .parent()
      .parent()
      .parent()
      .within(() => {
        cy.get(".deleteRecord").click();
      });
  },
};
