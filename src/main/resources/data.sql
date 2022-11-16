
INSERT INTO users(id,name,email,username,password,enabled) VALUES ('3f766ce2-cf17-4c99-a591-b89aec525d4e','Admin','admin@crealo.com','admin@crealo.com','admin',true);
INSERT INTO tags(name) VALUES ('Tag1'),('Tag2'),('Tag3'),('Tag4'),('Tag5');
INSERT INTO categories(name,parent_id) VALUES ('Category1',null),('Category2',null),('Category3',1);
INSERT INTO views(name,perspective) VALUES ('front','FRONT'),('back','BACK'),('left','LEFT'),('right','RIGHT');
INSERT INTO appearances(name,texture) VALUES ('white',false),('black',false);
INSERT INTO colors(name,color) VALUES ('white','#ffffff'),('black','#000000');
INSERT INTO designs(name,description,user_id) VALUES ('Design1','Description1','3f766ce2-cf17-4c99-a591-b89aec525d4e');
INSERT INTO medias(name,original_filename,content_type,user_id,url) VALUES ('Media1','Media1.png','image/png','3f766ce2-cf17-4c99-a591-b89aec525d4e','https://google.com');

INSERT INTO products(price,name,description,category_id) VALUES (1.0,'Product1','Description1',1),(2.0,'Product2','Description2',1),(3.0,'Product3','Description3',3);
INSERT INTO product_tags(product_id,tag_id) VALUES (1,1),(1,2),(1,3),(2,4),(2,5);
-- INSERT INTO product_medias(product_id,media_id,view_id,appearance_id) VALUES (1,1,1,1),(2,1,1,1);
INSERT INTO product_reviews(rating,comment,product_id,appearance_id,user_id) VALUES (5,'Comment1',1,1,'3f766ce2-cf17-4c99-a591-b89aec525d4e'),(4,'Comment2',1,2,'3f766ce2-cf17-4c99-a591-b89aec525d4e');
INSERT INTO product_views(product_id,view_id) VALUES (1,1),(1,2),(1,3),(1,4),(2,1),(2,2);
INSERT INTO product_appearances(product_id,appearance_id) VALUES (1,1),(1,2),(2,1);
INSERT INTO appearance_colors(appearance_id,color_id) VALUES (1,1),(2,2);

-- INSERT INTO products(price,name,description,category_id) VALUES (4.0,'Product4','Description4',2);
