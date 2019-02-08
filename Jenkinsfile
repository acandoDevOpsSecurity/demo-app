#!groovy

pipeline {
    agent any
    stages {
        stage('Notify Bitbucket') {
            steps {
                script {
                    sh '''
                        chmod +x ./demo/gradlew
                        ./demo/gradlew clean build check
                    '''
                }
            }
        }
    }

    post {
        always {
            junit testResults: '**/target/surefire-reports/TEST-*.xml'
            recordIssues enabledForFailure: true, tool: pmdParser(pattern: 'target/pmd.xml'),sourceCodeEncoding: 'UTF-8'
        }
    }

}