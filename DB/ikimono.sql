
-- users テーブル --
create table users (
    user_id         VARCHAR(20)     primary key,
    user_name       VARCHAR(40)     not null,
    mail_address    VARCHAR(256)    not null unique,
    password        VARCHAR(20)     not null
);

insert into users
    values ('inu_dog223','いぬのこまちゃん','exampleexample@example.ne.jp','inu3neko4__');

insert into users
    values ('nekoneko222','いくら','example2example2@example.ne.jp','shiro_nya20');

insert into users
    values ('hogehoge_3_3','ほげおじ','example3example3@example.ne.jp','hghgojiji4');


-- post_item テーブル --
create table post_item (
    post_id         INTEGER     generated always as identity primary key,
    user_id         VARCHAR(20)     references users(user_id),
    post_date       TIMESTAMP,
    caption         TEXT        
);

insert into post_item (user_id, post_date, caption)
values ('inu_dog223', '2024-12-13 13:45:00', 'お散歩中です');

insert into post_item (user_id, post_date, caption)
values ('inu_dog223', '2024-12-13 14:10:00', 'おうちに帰ってきました。満足そうです！');

insert into post_item (user_id, post_date, caption)
values ('nekoneko222', '2024-12-13 16:50:00', '犬さんかわよ～');

insert into post_item (user_id, post_date, caption)
values ('hogehoge_3_3', '2024-12-13 16:55:00', '犬って健康にいいですね');


-- stamp_master --
create table stamp_master (
    post_id         INTEGER,
    stamp_id         INTEGER,
    stamp_num         INTEGER   not null,
    
    foreign key (post_id)   references post_item(post_id),
    foreign key (stamp_id)  references stamp_source(stamp_id),
    primary key (stamp_id, post_id)
);

insert into stamp_master
values (1,1,2)


-- stamp_source --
create table stamp_source (
    stamp_id         INTEGER     generated always as identity primary key,
    stamp_name       VARCHAR(30) default 'no-name',    
    stamp_code       VARCHAR(20)   not null  default 'none'
);


insert into stamp_source (stamp_name, stamp_code)
values ('dog face','&#x1f436;')  --犬

insert into stamp_source (stamp_name, stamp_code)
values ('cat face','&#x1f431;') --猫

insert into stamp_source (stamp_name, stamp_code)
values ('rabbit face','&#x1f430;') --ウサギ


-- picture -- 
create table picture (
    picture_id       INTEGER     generated always as identity primary key,
    user_id         VARCHAR(20) not null,  
    post_id         INTEGER not null,
    picture_url     VARCHAR(255) not null unique,

    foreign key (user_id)  references users(user_id),
    foreign key (post_id)   references post_item(post_id)
);

insert into picture (user_id, post_id, picture_url)
values ('inu_dog223','1','/ikimonoSNS/WEB-INF/img/inu001.jpg')


-- category_master --
create table category_master (
    category_id     INTEGER,
    post_id         INTEGER,
    
    foreign key (category_id)  references category_source(category_id),
    foreign key (post_id)   references post_item(post_id),
    primary key (category_id, post_id)
);

insert into category_master
values (2,1),(2,2),(1,3),(1,4)


-- category_source --
create table category_source (
    category_id      INTEGER     generated always as identity primary key,
    category_name    VARCHAR(30) not null unique
);

insert into category_source (category_name)
values
    ('その他'),
    ('いぬ'),
    ('ねこ'),
    ('とり'),
    ('ハムスター'),
    ('りす'),
    ('はちゅうるい'),
    ('さかな');






--▼▼▼▼▼ 結合テスト ▼▼▼▼▼--
-- postとusersの結合テスト --
select post_id, user_name, caption, post_date, picture_url
from post_item AS Pi
join users AS U
on Pi.user_id = U.user_id


-- postとstamp、stamp_sourceの結合テスト --
select Pi.post_id, caption, post_date, Ss.stamp_id, stamp_name, stamp_code, stamp_num
from post_item AS Pi
join stamp_master AS Sm
on Pi.post_id = Sm.post_id
join stamp_source AS Ss
on Sm.stamp_id = Ss.stamp_id

-- post、users、photoの結合テスト --
select user_name, U.user_id, Pi.post_id, caption, post_date, picture_id, picture_url
from post_item AS Pi
join users AS U
on Pi.user_id = U.user_id
join picture AS Pc
on Pi.post_id = Pc.post_id

-- postとcategory_master、category_sourceの結合テスト --
select Pi.post_id, caption, post_date, Cs.category_id, category_name
from post_item AS Pi
join category_master AS Cm
on Pi.post_id = Cm.post_id
join category_source AS Cs
on Cm.category_id = Cs.category_id





-- FindAllPostWithDetails ビューの作成（完全版）
create view FindAllPostWithDetails as
select 
    pi.post_id,
    pi.user_id,
    u.user_name,
    pi.caption,
    pi.post_date,
    p.picture_url,
    group_concat(distinct cs.category_name order by cs.category_id) as categories,
    sum(coalesce(sm.stamp_num, 0)) as total_stamps
from 
    post_item pi
join 
    users u
on 
    pi.user_id = u.user_id
left join 
    picture p
on 
    pi.post_id = p.post_id
left join 
    category_master cm
on 
    pi.post_id = cm.post_id
left join 
    category_source cs
on 
    cm.category_id = cs.category_id
left join 
    stamp_master sm
on 
    pi.post_id = sm.post_id
group by 
    pi.post_id, pi.user_id, u.user_name, pi.caption, pi.post_date, p.picture_url
order by 
    pi.post_date desc;




POST_ID  	USER_ID  	USER_NAME  	CAPTION  	POST_DATE  	PICTURE_URL  	CATEGORIES  	TOTAL_STAMPS  
4	hogehoge_3_3	ほげおじ	犬って健康にいいですね	2024-12-13 16:55:00	null	その他	0
3	nekoneko222	いくら	犬さんかわよ～	2024-12-13 16:50:00	null	その他	0
2	inu_dog223	いぬのこまちゃん	おうちに帰ってきました。満足そうです！	2024-12-13 14:10:00	null	いぬ	1
1	inu_dog223	いぬのこまちゃん	お散歩中です	2024-12-13 13:45:00	/ikimonoSNS/WEB-INF/img/inu001.jpg	いぬ	6
(4 行, 5 ms)