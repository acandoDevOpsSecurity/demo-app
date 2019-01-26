pipeline {
    agent { label 'java' }
    stages {

        stage ('Build Project') {
            steps {
                sh './gradlew clean build'
            }
        }

    }

}