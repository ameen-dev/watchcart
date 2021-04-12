DROP TABLE IF EXISTS watch;

CREATE TABLE watch (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  watchid VARCHAR(250) NOT NULL,
  watchname VARCHAR(250) NOT NULL,
  unitprice INTEGER(10) NOT NULL,
  discountquantity INTEGER(10) DEFAULT 0,
  discountprice INTEGER(10) DEFAULT 0
);

INSERT INTO watch (watchid, watchname, unitprice, discountquantity, discountprice) VALUES
  ('001', 'Rolex', 100, 3, 200),
  ('002', 'Michael Kors', 80, 2, 120),
  ('003', 'Swatch', 50, 0, 0),
  ('004', 'Casio', 30, 0, 0);