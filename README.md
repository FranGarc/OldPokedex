# Pokedex

POC app using [pokemon api](https://pokeapi.co/docs/v2) to practice android/kotlin

## Table of contents

* [General info](#general-info)
* [Patterns](#patterns)
* [Technologies](#technologies)
* [Future iterations](#future-iterations)
* [Credits](#credits)

## General info

This project is simple consumer of the [pokemon api](https://pokeapi.co/docs/v2), from where it will
obtain data.

![Master list screen](https://github.com/FranGarc/Pokedex/blob/main/screenshots/list_first_version.png)
![Detail screen](https://github.com/FranGarc/Pokedex/blob/main/screenshots/detail_first_version.png)



## Patterns

* MVVM
* Repository pattern

## Technologies

Project is created with:

* Retrofit
* LiveData
* XML layouts
* Navigation component

## Future iterations

* Add "captured pokemon" and saving them using Room
* Use Dagger Hilt for Dependency Injection
* Change LiveData to Flow
* Change XML layouts to Jetpack Compose

## Credits

* Pokemon type icons from https://github.com/duiker101/pokemon-type-svg-icons
