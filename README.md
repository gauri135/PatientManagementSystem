__Patient Management System__
    
Welcome to the **Patient Management System**, a powerful and scalable RESTful API built with Spring Boot for managing patient records in a healthcare ecosystem. 
Hosted at https://github.com/gauri135/PatientManagementSystem, this project offers robust CRUD operations,
gRPC integration with a billing service, and advanced features like validation, logging, and interactive API documentation via Swagger.

🚀 Features

Patient CRUD Operations: Create, retrieve, update, and delete patient records seamlessly.
Input Validation: Custom validation groups ensure data integrity.
gRPC Integration: Connects with a billing service for efficient operations.
API Documentation: Interactive Swagger UI for exploring and testing endpoints.
Logging: Comprehensive SLF4J logging for monitoring and debugging.
Scalable Design: Built for extensibility and high performance.

🛠 Tech Stack

Language: Java 17
Framework: Spring Boot 3.x
API: RESTful with JSON
Dependencies:
Spring Web (REST API)
Spring Validation (Data validation)
OpenAPI/Swagger (API documentation)
SLF4J (Logging)
gRPC (Billing service client)


Build Tool: Maven
Testing: JUnit (planned for future releases)

📂 Project Structure
com.app.pm.patientService
├── controller
│   └── PatientController.java       # REST API endpoints
├── dto
│   ├── PatientRequestDTO.java       # Input DTO for patient data
│   ├── PatientResponseDTO.java      # Output DTO for patient data
│   └── validators
│       └── CreatePatientValidationGroup.java # Custom validation
├── service
│   └── PatientService.java          # Business logic layer
└── grpc
    └── BillingServiceGrpcClient.java # gRPC client for billing

⚙️ Getting Started
Prerequisites

Java 17 or higher
Maven 3.8+
gRPC server for billing service (ensure it's running)
IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)

Installation

Clone the Repository:
git clone https://github.com/gauri135/PatientManagementSystem.git
cd PatientManagementSystem


Build the Project:
mvn clean install


Run the Application:
mvn spring-boot:run


Access the API:

Base URL: http://localhost:8080/patients
Swagger UI: http://localhost:8080/swagger-ui.html
Swagger JSON: http://localhost:8080/v3/api-docs



📡 API Endpoints



Method
Endpoint
Description



GET
/patients
Fetch all patients


POST
/patients
Create a new patient


PUT
/patients/{id}
Update a patient by UUID


DELETE
/patients/{id}
Delete a patient by UUID


Example Requests
Create a Patient
curl -X POST http://localhost:8080/patients \
-H "Content-Type: application/json" \
-d '{"name":"Jane Doe","age":28,"email":"jane.doe@example.com"}'

Fetch All Patients
curl http://localhost:8080/patients

🔍 Logging

Info Logs: Capture high-level operation details (e.g., "Fetching list of patients").
Debug Logs: Provide granular insights into data (e.g., patient DTOs).
Configurable via application.properties or logback-spring.xml.

✅ Validation

Custom Validation: CreatePatientValidationGroup enforces strict rules for new patient data.
Default Validation: Applied to all operations for consistency.

🌟 Why This Project?

Production-Ready: Adheres to best practices for scalability and maintainability.
Developer-Friendly: Clear documentation, intuitive API design, and interactive Swagger UI.
Extensible: Easily integrates with other microservices or databases.

🤝 Contributing
We welcome contributions! Follow these steps:

Fork the repository.
Create a feature branch: git checkout -b feature/your-feature.
Commit changes: git commit -m "Add your feature".
Push to the branch: git push origin feature/your-feature.
Open a pull request.

Please read our Contributing Guidelines for more details.
📜 License
This project is licensed under the MIT License. See the LICENSE file for details.
📬 Contact

Maintainer: Gauri (gauri135)
Issues: Open a ticket on GitHub Issues
Email: Reach out via [rokadegauri56@gmail.com]


⭐ Star this repository on GitHub to support the project!Happy coding! 🚀
