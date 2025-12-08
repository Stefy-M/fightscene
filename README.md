# 🥊 FightScene Backend API

Backend service for **FightScene.gg**, built using Java Spring Boot and PostgreSQL.  
This backend will power fighter profiles, video uploads, gyms, and more as the platform evolves.

---

## 🚀 Tech Stack
- Java 21
- Spring Boot
- PostgreSQL
- Spring Data JPA
- Flyway
- Maven

---

## 📦 Current Features
- Project structure initialized  
- Database migrations via Flyway  
- Logging configuration  
- Basic health endpoint  
- Environment variable support for database credentials  

More features will be added as the backend is implemented.

---

## 🔧 Running Locally

### 1. Create PostgreSQL Database
```sql
CREATE DATABASE fightscene;
```

### 2. Set DB Password as Environment Variable
**Windows PowerShell:**
```ps
setx DB_PASSWORD "your-password"
```

**macOS/Linux:**
```bash
export DB_PASSWORD="your-password"
```

### 3. Start the Server
```
mvn spring-boot:run
```

Flyway will automatically apply migrations.

---

## 🗂 Project Structure
```
com.fightscene.backend/
├── config/
├── controller/
├── domain/
├── dto/
│   ├── request/
│   └── response/
├── exception/
├── mapper/
├── repository/
├── service/
│   └── impl/
└── FightSceneBackendApplication.java
```

---

## 📌 Roadmap
- Implement JPA entities  
- Add repositories  
- Build service layer  
- Add controller endpoints  
- Implement video upload workflow (S3 + CDN)  
- Authentication & user management  

---

## 📄 License
MIT License
