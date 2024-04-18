package io.github.pdkst.models.openai.api.files.request;

import io.github.pdkst.models.common.Response;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The File object represents a document that has been uploaded to OpenAI.
 *
 * @author pdkst
 * @since 2023/11/02
 */
@Data
@NoArgsConstructor
public class FileObject extends Response {
    /**
     * The file identifier, which can be referenced in the API endpoints.
     */
    private String id;
    /**
     * The object type, which is always "file".
     */
    private String object;
    /**
     * The size of the file in bytes.
     */
    private Integer bytes;
    /**
     * The Unix timestamp (in seconds) for when the file was created.
     */
    private Long created_at;
    /**
     * The name of the file.
     */
    private String filename;
    /**
     * The intended purpose of the file. Supported values are {@code fine-tune} or {@code fine-tune-results}.
     */
    private String purpose;
}
