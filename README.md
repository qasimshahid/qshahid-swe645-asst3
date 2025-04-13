# qshahid-swe645-asst3
 
# 🔧 Database Configuration (Important!)

This project requires a connection to a MySQL database. For security reasons, **you must provide your own database URL, username, and password** by creating a local `application-secret.properties` file.

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

---