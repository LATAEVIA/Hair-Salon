# _Hair Salon_

#### _Java application project built as a code review of competencies including setting up PostgreSQL, working with relational databases, writing SQL statements to select, insert, retrieve, update and delete data, building a Spark app with a database, implementing new HTTP requests: PATCH/PUT and DELETE and creating a database connection using the Java database connection library(JDBC) with Sql2o., 05/07/2016_

#### By _**LaTaevia**_

## Description

_Java application webpage assigned as a project for my eighth week at Epicodus. It functions as an app for a hair salon. The owner will be able to add a list of the stylists, and for each stylist, add clients who see that stylist. The stylists work independently, so each client only belongs to a single stylist._

## Prerequisites

You will need the following properly installed on your computer.

* [Gradle](https://gradle.org/gradle-download/)
* PostgreSQL on Mac with HomeBrew `brew install postgres` 
* [PostgreSQL (All OS)] (http://www.enterprisedb.com/products-services-training/pgdownload#windows)

## Installation

* `git clone https://github.com/LATAEVIA/Hair-Salon.git`
* Change into the new directory
* In a new terminal window/tab `postgres` to launch postgres
* In another new terminal window/tab `psql` to launch psql
* In psql window/tab `CREATE DATABASE hair_salon;`
* In the terminal window/tab `psql hair_salon < hair_salon.sql`
* In psql window/tab `CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;`
* `gradle build`

## Running / Development

* `gradle run`
* Visit app at [http://localhost:4567](http://localhost:4567).

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
