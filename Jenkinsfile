pipeline {
	agent none
   stages {
		stage('Build + Test') {
      parallel {
				stage('Build') {
					agent { label 'ja' }
					environment {
						VERSION = "1version"
						PROJECT_NAME = "${JOB_NAME.tokenize('/')[0]}"
						PROJECT_VERSION = "${BRANCH_NAME}+${BUILD_NUMBER}"
					}
					stages {
						stage('Build checkout') {
							steps {
                script {
                  sh """${VERSION}-${BUILD_NUMBER}-${PROJECT_NAME}-${PROJECT_VERSION}  """
							  }
              }
						}
						stage('Build compile') {
							when {
                expression { VERSION != "null" }
              }
							steps {
								script {
									sh """
										printenv
										IMAGE=test-repo:${VERSION}-${BUILD_NUMBER}
										docker build -t \$IMAGE .
										docker push \$IMAGE
									"""
								}
							}
						}
					}
				}
      }
    }
  }
}