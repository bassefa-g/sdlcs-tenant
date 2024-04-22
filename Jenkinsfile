def app
pipeline {
    agent any
    stages {
        stage("Checkout") {
            steps {
       		 checkout scm
            }
       	}
        stage('Build') {
            steps {
                sh './gradlew downloadDependencies'
                sh './gradlew build -x test'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh './gradlew sonar -Dsonar.projectKey=sdlcs-user'
                }
                sleep(30);
            }
        }
        stage("Quality Gate") {
            steps {
              timeout(time: 5, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: false
              }
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'build/libs/*-SNAPSHOT.jar', onlyIfSuccessful: true
        }
    }
}
