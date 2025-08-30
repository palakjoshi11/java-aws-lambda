package org.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.extern.slf4j.Slf4j;
import org.lambda.handler.DefaultExecutionContext;

import java.util.Map;

@Slf4j
public class Handler implements RequestHandler<Map<String,Object>, Map<String,Object>> {

    private final ExecutionContext executionContext;

    // When we run the lambda, it expect the handler to have a no-args constructor
    public Handler() {
        this.executionContext = new DefaultExecutionContext();
    }

    public Handler(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }


    @Override
    public Map<String,Object> handleRequest(Map<String,Object> event, Context context) {
        log.info("At handle Request {}",event);
        executionContext.getRequestDispatch().handler(event, context);
        return Map.of("request","handled");
    }

}
