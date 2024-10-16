<img src="./homework-1/figure/logo.jpg" width="250px">
# Wascoot 

WASCOOT is a scooter management web application. It allows the company to keep track of scooters, their locations, and their usage patterns.
The application provides data analytics and reporting features, allowing the company to track usage patterns, measure performance, and make data-driven decisions.
## Group members

| Surname       | Name          | ID       |
| ------------- | ------------- |----------|
| Crivellari	| Alberto	| 2061934	 |
| Gharehzad		| Shiva	| 2041385  |
| Huang	| Borwoei	| 2044019	 |
| kuijpers        | nickclauder	| 2096202	 |
| Mada | Sreeshalini | 2049543  |
| Niknamhesar | Sara | 2085443  |
| Tahan		| Paria		| 2043889	 |

## Organisation of the repository ###

The repository is organised as follows:

* `src`: This folder contains the source code of the developed web application.
    * `main` :
        * `database`
          * `db_schema.sql` : The sql file for creating wascoot database
          * `insert.sql` : Inject data to Wascoot database
          * `query.sql` : Test whether the databse structure is working
        * `java`
            * `wascoot`
                * `database`
                    * `AbstractDAO.java`
                    * `AdminLoginDAO.java`
                    * `CreateAdministratorDAO.java`
                    * `CreateModelDAO.java`
                    * `CreateScooterDAO.java`
                    * `CreateScooterrackDAO.java`
                    * `CreateSubscriptionDAO.java`
                    * `CustomerAvgAgeDAO.java`
                    * `CustomerDAO.java`
                    * `CustomerDAO.java`
                    * `CustomerGenderDisDAO.java`
                    * `DataAccessObject.java`
                    * `DeleteAdministratorDAO.java`
                    * `getModelListDatabase.java`
                    * `InsertNewModelDAO.java`
                    * `ListAdminstratorDAO.java`
                    * `ListModelDAO.java`
                    * `ListPaymentMethodDAO.java`
                    * `ListRentalsDAO.java`
                    * `ListScootersDAO.java`
                    * `ListSubscriptionDAO.java`
                    * `LoadAdminstratorPhoto.java`
                    * `package-info.java`
                    * `PaymentWithoutSubscriptionDatabase.java`
                    * `PaymentWithSubscriptionDatabase.java`
                    * `ReadAdministratorDAO.java`
                    * `RentalDatabase.java`
                    * `ScooterRackDatabase.java`
                    * `SearchAdministratorByEmailDAO.java`
                    * `SearchAdministratorByIdDAO.java`
                    * `UpdateAdministratorDAO.java`
                    * `UpdateModelDAO.java`
                    * `UpdatePaymentMethodDAO.java`
                    * `UpdateScooterDAO.java`
                    * `UpdateScooterrackDAO.java`
                    * `UpdateSubscriptionDAO.java`
                * `filter`
                    * `AbstractFilter.java`
                    * `LoginFilter.java`
                * `resource`
                    * `AbstractResource.java`
                    * `Actions.java`
                    * `Administrator.java`
                    * `Customer.java`
                    * `GenderDistributionResource.java`
                    * `LogContext.java`
                    * `MapEntryResource.java`
                    * `Message.java`
                    * `Model.java`
                    * `package-info.java.java`
                    * `PaymentMethod.java`
                    * `PaymentWithoutSubscription.java`
                    * `Rental.java`
                    * `Resource.java`
                    * `ResourceList.java`
                    * `Revenue.java`
                    * `Scooter.java`
                    * `Scooterrack.java`
                    * `Subscription.java`
                * `rest`
                    * `Abstract.java`
                    * `CreateAdministratorRR.java`
                    * `CustomerAvgAgeRR.java`
                    * `CustomerGenderDisRR.java`
                    * `CustomerRR.java`
                    * `DeleteAdministratorRR.java`
                    * `ListAdministratorRR.java`
                    * `ListModelRR.java`
                    * `ListScooterracksRR.java`
                    * `ListScooterRR.java`
                    * `package-info.java.java`
                    * `ReadAdministratorRR.java`
                    * `RestResource.java`
                    * `SearchAdministratorByEmailRR.java`
                    * `SearchAdministratorByIdRR.java`
                    * `UpdateAdministratorRR.java`
                * `servlet`
                    * `AbstractDatabaseServlet.java`
                    * `AdminLoginJspServlet.java`
                    * `CreateAdministratorNoPhotoServlet.java`
                    * `CreateAdministratorServlet.java`
                    * `DashboardServlet.java`
                    * `ListCustomerServlet.java`
                    * `ListRentalsServlet.java`
                    * `LoadAdministratorPhotoServlet.java`
                    * `ModelServlet.java`
                    * `package-info.java`
                    * `PaymentMethodServlet.java`
                    * `RestDispatcherServlet.java`
                    * `ScooterrackServlet.java`
                    * `ScooterServlet.java`
                    * `SearchAdministratorByEmailServlet.java`
                    * `SearchAdministratorByIdServlet.java`
                    * `SubscriptionServlet.java`
                * `utils`
                  * `ErrorCode.java`
        * `resources`
            * `log4j2.xml`
        * webapp
          * css
            * `cssstyle.css`
            * `dashboard.css`
            * `style.css`
          * html
          * js
            * `administrator.js`
            * `ajax_administrator_email.js`
            * `ajax_administrator_id.js`
            * `dashboard.js`
            * `js_script.js`
            * `managePages.js`
            * `utils.js`
            * `wascoot.js`
          * jsp here there various jsp files related to our servlets and rests and the jsp files below are imported to each jsp file
            * `head.jsp`
            * `header-menu.jsp`
            * `foot.jsp`
            * `footer.jsp`
          * media
          * META-INF
          * WEB-INF
            
* `homework-1`: This folder contains the report describing the structure of the database and our backend.
* `homework-2`: This folder contains the presentation of our web application.
## Project Description ##

This project aims at developping a web application related to our Web Application course from the University of Padoue from Professor Nicola Ferro. It a tool for managers of a scooter business, 
helping them improve the efficiency and control over their operations.

## How to run and use the codes? ##

Create directory to insert project.
Into directory : git clone https://bitbucket.org/upd-dei-stud-prj/wascoot/src/master/README.md?mode=edit&at=master.
cd wascoot.
Change pom.xml and context.xml file to adapt your local settings.
To build : mvn clean package.
