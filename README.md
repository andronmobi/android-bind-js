# JsWeb
An Android app showing how to bind JavaScript code to Android code. The html page (mobile.html) is locates in *assets* folder and it's displayed in a webview located on the activity layout by clicking on the 'SHOW INAPP' button.

The page uses jquery mobile. It's header is listed below:

```html
<html>
<head>
    <title>InApp Test</title>
    <link rel="stylesheet" type="text/css" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.css" />
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.js"></script>
</head>
```

The body contains three elements, the click event of witch is handled by JavaScript. The js code calls the methods of the *WebAppInterface* implemented in the *MainActivity*.
```html
<body>

<div data-role="page" style="background-color: lightskyblue">

    <div align="right">
        <a href="#" class="ui-btn ui-shadow ui-corner-all ui-icon-delete ui-btn-icon-notext" onClick="closeInApp()">Delete</a>
    </div>

    <div role="main" class="ui-content ">
        Hello world
        <form>
                <input type="checkbox" name="checkbox-mini-0" id="checkbox-mini-0" data-mini="true">
                <label for="checkbox-mini-0">I agree</label>
        </form>
    </div><!-- /content -->

    <a id="btn_settings" class="ui-btn-inline" data-role="button" data-icon="gear" onClick="openSettings()">Settings</a>
    <div align="center">
        Take a picture:
        <a class="ui-btn ui-corner-all ui-btn-inline ui-icon-camera ui-btn-icon-right" onClick="openCamera()">Camera</a>
    </div>

    <script>

        function closeInApp() {
            Android.closeInApp();
        }

        function openSettings() {
            Android.openSettings();
        }

        function openCamera() {
            Android.openCamera();
        }
    </script>

</div>
</body>
</html>
```

<p align="center" >
    <img src="screenshot.png?raw=true"> 
</p>

For more information check:
https://developer.android.com/guide/webapps/webview.html