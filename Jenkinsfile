pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Execute Tests') {
            steps {
                script {
                    // Tests run
                    def mvnResult = bat(script: 'mvn verify -Dcucumber.options="--tags=$env.TAGS"', returnStatus: true)

                    // Test result evaluation
                    isBuildSuccess = mvnResult == 0
                }
            }
        }

        stage('Send Report') {
            steps {
                script {
                    def reportDir = 'target/cucumber-html-reports'
                    def reportFile = "${reportDir}/${env.BUILD_ID}/cucumber-report.html"

                    def emaildata
                    if (isBuildSuccess) {
                        emaildata = [
                            to: '35test42@gmail.com',
                            subject: "Cucumber Test Report - Successful (${env.BUILD_NUMBER})",
                            body: "Hello,\n\nCucumber tests successfully completed. Click the report link: \nhttps://cucumber.io/docs/cucumber/reporting/(${reportFile})\n\nRegards,\nJenkins",
                            charset: 'UTF-8'
                        ]
                    } else {
                        emaildata = [
                            to: '35test42@gmail.com',
                            subject: "Cucumber Test Report - Failed (${env.BUILD_NUMBER})",
                            body: "Hello,\n\nCucumber tests ended in failure. Click the report link: \nhttps://cucumber.io/docs/cucumber/reporting/(${reportFile})\n\nRegards,\nJenkins",
                            charset: 'UTF-8'
                        ]
                    }
                    // Send email with the report
                    sendMail(emaildata)
                }
            }
        }
    }
}