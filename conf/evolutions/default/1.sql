# --- !Ups

CREATE TABLE rds_state (
  session_id BIGINT,
  counter INTEGER
);
# --- !Downs

DROP TABLE IF EXISTS rds_state;
