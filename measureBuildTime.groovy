// helpers.groovy
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
        // Выполняем переданный колбэк
        body()
    } catch (Exception e) {
        status = "FAILED"
        echo "Ошибка в шаге '${stepName}': ${e.message}"
        throw e // Пробрасываем ошибку дальше, чтобы Jenkins пометил билд как упавший
    } finally {
        long endTime = System.currentTimeMillis()
        long duration = (endTime - startTime) / 1000 // время в секундах
        
        // "Отправка" результата (в данном случае в лог, но здесь может быть HTTP-запрос)
        echo "--- Результат замера ---"
        echo "Шаг: ${stepName}"
        echo "Статус: ${status}"
        echo "Время выполнения: ${duration} сек."
        echo "------------------------"
        
        // Здесь можно добавить отправку в мониторинг, например:
        // sh "curl -X POST http://monitoring.api -d 'name=${stepName}&status=${status}&time=${duration}'"
    }
}


return this; // Обязательно в конце