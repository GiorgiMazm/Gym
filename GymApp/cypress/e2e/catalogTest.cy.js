import exerciseFactory from "./ExerciseFactory";

describe("catalogPage", () => {
  beforeEach(() => {
    cy.visit("catalog");
  });

  it("create new and delete it", () => {
    const exerciseName = "myName7354423442";
    exerciseFactory.create({
      name: exerciseName,
      muscle: "tongue",
      difficulty: "Medium",
      description: "La la la",
    });

    cy.get(".all-exercises").should("contain.text", exerciseName);
    exerciseFactory.delete(exerciseName);
    cy.get(".all-exercises").should("not.contain.text", exerciseName);
  });

  it("edit exercise", () => {
    const exerciseName = "myName7354423442";
    exerciseFactory.create({
      name: exerciseName,
      muscle: "tongue",
      difficulty: "Medium",
      description: "La la la",
    });
    cy.get(".all-exercises").should("contain.text", exerciseName);

    cy.get(".catalogCardName")
      .contains(exerciseName)
      .parent()
      .parent()
      .within(() => {
        cy.get("span").contains("Edit").click();
      });

    const newExerciseName = "i'm brand new name baby";
    cy.get("input[name='exerciseName']").clear().type(newExerciseName);

    cy.get("span").contains("Save").click();
    cy.get(".all-exercises").should("not.contain.text", exerciseName);
    cy.get(".all-exercises").should("contain.text", newExerciseName);

    exerciseFactory.delete(newExerciseName);
  });

  it("learn more", () => {
    const exerciseName = "myName736423442";
    exerciseFactory.create({
      name: exerciseName,
      muscle: "tongue",
      difficulty: "Medium",
      description: "La la gdgdg ssf la",
    });
    cy.get(".all-exercises").should("contain.text", exerciseName);

    cy.get(".catalogCardName")
      .contains(exerciseName)
      .parent()
      .parent()
      .within(() => {
        cy.get("span").contains("Learn more").click();
      });

    cy.get(".v-card-title")
      .contains("Exercise:")
      .should("contain.text", exerciseName);
    cy.get("span").contains("Back").click();
    exerciseFactory.delete(exerciseName);
  });
});
