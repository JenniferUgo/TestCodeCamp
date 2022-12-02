import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumWebBlogTest {

     //Import Selenium Webdriver
     private WebDriver driver;

     @BeforeTest
     public void setUp() throws InterruptedException {
          //locate where the chromedriver is and set it up
          System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
          //1. Open new Chrome browser
          driver = new ChromeDriver();
          //2. Input the URL you're testing
          driver.get("https://selenium-blog.herokuapp.com");
          //3. Maximize window
          driver.manage().window().maximize();
          Thread.sleep(5000);

          String expectedUrl = "https://selenium-blog.herokuapp.com";
          //Test 1. Verify that the user has input the right url and is on the right webpage
          if (driver.getCurrentUrl().contains(expectedUrl)) {
               //pass
               System.out.println("Correct Webpage");
          } else {
               //fail
               System.out.println("Wrong Webpage");
          }
     }

          @Test(priority = 0)
          public void signUpButton() throws InterruptedException {
               // 4. Click on the SignUp button to open the signup page
               driver.findElement(By.xpath("/html/body/div[2]/div/a[2]")).click();
               Thread.sleep(5000);

          //Test 2. Verify that when user clicks on the signup button, the user is directed to the signup page
          String expectedURL = "https://selenium-blog.herokuapp.com/signup";
          String actualURL = driver.getCurrentUrl();
          if (actualURL.contains(expectedURL)){
               System.out.println("Correct signup page");
          } else {
               System.out.println("Wrong signup page");
          }

     }
     @Test (priority = 1)
     public void negativeSignupInvalidUsername() throws InterruptedException {
          String signUpPage = "https://selenium-blog.herokuapp.com/signup";
          if (driver.getCurrentUrl().equals(signUpPage)) {
               //Refresh the page to reset all input fields
               driver.navigate().refresh();
          } else {
               driver.navigate().to(signUpPage);
          }

          // 5. Locate the username field and Input your username
          driver.findElement(By.name("user[username]")).sendKeys("J4");
          //6. Locate the email address field and Input your email address
          driver.findElement(By.name("user[email]")).sendKeys("jennee30@testemail.com");
          //7. Locate the password field and Input your password
          driver.findElement(By.name("user[password]")).sendKeys("password");
          //8. Click on the SignUp button to submit
          driver.findElement(By.id("submit")).click();
          Thread.sleep(5000);

          //Test 3. Verify that user cannot sign up with username less than 3 characters
          WebElement actualError = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div/div"));
          String actualErrorMessage = actualError.getText();
          // Verifying if an error message is displayed
          if (actualError.isDisplayed()) {
               System.out.println("Sign up failed");
          } else {
               System.out.println("Sign up is successful");
          }
     }

     @Test (priority = 2)
     public void negativeSignupInvalidEmail() throws InterruptedException {
          String signUpPage = "https://selenium-blog.herokuapp.com/signup";
          if (driver.getCurrentUrl().equals(signUpPage)) {
               //Refresh the page to reset all input fields
               driver.navigate().refresh();
          } else {
               driver.navigate().to(signUpPage);
          }

          // 5. Locate the username field and Input your username
          driver.findElement(By.name("user[username]")).sendKeys("Jennee1");
          //6. Locate the email address field and Input your email address
          driver.findElement(By.name("user[email]")).sendKeys("jennee30testemail.com");
          //7. Locate the password field and Input your password
          driver.findElement(By.name("user[password]")).sendKeys("password");
          //8. Click on the SignUp button to submit
          driver.findElement(By.id("submit")).click();
          Thread.sleep(5000);

          //Test4. Verify that user cannot sign up with invalid email address
          // Verifying if an error message is displayed
          WebElement emailValidation = driver.findElement(By.name("user[email]"));
          String actualValidationErrorMessage = emailValidation.getAttribute("validationMessage");
          if (emailValidation.isDisplayed()) {
               System.out.println("Sign up failed");
          } else {
               System.out.println("Sign up is successful");
          }
     }

     @Test (priority = 3)
     public void negativeSignupInvalidPassword() throws InterruptedException {
          String signUpPage = "https://selenium-blog.herokuapp.com/signup";
          if (driver.getCurrentUrl().equals(signUpPage)) {
               //Refresh the page to reset all input fields
               driver.navigate().refresh();
          } else {
               driver.navigate().to(signUpPage);
          }

          // 5. Locate the username field and Input your username
          driver.findElement(By.name("user[username]")).sendKeys("Jennee1");
          //6. Locate the email address field and Input your email address
          driver.findElement(By.name("user[email]")).sendKeys("jennee0@testemail.com");
          //7. Locate the password field and Input your password
          driver.findElement(By.name("user[password]")).sendKeys("p");
          //8. Click on the SignUp button to submit
          driver.findElement(By.id("submit")).click();
          Thread.sleep(5000);

          //Test 5. Verify user cannot log in with password less than or equal to 1

          // Verifying if an error message is displayed
          WebElement actualError = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div/div"));
          String actualErrorMessage = actualError.getText();
          String expectedErrorMessage = "Password can't be blank";
          if (actualError.isDisplayed()) {
               System.out.println("Sign up failed");
          } else {
               System.out.println("Sign up is successful");
          }
     }

     @Test (priority = 4)
     public void negativeSignupInvalidEmptyFields() throws InterruptedException {
          String signUpPage = "https://selenium-blog.herokuapp.com/signup";
          if (driver.getCurrentUrl().equals(signUpPage)) {
               //Refresh the page to reset all input fields
               driver.navigate().refresh();
          } else {
               driver.navigate().to(signUpPage);
          }
          // 5. Locate the username field and Input your username
          driver.findElement(By.name("user[username]")).sendKeys("Jennee");
          //6. Locate the email address field and Input your email address
          driver.findElement(By.name("user[email]")).sendKeys("");
          //7. Locate the password field and Input your password
          driver.findElement(By.name("user[password]")).sendKeys("");
          //8. Click on the SignUp button to submit
          driver.findElement(By.id("submit")).click();
          Thread.sleep(5000);

          //Test 6. Verify user cannot sign up with either/all the fields bLank

          // Verifying if an error message is displayed
          WebElement actualError = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div/div"));
          String actualErrorMessage = actualError.getText();
          String expectedErrorMessage = "Email can't be blank" +
                  "Email is invalid" +
                  "Password can't be blank";
          if (actualError.isDisplayed()) {
               System.out.println("Sign up failed");
          } else {
               System.out.println("Sign up is successful");
          }
     }

     @Test (priority = 5)
     public void positiveSignup() throws InterruptedException {
          String signUpPage = "https://selenium-blog.herokuapp.com/signup";
          if (driver.getCurrentUrl().equals(signUpPage)) {
               //Refresh the page to reset all input fields
               driver.navigate().refresh();
          } else {
               driver.navigate().to(signUpPage);
          }
                   //5. Locate the username field and Input your username
          driver.findElement(By.name("user[username]")).sendKeys("Jennee56");
                  //6. Locate the email address field and Input your email address
          driver.findElement(By.name("user[email]")).sendKeys("jennee46@testemail.com");
                  //7. Locate the password field and Input your password
          driver.findElement(By.name("user[password]")).sendKeys("testpassword");
                 //8. Click on the SignUp button to submit
          driver.findElement(By.id("submit")).click();
          Thread.sleep(5000);

          //Test 7. Verify that user is successfully signed up when valid details are inputted
          String actualURL = driver.getCurrentUrl();
          String expectedURL = "https://selenium-blog.herokuapp.com/users";
          if (expectedURL.equals(actualURL)) {
               System.out.println("Sign up was successful");
          } else {
               System.out.println("Signup failed");
          }
     }

     @Test (priority = 6)
     public void locateUser1Item() throws InterruptedException {
          //9. Click on user1 item on the list page
         WebElement user1Item = driver.findElement(By.xpath("//div[2]/div[1]/ul/div/div/li[1]/a"));
          //Test 8. Verify that User1 item is present on the item List page.
          String expectedUrl = "https://selenium-blog.herokuapp.com/signup";
          String actualUrl = driver.getCurrentUrl();
          if(actualUrl.contains(expectedUrl))
               //pass
               System.out.println("Correct Webpage");
          else
               //fail
               System.out.println("Wrong Webpage");
          user1Item.click();
          Thread.sleep(5000);
     }

     @Test (priority = 7)
     public void searchItem() throws InterruptedException {
          //10. Search for an element (Learn Xpath) and confirm that it's present
          WebElement searchedItem = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div[1]/a"));

          //Test 9.Verify that the item searched for is present.
          String expectedItemName = "Learn XPath";
          String actualItemName = searchedItem.getText();
          if (expectedItemName.equals(actualItemName)) {
               System.out.println("Searched item is present");
          } else {
               System.out.println("Searched item is not present");
          }
          //Click on the searched Item to view its content
          searchedItem.click();
          Thread.sleep(5000);
     }

     @Test (priority = 8)
             public void logoutSuccessfully() {
          //11. Click on logout button
          driver.findElement(By.xpath("//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[3]/a")).click();

          //Test 10. Verify that when the user logs out, user is directed back to the home page
          String expectedTitle = "Alpha Blog";
          String actualTitle = driver.getTitle();
          if (expectedTitle.contains(actualTitle))
               //pass
               System.out.println("Correct Title");
          else
               //fail
               System.out.println("Wrong Title");
     }
          @AfterTest
          public void closeBrowser () {
          //12. Quit the browser
               driver.quit();
          }
     }

