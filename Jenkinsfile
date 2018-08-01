node {
    def image
    stage('Clone') {
        checkout scm
    }

    stage('Build source') {
        withMaven(
            maven: 'maven',
            mavenSettingsConfig: '62b6e57c-374a-466d-8b38-399b111d4c5f',
            mavenLocalRepo: '.repository'
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