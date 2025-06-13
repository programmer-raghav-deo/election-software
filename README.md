# Overview of election software project

Purpose : The purpose of this project was to facilitate election for various positions in D.A.V. Public School Aundh,Pune for the academic year 2024 - 2025.

Tech Stack : Java programming language,Spring framework along with vaadin for frontend and backend,SQL for database management

Working : The project was deployed on a LAN connected computer. This computer would act as a server allowing for different client pcs connected to same LAN network, to access the website and vote.

Key features : Customisation options for ip address,database name,username,password of mysql database. Database connection pooling with Hikari.

# Screenshots of software

![screenshot](https://github.com/user-attachments/assets/80d6f392-21fb-4f0d-9384-4f6bf8a0e30d)
![screenshot](https://github.com/user-attachments/assets/c07520ab-a880-460b-8579-097dca542a52)
![screenshot](https://github.com/user-attachments/assets/4288a8c4-3bed-46ef-af6a-ee07e05ba11e)
![screenshot](https://github.com/user-attachments/assets/f8fa5bec-ae2f-44d9-91b1-0326619afda1)
![screenshot](https://github.com/user-attachments/assets/fd2da9d4-df71-40d0-ab06-c3077afdfc7c)
![screenshot](https://github.com/user-attachments/assets/8caff70c-286f-42b4-8397-0c8fa9b0909c)
![screenshot](https://github.com/user-attachments/assets/8db43036-88a4-468d-86a2-5f84388a9867)


Note : Images used are for demonstration purposes only. Actual election was conducted with images of candidates along with their names.

# How to use the software

Step 1 : Download the jar file -> https://github.com/programmer-raghav-deo/election-software/releases/download/v2.0.0/election-software.jar

Step 2 : Setup SQL server with database name of your choice (preferably test)

Step 3 : Create table by running this command in SQL server 
```sql
CREATE TABLE cartoon(name VARCHAR(100), votes VARCHAR(20));
```

Step 4 : Insert 0 votes for each candidate by running this command in SQL server 
```sql
INSERT INTO cartoon VALUES('doreamon','0'),('pikachu','0'),('bill','0'),('sho','0'),('arthur','0'),('gwen','0'),('blossom','0'),('shizuka','0'),('misty','0'),('buttercup','0'),('tyson','0'),('steve','0'),('goku','0'),('vegeta','0'),('thor','0'),('starfire','0'),('margo','0'),('ladybug','0'),('bubbles','0'),('dora','0');
```

Step 5 : Navigate to folder containing jar file and run
```bash
java -jar election-software.jar
```

Step 6 : Open browser and navigate to -> http://localhost:8080

Step 7 : Press Alt key + F5 to proceed from login page

Step 8 : Click on choices that you want to vote and hit submit button at bottom of page

Step 9 : Login to your sql server

Step 10 : Use database by running this command in SQL server 
```sql
USE test;
```

Step 11 : SELECT all rows from table by running this command in SQL server
```sql
SELECT * FROM cartoon;
```
