--context: dev
INSERT INTO INGREDIENT (name) values ('potato');
INSERT INTO INGREDIENT (name) values ('salad');
INSERT INTO INGREDIENT (name) values ('meat');
INSERT INTO INGREDIENT (name) values ('fish');
INSERT INTO RECIPE (name, is_vegetarian, instructions, serves) values ('Potato salad', true, 'Use olive oil potato and salad', 2);
INSERT INTO RECIPE (name, is_vegetarian, instructions, serves) values ('Fish chips', false, 'Cook fish and potato in the oven', 4);
INSERT INTO RECIPE (name, is_vegetarian, instructions, serves) values  ('Meat with potato', false, 'Cook meat and potato in the oven', 3);

INSERT INTO RECIPE_INGREDIENT (recipe_id, ingredient_id)
values (select id from RECIPE where name = 'Potato salad', select id from INGREDIENT where name = 'potato');
INSERT INTO RECIPE_INGREDIENT (recipe_id, ingredient_id)
values (select id from RECIPE where name = 'Potato salad', select id from INGREDIENT where name = 'salad');

INSERT INTO RECIPE_INGREDIENT (recipe_id, ingredient_id)
values (select id from RECIPE where name = 'Fish chips', select id from INGREDIENT where name = 'fish');
INSERT INTO RECIPE_INGREDIENT (recipe_id, ingredient_id)
values (select id from RECIPE where name = 'Fish chips', select id from INGREDIENT where name = 'potato');

INSERT INTO RECIPE_INGREDIENT (recipe_id, ingredient_id)
values (select id from RECIPE where name = 'Meat with potato', select id from INGREDIENT where name = 'potato');
INSERT INTO RECIPE_INGREDIENT (recipe_id, ingredient_id)
values (select id from RECIPE where name = 'Meat with potato', select id from INGREDIENT where name = 'meat');

