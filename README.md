# Android
I created Search Bar look like Google play store.
Searched text via voice and type mode get toast.
Now working on showing last searched work in SuggestionsAdapter

Calling method in xml
<demo.com.app.GoogleSearchBar
                android:id="@+id/mt_search"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="8dp" />
                
In MainActivity.java
Find view by following code 
GoogleSearchBar searchBar = findViewById(R.id.mt_search);
        searchBar.setOnCallVoiceListener(this);
        searchBar.setOnNavigationClickListener(this);
        
Entire handling Showing in MainActivity.java Class

Note: Working on suggesting showing where last searched in search bar.

This is my first project uploading in github. Please bear with me.
Thanks.
