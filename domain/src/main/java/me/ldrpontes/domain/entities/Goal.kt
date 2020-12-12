package me.ldrpontes.domain.entities

data class Goal(val id: String, var name: String, var totalBalance: Double, var goalAmount: Double?, var goalDate: String, var background: Background)
