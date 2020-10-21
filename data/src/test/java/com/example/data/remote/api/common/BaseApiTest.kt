package com.example.data.remote.api.common

import com.example.data.remote.response.ServerErrorResponse
import com.google.gson.Gson
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import java.io.File

@RunWith(JUnit4::class)
abstract class BaseApiTest {

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

    fun assertHttpException(exception: Exception, expected: ServerErrorResponse) {
        if (exception is HttpException) {
            val errorBody = exception.response()?.errorBody()?.string()
            val actual = Gson().fromJson(errorBody, ServerErrorResponse::class.java)
            Assert.assertEquals(expected, actual)
        } else {
            Assert.assertTrue(false)
        }
    }

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