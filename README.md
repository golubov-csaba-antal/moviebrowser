# Movie Browser

This is a simple, small application to browser through the contents from TMDB API. Currently it displays only the Trending Movies section, but as the development going forward, it will get more features.

The user can scroll through the displayed tray:

https://github.com/user-attachments/assets/70b74569-2024-435d-b44e-ed92d122a97b

The contents can be selected by touch to see their attributes:

https://github.com/user-attachments/assets/60655dcb-c04e-47d9-b521-942af3ea998a

The contents can be marked as favorite:

https://github.com/user-attachments/assets/12d59a28-6145-4db1-b310-d4aa04640901

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

## Planned changes

### Multiple trays

This is what will give the most to the app's landing screen. Currently we display only the Trending Movies screen, but as the API supports many-many different querries, we can expand the landing screen to display various contents.

### Different widgets

Not every tray should look alike, ja? Currently we display portrait posters, but why we cannot show landscape posters too, like for images and videos? And an artist could have a badge-like poster too.
