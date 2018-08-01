node {
    def image
    stage('Clone') {
        checkout scm
    }

    stage('Build source') {
        docker.image('maven:3-alpine').withRun('-v /root/.m2:/root/.m2').inside {
            sh 'mvn clean package'
        }
    }

    stage('Build docker image') {
        image = docker.build('lamth2/aikidodanang-backend:jenkins')
    }

    stage('Push image') {
        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
            image.push()
        }
    }
}