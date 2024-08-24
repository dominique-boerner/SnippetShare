CREATE DATABASE IF NOT EXISTS snippetshare;

USE snippetshare;

CREATE TABLE IF NOT EXISTS snippetshare.user
(
    id       VARCHAR(36) PRIMARY KEY,
    username VARCHAR(50),
    email    VARCHAR(50),
    password VARCHAR(255)
);