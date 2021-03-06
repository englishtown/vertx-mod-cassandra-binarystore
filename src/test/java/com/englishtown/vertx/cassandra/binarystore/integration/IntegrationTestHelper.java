/*
* The MIT License (MIT)
* Copyright © 2013 Englishtown <opensource@englishtown.com>
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the “Software”), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

package com.englishtown.vertx.cassandra.binarystore.integration;

//import com.englishtown.vertx.cassandra.binarystore.impl.CassandraBinaryStore;
import io.vertx.core.json.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
* Base verticle for Cassandra binary store integration tests
*/
public class IntegrationTestHelper {

    public static final String DEFAULT_CONTENT_TYPE = "image/jpeg";
    public static final String DEFAULT_FILENAME = "test_file.jpg";
    public static final Integer DEFAULT_CHUNK_SIZE = 102400;
    public static final Integer DEFAULT_LENGTH = 161966;

//    public static JsonObject onVerticleStart(final Verticle verticle, final Future<Void> startedResult) {
//
//        JsonObject config = loadConfig();
//        verticle.getContainer().deployVerticle(CassandraBinaryStore.class.getName(), config, result -> {
//            if (result.succeeded()) {
//                startedResult.setResult(null);
//                verticle.start();
//            } else {
//                startedResult.setFailure(result.cause());
//            }
//        });
//
//        return config;
//
//    }

    public static JsonObject loadConfig() {

        try (InputStream stream = IntegrationTestHelper.class.getResourceAsStream("/config.json")) {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

            String line = reader.readLine();
            while (line != null) {
                sb.append(line).append('\n');
                line = reader.readLine();
            }

            return new JsonObject(sb.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

//    public static void verifyErrorReply(Message<JsonObject> message, String error) {
//        assertEquals("error", message.body().getString("status"));
//        assertEquals(error, message.body().getString("message"));
//        testComplete();
//    }

}
