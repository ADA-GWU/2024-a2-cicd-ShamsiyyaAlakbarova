<h2>1.Prerequisites.</h2>
<p>Before you can use this application, you will need to ensure that you have Java (JDK), Maven, Docker, and Intellij IDEA installed on your system. If you don't have them installed, you can visit their official websites and download them.</p>

<h2>2.Set Up Environment.</h2>
<p>Step 1. Go to my github repository (https://github.com/ADA-GWU/2024-a2-cicd-ShamsiyyaAlakbarova).<br>Step 2. Press to green CODE button, and download the repository as a ZIP file.<br>
Step 3. Unzip the file.<br>
Step 4. Open downloaded unzipped project in Intellij IDEA.<br>
Step 5. Run this commands in terminal:<br>1) mvn clean package<br>2) docker build -t springio/webtest .<br>3) docker run -p 8080:8080 springio/webtest<br>
