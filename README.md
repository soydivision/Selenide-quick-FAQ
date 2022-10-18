# Selenide quick FAQ. 
This FAQ does not aim to be exhaustive reference. Rather a quick starter.
To get concise info please refer to [official](https://selenide.org/documentation.html) documentation.
In order to execute given examples, valid libraries and dependencies _are to be imported_. Henceforth TestNG annotations are used as testing library.

- Should one restart a browser for each test? What is the best practice?
[Here](https://youtu.be/ePvrXUCeAr8?t=2306). Subtitles available.


- How does one use Jenkins with Selenide?
[Here](https://www.youtube.com/playlist?list=PLeeLZATMBcD8ayVX7VpYg-fLYG1dueL4Y). Subtitles available.

- How to clean browser cache and storage without restarting it?
```
@BeforeEach void resetBrowser() {
  open("about:blank");
  open("");
  Selenide.clearBrowserCookies();
  Selenide.clearBrowserLocalStorage();
  executeJavaScript("sessionStorage.clear();");
  refresh();	
}
```
- Is there a simple working example I can refer to?
[Yup.](https://github.com/selenide-examples/google)
- This is such a great project. How can I help?
  Yes. Raise awareness.

## Some of the most commonly used functions.

How to open a page:
```
@Test
public void someTest() {
    open("https://www.google.kz/");
}
```
How to find an element:
```
$(Selectors.byXpath("//button[contains(@class,'submit-btn')]"));
```
or 
```
element(Selectors.byCssSelector("#bc-sf-filter-products article:nth-child(1)"));
```
How to find element using __$__?
```
$("intro") for CSS.  
$x("//*[contains(@routerlink,'nsi')]") - for xpath. 
```
**Deprecated**
How to start window maximized?
```
Configuration.startMaximized = true; // in code
Dselenide.startMaximized = true; //system property
```
How to start window with custom size?
```
Configuration.browserSize = "2100x1080";
```
How to change / set a browser in code?
```
//(before open() method)
Configuration.browser = "firefox";
```
How to change / set a browser using system property?
``` 
-Dselenide.browser=edge
```

How to open a page in a new tab :
```
executeAsyncJavaScript("window.open(\"http://www.amazon.com\");");
```
How to switch between tabs :
```
Selenide.open("https://bing.com/");
executeJavaScript("window.open('about:blank','_blank')");
Selenide.switchTo().window(1); 
open("https://google.kz/"); //  opening an url on a newly created tab
Selenide.switchTo().window(0); // going back to first window
```
How to make a screenshot?
```
// Set the screenshot folder in your code
Configuration.reportsFolder = "test-result/screenshots"; 
String pngFileName = screenshot("my_screenshot_file_name");
```

Click page element (in this case found by Xpath):
```
element(Selectors.byXpath("//*[contains(text(),'Some text on the button')]")).click();
```

Check if page element exists (in this case is visible):
```
element(Selectors.byXpath("//h2[contains(., 'Some visible text in an h2 text')]"))
.shouldBe(Condition.visible);
```

Launch headless browser using system propetry:
```
-Dselenide.headless=true
```

Launch headless browser setting an option from code:
```
Configuration.headless = true;
```
Custom timeout (5000 milliseconds)
```
Configuration.timeout = 5000;
```

URL manipulations:
```
String url1 = WebDriverRunner.url();
String url2 = WebDriverRunner.currentFrameUrl();
```
Check url contents:
```
webdriver().shouldHave(url("https://auth.google.com"));
webdriver().shouldHave(url("https://mastercard.ee"), Duration.ofSeconds(42));
webdriver().shouldNotHave(url("http://yandex.ru");
webdriver().shouldNotHave(urlStartingWith("ftp://"));
webdriver().shouldHave(currentFrameUrl(baseUrl + "/login.html"));
webdriver().shouldHave(currentFrameUrlStartingWith(baseUrl + "/logout.html"));
```
Clipboard:
```
Clipboard clipboard = Selenide.clipboard();
String foo = clipboard().getText();
clipboard().setText("bar");
```
Check clipboard contents:
```
clipboard().shouldHave(content("Hello fast World"));
clipboard().shouldHave(content("Hello slow World"), Duration.ofMillis(1500));
```
Check local storage:
```
localStorage().shouldHave(item("cat”));
localStorage().shouldHave(itemWithValue("mouse", "Jerry”));
localStorage.getItems();
```
Check session storage:
```
sessionStorage().shouldHave(item("cat”));
sessionStorage().shouldHave(itemWithValue("mouse", "Jerry”));
Map<String, String> items = sessionStorage.getItems();
```
Download element:
```
$.download()
or 
element.download()
```
Check if element ($) contains certains text, exists or visible:
```
element(Selectors.byCssSelector("#collection")).shouldNotHave(Condition.text(" Some Text Here "));
element(Selectors.byCssSelector("#collection")).shouldNotHave(Condition.exist);
element(Selectors.byCssSelector("#collection")).shouldNotHave(Condition.visible);
```
How to input value and press enter:
```
element(Selectors.byCssSelector("input[name='searchTerm']")).setValue("Ford mustang 2020").pressEnter();
```
How to check page title:
```
element("title").shouldHave(attribute("text", "Your page title"));
or
$("title").shouldHave(attribute("text", "Your page title"));
```
How to scroll down:
```
executeJavaScript("window.scrollTo(0,document.body.scrollHeight)")
```
How to scroll down dynamically loading page (my method, use at your own risk):
```
public static void scrollUntilElementExists(SelenideElement element) {
        while (!element.exists()) {
            executeJavaScript("window.scrollTo(0,document.body.scrollHeight);");           
        }
    }
```
How to scroll:
```
// the top of the element will be aligned to the top:
element("#my-button").scrollIntoView(true); 
// the bottom of the element will be aligned to the bottom: 
element(Selectors.byLinkText("Edit Interests")).scrollIntoView(false);
```
Custom timeout wait for a certain condition (for an element to exist in this example):
```
element(Selectors.byXpath("//*[text()=//*[@id='delete']"))
.shouldHave(Condition.exist, Duration.ofSeconds(5000));
```
element.click() does not work, what should I do? Try using actions() following code:
```
SelenideElement element = $(<some selector>);
actions().moveToElement(element).click(element).perform();
```
Click using JS:
```
element(Selectors.byAttribute("id", "name")).click(ClickOptions.usingJavaScript());
```

How to drag-and-drop using selenide? You can use something [like](https://github.com/Yastreba/cucumber/blob/4de8045596107f72aacb8623e09c028be1cc4065/src/main/java/pages/DragAndDropPage.java):
```
private SelenideElement bank = $x("//*[@id='credit2']/a");
private SelenideElement debitSideAccountSell = $x("//*[@id='bank']/li");
//...
public void bankToDebitAccount() 
{
   (bank).dragAndDropTo(debitSideAccountSell);
}

```
Or:
```
SelenideElement element = <Some selector>;
actions().dragAndDropBy(element, xOffset, yOffset).perform();
```
Check element:
```
SelenideElement title = element(Selectors.byAttribute("id", "title"));
SomePage.Title.shouldHave(Condition.exist);
SomePage.Title.shouldHave(Condition.visible);
SomePage.Title.shouldHave(Condition.enabled);
```
How to start a new browser for each test? TestNG scenario:
```
@AfterClass(alwaysRun = true)
{
 WebDriverRunner.closeWebDriver();
}

```
How to stop execution, wait, sleep?  
Commentary:as of 6.0.1 sleep() method "is guaranteed to wait at least given number of milliseconds", because it doesn't use standard Java implementation which can "wake up earlier".  
See docs or source code for detailed information. 
```
Selenide.sleep(n);
// n ~ milliseconds
```
Keep the browser opened after test execution:  
```
-Dselenide.holdBrowserOpen=true
```
Press key:
```
Selenide.actions().sendKeys(Keys.HOME).perform();
```








