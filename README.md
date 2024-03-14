# Posting Application

## Overview

In Project 5, we've developed a user-friendly GUI application that allows individuals to share their thoughts and ideas on their own account, similar to a social networking platform. This application supports multiple users simultaneously, enabling them to create, edit, or delete their postings and manage their accounts. 

### Key Features:

- **Multi-user Support:** Multiple users can interact with the application concurrently.
- **Account Management:** Users can create an account, log in, change account details, and delete their account.
- **Post Creation and Management:** Users can post their thoughts, edit or delete their posts.
- **Data Persistence:** All data is stored and preserved in `Accounts.txt` and `Posts.txt`, ensuring data availability even after server downtime.

### Adaptations for Two-Person Teams:

Given the two-member team structure, the CS 18000 Course Coordinators have tailored the project requirements:
- Only the core requirements for the chosen option are necessary.
- Network IO is optional, assuming all users interact via the same machine.
- All other requirements remain unchanged.

## Getting Started

### Prerequisites

- Java Runtime Environment (JRE) and Java Development Kit (JDK).

### How to Compile

To enhance user experience, start the application by running `User.java`. The application uses "localhost" for network communication, and the port number is predetermined, removing the need for manual configuration.

## Class Descriptions

- **Account.java:** Manages account creation and modification. Ensures unique account names and non-null credentials.
- **Post.java:** Facilitates post creation and modification, keeping authorship and account names separate to handle posts from deleted accounts gracefully.
- **Server.java:** The backend logic handling data storage, account and post management, and security checks.
- **User.java:** Acts as the client interface, handling user interactions, login procedures, and UI dynamics. Incorporates `ActionListener` for responsive design.

## Test Cases

We employ `Tests.md` to simulate and document user interactions, detailing various scenarios and outcomes. Screenshots are provided to aid comprehension.

## Contributing

We welcome contributions! Please refer to our [contributing guidelines](CONTRIBUTING.md) for more details.

## License

This project is licensed under the [MIT License](LICENSE.md).

## Acknowledgments

- CS 18000 Course Coordinators for guidance and project adaptations.
- Our fellow students for their feedback and support.

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://java.com)

---

For more details, visit our [GitHub repository](https://github.com/jaihyunkee/Project5).
