pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                deleteDir() // Clean old workspace files
                git branch: 'main', url: 'https://github.com/atharvamavle/8.2CDevSecOps.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'npm install'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'npm test || true' // Continue even if tests fail
            }
        }

        stage('Generate Coverage Report') {
            steps {
                sh 'npm run coverage || true' // Skip failure
            }
        }

        stage('NPM Audit (Security Scan)') {
            steps {
                sh 'npm audit || true' // Do not block on audit warnings
            }
        }
    }
}
