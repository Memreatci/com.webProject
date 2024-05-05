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

                 bat 'mvn clean test -Dcucumber.plugin=pretty,json:target/cucumber-reports/Cucumber.json'

            }
        }
    }

   post {
          always {
              bat 'powershell -Command "Compress-Archive -Path \'target/cucumber-reports/Cucumber.json\' -DestinationPath \'target/cucumber-reports.zip\'"'

              // Başarılı veya başarısız sonuç durumunda Cucumber raporunu e-posta ile gönderme
              def emailSubject = currentBuild.result == 'SUCCESS' ? 'Başarılı Build' : 'Başarısız Build'
              def emailBody = currentBuild.result == 'SUCCESS' ? 'Build başarılı. Cucumber raporunu ekte bulabilirsiniz.' : 'Build başarısız. Cucumber raporunu ekte bulabilirsiniz.'

              mail(
                  to: 'email@ornek.com',
                  subject: "Jenkins: ${emailSubject} - ${env.JOB_NAME}",
                  body: emailBody,
                  mimeType: 'multipart/mixed',
                  attachments: 'target/cucumber-reports.zip'
              )
          }
      }
}