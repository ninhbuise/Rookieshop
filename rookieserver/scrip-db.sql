INSERT INTO public.color (id,color_name,hex_code) VALUES
	 (1,'Trắng','#FFFFFF'),
	 (2,'Đen','#000000');
INSERT INTO public.product_type (id,product_type) VALUES
	 (1,'Áo Hoodie'),
	 (2, 'ÁO KHOÁC');
INSERT INTO public.roles (id,"name") VALUES
	 (3,'ROLE_ADMIN'),
	 (2,'ROLE_STORE'),
	 (1,'ROLE_CUSTOMER');
INSERT INTO public.sizes (id,size_name) VALUES
	 (1,'S'),
	 (2,'M'),
	 (3,'L');
INSERT INTO public.users (id,"password",status,username,avatar) VALUES
	 (1,'$2a$10$fyXsshPGtPb1zJVeUOb6BuLZcjFRzC6qkKNUKejc/DxXV9ZlXWvay','OPEN','admin',NULL),
	 (2,'$2a$10$lohqyU9yq2aRY4GNTXOUK.VcAMp.tkvXzvR0y4UfMP/r3o0CDL11m','OPEN','cadonglanh',NULL),
	 (3,'$2a$10$PR7IGKWWlmKy0NwrYA/CBOD/ww.lmJ61vShw/8KtuTnBDSdbUPySy','OPEN','ngocnguyen',NULL);
INSERT INTO public.customer (id,birth_day,email,first_name,last_name,phone,user_id) VALUES
	 (1,'1999-08-17 00:00:00','ngocnguyen@gmail.com','Ngọc','Nguyễn','0339737498',3);
INSERT INTO public.store (id,"name","owner") VALUES
	 (1,'Shop Cá Cơm',3);
INSERT INTO public.product (id,"name",price,quantity,product_type,store_id) VALUES
	 (1,'Áo Hoodie Nỉ Bông Unisex Streetwear Lục Lăng ( unisex nam nữ đều mặc được)',99000.0,100,1,1),
	 (2,'ÁO KHOÁC BOMBER NAM NỮ VẢI MERO CAO CẤP HỌA TIẾT THÊU 08 HUYỀN THOẠI SIÊU NGẦU HÓT TRIEND 2021',119000.0,100,1,1);
INSERT INTO public.user_roles (user_id, role_id) VALUES
	 (1,3),
	 (2,2),
	 (3,1);
INSERT INTO public.product_colors (product_id,colors_id) VALUES
	 (1,1),
	 (1,2),
	 (2,1),
	 (2,2);
INSERT INTO public.product_sizes (product_id,sizes_id) VALUES
	 (1,1),
	 (1,2),
	 (1,3),
	 (2,1),
	 (2,2),
	 (2,3);

