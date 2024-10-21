package server;

public enum HttpMessage {
    HTTP_RESPONSE("""
            HTTP/1.1 200 OK
            Content-Type: application/json
            Content-Length: %d
            
            %s
            """),
    RESULT_JSON("""
            {
                "x": "%s",
                "y": "%s",
                "r": "%s",
                "time": "%s",
                "date": "%s",
                "result": %b
            }
            """);



    private final String template;

    HttpMessage(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }
}
