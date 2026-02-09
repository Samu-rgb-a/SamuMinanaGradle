package com.samuelminana.tema4gradle;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final String TOKEN = "ollama";

        var model = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("gemma:2b")
                .build();

        List<ChatMessage> history = new ArrayList<>();

        history.add(UserMessage.from("Hola, soy Samuel"));
        AiMessage respuesta = model.chat(history).aiMessage();
        history.add(respuesta);

        history.add(UserMessage.from("¿Recuerdas cómo me llamo?"));
        respuesta = model.chat(history).aiMessage();
        history.add(respuesta);

        System.out.println(respuesta.text());
    }
}
