# IT Support Ticket Management System

💻 **IT Support Ticket Management System** is a Java-based application designed to help employees report and track IT issues efficiently. The system supports role-based access, ticket creation, status tracking, and audit logging.

---

## 📋 Features

### **1. Ticket Management**
- **Create Tickets**: Employees can create tickets with the following details:
    - Title
    - Description
    - Priority: Low, Medium, High
    - Category: Network, Hardware, Software, Other
    - Creation Date: Automatically set
- **Status Tracking**: Tickets can have the following statuses:
    - **New**: Default status when created
    - **In Progress**: Updated by IT support
    - **Resolved**: Updated by IT support

### **2. User Roles**
- **Employees**:
    - Create and view their own tickets.
- **IT Support**:
    - View all tickets.
    - Change ticket statuses.
    - Add comments to tickets.

### **3. Audit Log**
- Track changes to ticket statuses.
- Record comments added to tickets.

### **4. Search & Filter**
- Search tickets by:
    - Ticket ID
    - Status

---

## ⚙️ Technology Stack

### **Backend**
- **Java 17**
- **Spring Boot**: For building RESTful APIs.
- **Swagger/OpenAPI**: API documentation.

### **Database**
- **Oracle SQL**: Database for storing tickets, users, and audit logs.
- **Schema**: Provided as an SQL script.

### **User Interface (UI)**
- **Java Swing**: For the desktop-based UI.
- **Layout Managers**: MigLayout or GridBagLayout for responsive design.

---

## 🚀 Getting Started

### **Prerequisites**
- Java 17 JDK installed.
- Oracle SQL database set up.
- Maven for dependency management.

### **Setup Instructions**

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/IT-Support-Ticket-System.git
   cd IT-Support-Ticket-System
   ```
2. **Database Setup**:


Run the provided SQL script (schema.sql) to create the database schema.

Update the application.properties file with your Oracle database credentials:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=your-username
spring.datasource.password=your-password
```

3. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the UI**:
Launch the Java Swing application from the UI module.

Use the following credentials for testing:

- Employee: employee@example.com / password123
- IT Support: itsupport@example.com / password123

📖 Usage

For Employees
  - Log in with your credentials.

- Create a new ticket by filling in the required details.

- View your tickets and their current status.

For IT Support
- Log in with your credentials.

- View all tickets.

- Update ticket statuses and add comments.

- Search and filter tickets by ID or status.

📂 Project Structure

📁 **src/main/java**
   ```bash

IT-Support-Ticket-System/
├── backend/               # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/      # Java source code
│   │   │   └── resources/ # Configuration files
│   │   └── test/          # Unit tests
│   └── pom.xml            # Maven build file
├── ui/                    # Java Swing UI
│   ├── src/
│   │   └── main/          # UI source code
│   └── pom.xml            # Maven build file
├── database/              # Database scripts
│   └── schema.sql         # SQL schema script
├── README.md              # Project documentation
└── .gitignore             # Git ignore file
   ```
📄 API Documentation
The backend API is documented using Swagger/OpenAPI. After running the application, access the API docs at:
   ```bash
http://localhost:8080/swagger-ui.html
   ```
🛠️ Future Enhancements
- Add email notifications for ticket updates.

- Implement a web-based UI using JavaFX or a modern frontend framework.

- Add support for file attachments in tickets.

🤝 Contributing
Contributions are welcome! Follow these steps:

Fork the repository.

- Create a new branch (git checkout -b feature/your-feature).

- Commit your changes (git commit -m "Add your feature").

- Push to the branch (git push origin feature/your-feature).

- Open a pull request.

📜 License
This project is licensed under the MIT License. See the LICENSE file for details.

🙏 Acknowledgments
Thanks to Spring Boot and Oracle SQL for providing the tools to build this system.



