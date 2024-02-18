package com.example

data class ScientificResearchProjectsInputDto(val values: List<ScientificResearchProjectInputDto>) {

}

data class ScientificResearchProjectInputDto(val title: String) {

}
