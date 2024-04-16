package io.github.pdkst.models.openai.api.batch;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.request.HttpRequest;
import io.github.pdkst.models.openai.api.batch.request.CreateBatchRequest;
import io.github.pdkst.models.openai.api.batch.response.BatchObject;
import lombok.RequiredArgsConstructor;

/**
 * Create large batches of API requests to run asyncronously.
 *
 * @author pdkst
 * @see <a href="https://platform.openai.com/docs/api-reference/batch">Batch</a>
 * @since 2024/04/16
 */
@RequiredArgsConstructor
public class OpenaiBatch {
    private final HttpExchanger httpExchanger;

    /**
     * Creates and executes a batch from an uploaded file of requests
     *
     * @param request the request
     * @return the <a href="https://platform.openai.com/docs/api-reference/batch/object">batch</a> object
     * @see <a href="https://platform.openai.com/docs/api-reference/batch/create">create Batch</a>
     */
    public BatchObject createBatch(CreateBatchRequest request) throws Exception {
        final HttpRequest httpRequest = HttpRequest.post("/batches")
                .json(request);
        final HttpResponse response = httpExchanger.exchange(httpRequest);
        return response.body(BatchObject.class);
    }

    /**
     * Retrieves a batch.
     *
     * @param batchId The ID of the batch to retrieve.
     * @return The <a href="https://platform.openai.com/docs/api-reference/batch/object">Batch</a>
     * object matching the specified ID.
     * @see <a href="https://platform.openai.com/docs/api-reference/batch/retrieve">Retrieve Batch</a>
     */
    public BatchObject retrieveBatch(String batchId) throws Exception {
        final HttpRequest httpRequest = HttpRequest.get("/batches/{batchId}")
                .pathVariable("batchId", batchId);
        final HttpResponse response = httpExchanger.exchange(httpRequest);
        return response.body(BatchObject.class);
    }

    /**
     * Cancels a batch.
     *
     * @param batchId The ID of the batch to cancel.
     * @return The <a href="https://platform.openai.com/docs/api-reference/batch/object">Batch</a>
     * object matching the specified ID.
     * @see <a href="https://platform.openai.com/docs/api-reference/batch/cancel">Cancel Batch</a>
     */
    public BatchObject cancelBatch(String batchId) throws Exception {
        final HttpRequest httpRequest = HttpRequest.post("/batches/{batchId}/cancel")
                .pathVariable("batchId", batchId);
        final HttpResponse response = httpExchanger.exchange(httpRequest);
        return response.body(BatchObject.class);
    }
}
