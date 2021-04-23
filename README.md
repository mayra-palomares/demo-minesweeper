# Minesweeper API

RESTful API developed with Java framework Dropwizard.

## Dependencies
* Swagger for API documentation
    The available APIs are listed in Swagger
* MongoDB
    A NoSQL DB let me to save complex structures such as the matrix for the game board
* Google Guice for dependency injection
    The dependecies were specified in the MinesweeperModule class.

## Demo
The API was deployed on Heroku:
    https://minesweeper-api-mpalomares.herokuapp.com/swagger

## Next steps
* Improve algorithm for Minesweeper
* Use Jongo to simplify MongoDB queries
* Unit testing with JUnit and Mockito


