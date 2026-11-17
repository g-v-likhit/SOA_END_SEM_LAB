# Online Food Delivery System

Two Spring Boot microservices:
- Restaurant Service: http://localhost:8082
- Order Service: http://localhost:8083

Requires Java 21 and Maven.

## Run
Terminal 1:
```bash
cd restaurant-service
mvn spring-boot:run
```
Terminal 2:
```bash
cd order-service
mvn spring-boot:run
```

Order Service calls Restaurant Service at `http://localhost:8082` by default.
Override with `RESTAURANT_SERVICE_URL` if needed.

## Test Restaurant Service
- GET http://localhost:8082/restaurants
- GET http://localhost:8082/restaurants/1
- GET http://localhost:8082/restaurants/2

## Test Order Service
POST http://localhost:8083/orders
Content-Type: application/json
```json
{
  "restaurantId": 1,
  "customerName": "Likhit",
  "itemName": "Veg Biryani",
  "quantity": 2
}
```
Then GET http://localhost:8083/orders and GET http://localhost:8083/orders/1.

Orders are stored in memory and reset when the service restarts. This is a learning/demo project, not production software.

## Push to GitHub
Create an empty GitHub repository, then open CMD in this folder and run:
```cmd
git init
git add .
git commit -m "Add online food delivery microservices"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/food-delivery-system.git
git push -u origin main
```
Replace YOUR-USERNAME with your GitHub username. Never commit passwords, API keys, tokens, or `.env` files.
