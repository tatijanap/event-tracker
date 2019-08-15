create table "clubs"
(
    "id" int not null ,
    "name" varchar(128) not null ,
    "president" varchar(128) not null ,
    "short_description" text ,
    "created_at" timestamp not null default NOW(),
    "updated_at" timestamp not null default NOW(),
    constraint "pk_clubs" primary key ("id")
);

create table "events"
(
    "id" int not null ,
    "name" varchar(256) not null ,
    "date" date ,
    "attendance" int not null ,
    "club_id" int not null ,
    "created_at" timestamp not null default NOW(),
    "updated_at" timestamp not null default NOW(),
    constraint "pk_events" primary key ("id")
);

/*******************************************************************************
   Create Foreign Keys
********************************************************************************/
alter table "events" add constraint "fk_events_clubs_id"
foreign key ("club_id") references "clubs" ("id") on delete no action on update no action;

create index "ifk_events_clubs_id" on "events" ("club_id");