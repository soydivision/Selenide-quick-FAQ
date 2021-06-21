# Selenide quick FAQ.
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
[Yup](https://github.com/selenide-examples/google)
- This is such a great project. How can I help?
Yes. Raise awareness.