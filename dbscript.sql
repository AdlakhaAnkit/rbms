CREATE TABLE public."roomstype"
(
    "id" bigint NOT NULL,
    "room_type" character varying(20) NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE public."roomstype"
    OWNER to postgres;

INSERT INTO public."roomstype" (
"id", "room_type") VALUES (
'1'::bigint, 'SINGLE'::character varying(20))
 returning "id";

 INSERT INTO public."roomstype" (
"id", "room_type") VALUES (
'2'::bigint, 'DOUBLE'::character varying(20))
 returning "id";

 INSERT INTO public."roomstype" (
"id", "room_type") VALUES (
'3'::bigint, 'SUITE'::character varying(20))
 returning "id";
 
 
 CREATE TABLE public."rooms"
(
    "id" bigint NOT NULL,
    "room_number" bigint NOT NULL,
    "room_type_id" bigint NOT NULL,
    PRIMARY KEY ("id"),
    CONSTRAINT "room_type_id" FOREIGN KEY ("room_type_id")
        REFERENCES public."roomstype" ("id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE public."rooms"
    OWNER to postgres;
	
INSERT INTO public."rooms" (
"id", "room_number","room_type_id") VALUES (
'1'::bigint, '101'::bigint,'1'::bigint)
 returning "id";
 
 INSERT INTO public."rooms" (
"id", "room_number","room_type_id") VALUES (
'2'::bigint, '102'::bigint,'1'::bigint)
 returning "id";
 
 INSERT INTO public."rooms" (
"id", "room_number","room_type_id") VALUES (
'3'::bigint, '103'::bigint,'1'::bigint)
 returning "id";
 
 INSERT INTO public."rooms" (
"id", "room_number","room_type_id") VALUES (
'4'::bigint, '104'::bigint,'1'::bigint)
 returning "id";
 
 INSERT INTO public."rooms" (
"id", "room_number","room_type_id") VALUES (
'5'::bigint, '105'::bigint,'1'::bigint)
 returning "id";
 
 INSERT INTO public."rooms" (
"id", "room_number","room_type_id") VALUES (
'6'::bigint, '201'::bigint,'2'::bigint)
 returning "id";
 
 INSERT INTO public."rooms" (
"id", "room_number","room_type_id") VALUES (
'7'::bigint, '202'::bigint,'2'::bigint)
 returning "id";
 
 INSERT INTO public."rooms" (
"id", "room_number","room_type_id") VALUES (
'8'::bigint, '203'::bigint,'3'::bigint)
 returning "id";
 
 INSERT INTO public."rooms" (
"id", "room_number","room_type_id") VALUES (
'9'::bigint, '301'::bigint,'3'::bigint)
 returning "id";
 
 INSERT INTO public."rooms" (
"id", "room_number","room_type_id") VALUES (
'10'::bigint, '302'::bigint,'3'::bigint)
 returning "id";
 
CREATE TABLE public.roomsbooking
(
    id bigint NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    entity_status smallint DEFAULT 1,
    created_by bigint,
    created_date time with time zone,
    modified_by bigint,
    modified_date time with time zone,
    CONSTRAINT roomsbooking_pkey PRIMARY KEY (id)
);

ALTER TABLE public."roomsbooking"
    OWNER to postgres;
	
CREATE TABLE public."booked_rooms_mapping"
(
    "booking_date" date NOT NULL,
    "booking_id" bigint NOT NULL,
    "room_id" bigint NOT NULL,
    CONSTRAINT "booking_id" FOREIGN KEY ("booking_id")
        REFERENCES public."roomsbooking" ("id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "room_id" FOREIGN KEY ("room_id")
        REFERENCES public."rooms" ("id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE public."booked_rooms_mapping"
    OWNER to postgres;
	
	
