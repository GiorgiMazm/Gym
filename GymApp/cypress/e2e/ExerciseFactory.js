export default {
  create: function (exercise) {
    cy.get("[data-test='create-button']").click();
    if (exercise.name)
      cy.get("[data-test='exercise-name']").find("input").type(exercise.name);
    if (exercise.muscle)
      cy.get("[data-test='muscle-group']").find("input").type(exercise.muscle);
    cy.get("[data-test='difficulty']").click();
    if (exercise.difficulty)
      cy.get(".v-overlay-container").within(() => {
        cy.get("div").contains(exercise.difficulty).click();
      });
    if (exercise.description)
      cy.get("[data-test='description']")
        .find("input")
        .type(exercise.description);
    cy.get("[data-test='save-button']").click();
  },

  delete: function (name) {
    cy.get(".catalogCardName")
      .contains(name)
      .parent()
      .parent()
      .within(() => {
        cy.get(".catalogCardDelete").click();
      });
  },
};
