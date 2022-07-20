-- Revision: dbrevision-0.001

drop table "jms_keyspace1".patient;
drop table "jms_keyspace1"."SensorData";








create table "jms_keyspace1"."patient"
(
    patient_id     text primary key,
    date_of_Birth   date,
    gender          text,
    height          int,
    name            text,
    preconditions text,
    weight          int
);
--      with caching = {'class': 'com.amazonaws.cassandra.DefaultCaching'}
--         and compaction = {'class': 'com.amazonaws.cassandra.DefaultCompaction'}
--         and compression = {'class': 'com.amazonaws.cassandra.DefaultCompression'}
--         and dclocal_read_repair_chance = 0
--         and gc_grace_seconds = 7776000
--         and memtable_flush_period_in_ms = 3600000;

INSERT INTO jms_keyspace1."patient" (patient_id, date_of_birth, gender, height, name, preconditions, weight) VALUES
    ('1', '2015-02-18', 'M', 5.9, 'Tommy', 'good', 149);
create table "jms_keyspace1"."sensor_data"
(
    patient_id     text,
    sensor_data_id   text,
    time_stamp timestamp,
    sensor_image BLOB,
    PRIMARY KEY (patient_id, time_stamp)
);