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

        stage('Test and Send Results') {
            steps {
                echo 'Running tests...'
                script {
                def testResult = bat(script: "${MAVEN_HOME}/bin/mvn verify", returnStatus: true)

                    def reportFile = findFiles(glob: '**/target/cucumber-reports/*.html').first()
                    if (reportFile != null) {
                        emailext attachmentsPattern: "**/target/cucumber-reports/*.html",
                                 subject: 'Test Results',
                                 body: 'Please find attached the test results.',
                                 to: '35test42@gmail.com'
                    } else {
                        echo 'Test results not found.'
                    }

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

    triggers {
        cron('0 8 * * *')
    }
}