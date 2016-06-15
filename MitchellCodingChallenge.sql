drop table if exists Vehicle;

CREATE TABLE Vehicle
(
  id SERIAL PRIMARY KEY,
  year integer,
  make character varying(255) NOT NULL,
  model character varying(255) NOT NULL,
  CONSTRAINT check_year CHECK (year >= 1950 AND year <= 2050)
);

insert into vehicle(year, make, model) values (2012, 'Honda', 'Accord');
insert into vehicle(year, make, model) values (2010, 'Toyota', 'Camry');
insert into vehicle(year, make, model) values (2011, 'Honda', 'Civic');


