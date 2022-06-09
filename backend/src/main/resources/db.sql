CREATE DATABASE eventm;

CREATE TABLE IF NOT EXISTS events(
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR,
    person VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS comments(
    id SERIAL PRIMARY KEY,
    description VARCHAR,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    event_id SERIAL,
    FOREIGN KEY (event_id)
      REFERENCES events (event_id)
);