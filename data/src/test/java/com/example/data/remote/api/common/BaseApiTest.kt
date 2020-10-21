package com.example.data.remote.api.common

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.AutoCloseKoinTest
import java.io.File

@RunWith(JUnit4::class)
abstract class BaseApiTest : AutoCloseKoinTest() {

    lateinit var mockServer: MockWebServer

    open fun onStartMockServer(url: String) {}

    @Before
    open fun setUp() {
        configMockServer()
    }

    @After
    open fun tearDown() {
        mockServer.shutdown()
    }

    fun mockHttpResponse(
        responseCode: Int,
        fileName: String = "",
        path: String? = null,
        method: HttpMethod? = null
    ): String {
        val body = try {
            getJson(fileName)
        } catch (e: Exception) {
            ""
        }
        val response = MockResponse()
            .setHeader("Content-Type", "application/json")
            .setResponseCode(responseCode)
            .setBody(body)
        val dispatcher: Dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                if ((path != null && request.path != "/$path") || (method != null && request.method != method.name)) {
                    return MockResponse().setResponseCode(404)
                }
                return response
            }
        }
        mockServer.dispatcher = dispatcher
        return body
    }

    fun takeRequest(): RecordedRequest? = mockServer.takeRequest()

    private fun configMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
        onStartMockServer(mockServer.url("/").toString())
    }

    private fun getJson(path: String): String {
        val uri = javaClass.classLoader?.getResource(path)
        uri?.path?.let {
            val file = File(it)
            return String(file.readBytes())
        }
        return ""
    }
}