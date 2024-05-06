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

                    def htmlFiles = findFiles(glob: '**/target/cucumber-html-reports/*.html')

                     def firstHtmlFile = null

                    if (htmlFiles.length == 0) {
                        error('Hata: Belirtilen konumda HTML dosyası bulunamadı.')
                    } else {
                        firstHtmlFile = htmlFiles.first()

                    }

                     def emailSubject = isBuildSuccess ? "Cucumber Test Report - Successful (${env.BUILD_NUMBER})" :
                                                          "Cucumber Test Report - Failed (${env.BUILD_NUMBER})"
                     def emailBody = isBuildSuccess ? "Hello,\n\nCucumber tests successful completed. Report link: \n${firstHtmlFile.path}\n\nBest Regards,\nJenkins" :
                                                      "Hello,\n\nCucumber tests Failed. Report link: \n${firstHtmlFile.path}\n\nBest Regards,\nJenkins"

                     mail to      :'35test42@gmail.com',
                          subject : emailSubject,
                          body    : emailBody
                }
            }
        }
    }
}