# Trello API Testing Project

Welcome to the Trello API testing project! This project is designed for testing Trello's API using Java, Cucumber, Rest Assured, and the Singleton design pattern. It is built using Gradle for easy dependency management and task execution.

## Overview

This project aims to provide a comprehensive testing framework for Trello's API endpoints. It utilizes Java for backend development, Cucumber for behavior-driven development (BDD) testing, Rest Assured for API testing, and the Singleton design pattern for efficient object creation.

## Getting Started

To get started with running the tests, follow these steps:

1. **Clone the Repository:**
    - Clone this repository to your local machine.

2. **Create `key.properties` File:**
    - Create a file named `key.properties` in the root directory of the project.
    - Add the following variables to the `key.properties` file:
      ```properties
      api.token = your_api_token
      api.key = your_api_key
      ```
    - Replace `your_api_token` and `your_api_key` with your actual Trello API token and key.

3. **Run the Tests:**
    - Use the following Gradle command to run the tests:
      ```bash
      ./gradlew clean executeFeatures -PenvId="your_environment_id" -PcucumberOptions="@your_feature_option"
      ```
    - Replace `your_environment_id` with the ID of your environment, and `your_feature_option` with the desired feature option you want to run.

## Contributing

If you would like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them.
4. Push your changes to your fork.
5. Submit a pull request.

## Issues

If you encounter any issues or have suggestions for improvements, please open an issue on the GitHub repository.

## Acknowledgments

- [Cucumber](https://cucumber.io/) for behavior-driven development testing.
- [Rest Assured](https://rest-assured.io/) for API testing.
- [Gradle](https://gradle.org/) for dependency management and task execution.
- Trello for providing a comprehensive API for testing.

Happy testing!
