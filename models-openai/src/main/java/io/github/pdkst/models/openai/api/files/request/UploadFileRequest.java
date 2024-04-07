package io.github.pdkst.models.openai.api.files.request;

import io.github.pdkst.models.common.Request;
import lombok.Data;

import java.io.File;

/**
 * Upload a file that can be used across various endpoints/features.
 * Currently, the size of all the files uploaded by one organization can be up to 1 GB.
 * Please contact us if you need to increase the storage limit.
 *
 * @author pdkst.zhang
 * @since 2023/11/02
 */
@Data
public class UploadFileRequest extends Request {
    /**
     * The file object (not file name) to be uploaded.
     * <p>
     * If the purpose is set to "fine-tune", the file will be used for fine-tuning.
     */
    private File file;
    /**
     * The intended purpose of the uploaded file.
     * <p>
     * Use "fine-tune" for <a href="https://platform.openai.com/docs/api-reference/fine-tuning">fine-tuning</a>.
     * This allows us to validate the format of the uploaded file is correct for  fine-tuning.
     */
    private String purpose;
}
