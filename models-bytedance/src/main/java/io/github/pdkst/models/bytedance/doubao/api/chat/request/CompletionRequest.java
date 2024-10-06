package io.github.pdkst.models.bytedance.doubao.api.chat.request;

import io.github.pdkst.models.annotation.Required;
import io.github.pdkst.models.common.Request;
import io.github.pdkst.models.openai.api.chat.request.Message;
import io.github.pdkst.models.openai.api.chat.request.StreamOptions;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * {@code POST https://api.openai.com/v1/chat/completions}
 * <p>
 * Creates a model response for the given chat conversation.
 *
 * @author pdkst
 * @see <a href="https://platform.openai.com/docs/api-reference/chat/create">Create chat completion</a>
 * @since 2023/07/30
 */
@Data
@NoArgsConstructor
public class CompletionRequest extends Request {
    /**
     * 以 endpoint_id 索引对应的模型接入点。
     */
    @Required
    private String model;

    /**
     * 是否流式返回。如果为 true，则按 SSE 协议返回数据。
     * <p>Defaults to false</p>
     */
    private Boolean stream;

    /**
     * 本次对话的消息列表，包含用户输入的最后一条消息。
     */
    @Required
    private List<Message> messages;

    /**
     * 修改指定 token 在模型输出内容中出现的概率。 接受一个 map，该对象将 token(token id 使用 tokenization 接口获取)
     * 映射到从-100到100的关联偏差值。每个模型的效果有所不同，但-1和1之间的值会减少或增加选择的可能性；-100或100应该导致禁止或排他选择相关的 token。
     */
    private Map<String, Object> logit_bias;

    /**
     * 是否返回输出 tokens 的 logprobs。如果为 true，则返回 message (content) 中每个输出 token 的 logprobs。
     */
    private Boolean logprobs;

    /**
     * 0 到 20 之间的整数，指定每个 token 位置最有可能返回的token数量，每个token 都有关联的对数概率。 如果使用此参数，则 logprobs 必须设置为 true
     */
    private Integer top_logprobs;

    /**
     * 模型最大输出 token 数。
     * 输入 token 和输出 token 的总长度还受模型的上下文长度限制。
     */
    private Integer max_tokens;

    /**
     * -2.0 到 2.0 之间的数字。如果为正，会根据新 token 在文本中的出现频率对其进行惩罚，从而降低模型重复相同内容的可能性。
     */
    private Float frequency_penalty;

    /**
     * Number between -2.0 and 2.0.
     * Positive values penalize new tokens based on whether they appear in the text so far,
     * increasing the model's likelihood to talk about new topics.
     * <p>Defaults to 0</p>
     */
    private Float presence_penalty;

    /**
     * This feature is in Beta. If specified, our system will make a best effort to sample deterministically, such
     * that repeated requests with the same seed and parameters should return the same result. Determinism is not
     * guaranteed, and you should refer to the system_fingerprint response parameter to monitor changes in the backend.
     */
    private Integer seed;

    /**
     * 用于指定模型在生成响应时应停止的词语。当模型生成的响应中包含这些词汇时，生成过程将停止。
     * <p>string / array / null; Defaults to null</p>
     */
    private List<String> stop;

    /**
     * 采样温度在0到2之间。较高的值(如0.8)将使输出更加随机，而较低的值(如0.2)将使输出更加集中和确定。
     * 通常建议修改 temperature 或 top_p，但不建议两者都修改。
     * <p>Defaults to 1</p>
     */
    private Float temperature;

    /**
     * temperature 抽样的另一种选择，称为核抽样，其中模型考虑具有 top_p 概率质量的 token。所以 0.1 意味着只考虑包含前 10% 概率质量的标记。
     * 一般建议修改 top_p 或 temperature，但不建议两者都修改
     * <p>Defaults to 1</p>
     */
    private Float top_p;

    /**
     * stream=true 时可以设置这个参数。
     */
    private StreamOptions stream_options;

    public void messages(Message... messages) {
        this.messages = Arrays.asList(messages);
    }
}
