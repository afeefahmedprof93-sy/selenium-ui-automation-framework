# 🍊 OrangeHRM UI Automation Framework

A robust, production-ready **Selenium + TestNG** automation framework for testing the [OrangeHRM](https://opensource-demo.orangehrmlive.com) web application. Built with clean architecture principles, including the **Page Object Model (POM)**, **ThreadLocal WebDriver** for parallel execution, **Allure reporting**, and **Log4j2 logging**.

---

## 📋 Table of Contents

- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Framework Architecture](#-framework-architecture)
- [Prerequisites](#-prerequisites)
- [Setup & Installation](#-setup--installation)
- [Configuration](#-configuration)
- [Running Tests](#-running-tests)
- [Test Reporting](#-test-reporting)
- [Key Design Decisions](#-key-design-decisions)

---

## 🛠 Tech Stack

| Tool / Library         | Version   | Purpose                                      |
|------------------------|-----------|----------------------------------------------|
| Java                   | 21        | Programming language                         |
| Maven                  | 3.x       | Build tool & dependency management           |
| Selenium WebDriver     | 4.27.0    | Browser automation                           |
| TestNG                 | 7.10.2    | Test execution framework                     |
| WebDriverManager       | 5.9.2     | Automatic browser driver management          |
| Allure TestNG          | 2.27.0    | Test reporting                               |
| Log4j2                 | 2.24.3    | Logging                                      |

---

## 📁 Project Structure

```
OrangeHRMAutomation/
│
├── config/
│   └── config.properties          # Browser, URL, credentials, timeouts
│
├── src/
│   ├── main/java/com/orange/automation/
│   │   ├── base/
│   │   │   └── BasePage.java          # Parent for all Page classes; shared step logging
│   │   ├── pageobjects/
│   │   │   └── LoginPage.java         # Page Object for the Login page
│   │   └── utils/
│   │       ├── AllureReport.java      # Programmatic Allure report generation
│   │       ├── ConfigReader.java      # Reads config.properties (singleton utility)
│   │       ├── DriverManager.java     # ThreadLocal WebDriver initialization & teardown
│   │       ├── Listeners.java         # TestNG listener for pass/fail tracking
│   │       ├── PageManager.java       # PageFactory initializer for all page objects
│   │       └── ScreenshotUtil.java    # Captures & attaches screenshots to Allure
│   │
│   ├── main/resources/
│   │   └── log4j2.xml                 # Log4j2 configuration
│   │
│   └── test/java/com/orange/automation/
│       ├── base/
│       │   └── BaseTest.java          # Parent for all Test classes; setup & teardown
│       └── testcases/
│           └── LoginPageTest.java     # Test cases for Login functionality
│
├── test-suites/
│   └── smoketest.xml              # TestNG suite definition for smoke tests
│
└── pom.xml                        # Maven dependencies & build config
```

---

## 🏗 Framework Architecture

```
BaseTest (@BeforeMethod / @AfterMethod)
    └── DriverManager (ThreadLocal WebDriver)
    └── ConfigReader (config.properties)

Test Classes (e.g., LoginPageTest)
    └── PageManager (PageFactory)
        └── Page Objects (e.g., LoginPage)
            └── BasePage (stepPassed / stepFailed)
                └── ScreenshotUtil (auto screenshot on failure)
                └── Allure (step logging in report)

Listeners (TestNG ITestListener)
    └── Tracks pass/fail counts
    └── Triggers stepFailed on assertion errors

AllureReport (@AfterSuite)
    └── Auto-generates & opens Allure HTML report
```

---

## ✅ Prerequisites

Before running the project, make sure you have the following installed:

- **Java 21+** — [Download here](https://adoptium.net/)
- **Maven 3.6+** — [Download here](https://maven.apache.org/download.cgi)
- **Google Chrome** (default browser) — or Firefox / Edge
- **Allure CLI** (for report generation) — [Install guide](https://allurereport.org/docs/install/)

Verify your setup:
```bash
java -version
mvn -version
allure --version
```

---

## ⚙️ Setup & Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/afeefahmedprof93-sy/selenium-ui-automation-framework.git
   cd selenium-ui-automation-framework/OrangeHRMAutomation
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install -DskipTests
   ```

> **Note:** WebDriverManager automatically downloads the correct ChromeDriver — no manual driver setup needed.

---

## 🔧 Configuration

All test configuration is managed via `config/config.properties`:

```properties
# Browser Configuration (chrome | firefox | edge)
browser=chrome

# Application URL
url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login

# Test Credentials
username=Admin
password=admin123

# Timeouts (in seconds)
implicit.wait=10
explicit.wait=15
```

To switch browsers, simply change the `browser` property — no code changes required.

---

## ▶️ Running Tests

**Run the full smoke test suite:**
```bash
mvn clean test
```

**Run a specific test class:**
```bash
mvn clean test -Dtest=LoginPageTest
```

**Run with a specific TestNG XML suite:**
```bash
mvn clean test -DsuiteXmlFile=test-suites/smoketest.xml
```

---

## 📊 Test Reporting

This framework uses **Allure** for rich HTML test reports. Reports are `auto-generated` after each suite run via `AllureReport.java`.

**To manually generate and open the report:**
```bash
# Generate report from raw results
allure generate allure-results -o allure-report --clean

# Open in browser
allure open allure-report
```

Each Allure report includes:
- ✅ Pass / ❌ Fail status per test
- Step-by-step execution log
- Screenshots automatically attached on failures
- Severity levels (`CRITICAL`, `NORMAL`, etc.)
- Epic → Feature → Story hierarchy

---

## 💡 Key Design Decisions

**ThreadLocal WebDriver (`DriverManager`)**
Each test thread gets its own isolated `WebDriver` instance, enabling safe parallel test execution without driver conflicts.

**Page Object Model**
All UI interactions are encapsulated in dedicated page classes under `pageobjects/`. Tests never interact with Selenium directly — they call clean, readable page methods.

**`PageManager` with `PageFactory`**
All page objects are initialized via `PageFactory.initElements()`, keeping `@FindBy` annotations clean and managed in a single place.

**`ConfigReader` (Static Utility)**
Loaded once via a static block, `ConfigReader` provides type-safe access to all config values across the framework without repeated file I/O.

**`BasePage` — Centralized Step Logging**
`stepPassed()` and `stepFailed()` in `BasePage` handle Log4j2 logging, Allure step annotation, and screenshot capture in one place — every page action reports itself automatically.

**Auto-screenshot on Failure**
`ScreenshotUtil` uses Allure's `@Attachment` annotation to automatically embed a PNG screenshot into the report whenever a test step fails.

---

## 📌 Test Coverage

| Module         | Test Case       | Description                        | Severity |
|----------------|-----------------|------------------------------------|----------|
| Authentication | `Login with valid credentials`   | Enter Admin/admin123 → verify redirect to dashboard      | CRITICAL |
| Authentication | `Login with invalid credentials`   | Enter wrong username/password → verify error message      | CRITICAL |
| Authentication | `Login with empty fields`   | Submit blank form → verify required field validation       | HIGH |

---

## 👤 Author & Collaborators

**Afeef Ahmed**

**Chowdhury MD Intiser Ali**
- GitHub: [@afeefahmedprof93-sy](https://github.com/afeefahmedprof93-sy)
