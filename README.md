
- 도메인 작성 완료
- 시큐리티 적용 완료
- 테스트 단위/통합 세팅 완료

# 테이블
* 주의사항 (메인 기능을 먼저 구현함)
- board 추후 생성
- trade 추후 생성


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
  customer_name varchar
  customer_age varchar
  created_at timestamp
}

table shop{
  shop_id int [pk, increment]
  user_id int
  shop_name varchar
  shop_phone varchar
  category varchar
  address varchar
  open_time timestamp
  close_time timestamp
  created_at timestamp
}

table marchandise{
  marchandise_id int [pk, increment]
  shop_id int
  marchandise_price int
  max_people int
  created_at timestamp
}

table menu{
  menu_id int [pk, increment]
  shop_id int
  menu_name varchar
  menu_price int
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
  marchandise_id int
  created_at timestamp
}

table recipt{
  recipt_id int [pk, increment]
  reservation_id int
  marchandise_price int
  reservation_time timestamp
  created_at timestamp
}

table recipt_menu{
  recipt_menu_id int [pk, increment]
  recipt_id int 
  recipt_menu_name varchar
  recipt_menu_price int
  recipt_menu_qty int
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
```