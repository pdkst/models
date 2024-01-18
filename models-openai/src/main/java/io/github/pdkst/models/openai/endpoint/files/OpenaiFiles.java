package io.github.pdkst.models.openai.endpoint.files;

import io.github.pdkst.models.annotation.Required;
import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.request.HttpMethod;
import io.github.pdkst.models.http.request.HttpRequest;
import io.github.pdkst.models.openai.endpoint.files.request.FileObject;
import io.github.pdkst.models.openai.endpoint.files.request.UploadFileRequest;
import io.github.pdkst.models.openai.endpoint.files.response.DeleteFileResponse;
import io.github.pdkst.models.openai.endpoint.files.response.ListFilesResponse;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;

/**
 * Files are used to upload documents
 * that can be used with features like
 * <a href="https://platform.openai.com/docs/api-reference/fine-tuning">fine-tuning</a> .
 *
 * @author pdkst.zhang
 * @since 2023/11/02
 */
@RequiredArgsConstructor
public class OpenaiFiles {
    private final HttpExchanger exchanger;

    /**
     * Returns a list of files that belong to the user's organization.
     *
     * @param purpose Only return files with the given purpose.
     * @return A list of File objects.
     * @throws Exception if the request fails.
     */
    public ListFilesResponse listFiles(String purpose) throws Exception {
        if (purpose != null) {
            return listFilesPurposeApi(purpose);
        } else {
            return listFilesApi();
        }
    }

    private ListFilesResponse listFilesPurposeApi(String purpose) throws Exception {
        final HttpRequest get = HttpRequest.get("/files")
                .form("purpose", purpose);
        final HttpResponse response = exchanger.exchange(get);
        return response.body(ListFilesResponse.class);
    }

    private ListFilesResponse listFilesApi() throws Exception {
        final HttpRequest get = HttpRequest.get("/files");
        final HttpResponse response = exchanger.exchange(get);
        return response.body(ListFilesResponse.class);
    }

    /**
     * Upload a file that can be used across various endpoints/features.
     * Currently, the size of all the files uploaded by one organization can be up to 1 GB.
     * Please contact us if you need to increase the storage limit.
     *
     * @param request files request
     * @return The uploaded <a href="https://platform.openai.com/docs/api-reference/files/object">file</a> object.
     */
    public FileObject uploadFile(UploadFileRequest request) throws Exception {
        final String purpose = request.getPurpose();
        final HttpRequest httpRequest = HttpRequest.create()
                .method(HttpMethod.POST)
                .url("/files")
                .file(request.getFile())
                .form("purpose", purpose);
        final HttpResponse response = exchanger.exchange(httpRequest);
        return response.body(FileObject.class);
    }

    /**
     * Delete a file.
     *
     * @param fileId The ID of the file to use for this request.
     * @return Deletion status.
     */
    public DeleteFileResponse deleteFile(String fileId) throws Exception {
        final HttpRequest delete = HttpRequest.delete("/files/" + fileId);
        final HttpResponse response = exchanger.exchange(delete);
        return response.body(DeleteFileResponse.class);
    }

    /**
     * Returns information about a specific file.
     *
     * @param fileId The ID of the file to use for this request.
     * @return The file object matching the specified ID.
     */
    public FileObject retrieveFile(@Required String fileId) throws Exception {
        final HttpRequest get = HttpRequest.get("/files/" + fileId);
        final HttpResponse response = exchanger.exchange(get);
        return response.body(FileObject.class);
    }

    /**
     * Returns the contents of the specified file.
     *
     * @param fileId The ID of the file to use for this request.
     * @return The file content.
     */
    public InputStream retrieveFileContent(@Required String fileId) throws Exception {
        final HttpRequest get = HttpRequest.get("/files/" + fileId + "/content");
        final HttpResponse response = exchanger.exchange(get);
        return response.byteStream();
    }
}
