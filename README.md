## Movie Search API using Meilisearch and Spring Boot

This project is a demonstration of a movie search API built with Spring Boot and Meilisearch. Meilisearch is used as the search engine to provide fast and efficient movie search capabilities.

### Components

- **Backend**: The backend of this project is implemented using Spring Boot, which exposes a RESTful API for movie search operations. Meilisearch is integrated with Spring Boot to perform the movie search efficiently.

- **Frontend**: The frontend is a simple web application built with Quasar, a Vue.js framework for building responsive web applications. The frontend consumes the movie search API provided by the backend to display search results in a user-friendly manner.

### Installation and Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/mehdizebhi/spring-data-meilisearch-demo
   cd spring-data-meilisearch-demo
   ```

2. **Running Meilisearch**

   Before running the application, you need to set up and run Meilisearch using Docker Compose.

   a. Ensure you have Docker and Docker Compose installed.

   b. Create a `docker-compose.yaml` file in the project root with the following content:
      ```yaml
      version: "3.9"
      services:
        meilisearch:
          image: "getmeili/meilisearch:v1.7"
          ports:
            - "7700:7700"
          volumes:
            - ./meili_data:/data.ms
          environment:
            - MEILI_ENV=development
            - MEILI_MASTER_KEY=masterKey
      ```

   c. Start Meilisearch using Docker Compose:
      ```bash
      docker-compose up -d
      ```

   d. Meilisearch will be available at `http://localhost:7700` with the master key set to `masterKey`.

3. **Loading Movie Data into Meilisearch**

   Once Meilisearch is up and running, you can load the movie data into it.

   a. Download the movie data JSON file from [here](https://www.meilisearch.com/movies.json).

   b. Use Postman or curl to send a POST request to Meilisearch for indexing the data. Replace `<MASTER_KEY>` with your Meilisearch master key.
      ```bash
      curl -X POST 'http://localhost:7700/indexes/movies/documents?primaryKey=id' \
      -H 'Content-Type: application/json' \
      -H 'Authorization: Bearer masterKey' \
      --data-binary '@movies.json'
      ```

4. **Building and Running the Spring Boot Application**

   a. Build the Maven project (The frontend build files will be copied to `target/classes/static` during the Maven build process.):
      ```bash
      mvn clean package
      ```

   b. After successful build, navigate to the target directory:
      ```bash
      cd target
      ```

   c. Run the Spring Boot application using the generated JAR file (replace `<JAR_FILE_NAME>` with the actual JAR file name):
      ```bash
      java -jar spring-data-emailsearch-demo-0.0.1-SNAPSHOT.jar
      ```

5. **Accessing the Application**

   Once the backend is running, you can access the frontend at `http://localhost:8080`.

### Movie Data
The movie data used in this project is sourced from [Meilisearch's own documentation](https://www.meilisearch.com/movies.json).

### Preview

![Movie Search API Preview](preview.gif)

---

Feel free to customize and extend this project as needed. If you have any questions or feedback, please reach out!