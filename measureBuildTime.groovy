// helpers.groovy
def sayHello(name) {
    echo "Привет, ${name}! Это функция из файла."
}

def getVersion() {
    return "1.0.2026"
}

def measureTime() {
  long startTime = System.currentTimeMillis()
  echo "Функция запущена в: ${new Date(startTime)}"

  // 2. Цикл, который выполняется несколько секунд (например, 3 секунды)
    echo "Запуск тяжелого цикла..."
    int iterations = 0
    // Эмулируем работу: цикл будет крутиться, пока не пройдет 3000 мс
    while (System.currentTimeMillis() - startTime < 3000) {
        iterations++
        // Маленькая пауза, чтобы не нагружать процессор на 100%
        sleep(1) 
    }

    // 3. Вычисляем время выполнения
    long endTime = System.currentTimeMillis()
    long durationSeconds = (endTime - startTime) / 1000

    echo "Цикл завершен. Выполнено итераций: ${iterations}"
    echo "Время окончания: ${new Date(endTime)}"
    echo "Общее время выполнения функции: ${durationSeconds} сек."
}

return this; // Обязательно в конце