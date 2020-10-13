package cuke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TeaTest {

	public static WebDriver driver;

	public static ChromeOptions chromeCfg() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions cOptions = new ChromeOptions();

		// Settings
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);

		// Create ChromeOptions to disable Cookies pop-up
		cOptions.setExperimentalOption("prefs", prefs);
		cOptions.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		return cOptions;
	}

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\azwad\\Documents\\workspace-spring-tool-suite-4-4.8.0.RELEASE\\CukeTableTask\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());

	}

	@Given("^the correct web address$")
	public void the_correct_web_address() throws Throwable {
		driver.get("http://www.practiceselenium.com/welcome.html");
		WebElement targ = driver
				.findElement(By.xpath("//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[1]"));

		String expected = "active";
		assertEquals(expected, targ.getAttribute("class"));
	}

	@When("^I navigate to the 'Menu' page$")
	public void i_navigate_to_the_Menu_page() throws Throwable {
		WebElement targ = driver
				.findElement(By.xpath("//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[3]/a"));
		targ.click();

		targ = driver.findElement(By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000450914921\"]/div/h1"));
		String expected = "Menu";
		assertEquals(expected, targ.getText());
	}

	@Then("^I can browse a list of the available products\\.$")
	public void i_can_browse_a_list_of_the_available_products() throws Throwable {
		WebElement targ = driver.findElement(
				By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000453230000\"]/div/p/span/span/strong"));
		String expected = "Green Tea";
		assertEquals(expected, targ.getText());

		targ = driver.findElement(
				By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000453231072\"]/div/p/span/span/strong"));
		expected = "Red Tea";
		assertEquals(expected, targ.getText());

		targ = driver.findElement(
				By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000453231735\"]/div/p/span/span/strong"));
		expected = "Oolong Tea";
		assertEquals(expected, targ.getText());
	}

	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() throws Throwable {
		WebElement targ = driver
				.findElement(By.xpath("//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[5]/a"));
		targ.click();
	}

	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page() throws Throwable {
		WebElement targ = driver.findElement(
				By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000452010925\"]/div/div/form/fieldset[1]"));
		assertTrue(targ.isDisplayed());
	}

	@After
	public void cleanUp() {
		driver.close();
		driver.quit();
	}
}
