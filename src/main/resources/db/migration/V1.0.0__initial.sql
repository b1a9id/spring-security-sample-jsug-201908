CREATE TABLE user (
  id          INTEGER auto_increment,
  name        VARCHAR(30) NOT NULL,
  username    VARCHAR(30) NOT NULL,
  password    VARCHAR(100) NOT NULL,
  age         INT,
  gender      VARCHAR(10) NOT NULL,
  role        VARCHAR(10) NOT NULL,
  PRIMARY KEY (id)
);

-- password -> 12345678
INSERT INTO user (name, username, password, age, gender, role)
  VALUES ('内立　良介', 'ruchitate', '$2a$10$1ppG6VNBT0/vLRvxZoO/jeH23T9KI3Ln8Al0bibb0JvBB2ysLXQEa', 29, 'MAN', 'OWNER'),
         ('新垣　結衣', 'yaragaki', '$2a$10$1ppG6VNBT0/vLRvxZoO/jeH23T9KI3Ln8Al0bibb0JvBB2ysLXQEa', 31, 'WOMAN', 'MANAGER'),
         ('山崎　賢人', 'kyamazaki', '$2a$10$1ppG6VNBT0/vLRvxZoO/jeH23T9KI3Ln8Al0bibb0JvBB2ysLXQEa', 24, 'MAN', 'STAFF');
