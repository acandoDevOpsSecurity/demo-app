#!groovy

pipeline {

    stages {
        stage('Notify Bitbucket') {
            script {
                sh '''
                    cd ./demo
                    gradle clean build
                '''
            }
        }
    }

    post {
        always {
            junit testResults: '**target/surefire-reports/TEST-*.xml'
            recordIssues enableForFailure: true, tool: checkStyle()
        }
    }

}