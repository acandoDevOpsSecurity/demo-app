pipeline {
    agent any
    stages {

        stage ('Build Project') {
            steps {
                sh 'chmod +x ./demo/gradlew'
                sh './demo/gradlew clean build --stacktrace'
            }
        }

    }

}