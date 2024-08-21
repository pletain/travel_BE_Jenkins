pipeline {
    agent any
    environment {
        AWS_REGION = 'ap-northeast-2'
        ECR_REPO_NAME = 'ktb-samsam-be-ecr'
        IMAGE_TAG = "latest"
        AWS_ACCOUNT_ID = '992382409749'
        ECR_REPO_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO_NAME}"

        // 새로운 환경 변수
        PRIVATE_INSTANCE_SSH_KEY_ID = 'instance-ssh-key-id'
        BASTION_HOST_SSH_KEY_ID = 'instance-ssh-key-id'
        PRIVATE_IP = '192.168.2.189'
        PUBLIC_IP = '3.36.118.180'
        COMPOSE_FILE = 'docker-compose.yml'
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
                    sh 'aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REPO_URI}'
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
                    sshagent([BASTION_HOST_SSH_KEY_ID, PRIVATE_INSTANCE_SSH_KEY_ID]) {
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