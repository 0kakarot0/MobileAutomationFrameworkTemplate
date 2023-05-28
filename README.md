# 📱 MobileAutomationFrameworkTemplate

The MobileAutomationFrameworkTemplate is a modern and comprehensive Maven-based Java project for mobile test automation. It combines powerful libraries and frameworks to provide a robust and efficient automation solution.

![Framework Logo](https://github.com/0kakarot0/MobileAutomationFramewokTemplate/blob/master/light.png)

[![GitHub issues](https://img.shields.io/github/issues/0kakarot0/MobileAutomationFramewokTemplate)](https://github.com/0kakarot0/MobileAutomationFramewokTemplate/issues)
[![GitHub forks](https://img.shields.io/github/forks/0kakarot0/MobileAutomationFramewokTemplate)](https://github.com/0kakarot0/MobileAutomationFramewokTemplate/network)
[![GitHub stars](https://img.shields.io/github/stars/0kakarot0/MobileAutomationFramewokTemplate)](https://github.com/0kakarot0/MobileAutomationFramewokTemplate/stargazers)
[![GitHub license](https://img.shields.io/github/license/0kakarot0/MobileAutomationFramewokTemplate)](https://github.com/0kakarot0/MobileAutomationFramewokTemplate/blob/master/LICENSE)

## 🌟 Features

- 💻 Selenium - Integration with Selenium WebDriver library for web-based automation in mobile testing.
- 📱 Appium - Seamless integration with the Appium library for cross-platform mobile automation (Android and iOS).
- 🧪 TestNG - Utilizes the TestNG testing framework for structured test organization, reporting, and parallel execution capabilities.
- 📈 Extent Report - Generates detailed and visually appealing HTML reports using the Extent Report library, providing insights into test execution results.
- 📧 javax.mail - Incorporates the javax.mail library for sending automated email notifications with test reports or relevant information.
- 🔧 Maven - Built on the Maven project structure, making it easy to manage dependencies and build automation projects.

[//]: # (![Framework Architecture]&#40;https://example.com/framework-architecture.png&#41;)
## 🚀 Framework Architecture
````
├───apps                 <!-- Directory for storing mobile app files -->
├───src
│   ├───main             <!-- Main source code directory -->
│   │   ├───java         <!-- Java source code directory -->
│   │   │   ├───pages    <!-- Page object classes representing different app screens -->
│   │   │   │   ├───calculator
│   │   │   │   └───logout
│   │   │   └───utils    <!-- Utility classes for common functionalities -->
│   │   │       ├───commonComponents
│   │   │       │   ├───buttonActions
│   │   │       │   ├───checkBoxs
│   │   │       │   ├───dropDowns
│   │   │       │   ├───editTextFields
│   │   │       │   ├───notifications
│   │   │       │   ├───scrollMethods
│   │   │       │   └───validationMessages
│   │   │       └───seleniumUtils
│   │   └───resources   <!-- Resource files -->
│   │       └───screenShots
│   └───test             <!-- Test source code directory -->
│       ├───java         <!-- Java test classes -->
│       │   ├───testBase
│       │   ├───tests
│       │   └───utils
│       │       ├───email
│       │       ├───fileReader
│       │       ├───fileWriter
│       │       ├───listener
│       │       ├───reporter
│       │       └───server
│       └───resources    <!-- Test resource files -->
│           ├───reports
│           └───testDataFiles


````



## 🚀 Getting Started

To get started with the MobileAutomationFrameworkTemplate:

1. **Clone the Repository:** Start by cloning the MobileAutomationFrameworkTemplate repository to your local machine using the following command:
```git clone https://github.com/0kakarot0/MobileAutomationFramewokTemplate.git```
2. **Open the Project:** Open your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse) and import the project by selecting the root directory where you cloned the repository.

3. **Set Up Dependencies:** The project is built using Maven, so it will automatically download the necessary dependencies. However, ensure that you have Maven installed on your machine. If not, you can download it from the official Maven website and follow the installation instructions.
4. Update the necessary configuration files, such as `configuration.properties` and `emailConfiguration.properties`, with relevant settings.
5. Customize the framework as per your project requirements by adding test cases, modifying page objects, and extending utility classes.
6. Run the tests using TestNG, and the Extent Report will be generated automatically with detailed test execution results.
7.  **Customize the Framework:** Customize the framework as per your project requirements. You can add your own test cases, modify the existing page objects, extend utility classes, or add additional utility classes specific to your needs.

The project structure provides a logical organization for your automation project:
- `pages`: Contains page object classes representing different app screens.
- `utils`: Contains utility classes for common functionalities and seleniumUtils for Selenium-specific utilities.
- `tests`: Contains test classes with TestNG annotations for executing test cases.
- `resources`: Contains configuration files (`configuration.properties`, `emailConfiguration.properties`) and other resource files.

## 🎯 Conclusion

The MobileAutomationFrameworkTemplate offers a modern and feature-rich foundation for mobile test automation. Its integration with Selenium, Appium, TestNG, Extent Report, and javax.mail libraries, combined with its structured approach and utilities, makes it an ideal choice for building scalable and efficient mobile automation projects.

For more details, refer to the [project repository](https://github.com/0kakarot0/MobileAutomationFramewokTemplate).

![Framework Demo](https://example.com/framework-demo.gif)

## 📝 License

This project is licensed under the terms of the MIT license. See the [LICENSE](https://github.com/0kakarot0/MobileAutomationFramewokTemplate/blob/master/LICENSE) file for details.

## 👤 Author

**Ahtisham Ilyas**

- GitHub: [@0kakarot0](https://github.com/0kakarot0)
<!-- - Twitter: [@yourhandle](https://twitter.com/yourhandle) -->
- LinkedIn: [Ahtisham Ilyas](https://www.linkedin.com/in/ahtisham-ilyas-62193768/)

