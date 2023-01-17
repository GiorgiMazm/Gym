describe("empty spec", () => {
  beforeEach(() => {
    cy.visit("catalog");
  });

  it("has new exercise button and it works", () => {
    const newExercise = cy.get("[data-test='create-button']");
    newExercise.should("contain.text", "New exercise");
    newExercise.click();
    newExercise.should("not.exist");
  });

  it("Has cancel button and can use it", () => {
    cy.get("[data-test='create-button']").click();
    const cancelExercise = cy.get("[data-test='cancel-button']");
    cancelExercise.should("contain.text", "Cancel");
    cancelExercise.click();
    cancelExercise.should("not.exist");
  });

  it("Can create a new exercise", () => {
    cy.get("[data-test='create-button']").click();
    cy.get("[data-test='save-button']").as("saveExercise");

    cy.get("@saveExercise").should("contain.text", "Save");
    cy.get("[data-test='muscle-group']").find("input").type("Chest");
    cy.get("[data-test='exercise-name']").find("input").type("Bench press");
    cy.get("[data-test='difficulty']").click();
    cy.get("div").contains("Medium").click();

    cy.get("@saveExercise").click();

    cy.get(".catalogCardName").should("have.text", "Bench press");
    cy.get(".catalogCardDifficulty").should("contain.text", "Medium");
    cy.get(".catalogCardMuscleGroup").should("contain.text", "Chest");

    cy.get(".catalogCardEdit").should("contain.text", "Edit");
    cy.get(".catalogCardDelete").should("contain.text", "Delete");

    cy.get(".catalogCardEdit").click();

    /*
    cy.get(".catalogCardDelete").click();
    cy.get("[data-test='muscle-group']").should("not.exist");
    cy.get("[data-test='exercise-name']").should("not.exist");
    cy.get("[data-test='difficulty']").should("not.exist");*/
  });
});
