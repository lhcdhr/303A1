# Movie Library Version 1
The 1st assignment of COMP303 Software Design
## Features
1. Library - made up of movies and watchlists
2. Movie - each movie corresponds to a path that can never be changed after creation, pointing to the video file. Formats of the file are limited to MP4, MOV, WMV, AVI, FLV, or MKV, and creating movie using other formats of files is not possible. If the file exist, the movie is valid, otherwise it is invalid. This state can be changed when the file is restored or deleted.
3. Watchlist - contains a sequence of movie, identifies by its name. The first movie being watched can be removed. Client also have access to all the movies in the watchlist. The client will know how many valid movies are in this watchlist.
## Diagram
Object Diagram
![alt text](https://github.com/lhcdhr/Movie-Library-V1-Software-Design/blob/main/A1.object.png)
