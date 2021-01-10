/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *          http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.scallop.data.fakes

import com.scallop.data.utils.getJson
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

/**
 * Handles Requests from mock web server
 */
internal class ItunesApiDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/search?entity=musicArtist&term=rolling&offset=0&limit=20" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/artists.json"))
            }
            "/search?entity=album&term=rolling&offset=0&limit=20" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/albums.json"))
            }
            "/search?entity=song&attribute=albumTerm&term=rolling&offset=0&limit=20" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/songs.json"))
            }
            "/search?entity=musicVideo&attribute=songTerm&term=chojin&offset=0&limit=20" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/video.json"))
            }
            "/search?entity=musicVideo&attribute=songTerm&term=not_found&offset=0&limit=20" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/not_found.json"))
            }
            else -> throw IllegalArgumentException("Unknown Request Path ${request.path.toString()}")
        }
    }

}