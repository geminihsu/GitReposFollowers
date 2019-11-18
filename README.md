# GitReposFollowers App

This application shows the the followers of a given GitHub user in a grid page. User will able to enter the username on the top of search bar.
It implementing Kotlin + MVVM + Architecture components.

# Libray dependancy
* AndroidX RecyclerView
* com.google.android.material
* Glide
* LiveData + ViewModel + Coroutines
* Retrofit
* junit 
* Mockito 
 
# Folowers list ScreenShot
![alt text](https://github.com/geminihsu/GitReposFollowers/blob/master/screenshot/Screenshot_20191118-120352.png)

# UserDetail ScreenShot
![alt text](https://github.com/geminihsu/GitReposFollowers/blob/master/screenshot/Screenshot_20191118-115641.png)

# Folder structure
- [x] **model** : Follower and UserInfo collection property mapping with Dto response
- [x] **view** : Custom avatar view and Adatper object
- [x] **viewmodel** : Retain the list of Follower object and UserInfo object within Live data
- [x] **constan**t : Define constant variable
- [x] **network** : The network api function call and repository
- [x] **unitTest** : unit test for model, viewmodel, repository 
