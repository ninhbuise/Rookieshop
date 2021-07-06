INSERT INTO "role" (id, name) VALUES 
	(3, 'ADMIN'),
	(2, 'STORE'),
	(1, 'CUSTOMER');

INSERT INTO public."user" (id, "password" , status, avatar, username) VALUES
     (1,'$2a$10$fyXsshPGtPb1zJVeUOb6BuLZcjFRzC6qkKNUKejc/DxXV9ZlXWvay','OPEN',NULL,'admin');
INSERT INTO public.user_roles (user_id, roles_id) VALUES
      (1,3);
INSERT INTO public.customer (id, birth_day, email, first_name, last_name, phone, user_id) VALUES
      (1,'1999-08-17 00:00:00','ninhbuise@gmail.com','Ninh','Bui','0339737498', 1);     

select * from user_role ur 
