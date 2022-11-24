-- 테스트 : 필요시 더미데이터 생성

insert into users(username, password, role, created_at)
values ('test', '123', 'USER', now());