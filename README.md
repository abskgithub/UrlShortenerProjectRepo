---URL Shortener Service---
This project implements a simple URL shortener service with a RESTful API. The service provides functionality to shorten URLs, 
retrieve original URLs from shortened ones, and track metrics related to the most frequently shortened domains.

Features : ---
1. URL Shortening:
Accepts a URL as input and returns a shortened version. If the same URL is shortened multiple times, the same shortened URL is returned.
URLs and their shortened versions are stored in-memory by the application.

2. Redirection:
Redirects users from the shortened URL to the original URL.

3. Metrics:
Provides a list of the top 3 most shortened domain names, along with the number of times they have been shortened.

Prerequisites for running the service : ---
1. Java 17 or above
2. Maven 3.9.9 or above
3. Postman

How to Run the Application : ---
1. Clone the repository.
2. Navigate to the project directory.
3. Build the project using Maven:
command : mvn clean install
4. Run the application:
command : mvn spring-boot:run

APIs : ---
1. Generate Short URL

Description: Accepts a URL and returns a shortened URL.
Request:
curl --location 'http://localhost:8080/api/shorten' \
--header 'Content-Type: text/plain' \
--data 'https://www.netflix.com/'

Import the Curl in Postman and hit the api.
Response: A shortened URL, e.g., http://localhost:8080/api/YiyCsz

2. Redirect to Original URL

Endpoint: GET /api/{shortURL}
Description: Redirects the request to the original URL.
Url : http://localhost:8080/api/YiyCsz
Copy the Url and put it into browser, it will redirect to orignal Url.

3. Retrieve Original URL

Endpoint: GET /api/original/{shortURL}
Description: Returns the original URL corresponding to the given shortened URL.
Request:
curl --location 'http://localhost:8080/api/original/YiyCsz'

Import the Curl in Postman and hit the api.
Response: original url

4. Get Metrics
Endpoint: GET /api/metrics
Description: Returns the top 3 most shortened domain names and the number of times each has been shortened.
Request:
curl --location 'http://localhost:8080/api/metrics'

Import the Curl in Postman and hit the api.
Response: As expected

For checking the data through console : ---
1. Put the url 'http://localhost:8080/h2-console' into the browser and enter.
2. Use the username and password, provided in the application.properties file.
3. run "Select * From URL_MAPPING" in the editor.









