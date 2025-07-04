# Distributed Job Portal Microservices

## Overview
This project is a distributed job portal system designed to showcase advanced Java, microservices, and data structures/algorithms skills. It demonstrates how to build scalable, maintainable, and algorithmically sophisticated backend systems, similar to those used by top tech companies like Amazon.

## Features
- **User Service:** User profile management with skill autocomplete (Trie data structure)
- **Job Service:** Job postings, search by location/skills, urgent job queue (PriorityQueue)
- **Matching Service:** Algorithmic job matching using graph-based skill similarity
- **API Gateway:** Unified entry point and routing for all services
- **Dockerized:** Easy deployment with Docker Compose

## Architecture
- **Microservices:** Each core function is a separate Spring Boot service
- **Data Structures:** Trie (autocomplete), HashMap (caching), PriorityQueue (urgent jobs), Graph (matching)
- **Database:** MySQL for user-service, H2 for job-service (can be swapped for MySQL)
- **API Gateway:** Spring Cloud Netflix Zuul

```
job-portal-microservices/
├── user-service/
├── job-service/
├── matching-service/
├── api-gateway/
└── docker-compose.yml
```

## Tech Stack
- Java 17
- Spring Boot 2.7+
- Spring Cloud
- Maven
- MySQL, H2
- Docker & Docker Compose

## How to Build and Run
1. **Build all services:**
   ```sh
   cd user-service && mvn clean package
   cd ../job-service && mvn clean package
   cd ../matching-service && mvn clean package
   cd ../api-gateway && mvn clean package
   cd ..
   ```
2. **Start all services:**
   ```sh
   docker-compose up --build
   ```
3. **Test endpoints:** Use Postman or curl (see below).

## Example API Calls & Output

### Create User
```sh
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice","email":"alice@example.com","skills":["Java","Spring"],"experience":"2 years","location":"Seattle"}'
```
**Response:**
```json
{
  "id": 1,
  "name": "Alice",
  "email": "alice@example.com",
  "skills": ["Java", "Spring"],
  "experience": "2 years",
  "location": "Seattle"
}
```

### Skill Autocomplete
```sh
curl "http://localhost:8080/api/users/skills/autocomplete?prefix=ja"
```
**Response:**
```json
["java"]
```

### Job Search
```sh
curl "http://localhost:8080/api/jobs/search?location=Seattle&skills=Java,Spring"
```
**Response:**
```json
[
  {
    "id": 1,
    "title": "Java Developer",
    "company": "Amazon",
    "requiredSkills": ["Java", "Spring"],
    "experienceLevel": "Mid",
    "location": "Seattle",
    "postedDate": "2024-06-01T12:00:00",
    "priority": 10
  }
]
```

### Job Recommendations
```sh
curl "http://localhost:8080/api/matching/recommendations/1"
```
**Response:**
```json
[
  {
    "job": {
      "id": 1,
      "title": "Java Developer",
      "company": "Amazon",
      "requiredSkills": ["Java", "Spring"],
      "experienceLevel": "Mid",
      "location": "Seattle",
      "postedDate": "2024-06-01T12:00:00",
      "priority": 10
    },
    "matchScore": 1.0
  }
]
