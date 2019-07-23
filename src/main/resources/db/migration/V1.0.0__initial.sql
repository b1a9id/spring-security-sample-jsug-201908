CREATE TABLE user (
  id          INTEGER auto_increment,
  name        VARCHAR(100) NOT NULL,
  age         INT,
  gender      VARCHAR(10) NOT NULL,
  role        VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO user (name, age, gender, role) VALUES ('内立　良介', 29, 'MAN', 'OWNER'), ('新垣　結衣', 31, 'WOMAN', 'MANAGER'), ('山崎　賢人', 24, 'MAN', 'STAFF');
