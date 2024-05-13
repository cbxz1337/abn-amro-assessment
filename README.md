## Objective
Create a standalone java application which allows users to manage their favourite recipes. It should
allow adding, updating, removing and fetching recipes. Additionally users should be able to filter
available recipes based on one or more of the following criteria:
1. Whether or not the dish is vegetarian
2. The number of servings
3. Specific ingredients (either include or exclude)
4. Text search within the instructions.
For example, the API should be able to handle the following search requests:
• All vegetarian recipes
• Recipes that can serve 4 persons and have “potatoes” as an ingredient
• Recipes without “salmon” as an ingredient that has “oven” in the instructions.
## Requirements
Please ensure that we have some documentation about the architectural choices and also how to
run the application. The project is expected to be delivered as a GitHub (or any other public git
hosting) repository URL.
All these requirements needs to be satisfied:
1. It must be a REST application implemented using Java (use a framework of your choice)
2. Your code should be production-ready.
3. REST API must be documented
4. Data must be persisted in a database
5. Unit tests must be present
6. Integration tests must be present

## Running the application
### Requirements
- Java 21
- Maven 3.x
1. Open the command line in the source code folder
2. Build project
 ```
  $ mvn package
  ```
  ```
Run the project
  ```
  $ java -jar abn-0.0.1-SNAPSHOT.jar
  ```
3. Open the swagger-ui with the link below

```text
http://localhost:8080/swagger-ui/index.html
```

## Architectural choices
### Architecture
Onion architecture was used to develop this app. Decision was made because of simplicity and scaleablity of this approach.
### Main FrameWorks
As a Framework Spring Boot 3.x was used because it's widely known leader in Java world and everyone is familiar with it.
Speaking about persistence, Jpa CriteriaApi approach was used instead of raw JDBC (which I really prefer, especially because of perfomance and Hibernate n+1 select).
CriteraApi was used to make app more scalable, instead of creating my own framework to build queries. As the result - more object orientated solution.
You can easly add new Fields as a keys and filter options.
### Database
To make app standalone, H2 in memory DB was used. It can be easely changed to any other relational db.
### Test
Unit tests and Integration tests provided, including DB tests and Controller tests.
