DROP DATABASE IF EXISTS `at`
CREATE DATABASE `at`
USE `at`

DROP TABLE IF EXISTS `article`

# article 테이블 생성
CREATE TABLE `article` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    delDate DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    displayStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    title CHAR(200) NOT NULL,
    `body` LONGTEXT NOT NULL
);

# article 테이블에 테스트 데이터 삽입
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = "제목1",
BODY = "내용1",
displayStatus = 1;

SELECT * FROM article

