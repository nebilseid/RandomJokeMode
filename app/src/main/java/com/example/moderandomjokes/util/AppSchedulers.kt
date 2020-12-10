package com.example.moderandomjokes.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulers {
    companion object {
        private var androidUI: Scheduler? = null
        var io: Scheduler = Schedulers.io()
        var ui: Scheduler
            get() = androidUI ?: AndroidSchedulers.mainThread()
            set(value) {
                androidUI = value
            }
    }
}