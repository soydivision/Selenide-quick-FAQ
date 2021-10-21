# Selenide quick FAQ. 
This FAQ does not aim to be exhaustive reference. Rather a quick starter.
To get concise info please refer to [official](https://selenide.org/documentation.html) documentation.

- Should one restart a browser for each test? What is the best practice?
[Here](https://youtu.be/ePvrXUCeAr8?t=2306). Subtitles available.


- How does one use Jenkins with Selenide?
[Here](https://www.youtube.com/playlist?list=PLeeLZATMBcD8ayVX7VpYg-fLYG1dueL4Y). Subtitles available.

- How to clean browser cache and storage without restarting it?
```
@BeforeEach void resetBrowser() {
  Selenide.clearBrowserCookies();
  Selenide.clearBrowserLocalStorage();
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
$(Selectors.byXpath("//nav/ul/li/a[contains(@href,'new-hardware')]"));
```
or 
```
element(Selectors.byCssSelector("#bc-sf-filter-products article:nth-child(1)"));
```
How to start window maximized ?
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
executeJavaScript("window.scrollTo(0,document.body.scrollHeight);
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
element("#my-button").scrollIntoView(true);  // the top of the element will be aligned to the top
element(Selectors.byLinkText("Edit Interests")).scrollIntoView(false); // the bottom of the element will be aligned to the bottom

```
Custom timeout wait for a certain condition (for an element to exist in this example):
```
element(Selectors.byXpath("//*[text()=//*[@id='delete']")).shouldHave(Condition.exist, Duration.ofSeconds(5000));
```





