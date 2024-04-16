package io.github.pdkst.models.openai.api.chat.listener;

import io.github.pdkst.models.openai.api.chat.request.FunctionCall;
import io.github.pdkst.models.openai.api.chat.request.ToolCall;
import io.github.pdkst.models.openai.api.chat.response.ChunkChoice;
import io.github.pdkst.models.openai.api.chat.response.ChunkChoiceDelta;
import io.github.pdkst.models.openai.api.chat.response.CompletionChunkResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * @author pdkst
 * @since 2024/04/05
 */
public abstract class CachedFunctionOpenEventReceiver extends CachedOpenaiEventReceiver {
    @Override
    public void onDone() {
        final CompletionChunkResponse cached = getCached();
        processContent(cached);
        final Map<String, String> resultMap = invokeFunctions(cached);
        processCallResult(resultMap);
    }

    private Map<String, String> invokeFunctions(CompletionChunkResponse cached) {
        final Map<String, String> resultMap = new HashMap<>();
        if (cached == null) {
            return resultMap;
        }
        final List<ChunkChoice> choices = cached.getChoices();
        if (isEmpty(choices)) {
            return resultMap;
        }
        for (ChunkChoice choice : choices) {
            final ChunkChoiceDelta delta = choice.getDelta();
            invokeFunctionDelta(delta, resultMap);
        }
        return resultMap;
    }

    private void invokeFunctionDelta(ChunkChoiceDelta delta, Map<String, String> resultMap) {
        if (delta == null) {
            return;
        }
        final FunctionCall functionCall = delta.getFunction_call();
        if (functionCall != null) {
            String result = invokeFunctionCall(functionCall);
            resultMap.put(functionCall.getName(), result);
        }
        final List<ToolCall> toolCalls = delta.getTool_calls();
        if (CollectionUtils.isNotEmpty(toolCalls)) {
            invokeToolCalls(toolCalls, resultMap);
        }
    }

    protected String invokeFunctionCall(@Nullable FunctionCall functionCall) {
        return invokeFunction(functionCall);
    }

    protected String invokeFunction(@Nullable FunctionCall functionCall) {
        if (functionCall == null) {
            return null;
        }
        final String name = functionCall.getName();
        final String arguments = functionCall.getArguments();
        return invokeCall(name, arguments);
    }

    private void invokeToolCalls(List<ToolCall> toolCalls, Map<String, String> resultMap) {
        if (isEmpty(toolCalls)) {
            return;
        }
        for (ToolCall toolCall : toolCalls) {
            if (toolCall == null) {
                continue;
            }
            final FunctionCall function = toolCall.getFunction();
            final String result = invokeFunction(function);
            resultMap.put(toolCall.getId(), result);
        }
    }

    /**
     * 执行实际的方法
     *
     * @param name      方法名
     * @param arguments 方法参数（一般是json）
     * @return call结果
     */
    public abstract String invokeCall(String name, String arguments);

    public void processContent(CompletionChunkResponse cached) {
        final List<ChunkChoice> choices = cached.getChoices();
        for (ChunkChoice choice : choices) {
            if (choice == null) {
                continue;
            }
            processDeltaContent(choice.getDelta());
        }
    }

    private void processDeltaContent(ChunkChoiceDelta delta) {
        if (delta == null) {
            return;
        }
        final String role = delta.getRole();
        final String content = delta.getContent();
        if (content == null) {
            return;
        }
        processContent(role, content);
    }

    /**
     * 当大模型返回内容时，处理内容
     *
     * @param role    返回角色
     * @param content 大模型文字内容
     */
    public abstract void processContent(String role, String content);


    /**
     * 处理call结果的map，key是function的name或者toolCall的id，value是方法结果
     *
     * @param resultMap 方法结果
     */
    public abstract void processCallResult(Map<String, String> resultMap);
}
