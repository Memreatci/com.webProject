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
                        println "Bulunan ilk HTML dosyası: ${firstHtmlFile.path}"
                    }

                     // ZIP dosyasına sıkıştırma
                     def zipFilePath = 'target/cucumber-html-reports.zip'
                     zip archive: zipFilePath, dir: 'target/cucumber-html-reports'

                     // E-posta konusu ve gövdesi
                     def emailSubject = isBuildSuccess ? "Cucumber Test Report - Successful (${env.BUILD_NUMBER})" :
                                                          "Cucumber Test Report - Failed (${env.BUILD_NUMBER})"
                     def emailBody = isBuildSuccess ? "Hello,\n\nCucumber tests successful completed. Please find the attached report ZIP file.\n\nBest Regards,\nJenkins" :
                                                      "Hello,\n\nCucumber tests Failed. Please find the attached report ZIP file.\n\nBest Regards,\nJenkins"

                     // E-posta gönderme (ZIP dosyası ekli)
                                mail to: '35test42@gmail.com',
                                     subject  : emailSubject,
                                     body     : emailBody,
                                     attachLog: true, // İsterseniz e-posta ekinde build log'unu da ekleyebilirsiniz
                                     attachmentsPattern: zipFilePath // ZIP dosyasını ekleyin
                            }
            }
        }
    }
}