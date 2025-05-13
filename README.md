# payments-project
A simple project that contains two microservices that validate a payment transaction.

    Basically, the first microservice will send an HTTP request to the /api/payments endpoint, containing a payload with the credit card information. The data will then be saved to the database. If the message broker is unavailable, a scheduler will be responsible for periodically checking the bank to check for payments that are still marked as pending.

    After saving to the bank, the microservice publishes a message to a queue, which will be monitored by a second microservice. This will listen for messages in the queue and, upon receiving one, will process the transaction. During processing, it will check whether the card data corresponds to one of the 10 fictitious cards previously registered, and whether there is a limit available for the transaction.

    At the end of processing, if the result is APPROVED or REJECTED, the second microservice will publish a message to a response topic, which will be consumed by the first microservice to update the payment status in its database.

Simple Architecture
![Captura de tela 2025-05-11 231343](https://github.com/user-attachments/assets/dd2dafd9-3c51-44d5-8fc9-c77b0bf0d66e)
