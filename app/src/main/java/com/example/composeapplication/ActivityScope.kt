package com.example.composeapplication

import org.koin.core.qualifier.named
import org.koin.dsl.module

val activityScope = module {
    scope<MainActivity> {
        /**
         * the string will live as long as MainActivity is alive and
         * when activity destroyed, the string will be garbage collected.
         */
        scoped(qualifier = named("HELLO")) { "Hello" }
        scoped(qualifier = named("BYE")) { "Bye" }
    }
}