#!groovy

pipeline {
    agent any
    stages {
        stage('Notify Bitbucket') {
            steps {
                script {
                    sh '''
                        /opt/gradle/gradle-4.8/bin/gradle clean ./demo/build check
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