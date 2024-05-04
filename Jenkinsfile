pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'Maven'
        JAVA_HOME = tool 'Java'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                bat "${MAVEN_HOME}/bin/mvn clean install"
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                bat "${MAVEN_HOME}/bin/mvn verify"
            }
        }

        stage('Send Test Results') {
            steps {
                script {
                    def reportFile = findFiles(glob: '**/target/cucumber-reports/*.json').first()
                    if (reportFile != null) {
                        emailext attachmentsPattern: "**/target/cucumber-reports/*.json",
                                 subject: 'Test Results',
                                 body: 'Please find attached the test results.',
                                 to: '35test42@gmail.com'
                    } else {
                        echo 'Test results not found.'
                    }
                }
            }
        }

        stage('Send Email') {
            steps {
                script {

                    def testResult = sh(script: 'mvn test', returnStatus: true)
                    if (testResult == 0) {

                        emailext body: 'Tests successfully completed.',
                                 subject: 'Test Results - Error Free',
                                 to: '35test42@gmail.com'
                    } else {

                        emailext body: 'Error in tests, please check.',
                                 subject: 'Test Results - Failed',
                                 to: '35test42@gmail.com'
                    }
                }
            }
        }
    }


}





