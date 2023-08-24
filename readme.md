\# Asana and Airtable Integration

This repository contains a Spring Boot application that facilitates the
integration between Asana and Airtable. With this integration, you can
automatically create records in an Airtable table whenever new tasks are
created in Asana.

\## Prerequisites

\- Java 8 or higher

\- Asana account and access token

\- Airtable account and API key

\## Setup and Usage

1\. \*\*Clone the Repository:\*\*

\`\`\`sh

git clone https://github.com/yourusername/asana-airtable-integration.git

cd asana-airtable-integration

\`\`\`

2\. \*\*Configure Application Properties:\*\*

Open the \`src/main/resources/application.properties\` file and replace
the placeholders with your actual credentials:

\`\`\`properties

asana.access.token=1/1205324555686787:2989D1BA48D8BA1588F0FD153C7DD428

airtable.api.key=PATU3WG9VJNBQKROZ.82E2482EA1E3E1DF687E70DD3DA8580563C010CC9B75B3CBB12248B93E82CE7B

\`\`\`

3\. \*\*Customize the Code:\*\*

Open \`src/main/java/com/java/integration/service/WebhookService.java\`
and customize the code to parse task data from the incoming webhook and
adjust any data transformation as needed.

4\. \*\*Build and Run the Application:\*\*

Build and run the application using the provided Maven wrapper:

\`\`\`sh

./mvnw spring-boot:run

\`\`\`

5\. \*\*Set Up Asana Webhook:\*\*

\- In your Asana project settings, create a webhook that points to your
deployed service\'s endpoint (e.g., \`http://your-server-url/webhook\`).

6\. \*\*Create New Task in Asana:\*\*

\- When a new task is created in Asana, a webhook notification will be
sent to your service.

\- The service will extract task information and create a corresponding
record in the specified Airtable table.

\## Error Handling

\- The service includes basic error handling and logging for creating
records in Airtable and handling HTTP errors.

\## Contributing

Contributions are welcome! If you find any issues or improvements, feel
free to submit a pull request.

\## License

This project is licensed under the MIT License. See the
\[LICENSE\](LICENSE) file for details.
