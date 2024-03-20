package com.qa.opencart.utils;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.opencart.frameworkexceptions.FrameworkExceptions;


public class ElementUtil {

	private WebDriver driver;
	private int DEFAULT_TIME_OUT = 5;

	public ElementUtil(WebDriver driver) {

		this.driver = driver;

	}

	/**
	 * most used driver.findElements
	 * 
	 * @param locator
	 * @return
	 */

	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			System.out.println("Element  found using the given locator:" + locator);
		} catch (NoSuchElementException e) {
			System.out.println("Element not found using the given locator:" + locator);
			element = waitForElementVisibility(locator, DEFAULT_TIME_OUT);
		}
		return element;

	}

	public WebElement getElement(By locator, int timeout) {
		return waitForElementVisibility(locator, timeout);

	}

	/**
	 * to enter value in fields provided locator and value.We clear the field before
	 * entering the value
	 * 
	 * @param locator
	 * @param value
	 */

	public void doSendKeys(By locator, String value) {
		if (value == null) {
			System.out.println("null values are not allowed");
			throw new FrameworkExceptions("VALUECANNOTBENULL");
		}
		// doClear(locator);
		WebElement ele=getElement(locator);
		ele.clear();
		ele.sendKeys(value);
		//getElement(locator).sendKeys(value);
	}

	/**
	 * To click on a WebElement
	 * 
	 * @param locator
	 */

	public void doClick(By locator) {
		getElement(locator).click();
	}

	/**
	 * An expectation for checking an element is visible and enabled before
	 * performing a click on it. Timeout in seconds
	 * 
	 * @param locator
	 * @param timeOut
	 */

	public void doClick(By locator, int timeOut) {
		checkElementClikable(locator, timeOut).click();
	}

	/**
	 * To clear the value contained in the locator
	 * 
	 * @param locator
	 */

	public void doClear(By locator) {
		getElement(locator).clear();
	}

	/**
	 * It returns the text of the locator that we specify.The text can like shown
	 * below p tag getTextvalue p tag closing * getText can done to get the text of
	 * any WebElement like p tag,h tag or a tag It cannot be used to get the text
	 * that has been entered in the textfield
	 * 
	 * @param locator
	 * @return
	 */

	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}

	/**
	 * More about how elementIsDisplayed() was developed is in the class
	 * ElementIsDisplayed
	 * 
	 * @param locator
	 * @return Boolean
	 */

	public Boolean elementIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();

	}

	/**
	 * It is used to get the any attribute value in the HTML DOM by specifying the
	 * key for which we want the value and by specifying the locator
	 * 
	 * @param locator
	 * @param attrName placeholder is an attribute of WebElement which holder the
	 *                 grey value that we see in textfields
	 */

	public String doGetAttributeValue(By locator, String attrName) {

		return getElement(locator).getAttribute(attrName);

	}

	/**
	 * It is used to store all the Webelements of the HTML DOM using a single
	 * locator in List<WebElements
	 * 
	 * @param Locator
	 * @return
	 */

	public List<WebElement> getElements(By Locator) {
		return driver.findElements(Locator);
	}

	/**
	 * The popular driver.findElements will return a list of Webelements. The
	 * WebElement might have various attributes. Example use:If we want to validate
	 * a specific attribute like src in img tag or href in a tag. It can be used to
	 * validate any attribute value provided the locator and attribute name
	 * 
	 * @param locator
	 * @param attrName
	 * @return
	 */
	public List<String> getElementsAttributeValue(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		List<String> attrValues = new ArrayList<String>();
		int count = 0;
		for (WebElement webElement : eleList) {
			String attrValue = webElement.getAttribute(attrName);
			System.out.println(count + "=" + attrValue);
			attrValues.add(attrValue);
			count++;

		}
		System.out.println("Total size of attrValues which is List<String> is:" + attrValues.size());
		System.out.println("Total size of eleList which is List<WebElement> is:" + eleList.size());
		return attrValues;
	}

	/**
	 * To get the count of list of WebElements by providing the locator
	 * 
	 * @param locator
	 * @return
	 */

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	/**
	 * To get the text value of elements from a specific section. example: right
	 * panel has 13 links and we want the text value of those links
	 * 
	 * @param locator
	 * @return
	 */

	public List<String> getElementsTextList(By locator) {
		List<WebElement> elementsList = getElements(locator);
		List<String> elesTextList = new ArrayList<String>();
		for (WebElement webElement : elementsList) {
			String linkText = webElement.getText();
			// System.out.println(linkText);
			elesTextList.add(linkText);

		}
		return elesTextList;

	}

	/**
	 * to click on a specific element from a list of WebElements(from specific
	 * section of page) and perform a click on it
	 * 
	 * @param locator
	 * @param eleText
	 */

	public void doClickElementFromPageSection(By locator, String eleText) {
		List<WebElement> eleList = getElements(locator);
		for (WebElement webElement : eleList) {
			String eleValue = webElement.getText();
			if (eleValue.equals(eleText)) {
				System.out.println("the link to be clicked is:" + eleValue);
				webElement.click();
				break;
			}
		}
	}

	/**
	 * google search logic
	 * 
	 * @param searchLocator
	 * @param searchText
	 * @param suggestionLocator
	 * @param suggestionString
	 * @throws InterruptedException
	 */

	public void search(By searchLocator, String searchText, By suggestionLocator, String suggestionString)
			throws InterruptedException {

		// driver.findElement(searchLocator).sendKeys(searchText);
		doSendKeys(searchLocator, searchText);
		Thread.sleep(5000);
		List<WebElement> searchList = getElements(suggestionLocator);
		// List<WebElement> searchList = driver.findElements(suggestionLocator);

		int searchResults = searchList.size();
		System.out.println("Number of Search Results:" + searchResults);
		int count = 1;
		if (searchResults > 0) {
			for (WebElement webElement : searchList) {
				String searchtext = webElement.getText();
				if (searchtext.length() > 0) {
					System.out.println(count + "=" + searchtext);
					count++;
					if (searchtext.contains(suggestionString)) {
						webElement.click();
						break;
					}

				} else {
					System.out.println("The Results have either Blank Suggestions or No Suggestion Results");
					break;
				}

			}

		} else {
			System.out.println("No Search suggestions");
		}

	}

	/**
	 * alternative of isDisplayed
	 * 
	 * @param locator
	 * @return
	 */

	public boolean IsElementDisplayed(By locator) {
		List<WebElement> eleList = getElements(locator);
		if (eleList.size() > 0) {
			System.out.println(locator + " Element is present on the page");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Select class has 2 ways of selecting the drop down value.By index ,value and
	 * visibletext. Below 3 generic methods are for the same.
	 * 
	 * @param locator
	 * @param index
	 */

	public void doSelectDropDownByIndex(By locator, int index) {

		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByValueAttribute(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownByVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	/**
	 * The below 3 methods are custom methods: getAllDropDownOptions: to store the
	 * values of the dropdown getDropDownValueCount: get the total count of options
	 * available in the dropdown doSelectDropDownValue:To click on a specific value
	 * from a dropdown
	 */
	public List<String> getAllDropDownOptions(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionslist = select.getOptions();
		List<String> options = new ArrayList<String>();
		for (WebElement e : optionslist) {
			String option = e.getText();
			options.add(option);
		}
		return options;

	}

	public int getDropDownValueCount(By locator) {
		return getAllDropDownOptions(locator).size();
	}

	public boolean doSelectDropDownValue(By locator, String dropDownValue) {
		boolean flag = false;
		Select select = new Select(getElement(locator));
		List<WebElement> optionslist = select.getOptions();
		for (WebElement e : optionslist) {
			String text = e.getText();
			if (text.equals(dropDownValue)) {
				flag = true;
				e.click();
				break;
			}

		}
		if (flag == false) {
			System.out.println(dropDownValue + " is not present in the drop down " + locator);
		}
		return flag;

	}

	/**
	 * If we have to click a option from dropdown without using select class and
	 * methods
	 * 
	 * @param locator
	 * @param value
	 * @return
	 */

	public boolean doSelectValueFromDropDownWithoutSelect(By locator, String value) {
		boolean flag = false;
		List<WebElement> optionsList = getElements(locator);
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				flag = true;
				e.click();
				break;
			}

		}
		if (flag == false) {
			System.out.println(value + " option is not present in the locator " + locator);
		}
		return flag;
	}

	// **************Actions Class Utils**********************
	/**
	 * performs click on the specific in the middle of the + element
	 * 
	 * @param locator
	 */

	public void doActionsClick(By locator) {

		Actions ac = new Actions(driver);
		ac.click(getElement(locator)).build().perform();

	}

	/**
	 * If we are not able to click on the element normally,then we use Actions class
	 * click
	 * 
	 * @param locator
	 * @param timeOut
	 */

	public void doActionsClick(By locator, int timeOut) {

		Actions ac = new Actions(driver);
		ac.click(checkElementClikable(locator, timeOut)).build().perform();

	}

	/**
	 * performs entering a value after moving and clicking on a specific element at
	 * the middle
	 * 
	 * @param locator
	 * @param value
	 */

	public void doActionsSendKeys(By locator, String value) {

		Actions ac = new Actions(driver);
		ac.sendKeys(getElement(locator), value).build().perform();

	}

	/**
	 * drag and drop
	 * 
	 * @param sourceLocator
	 * @param targetLocator
	 */
	public void doDragandDrop(By sourceLocator, By targetLocator) {
		Actions ac = new Actions(driver);
		ac.dragAndDrop(getElement(sourceLocator), getElement(targetLocator)).build().perform();

	}

	/**
	 * This method handles mouse hover on a dropdown header and then select the
	 * dropdown value using actions class
	 * 
	 * @param parentMenu
	 * @param childMenu
	 * @throws InterruptedException
	 */

	public void handleTwoLevelMenu(By parentMenu, By childMenu) throws InterruptedException {
		Actions ac = new Actions(driver);
		ac.moveToElement(getElement(parentMenu)).build().perform();
		Thread.sleep(4000);
		doClick(childMenu);

	}

	/**
	 * This method handles mouse hover on a dropdown header and then select the
	 * dropdown value using actions class
	 * 
	 * @param parentMenu
	 * @param childMenuText
	 * @throws InterruptedException
	 */

	public void handleTwoLevelMenu(By parentMenu, String childMenuText) throws InterruptedException {
		Actions ac = new Actions(driver);
		ac.moveToElement(getElement(parentMenu)).build().perform();
		Thread.sleep(4000);
		doClick(By.linkText(childMenuText));

	}

	/**
	 * This method helps us in handling multilevel Menu- Example-Category Button
	 * dropdown-->Beverages-->Tea-->Tea Bags
	 * 
	 */

	public void multiLevelMenuHandle(By parentMenu, String level2, String level3, String level4)
			throws InterruptedException {
		WebElement parent = getElement(parentMenu);
		parent.click();
		Thread.sleep(3000);

		WebElement level2element = getElement(By.linkText(level2));
		Actions ac = new Actions(driver);
		ac.moveToElement(level2element).build().perform();
		Thread.sleep(3000);

		WebElement level3element = getElement(By.linkText(level3));
		ac.moveToElement(level3element).build().perform();
		Thread.sleep(3000);

		doClick(By.linkText(level4));
		Thread.sleep(3000);
	}

	/**
	 * doing right click on a element
	 * 
	 * @param locator
	 */

	public void doContextClick(By locator) {
		Actions ac = new Actions(driver);
		ac.contextClick(getElement(locator)).build().perform();
	}

	/**
	 * moveToElement action
	 * 
	 * @param locator
	 */
	public void doMoveToElement(By locator) {

		Actions ac = new Actions(driver);
		ac.moveToElement(getElement(locator)).build().perform();

	}

	// ***********************************WAIT******************************//
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible on the web page
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */

	public WebElement waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	// ***********************************WAITS******************************//
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return WebElement
	 */

	public WebElement waitForElementVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locatorare visible. Visibility means that the elements are not only
	 * displayed but also have a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout deault time out is 500ms
	 * @return List<WebElement>
	 */
	public List<WebElement> waitForElementsVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	// default timeout = intervalTime
	public List<WebElement> waitForElementsVisible(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeout
	 * @return List<WebElement>
	 */

	public List<WebElement> waitForElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeout
	 * @return void
	 */

	public void clickElementWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeout
	 * @return WebElement
	 */

	public WebElement checkElementClikable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));

	}

	// **********************Alerts with Explicit
	// Waits***************************////////////////

	/**
	 * Explicitly wait with FluentWait features This method return an alert after
	 * waiting for it for the given explicit timeout period. We can later call any
	 * alert specific methods onit like accept(),dismiss(),getText(),sendKeys()
	 * 
	 * @param timeout
	 * @return Alert
	 */
	public Alert waitForAlertJSPopUpWithFluentWait(int timeout, int pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.ignoring(NoAlertPresentException.class).pollingEvery(Duration.ofSeconds(pollingtime))
				.withMessage("..............time out is done........alert not found");

		return wait.until(ExpectedConditions.alertIsPresent());

	}

	/**
	 * This method return an alert after waiting for it for the given explicit
	 * timeout period. We can later call any alert specific methods onit like
	 * accept(),dismiss(),getText(),sendKeys()
	 * 
	 * @param timeout
	 * @return Alert
	 */

	public Alert waitForAlertJSPopUp(int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	/**
	 * waitForAlertJSPopUp is a custom method in ElementUtil that returns an Alert
	 * on which accept is performed
	 * 
	 * @param timeout
	 */

	public void alertAccept(int timeout) {

		waitForAlertJSPopUp(timeout).accept();

	}

	/**
	 * waitForAlertJSPopUp is a custom method in ElementUtil that returns an Alert
	 * on which dismiss is performed
	 * 
	 * @param timeout
	 */
	public void alertDismiss(int timeout) {

		waitForAlertJSPopUp(timeout).dismiss();

	}

	/**
	 * waitForAlertJSPopUp is a custom method in ElementUtil that returns an Alert
	 * on which getText is performed
	 * 
	 * @param timeout
	 */

	public String alertJSGetText(int timeout) {

		return waitForAlertJSPopUp(timeout).getText();

	}

	/**
	 * waitForAlertJSPopUp is a custom method in ElementUtil that returns an Alert
	 * on which sendKeys is performed
	 * 
	 * @param timeout
	 */
	public void alertEnterValue(String value, int timeout) {

		waitForAlertJSPopUp(timeout).sendKeys(value);

	}

	/**
	 * 
	 * @param timeout
	 * @param titleFraction
	 * @return
	 */

	public String waitForTitleContainsAndCapture(int timeout, String titleFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			String titleofpage = driver.getTitle();
			return titleofpage;
		} else {
			System.out.println("The title is not present within the given timeout:" + timeout);
			return null;
		}

	}

	/**
	 * 
	 * @param timeout
	 * @param title
	 * @return
	 */
	public String waitForTitleIsAndCapture(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.titleIs(title))) {
			String titleofpage = driver.getTitle();
			return titleofpage;
		} else {
			System.out.println("The title is not present within the given timeout:" + timeout);
			return null;
		}

	}

	/**
	 * 
	 * @param timeout
	 * @param urlFraction
	 * @return
	 */

	public String waitForURLContainsAndCapture(int timeout, String urlFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
			String urlofpage = driver.getCurrentUrl();
			return urlofpage;
		} else {
			System.out.println("The url is not present within the given timeout:" + timeout);
			return null;
		}

	}

	/**
	 * 
	 * @param timeout
	 * @param url
	 * @return
	 */

	public String waitForURLToBeAndCapture(int timeout, String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.urlToBe(url))) {
			String urlofpage = driver.getCurrentUrl();
			return urlofpage;
		} else {
			System.out.println("The url is not present within the given timeout:" + timeout);
			return null;
		}

	}

	/**
	 * 
	 * @param timeout
	 * @param totalWindowsToBe
	 * @return
	 */

	public Boolean waitForTotalWindows(int timeout, int totalWindowsToBe) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.numberOfWindowsToBe(totalWindowsToBe));

	}

	/********************* Frames with Explicit Wait **********************/
	/**
	 * Explicitly wait with FluentWait features.
	 * 
	 * @param timeout
	 * @param pollingtime
	 * @param idOrName
	 */

	public void waitForFrameAndSwitchToItByIDOrNameWithFluentWait(int timeout, int pollingtime, String idOrName) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.ignoring(NoSuchFrameException.class).pollingEvery(Duration.ofSeconds(pollingtime))
				.withMessage("..............time out is done........frame not found");

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));

	}

	/**
	 * 
	 * @param timeOut
	 * @param idOrName
	 */

	public void waitForFrameAndSwitchToItByIDOrName(int timeOut, String idOrName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	/**
	 * 
	 * @param timeOut
	 * @param frameIndex
	 */

	public void waitForFrameAndSwitchToItByIndex(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	/**
	 * 
	 * @param timeOut
	 * @param frameElement
	 */

	public void waitForFrameAndSwitchToItByFrameElement(int timeOut, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	/**
	 * 
	 * @param timeOut
	 * @param frameLocator
	 */
	public void waitForFrameAndSwitchToItByFrameLoctor(int timeOut, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	/**
	 * Explicitly wait with Fluent Wait features.
	 * 
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible.Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @param pollingtime
	 * @return
	 */
	public WebElement waitForElementVisibleWithFluentWait(By locator, int timeout, int pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotInteractableException.class).pollingEvery(Duration.ofSeconds(pollingtime))
				.withMessage("..............time out is done........element not found");

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * Explicitly wait with Fluent Wait features.
	 * 
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeout
	 * @param pollingtime
	 * @return
	 */

	public WebElement waitForElementPresenceWithFluentWait(By locator, int timeout, int pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotInteractableException.class).pollingEvery(Duration.ofSeconds(pollingtime))
				.withMessage("..............time out is done........element not found");

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	// ******************************CUSTOM WAITS*****************************

	public WebElement retryElement(By locator, int timeout) {

		WebElement element = null;
		int retry = 0;

		while (retry < timeout) {
			try {
				element = driver.findElement(locator);
				System.out.println("The element using locator " + locator + " has been found in attempt " + retry);
				break;
			} catch (NoSuchElementException e) {
				System.out.println(" The element using the " + locator + " is not found...in attempt " + retry);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}

			retry++;
		}
		if (element == null) {
			System.out.println(
					"..element not found ...tried for " + timeout + " seconds,with poliing time of 500 milli seconds");
		}

		return element;

	}

	public WebElement retryElement(By locator, int timeout, int pollingtime) {

		WebElement element = null;
		int retry = 0;

		while (retry < timeout) {
			try {
				element = driver.findElement(locator);
				System.out.println("The element using locator " + locator + " has been found in attempt " + retry);
				break;
			} catch (NoSuchElementException e) {
				System.out.println(" The element using the " + locator + " is not found...in attempt " + retry);
				try {
					Thread.sleep(pollingtime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}

			retry++;
		}
		if (element == null) {
			System.out.println("..element not found ...tried for " + timeout + " seconds,with poliing time of "
					+ pollingtime + " milli seconds");
		}

		return element;

	}

	// *************************PAGE LOAD WAIT******************************

	public Boolean isPageLoaded() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == \'complete\'"))
				.toString(); // "true"
		return Boolean.parseBoolean(flag);// true
	}

	public void waitForPageLoad(int timeOut) {

		long endTime = System.currentTimeMillis() + timeOut;
		while (System.currentTimeMillis() < endTime) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String pageState = js.executeScript("return document.readyState").toString();
			if (pageState.equals("complete")) {
				System.out.println("PAGE DOM is fully loaded now....");
				break;
			} else {
				System.out.println("PAGE IS not loaded...." + pageState);
			}
		}

	}
}
