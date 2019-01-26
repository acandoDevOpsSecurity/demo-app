pipeline {
    agent any
    stages {

        stage ('Build Project') {
            steps {
                sh '/bin/bash ./demo/gradlew clean build --stacktrace'
            }
        }

    }

}