package com.example

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {
    @Test
    fun ユースケースのテスト() {

        val sut = ScientificResearchProjectsUsecase()
        val actual = sut.execute()
        val expected = ScientificResearchProjectsDto(
            listOf(
                ScientificResearchProjectDto(
                    title = "研究課題1"
                )
            )
        )
        assertEquals(expected, actual)
    }

}