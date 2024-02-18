package com.example

data class ResearchProjectsInputDto(val values: List<ResearchProjectInputDto>) {

}

data class ResearchProjectInputDto(val title: String) {

}
