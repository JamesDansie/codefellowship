# Code Fellow Ship
### Author: James Dansie
This is a social network for codefellows people to nerd out on code. It is built with a Java/Spring back end. Users can have posts, and follow other users. All info is stored in post gres SQL.

### Running the App
Assuming gradle is installed, in the command line call;
```
./gradlew bootrun
```
But before that set a system env variable of;
```
DATABASE_URL=postgresql://localhost:5432/codefellowship
```
If running on windows or linux include your postgres username and password in the system env, and the application.properties.