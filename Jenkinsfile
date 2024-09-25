pipeline {
    agent any
    triggers {
        githubPush()
    }
    stages {
        stage("Cleaning before building"){
           steps {
            deleteDir()
         }
        }
         stage("clone repo") {
            steps {
                bat "git clone https://bitbucket.es.ad.adp.com/scm/gidt1/e-letter-2024-batch2-team1.git"
            }
         }
        stage("build"){
            steps {
                dir("e-letter-2024-batch2-team1/backend"){
                bat "mvn clean package"
                }
            }
        }
        stage("Creating docker image"){
            steps {
                dir("e-letter-2024-batch2-team1/backend"){
                    bat "docker build -t docker.repository.esi.adp.com/clientcentral/e-letter-backend-with-jenkins ."
                }
               
            }
        }
        stage("push docker image to the docker hub"){
            steps {
                bat "docker login docker.repository.esi.adp.com -u clientcentralcicd -p T19zAg7StlDlrav9PatH0bRO"
                bat "docker push docker.repository.esi.adp.com/clientcentral/e-letter-backend-with-jenkins"
            }
        }

    }

}