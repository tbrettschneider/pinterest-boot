package de.tb.showroom.pinterest.util;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {

    public void copyStreams(final InputStream is, final OutputStream os) {
        try {
            StreamUtils.copy(is, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}