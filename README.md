# RecursiveWebTraverse
This is an application that recursively find the URLs of the webside defined in Constants.class.
But having said that, rather than obtainind all the URLs, it gets urls containings specific String "This string is also defined in Constants.class".
For getting them I used parallel Stream, synchronization and recursive algorithmn. By that, two static variables, One for Urls and one for Directory informations
are filled.
After obtaining this Directory informations, downloading starts which download ".html" files.Here I used multithreading since non of these websites are 
dependent.
In the application there are interfaces which can be useful for maintaining and expanding the applicationion.
Since this application is written in a java core we need to handel dependency injection by our self to have loosely coupled application.
Additionally, there are different tests which can be used for authomatically find the bugs, However to test, it is better to use the website which you are 
familiare with, for example you know the number of urls it has and ecs.
