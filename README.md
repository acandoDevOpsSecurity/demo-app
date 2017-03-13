# demo-app
Demo Application for DevOps Security testing 

run with VM arugments
-Dspring.profiles.active=dev -javaagent:springloaded-1.2.5.RELEASE.jar -noverify

login with name/password, see src/main/resources/data.sql

### Known Vulnerabilities
1. **reflected XSS**, e.g. enter http://localhost:8080/home%3Cscript%3Ealert('hi')%3C/script%3E
2. **stored XSS**, e.g. create a snippet with the following text: ```<script>alert('hi')</script>```
3. **DoS - Quit the server**, login as a admin (e.g. by spoofing attack), do a POST to http://localhost:8080/admin/shutdown

