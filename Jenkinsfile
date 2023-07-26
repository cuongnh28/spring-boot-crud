pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage ('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/cuongnh28/spring-boot-crud']])
                sh 'mvn clean install'
            }
        }
        stage ('Build docker image') {
            steps {
                sh 'docker build -t cuongnh28/backend .'
            }
        }
        stage ('Push image to Hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                        sh 'docker login -u cuongnh28 -p ${dockerhubpwd}'
                    }
                    sh 'docker push cuongnh28/backend'
                }
            }
        }
        stage ('Run Docker on Server') {
            steps {
                script {
                    sshagent(['ssh-springboot-remote']) {
                        // some block
                        sh 'ssh -o StrictHostKeyChecking=no ubuntu@ec2-52-77-228-147.ap-southeast-1.compute.amazonaws.com docker pull cuongnh28/backend'
                        sh 'ssh -o StrictHostKeyChecking=no ubuntu@ec2-52-77-228-147.ap-southeast-1.compute.amazonaws.com docker docker run --rm -d -p 8080:8080 --name backend cuongnh28/backend'
'
                    }
                }
            }
        }
    }
}
