import com.vinyl.app.pojo.Album
import com.vinyl.app.retrofit.VinylApi
import com.vinyl.app.viewmodel.HomeViewModel
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class VinylApiTest2 {

    @Mock
    private lateinit var vinylApi: VinylApi
    private lateinit var viewModel: HomeViewModel
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

        viewModel = HomeViewModel()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testingGetAlbums() = runBlocking {
        val response = MockResponse().setResponseCode(200).setBody(
            """
        [
            {
                "id": 103,
                "name": "A Day at the Races",
                "cover": "https://www.udiscovermusic.com/wp-content/uploads/2019/11/a-day-at-the-races.jpg",
                "releaseDate": "1976-12-10T00:00:00.000Z",
                "description": "El álbum fue grabado en los Estudios Sarm West, The Manor and Wessex en Inglaterra y con el ingeniero Mike Stone. El título del álbum es una referencia directa al anterior, A Night at the Opera. Ambos álbumes están titulados como películas de los hermanos Marx.",
                "genre": "Rock",
                "recordLabel": "EMI",
                "tracks": [],
                "performers": [
                    {
                        "id": 101,
                        "name": "Queen",
                        "image": "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg",
                        "description": "Queen es una banda británica de rock formada en 1970 en Londres por el cantante Freddie Mercury, el guitarrista Brian May, el baterista Roger Taylor y el bajista John Deacon. Si bien el grupo ha presentado bajas de dos de sus miembros (Mercury, fallecido en 1991, y Deacon, retirado en 1997), los integrantes restantes, May y Taylor, continúan trabajando bajo el nombre Queen, por lo que la banda aún se considera activa.",
                        "creationDate": "1970-01-01T00:00:00.000Z"
                    }
                ],
                "comments": []
            }
        ]
        """
        )

        mockWebServer.enqueue(response)

        val call: Call<List<Album>> = vinylApi.getAlbums()
        val albumList: List<Album>? = call.execute().body()

        Assert.assertNotNull(albumList)
        Assert.assertTrue(albumList!!.isNotEmpty())

        val album1 = albumList[0]
        Assert.assertEquals("103", album1.id)
        Assert.assertEquals("A Day at the Races", album1.name)
        Assert.assertEquals("Rock", album1.genre)
        Assert.assertEquals("EMI", album1.recordLabel)
        Assert.assertEquals("1976-12-10T00:00:00.000Z", album1.releaseDate)

    }
}