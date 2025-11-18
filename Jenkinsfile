pipeline {
  agent any

  environment {
    APP_NAME = 'fast-jenkins-demo'
    IMAGE_NAME = "fast-jenkins-demo:${env.VERSION ?: '0.1.0'}"
    K8S_NAMESPACE = 'default'
  }

  options {
    timestamps()
    // ansiColor is NOT valid here
  }

  parameters {
    string(name: 'VERSION', defaultValue: '0.1.0', description: 'App version tag')
  }

  libraries {
    lib('fast-shared-lib')
  }

  stages {
    stage('Prepare') {
      steps {
        ansiColor('xterm') {
          script {
            pipelineConfig() // load defaults from shared lib
            echo "Using IMAGE_NAME=${env.IMAGE_NAME}"
          }
        }
      }
    }

    stage('Build image') {
      steps {
        ansiColor('xterm') {
          bat """
            docker build -t %IMAGE_NAME% -f docker/Dockerfile .
          """
        }
      }
    }

    stage('K8s deploy') {
      steps {
        ansiColor('xterm') {
          bat """
            kubectl config current-context
            kubectl create namespace %K8S_NAMESPACE% --dry-run=client -o yaml | kubectl apply -f -
            kubectl set image -n %K8S_NAMESPACE% deployment/%APP_NAME% %APP_NAME%=%IMAGE_NAME% --record || kubectl apply -n %K8S_NAMESPACE
