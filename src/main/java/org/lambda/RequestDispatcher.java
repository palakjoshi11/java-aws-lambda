package org.lambda;

import com.amazonaws.services.lambda.runtime.Context;

import java.util.Map;

public interface RequestDispatcher {

    void handler(Map<String,Object> event, Context context);
}
