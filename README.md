# Appointment Booking System

A full-stack appointment scheduling platform where customers can book time slots with approved service providers. Built using **Spring Boot** for the backend and **React** for the frontend (currently in development).

---

## 📌 Features

- Customer and Provider registration with role-based access
- Admin approval workflow for provider onboarding
- JWT-based secure authentication
- Browse providers by business category
- Book, edit, and cancel appointments
- Standardized API responses using DTOs
- Modular backend structure using Spring Boot

---

## 🛠️ Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA (Hibernate)
- MySQL
- Maven

### Frontend (In Progress)
- React.js (with Redux Toolkit)
- Axios
- React Router
- Tailwind CSS

---

## 📁 Project Structure

appointment-booking-system/
├── backend/
│ ├── controller/
│ ├── service/
│ ├── model/
│ ├── repository/
│ ├── dto/
│ ├── security/
│ └── util/
├── frontend/ (coming soon)
└── README.md

yaml
Copy
Edit

---

## 🚀 Getting Started

### Backend Setup

```bash
# Navigate to backend
cd backend

# Add DB credentials in src/main/resources/application.properties

# Run the application
mvn spring-boot:run
Frontend Setup (Coming Soon)
The frontend is currently under development and will be available shortly.

🧪 API Examples
Method	Endpoint	Description
POST	/api/auth/register	Register as user/provider
POST	/api/auth/login	Login and receive JWT
GET	/api/providers	View approved providers
POST	/api/appointments	Book an appointment
PUT	/api/appointments/{id}	Edit an appointment
DELETE	/api/appointments/{id}	Cancel an appointment

📦 Deployment
The complete application (backend + frontend) will be deployed soon on platform such as:

Render (Backend)

Vercel  (Frontend)

🙋‍♂️ Author
Sreeharinaidu Rangani



