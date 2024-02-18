package com.example

class researchProjectsUsecase() {
    fun execute(): ResearchProjectsDto {
        val expected = ResearchProjectsDto(
            listOf(
                ResearchProjectDto(
                    title = "研究課題1"
                ),
                ResearchProjectDto(
                    title = "研究課題3"
                ),
                ResearchProjectDto(
                    title = "研究課題5"
                ),
            )
        )
        return expected
    }
}
