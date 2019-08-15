CREATE SEQUENCE tracker_club_id_seq
    INCREMENT BY 1 NO MINVALUE NO MAXVALUE START WITH 1;

ALTER SEQUENCE tracker_club_id_seq OWNED BY clubs.id;
------------------------------------------------------------------------------------------------------------------------
CREATE SEQUENCE tracker_event_id_seq
    INCREMENT BY 1 NO MINVALUE NO MAXVALUE START WITH 1;

ALTER SEQUENCE tracker_event_id_seq OWNED BY events.id;
