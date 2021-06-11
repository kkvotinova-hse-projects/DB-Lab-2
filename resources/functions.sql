create function create_table()
	returns void language sql as $$
	-- table household
		create table if not exists household(
			id integer primary key not null generated always as identity,
			rooms integer not null,
			relevance timestamptz default current_timestamp not null
		);
	-- table students 
		create table if not exists students(
			name  text not null,
			floor integer primary key not null,
			room  integer not null
		);
	-- index
		create index if not exists name on "students" (name);

	-- trigger
		create or replace function update_time() returns trigger
		as $trigger$
			begin
				new.relevance = current_timestamp;
        		return new;
    		end;
		$trigger$ language plpgsql;

		drop trigger if exists trigger_update on household;

		create trigger trigger_update
 		   before update on household
 		   for each row
 		   when (old.rooms is distinct from new.rooms)
 		   execute procedure update_time();
$$;

select "create_table"();

create function get_household()
    returns table(
    	id integer,
    	rooms integer,
    	relevance date)
    language plpgsql as $$
    begin
    	return query (select * from household);
    end
$$;

create function get_students()
    returns table(
    	name text,
    	floor integer,
    	room integer)
    language plpgsql as $$
    begin
    	return query (select * from students);
    end
$$;

create or replace function add_to_household(in rooms integer, in relevance date)
    returns void language sql as $$
insert into "household"(rooms, relevance) values (rooms, relevance)
$$;

create or replace function add_to_students(in name text, in floor integer, in room integer)
    returns void language sql as $$
insert into "students"(name, floor, room) values (name, floor, room)
$$;

create function clear_household() 
	returns void language sql as $$ 
		truncate "household"
	$$;

create function clear_students()
	returns void language sql as $$ 
		truncate "students"
	$$;

create function clear_all()
	returns void language sql as $$
	begin 
		truncate "household";
		truncate "students"
	end
	$$;

create function find_household(in floor integer)
    returns table(
    	id integer,
    	rooms integer,
    	relevance date)
    language plpgsql as $$
    begin
    	return query (select * from household where household.id = floor);
    end
$$;

create function find_students(in name text)
    returns table(
    	name text,
    	floor integer,
    	room integer)
    language plpgsql as $$
    begin
    	return query (select * from students where students.name = name);
    end
$$;

create function delete_student_by_name(in name text)
	returns void language plpgsql as $$
		begin 
			delete from "students" where students.name = name;
		end;
	$$;