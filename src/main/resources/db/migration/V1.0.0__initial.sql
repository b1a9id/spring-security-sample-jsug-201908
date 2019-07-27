CREATE TABLE user (
  id          INTEGER auto_increment,
  name        VARCHAR(30) NOT NULL,
  password    VARCHAR(100) NOT NULL,
  age         INT,
  gender      VARCHAR(10) NOT NULL,
  role        VARCHAR(10) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO user (name, password, age, gender, role)
  VALUES ('内立　良介', '$2a$10$1ppG6VNBT0/vLRvxZoO/jeH23T9KI3Ln8Al0bibb0JvBB2ysLXQEa', 29, 'MAN', 'OWNER'),
         ('新垣　結衣', '$2a$10$1ppG6VNBT0/vLRvxZoO/jeH23T9KI3Ln8Al0bibb0JvBB2ysLXQEa', 31, 'WOMAN', 'MANAGER'),
         ('山崎　賢人', '$2a$10$1ppG6VNBT0/vLRvxZoO/jeH23T9KI3Ln8Al0bibb0JvBB2ysLXQEa', 24, 'MAN', 'STAFF');
