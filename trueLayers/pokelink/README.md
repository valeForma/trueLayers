# Read Me First


## developement installation
### backend
	1. install jdk 11
	2. install maven 
	3. move to folder pokelink
	4. run mvn clean  install
	5.run the gennerated jar using "java -jar -Dspring.profiles.active=docker	<jar name under target folder>"
### frontend 
	1. install node js	
	2. install nginix
	3. move to folder pokeFornt
	4. run : npm  install
	5. run : npm run build --prod
	6. move the pokeFornt folder from dist to /usr/share/nginx/html
## Test & Production
	- for test and production environment use docker compose yaml from trueLayers folder run 
	- to start both frontend both beckend run : docker-compose up -d
	- to stop both  :docker-compose down
	


## Architecthure
### backend
	-backend is spring based  with jetty embedded so it can run as standalone jar,spring cloud with feign is used for rest communication
### frontend
	-frontend is angular based ,ngrx is used for  state management according to the react pattern, angular material and flex layout is used for
	 make it responsinve and good looking 
## Testing
### backend
	-backend test is spring based  with junit performing mvcMock rest call and testing results
### frontend
	-frontend test are karma and jasmine based to check compnents creation  ngrx store values and services working properly 
	
## additional features
	-it was added a page to list all pokemon available that are pickable for shakespeare translation,autocomplete was added on the search for, and 
	the chance to chose different pokekemon version and language description
	