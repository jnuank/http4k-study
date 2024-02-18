package com.example

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {
    @Test
    fun ユースケースのテスト() {
        val driver = mockk<ScientificResearchPort>()
        val dto = ScientificResearchProjectsInputDto(
            listOf(
                ScientificResearchProjectInputDto(
                    title = "研究課題1"
                )
            )
        )
        every { driver.scientificResearchProjectsById() } returns dto
        val sut = ScientificResearchProjectsUsecase(driver)
        val expected = ScientificResearchProjectsDto(
            listOf(
                ScientificResearchProjectDto(
                    title = "研究課題1"
                )
            )
        )
        assertEquals(expected, sut.execute())
    }

    @Test
    fun ユースケースのテスト2() {
        val driver = mockk<ScientificResearchPort>()
        val dto = ScientificResearchProjectsInputDto(
            listOf(
                ScientificResearchProjectInputDto(
                    title = "研究課題2"
                )
            )
        )
        every { driver.scientificResearchProjectsById() } returns dto
        val sut = ScientificResearchProjectsUsecase(driver)
        val expected = ScientificResearchProjectsDto(
            listOf(
                ScientificResearchProjectDto(
                    title = "研究課題2"
                )
            )
        )
        assertEquals(expected, sut.execute())
    }
}