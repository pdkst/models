package io.github.pdkst.models.openai.endpoint.chat.listener;

import io.github.pdkst.models.openai.endpoint.chat.request.FunctionCall;
import io.github.pdkst.models.openai.endpoint.chat.request.ToolCall;
import io.github.pdkst.models.openai.endpoint.chat.response.ChunkChoice;
import io.github.pdkst.models.openai.endpoint.chat.response.ChunkChoiceDelta;
import io.github.pdkst.models.openai.endpoint.chat.response.CompletionChunkResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang3.StringUtils.join;

/**
 * 缓存openai内容
 *
 * @author pdkst.zhang
 * @since 2024/03/12
 */
@Slf4j
@RequiredArgsConstructor
public class CachedOpenEventReceiver implements OpenaiEventReceiver {
    private final CompletionChunkResponse cached = new CompletionChunkResponse();

    @Override
    public void onEvent(CompletionChunkResponse next) {
        mergeResponse(cached, next);
    }

    private void mergeResponse(CompletionChunkResponse response, CompletionChunkResponse next) {
        final String id = defaultIfNull(response.getId(), next.getId());
        response.setId(id);
        response.setObject(defaultIfNull(response.getObject(), next.getObject()));
        response.setCreated(defaultIfNull(response.getCreated(), next.getCreated()));
        response.setModel(defaultIfNull(response.getModel(), next.getModel()));
        response.setUsage(defaultIfNull(response.getUsage(), next.getUsage()));
        final String systemFingerprint = defaultIfEmpty(response.getSystem_fingerprint(), next.getSystem_fingerprint());
        response.setSystem_fingerprint(systemFingerprint);
        final List<ChunkChoice> chunkChoices = mergeChoices(response.getChoices(), next.getChoices());
        response.setChoices(chunkChoices);
    }

    private List<ChunkChoice> mergeChoices(List<ChunkChoice> choices, List<ChunkChoice> nextChoices) {
        if (nextChoices == null) {
            return choices;
        }
        if (nextChoices.isEmpty()) {
            return choices;
        }
        if (choices == null) {
            choices = new ArrayList<>();
        }
        if (choices.isEmpty()) {
            choices.add(new ChunkChoice());
        }
        mergeChoice(choices.get(0), nextChoices.get(0));
        return choices;
    }

    private void mergeChoice(ChunkChoice choice, ChunkChoice nextChoice) {
        choice.setIndex(defaultIfNull(choice.getIndex(), nextChoice.getIndex()));
        choice.setFinish_reason(defaultIfNull(choice.getFinish_reason(), nextChoice.getFinish_reason()));
        choice.setLogprobs(defaultIfNull(choice.getLogprobs(), nextChoice.getLogprobs()));
        final ChunkChoiceDelta delta = mergeDelta(choice.getDelta(), nextChoice.getDelta());
        choice.setDelta(delta);
    }

    private ChunkChoiceDelta mergeDelta(ChunkChoiceDelta delta, ChunkChoiceDelta nextDelta) {
        if (nextDelta == null) {
            return delta;
        }
        if (delta == null) {
            delta = new ChunkChoiceDelta();
        }
        delta.setRole(defaultIfNull(delta.getRole(), nextDelta.getRole()));
        delta.setContent(join(delta.getContent(), nextDelta.getContent()));
        final FunctionCall functionCall = mergeFunctionCall(delta.getFunction_call(), nextDelta.getFunction_call());
        delta.setFunction_call(functionCall);
        delta.setTool_calls(mergeToolCalls(delta.getTool_calls(), nextDelta.getTool_calls()));
        return delta;
    }

    private FunctionCall mergeFunctionCall(FunctionCall function, FunctionCall nextFunction) {
        if (nextFunction == null) {
            return function;
        }
        if (function == null) {
            function = new FunctionCall();
        }
        final String functionName = join(function.getName(), nextFunction.getName());
        function.setName(functionName);
        final String arguments = join(function.getArguments(), nextFunction.getArguments());
        function.setArguments(arguments);
        return function;
    }

    private List<ToolCall> mergeToolCalls(List<ToolCall> toolCalls, List<ToolCall> nextToolCalls) {
        if (nextToolCalls == null || nextToolCalls.isEmpty()) {
            return toolCalls;
        }
        if (toolCalls == null) {
            toolCalls = new ArrayList<>();
        }
        for (ToolCall nextToolCall : nextToolCalls) {
            final Integer index = nextToolCall.getIndex();
            if (index >= toolCalls.size()) {
                final ToolCall toolCall = new ToolCall();
                mergeToolCall(toolCall, nextToolCall);
                toolCalls.add(toolCall);
            } else {
                final ToolCall toolCall = toolCalls.get(index);
                mergeToolCall(toolCall, nextToolCall);
            }
        }
        return null;
    }

    private void mergeToolCall(ToolCall toolCall, ToolCall nextToolCall) {
        toolCall.setIndex(nextToolCall.getIndex());
        toolCall.setId(nextToolCall.getId());
        toolCall.setType(nextToolCall.getType());
        final FunctionCall function = mergeFunctionCall(toolCall.getFunction(), nextToolCall.getFunction());
        toolCall.setFunction(function);
    }
}
