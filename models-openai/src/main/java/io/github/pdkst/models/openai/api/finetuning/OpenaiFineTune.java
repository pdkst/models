package io.github.pdkst.models.openai.api.finetuning;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.request.HttpRequest;
import io.github.pdkst.models.openai.api.finetuning.request.CreateFineTuneRequest;
import io.github.pdkst.models.openai.api.finetuning.response.FineTuneJobObject;
import io.github.pdkst.models.openai.api.finetuning.response.ListFineTuneCheckPointsResponse;
import io.github.pdkst.models.openai.api.finetuning.response.ListFineTuneEventsResponse;
import io.github.pdkst.models.openai.api.finetuning.response.ListFineTuneJobsResponse;
import lombok.RequiredArgsConstructor;

/**
 * Manage fine-tuning jobs to tailor a model to your specific training data.
 * <p>
 * Related guide: <a href="https://platform.openai.com/docs/guides/fine-tuning">Fine-tune models</a>
 *
 * @author pdkst
 * @see <a href="https://platform.openai.com/docs/api-reference/fine-tuning">Fine-tuning API documentation</a>
 * @since 2024/04/07
 */
@RequiredArgsConstructor
public class OpenaiFineTune {
    private final HttpExchanger httpExchanger;

    /**
     * Creates a job that fine-tunes a specified model from a given dataset.
     * <p>
     * Response includes details of the enqueued job including job status and the name of the fine-tuned models once
     * complete.
     * <p>
     * <a href="https://platform.openai.com/docs/guides/fine-tuning">Learn more about fine-tuning</a>
     *
     * @param request request
     * @return response
     * @throws Exception exception
     */
    public FineTuneJobObject createFineTune(CreateFineTuneRequest request) throws Exception {
        final HttpRequest post = HttpRequest.post("/fine_tuning/jobs");
        post.json(request);
        final HttpResponse response = httpExchanger.exchange(post);
        return response.body(FineTuneJobObject.class);
    }

    /**
     * List your organization's fine-tuning jobs
     *
     * @param after Identifier for the last job from the previous pagination request. default null
     * @param limit Optional Number of fine-tuning jobs to retrieve. Defaults to 20.
     * @return A list of fine-tuning jobs that belong to the user's organization.
     * @throws Exception io
     */
    public ListFineTuneJobsResponse listFineTuneJobs(String after, Integer limit) throws Exception {
        final HttpRequest get = HttpRequest.get("/fine_tuning/jobs");
        get.query("after", after);
        get.query("limit", limit);
        final HttpResponse response = httpExchanger.exchange(get);
        return response.body(ListFineTuneJobsResponse.class);
    }

    /**
     * Get info about a fine-tuning job.
     *
     * @param fineTuneId The ID of the fine-tuning job.
     * @return The fine-tuning object with the given ID.
     * @throws Exception io
     */
    public FineTuneJobObject retrieveFineTune(String fineTuneId) throws Exception {
        final HttpRequest get = HttpRequest.get("/fine_tuning/jobs/" + fineTuneId);
        final HttpResponse response = httpExchanger.exchange(get);
        return response.body(FineTuneJobObject.class);
    }

    /**
     * Immediately cancel a fine-tune job.
     *
     * @param fineTuneId The ID of the fine-tune job to cancel.
     * @return The fine-tuning object with the given ID.
     * @throws Exception io
     */
    public FineTuneJobObject cancelFineTune(String fineTuneId) throws Exception {
        final HttpRequest post = HttpRequest.post("/fine_tuning/jobs/" + fineTuneId + "/cancel");
        final HttpResponse response = httpExchanger.exchange(post);
        return response.body(FineTuneJobObject.class);
    }

    /**
     * Get status updates for a fine-tuning job.
     *
     * @param fineTuneId The ID of the fine-tune job.
     * @return A list of fine-tuning checkpoint objects for a fine-tuning job.
     * @throws Exception io
     */
    public ListFineTuneEventsResponse listFineTuneEvents(String fineTuneId) throws Exception {
        final HttpRequest get = HttpRequest.get("/fine_tuning/jobs/" + fineTuneId + "/events");
        final HttpResponse response = httpExchanger.exchange(get);
        return response.body(ListFineTuneEventsResponse.class);
    }

    /**
     * List checkpoints for a fine-tuning job.
     *
     * @param fineTuneId The ID of the fine-tune job.
     * @return A list of fine-tuning checkpoint objects for a fine-tuning job.
     * @throws Exception io
     */
    public ListFineTuneCheckPointsResponse listFineTuneCheckPoints(String fineTuneId) throws Exception {
        final HttpRequest get = HttpRequest.get("/fine_tuning/jobs/" + fineTuneId + "/checkpoints");
        final HttpResponse response = httpExchanger.exchange(get);
        return response.body(ListFineTuneCheckPointsResponse.class);
    }
}
