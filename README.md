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
There are over five different vulnerabilities in here. Look at the content of https://google-gruyere.appspot.com/part1 and check some out!

### Appsensor integration
Use of appsensor-local with spring-security integration, attact detection exemplary. 
* disables user on the first path traversal attack detection, include a "/" in the filename parameter 
* disables user on the thrid stored XSS attack detection

appsensor configuration see [appsensor-server-config](https://github.com/devOpsSecurity/demo-app/blob/master/demo/src/main/resources/appsensor-server-config.xml) RE8 detection-point.
