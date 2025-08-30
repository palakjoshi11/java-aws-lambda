FROM public.ecr.aws/lambda/java:21.2024.05.15.12
RUN dnf -y upgrade ca-certificates python python-libs libxml2 zlib

COPY target/classes ${LAMBDA_TASK_ROOT}
COPY target/dependency/* ${LAMBDA_TASK_ROOT}/lib/

CMD [ "org.lambda.Handler::handleRequest" ]