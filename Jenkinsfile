pipeline {
    agent any

    options {
        disableConcurrentBuilds()        // never run two deploys at the same time
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    environment {
        COMPOSE_PROJECT_NAME = 'tcms'                  // same containers + volume as step F
        DB_PASSWORD = credentials('tcms-db-password')  // from Jenkins, never from Git
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'docker compose build app'   // runs Maven + tests inside Docker
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker compose up -d'
            }
        }

        stage('Health check') {
            steps {
                sh '''
                    URL=http://localhost:8080/actuator/health
                    for i in $(seq 1 30); do
                        if curl -fs "$URL" | grep -q '"status":"UP"'; then
                            echo "App is UP"
                            exit 0
                        fi
                        echo "Waiting for the app... ($i/30)"
                        sleep 5
                    done
                    echo "App did not become healthy"
                    docker compose logs --tail=100 app
                    exit 1
                '''
            }
        }
    }

    post {
        success { echo 'Deployed successfully' }
        failure { echo 'Pipeline FAILED: see the stage marked red' }
        always  { sh 'docker image prune -f' }        // remove old, unused images
    }
}
