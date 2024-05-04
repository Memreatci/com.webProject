pipeline {
    agent any

    environment {
            MAVEN_HOME = tool 'Maven'
            JAVA_HOME = tool 'Java'
        }

    stages {
        stage('Checkout') {
            steps {
                // Kod deposundan projeyi çekme
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                // Maven kullanarak projenin derlenmesi ve test edilmesi
                bat 'mvn clean test'

                // Test sonuçlarını toplama
                junit 'target/cucumber-html-reports/*.xml'
            }
        }
    }

    post {
        always {
            // Test sonuçlarını toplama
            junit 'target/cucumber-html-reports/*.xml'

            // Duruma göre farklı e-posta mesajları gönderme
            script {
                if (currentBuild.result == 'SUCCESS') {
                    // Test sonuçları başarılıysa e-posta gönder
                    emailext(
                        to: '35test42@gmail.com',
                        subject: 'Test Sonuçları: Başarılı',
                        body: '''Merhaba,Yapılan testler başarıyla tamamlandı. Test sonuçlarına aşağıdaki bağlantıdan erişebilirsiniz:
                        ${BUILD_URL}artifact/target/surefire-reports/İyi çalışmalar!''',
                        attachLog: true
                    )
                } else if (currentBuild.result == 'FAILURE') {
                    // Test sonuçları başarısızsa e-posta gönder
                    emailext(
                        to: '35test42@gmail.com',
                        subject: 'Test Sonuçları: Başarısız',
                        body: '''Merhaba,Yapılan testler başarısız oldu. Lütfen test sonuçlarını kontrol edin:
                        ${BUILD_URL}artifact/target/surefire-reports/ İyi çalışmalar!''',
                        attachLog: true
                    )
                }
            }
        }
    }
}







