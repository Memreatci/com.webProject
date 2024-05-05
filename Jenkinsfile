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
          success {
              // Başarılı build durumunda e-posta gönderme
              script {
                  // Rapor dosyasını zip dosyasına sıkıştırma
                  bat 'powershell -Command "Compress-Archive -Path \'target/cucumber-reports/Cucumber.json\' -DestinationPath \'target/cucumber-reports.zip\'"'

                  // E-postayı gönderme
                  mail(
                      to: 'email@ornek.com',
                      subject: "Jenkins: Başarılı Build - ${env.JOB_NAME}",
                      body: "Build başarılı. Cucumber raporunu ekte bulabilirsiniz.",
                      attachmentsPattern: 'target/cucumber-reports.zip'
                  )
              }
          }

          failure {
              // Başarısız build durumunda e-posta gönderme
              script {
                  // Rapor dosyasını zip dosyasına sıkıştırma
                  bat 'powershell -Command "Compress-Archive -Path \'target/cucumber-reports/Cucumber.json\' -DestinationPath \'target/cucumber-reports.zip\'"'

                  // E-postayı gönderme
                  mail(
                      to: 'email@ornek.com',
                      subject: "Jenkins: Başarısız Build - ${env.JOB_NAME}",
                      body: "Build başarısız. Cucumber raporunu ekte bulabilirsiniz.",
                      attachmentsPattern: 'target/cucumber-reports.zip'
                  )
              }
          }
      }
}