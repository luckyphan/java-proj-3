The SpringmvcApplication is the @SpringBootApplication that will run and return the beans in the ApplicationContext
	Beans: instances created by the Spring container 
 	Launches the app and the bean defined in the run is created
-	Starts the Spring framework and integrates main with SpringBoot
-	@Component(detects beans automatically) @Controller @Repository
-	Uses the @ComponentScan from the ApplicationContext to not define the bean in class for run
		 

The MovieApp is the @ComponentScanlooks for beans to include in the ApplicationContext
	Creates ApplicationContext with the MovieApp where getBean of bestMovieService returns the bestMovie which is titanic and returns the properties within TitanicMovie

The BestMovieService is a @Component that has Movie object that doesn’t need to be instantiated because the bean enables that functionality in the dependency inversion unless custom constructor 
	getBestMovie(returns movie)
	BestMovieService constructor that injects titanicMovie and sets the movie to be 
Returned as titanic

The Movie interface enables reusability, abstraction that stores method signatures getTitle,getMaturityRating, getGenre implemented by BatmanMovie and TitanicMovie

BatmanMovie and TitanicMovie are two individual movies that implement Movie that are @Component that can be injected in BestMovieService with Batman being the default

Default beans are overridden with @Qualifier(beans of same type in this case Movie) and @Autowired to specify the bean wired

InversionofControl(IoC) looks inside package and nested package, enables dependency injection and dependency inversion. Controls creating and instantiating beans loose-coupling) with dependencies injected by assembler objects.
 In this case the ApplicationContext is the IoC which is declared and instantiated in 
MovieApp

./mvn spring-boot:run
http://localhost:8080 i






Templates directory holds pages for user to redirect to: index, bestMovie, voteForBestMovie, addMovie

Go to pages in templates using the @Controller, MovieController
(“/”) goes to index 
(“/bestMovie”) goes to bestMovie, passes the Model into the method. 
	Model is the container that holds data of the app
		(objects, strings, db info)
		In the @Controller
	Variable BestMovie is defined in model.addAttribute(attributeName, with getTitle
(“/voteForBestMovieForm”) goes to voteForBestMovie
(“/voteForBestMovie”) goes to voteForBestMovie
	Variable BestMovie is defined in model.addAttribute(attributeName, with the 
HttpServletRequest to access the parameter in the request req.getParameter
(“addMovieForm”) goes to addMovie
(“addMovie”) goes to addMovie
	SessionFactory variable (field injection)  is used to create a Session object 
	Get parameters ( title, maturity rating, genre) from the request
	Create movie entity object and set values above to it
	Create,save,commit transactionhint: Updates were rejected because the tip of your current branch is behind
hint: its remote counterpart. Integrate the remote changes (e.g.
hint: 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.


Use supabase to connect to the db
	Go to settings
	Go to Connection string
		Click and copy the string from JDBC
jdbc:postgresql://db.++++++HOST++++++.supabase.co:5432/postgres?user=postgres&pa
ssword=++++++PASSWORD++++++

Run the Postgres server 
Run the command in terminal
	$ createdb movies
In application.properties 
	spring.datasource.url=jdbc:postgresql://localhost/movies?useSSL=false
spring.datasource.username=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=create-drop
logging.level.sql=DEBUG
spring.jpa.show-sql=true

HibernateConfig is @Configuration that declares at least one bean, process and generate Spring 
Beans look at question

@Entity is a persistent object that stores records in DB (user, product, data the app needs to 
persistently retrieve

![image](https://github.com/luckyphan/java-proj-3/assets/63356985/e74370e4-9eaf-4d8f-a3e4-16a4eb512173)
