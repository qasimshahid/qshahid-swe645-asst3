# qshahid-swe645-asst3
 
# 🔧 Important: Read These Instructions! 

This project requires a connection to a MySQL database. For security reasons, **you must provide your own database URL, username, and password** by creating a local `application-secret.properties` file.
**You must also then provide the file to the Jenkins server as a Secret File with the id 'app_secret_file'. This is so we don't expose our DB url, username, and password, in this public git repository.**

Please also see the createDb.sql file. Please execute that command after connecting to your AWS MySQL db using MySQL Workbench. We only need to create the database, the tables will be automatically created.

You need to setup the following security group in AWS: 
- HTTP (TCP 80) from 0.0.0.0/0
- Custom TCP (TCP 30000–40000) from 0.0.0.0/0
- HTTPS (TCP 443) from 0.0.0.0/0
- SSH (TCP 22) from 0.0.0.0/0
- MySQL/Aurora (TCP 3306) from 0.0.0.0/0
- Custom TCP (TCP 8080) from 0.0.0.0/0

---

## 📄 Create a Secret Configuration File

Create the following file in your local setup:

```
surveyapi/src/main/resources/application-secret.properties
```

Inside that file, add your actual database configuration:

```properties
spring.datasource.url=jdbc:mysql://<your-db-host>:3306/survey_db
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
```

Add this file as a secret credential to your Jenkins server with the id 'app_secret_file'.

---

## 📬 Postman API Examples

Here are example JSON bodies you can use in Postman to test the API endpoints:

### 1. **Create a Survey (POST `/api/surveys`)**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "streetAddress": "123 Main St",
  "city": "Fairfax",
  "state": "VA",
  "zip": "22030",
  "telephone": "123-456-7890",
  "email": "john.doe@example.com",
  "dateOfSurvey": "2025-04-13",
  "likedMost": "students",
  "interestSource": "Internet",
  "recommendLikelihood": "Very Likely",
  "additionalComments": "Great experience!"
}
```

---

#### Additional Examples:

#### Example 1:
```json
{
  "firstName": "Alice",
  "lastName": "Johnson",
  "streetAddress": "789 Pine St",
  "city": "Reston",
  "state": "VA",
  "zip": "20190",
  "telephone": "555-123-4567",
  "email": "alice.johnson@example.com",
  "dateOfSurvey": "2025-04-10",
  "likedMost": "Dorm rooms",
  "interestSource": "Television",
  "recommendLikelihood": "Likely",
  "additionalComments": "The faculty was very helpful!"
}
```

#### Example 2:
```json
{
  "firstName": "Bob",
  "lastName": "Brown",
  "streetAddress": "321 Oak St",
  "city": "Herndon",
  "state": "VA",
  "zip": "20170",
  "telephone": "555-987-6543",
  "email": "bob.brown@example.com",
  "dateOfSurvey": "2025-04-11",
  "likedMost": "location",
  "interestSource": "Friends",
  "recommendLikelihood": "Very Likely",
  "additionalComments": ""
}
```

---

### 2. **Update a Survey (PUT `/api/surveys/{id}`)**
Replace `{id}` with the ID of the survey you want to update. Example body:
```json
{
  "firstName": "Jane",
  "lastName": "Smith",
  "streetAddress": "456 Elm St",
  "city": "Arlington",
  "state": "VA",
  "zip": "22201",
  "telephone": "987-654-3210",
  "email": "jane.smith@example.com",
  "dateOfSurvey": "2025-04-12",
  "likedMost": "campus",
  "interestSource": "friends",
  "recommendLikelihood": "Likely",
  "additionalComments": "Loved the campus atmosphere!"
}
```

---

### 3. **Delete a Survey (DELETE `/api/surveys/{id}`)**
- No body is required for this request.
- Replace `{id}` with the ID of the survey you want to delete.

---

### 4. **Get a Survey by ID (GET `/api/surveys/{id}`)**
- No body is required for this request.
- Replace `{id}` with the ID of the survey you want to retrieve.

---

### 5. **Get All Surveys (GET `/api/surveys`)**
- No body is required for this request.

---

### Notes:
- Ensure the `Content-Type` header in Postman is set to `application/json`.
- Replace `{id}` in the URL with the actual survey ID you want to interact with.
- The `dateOfSurvey` field should be in `YYYY-MM-DD` format.
