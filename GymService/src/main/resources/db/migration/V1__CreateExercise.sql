CREATE TABLE exercise
(
    id           serial not null primary key,
    name         text   not null,
    description  text   not null,
    difficulty   text   not null,
    muscle_group text   not null
);

INSERT INTO exercise (name, description, difficulty, muscle_group)
VALUES ('Bench press',
        'The Bench Press is a full body, compound exercise. It works your chest, shoulders and triceps most. ' ||
        'It’s the most effective exercise to gain upper-body strength and muscle mass because it’s the upper-body ' ||
        'exercise you’ll lift most weight on (more than Overhead Press). The bigger your bench, the bigger your chest.  ' ||
        'To avoid shoulder pain, tuck your elbows 75° when you lower the bar. Don’t try to stretch your chest by flaring your' ||
        ' elbows 90° out. You’ll impinge your shoulders if your upper-arms are perpendicular to your torso at the bottom. T' ||
        'uck your elbows 75° to Bench Press pain-free.',
        'Hard', 'chest'),
       ('Squat',
        'Knowing how to do squats using your legs and not your back will translate into better workout performance and real-life functionality. ' ||
        'Also among the benefits of squats is that they are one of the most time-effective moves for shaping and strengthening your entire lower body,
        especially your backside. Since it engages all the major muscle groups of the lower body at once,
        you can actually squat your way to being an improved runner, tennis player, or dancer.(As well as being able to
        move furniture without pulling something !) ', ' Hard ', ' legs ');