FROM node:20-alpine
WORKDIR /app
# Копируем файлы зависимостей
COPY package*.json ./
RUN npm install
# Копируем весь проект
COPY . .
# Собираем билд
RUN npm run build
# Запускаем на 3000 порту
EXPOSE 3000
CMD ["npm", "start"]
