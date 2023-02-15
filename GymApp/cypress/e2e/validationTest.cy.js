import exerciseFactory from "./ExerciseFactory";
import recordFactory from "./RecordFactory";
describe("Can't create a new exercise if", () => {
  beforeEach(() => {
    cy.visit("catalog");
  });

  it("Name is empty", () => {
    exerciseFactory.create({
      muscle: "chest777",
      description: "do it!!",
      difficulty: "Hard",
    });
    cy.get(".all-exercises").should("not.contain.text", "chest777");
    cy.get(".v-messages__message").should("have.text", "Field is required!");
  });

  it("Muscle is empty", () => {
    const exerciseName = "Chest4464464";
    exerciseFactory.create({
      name: exerciseName,
      description: "do it!!",
      difficulty: "Hard",
    });
    cy.get(".v-messages__message").should("have.text", "Field is required!");
    cy.get(".all-exercises").should("not.contain.text", exerciseName);
  });

  it("Difficulty is empty", () => {
    const exerciseName = "Chest446146464";
    exerciseFactory.create({
      name: exerciseName,
      muscle: "chest",
      description: "do it!!",
    });
    cy.get(".v-messages__message").should("have.text", "Field is required!");
    cy.get(".all-exercises").should("not.contain.text", exerciseName);
  });

  it("Description is empty", () => {
    const exerciseName = "Chest446436464";
    exerciseFactory.create({
      name: exerciseName,
      muscle: "chest",
      difficulty: "Hard",
    });
    cy.get(".v-messages__message").should("have.text", "Field is required!");
    cy.get(".all-exercises").should("not.contain.text", exerciseName);
  });

  it("Exercise name is not unique", () => {
    const exerciseName = "Chest44646464";
    exerciseFactory.create({
      name: exerciseName,
      muscle: "chest",
      difficulty: "Hard",
      description: "do it!!",
    });

    exerciseFactory.create({
      name: exerciseName,
      muscle: "chest",
      difficulty: "Hard",
      description: "do it!!",
    });
    cy.get(".v-messages__message").should(
      "have.text",
      "Exercise name must be unique"
    );
    exerciseFactory.delete(exerciseName);
  });
});

describe("Can create a new exercise if", () => {
  it("All field are correct", () => {
    cy.visit("catalog");
    const exerciseName = "Biceps Curls";
    exerciseFactory.create({
      name: exerciseName,
      muscle: "Biceps",
      difficulty: "Easy",
      description: "Do some curls for ur biceps",
    });
    cy.get("div").should("not.contain.text", "Field is required!");
    cy.get(".all-exercises").should("contain.text", exerciseName);

    exerciseFactory.delete(exerciseName);
  });
});

describe("Can create a new training day if", () => {
  it("All field are correct", () => {
    cy.visit("records");
    const trainingType = "Cheeest6574857381";
    recordFactory.create({
      type: trainingType,
      exercise: "bench",
      weight: 100,
      repetition: 8,
      date: "2000-11-23",
    });
    cy.get(".records-wrapper").should("contain.text", trainingType);
    recordFactory.delete(trainingType);
    cy.get(".records-wrapper").should("not.contain.text", trainingType);
  });
});
