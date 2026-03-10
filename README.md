# Movie Browser

This is a simple, small application to browser through the contents from TMDB API.

The user can scroll through the displayed tray:

<img width="240" height="auto" alt="Screenshot_20260310_131730" src="https://github.com/user-attachments/assets/4a479db8-e53c-4eaf-98ed-3236869d8371" />

<img width="240" height="auto" alt="Screenshot_20260310_131738" src="https://github.com/user-attachments/assets/37947718-45a7-4884-9772-08d8b377d7e5" />


The contents can be selected by touch to see their attributes:

<img width="240" height="auto" alt="Screenshot_20260310_131844" src="https://github.com/user-attachments/assets/4699c651-81ba-4919-9981-261cb7bb26d0" />

<img width="240" height="auto" alt="Screenshot_20260310_131858" src="https://github.com/user-attachments/assets/40b8a1e6-7813-4d85-992f-05a97723bb2d" />


The contents can be marked as favorite:

<img width="240" height="auto" alt="Screenshot_20260310_131919" src="https://github.com/user-attachments/assets/6da60bb3-095b-452d-8dcf-c8df7eed7bff" />

<img width="240" height="auto" alt="Screenshot_20260310_131924" src="https://github.com/user-attachments/assets/5547fa06-95e7-48c0-96e0-f8f3a5812936" />


## Modules

The app structure is pretty standards: we have data, repository, backend, etc.

### App

The main entry point of the Android application, and where all the DI binders and providers are stored. Not to mention the Toolbar composable, and the navigation3 implementation.

### Core / Backend

The main implementation of the network calls. Currently we use TMDB, but later this could be expanded.

### Core / CommonUI

The commonly used composables and UI related utilities can be found here.

### Core / Data

This is where we have all the common used data classes.

### Core / Database

We use Room to save the favorite movies.

### Core / Repository

Currently we have only the MovieRepository here, which is used to provide the data from Database to ViewModels.

### Page / DetailPage

This screen displays the attributes of a content.

### Page / LandingPage

This is the main screen, where we list all the contents from the network providers.

## Screens

As the app does not support anything besides getting list of content and display the details of them, we have only two screens. Maybe this will change once in the future, but currently, I can do a lot of work on those screens even. :)

### Landing screen

This is the starting point of the application. The user can scroll through the listed trays, the contents in trays, and by touching one content, the app will take the user to the Detail screen.

### Detail screen

This is where we can see many attributes for the selected content. Even on this screen, we have a lot of possibilities to display extra contents, like related images and videos, the movies where an artist worked in, etc.

### Different widgets

Not every tray should look alike, ja? Currently we display portrait posters, but why we cannot show landscape posters too, like for images and videos? And an artist could have a badge-like poster too.
