package com.example

class ScientificResearchProjectsUsecase {
    fun execute(): ScientificResearchProjectsDto {
        val expected = ScientificResearchProjectsDto(
            listOf(
                ScientificResearchProjectDto(
                    title = "研究課題1"
                )
            )
        )
        return expected
    }

}
