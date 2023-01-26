export default {
  create: function ({ name, muscle, description, difficulty }) {
    cy.get("[data-test='create-button']").click();
    if (name) cy.get("[data-test='exercise-name']").find("input").type(name);
    if (muscle) cy.get("[data-test='muscle-group']").find("input").type(muscle);
    cy.get("[data-test='difficulty']").click();
    if (difficulty)
      cy.get(".v-overlay-container").within(() => {
        cy.get("div").contains(difficulty).click();
      });
    if (description)
      cy.get("[data-test='description']").find("input").type(description);
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
