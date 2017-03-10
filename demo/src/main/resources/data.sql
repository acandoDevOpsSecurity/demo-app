INSERT INTO PUBLIC.USER_PROFILE (id, name, password, is_author, is_admin, icon, private_snippet, website, color) VALUES (1,'Admin','password',FALSE,TRUE,'icon-a.png','Hi, ich bin der <strong>mächtige Administrator</strong> dieses Systems.','www.acando.de','blue');
INSERT INTO PUBLIC.USER_PROFILE (id, name, password, is_author, is_admin, icon, private_snippet, website, color) VALUES (2,'Shushant Kakkar','password',TRUE,FALSE,'icon-b.png','Lorem ipsum text goes here....','http://www.acando.de','#ff9900');
INSERT INTO PUBLIC.USER_PROFILE (id, name, password, is_author, is_admin, private_snippet, website, color) VALUES (3,'Benjamin Schnarr','password',TRUE,FALSE,'Lorem ipsum text goes here....','http://www.acando.de','#ff3399');
INSERT INTO PUBLIC.USER_PROFILE (id, name, password, is_author, is_admin, private_snippet, website, color) VALUES (4,'Andreas Vetterlein','password',TRUE,FALSE,'Lorem ipsum text goes here....','http://www.acando.de','green');
INSERT INTO PUBLIC.USER_PROFILE (id, name, password, is_author, is_admin, private_snippet, website, color) VALUES (5,'Michael Kaiser','password',TRUE,FALSE,'Lorem ipsum text goes here....','http://www.acando.de','#00ccff');
INSERT INTO PUBLIC.USER_PROFILE (id, name, password, is_author, is_admin, private_snippet, website, color) VALUES (6,'Frank Pöhler','password',TRUE,FALSE,'Lorem ipsum text goes here....','http://www.acando.de','#660000');

INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (1,2,'Heute hacken wir');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (2,2,'Denn alles wird aus Hack gemacht');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (3,3,'Entschuldigen Sie, ham Sie Ćevapčići?');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (4,3,'Tut mir leid, wenn ich glotze, schöne Königsberger Kloppse!');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (5,4,'Ey was geht, haben Sie Hacksteak?');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (6,4,'Mach mir Chilli-Concarne, ich will ne Hackfahne!');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (7,5,'In Blankenese essen sie Bolognese');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (8,5,'Hör mal zu, du Klappspaten! Ich will jetzt nen Hackbraten!');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (9,6,'Oder Frikadelle, sonst kriegst du ne Schelle!');
INSERT INTO PUBLIC.SNIPPET (id, user_id, text) VALUES (10,6,'Ich werd gleich zum Attentäter und aus Hackepeter wird Kacke später');