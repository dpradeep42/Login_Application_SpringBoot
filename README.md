# Login Application

A small Spring Boot web app for user registration and login. A visitor creates an account with a name, email, and password, then signs in with that email and password. After login, the home page shows the signed-in user.

Registration stores the password as a BCrypt hash. Spring Security compares the submitted password with that hash during form login. The hash is produced by `BCryptPasswordEncoder` from `spring-security-crypto`, which comes in with `spring-boot-starter-security`.

## Tech stack

- Java 17
- Spring Boot 2.6.4
- Spring Web and Spring Security
- Spring Data JPA and Hibernate
- Thymeleaf
- Microsoft SQL Server (`mssql-jdbc`)
- H2, used only by the local `h2` profile
- Maven, including the Maven Wrapper

## Prerequisites

- JDK 17
- Maven is optional. `LoginApplication/mvnw` downloads the project Maven version.
- SQL Server, reachable from this machine, with a database the app may update. The default URL expects a database named `Credentials` on `localhost:1433`.

To try the app without SQL Server, use the `h2` profile described below. That profile keeps the data in memory and drops it when the process stops.

## Environment variables

The SQL Server username and password are not stored in the repo. `LoginApplication/src/main/resources/application.properties` reads them from the environment:

| Variable | Required | Purpose |
| --- | --- | --- |
| `DB_USERNAME` | Yes, for the default profile | Database username |
| `DB_PASSWORD` | Yes, for the default profile | Database password |
| `DB_URL` | No | JDBC URL. When unset, the app uses `jdbc:sqlserver://localhost:1433;databaseName=Credentials` |

`LoginApplication/.env.example` lists the same variables with placeholder values. Export them in the shell before starting the default profile. Do not commit a real `.env` file.

```bash
export DB_USERNAME=your_db_username
export DB_PASSWORD=your_db_password
# optional
export DB_URL=jdbc:sqlserver://localhost:1433;databaseName=Credentials
```

## Run

From `LoginApplication`:

```bash
./mvnw spring-boot:run
```

The app listens on port 8080.

- Registration: http://localhost:8080/registration
- Login: http://localhost:8080/login
- Home (requires login): http://localhost:8080/

Hibernate is set to `ddl-auto=update`, so it creates or updates the `users`, `role`, and `userss_roles` tables in the configured database.

### H2 profile

This starts the same application against an in-memory H2 database. No SQL Server account is required.

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=h2
```

## Tests

From `LoginApplication`:

```bash
./mvnw test
```

Tests activate the `h2` profile. They check that the application context starts, that a new password is stored as a BCrypt hash rather than the original text, and that form login accepts that password and rejects a different one.

## Project layout

The Maven project is in `LoginApplication`. `src/main/java/com/pradeep` holds the application, security configuration, user model, and registration and login controllers. Thymeleaf templates are in `src/main/resources/templates`.

## License

MIT License. Copyright (c) 2026 Pradeep Kumar Dasari. See [LICENSE](LICENSE).
