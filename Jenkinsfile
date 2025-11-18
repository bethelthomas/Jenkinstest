pipeline {
  agent any

  environment {
    APP_NAME = 'fast-jenkins-demo'
    IMAGE_NAME = "fast-jenkins-demo:${env.VERSION ?: '0.1.0'}"
    K8S_NAMESPACE = 'default'
  }

  options {
    timestamps()
    ansiColor('xterm')
  }

  parameters {
    string(name: 'VERSION', defaultValue: '0.1.0', description: 'App version tag')
  }

  // Load shared library configured in Jenkins as "fast-shared-lib"
  libraries {
    lib('fast-shared-lib')
  }

  stages {
    stage('Prepare') {
      steps {
        script {
          pipelineConfig() // load defaults from shared lib
          echo "Using IMAGE_NAME=${env.IMAGE_NAME}"
        }
      }
    }

    stage('Build image') {
      steps {
        bat """
          docker build -t %IMAGE_NAME% -f docker/Dockerfile .
        """
      }
    }

    stage('K8s deploy') {
      steps {
        bat """
          kubectl config current-context
          kubectl create namespace %K8S_NAMESPACE% --dry-run=client -o yaml | kubectl apply -f -
          kubectl set image -n %K8S_NAMESPACE% deployment/%APP_NAME% %APP_NAME%=%IMAGE_NAME% --record || kubectl apply -n %K8S_NAMESPACE% -f k8s\\deployment.yaml
          kubectl rollout status -n %K8S_NAMESPACE% deployment/%APP_NAME%
        """
      }
    }
  }

  post {
    always {
      echo "Pipeline finished for ${env.IMAGE_NAME}"
    }
  }
}
