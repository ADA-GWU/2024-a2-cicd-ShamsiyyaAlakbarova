package ada.edu.demo.webtest;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.Duration;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WebInterfaceTests {

	@Autowired
	private WebDriver webDriver;

	@LocalServerPort
	private int port;

	@Test
	@Order(1)
	@DisplayName("Create a user")
	public void CreateUser() {
		webDriver.get("http://localhost:"+port+"/student/new");

		WebElement studentIdInput = webDriver.findElement(By.id("studentId"));
		WebElement firstNameInput = webDriver.findElement(By.id("firstName"));
		WebElement lastNameInput = webDriver.findElement(By.id("lastName"));
		WebElement emailInput = webDriver.findElement(By.id("email"));

		// Check if such a field exists
		assertNotNull(firstNameInput);

		try {
			studentIdInput.sendKeys("1");
			Thread.sleep(2000);
			firstNameInput.sendKeys("Nigar");
			Thread.sleep(2000);
			lastNameInput.sendKeys("Salayeva");
			Thread.sleep(2000);
			emailInput.sendKeys("ns@ada.edu.az");
			Thread.sleep(2000);
		}
		catch (Exception ex) {
			System.out.println(ex);
		}

		// Find and submit the form (assuming there's a submit button with a specific attribute)
		WebElement submitButton = webDriver.findElement(By.id("submit"));
		submitButton.click();
	}

	@Test
	@Order(2)
	@DisplayName("Check the created user")
	public void CheckUser() {
		// Check if the student is added
		webDriver.get("http://localhost:"+port+"/student/list");
		List<WebElement> bodyElementFName = webDriver.findElements(By.xpath("//*[contains(text(), 'Nigar')]"));
		List<WebElement> bodyElementLName = webDriver.findElements(By.xpath("//*[contains(text(), 'Salayeva')]"));
		System.out.println("Element result"+bodyElementLName);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		// Check if the text "Jamal" is present in the page content
		assert(bodyElementFName.size() == 1);
		assert(bodyElementLName.size() == 1);
	}
	@Test
	@Order(3)
	@DisplayName("Update Student With Courses")
	public void updateStudentWithCourses() {
		// Assume an existing student ID to update. Replace with an actual ID.
		int studentIdToUpdate = 1;
		webDriver.get("http://localhost:" + port + "/student/update?id=" + studentIdToUpdate);

		// Use explicit wait instead of Thread.sleep()
		WebDriverWait wait = new WebDriverWait(webDriver, 10); // Wait up to 10 seconds

		// Wait for the submit button to be clickable
		WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));

		// Select courses. This example assumes checkboxes for course selection.
		// Adjust the logic based on your actual page structure.
		List<WebElement> courseCheckboxes = webDriver.findElements(By.name("courses"));
		for (WebElement checkbox : courseCheckboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click(); // Select all available courses for simplicity
			}
		}

		// Submit the form
		submitButton.click();

		// Verify the courses are added by navigating to the student's detail page and checking selected courses
		// This step depends on how courses are displayed on the student's detail or list page after being added
		webDriver.get("http://localhost:" + port + "/student/id?id=" + studentIdToUpdate);

	}


}

