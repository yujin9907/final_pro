-- 테스트 : 필요시 더미데이터 생성

insert into users(username, password, role, created_at) values ('ssar', '$2a$10$sPyqasyfpVYeHDVrtRcXKelXutSQobLuWzf32BXmQvnLTMbddkIwy', 'USER', now());
insert into users(username, password, role, created_at) values ('cos', '$2a$10$sPyqasyfpVYeHDVrtRcXKelXutSQobLuWzf32BXmQvnLTMbddkIwy', 'SHOP', now());

insert into image_file(origin_filename, store_filename, created_at) values('testimage.jpg', 'randomname.jpg', now());

insert into shop(category, address, close_time, information, open_time, per_hour, per_price, phone_number, shop_name, user_id, created_at)
values ('한식', '가게주소', '22', '소개', '10', '1', '10000', '01011113333', '가게', 2, now());

insert into customer(address, name, phone_number, user_id, created_at)
values ('주소', '커스터머', '01099966462', 1, now());

insert into review(score, content, shop_id, customer_id, created_at) values (5, 'test',1,1,now());

insert into shop_table(max_people, shop_id, created_at)
values (4, 1, now());
insert into shop_table(max_people, shop_id, created_at)
values (4, 1, now());
insert into shop_table(max_people, shop_id, created_at)
values (2, 1, now());

insert into reservation(reservation_date, reservation_time, customer_id, shop_table_id, created_at)
values ('20221126', '16', 1, 1, now());
insert into reservation(reservation_date, reservation_time, customer_id, shop_table_id, created_at)
values ('20221126', '18', 1, 1, now());
insert into reservation(reservation_date, reservation_time, customer_id, shop_table_id, created_at)
values ('20221127', '12', 1, 2, now());

insert into subscribe(customer_id, shop_id, created_at)
values (1, 1, now());
