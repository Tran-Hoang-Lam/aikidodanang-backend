pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build source') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Build docker image') {
            steps {
                sh 'docker build -t lamth2/aikidodanang-backend:jenkins .'
            }
        }
        stage('Publish images') {
            steps {
                sh 'docker push lamth2/aikidodanang-backend:jenkins'
            }
        }
    }
}