package io.github.pdkst.models.openai.endpoint.audio;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.request.HttpRequest;
import io.github.pdkst.models.openai.endpoint.audio.request.CreateSpeechRequest;
import io.github.pdkst.models.openai.endpoint.audio.request.CreateTranscriptionRequest;
import io.github.pdkst.models.openai.endpoint.audio.request.CreateTranslationRequest;
import io.github.pdkst.models.openai.endpoint.audio.response.TranscriptionResponse;
import io.github.pdkst.models.openai.endpoint.audio.response.TranscriptionVerboseResponse;
import io.github.pdkst.models.openai.endpoint.audio.response.TranslationResponse;
import lombok.RequiredArgsConstructor;

/**
 * Learn how to turn audio into text or text into audio.
 * <p>
 * Related guide: <a href="https://platform.openai.com/docs/guides/speech-to-text">Speech to text</a>
 *
 * @author pdkst.zhang
 * @see <a href="https://platform.openai.com/docs/api-reference/audio">Audio API</a>
 * @since 2024/04/05
 */
@RequiredArgsConstructor
public class OpenaiAudio {
    private final HttpExchanger httpExchanger;

    /**
     * Generates audio from the input text.
     *
     * @param request request
     * @return response
     * @throws Exception io
     */
    public HttpResponse createSpeech(CreateSpeechRequest request) throws Exception {
        final HttpRequest post = HttpRequest.post("/audio/speech");
        post.json(request);
        return httpExchanger.exchange(post);
    }

    /**
     * Transcribes audio into the input language.
     *
     * @param request request
     * @return response
     * @throws Exception io
     */
    public TranscriptionResponse createTranscription(CreateTranscriptionRequest request) throws Exception {
        final HttpRequest post = HttpRequest.post("/audio/transcriptions");
        post.json(request);
        final HttpResponse response = httpExchanger.exchange(post);
        return response.body(TranscriptionResponse.class);
    }

    /**
     * Transcribes audio into the input language.
     *
     * @param request request
     * @return response
     * @throws Exception io
     */
    public TranscriptionVerboseResponse createTranscriptionVerbose(CreateTranscriptionRequest request) throws Exception {
        final HttpRequest post = HttpRequest.post("/audio/transcriptions");
        post.json(request);
        final HttpResponse response = httpExchanger.exchange(post);
        return response.body(TranscriptionVerboseResponse.class);
    }

    /**
     * Translates audio into English.
     *
     * @param request request
     * @return response
     * @throws Exception io
     */
    public TranslationResponse createTranslate(CreateTranslationRequest request) throws Exception {
        final HttpRequest post = HttpRequest.post("/audio/translations");
        post.json(request);
        final HttpResponse response = httpExchanger.exchange(post);
        return response.body(TranslationResponse.class);
    }
}
