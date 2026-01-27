// helpers.groovy
def sayHello(name) {
    echo "Привет, ${name}! Это функция из файла."
}

def getVersion() {
    return "1.0.2026"
}

return this; // Обязательно в конце