pipeline {
    agent any

    environment {
        RECIPIENTS = 'amavale34@gmail.com'
    }

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
                script {
                    try {
                        sh 'npm test | tee test.log'
                        currentBuild.result = 'SUCCESS'
                    } catch (e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
            post {
                always {
                    mail(
                        to: "${RECIPIENTS}",
                        subject: "Run Tests Stage - ${currentBuild.currentResult}",
                        body: "The 'Run Tests' stage completed with status: ${currentBuild.currentResult}. Check Jenkins workspace for logs."
                    )
                }
            }
        }

        stage('Generate Coverage Report') {
            steps {
                sh 'npm run coverage || true'
            }
        }

        stage('NPM Audit (Security Scan)') {
            steps {
                script {
                    try {
                        sh 'npm audit | tee audit.log'
                        currentBuild.result = 'SUCCESS'
                    } catch (e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
            post {
                always {
                    mail(
                        to: "${RECIPIENTS}",
                        subject: "Security Scan Stage - ${currentBuild.currentResult}",
                        body: "The 'NPM Audit' stage completed with status: ${currentBuild.currentResult}. Check Jenkins workspace for audit results."
                    )
                }
            }
        }
    }
}
pipeline {
    agent any

    environment {
        RECIPIENTS = 'amavale34@gmail.com'
    }

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
                script {
                    try {
                        sh 'npm test | tee test.log'
                        currentBuild.result = 'SUCCESS'
                    } catch (e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
            post {
                always {
                    mail(
                        to: "${RECIPIENTS}",
                        subject: "Run Tests Stage - ${currentBuild.currentResult}",
                        body: "The 'Run Tests' stage completed with status: ${currentBuild.currentResult}. Check Jenkins workspace for logs."
                    )
                }
            }
        }

        stage('Generate Coverage Report') {
            steps {
                sh 'npm run coverage || true'
            }
        }

        stage('NPM Audit (Security Scan)') {
            steps {
                script {
                    try {
                        sh 'npm audit | tee audit.log'
                        currentBuild.result = 'SUCCESS'
                    } catch (e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
            post {
                always {
                    mail(
                        to: "${RECIPIENTS}",
                        subject: "Security Scan Stage - ${currentBuild.currentResult}",
                        body: "The 'NPM Audit' stage completed with status: ${currentBuild.currentResult}. Check Jenkins workspace for audit results."
                    )
                }
            }
        }
    }
}
