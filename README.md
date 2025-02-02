# produc-team-case
Project to consume user data with spring boot, redis, kafka

**System Architecture**

![Screenshot from 2025-02-02 23-10-23](https://github.com/user-attachments/assets/35eec589-a6a8-4c5e-a602-b73b8a338344)

![Screenshot from 2025-02-02 23-13-29](https://github.com/user-attachments/assets/42bcb79d-ebe2-492a-b410-9f1fd62b0ac2)

**Requeirements**
- JDK 21
- Docker
- Postman
**Setup**
- Before run projects you need to start to docker.
- Run the docker-compose.yml inside the infrastructure

**Db Connection**
- url : jdbc:postgresql://localhost:5433/kafka_example
- username : example
- password : example


**Running**
- Uplaod a csv or excel file that contains user info to localhost:8001/upload/excel or localhost:8001/upload/csv

- every request to user-service need to be authenticated with basic auth
  ![Screenshot from 2025-02-02 23-25-03](https://github.com/user-attachments/assets/241629e2-fab2-48a6-9810-870f04dc5725)

- to query users  send a get request to http://localhost:8080/api/v1/users with given queryparam on postman(http://localhost:8080/api/v1/users?name=omer)
- to update user info send a put request http://localhost:8080/api/v1/users/{userId}
- to delete user info send a DELETE request   http://localhost:8080/api/v1/users/{userId}
- to show only a user info send a GET request http://localhost:8080/api/v1/users/{userId}
