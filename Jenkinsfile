pipeline {
    agent any

    tools {
        gradle "gradle"
    }

    environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
    }

    options {
        timeout(5)
        gitLabConnection('GitLab')
    }

    triggers {
        gitlab(
        triggerOnPush: true,
        triggerOnMergeRequest: true,
        branchFilterType: 'All',
        addVoteOnMergeRequest: true,
        )
    }

   stages {
        stage ('Build') {
            steps {
                script {
                    sh 'chmod +x gradlew && ./gradlew clean build --info'
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    docker.build("6745345/api2:1.0")
                }
            }
        }

        stage('Pushing Docker Image to Dockerhub') {
            when {
                branch "master"
            }
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker_credential') {
                        docker.image("6745345/api:1.0").push()
                        docker.image("6745345/api:1.0").push("latest")
                    }
                }
            }
        }
    }
}
