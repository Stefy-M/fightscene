# FightScene Backend API

Spring Boot + PostgreSQL backend for **FightScene.gg**. Handles fighter profiles, gyms, video uploads/processing, and authentication.

---

## Tech Stack
- Java 21, Spring Boot, Spring Data JPA
- PostgreSQL
- Flyway for migrations
- Maven

---

## Current Status
- Domain models for users, fighters, gyms, videos (including processing jobs).
- Repositories and service layer skeletons; video services wired to storage/privacy/processing helpers.
- AuthController/AuthService for signup/login (token and password verification still TODO).
- Postgres enums mapped for `User.role` (and similar enums).
- Health endpoint present; other controllers pending.

---

## Running Locally
1) Create the database:
```sql
CREATE DATABASE fightscene;
```
2) Configure DB credentials (example):
```ps1
# PowerShell
setx DB_PASSWORD "your-password"
```
```bash
# macOS/Linux
export DB_PASSWORD="your-password"
```
3) Start the app (port 8080 by default):
```bash
mvn spring-boot:run
```
Flyway will apply migrations automatically.

---

## API Notes
- Auth: `POST /api/auth/signup`, `POST /api/auth/login` (password check + token issuance not implemented yet).
- Health: `/health` (or `/actuator/health` if enabled).
- Other feature controllers (fighter, gym, video) still to be exposed.

---

## Roadmap
- Add password hashing/verification and JWT/session issuance.
- Expose fighter/gym/video controllers and wire search.
- Complete video upload/processing flow and storage deletion.
- Add tests (DataJpaTest + service tests).

---

## License
MIT License
