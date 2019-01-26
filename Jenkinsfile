pipeline {
    agent any
    stages {

        stage ('Build Project') {
            steps {
                sh './demo/gradlew clean build --stacktrace'
            }
        }

    }

}