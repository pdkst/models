package io.github.pdkst.models.openai.api.files.response;

import io.github.pdkst.models.common.Response;
import io.github.pdkst.models.openai.api.files.request.FileObject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Returns a list of files that belong to the user's organization.
 *
 * @author pdkst.zhang
 * @since 2023/11/02
 */
@Data
@NoArgsConstructor
public class ListFilesResponse extends Response {
    /**
     * list
     */
    private String object;
    /**
     * file objects
     */
    private List<FileObject> data;
}
