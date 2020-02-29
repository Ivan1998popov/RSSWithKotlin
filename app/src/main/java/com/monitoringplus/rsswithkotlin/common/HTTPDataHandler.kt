package com.monitoringplus.rsswithkotlin.common

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HTTPDataHandler{

    companion object {
        var stream: String = ""
    }

    public fun getHTTPDataHandler(urlString: String): String {
        try {
            val url: URL = URL(urlString)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

            if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream: InputStream = BufferedInputStream(urlConnection.inputStream)

                val r: BufferedReader = BufferedReader(InputStreamReader(inputStream))
                val sb: StringBuilder = StringBuilder()
                var line: String?

                do {
                    line = r.readLine()
                    if (line == null)
                        break
                    sb.append(line)
                } while (true)
                stream = sb.toString()
                urlConnection.disconnect()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return stream
    }

}