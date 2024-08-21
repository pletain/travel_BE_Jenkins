# 실행 환경 설정
FROM openjdk:17-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드 단계에서 생성된 JAR 파일을 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 애플리케이션 실행
ENTRYPOINT ["java","-jar","/app.jar"]

# 컨테이너 포트 설정
EXPOSE 8090