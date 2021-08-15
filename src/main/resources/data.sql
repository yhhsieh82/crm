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



------------------------------

DROP TABLE IF EXISTS crm_user;

CREATE TABLE crm_user (
id INT AUTO_INCREMENT  PRIMARY KEY,
email VARCHAR(250) NOT NULL,
username VARCHAR(250) NOT NULL,
password VARCHAR(250),
role VARCHAR(250)
);


{
  "email": "adminA@test.com",
  "password": "password",
  "role": "ADMIN",
  "username": "ADMIN_A"
}