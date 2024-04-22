
CREATE TABLE resource (
	id serial4 NOT NULL,
	created_at timestamp(6) NULL,
	created_by varchar(255) NULL,
	dml_flag int4 NULL,
	updated_at timestamp(6) NULL,
	updated_by varchar(255) NULL,
	"uuid" varchar(255) NULL,
	"version" int4 NULL,
	organization_id int4 NULL,
	description varchar(255) NULL,
	"module" varchar(255) NULL,
	"name" varchar(255) NULL,
	CONSTRAINT resource_pkey PRIMARY KEY (id)
);


CREATE TABLE "role" (
	id serial4 NOT NULL,
	created_at timestamp(6) NULL,
	created_by varchar(255) NULL,
	dml_flag int4 NULL,
	updated_at timestamp(6) NULL,
	updated_by varchar(255) NULL,
	"uuid" varchar(255) NULL,
	"version" int4 NULL,
	organization_id int4 NULL,
	description varchar(255) NULL,
	"name" varchar(255) NULL,
	resource_category varchar(255) NULL,
	CONSTRAINT role_pkey PRIMARY KEY (id),
	CONSTRAINT role_resource_category_check CHECK (((resource_category)::text = ANY ((ARRAY['SUPER_ADMIN'::character varying, 'TENANT'::character varying, 'LEANDER'::character varying, 'CUSTOMER'::character varying, 'All'::character varying])::text[])))
);


CREATE TABLE users (
	id serial4 NOT NULL,
	created_at timestamp(6) NULL,
	created_by varchar(255) NULL,
	dml_flag int4 NULL,
	updated_at timestamp(6) NULL,
	updated_by varchar(255) NULL,
	"uuid" varchar(255) NULL,
	"version" int4 NULL,
	organization_id int4 NULL,
	is_active bool NULL,
	attempt int4 NOT NULL,
	email varchar(255) NULL,
	is_first_time bool NULL,
	is_locked bool NULL,
	"password" varchar(255) NULL,
	phone_number varchar(255) NULL,
	user_type varchar(255) NULL,
	username varchar(255) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT users_user_type_check CHECK (((user_type)::text = ANY ((ARRAY['SUPER_ADMIN'::character varying, 'TENANT'::character varying, 'LEANDER'::character varying, 'CUSTOMER'::character varying])::text[])))
);


CREATE TABLE resource_category (
	id serial4 NOT NULL,
	created_at timestamp(6) NULL,
	created_by varchar(255) NULL,
	dml_flag int4 NULL,
	updated_at timestamp(6) NULL,
	updated_by varchar(255) NULL,
	"uuid" varchar(255) NULL,
	"version" int4 NULL,
	organization_id int4 NULL,
	resource_category varchar(255) NULL,
	resource_id int4 NULL,
	CONSTRAINT resource_category_pkey PRIMARY KEY (id),
	CONSTRAINT resource_category_resource_category_check CHECK (((resource_category)::text = ANY ((ARRAY['SUPER_ADMIN'::character varying, 'TENANT'::character varying, 'LEANDER'::character varying, 'CUSTOMER'::character varying, 'All'::character varying])::text[]))),
	CONSTRAINT fkih44rnhjtf1dtys6bikr0rnr FOREIGN KEY (resource_id) REFERENCES resource(id)
);


CREATE TABLE role_category (
	id serial4 NOT NULL,
	created_at timestamp(6) NULL,
	created_by varchar(255) NULL,
	dml_flag int4 NULL,
	updated_at timestamp(6) NULL,
	updated_by varchar(255) NULL,
	"uuid" varchar(255) NULL,
	"version" int4 NULL,
	organization_id int4 NULL,
	resource_category varchar(255) NULL,
	role_id int4 NULL,
	CONSTRAINT role_category_pkey PRIMARY KEY (id),
	CONSTRAINT role_category_resource_category_check CHECK (((resource_category)::text = ANY ((ARRAY['SUPER_ADMIN'::character varying, 'TENANT'::character varying, 'LEANDER'::character varying, 'CUSTOMER'::character varying, 'All'::character varying])::text[]))),
	CONSTRAINT fki693b2ud5guafbw3q8olks1qi FOREIGN KEY (role_id) REFERENCES "role"(id)
);

CREATE TABLE role_resource (
	role_id int4 NOT NULL,
	resource_id int4 NOT NULL,
	CONSTRAINT role_resource_pkey PRIMARY KEY (role_id, resource_id),
	CONSTRAINT fkh8lunkrwoyio367ec8y12bis1 FOREIGN KEY (role_id) REFERENCES "role"(id),
	CONSTRAINT fkr2orp5em3dob6f299ra9oyexr FOREIGN KEY (resource_id) REFERENCES resource(id)
);


CREATE TABLE user_role (
	user_id int4 NOT NULL,
	role_id int4 NOT NULL,
	CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id),
	CONSTRAINT fka68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES "role"(id),
	CONSTRAINT fkj345gk1bovqvfame88rcx7yyx FOREIGN KEY (user_id) REFERENCES users(id)
);