package com.example.smarthome.domain

import com.example.smarthome.ui.data.listClasses.Light

class LightsDataSource {
    fun getLights(): List<Light> {
        return listOf(
            Light("Light 1", "Place 1", true),
            Light("Light 2", "Place 2", false),
            Light("Light 3", "Place 3", true),
            Light("Light 4", "Place 4", true),
            Light("Light 5", "Place 1", true),
            Light("Light 6", "Place 2", false),
            Light("Light 7", "Place 3", true),
            Light("Light 8", "Place 4", true),
            Light("Light 1", "Place 1", true),
            Light("Light 2", "Place 2", false),
            Light("Light 3", "Place 3", true),
            Light("Light 4", "Place 4", true),
            Light("Light 5", "Place 1", true),
            Light("Light 6", "Place 2", false),
            Light("Light 7", "Place 3", true),
            Light("Light 8", "Place 4", true),
        )
    }
}