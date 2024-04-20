package io.github.pdkst.models.server.embeddings;

import io.github.pdkst.models.openai.api.embeddings.request.EmbeddingsRequest;
import io.github.pdkst.models.openai.api.embeddings.response.EmbeddingsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author pdkst
 * @since 2024/04/20
 */
@Slf4j
@RestController
@RequestMapping("/v1/embeddings")
@RequiredArgsConstructor
public class EmbeddingsController {
    private final FileOpenaiEmbeddings embeddings;

    @PostMapping("")
    public EmbeddingsResponse embeddings(@RequestBody EmbeddingsRequest request) throws IOException {
        return embeddings.embeddings(request);
    }
}
