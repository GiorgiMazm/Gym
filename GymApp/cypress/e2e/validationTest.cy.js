import exerciseFactory from "./ExerciseFactory";

describe("Can't create a new exercise if", () => {
  beforeEach(() => {
    cy.visit("catalog");
  });

  it("Name is empty", () => {
    exerciseFactory.create("", "chest777", "Hard", "Do bench press");
    cy.get(".all-exercises").should("not.contain.text", "chest777");
    cy.get(".v-messages__message").should("have.text", "Field is required!");
  });

  it("Muscle is empty", () => {
    const exerciseName = "Chest4464464";
    exerciseFactory.create(exerciseName, "", "Hard", "Do bench press");
    cy.get(".v-messages__message").should("have.text", "Field is required!");
    cy.get(".all-exercises").should("not.contain.text", exerciseName);
  });

  it("Difficulty is empty", () => {
    const exerciseName = "Chest446146464";
    exerciseFactory.create(exerciseName, "chest", "", "Do bench press");
    cy.get(".v-messages__message").should("have.text", "Field is required!");
    cy.get(".all-exercises").should("not.contain.text", exerciseName);
  });

  it("Description is empty", () => {
    const exerciseName = "Chest446436464";
    exerciseFactory.create(exerciseName, "chest", "Easy", "");
    cy.get(".v-messages__message").should("have.text", "Field is required!");
    cy.get(".all-exercises").should("not.contain.text", exerciseName);
  });
});

describe("Can create a new exercise if", () => {
  beforeEach(() => {
    cy.visit("catalog");
  });

  it("All field are correct", () => {
    const exerciseName = "Biceps Curls";
    exerciseFactory.create(
      exerciseName,
      "Biceps",
      "Easy",
      "Do some curls for ur biceps"
    );
    cy.get("div").should("not.contain.text", "Field is required!");
    cy.get(".all-exercises").should("contain.text", exerciseName);

    exerciseFactory.delete(exerciseName);
  });
});
