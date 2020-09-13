-- CREATE TABLES
create extension if not exists citext;
create table users (id bigserial not null, created_at timestamp DEFAULT current_timestamp not null, updated_at timestamp DEFAULT current_timestamp not null, version BIGINT not null default (1) not null, login varchar(255) not null, email citext not null,password varchar(255) not null, primary key (id));
create table products(id bigserial not null, created_at timestamp DEFAULT current_timestamp not null, updated_at timestamp DEFAULT current_timestamp not null, version BIGINT not null default (1) not null, title citext not null, price money not null, description varchar(255), url varchar(255) not null, category_id int8 not null, shop_id int8 not null, subcategory_id int8 not null, primary key (id));
create table subcategories(id bigserial not null, created_at timestamp DEFAULT current_timestamp not null, updated_at timestamp DEFAULT current_timestamp not null, version BIGINT not null default (1) not null,title citext not null, description varchar(255) not null, category_id int8 not null, primary key (id));
create table categories(id bigserial not null, created_at timestamp DEFAULT current_timestamp not null, updated_at timestamp DEFAULT current_timestamp not null, version BIGINT not null default (1) not null,title citext not null, description varchar(255) not null, primary key (id));
create table shops(id bigserial not null, created_at timestamp DEFAULT current_timestamp not null, updated_at timestamp DEFAULT current_timestamp not null, version BIGINT not null default (1) not null,title citext not null, url varchar(255) not null, primary key (id));
create table options(id bigserial not null, created_at timestamp DEFAULT current_timestamp not null, updated_at timestamp DEFAULT current_timestamp not null, version BIGINT not null default (1) not null, value varchar(255) not null, criteria_id int8 not null, primary key (id));
create table criteria( id bigserial not null, created_at timestamp DEFAULT current_timestamp not null, updated_at timestamp DEFAULT current_timestamp not null, version BIGINT not null default (1) not null, title citext not null, is_numeric boolean not null, primary key (id));
create table price_track(id bigserial not null, created_at timestamp DEFAULT current_timestamp not null, updated_at timestamp DEFAULT current_timestamp not null, version BIGINT not null default (1) not null, price money not null, product_id int8 not null, primary key (id));
create table recommendations(user_id int8 not null, product_id int8 not null);
create table users_products(user_id int8 not null, product_id int8 not null);
create table users_options(user_id int8 not null, option_id int8 not null);
create table products_options(product_id int8 not null, option_id int8 not null);

-- ADD CONSTRAINTS
alter table users_products add constraint fk_users_products_user_id foreign key (user_id) references users;
alter table users_products add constraint fk_users_products_product_id foreign key (product_id) references products;
alter table recommendations add constraint fk_recommendations_user_id foreign key (user_id) references users;
alter table recommendations add constraint fk_recommendations_product_id foreign key (product_id) references products;
alter table subcategories add constraint fk_subcategories_category_id foreign key (category_id) references categories;
alter table products add constraint fk_products_category_id foreign key (category_id) references categories;
alter table products add constraint fk_products_subcategory_id foreign key (subcategory_id) references subcategories;
alter table products add constraint fk_products_shop_id foreign key (shop_id) references shops;
alter table products_options add constraint fk_products_options_option_id foreign key (option_id) references options;
alter table products_options add constraint fk_products_options_product_id foreign key (product_id) references products;
alter table users_options add constraint fk_users_options_user_id foreign key (user_id) references users;
alter table users_options add constraint fk_users_options_option_id foreign key (option_id) references options;
alter table price_track add constraint fk_price_track_product_id foreign key (product_id) references products;
alter table options add constraint fk_options_criteria_id foreign key (criteria_id) references criteria;

-- UNIQUE
alter table users add constraint uk_users_login unique (login);
alter table users add constraint uk_users_email unique (email);
alter table products add constraint uk_products_title unique (title);
alter table subcategories add constraint uk_subcategories_title unique (title);
alter table categories add constraint uk_categories_title unique (title);
alter table shops add constraint uk_shops_title unique (title);
alter table criteria add constraint uk_criteria_title unique (title);