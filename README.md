# cartrack-user
This Application is speifically designed for the Cartrack users. Once they sign-in to the app they can see the list of customers and details of them including their location on the Map. User of this app can quickly call\email them and view their website easily.

#### Breif Description about app
- User can login to the app(User details are configured and saved in the Database securely).
- Upon successful login user can see list of the customers with their phone number and email.
- When selecting any user it will open the customer user details screen, there you can see the detailed info of the customer.
- We have given a 'country picker' in the log-in screen to give the best and customized experience to the user.



#### Note:
- We used a Country list json `CountryList.json` to show the list of countries on spinner and country images we are fetching from this (https://www.countryflags.io/sg/flat/64.png) api by passing the country code. Check the `ImageBinder`

- We used a third party library to show the GIF ont he splash screen and we put a `5s delay` in splashscreen before navigating to home screen.
- In the Map we customized the 'Marker'
- We added a Custom Info window adapter in map to show the details when the user clicking on the marker.
- We used the Auto link built-in option for email\call\web.
