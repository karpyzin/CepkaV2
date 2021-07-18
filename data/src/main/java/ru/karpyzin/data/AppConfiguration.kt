package ru.karpyzin.data

import javax.inject.Inject

class AppConfiguration @Inject constructor() {
    val baseHost = "https://api.themoviedb.org/"
}