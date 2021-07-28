INSERT INTO public.address (id,city,district,street,ward) VALUES
	 (79,'Lâm Đồng','Đà Lạt','Tổ Lầm Văn Thạch','P11');
INSERT INTO public.color (id,color_name,hex_code) VALUES
	 (1,'White','#FFFFFF'),
	 (2,'Black','#000000'),
	 (3,'Gray',NULL),
	 (4,'Dark Blue',NULL),
	 (5,'Pink',NULL),
	 (6,'Yellow',NULL);
INSERT INTO public.product_type (id,product_type) VALUES
	 (3,'Men''s clothes'),
	 (4,'Women''s Pajamas'),
	 (1,'Hoodie'),
	 (2,'Coat'),
	 (5,'Bag'),
	 (6,'Clock');
INSERT INTO public.sizes (id,size_name) VALUES
	 (2,'M'),
	 (1,'L'),
	 (3,'XL'),
	 (4,'XXL');
INSERT INTO public.image (id,alt,url) VALUES
	 (1,NULL,'/images/4bd7621459eeb6b7e9c8d8717df33b88.jpg'),
	 (2,NULL,'/images/6e4f13e42b9559e8af707ecffd96bcff.jpg'),
	 (3,NULL,'/images/8f0386253fb5a1ed9471358967d560d2.jpg'),
	 (5,NULL,'/images/89267e75053070ee7cc5a931c5be92f7.jpg'),
	 (6,NULL,'/images/1606fa50b51c4168cf4c70927f40a34f.jpg'),
	 (7,NULL,'/images/de25e08901040f77684e59036d21df41.jpg'),
	 (4,NULL,'/images/d3f58acde60697f4448182e5e0bd6812.jpg'),
	 (179,NULL,'https://firebasestorage.googleapis.com/v0/b/rookies-shop.appspot.com/o/folder%2Fd3f58acde60697f4448182e5e0bd6812.jpg?alt=media&token=496f6396-08e1-44f3-9919-ce54a8b22de2'),
	 (180,NULL,'https://firebasestorage.googleapis.com/v0/b/rookies-shop.appspot.com/o/folder%2F89267e75053070ee7cc5a931c5be92f7.jpg?alt=media&token=f1b1c175-a4de-42e5-a315-1517976f6d8c'),
	 (9,NULL,'/images/14c1ec4356104f12dca8c21091d16e41.jpg');
INSERT INTO public.image (id,alt,url) VALUES
	 (10,NULL,'/images/07d98bee1cff1ff4516e6dedeff6e9b2.jpg'),
	 (11,NULL,'/images/10ada2f71fa9baca785810d5cb2c5067.jpg'),
	 (12,NULL,'/images/eef5abb9b2bdd90364213cc57ee6d4fc.jpg'),
	 (13,NULL,'/images/83f09d830cb7d6f8874f440c3df7b9bc.jpg'),
	 (14,NULL,'/images/9e5db1dd997660fb0f2721c1fd9155d9.jpg'),
	 (8,NULL,'/images/75d9d998da31c306ea59a8ff0d1ea391.jpg'),
	 (15,NULL,'/images/1c30139a149bdfd4f829c8a8ddd93114.jpg'),
	 (16,NULL,'/images/c9149b37eb83e850d4af1ea3faa86f80.jpg'),
	 (17,NULL,'/images/a3acf1c38781f9493e6fd75c1ebfd4dd.jpg'),
	 (18,NULL,'/images/8f258b7c9ed94556be0eb32fe453d652.jpg');
INSERT INTO public.image (id,alt,url) VALUES
	 (19,NULL,'/images/a90a6bb35d3d3709a818735beeefef9c.jpg'),
	 (20,NULL,'/images/b1d74f6950385372e8249c9d15b1b151.jpg'),
	 (21,NULL,'/images/e945b18e8d01e96658fde9e79a58880b.jpg'),
	 (22,NULL,'/images/63cfe3bd5b78ad1aff1bb34dbd001c02.jpg'),
	 (23,NULL,'/images/77091ae2f43eeff604598679f96ef36b.jpg'),
	 (24,NULL,'/images/d0474778a7a8d9d92b85dcccc005e0e5.jpg'),
	 (25,NULL,'/images/354f6f7af5765cef15b6565d27d6ee51.jpg'),
	 (26,NULL,'/images/1269509b209411c9d378afac99f3a1a0.jpg'),
	 (27,NULL,'/images/062e874aeae696f4e7372ffd1ebce1ee.jpg'),
	 (28,NULL,'/images/307d1cddf7695662ca41bb89ad416d15.jpg');
INSERT INTO public.image (id,alt,url) VALUES
	 (29,NULL,'/images/afba517514a5a8040b05027041862a88.jpg'),
	 (30,NULL,'/images/c758fc65e67daf06fcbb33361c15c06f.jpg'),
	 (31,NULL,'/images/d56543ef357298fac8e0b73f12b148ec.jpg'),
	 (32,NULL,'/images/049fc73dbdbe1f723225bcebab37be57.jpg'),
	 (33,NULL,'/images/808d543357cd4325182de861014a3cb2.jpg'),
	 (34,NULL,'/images/1023c8c3ed45f178c511eaede136e6b2.jpg');
INSERT INTO public.roles (id,"name") VALUES
	 (3,'ROLE_ADMIN'),
	 (2,'ROLE_STORE'),
	 (1,'ROLE_CUSTOMER');
INSERT INTO public.users (id,"password",status,username,avatar) VALUES
	 (1,'$2a$10$fyXsshPGtPb1zJVeUOb6BuLZcjFRzC6qkKNUKejc/DxXV9ZlXWvay','ACTIVE','admin',NULL),
	 (2,'$2a$10$lohqyU9yq2aRY4GNTXOUK.VcAMp.tkvXzvR0y4UfMP/r3o0CDL11m','ACTIVE','cadonglanh',NULL),
	 (3,'$2a$10$PR7IGKWWlmKy0NwrYA/CBOD/ww.lmJ61vShw/8KtuTnBDSdbUPySy','ACTIVE','ngocnguyen',180);
INSERT INTO public.user_roles (user_id,role_id) VALUES
	 (1,3),
	 (2,2),
	 (3,1);
INSERT INTO public.store (id,"name",address_id,"owner") VALUES
	 (1,'Shop Cá Cơm',NULL,2);
INSERT INTO public.customer (id,birth_day,email,first_name,last_name,phone,address_id,user_id) VALUES
	 (1,NULL,'ngocnguyen@gmail.com','Ngọc','Nguyễn','0339737498',79,3);
INSERT INTO public.product (id,"name",price,quantity,product_type,store_id) VALUES
	 (2,'ÁO KHOÁC BOMBER NAM NỮ VẢI MERO CAO CẤP HỌA TIẾT THÊU 08 HUYỀN THOẠI SIÊU NGẦU HÓT TRIEND 2021',19.99,993,2,1),
	 (3,'Bộ Mặc Nhà Nam Mùa Hè Mát Mẻ Cao Cấp Thời Trang ZERO',9.99,299,3,1),
	 (4,'Đồ bộ mặc nhà - Homewear họa tiết mây xinh',12.99,200,4,1),
	 (5,'TÚI TOTE NỮ DẠO PHỐ DA PU THỜI DỄ THƯƠNG MDT89A',33.6,10,5,1),
	 (1,'Áo khoác jean nam nữ có nón hot nhất hiện nay thời trang cao cấp',23.99,994,2,1),
	 (6,'Đồng hồ điện tử thể thao nam nữ PAGINI phong cách Hàn Quốc – Đa chức năng báo thức – Hiển thị lịch ngày giờ thứ - WA000002',86.99,3,6,1);
INSERT INTO public.product_colors (product_id,colors_id) VALUES
	 (1,1),
	 (1,2),
	 (2,1),
	 (2,2),
	 (3,1),
	 (3,2),
	 (3,3),
	 (3,4),
	 (4,5),
	 (4,6);
INSERT INTO public.product_colors (product_id,colors_id) VALUES
	 (4,4),
	 (5,1),
	 (5,2),
	 (5,3),
	 (5,4),
	 (5,5),
	 (5,6),
	 (6,2),
	 (6,1),
	 (6,5);
INSERT INTO public.product_images (product_id,images_id) VALUES
	 (1,1),
	 (1,2),
	 (1,3),
	 (2,4),
	 (2,5),
	 (2,6),
	 (2,7),
	 (3,8),
	 (3,9),
	 (3,10);
INSERT INTO public.product_images (product_id,images_id) VALUES
	 (3,11),
	 (3,12),
	 (3,13),
	 (3,14),
	 (4,15),
	 (4,16),
	 (4,17),
	 (4,18),
	 (4,19),
	 (5,20);
INSERT INTO public.product_images (product_id,images_id) VALUES
	 (5,21),
	 (5,22),
	 (5,23),
	 (5,24),
	 (6,25),
	 (6,26),
	 (6,27),
	 (6,28),
	 (6,29),
	 (6,30);
INSERT INTO public.product_images (product_id,images_id) VALUES
	 (6,31),
	 (6,32),
	 (6,33),
	 (6,34);
INSERT INTO public.product_sizes (product_id,sizes_id) VALUES
	 (1,1),
	 (1,2),
	 (1,3),
	 (2,1),
	 (2,2),
	 (2,3),
	 (3,1),
	 (3,2),
	 (3,3),
	 (3,4);
INSERT INTO public.product_sizes (product_id,sizes_id) VALUES
	 (4,2),
	 (4,3);