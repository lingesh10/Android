# Android
I created Search Bar look like Google play store.
Searched text via voice and type mode get toast.
Now working on showing last searched work in SuggestionsAdapter

Calling method in xml <br/>
<demo.com.app.GoogleSearchBar <br/>
                android:id="@+id/mt_search"  <br/>
                android:layout_width="match_parent"  <br/>
                android:layout_height="wrap_content"  <br/>
                android:layout_margin="8dp" />  <br/>
                
In MainActivity.java  <br/>
Find view by following code  <br/> 
GoogleSearchBar searchBar = findViewById(R.id.mt_search);  <br/>
        searchBar.setOnCallVoiceListener(this);  <br/>
        searchBar.setOnNavigationClickListener(this);  <br/>
        
Entire handling Showing in MainActivity.java Class  <br/>

Note: Working on suggesting showing where last searched in search bar.  <br/>

This is my first project uploading in github. Please bear with me.  <br/>
Thanks.  <br/>
