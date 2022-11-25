
- 도메인 작성 완료
- 시큐리티 적용 완료
- 테스트 단위/통합 세팅 완료

# 테이블
* 주의사항 (메인 기능을 먼저 구현함)
- board 추후 생성
- trade 추후 생성
- merchandise 수정될 수 있음


```
table user{
  user_id int [pk, increment]
  username varchar
  password varchar
  role varchar
  created_at timestamp
}

table customer{
  customer_id int [pk, increment]
  user_id int
  name varchar
  address varchar
  phone varchar
  created_at timestamp
}

table shop{
  shop_id int [pk, increment]
  user_id int
  shop_name varchar
  category varchar
  address varchar
  information varchar
  telephone varchar
  image varchar
  open_time varchar
  close_time varchar
  per_hour int
  created_at timestamp
}

table menu{
  menu_id int [pk, increment]
  shop_id int
  menu_name varchar
  menu_price int
  image varchar
  is_recommanded boolean
  created_at timestamp
}


table feature{
  feature_id int [pk, increment]
  shop_id int
  feature_name varchar
  created_at timestamp
}


table subscribe{
  subscribe_id int [pk, increment]
  customer_id int
  shop_id int
  created_at timestamp
}


table reservation{
  reservation_id int [pk, increment]
  customer_id int
  table_id int
  reservation_date timestamp
  reservation_time timestamp
  created_at timestamp
}


table review{
  review_id int [pk, increment]
  customer_id int
  shop_id int
  review_score int
  content varchar
  image varchar
  created_at timestamp
}

table board{
  board_id int [pk, increment]
  title varchar
  content varchar
  reservation_id int
  customer_id int
  created_at timestamp
}

table trade{
  trade_id int [pk, increment]
  reservation_id int
  buyer_id int
  seller_id int
  price int
  created_at timestamp
}

table table{
  table_id int [pk, increment]
  shop_id int
  max_people int    
  created_at timestamp
}
```