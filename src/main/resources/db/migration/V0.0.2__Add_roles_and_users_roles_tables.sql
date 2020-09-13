create table roles (id bigserial not null, created_at timestamp DEFAULT current_timestamp not null, updated_at timestamp DEFAULT current_timestamp not null, version BIGINT not null default (1) not null, title citext not null, primary key (id));
create table users_roles(user_id int8 not null, role_id int8 not null);

alter table users_roles add constraint fk_users_roles_role_id foreign key (role_id) references roles;
alter table users_roles add constraint fk_users_roles_user_id foreign key (user_id) references users;

alter table roles add constraint uk_roles_title unique (title);

insert into roles(id, created_at, updated_at, version, title) values (1,current_timestamp,current_timestamp,1,'ROLE_USER');
insert into roles(id, created_at, updated_at, version, title) values (2,current_timestamp,current_timestamp,1,'ROLE_ADMIN');
