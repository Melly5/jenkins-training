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
                  // Загружаем файл
                  def helpers = load 'measureBuildTime.groovy'

                  // Используем функции
                  helpers.sayHello('Alina')
                  def v = helpers.getVersion()
                  echo "Текущая версия: ${v}"

									// Пример 1: Успешный запуск с циклом
                  helpers.measureStep("Heavy Loop") {
                    echo "Запуск цикла на 3 секунды..."
                    sleep 3
                    int x = 10 * 10
                    echo "Результат вычислений: ${x}"
                  }

                  // Пример 2: Запуск шага, который упадет
                  helpers.measureStep("Failing Task") {
                    def requestConfig = [url: 'https://api.github.com']
    
                    stats = httpRequest(
                            url: requestConfig.url,
                            httpMode: 'GET',
                            validResponseCodes: '200:299',
                            timeout: 10
                        )

                    echo "Ответ от сервера: ${stats.response.content}"

                    echo "Этот шаг сейчас упадет..."
                    sh "exit 1" // Вызываем ошибку
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