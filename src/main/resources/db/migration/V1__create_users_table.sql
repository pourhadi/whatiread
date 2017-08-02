CREATE TABLE users (
  `id`      CHAR(36)    NOT NULL,
  auth0Id    VARCHAR(64) NOT NULL,
  code      VARCHAR(64) NOT NULL,
  nickname  VARCHAR(255) NOT NULL,
  email     VARCHAR(255) NOT NULL,
  createdAt TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE articles (
  `id`      CHAR(36)     NOT NULL,
  userId    VARCHAR(64)  NOT NULL,
  url       TEXT         NOT NULL,
  title     VARCHAR(255) NOT NULL,
  host      VARCHAR(255) NOT NULL,
  createdAt TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);