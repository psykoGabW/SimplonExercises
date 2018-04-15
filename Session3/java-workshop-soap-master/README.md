# Java SOAP Webservice workshop

This repository contains a simple SOAP Java Web Service linked to a Postgresql DB.

## Features

The web service is linked to a food datatabe. Food database contains 2 tables : `food_attribute` which is linked to `food_category` table. The web service exposes 3 methods to interact with DB:

* List food attributes based on a name filter (listing food starting with name filter)
* Create a new food attribute
* Getting a food attribute based on its ID

## Project structure

### Maven project

Web service source code may be imported in an IDE as a Maven project. Java source code is available in `src` folder.

### Docker

To enable fast testing, a `docker` folder has been created where a Tomcat server image and a Postgresql DB image have been configured using `docker-compose`.

**Steps to test :**

* Import maven project in your IDE
* Generate soap-demo.war by running maven `install` command (war file should automatically go in `docker/ws` folder)
* Install `docker` and `docker-compose`
* Open a terminal in `docker` folder and run following commands :
  * `docker-compose down`
  * `docker-compose build`
  * `docker-compose up -d`

You should now be able to access the web service on the following address : http://localhost:8081/soap-demo/foodDBService

## Important notice

To enable fast testing a specific database and a specific db user account are created in [db init script](./docker/db/init-db-data.sh). Password is written in clear text. This should never be the case in production environment. Same is true for [web service config file](./src/main/resources/config.properties).