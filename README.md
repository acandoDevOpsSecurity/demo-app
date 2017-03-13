# demo-app
Demo Application for DevOps Security testing 

run with VM arugments
-Dspring.profiles.active=dev -javaagent:springloaded-1.2.5.RELEASE.jar -noverify

login with name/password, see src/main/resources/data.sql

### Known Vulnerabilities
1. **reflected XSS**, e.g. enter http://localhost:8080/home%3Cscript%3Ealert('hi')%3C/script%3E
2. **stored XSS**, e.g. create a snippet with the following text: ```<script>alert('hi')</script>```
3. **DoS** - Quit the server, do a POST request to http://localhost:8080/admin/shutdown with an authorized admin-user (e.g. by spoofing or elevation of privilege, see below)
4. **Elevation of Privilege** - set role admin to any user, e.g [fake the save request] (Elevation-of-Privilege.JPG)
