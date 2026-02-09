package com.samuelminana.tema4gradle;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class Main {
    public static void main(String[] args) {

        var model = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey("ollama")          // en local da igual
                .modelName("gemma:2b")
                .build();

        String respuesta = model.chat("Cuéntame un chiste");
        System.out.println(respuesta);
    }
}
