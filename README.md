# captcha-generator
## Simple JAVA / C captcha generator (CS course assigment)


**Generates 0 - 10 digit long captchas with preset blur values**

**Java with javaFX (MVC design pattern) was used to create user inteface, C was used to create bmp generator**

**In order to make this app working, compile and save files in the same directory as:**

-CaptchaGenerator.jar (Java folder)

-CaptchaGenerator.exe (C folder)

-Debug.bat (optional, opens console to get System.out.print messages) (bash folder)


**You can also use CaptchaGenerator.exe as a standalone app. Start it from command prompt and give it two arguments in following order:**

-number of digits

-blur value

**and wait until it finishes creating output.bmp.**


## How does it work?

User chooses desired settings in javaFX application, after clicking generate CaptchaGenerator.exe file is launched with two arguments:

-number of digits which specifies the length of generated captcha

-blur (mess value) which specifies how hard it will be to read captcha

CaptchaGenerator.exe creates output.bmp with n blurred random numbers inside and prints a correct answer. Java application waits for ouput.bmp and reads .exe console. Picture is displayed in ImageView and console output is used to verify if user entered correct string. Saving file renames output.bmp to *captchaanswer*_captcha.bmp. On exit ouput.bmp is deleted.
