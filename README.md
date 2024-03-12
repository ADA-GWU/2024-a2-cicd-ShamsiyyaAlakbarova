<h2>1.Prerequisites.</h2>
<p>Before you can use this application, you will need to ensure that you have Java (JDK), Maven, Docker, and Intellij IDEA installed on your system. If you don't have them installed, you can visit their official websites and download them.</p>

<h2>2.Set Up Environment.</h2>
<p>Step 1. Go to my github repository (https://github.com/ADA-GWU/2024-a2-cicd-ShamsiyyaAlakbarova).<br>Step 2. Press to green CODE button, and download the repository as a ZIP file.<br>
Step 3. Unzip the file.<br>
Step 4. Open downloaded unzipped project in Intellij IDEA.<br>
Step 5. Run this commands in terminal:<br>1) mvn clean package<br>2) docker build -t springio/webtest .<br>3) docker run -p 8080:8080 springio/webtest (make sure this port is available)<br>
Application is running on port 8080. Set up is DONE!<br></p>

<h2>3.Run the Tests</h2>
<p>In order to run the tests you should be inside webtest-master directory. You can press run buttons, or use Ctrl + Shift + f10.</p>
<h3>1) Unit Tests. UnitTests class.</h3>
<p>1. The number of courses shall correspond to the added courses<br>
2. The total credits shall correspond to the sum of the added credits<br>
</p>

<h3>2) Functionality Tests. FunctionalityTests class.</h3>
 <p>1. Test finding a student by ID<br>
2. Search by first or last name<br>
3. Get student by name is accurate<br></p>

<h3>2) Web Interface Tests. WebInterfaceTestsTests class.</h3>
 <p>1. Create a user<br>
2. Check the created user<br>
3. Update Student With Courses<br>
 If you want to see visual representation of Web tests, go to SeleniumConfig file=> comment "return new FirefoxDriver(options);", and uncomment "return new FirefoxDriver();"</p>
