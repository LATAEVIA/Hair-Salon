# _Epicodus JAVA Week 3 Independent Project: Hair Salon_

#### _When run through Gradle, will create an app for a hair salon. The owner will be able to add a list of the stylists, and for each stylist, add clients who see that stylist. The stylists work independently, so each client only belongs to a single stylist., 05/07/2016_

#### By _**LaTaevia**_

## Description

_This a static webpage with dynamic elements, a snapshot of sorts, assigned as an end-of-week project for my eighth week at Epicodus. It uses HTML, CSS, Bootstrap, Java, Velocity, Spark, FluentLenium, PostgreSQL and Gradle, and functions as an app for a hair salon. The owner will be able to add a list of the stylists, and for each stylist, add clients who see that stylist. The stylists work independently, so each client only belongs to a single stylist._

## Setup/Installation Requirements

* _Clone through Terminal using git clone https://github.com/LATAEVIA/JAVA-WK3-Hair-Salon.git_
* _Open files in any text editor to view source code_

* _In PSQL:_
* _CREATE DATABASE hair_salon;_
* _\c hair_salon_
* _CREATE TABLE stylists (id serial PRIMARY KEY, stylist_name varchar);_
* _CREATE TABLE clients (id serial PRIMARY KEY, client_name varchar, stylist_id int);_
* _CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;_
* _With Gradle installed, navigate to folder in the terminal and 'gradle run'_
* _Open localhost:4567 in any browser to view_

## Technologies Used

* _HTML_
* _CSS_
* _Bootstrap_
* _Java_
* _Gradle_
* _Spark_
* _Velocity_
* _FluentLenium_
* _PostgreSQL_


### License

*This software is licensed under the MIT license*

Copyright (c) 2016 **_LaTaevia_**
