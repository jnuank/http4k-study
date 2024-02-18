package com.example

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {
    @Test
    fun ユースケースのテスト() {
        val driver = mockk<ResearchPort>()
        val dto = ResearchProjectsInputDto(
            listOf(
                ResearchProjectInputDto(
                    title = "研究課題1"
                )
            )
        )
        every { driver.researchProjectsById() } returns dto
        val sut = ResearchProjectsUsecase(driver)
        val expected = ResearchProjectsDto(
            listOf(
                ResearchProjectDto(
                    title = "研究課題1"
                )
            )
        )
        assertEquals(expected, sut.execute())
    }

    @Test
    fun ユースケースのテスト2() {
        val driver = mockk<ResearchPort>()
        val dto = ResearchProjectsInputDto(
            listOf(
                ResearchProjectInputDto(
                    title = "研究課題2"
                )
            )
        )
        every { driver.researchProjectsById() } returns dto
        val sut = ResearchProjectsUsecase(driver)
        val expected = ResearchProjectsDto(
            listOf(
                ResearchProjectDto(
                    title = "研究課題2"
                )
            )
        )
        assertEquals(expected, sut.execute())
    }
}