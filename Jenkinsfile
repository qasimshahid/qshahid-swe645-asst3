pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'qshahid/surveyapi'
        REGISTRY_CREDENTIAL = 'dockerhub_credentials'
        GIT_REPO_URL = 'https://github.com/qasimshahid/qshahid-swe645-asst3.git'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: "${env.GIT_REPO_URL}"
            }
        }
        stage('Build Maven Project') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${DOCKER_IMAGE}:${env.BUILD_NUMBER}")
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', REGISTRY_CREDENTIAL) {
                        dockerImage.push()
                        dockerImage.push('latest')
                    }
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withKubeConfig([credentialsId: 'kubeconfig_credentials']) {
                        sh 'kubectl apply -f deployment.yaml'
                        sh 'kubectl apply -f service.yaml'
                        sh 'kubectl rollout restart deployment/surveyapi-deployment'
                    }
                }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}