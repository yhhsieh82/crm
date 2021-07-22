DROP TABLE IF EXISTS company;

CREATE TABLE company (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250),
  industry VARCHAR(250)
);

INSERT INTO company (name, description, industry) VALUES
  ('Spotify', 'streaming tech company', 'Entertainment'),
  ('Amazon', 'platform', 'Paas'),
  ('Apple', 'tech company', 'Tech');