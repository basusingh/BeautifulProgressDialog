# BeautifulProgressDialog
Brush up your mobile app with customized Progress Dialog. Beautiful Progress Dialog is a small library that let you show custom Progress Dialog into your app. It can show static images, GIFs and the new Lottie Animation.

To add BeautifulProgressDialog into youyr app,
Step 1. Add the JitPack repository to your build file
```
allprojects {
        repositories {
			...
            maven { url 'https://jitpack.io' }
        }
    }
  ```
  Step 2. Add the dependency
  ```
  dependencies {
	        implementation 'com.github.basusingh:BeautifulProgressDialog:1.001'
	}
  ```
 Current Version:  [![](https://jitpack.io/v/basusingh/BeautifulProgressDialog.svg)](https://jitpack.io/#basusingh/BeautifulProgressDialog)
 
 **##Create a Progress Dialog**
 ________________________________________________
 **###Create a dialog**
 ```
 //Params: Context, View Type, Text Message
 BeautifulProgressDialog progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withImage, "Please wait");
 ```
 View Type:
 ```
BeautifulProgressDialog.withImage
BeautifulProgressDialog.withGIF
BeautifulProgressDialog.withLottie
```


**###Create an Image Dialog with Text Message**
```
progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withImage, "Please wait");
progressDialog.setImageLocation(getResources().getDrawable(R.drawable.burger_logo));
progressDialog.setLayoutColor(getResources().getColor(R.color.cream));
```

**###Create a GIF Dialog without Text Message**
```
progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withGIF, null);
Uri myUri = Uri.fromFile(new File("//android_asset/sample_gif_1.gif"));
progressDialog.setGifLocation(myUri);
```

**###Create a Lottie Animation Dialog without Text Message**
```
progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withLottie, null);
progressDialog.setLottieLocation("lottie_1.json");
//Loop the Lottie Animation
progressDialog.setLottieLoop(true);
```

**###Show the Dialog**
```
progressDialog.show();
```

**#Dismiss the Dialog**
```
progressDialog.dismiss();
```

_________________________________________________

**##Additional Style**

**###Set Background Color**
```
progressDialog.setLayoutColor(getResources().getColor(R.color.MY_COLOR_NAME));
```

**###Change View Type**
```
progressDialog.setViewType(BeautifulProgressDialog.withGIF);
```
**Don't forget to change the source of each view type**

Available method:
```
//Set Image Resource
setImageLocation(Drawable drawable)
setImageLocation(Bitmap bitmap)
setImageLocation(Uri location)

//Set GIF Resource
setGifLocation(Uri gifLocation)

//Set Lottie Resource
setLottieLocation(String url)
```

**###Custom font for the message**
```
//By default, the font is avenir_bold
setFont(String font)
```

**###Change message**
```
setMessage(String text)
```

**###Remove message**
```
removeMessage()
```

**###Additional Methods:**
```
//Dialog cancelable on touch outside
setCancelableOnTouchOutside(boolean value)
```








Please feel free to add new features to it. 
I'm currently adding a Progress Bar and rounded corners to the view.

Cheers!
