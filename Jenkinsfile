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

        stage('Create Zip Report') {
            steps {
                script {
                    def zip = archiveArtifacts artifacts: 'target/cucumber-html-reports/*.html', format: 'zip', archiveType: 'zip'
                }
            }
        }

        stage('Send Report') {
            steps {
                script {
                    def attachedFile = zip.fingerprint.expand(env.BUILD_ID) + '.zip'

                    // Email sending logic with the zip attachment
                    mail to: '35test42@gmail.com',
                         subject: isBuildSuccess ? "Cucumber Test Report - Successful (${env.BUILD_NUMBER})" :
                                                   "Cucumber Test Report - Failed (${env.BUILD_NUMBER})",
                         body: isBuildSuccess ? "Hello,\n\nCucumber tests successful completed. Attached is the report.\n\nBest Regards,\nJenkins" :
                                                 "Hello,\n\nCucumber tests Failed. Attached is the report.\n\nBest Regards,\nJenkins",
                         attachFile: attachedFile
                }
            }
        }
    }
}



