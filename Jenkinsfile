pipeline {
    agent any

    tools {
        maven 'Maven 3'
        jdk 'JDK 11'
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'your-credentials-id', url: 'https://github.com/your-user/your-repo.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false, jdk: 'JDK 11', results: [[path: 'allure-results']]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            junit 'target/surefire-reports/*.xml'
        }
    }
}