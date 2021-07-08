INSERT INTO public.color (id,color_name,hex_code) VALUES
	 (1,'Đen','#FFFFFF'),
	 (2,'Trắng','#000000');
INSERT INTO public.product_type (id,product_type) VALUES
	 (1,'Áo Hoodie');
INSERT INTO public.roles (id,"name") VALUES
	 (3,'ADMIN'),
	 (2,'STORE'),
	 (1,'CUSTOMER');
INSERT INTO public.sizes (id,size_name) VALUES
	 (1,'S'),
	 (2,'M'),
	 (3,'L');
INSERT INTO public.users (id,"password",status,username,avatar) VALUES
	 (1,'$2a$10$fyXsshPGtPb1zJVeUOb6BuLZcjFRzC6qkKNUKejc/DxXV9ZlXWvay','OPEN','admin',NULL),
	 (19,'$2a$10$lohqyU9yq2aRY4GNTXOUK.VcAMp.tkvXzvR0y4UfMP/r3o0CDL11m','OPEN','admin1',NULL),
	 (20,'$2a$10$PR7IGKWWlmKy0NwrYA/CBOD/ww.lmJ61vShw/8KtuTnBDSdbUPySy','OPEN','ngocnguyen',NULL);
INSERT INTO public.customer (id,birth_day,email,first_name,last_name,phone,user_id) VALUES
	 (1,'1999-08-17 00:00:00','ninhbuise@gmail.com','Ninh','Bui','0339737498',1);
INSERT INTO public.store (id,"name","owner") VALUES
	 (1,'Shop Cá Cơm',20);
INSERT INTO public.product (id,"name",price,quantity,product_type) VALUES
	 (1,'Áo Hoodie Nỉ Bông Unisex Streetwear Lục Lăng ( unisex nam nữ đều mặc được)',99.0,100,1);
INSERT INTO public.user_roles (user_id, role_id) VALUES
	 (1,3),
	 (19,3),
	 (20,1);
INSERT INTO public.product_colors (product_id,colors_id) VALUES
	 (1,1),
	 (1,2);
INSERT INTO public.product_sizes (product_id,sizes_id) VALUES
	 (1,1),
	 (1,2),
	 (1,3);
INSERT INTO public.store_products (store_id, products_id) VALUES 
	 (1,1);
	 
select * from product p join store_products sp on sp.products_id = p.id
	join store s on s.id  = sp.store_id 
		where s."name" like '%Shop Cá Cơm'