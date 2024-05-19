# Form Filler

Form Filler is an Android application built using Kotlin that leverages generative AI models to automate the process of filling out Google Forms and generating responses. Additionally, it offers advanced features such as text summarization, image and text context analysis using Gemini Vision Pro, and multi-turn chatting with a virtual assistant.

## Features

- Google Forms Automation: Automatically fill out and submit Google Forms using AI-powered responses.
- Text Summarization: Generate concise summaries for large texts to quickly grasp the main points.
- Image and Text Context Analysis: Provide detailed context and insights for both image and text inputs using Gemini Vision Pro.
- Multi-Turn Chatting: Engage in multi-turn conversations with a virtual assistant for enhanced interaction and support.

## Images

![Screenshot of Form Filler](images/example.jpg)

## Dependencies

This project utilizes the following dependencies:

- Material3: For creating a visually appealing and modern UI.
- Dagger-hilt: For dependency injection
- Datastore-preferences: To store simple key-value pairs asynchronously using Kotlin coroutines.
- Accompanist: Helps in controlling the system UI elements such as status bar and navigation bar.
- Firebase-auth-ktx: Firebase Authentication for managing user authentication.
- Play-services-auth: Facilitates integration with Google Sign-In.
- Coil-compose: An image loading library for Android backed by Kotlin Coroutines, optimized for Jetpack Compose.
- Generativeai: To leverage generative AI capabilities for text and image processing.
- Jsoup: For working with real-world HTML, enabling extraction and manipulation of data from web pages.

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/rishabh379/formfiller.git
    cd formfiller
    ```

2. Open the project in Android Studio.

3. Sync the project to download all dependencies.

4. Set up environment variables and configuration files as needed.

## Usage

1. Google Forms Automation:
    - Fetch questions from a Google Form using the Gemini API and generate responses accordingly.
    - The application will automatically fill out the form using AI-generated responses.

2. Text Summarization:
    - Use the built-in functionality to summarize large texts within the app.

3. Image and Text Context Analysis:
    - Analyze text and images using Gemini Vision Pro to provide detailed context and insights.

4. Multi-Turn Chatting:
    - Interact with the virtual assistant within the app for enhanced user experience and support.

## Contributing

We welcome contributions to enhance Form Filler! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch:
    ```bash
    git checkout -b feature/your-feature-name
    ```
3. Make your changes and commit them:
    ```bash
    git commit -m "Add your message"
    ```
4. Push to the branch:
    ```bash
    git push origin feature/your-feature-name
    ```
5. Open a pull request on GitHub.

## Authors

Form Filler is developed by [Rishabh Sharma](https://github.com/rishabh379) and [Avula Puneeth Kumar Reddy](https://github.com/avulapuneethkumarreddy).

## Contact

For any questions or feedback, please open an issue on GitHub or contact at pvsrishabh@gmail.com.

---

Thank you for using Form Filler! We hope this tool enhances your productivity and efficiency with Google Forms and AI-powered functionalities.