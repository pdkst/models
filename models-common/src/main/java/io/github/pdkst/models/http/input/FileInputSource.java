package io.github.pdkst.models.http.input;

import io.github.pdkst.models.http.HttpInputSource;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @author pdkst
 * @since 2023/12/30
 */
@Data
@RequiredArgsConstructor
public class FileInputSource implements HttpInputSource {
    private final String name;
    private final File file;

    public FileInputSource(File file) {
        this.name = "file";
        this.file = file;
    }

    @Override
    public String name() {
        return ObjectUtils.defaultIfNull(name, "file");
    }

    @Override
    public File file() {
        return file;
    }

    @Override
    public InputStream inputStream() throws IOException {
        return inputStream(file);
    }

    private InputStream inputStream(File file) throws IOException {
        return Files.newInputStream(file.toPath());
    }
}
