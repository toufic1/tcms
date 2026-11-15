// Jenkinsfile: the pipeline Jenkins runs on every push to main.
//
//   Checkout  →  Build & Test  →  Deploy  →  Health check
//
// The stages run one after the other. If one fails, the next ones are SKIPPED
// and the build turns red, so broken code never replaces the running app.
// This file is in Git, so the pipeline is versioned and reviewed like the code.

pipeline {
    // Run on any available Jenkins machine (we have only one: the VPS).
    agent any

    options {
        // Never run two deploys at the same time: two "docker compose up"
        // running together could mix up the containers.
        disableConcurrentBuilds()
        // Keep only the last 20 builds, so old logs don't fill the disk.
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    // Environment variables for every stage below.
    environment {
        // Compose names everything after the project: tcms-app-1, tcms-db-1, tcms_pgdata.
        // Using 'tcms' (the same name as the first manual deploy) means Jenkins manages
        // the SAME containers and the SAME volume, so no data is lost.
        COMPOSE_PROJECT_NAME = 'tcms'
        // Read the DB password from Jenkins credentials (id 'tcms-db-password').
        // It is never in Git, and Jenkins hides it as **** in the logs.
        // docker-compose.yaml reads it as ${DB_PASSWORD}.
        DB_PASSWORD = credentials('tcms-db-password')
    }

    stages {
        // 1. Get the code of the commit that triggered this build from GitHub.
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        // 2. Build the app image with the Dockerfile.
        //    Maven compiles and runs the tests INSIDE Docker, so Jenkins needs no Java or Maven.
        //    A compile error or a failed test stops the pipeline here.
        stage('Build & Test') {
            steps {
                sh 'docker compose build app'
            }
        }

        // 3. Start the containers. -d = detached (in the background).
        //    Compose replaces only what changed (the app container with the new image);
        //    the db container and its volume stay as they are.
        stage('Deploy') {
            steps {
                sh 'docker compose up -d'
            }
        }

        // 4. Check that the NEW app really works, not just that the container started.
        //    Spring Boot Actuator answers /actuator/health with {"status":"UP"} when the app
        //    and its database connection are OK.
        //    We try every 5 seconds, up to 30 times (2.5 minutes), because Spring Boot takes time to start.
        stage('Health check') {
            steps {
                sh '''
                    URL=http://localhost:8080/actuator/health
                    for i in $(seq 1 30); do
                        # -f = fail on HTTP errors, -s = silent. grep -q = just check, print nothing.
                        if curl -fs "$URL" | grep -q '"status":"UP"'; then
                            echo "App is UP"
                            exit 0
                        fi
                        echo "Waiting for the app... ($i/30)"
                        sleep 5
                    done
                    # Never became UP: print the last app logs to see why, then fail the build.
                    echo "App did not become healthy"
                    docker compose logs --tail=100 app
                    exit 1
                '''
            }
        }
    }

    // Runs after all the stages.
    post {
        success { echo 'Deployed successfully' }
        failure { echo 'Pipeline FAILED: see the stage marked red' }
        // Every build makes a new image; delete the old unused ones so the disk doesn't fill up.
        always  { sh 'docker image prune -f' }
    }
}
