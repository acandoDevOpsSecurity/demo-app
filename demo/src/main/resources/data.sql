INSERT INTO PUBLIC.APPUSER (id, name, password, is_author, is_admin, is_locked, icon, private_snippet, website, color) VALUES (1,'Admin','$2a$10$De4RAFuUC6kuMjWsFvTgqO0JGJe0KKjKroIDWjC/RC/DzDtzfbUj.',FALSE,TRUE,FALSE,'default-smiley.JPG','Hi, ich bin der <strong>mächtige Administrator</strong> dieses Systems.','www.google.de','blue');
INSERT INTO PUBLIC.APPUSER (id, name, password, is_author, is_admin, is_locked, icon, private_snippet, website, color) VALUES (2,'Shushant','$2a$10$De4RAFuUC6kuMjWsFvTgqO0JGJe0KKjKroIDWjC/RC/DzDtzfbUj.',TRUE,FALSE,FALSE,'default-smiley.JPG','Lorem ipsum text goes here....','http://www.google.de','#ff9900');
INSERT INTO PUBLIC.APPUSER (id, name, password, is_author, is_admin, is_locked, icon, private_snippet, website, color) VALUES (3,'Benjamin','$2a$10$De4RAFuUC6kuMjWsFvTgqO0JGJe0KKjKroIDWjC/RC/DzDtzfbUj.',TRUE,FALSE,FALSE,'default-smiley.JPG','Lorem ipsum text goes here....','http://www.google.de','#ff3399');
INSERT INTO PUBLIC.APPUSER (id, name, password, is_author, is_admin, is_locked, icon, private_snippet, website, color) VALUES (4,'Andreas','$2a$10$De4RAFuUC6kuMjWsFvTgqO0JGJe0KKjKroIDWjC/RC/DzDtzfbUj.',TRUE,FALSE,FALSE,'default-smiley.JPG','Lorem ipsum text goes here....','http://www.google.de','green');
INSERT INTO PUBLIC.APPUSER (id, name, password, is_author, is_admin, is_locked, icon, private_snippet, website, color) VALUES (5,'Michael','$2a$10$De4RAFuUC6kuMjWsFvTgqO0JGJe0KKjKroIDWjC/RC/DzDtzfbUj.',TRUE,FALSE,FALSE,'default-smiley.JPG','Lorem ipsum text goes here....','http://www.google.de','#00ccff');
INSERT INTO PUBLIC.APPUSER (id, name, password, is_author, is_admin, is_locked, icon, private_snippet, website, color) VALUES (6,'Frank','$2a$10$De4RAFuUC6kuMjWsFvTgqO0JGJe0KKjKroIDWjC/RC/DzDtzfbUj.',TRUE,FALSE,FALSE,'default-smiley.JPG','Lorem ipsum text goes here....','http://www.google.de','#660000');

INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (1,2,'Test snippet 1');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (2,2,'Test snippet 2');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (3,3,'Test snippet 3');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (4,3,'Test snippet 4');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (5,4,'Test snippet 5');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (6,4,'Test snippet 6');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (7,5,'Test snippet 7');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (8,5,'Test snippet 8');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (9,6,'Test snippet 9');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (10,6,'Test snippet 10');