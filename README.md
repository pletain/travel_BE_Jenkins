# travel_BE
지능형 여행 및 생활 어시스턴트 : 사용자 맞춤형 여행 정보 및 생활 정보를 제공하는 지능형 어시스턴트 개발

# Getting Started
지능형 여행 및 생활 어시스턴트 : 사용자 맞춤형 여행 정보 및 생활 정보를 제공하는 지능형 어시스턴트 개발

## 로컬 환경 구성
미정

### CI
미정

### Branch 전략
Git-flow 전략을 따름
- main : 제품으로 출시될 수 있는 브랜치
- develop : 다음 출시 버전을 개발하는 브랜치
- feature : 기능을 개발하는 브랜치
- release : 이번 출시 버전을 준비하는 브랜치
- hotfix : 출시 버전에서 발생한 버그를 수정 하는 브랜치

[참고](https://techblog.woowahan.com/2553/)

### 커밋 메시지 컨벤션
한글로 작성  
기본 원칙 : 1 Action 1 Commit(최대한 작게 쪼개 한 커밋에 하나의 기능만 커밋되도룍)

- feat : 새로운 기능 추가
- fix : 버그 수정
- docs : 문서 수정
- style : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
- design : css, html등 변경
- refactor : 코드 리펙토링
- test : 테스트 코드, 리펙토링 테스트 코드 추가
- chore : 빌드 업무 수정, 패키지 매니저 수정
- rename : 파일 혹은 폴더 명 변경만 진행된 경우
- remove : 파일 혹은 폴더 삭제 작업만 진행된 경우

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
