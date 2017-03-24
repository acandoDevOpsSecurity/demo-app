# demo-app
Demo Application for DevOps Security testing. For more info about the app, see [https://google-gruyere.appspot.com/part1] (https://google-gruyere.appspot.com/part1)

### Run with Eclipse
Add VM arugments to your run configuration (spring profiles are not in use yet)
-Dspring.profiles.active=dev -javaagent:springloaded-1.2.5.RELEASE.jar -noverify

### Use the App
Open browser on [http://localhost:8080] (http://localhost:8080)
To log in look-up name:password pairs in src/main/resources/data.sql
The application uses Spring Security, but CRSF has been disabled.

### Known Vulnerabilities
1. **reflected XSS**, e.g. enter [http://localhost:8080/home%3Cscript%3Ealert('hi')%3C/script%3E] (http://localhost:8080/home%3Cscript%3Ealert('hi')%3C/script%3E)
2. **stored XSS**, e.g. create a snippet with the following text: ```<script>alert('hi')</script>```
3. **DoS** - Quit the server, do a POST request to [http://localhost:8080/admin/shutdown] (http://localhost:8080/admin/shutdown) with an authorized admin-user (e.g. by spoofing or elevation of privilege, see below)
4. **Elevation of Privilege** - set role admin to any user, e.g [fake the save request] (Elevation-of-Privilege.JPG)
5. **Information Disclosure** - get knowledge about server configuration, endpoints, ... Nowadays many Spring Boot applications make use of [Spring Boot Actuator] (Information-Disclosure.JPG) 
6. **Path Traversal** - upload a file and specify filename like "../myimage.png" instead of "myimage.png"
