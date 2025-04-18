// # Qasim Shahid
// SWE 645 - Assignment 3
// Jenkinsfile for CI/CD pipeline
// This Jenkinsfile automates the build, test, and deployment process for a Spring Boot application using Docker and Kubernetes.

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

        stage('Inject Secret File') {
            steps {
                withCredentials([file(credentialsId: 'app_secret_file', variable: 'SECRET_FILE')]) {
                    sh 'cp "$SECRET_FILE" surveyapi/src/main/resources/application-secret.properties'
                }
            }
        }

        stage('Build Maven Project') {
            steps {
                dir('surveyapi') {
                    sh 'mvn clean package spring-boot:repackage -DskipTests=true'
                }       
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
