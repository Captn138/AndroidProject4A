# AndroidProject4A
Par Mickael Almeida (classe 40)
## Présentation du projet
Ce projet de programmation mobile devait contenir un appel à une API REST afin d'afficher une liste d'éléments sur lesquels on peut cliquer pour avoir plus de détails. Il devait aussi contenir une base de donnée d'utilisateurs, avec une page de connexion/inscription et implémenter une clean architecture.

J'ai choisi d'utiliser une API recencontenant tous les acteurs ayant incarné le Docteur dans la série *Doctor Who*, disponible sur [ce site](https://api.catalogopolis.xyz/v1/doctors).
## Prérequis
* Télécharger [Android Studio](https://developer.android.com/studio)
## Installation
Pour tester l'application sur un PC, il faudra installer un émulateur Android avec un niveau d'API minimum de 26. Dans le fichier *build.gradle (app)*, il faudra rajouter les lignes suivantes dans les dépendances:
```java
    def koin_version = '2.1.6'
    def coroutine_version = '1.4.0'
    def lifecycle_version = '2.2.0'
    def room_version = '2.2.5'
    def material_version = '1.2.1'
    def mockk_version = '1.10.2'
    def recyclerview_version = '1.1.0'
    def retrofit_version = '2.6.0'
    def picasso_version = '2.71828'
    //KOIN
    implementation "org.koin:koin-core:$koin_version"
    implementation "org.koin:koin-androidx-ext:$koin_version"
    //KOTLIN COROUTINES
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    //ROOM
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    //MATERIAL
    implementation "com.google.android.material:material:$material_version"
    //MOCKK
    implementation "io.mockk:mockk:$mockk_version"
    //RECYCLERVIEW
    implementation "androidx.recyclerview:recyclerview:$recyclerview_version"
    //RETROFIT
    implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
```
Pour tester l'application sur un téléphone Android, on peut [télécharger et installer l'apk de l'application](https://github.com/Captn138/AndroidProject4A/releases/tag/1.0).

## Consignes respectées
* Langage Kotlin
* Nombre d'activités minimales
* Affichage d'une liste
* Possibilité de cliquer sur un objet pour plus de détails
* Récupération des données via une API REST
* Stockage et récupération des données en cache
* Clean architecture
* Splash screen
* Architecture MVC
* Affichage d'images
* Principe du singleton
* Support Dark Mode
## Fonctionnalités
### Premier écran
Le premier écran est un splash screen, c'est un écran qui permet d'afficher une interface pour l'utilisateur pendant que l'application se charge.
![splash](https://i.imgur.com/cs0KtyX.png)
### Deuxième écran
![login](https://i.imgur.com/L8C9fPL.png)
### Troisième écran
Le troisième écran est celui sur lequel on voit notre liste d'éléments.
![liste](https://i.imgur.com/5ISIPoJ.png)
### Quatrième écran
Le quatrième écran est celui sur lequel on peut voir des détails sur l'objet. *Item name* est le nom de l'objet, *under the set* est le type d'objet dont est dérivé l'objet sélectionné et *Minecraft item name* est le nom de l'objet pour le système, composé de som parent et d'un champ supplémentaire appelé *meta*.
![details](https://i.imgur.com/p69WpaF.png)
