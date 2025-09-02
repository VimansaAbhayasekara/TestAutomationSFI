@echo off
echo Setting up environment variables for test automation...
set CHROME_DRIVER_PATH=drivers\chromedriver.exe
set BROWSER=chrome
set HEADLESS=false
echo Environment variables set successfully!
echo.
echo Please download ChromeDriver 139.0.7258.140 from:
echo https://storage.googleapis.com/chrome-for-testing-public/139.0.7258.140/win64/chromedriver-win64.zip
echo.
echo Extract chromedriver.exe to the 'drivers' folder
pause