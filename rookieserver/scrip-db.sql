INSERT INTO public.address (id, city, district, street, ward)
VALUES (
		79,
		'Lâm Đồng',
		'Đà Lạt',
		'Tổ Lầm Văn Thạch',
		'P11'
	)
INSERT INTO public.color (id, color_name, hex_code)
VALUES (1, 'Trắng', '#FFFFFF'),
	(2, 'Đen', '#000000');
INSERT INTO public.users (id, "password", status, username, avatar)
VALUES (
		1,
		'$2a$10$fyXsshPGtPb1zJVeUOb6BuLZcjFRzC6qkKNUKejc/DxXV9ZlXWvay',
		'ACTIVE',
		'admin',
		NULL
	),
	(
		2,
		'$2a$10$lohqyU9yq2aRY4GNTXOUK.VcAMp.tkvXzvR0y4UfMP/r3o0CDL11m',
		'ACTIVE',
		'cadonglanh',
		NULL
	),
	(
		3,
		'$2a$10$PR7IGKWWlmKy0NwrYA/CBOD/ww.lmJ61vShw/8KtuTnBDSdbUPySy',
		'ACTIVE',
		'ngocnguyen',
		NULL
	);
INSERT INTO public.customer (
		id,
		birth_day,
		email,
		first_name,
		last_name,
		phone,
		address_id,
		user_id
	)
VALUES (
		1,
		'1999-08-17 00:00:00',
		'ngocnguyen@gmail.com',
		'Ngọc',
		'Nguyễn',
		'0339737498',
		89,
		3
	);
INSERT INTO public.image (id, alt, url)
VALUES (
		1,
		NULL,
		'/images/4bd7621459eeb6b7e9c8d8717df33b88.jpg'
	),
	(
		2,
		NULL,
		'/images/6e4f13e42b9559e8af707ecffd96bcff.jpg'
	),
	(
		3,
		NULL,
		'/images/8f0386253fb5a1ed9471358967d560d2.jpg'
	),
	(
		5,
		NULL,
		'/images/89267e75053070ee7cc5a931c5be92f7.jpg'
	),
	(
		6,
		NULL,
		'/images/1606fa50b51c4168cf4c70927f40a34f.jpg'
	),
	(
		7,
		NULL,
		'/images/de25e08901040f77684e59036d21df41.jpg'
	),
	(
		4,
		NULL,
		'/images/d3f58acde60697f4448182e5e0bd6812.jpg'
	);
INSERT INTO public.product_type (id, product_type)
VALUES (1, 'Áo Hoodie'),
	(2, 'ÁO KHOÁC');
INSERT INTO public.roles (id, "name")
VALUES (3, 'ROLE_ADMIN'),
	(2, 'ROLE_STORE'),
	(1, 'ROLE_CUSTOMER');
INSERT INTO public.sizes (id, size_name)
VALUES (2, 'M'),
	(1, 'L'),
	(3, 'XL');
INSERT INTO public.store (id, "name", address_id, "owner")
VALUES (1, 'Shop Cá Cơm', NULL, 3);
INSERT INTO public.user_roles (user_id, role_id)
VALUES (1, 3),
	(2, 2),
	(3, 1);
INSERT INTO public.product (
		id,
		"name",
		price,
		quantity,
		product_type,
		store_id
	)
VALUES (
		1,
		'Áo Hoodie Nỉ Bông Unisex Streetwear Lục Lăng ( unisex nam nữ đều mặc được)',
		23.99,
		999,
		1,
		1
	),
	(
		2,
		'ÁO KHOÁC BOMBER NAM NỮ VẢI MERO CAO CẤP HỌA TIẾT THÊU 08 HUYỀN THOẠI SIÊU NGẦU HÓT TRIEND 2021',
		19.99,
		999,
		2,
		1
	);
INSERT INTO public.product_colors (product_id, colors_id)
VALUES (1, 1),
	(1, 2),
	(2, 1),
	(2, 2);
INSERT INTO public.product_images (product_id, images_id)
VALUES (1, 1),
	(1, 2),
	(1, 3),
	(2, 4),
	(2, 5),
	(2, 6),
	(2, 7);
INSERT INTO public.product_sizes (product_id, sizes_id)
VALUES (1, 1),
	(1, 2),
	(1, 3),
	(2, 1),
	(2, 2),
	(2, 3);