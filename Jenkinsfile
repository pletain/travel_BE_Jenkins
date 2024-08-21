pipeline {
    agent any
    environment {
        AWS_REGION = 'ap-northeast-2'  // AWS 리전 설정
        ECR_REPO_NAME = 'ktb-samsam-be-ecr'  // ECR 리포지토리 이름
        IMAGE_TAG = "latest"  // 태그, 필요에 따라 커밋 SHA 등으로 설정 가능
        AWS_ACCOUNT_ID = '992382409749'  // AWS 계정 ID
        ECR_REPO_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO_NAME}"  // ECR 리포지토리 URI

        // 새로운 환경 변수
        PRIVATE_INSTANCE_SSH_KEY_ID = 'instance-ssh-key-id'  // Jenkins에 등록된 SSH 키의 ID
        BASTION_HOST_SSH_KEY_ID = 'instance-ssh-key-id'  // Jenkins에 등록된 Bastion Host SSH 키의 ID
        PRIVATE_IP = '192.168.2.189'  // 프라이빗 인스턴스 IP 주소
        PUBLIC_IP = '3.36.118.180'  // 퍼블릭 인스턴스 (Bastion Host) IP 주소
        COMPOSE_FILE = 'docker-compose.yml'  // Docker Compose 파일 경로
    }
    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${ECR_REPO_NAME}:${IMAGE_TAG}")
                }
            }
        }
        stage('Login to AWS ECR') {
            steps {
                script {
                    withAWS(region: "${AWS_REGION}", credentials: 'aws-credentials-id') {
                        sh 'aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REPO_URI}'
                    }
                }
            }
        }
        stage('Push to ECR') {
            steps {
                script {
                    sh "docker tag ${ECR_REPO_NAME}:${IMAGE_TAG} ${ECR_REPO_URI}:${IMAGE_TAG}"
                    sh "docker push ${ECR_REPO_URI}:${IMAGE_TAG}"
                }
            }
        }
        stage('Deploy on Private Instance with Docker Compose') {
            steps {
                script {
                    sshagent(['${BASTION_HOST_SSH_KEY_ID}', '${PRIVATE_INSTANCE_SSH_KEY_ID}']) {
                        sh """
                        ssh -o ProxyCommand="ssh -W %h:%p ubuntu@${PUBLIC_IP}" ubuntu@${PRIVATE_IP} <<EOF
                        docker pull ${ECR_REPO_URI}:${IMAGE_TAG}
                        sed -i 's|image: .*|image: ${ECR_REPO_URI}:${IMAGE_TAG}|' ${COMPOSE_FILE}
                        docker-compose -f ${COMPOSE_FILE} up -d --force-recreate
                        EOF
                        """
                    }
                }
            }
        }
    }
}