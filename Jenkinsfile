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
    }
}