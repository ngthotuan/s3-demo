FROM bellsoft/liberica-openjdk-debian:21.0.2

# receive CI token to pull outside git repo config
ARG APPENV
ARG PROJECT_NAME
ENV ENV_APPENV=$APPENV
ENV ENV_PROJECT_NAME=$PROJECT_NAME
WORKDIR /app
EXPOSE 8080
COPY target ./target
COPY start.sh ./
RUN ls ./target/*
RUN chmod +x start.sh
ENTRYPOINT ./start.sh
