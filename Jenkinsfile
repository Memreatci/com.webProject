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
                     def reportFile = "${reportDir}/cucumber-report.html"

                     def emailSubject = isBuildSuccess ? "Cucumber Test Report - Başarılı (${env.BUILD_NUMBER})" : "Cucumber Test Report - Başarısız (${env.BUILD_NUMBER})"
                     def emailBody = isBuildSuccess ? "Merhaba,\n\nCucumber testleri başarıyla tamamlandı. Rapor bağlantısı: \n${reportFile}\n\nSaygılar,\nJenkins" : "Merhaba,\n\nCucumber testleri başarısız oldu. Rapor bağlantısı: \n${reportFile}\n\nSaygılar,\nJenkins"

                     mail to: '35test42@gmail.com',
                          subject: emailSubject,
                          body: emailBody
                }
            }
        }
    }
}