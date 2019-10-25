# Room Booking Management System

## Available Functionality

1. Fetch All Bookings- /bookings

2. Fetch All Rooms Availability- /rooms
   
3. Book a room- /booking
   
4. Cancel a booking- /booking/{id}

## Database Tables

1. ROOMSTYPE - It has all different type of rooms entries i.e SINGLE,DOUBLE,SUITE
2. ROOMS- It has all the entries of the rooms with room number and room type id.
3. ROOMSBOOKING- It has details of the room booking requests

Table script and sample entries for ROOMSTYPE and ROOMS table is placed in db/init.sql

## Build Project

1. Clone repository and Run command "mvn clean install"- It will create jar file in target folder.
2. Build a docker image for jar file created in step 1, with command "docker build -t rbms ."
3. Verfiy that image is created with name as rbms and version as latest, with command "docker images"
3. Next build image for database, Go to db folder
4. Build a docker image for postgres db, with command "docker build -t postgredb ."
5. Verify that image created with name as postgredb and version as latest. Command- "docker images"
6. Now go back to the parent folder and run docker compose with command "docker-compose up"
7. Verify http://localhost:8080/swagger-ui.html is accessible.



