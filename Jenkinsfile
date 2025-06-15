pipeline {
    agent { label 'local' }

    environment {
        REPO_URL = 'https://github.com/OtoGvazava/DemoService.git'
        IMAGE_NAME = 'demo-service'
        CONTAINER_NAME = 'demo-service-container'
        SERVER_PORT = '8090'
        NETWORK_NAME = 'demo-net'
    }

    stages {
//         stage('Checkout') {
//             steps {
//                 git url: "${REPO_URL}", branch: 'main'
//             }
//         }

        stage('Code Quality Check') {
            steps {
                sh 'mvn checkstyle:checkstyle pmd:pmd spotbugs:spotbugs || true'
            }
        }

        stage('Publish Quality Reports') {
            steps {
                recordIssues(tools: [
                    checkStyle(pattern: '**/target/checkstyle-result.xml'),
                    spotBugs(pattern: '**/target/spotbugsXml.xml'),
                    pmdParser(pattern: '**/target/pmd.xml')
                ])
            }
        }


        stage('Build Docker Image') {
            steps {
                sh 'docker --version'
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Stop Existing Container') {
            steps {
                sh """
                    if [ \$(docker ps -q -f name=${CONTAINER_NAME}) ]; then
                        echo "Stopping existing container..."
                        docker stop ${CONTAINER_NAME}
                        docker rm ${CONTAINER_NAME}
                    else
                        echo "No container named ${CONTAINER_NAME} is running."
                    fi
                """
            }
        }

        stage('Run Docker Container') {
            steps {
                sh "docker run -d --name ${CONTAINER_NAME} --network ${NETWORK_NAME} -p ${SERVER_PORT}:${SERVER_PORT} ${IMAGE_NAME}"
            }
        }
    }

    post {
        success {
            echo '✅ DemoService deployed successfully on network demo-net!'
        }
        failure {
            echo '❌ Deployment failed. Check build logs for details.'
        }
    }
}