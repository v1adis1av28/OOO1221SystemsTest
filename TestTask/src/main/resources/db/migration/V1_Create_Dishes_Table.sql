CREATE TABLE DISHES(
    id int primary key GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR not null,
    calorie float check ( calorie > 0 ),
    protein float check (protein >= 0),
    fat float check ( fat >= 0 ),
    carbs float check ( carbs >= 0 )
);