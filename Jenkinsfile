pipeline {
    agent any

    environment {
            MAVEN_HOME = tool 'Maven'
            JAVA_HOME = tool 'Java'
        }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                bat 'mvn clean test'

                junit 'target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {

            junit 'target/surefire-reports/*.xml'


            script {
                if (currentBuild.result == 'SUCCESS') {

                    emailext(
                        to: '35test42@gmail.com',
                        subject: 'The tests were successfully completed.',
                        body: 'Test Results - Error Free',
                        attachLog: true
                    )
                } else if (currentBuild.result == 'FAILURE') {

                    emailext(
                        to: '35test42@gmail.com',
                        subject: 'There was an error in the tests, please check.',
                        body: 'Tests fail',
                        attachLog: true
                    )
                }
            }
        }
    }
}







