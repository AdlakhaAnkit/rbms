# Room Booking Management System

## Available Functionality

1. Fetch All Bookings

2. Fetch All Rooms Availability

3. Book a room

4. Cancel a booking

## Build Project

1. Run command "mvn clean install"
2. Build a docker image for jar file created in step 1, with command "docker build -t rbms ."
3. Go to db folder
4. Build a docker image for postgres db, with command "docker build -t postgredb ."
5. Verify both the images build with version as latest. Command- "docker images"
6. Now go back to the parent folder and run docker compose with command "docker-compose up"
7. Verify http://localhost:8080/swagger-ui.html is accessible.



