node {
    def image
    stage('Clone') {
        checkout scm
    }

    stage('Build source') {
        withMaven(
            maven: 'M3'
        ) {
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