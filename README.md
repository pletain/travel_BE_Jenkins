# travel_BE
지능형 여행 및 생활 어시스턴트 : 사용자 맞춤형 여행 정보 및 생활 정보를 제공하는 지능형 어시스턴트 개발

# Getting Started
지능형 여행 및 생활 어시스턴트 : 사용자 맞춤형 여행 정보 및 생활 정보를 제공하는 지능형 어시스턴트 개발

## 로컬 환경 구성
미정

### CI
미정

### Branch 전략
정하는 중

### DB 접속정보
서버 DB 접속 정보

### Local DB
DB 정보

### openjdk 1.11 설치
미정


### 테스트 테이블 및 데이터

예시입니다.
```
CREATE TABLE `Sample` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` VARCHAR(100) NOT NULL COMMENT '이름' COLLATE 'utf8_unicode_ci',
  `status` VARCHAR(1) NULL COMMENT '상태(A,B,C,D)' COLLATE 'utf8_unicode_ci',
  `birthDate` VARCHAR(8) NULL COMMENT '생일' COLLATE 'utf8_unicode_ci',
  `useYn` VARCHAR(1) NULL COMMENT '사용여부YN' COLLATE 'utf8_unicode_ci',
  `createdDatetime` DATETIME NOT NULL COMMENT '최초생성일시',
  `createId` INT(11) NOT NULL COMMENT '최초생성자회원ID',
  `updatedDatetime` DATETIME NOT NULL COMMENT '최종수정일시',
  `updateId` INT(11) NOT NULL COMMENT '최종수정자회원ID',
  PRIMARY KEY (`id`)
)
COMMENT='샘플'
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
;

INSERT INTO `Sample` (`name`,`status`,`birthDate`,`useYn`,`createdDatetime`,`createId`,`updatedDatetime`,`updateId`) VALUES ('테스트1','A', '20110101', 'Y', NOW(), -1, NOW(), -1);
INSERT INTO `Sample` (`name`,`status`,`birthDate`,`useYn`,`createdDatetime`,`createId`,`updatedDatetime`,`updateId`) VALUES ('테스트2','B', '20110201', 'Y', NOW(), -1, NOW(), -1);
INSERT INTO `Sample` (`name`,`status`,`birthDate`,`useYn`,`createdDatetime`,`createId`,`updatedDatetime`,`updateId`) VALUES ('테스트3','C', '20110301', 'Y', NOW(), -1, NOW(), -1);
INSERT INTO `Sample` (`name`,`status`,`birthDate`,`useYn`,`createdDatetime`,`createId`,`updatedDatetime`,`updateId`) VALUES ('테스트4','D', '20140101', 'Y', NOW(), -1, NOW(), -1);
INSERT INTO `Sample` (`name`,`status`,`birthDate`,`useYn`,`createdDatetime`,`createId`,`updatedDatetime`,`updateId`) VALUES ('테스트5','A', '20140201', 'N', NOW(), -1, NOW(), -1);
INSERT INTO `Sample` (`name`,`status`,`birthDate`,`useYn`,`createdDatetime`,`createId`,`updatedDatetime`,`updateId`) VALUES ('테스트6','A', '20140301', 'N', NOW(), -1, NOW(), -1);
```

### aws config 파일 생성
미정

### aws credential 파일 생성
미정

### docker 세팅
미정

### aws config 파일 생성
미정

### aws credential 파일 생성
미정
