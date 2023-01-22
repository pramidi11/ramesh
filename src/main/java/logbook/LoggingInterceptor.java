package logbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import org.apache.commons.logging.Log;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.logging.Logger;

public class LoggingInterceptor implements Interceptor {

//    Logger logger = Logger.getLogger("LoggingIntercept");
    org.slf4j.Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    private static String stringifyRequestBody(Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        logger.info(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
        logger.info(stringifyRequestBody(request));

        Response response = chain.proceed(request);
        System.out.println(response.isSuccessful());

        long t2 = System.nanoTime();
        logger.info(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        assert response.body() != null;
        logger.info("response is " + response.code());
        return response;
    }
}
