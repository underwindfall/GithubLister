# GithubLister

This project is coding based on mvvm clean architecure.

## Architecture

### Core
This package include all Extension,DI and BaseController(Activity,Fragment,ViewModel,Adapter)

### Ui/Feature layer 
Feature Layer is using mvvm to bind viewmodel with lifeCycleOwner controller. The controller will use viewmodel function to get
data from repository.

LifeCycleOwner Controller could have different BehaviorObservers to implement different behavior into lifecycle.
For example,
ReactiveBehavior provides a CompositeDisposable to add different Observer actions.


### Repository layer
Repository layer is an intermidate layer between ui feature and dataSource. The role of this layer is to build usecase and respository doing bussiness logic.
It's very flexiable and extendable, could add LocalDataCache system in the future if we really need that.

### DataSource layer 
The Remote DataSource layer is tring to fetch data from network.

### DataFlow

![](https://raw.githubusercontent.com/underwindfall/blogAssets/master/blog/cleanArchitecture/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-08-16%2011.47.06.png)

