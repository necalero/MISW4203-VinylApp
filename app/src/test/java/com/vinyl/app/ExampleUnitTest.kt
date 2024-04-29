package com.vinyl.app
import androidx.lifecycle.MutableLiveData
import com.vinyl.app.pojo.Album
import com.vinyl.app.pojo.Musician
import com.vinyl.app.retrofit.VinylApi
import com.vinyl.app.viewmodel.HomeViewModel
import com.vinyl.app.viewmodel.MusicianViewModel
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertNotNull
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.mock
import kotlin.collections.any
import org.junit.Assert.assertEquals
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class VinylApiTest {

    @Mock
    private lateinit var vinylApi: VinylApi
    private lateinit var viewModel: MusicianViewModel
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()

        vinylApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VinylApi::class.java)

        viewModel = MusicianViewModel()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testingNormalGet() = runBlocking {
        val response = MockResponse().setResponseCode(200).setBody(
            """
            [
                {
                    "id": 2,
                    "name": "Héctor Lavoe",
                    "image": "https://www.kienyke.com/sites/default/files/2023-06/Hector%20Lavoe.jpg",
                    "description": "Héctor Lavoe, también conocido como 'El Cantante de los Cantantes', fue un cantante de salsa puertorriqueño ampliamente considerado como uno de los más influyentes en la historia de la música latina. Nacido el 30 de septiembre de 1946 en Ponce, Puerto Rico, Lavoe se destacó por su voz poderosa y su estilo único. Su legado incluye éxitos como 'Periódico de ayer', 'El cantante' y 'Aquel lugar'. A pesar de su éxito, también enfrentó luchas personales, incluida la adicción a las drogas y problemas de salud. Falleció el 29 de junio de 1993 a la edad de 46 años."
                }
            ]
            """
        )

        mockWebServer.enqueue(response)

        val call: Call<List<Musician>> = vinylApi.getMusicians()
        val musicianList: List<Musician>? = call.execute().body()

        assertNotNull(musicianList)
        assertTrue(musicianList!!.isNotEmpty())

        val musician = musicianList[0]
        assertEquals(2, musician.id.toInt())
        assertEquals("Héctor Lavoe", musician.name)
        assertEquals("https://www.kienyke.com/sites/default/files/2023-06/Hector%20Lavoe.jpg", musician.image)
        assertEquals("Héctor Lavoe, también conocido como 'El Cantante de los Cantantes', fue un cantante de salsa puertorriqueño ampliamente considerado como uno de los más influyentes en la historia de la música latina. Nacido el 30 de septiembre de 1946 en Ponce, Puerto Rico, Lavoe se destacó por su voz poderosa y su estilo único. Su legado incluye éxitos como 'Periódico de ayer', 'El cantante' y 'Aquel lugar'. A pesar de su éxito, también enfrentó luchas personales, incluida la adicción a las drogas y problemas de salud. Falleció el 29 de junio de 1993 a la edad de 46 años.", musician.description)
    }
}





