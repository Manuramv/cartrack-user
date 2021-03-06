# Cartrack-user
This Application is speifically designed for the Cartrack users. Once they sign-in to the app they can see the list of customers and details of them including their location on the Map. User of this app can quickly call\email them and view their website easily.

#### Breif Description about app
- User can login to the app(User details are configured and saved in the Database securely).
- Upon successful login user can see list of the customers with their phone number and email.
- When selecting any user it will open the customer user details screen, there you can see the detailed info of the customer.
- We have given a 'country picker' in the log-in screen to give the best and customized experience to the user.

![Car Track user](https://user-images.githubusercontent.com/31012185/89326480-f0c72480-d6bc-11ea-9edf-9bd21886faca.gif)
*Fig. 1: Demonstrating App*

![Car Track user](https://user-images.githubusercontent.com/31012185/89326574-148a6a80-d6bd-11ea-9700-abe97df25834.gif)
*Fig. 2: Demonstrating App*

- Error Cases
![Car Track user](https://user-images.githubusercontent.com/31012185/89327979-4d2b4380-d6bf-11ea-8640-ce512e7ba667.png)
*Fig. 3: Login Error*

![Car Track user](https://user-images.githubusercontent.com/31012185/89327983-4e5c7080-d6bf-11ea-976c-0e7d024aaced.png)
*Fig. 4: Netowrk Error*



#### Note:
- I've added a userlist json file in Assests folder and reading that on the app launch and inserting to the 'Room' database. If you want to add any users pls add to the `UserList.json`. Currently following users are in the json file and has the access to the app.

| User Name           | Password      |
| ------------------- | ------------- |
| user1@cartrack.com  | Cartrack@123  |
| user2@cartrack.com  | Cartrack@222  |
| user3@cartrack.com  | Cartrack@333  |
| karen@cartrack.com  | Cartrack@123  |
| manu@cartrack.com   | Cartrack@123  |


- We used a Country list json `CountryList.json` to show the list of countries on spinner and country images we are fetching from this (https://www.countryflags.io/sg/flat/64.png) api by passing the country code. Check the `ImageBinder`


- We used a third party library to show the `GIF` on the splash screen and we put a `5s delay`(To make the gif visible) in splashscreen before navigating to home screen.
- In the Map we customized the `Marker`
- We added a `Custom Info window adapter` in map to show the details when the user clicking on the marker.
- We used the Auto link built-in option for email\call\web.

```diff
- IMPORTANT: Note that our API is not returning the valid lat and lang so MAP in not able to show the proper location. You can check that in the attached images I've given the valid latitude and langitude and shown the working map.
```

# Built With
  Kotlin

# Architectural Pattern
- Used MVVM as the code can be easily reused and binding makes UI updates easier to handle. 
- Used `JetPack` components and followed the best practices to develp this app.
- This architecture makes the code more modular so maintainability of code in future will be more easy
- `LiveData` - **LiveData** to update the UI automatically when the data updates. We Used LiveData extensively to communicate between view and viewmodel. Whenever the API call is success or currecny conversion is success it will update the UI automatically according to our design.
- We are using the Room database for the local storage.
- `Navigation Graph` for navigate between fragments



#  Data Binding & 2 Way data Binding
- I've followed the data binding approach to bind the views with classes and view models,so that I can perform the UI actions with less code and directly from viewmodel.We have extensively used data binding in this application wherever it's possible. 
- I also used the 2 way binding for the Edittexts and Spinners to improve app's efficiency. 
- My Activity class and Spinner Adapter looks very clean.
- `Binding Adapters` and `Inverse Binding Adapters` used wherever it's possible, especially on setting the `data and image` at spinner, `validation check` in login page, `Mapinfo Window`.
    
# Other Important Libraries Used:
- `Retrofit` - For networking calls
- `Coroutines` - Used to manage Asynchronous operations also to communicate between repository and view model.
- `Glide` - To Render the Image.
 # Packages in the App:
    - Data - Room Database ,Retrofit API related class and repository classes under sub repositroy project.
    - Model - Model classes and kotlin data classes
    - UI - Views and Viewmodels and Adapters specific to the screen.
    - Utils - Validation utils, Constants and Alert Classes.
    
# Min Supported version
Android 19(4.4)

# Further Improvements:
Now, let's check what we can improve in the feature for this app.

- Spinner UI is not perfect now, I dind't pay much attention to improve this now. We can make it better later.
- Orientation Chanes are working but we can add the scrollview and make it better.
- We can add the test cases. (Due to the lack of time I dind't add test cases at the moment).
