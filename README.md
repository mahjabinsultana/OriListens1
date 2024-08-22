# OriListens : A Mental Health Counselling Application using Natural Language Processing
This repository contains the code for the android application "OriListens" and chatbot named "Ori". It is a mental health counselling app that provides users various resources on mental health issues and has a well-responsive chatbot that suggest resources relevant to the users. 

## Features
The application starts with a simple splash screen, login and signup page. There are three main sections of the application : 

- **The video resources section:** This section contains video resources on various mental health issues. There are separate folder for each issues. The resources are stored on Firebase Real-time Database.
- **The chatbot section:** The chatbot section has a chatbot named "Ori" that uses Natural Language Processing Techniques to understand users' messages and generates response accordingly.
- **The contact section:** This section shows some important contact information of various mental health organizations. 
 
## Tools Used
This mental health counselling system combines various tools and technologies including:

- **Android Studio:** The Android Studio Dolphine Version 2021.3.1 is used to develop the android application.
- **Java:** Java is used as programming language for developing the application.
- **Visual Studio Code:** The VS Code 1.92.2 is used to build the chatbot.
- **Python:** Python 3 programming language is used to write code for the chatbot.
- **Libraries and Modules used:** Retrofit, Numerical Python(NumPy), Natural Language Toolkit(NLTK), TensorFlow, Pickle. 

## Installation Guide

1. Clone the repository.
2. Open the "Chatbot Files" folder in Visual Studio Code.
3. Open the "main.py" and replace the host ip (line 111) with your own ip. Then run "main.py". The chatbot will start working on your local server.
4. Open the "OriListens Application" project in Android Studio.
5. Update the host ip in "net_security_config.xml" file.
6. Update the url and BASE_URL (line 106, 107) in the "ChatbotActivity" file.
7. Build and run the application.

## Connecting a Firebase Account

All the resources of the application must be stored on Firebase Real-time Database. The steps to follow are:

1. Go to your Firebase Console and click on "Add Project". Follow the instruction given in the Firebase website.
2. Click on the Android icon to add an Android application. Register the application with package name found in "AndroidManifest.xml file). Download the google-services.json file.
3. Move the downloaded file in the root directory of the application. Add "classpath 'com.google.gms:google-services:4.3.8'" in build.gradle file.
4. Now, on Firebase Console go to the Realtime Database and Storage and update the rules.
5. The resources in the database must follow below structure:
<img src = "" />
