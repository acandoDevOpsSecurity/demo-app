#!groovy

pipeline {
    agent any
    stages {
        stage('Notify Bitbucket') {
            steps {
                script {
                    sh '''
                        cd ./demo
                        gradle clean build
                    '''
                }
            }
        }
    }

    post {
        always {
            junit testResults: '**target/surefire-reports/TEST-*.xml'
            recordIssues enabledForFailure: true, tool: checkStyle()
        }
    }

}