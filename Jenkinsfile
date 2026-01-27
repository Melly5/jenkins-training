pipeline {
  agent { docker { image 'node:24.12.0-alpine3.23' } }   
  stages {
		stage('Build + Test') {
      parallel {
				stage('Build') {
					environment {
						VERSION = "1version"
						PROJECT_NAME = "${JOB_NAME.tokenize('/')[0]}"
						PROJECT_VERSION = "${BRANCH_NAME}+${BUILD_NUMBER}"
					}
					stages {
						stage('Build checkout') {
							steps {
                script {
    							echo "${VERSION}-${BUILD_NUMBER}-${PROJECT_NAME}-${PROJECT_VERSION}"
							  }
              }
						}
						stage('Build compile') {
							when {
                expression { VERSION != "null" }
              }
							steps {
								script {
                  // Это чистый Groovy
                  def name = "Jenkins"
                  def greeting = "Hello, ${name}!"
                    
                  if (name == "Jenkins") {
                    println(greeting.toUpperCase())
                  } else {
                    println("Hello, stranger")
                  }

                  // Пример цикла
                  def tools = ['Git', 'Docker', 'Maven']
                  for (tool in tools) {
                    echo "I can use ${tool}"
                  }
                }
							}
						}
					}
				}
      }
    }
  }
}