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
               mail(
                   to: '35test42@gmail.com',
                   subject: "Jenkins: Successful Build - ${env.JOB_NAME}",
                   body: "Build Successful Cucumber report attached",
                   attachments: 'target/cucumber-reports/Cucumber.json'
               )
           }

           failure {
               mail(
                   to: '35test42@gmail.com',
                   subject: "Jenkins: Failed Build - ${env.JOB_NAME}",
                   body: "Build Failed. Cucumber report attached",
                   attachments: 'target/cucumber-reports/Cucumber.json'
               )
           }
       }
}