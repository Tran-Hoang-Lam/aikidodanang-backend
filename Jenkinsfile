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

        stage('Build and push docker image') {
            steps {
                docker.build('lamth2/aikidodanang-backend:jenkins').push()
            }
        }
    }
}