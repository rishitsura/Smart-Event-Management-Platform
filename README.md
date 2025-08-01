# 🎯 Smart Event Management Platform

A comprehensive full-stack event management platform built with modern technologies, featuring real-time updates, robust authentication, and scalable architecture.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![React](https://img.shields.io/badge/React-18-blue)
![TypeScript](https://img.shields.io/badge/TypeScript-5-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Redis](https://img.shields.io/badge/Redis-7-red)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue)

## 🚀 Features

### Core Functionality
- ✅ **User Authentication & Authorization** - JWT-based security with role management
- ✅ **Event Management** - Create, update, publish, and manage events
- ✅ **RSVP System** - Track attendees with real-time capacity updates
- ✅ **Real-time Updates** - WebSocket integration for live event data
- ✅ **Advanced Caching** - Redis-powered performance optimization

### Technical Highlights
- ✅ **RESTful API** - Comprehensive REST endpoints with OpenAPI documentation
- ✅ **Responsive UI** - Material-UI React frontend with TypeScript
- ✅ **Database Design** - Properly normalized MySQL schema with JPA relationships
- ✅ **Security** - Spring Security with JWT token authentication
- ✅ **Monitoring** - Spring Boot Actuator for health checks and metrics
- ✅ **Containerization** - Docker setup for development environment

## 🛠️ Technology Stack

| Layer | Technology | Purpose |
|-------|------------|---------|
| **Frontend** | React 18 + TypeScript | Modern, type-safe UI development |
| **UI Library** | Material-UI (MUI) | Professional component library |
| **Backend** | Spring Boot 3.2 | Enterprise-grade Java framework |
| **Security** | Spring Security + JWT | Authentication and authorization |
| **Database** | MySQL 8.0 | Reliable relational data storage |
| **Cache** | Redis 7 | High-performance caching layer |
| **Real-time** | WebSocket (STOMP) | Live updates and messaging |
| **Documentation** | OpenAPI 3 (Swagger) | Interactive API documentation |
| **Containerization** | Docker & Docker Compose | Consistent development environment |

## 🏗️ Architecture

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   React App     │    │   Spring Boot    │    │     MySQL       │
│   (Port 3000)   │◄──►│   (Port 8080)    │◄──►│   (Port 3306)   │
│                 │    │                  │    │                 │
│ -  Material-UI   │    │ -  REST API       │    │ -  User Data     │
│ -  TypeScript    │    │ -  JWT Security   │    │ -  Events        │
│ -  WebSocket     │    │ -  WebSocket      │    │ -  RSVPs         │
│ -  Routing       │    │ -  JPA/Hibernate  │    │                 │
└─────────────────┘    └──────────────────┘    └─────────────────┘
                                │
                                ▼
                       ┌─────────────────┐
                       │     Redis       │
                       │   (Port 6379)   │
                       │                 │
                       │ -  Session Cache │
                       │ -  Event Cache   │
                       │ -  Rate Limiting │
                       └─────────────────┘
```

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Node.js 18+
- Maven 3.9+
- Docker & Docker Compose
- IntelliJ IDEA (recommended)

### 1. Clone the Repository
```
git clone https://github.com/yourusername/smart-event-management-platform.git
cd smart-event-management-platform
```

### 2. Start Database Services
```
docker-compose up -d
```

### 3. Run Backend
**Option A: Using IntelliJ IDEA**
1. Open project in IntelliJ
2. Right-click `SmartEventManagementApplication.java`
3. Select "Run 'SmartEventManagementApplication'"

**Option B: Using Maven**
```
mvn spring-boot:run
```

### 4. Run Frontend
```
cd frontend
npm install
npm start
```

### 5. Access Applications
- **Frontend:** http://localhost:3000
- **API Documentation:** http://localhost:8080/swagger-ui.html
- **Health Check:** http://localhost:8080/actuator/health

## 🔐 Default Test Accounts

| Role | Username | Password | Access Level |
|------|----------|----------|-------------|
| **Admin** | admin | admin123 | Full system access |
| **User** | user | user123 | Standard user operations |

## 📊 API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/signup` - User registration

### Events
- `GET /api/events/public` - List public events (cached)
- `POST /api/events` - Create event (authenticated users)
- `PUT /api/events/{id}` - Update event (admin only)
- `GET /api/events/{id}/capacity` - Get real-time event capacity

### WebSocket
- **Endpoint:** `/ws` - WebSocket connection
- **Topic:** `/topic/capacity/{eventId}` - Real-time capacity updates

## 🐳 Docker Services

The application uses Docker Compose for local development:

```
services:
  mysql:    # Port 3306
  redis:    # Port 6379
```

**Management Commands:**
```
# Start services
docker-compose up -d

# View logs
docker logs event-mysql
docker logs event-redis

# Stop services
docker-compose down
```

## 🏆 Key Learning Outcomes

This project demonstrates proficiency in:

### **Backend Development**
- **Spring Boot 3.2** with modern Java 17 features
- **Spring Security** with JWT token authentication
- **Spring Data JPA** with complex entity relationships
- **Spring Cache** with Redis integration
- **WebSocket** real-time communication
- **Spring Actuator** for monitoring and health checks

### **Frontend Development**
- **React 18** with modern hooks and functional components
- **TypeScript** for type-safe development
- **Material-UI** for professional UI components
- **React Router** for client-side routing
- **Axios** for API communication
- **WebSocket Client** for real-time updates

### **Database & Caching**
- **MySQL 8.0** with optimized schema design
- **JPA/Hibernate** ORM with proper relationships
- **Redis** caching strategies for performance
- **Database migrations** handled automatically

### **DevOps & Tooling**
- **Docker** containerization for consistent environments
- **Maven** build automation and dependency management
- **OpenAPI 3** documentation with Swagger UI
- **Development tools** with hot reload capabilities

## 📁 Project Structure

```
smart-event-management-platform/
├── src/
│   ├── main/
│   │   ├── java/com/eventmanagement/
│   │   │   ├── config/          # Configuration classes
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── dto/            # Data Transfer Objects
│   │   │   ├── entity/         # JPA entities
│   │   │   ├── repository/     # Data access layer
│   │   │   ├── security/       # Security & JWT
│   │   │   ├── service/        # Business logic
│   │   │   └── websocket/      # WebSocket config
│   │   └── resources/
│   │       ├── application.yml          # Base configuration
│   │       ├── application-dev.yml      # Development profile
│   │       └── application-prod.yml     # Production profile
│   └── test/                   # Unit and integration tests
├── frontend/
│   ├── src/
│   │   ├── components/         # Reusable React components
│   │   ├── pages/             # Page-level components
│   │   ├── services/          # API service layer
│   │   ├── types/             # TypeScript interfaces
│   │   ├── App.tsx            # Main application component
│   │   └── index.tsx          # Application entry point
│   ├── public/                # Static assets
│   ├── package.json           # Node.js dependencies
│   └── tsconfig.json          # TypeScript configuration
├── docker-compose.yml         # Container orchestration
├── pom.xml                   # Maven configuration
├── .gitignore               # Git ignore patterns
└── README.md                # This file
```

## 🧪 Testing

### Backend Testing
```
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=EventServiceTest
```

### Frontend Testing
```
cd frontend
npm test
```

## 🚀 Deployment

### Production Build
```
# Backend
mvn clean package -DskipTests

# Frontend
cd frontend
npm run build
```

### Docker Production
```
# Build production image
docker build -t smart-event-management .

# Run production container
docker run -p 8080:8080 smart-event-management
```

## 📈 Performance Features

- **Redis Caching:** Event lists cached for 5 minutes
- **Database Connection Pooling:** HikariCP for optimal performance
- **Lazy Loading:** JPA entities configured for efficient data fetching
- **WebSocket:** Real-time updates without polling overhead
- **Compression:** Production builds optimized and compressed

## 🔧 Development Tools

### **IntelliJ IDEA Configuration**
- Spring Boot run configurations included
- Maven auto-reload enabled
- Database connection profiles setup
- Docker integration configured

### **Hot Reload**
- **Backend:** Spring Boot DevTools for automatic restart
- **Frontend:** React development server with hot module replacement
- **Database:** MySQL container with persistent volume

## 🤝 Contributing

This is primarily a portfolio and learning project, but contributions are welcome!

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

