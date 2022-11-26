-- 테스트 : 필요시 더미데이터 생성

insert into users(username, password, role, created_at) values ('ssar', '123', 'USER', now());
insert into users(username, password, role, created_at) values ('cos', '123', 'SHOP', now());

insert into shop(category, address, closetime, image, information, opentime, per_hour, per_price, phone_number, shop_name, user_id, created_at)
values ('한식, 분식', '가게주소', '22', '이미지1', '소개', '10', '1', '10000', '01011113333', '가게', 2, now());

insert into customer(address, name, phone_number, user_id, created_at)
values ('주소', '커스터머', '01099966462', 1, now());

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
