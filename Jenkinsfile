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

        def image;

        stage('Build docker image') {
            agent { dockerfile true }
            steps {
                image = docker.build('lamth2/aikidodanang-backend:jenkins')
            }
        }
        stage('Publish images') {
            agent { dockerfile true }
            steps {
                image.push()
            }
        }
    }
}