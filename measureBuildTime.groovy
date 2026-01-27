import groovy.json.JsonOutput

def sayHello(name) {
    echo "Привет, ${name}! Это функция из файла."
}

def getVersion() {
    return "1.0.2026"
}

def measureStep(String stepName, Closure body) {
    long startTime = System.currentTimeMillis()
    String status = "SUCCESS"
    
    echo "--- Начало выполнения: ${stepName} ---"
    
    try {
        body()
    } catch (Exception e) {
        status = "FAILED"
        echo "Callback run failed for project - ${stepName}"
        throw e
    } finally {
        long endTime = System.currentTimeMillis()
        long duration = (endTime - startTime) / 1000
        
        echo "--- Результат замера ---"
        echo "Шаг: ${stepName}"
        echo "Статус: ${status}"
        echo "Время выполнения: ${duration} сек."
        echo "------------------------"
        def payload = [
            metrics: [
                [
                name  : 'тест данных',
                type  : 'gauge',
                value : duration,
                labels: [
                    project: "project - ${stepName}",
                    id     : "id - ${stepName}",
                    status : status
                ]
                ]
            ]
        ]

        try {
            httpRequest(
                httpMode: 'POST',
                url: 'https://webhook.site/3a8ce3cb-78b3-441e-85f9-1191cddbada7',
                contentType: 'APPLICATION_JSON',
                requestBody: JsonOutput.toJson(payload),
                validResponseCodes: '200:299',
                timeout: 10
            )
        } catch (err) {
            // metrics must never break the build
            echo "Metrics push failed: ${err}"
        }
    }
}


return this; // Обязательно в конце