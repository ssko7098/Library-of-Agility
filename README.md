# Library of Agility

Welcome to the Library of Agility, developed as part of the SOFT2412 Agile Software Development Project. This system allows users to manage digital scrolls (binary files) through a command-line interface (CLI) with features for both general users and administrators.

## Admin Login Details
* username = `admin`  
* password = `123`

## Table of Contents
- [System Overview](#system-overview)
- [Features](#features)
  - [User Management](#user-management)
  - [Digital Scroll Management](#digital-scroll-management)
  - [Admin Dashboard](#admin-dashboard)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
  - [General Users](#general-users)
  - [Admin Users](#admin-users)
- [Contributing](#contributing)
- [License](#license)

## System Overview
The Virtual Scroll Access System (VSAS) is a digital repository for storing and accessing scrolls (binary files). It offers user management and scroll management features for both general users and administrators. This version of the application runs entirely through a command-line interface (CLI), making it easy to operate from any terminal environment.

## Features

### User Management
- **Registration and Login**: Users can create accounts with personal information (name, email, phone number, etc.). All accounts are locked behind a username and password, with no two accounts having the same ID key.
- **Guest Users**: Guests can browse scrolls without logging in but cannot upload or download scrolls.
- **Admin Users**: Admins have special privileges, such as managing users and viewing application statistics.
- **Profile Management**: Users can update their profiles and change passwords after logging in.
- **Password Encryption**: Passwords are stored securely using a hashing algorithm.

### Digital Scroll Management
- **Add New Scrolls**: Users can upload binary files as digital scrolls, each with a unique ID and name.
- **Edit Scrolls**: Users can modify the scrolls they uploaded but cannot edit others’ scrolls.
- **Remove Scrolls**: Users can delete their uploaded scrolls.
- **View and Download Scrolls**: Users can browse available scrolls and download them from the platform.
- **Search and Filter**: Scrolls can be filtered by uploader, scroll ID, name, and upload date.

### Admin Dashboard
- **View All Users**: Admins can view the list of all registered users and their profiles.
- **Manage Users**: Admins can add or delete users and create new admins.
- **View Statistics**: Admins can track the number of downloads and uploads for each scroll.

## Setup and Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/ssko7098/Library-of-Agility.git
   ```
2. Navigate to the project directory:
   ```bash
   cd Library-of-Agility
   ```
3. Build the application using Gradle:
   ```bash
   gradle build
   ```
4. Run the application:
   ```bash
   gradle run
   ```

## Usage

### General Users
- **Register/Login**: Use the command line to create an account or log in.
- **Browse Scrolls**: View available scrolls and download them to your local machine.
- **Manage Profile**: Update your profile or change your password.

### Admin Users
- **Manage Users**: Add or delete users, and promote users to admins.
- **Track Scrolls**: View download and upload statistics for each scroll.
- **Manage Scrolls**: Admins can perform all scroll-related operations like regular users.

## Contributing
We welcome contributions to improve this CLI tool! Feel free to fork the repository and submit a pull request.

## License
This project is licensed under the MIT License.
