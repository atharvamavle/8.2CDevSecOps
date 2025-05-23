pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
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
                // Run tests but allow pipeline to continue on failure
                sh 'npm test || true'
            }
        }

        stage('Generate Coverage Report') {
            steps {
                // Run coverage script (e.g., nyc or jest), continue even if it fails
                sh 'npm run coverage || true'
            }
        }

        stage('NPM Audit (Security Scan)') {
            steps {
                // Scan for vulnerabilities and continue even if issues are found
                sh 'npm audit || true'
            }
        }
    }
}
