<h1>Backend project RESTFUL API with Java and Springboot</h1>

# Peaky Blinders Food API

## Description
Peaky Blinders Food API is a demo project for Spring Boot, designed to manage restaurants, orders, products, and users in a food delivery application.

## Requirements
- Java 17
- Maven
- MySQL

## Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/peaky-blinders-food-api.git
    cd peaky-blinders-food-api
    ```

2. Configure the MySQL database:
    - Create a database named `peaky_blinders_food_api`.
    - Update the `application.properties` file with your database credentials:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/peaky_blinders_food_api
      spring.datasource.username=your-username
      spring.datasource.password=your-password
      ```

3. Install dependencies and compile the project:
    ```bash
    mvn clean install
    ```

4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Usage
After starting the application, you can access the API at the following URL:

http://localhost:8080

## Main Endpoints
- `/cozinhas` - Manages kitchens
- `/restaurantes` - Manages restaurants
- `/produtos` - Manages products
- `/pedidos` - Manages orders
- `/usuarios` - Manages users
- `/formas-pagamento` - Manages payment methods
- `/cidades` - Manages cities
- `/estados` - Manages states
- `/grupos` - Manages user groups
- `/permissoes` - Manages permissions

## Features
- **Kitchen Management:** Allows adding, listing, updating, and removing kitchens.
- **Restaurant Management:** Includes registration, listing, updating, and removal of restaurants.
- **Product Management:** Enables the management of products offered by restaurants.
- **Order Management:** Full control over orders, from creation to delivery status.
- **User Management:** Allows managing system users.
- **Payment Methods:** Management of payment methods accepted by restaurants.
- **City and State Management:** Maintenance of geographic locations.

## Contribution
If you want to contribute to the project, follow the steps below:
1. Fork the project.
2. Create a branch for your feature (`git checkout -b feature/feature-name`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature/feature-name`).
5. Open a Pull Request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Contact
If you have any questions or suggestions, feel free to contact me.

- Name: Your Name
- Email: your.email@domain.com
- LinkedIn: [https://www.linkedin.com/in/igor-cataldi-grillo-5709bb47/)
- GitHub: [https://github.com/Igorlander]

## Class Diagram

![Class Diagram]("src/main/resources/img/diagrama-de-classes-de-dominio.jpg")
