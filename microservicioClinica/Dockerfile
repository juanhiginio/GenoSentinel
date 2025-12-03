FROM node:20-alpine AS build
WORKDIR /app


COPY package*.json ./
RUN npm ci --legacy-peer-deps


COPY . .
RUN npm run build


FROM node:20-alpine
WORKDIR /app
COPY package*.json ./
RUN npm ci --only=production --legacy-peer-deps
COPY --from=build /app/dist ./dist


ENV NODE_ENV=production
EXPOSE 4000
CMD ["node", "dist/main.js"]