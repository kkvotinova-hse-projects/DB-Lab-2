drop table if exists students;
drop table if exists household;
drop database if exists hostel;

create database hostel;
grant all privileges on database hostel to myuser;

create table if not exists household
(
    id integer primary key not null generated always as identity,
    rooms     integer                               not null,
    relevance timestamptz default current_timestamp not null
);

insert into household (rooms, relevance)
values  (100, '2021-06-10'),
        (100, '2021-06-10'),
        (100, '2021-06-10'),
        (100, '2021-06-10'),
        (100, '2021-06-10');

alter table household
    owner to myuser;

create table if not exists students
(
    name  text                not null,
    floor integer primary key not null,
    room  integer             not null
);

insert into students (name, floor, room)
values  ('Пупкин К. С.', 2, 225),
        ('Лупкин Л. М.', 3, 326),
        ('Васильев С. Р.', 4, 404);

alter table students
    owner to myuser;

-- index
create index if not exists name on "students" (name);

-- trigger
create or replace function update_time() returns trigger
as $$
    begin
        new.relevance = current_timestamp;
        return new;
    end;
$$ language plpgsql;

drop trigger if exists trigger_update on household;

create trigger trigger_update
    before update on household
    for each row
    when (old.rooms is distinct from new.rooms)
    execute procedure update_time();