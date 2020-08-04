package com.cartrack.users.data.model

class CountryList : ArrayList<CountryListItem>()

data class CountryListItem(
    val code: String,
    val dial_code: String,
    val name: String
)